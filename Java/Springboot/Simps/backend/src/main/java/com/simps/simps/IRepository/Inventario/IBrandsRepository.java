package com.simps.simps.IRepository.Inventario;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Inventario.IBrandsDto;
import com.simps.simps.Entity.Inventario.Brands;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IBrandsRepository extends IObjectTRepository<Brands, Long> {
	
	@Query(value = "SELECT id, name, nit, state " +
            "FROM brands " +
            "WHERE (:search IS NULL OR name LIKE '%' || :search || '%' OR nit LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IBrandsDto> getDatatable(Pageable pageable, String search);



@Query(value = "SELECT "
	+ " 	count(nit) as quantity "
	+ " FROM "
	+ " 	brands "
	+ " WHERE nit=  :nit", nativeQuery = true)
Optional<IBrandsDto> getValidate(String nit);
}

