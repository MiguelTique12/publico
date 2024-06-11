package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.ITechniquesDto;
import com.simps.simps.Entity.Parametrizacion.Techniques;
import com.simps.simps.IRepository.Parametrizacion.ITechniquesRepository;
import com.simps.simps.IService.Parametrizacion.ITechniquesService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;


@Service
public class TechniquesService extends ObjectTService<Techniques> implements ITechniquesService{

	@Autowired
	private ITechniquesRepository repository;
	
	@Override
	public Page<ITechniquesDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Techniques save(@Valid Techniques techniques) throws Exception {
		Optional<ITechniquesDto> op = repository.getValidate(techniques.getCode(), techniques.getName());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(techniques);
	}
	

}
