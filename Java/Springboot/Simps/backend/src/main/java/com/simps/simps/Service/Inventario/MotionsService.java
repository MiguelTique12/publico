package com.simps.simps.Service.Inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Inventario.IMotionClassroomAmountDto;
import com.simps.simps.Dto.Inventario.IMotionsDto;
import com.simps.simps.Dto.Inventario.IValidateExistDto;
import com.simps.simps.Entity.Inventario.ElementsClassrooms;
import com.simps.simps.Entity.Inventario.Motions;
import com.simps.simps.Entity.Parametrizacion.Classrooms;
import com.simps.simps.Entity.Seguridad.Persons;
import com.simps.simps.IRepository.Inventario.IElementsClassroomsRepository;
import com.simps.simps.IRepository.Inventario.IMotionsRepository;
import com.simps.simps.IService.Inventario.IMotionsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class MotionsService extends ObjectTService<Motions> implements IMotionsService {

	@Autowired
	private IMotionsRepository repository;
	@Autowired
	private IElementsClassroomsRepository elementClassroomRepository;


	@Override
	public Page<IMotionsDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}
	
	@Transactional
	@Override
	public Motions save(@Valid Motions motions) throws Exception {
		Classrooms classrooms = motions.getClassroomsDestinationId();
		Long classroomId = classrooms.getId();

		Persons users = motions.getUserId();
		Long usersId = users.getId();

		ElementsClassrooms elementClassroomOne = motions.getElementClassroomId();
		if (elementClassroomOne != null) {
			Long elementClassroomId = elementClassroomOne.getId();

			Optional<IMotionsDto> op = repository.getValidate(elementClassroomId, motions.getAmount(),
					motions.getDate(), classroomId, usersId);
			if (op.isPresent() && op.get().getQuantity() >= 1) {
				throw new Exception("Validar datos, ya existe registro con este código o ruta.");
			}

			// Consulta la entidad ElementosSalones usando el elemento_salon_id de tu
			// movimiento
			ElementsClassrooms elementClassroom = elementClassroomRepository.findById(elementClassroomId).orElse(null);

			if (elementClassroom != null) {
				// Obtiene la cantidad de elementos_salones y de movimientos
				Integer amountElementClassroom = elementClassroom.getAmount();
				Integer amountMotion = motions.getAmount();

				// Verifica si hay suficientes elementos para el movimiento
				if (amountElementClassroom >= amountMotion) {
					// Actualiza la cantidad de elementos_salones restando la cantidad de
					// movimientos
					elementClassroom.setAmount(amountElementClassroom - amountMotion);
					elementClassroomRepository.save(elementClassroom);
					Motions savedMotions = repository.save(motions);
					
					Optional<IValidateExistDto> validateIfTheElementExist = repository.validateIfTheElementExist(elementClassroomId, classroomId);
					
					if(validateIfTheElementExist.get().getQuantity() >= 1) {
						Optional<IMotionClassroomAmountDto> omca = repository.getAmountByClassroomDestination();
						if (omca.isPresent()) {
							elementClassroom = elementClassroomRepository.findById(omca.get().getElementClassroomId()).orElse(null);
							if (elementClassroom != null) {
								elementClassroom.setAmount(omca.get().getAmountQuantity() + amountMotion);
								elementClassroomRepository.save(elementClassroom);
							} else {
								throw new Exception(
										"No hay suficientes elementos disponibles en el salón destino para el movimiento.");
							}
						} else {
							throw new Exception(
									"No se encontraron datos para la cantidad de elementos en el salón destino.");
						}
					} else {
						
					    // El objeto elementClassroomOne es nulo, lo que indica que el elemento no existe
					    // Aquí puedes crear un nuevo registro en element_classrooms
					    ElementsClassrooms newElementClassroom = new ElementsClassrooms();
					    newElementClassroom.setAmount(motions.getAmount()); // Establece la cantidad adecuada
					    
					    newElementClassroom.setElementId(elementClassroom.getElementId()); // Establece el elemento adecuado
					    newElementClassroom.setClassroomId(classrooms); // Asocia al salón de destino
					    newElementClassroom.setState(true);

					    // Guarda el nuevo registro en la base de datos
					    elementClassroomRepository.save(newElementClassroom);
					    
					}

					// Continúa con el proceso de guardar el movimiento

					return savedMotions;
				} else {
					throw new Exception("No hay suficientes elementos disponibles para el movimiento.");
				}
			} else {
				throw new Exception("El elemento_salón especificado no fue encontrado.");
			}
		} else {
			throw new Exception("El objeto elementClassroomOne es nulo.");
		}
	}

}
