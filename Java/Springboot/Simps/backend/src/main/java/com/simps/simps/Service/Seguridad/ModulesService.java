package com.simps.simps.Service.Seguridad;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.simps.simps.Dto.Seguridad.IModulesDto;
import com.simps.simps.Entity.Seguridad.Modules;
import com.simps.simps.IRepository.Seguridad.IModulesRepository;
import com.simps.simps.IService.Seguridad.IModulesService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class ModulesService extends ObjectTService<Modules> implements IModulesService{

	@Autowired
	private IModulesRepository repository;

	@Override
	public Page<IModulesDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Modules save(@Valid Modules modules) throws Exception {
		Optional<IModulesDto> op = repository.getValidate(modules.getCode(), modules.getRoute(), modules.getDescription());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	return  repository.save(modules);
	}
}
