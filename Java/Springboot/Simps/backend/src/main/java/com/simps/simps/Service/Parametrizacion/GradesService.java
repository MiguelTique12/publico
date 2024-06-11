package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.IGradesDto;
import com.simps.simps.Entity.Parametrizacion.Grades;
import com.simps.simps.IRepository.Parametrizacion.IGradesRepository;
import com.simps.simps.IService.Parametrizacion.IGradesService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class GradesService extends ObjectTService<Grades> implements IGradesService{

	@Autowired
	private IGradesRepository repository;

	@Override
	public Page<IGradesDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Grades save(@Valid Grades grades) throws Exception {
		Optional<IGradesDto> op = repository.getValidate(grades.getName());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(grades);
	}
	
	
	
}
