package com.simps.simps.Service.Inventario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Inventario.ITypesElementsDto;
import com.simps.simps.Entity.Inventario.TypesElements;
import com.simps.simps.IRepository.Inventario.ITypesElementsRepository;
import com.simps.simps.IService.Inventario.ITypesElementsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class TypesElementsService extends ObjectTService<TypesElements> implements ITypesElementsService{

	@Autowired
	private ITypesElementsRepository repository;

	@Override
	public Page<ITypesElementsDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}

	@Override
	public TypesElements save( @Valid TypesElements typesElements) throws Exception {
        
		Optional<ITypesElementsDto> op = repository.getValidate(typesElements.getName(),typesElements.getDescription());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(typesElements);
	}
}
