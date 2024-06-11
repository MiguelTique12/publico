package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.ISchendulesDto;
import com.simps.simps.Entity.Parametrizacion.Schendules;
import com.simps.simps.IRepository.Parametrizacion.ISchenduleRepository;
import com.simps.simps.IService.Parametrizacion.ISchendulesService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class SchendulesService extends ObjectTService<Schendules> implements ISchendulesService {

	@Autowired
	ISchenduleRepository repository;

	@Override
	public Page<ISchendulesDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);

	}

	@Override
	public Schendules save(@Valid Schendules schendules) throws Exception {
		Optional<ISchendulesDto> op = repository.getValidate(schendules.getStartTime(), schendules.getEndTime());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(schendules);
	}
	
	
	
}
