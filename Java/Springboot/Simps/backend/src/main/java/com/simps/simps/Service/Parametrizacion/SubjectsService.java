package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.ISubjectsDto;
import com.simps.simps.Entity.Parametrizacion.Subjects;
import com.simps.simps.IRepository.Parametrizacion.ISubjectsRepository;
import com.simps.simps.IService.Parametrizacion.ISubjectsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class SubjectsService extends ObjectTService<Subjects> implements ISubjectsService{

	@Autowired
	private ISubjectsRepository repository;

	@Override
	public Page<ISubjectsDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Subjects save(@Valid Subjects subjects) throws Exception {
		Optional<ISubjectsDto> op = repository.getValidate(subjects.getCode(), subjects.getName());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(subjects);
	}
	
	
}
