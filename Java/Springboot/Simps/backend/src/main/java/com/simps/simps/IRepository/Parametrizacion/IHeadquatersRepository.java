package com.simps.simps.IRepository.Parametrizacion;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Parametrizacion.IHeadquatersDto;
import com.simps.simps.Entity.Parametrizacion.Headquaters;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IHeadquatersRepository extends IObjectTRepository<Headquaters, Long> {

	@Query(value = "SELECT he.id, he.name, he.address, he.phone_number as phoneNumber, he.mail, he.nit, he.institute_id as instituteId, he.state  "
			+ "			  FROM headquaters he    inner join institutes ins on he.institute_id = ins.id  "
			+ "			  WHERE (:search IS NULL OR he.name LIKE '%' || :search || '%' OR he.address LIKE '%' || :search || '%' OR he.phone_number LIKE '%' || :search || '%' OR he.mail LIKE '%' || :search || '%' OR he.nit LIKE '%' || :search || '%'  "
			+ "			  OR ins.name LIKE '%' || :search || '%')", nativeQuery = true)
	Page<IHeadquatersDto> getDatatable(Pageable pageable, String search);

	@Query(value = "SELECT " + " 	count(name) as quantity " + " FROM " + " 	headquaters " + " WHERE name = :name "
			+ " OR address = :address " + " OR phone_number = :phoneNumber " + " OR mail = :mail " + " OR nit = :nit "
			+ " OR institute_id = :instituteId ", nativeQuery = true)
	Optional<IHeadquatersDto> getValidate(String name, String address, String phoneNumber, String mail, String nit,
			Long instituteId);

}
