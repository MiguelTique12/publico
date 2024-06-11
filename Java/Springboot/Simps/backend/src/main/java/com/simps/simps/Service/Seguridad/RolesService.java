package com.simps.simps.Service.Seguridad;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Seguridad.IRolesDto;
import com.simps.simps.Entity.Seguridad.Roles;
import com.simps.simps.IRepository.Seguridad.IRolesRepository;
import com.simps.simps.IService.Seguridad.IRolesService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class RolesService extends ObjectTService<Roles> implements IRolesService{

	@Autowired
	private IRolesRepository repository;

	@Override
	public Page<IRolesDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Roles save(@Valid Roles roles) throws Exception {
		Optional<IRolesDto> op = repository.getValidate(roles.getCode(), roles.getDescription());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	return  repository.save(roles);
	}
}
