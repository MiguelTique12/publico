package com.simps.simps.IRepository.Parametrizacion;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.IInstitutesDto;
import com.simps.simps.Entity.Parametrizacion.Institutes;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IInstitutesRepository extends IObjectTRepository<Institutes, Long>{

	@Query(value = "SELECT id, name, address, phone_number as phoneNumber, mail, nit, state " +
            "FROM institutes " +
            "WHERE (:search IS NULL OR name LIKE '%' || :search || '%' OR address LIKE '%' || :search || '%' OR phone_number LIKE '%' || :search || '%' OR mail LIKE '%' || :search || '%' OR nit LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IInstitutesDto> getDatatable(Pageable pageable, String search);



	@Query(value = "SELECT "
			+ " 	count(name) as quantity "
			+ " FROM "
			+ " 	institutes "
			+ " WHERE name = :name "
			+ " OR address = :address "
			+ " OR phone_number = :phoneNumber "
			+ " OR mail = :mail "
			+ " OR nit = :nit ", nativeQuery = true)
    Optional<IInstitutesDto> getValidate(String name, String address, String phoneNumber, String mail, String nit);
	
}
