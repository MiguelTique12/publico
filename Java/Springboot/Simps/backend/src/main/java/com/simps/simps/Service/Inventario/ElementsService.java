package com.simps.simps.Service.Inventario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Inventario.IElementsDto;
import com.simps.simps.Entity.Inventario.Brands;
import com.simps.simps.Entity.Inventario.Elements;
import com.simps.simps.Entity.Inventario.TypesElements;
import com.simps.simps.IRepository.Inventario.IElementsRepository;
import com.simps.simps.IService.Inventario.IElementsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

@Service
public class ElementsService extends ObjectTService<Elements> implements IElementsService {

	@Autowired
	private IElementsRepository repository;

	@Override
	public Page<IElementsDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Elements save(Elements elements) throws Exception {
		Brands brand = elements.getBrandId();
        Long brandId = brand.getId();
        
        TypesElements typeElement = elements.getTypesElements();
        Long typeElementsId = typeElement.getId();
        
		Optional<IElementsDto> op = repository.getValidate(elements.getName(),elements.getDescription(),brandId,typeElementsId);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(elements);
	}

}
