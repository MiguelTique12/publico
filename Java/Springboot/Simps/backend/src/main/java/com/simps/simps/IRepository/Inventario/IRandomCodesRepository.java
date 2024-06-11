package com.simps.simps.IRepository.Inventario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Inventario.IRandomCodesDto;
import com.simps.simps.Entity.Inventario.RandomsCodes;

public interface IRandomCodesRepository extends JpaRepository<RandomsCodes, Long>{

	@Query(value = " select id, "
			+ " code, "
			+ " date_creation as dateCreation, "
			+ " state "
			+ "from randoms_codes"
			+ " WHERE code = :code", nativeQuery = true)
	Optional<IRandomCodesDto> validateCode(Integer code);

	
}
