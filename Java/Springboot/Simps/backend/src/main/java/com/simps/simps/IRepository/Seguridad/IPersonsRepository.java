package com.simps.simps.IRepository.Seguridad;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import com.simps.simps.Dto.Seguridad.IPersonsDto;
import com.simps.simps.Entity.Seguridad.Persons;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IPersonsRepository extends IObjectTRepository<Persons, Long>  {
	
	@Query(value = "SELECT id, first_name as firstName, second_name as secondName, first_last_name as firstLastName, second_last_name as secondLastName, " +
            "document_type as documentType, document, age, rfid, gender, mail, phone_number  as phoneNumber, address, state " +
            "FROM persons " +
            "WHERE (:search IS NULL OR first_name LIKE '%' || :search || '%' OR second_name LIKE '%' || :search || '%' OR " +
            "first_last_name LIKE '%' || :search || '%' OR second_last_name LIKE '%' || :search || '%' OR " +
            "document_type LIKE '%' || :search || '%' OR document LIKE '%' || :search || '%' OR " +
            " mail LIKE '%' || :search || '%' OR " +
            " phone_number LIKE '%' || :search || '%' OR address LIKE '%' || :search || '%' OR rfid LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IPersonsDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
			+ " 	count(document) as quantity "
			+ " FROM "
			+ " 	persons "
			+ " WHERE document = :document "
			+ " OR mail = :mail ", nativeQuery = true)
    Optional<IPersonsDto> getValidate(String document, String mail);
	
	Optional<Persons> findByDocument(String document);
	
	
	

	
	
	
}

