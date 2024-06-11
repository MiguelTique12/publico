package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.IFloorsDto;
import com.simps.simps.Entity.Parametrizacion.Floors;
import com.simps.simps.IRepository.Parametrizacion.IFloorsRepository;
import com.simps.simps.IService.Parametrizacion.IFloorsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class FloorsService extends ObjectTService<Floors> implements IFloorsService {

	@Autowired
	private IFloorsRepository repository;

	@Override
	public Page<IFloorsDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Floors save(@Valid Floors floors) throws Exception {
		Optional<IFloorsDto> op = repository.getValidate(floors.getFloorNumber());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(floors);
	}
	
	
	
}
