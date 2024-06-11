package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.ITeachersDto;
import com.simps.simps.Entity.Parametrizacion.Teachers;
import com.simps.simps.Entity.Seguridad.Users;
import com.simps.simps.IRepository.Parametrizacion.ITeachersRepository;
import com.simps.simps.IService.Parametrizacion.ITeachersService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class TeachersService extends ObjectTService<Teachers> implements ITeachersService{

	@Autowired
	private ITeachersRepository repository;
	
	@Override
	public Page<ITeachersDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Teachers save(@Valid Teachers teachers) throws Exception {
		Users users = teachers.getUserId();
        Long userId = users.getId();
		
		Optional<ITeachersDto> op = repository.getValidate(userId);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(teachers);
	}

}
