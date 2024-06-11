package com.simps.simps.Service.Parametrizacion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Parametrizacion.IInstitutesDto;
import com.simps.simps.Entity.Parametrizacion.Institutes;
import com.simps.simps.IRepository.Parametrizacion.IInstitutesRepository;
import com.simps.simps.IService.Parametrizacion.IInstitutesService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class InstitutesService extends ObjectTService<Institutes> implements IInstitutesService{

	@Autowired
	private IInstitutesRepository repository;

	@Override
	public Page<IInstitutesDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Institutes save(@Valid Institutes institutes) throws Exception {
		Optional<IInstitutesDto> op = repository.getValidate(institutes.getName(), institutes.getAddress(), institutes.getPhoneNumber(), institutes.getMail(), institutes.getNit());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(institutes);
	}
	
	
	
}
