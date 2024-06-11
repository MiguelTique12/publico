package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.IHeadquatersDto;
import com.simps.simps.Entity.Parametrizacion.Headquaters;
import com.simps.simps.Entity.Parametrizacion.Institutes;
import com.simps.simps.IRepository.Parametrizacion.IHeadquatersRepository;
import com.simps.simps.IService.Parametrizacion.IHeadquatersService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class HeadquatersService extends ObjectTService<Headquaters> implements IHeadquatersService {

	@Autowired
	private IHeadquatersRepository repository;

	@Override
	public Page<IHeadquatersDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Headquaters save(@Valid Headquaters headquaters) throws Exception {
		Institutes institutes = headquaters.getInstituteId();
        Long instituteId = institutes.getId();
		
		Optional<IHeadquatersDto> op = repository.getValidate(headquaters.getName(), headquaters.getAddress(), headquaters.getPhoneNumber(), headquaters.getMail(), headquaters.getNit(), instituteId);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(headquaters);
	}
	
	
	
}
