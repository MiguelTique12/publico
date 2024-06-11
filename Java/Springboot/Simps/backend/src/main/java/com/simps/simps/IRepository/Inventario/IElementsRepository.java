package com.simps.simps.IRepository.Inventario;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;


import com.simps.simps.Dto.Inventario.IElementsDto;
import com.simps.simps.Entity.Inventario.Elements;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IElementsRepository  extends IObjectTRepository<Elements, Long>{
	
	@Query(value = "SELECT el.id, el.image, el.name, el.description, br.name as brandId, te.name as typeElementId, el.state " +
            "FROM elements el "
            + " inner join brands br on el.brand_id = br.id "
            + " inner join types_elements te on el.type_element_id = te.id " +
            "WHERE (:search IS NULL OR el.name LIKE '%' || :search || '%' OR el.description LIKE '%' || :search || '%'"
            + " OR br.name LIKE '%' || :search || '%' OR te.name LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IElementsDto> getDatatable(Pageable pageable, String search);



@Query(value = "SELECT "
	+ " 	count(name) as quantity "
	+ " FROM "
	+ " 	elements "
	+ " WHERE name= :name "
	+ " and description = :description "
    + " and brand_id = :brandId "
    + " and type_element_id = :typeElementId ",nativeQuery = true)
Optional<IElementsDto> getValidate(String name, String description, Long brandId, Long typeElementId);
}
