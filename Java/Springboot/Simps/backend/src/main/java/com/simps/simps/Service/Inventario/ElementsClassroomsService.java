package com.simps.simps.Service.Inventario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Inventario.IElementsClassroomsDto;
import com.simps.simps.Entity.Inventario.Elements;
import com.simps.simps.Entity.Inventario.ElementsClassrooms;
import com.simps.simps.Entity.Parametrizacion.Classrooms;
import com.simps.simps.IRepository.Inventario.IElementsClassroomsRepository;
import com.simps.simps.IService.Inventario.IElementsClassroomsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class ElementsClassroomsService extends ObjectTService<ElementsClassrooms> implements IElementsClassroomsService{

	@Autowired
	private IElementsClassroomsRepository repository;

	
	@Override
	public Page<IElementsClassroomsDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}


	@Override
	public ElementsClassrooms save(@Valid ElementsClassrooms elementsClassrooms) throws Exception {
		 
		Elements element = elementsClassrooms.getElementId();
        Long elementId = element.getId();
        
		Classrooms classrooms = elementsClassrooms.getClassroomId();
        Long classroomsId = classrooms.getId();
		
		Optional<IElementsClassroomsDto> op = repository.getValidate(classroomsId, elementsClassrooms.getAmount(), elementId);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(elementsClassrooms);
	}
	

}