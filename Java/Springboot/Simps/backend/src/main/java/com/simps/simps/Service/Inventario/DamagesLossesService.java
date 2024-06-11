package com.simps.simps.Service.Inventario;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Inventario.IDamagesLossesDto;
import com.simps.simps.Entity.Inventario.DamagesLosses;
import com.simps.simps.Entity.Inventario.ElementsClassrooms;
import com.simps.simps.IRepository.Inventario.IDamagesLossesRepository;
import com.simps.simps.IRepository.Inventario.IElementsClassroomsRepository;
import com.simps.simps.IService.Inventario.IDamagesLossesService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class DamagesLossesService extends ObjectTService<DamagesLosses> implements IDamagesLossesService{

	@Autowired
	private IDamagesLossesRepository repository;
	@Autowired
	private IElementsClassroomsRepository elementClassroomRepository;
	

	@Override
	public Page<IDamagesLossesDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DamagesLosses save(@Valid DamagesLosses damagesLosses) throws Exception {
		
		ElementsClassrooms elementClassroomOne = damagesLosses.getElementClassroomId();
        
		damagesLosses.setDate(LocalDateTime.now());
		
		if (elementClassroomOne != null) {
			Long elementClassroomId = elementClassroomOne.getId();
			Optional<IDamagesLossesDto> op = repository.getValidate(damagesLosses.getAmount(), damagesLosses.getDate(), damagesLosses.getDescription(), elementClassroomId);
	    	if (op.get().getQuantity()>=1) {
	            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
	        }
    	
	        // Consulta la entidad ElementosSalones usando el elemento_salon_id de tu movimiento
	    	ElementsClassrooms elementClassroom = elementClassroomRepository.findById(elementClassroomId).orElse(null);

	    	    if (elementClassroom != null) {
	    	        // Obtiene la cantidad de elementos_salones y de movimientos
	    	        Integer amountElementClassroom = elementClassroom.getAmount();
	    	        Integer amountDamagesLosses = damagesLosses.getAmount();

	    	        // Verifica si hay suficientes elementos para el movimiento
	    	        if (amountElementClassroom >= amountDamagesLosses) {
	    	            // Actualiza la cantidad de elementos_salones restando la cantidad de movimientos
	    	            elementClassroom.setAmount(amountElementClassroom - amountDamagesLosses);
	    	            elementClassroomRepository.save(elementClassroom);

	    	            // Continúa con el proceso de guardar el movimiento
	    	            DamagesLosses savedDamagesLosses = repository.save(damagesLosses);
	    	            return savedDamagesLosses;
	    	        } else {
	    	            throw new Exception("No hay suficientes elementos disponibles para el daño/perdida.");
	    	        }
	    	    } else {
	    	        throw new Exception("El elemento_salón especificado no fue encontrado.");
	    	    }
	    	
	        } else {
	            throw new Exception("El objeto elementClassroomOne es nulo.");
	        }
	}
}
