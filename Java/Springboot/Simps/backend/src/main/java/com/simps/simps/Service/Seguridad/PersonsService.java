package com.simps.simps.Service.Seguridad;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Seguridad.IPersonsDto;
import com.simps.simps.Entity.Seguridad.Persons;
import com.simps.simps.IRepository.Seguridad.IPersonsRepository;
import com.simps.simps.IService.Seguridad.IPersonsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class PersonsService extends ObjectTService<Persons> implements IPersonsService{

	@Autowired
	private IPersonsRepository repository;

	@Override
	public Page<IPersonsDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Persons save(@Valid Persons persons) throws Exception {
		Optional<IPersonsDto> op = repository.getValidate(persons.getDocument(), persons.getMail());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	return  repository.save(persons);
	}

	@Override
	public Optional<Persons> findByDocument(String document) {
		// TODO Auto-generated method stub
		return repository.findByDocument(document);
	}
}
