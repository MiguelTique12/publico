package com.simps.simps.IRepository.Inventario;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Inventario.ITypesElementsDto;
import com.simps.simps.Entity.Inventario.TypesElements;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface ITypesElementsRepository extends IObjectTRepository<TypesElements, Long> {
	
	@Query(value = "SELECT id, name, description, state " +
            "FROM types_elements " +
            "WHERE (:search IS NULL OR name LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<ITypesElementsDto> getDatatable(Pageable pageable, String search);



@Query(value = "SELECT "
	+ " 	count(name) as quantity "
	+ " FROM "
	+ " 	types_elements "
	+ " WHERE name = :name "
    + " OR description = :description ",nativeQuery = true)
Optional<ITypesElementsDto> getValidate(String name, String description);
}
