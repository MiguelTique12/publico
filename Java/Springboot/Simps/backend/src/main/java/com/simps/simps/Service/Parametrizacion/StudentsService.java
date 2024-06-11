package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.IStudentsDto;
import com.simps.simps.Entity.Parametrizacion.Students;
import com.simps.simps.Entity.Seguridad.Users;
import com.simps.simps.IRepository.Parametrizacion.IStudentsRepository;
import com.simps.simps.IService.Parametrizacion.IStudentsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class StudentsService extends ObjectTService<Students> implements IStudentsService{

	@Autowired
	private IStudentsRepository repository;

	@Override
	public Page<IStudentsDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Students save(@Valid Students students) throws Exception {
		Users users = students.getUserId();
        Long usersId = users.getId();
		
		Optional<IStudentsDto> op = repository.getValidate(usersId);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(students);
	}
	
	
	
}
