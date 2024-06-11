package com.simps.simps.Service.Seguridad;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Seguridad.IViewsDto;
import com.simps.simps.Entity.Seguridad.Views;
import com.simps.simps.IRepository.Seguridad.IViewsRepository;
import com.simps.simps.IService.Seguridad.IViewsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class ViewsService extends ObjectTService<Views> implements IViewsService{

	@Autowired
	private IViewsRepository repository;

	@Override
	public Page<IViewsDto> getDatatable(Pageable pageable, String search) throws Exception {
		return repository.getDatatable(pageable, search);
		
	}

	@Override
	public Views save(@Valid Views views) throws Exception {
		Optional<IViewsDto> op = repository.getValidate(views.getCode(),views.getRoute());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	return  repository.save(views);
	}


}
