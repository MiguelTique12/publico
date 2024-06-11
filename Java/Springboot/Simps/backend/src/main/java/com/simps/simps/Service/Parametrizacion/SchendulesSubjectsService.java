package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.ISchendulesSubjectsDto;
import com.simps.simps.Entity.Parametrizacion.Curses;
import com.simps.simps.Entity.Parametrizacion.Schendules;
import com.simps.simps.Entity.Parametrizacion.SchendulesSubjects;
import com.simps.simps.Entity.Parametrizacion.Subjects;
import com.simps.simps.IRepository.Parametrizacion.ISchendulesSubjectsRepository;
import com.simps.simps.IService.Parametrizacion.ISchendulesSubjectsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class SchendulesSubjectsService extends ObjectTService<SchendulesSubjects> implements ISchendulesSubjectsService{

	@Autowired
	private ISchendulesSubjectsRepository repository;

	@Override
	public Page<ISchendulesSubjectsDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}

	@Override
	public SchendulesSubjects save(@Valid SchendulesSubjects schendulesSubjects) throws Exception {
		Subjects subjects = schendulesSubjects.getSubjectId();
        Long subjectsId = subjects.getId();
        
		Schendules schendules = schendulesSubjects.getSchenduleId();
        Long schenduleId = schendules.getId();
        
		Curses curses = schendulesSubjects.getCurseId();
        Long cursesId = curses.getId();
        
        
		
		Optional<ISchendulesSubjectsDto> op = repository.getValidate(subjectsId , schenduleId, cursesId);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(schendulesSubjects);
	}
	
	
	
}
