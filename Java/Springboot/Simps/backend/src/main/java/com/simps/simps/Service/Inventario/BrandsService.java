package com.simps.simps.Service.Inventario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Inventario.IBrandsDto;
import com.simps.simps.Entity.Inventario.Brands;
import com.simps.simps.IRepository.Inventario.IBrandsRepository;
import com.simps.simps.IService.Inventario.IBrandsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class BrandsService extends ObjectTService<Brands> implements IBrandsService{

	@Autowired
	private IBrandsRepository repository;

	@Override
	public Page<IBrandsDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Brands save(@Valid Brands brands) throws Exception {
		
		Optional<IBrandsDto> op = repository.getValidate(brands.getNit());
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	return repository.save(brands);
	}
}
