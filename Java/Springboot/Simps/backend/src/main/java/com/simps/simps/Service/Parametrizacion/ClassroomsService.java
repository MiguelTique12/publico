package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.IClassroomsDto;
import com.simps.simps.Entity.Parametrizacion.Classrooms;
import com.simps.simps.IRepository.Parametrizacion.IClassroomsRepository;
import com.simps.simps.IService.Parametrizacion.IClassroomsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;


@Service
public class ClassroomsService extends ObjectTService<Classrooms> implements IClassroomsService{

	@Autowired
	private IClassroomsRepository repository;

	@Override
	public Page<IClassroomsDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}
	
	@Override
    public Classrooms save(@Valid Classrooms classrooms) throws Exception {
    	Optional<IClassroomsDto> op = repository.getValidate(classrooms.getName());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(classrooms);
    }
	
	

	
}
