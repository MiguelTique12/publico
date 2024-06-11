package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.ICursesDto;
import com.simps.simps.Entity.Parametrizacion.Classrooms;
import com.simps.simps.Entity.Parametrizacion.Curses;
import com.simps.simps.IRepository.Parametrizacion.ICursesRepository;
import com.simps.simps.IService.Parametrizacion.ICursesService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class CursesService  extends ObjectTService<Curses> implements ICursesService{

	@Autowired
	private ICursesRepository repository;

	@Override
	public Page<ICursesDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);

	}

	@Override
	public Curses save(@Valid Curses curses) throws Exception {
		Classrooms classrooms = curses.getClassroomsId();
        Long classroomId = classrooms.getId();
		
		Optional<ICursesDto> op = repository.getValidate(curses.getName(), classroomId);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(curses);
	}
	
}
