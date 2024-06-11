package com.simps.simps.IRepository.Inventario;



import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Inventario.IDamagesLossesDto;
import com.simps.simps.Entity.Inventario.DamagesLosses;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IDamagesLossesRepository extends IObjectTRepository<DamagesLosses, Long> {
	
	@Query(value = "SELECT dl.id, dl.element_classrooms_id as elementClassroomsId, dl.amount, dl.date_time as dateTime, dl.description, dl.state " +
            "FROM damages_losses dl "
            + " inner join elements_classrooms ec on dl.element_classrooms_id = ec.id "
            + " inner join elements el on ec.element_id = el.id " +
            "WHERE (:search IS NULL OR el.name LIKE '%' || :search || '%' OR dl.amount LIKE '%' || :search || '%' OR dl.date_time LIKE '%' || :search || '%' OR dl.description LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IDamagesLossesDto> getDatatable(Pageable pageable, String search);



@Query(value = "SELECT "
	+ " 	count(amount) as quantity "
	+ " FROM "
	+ " 	damages_losses "
	+ " WHERE amount= :amount "
	+ " and date = :date "
    + " and description = :description "
    + " and element_classrooms_id = :elementClassroomsId",nativeQuery = true)
Optional<IDamagesLossesDto> getValidate(Integer amount, LocalDateTime date ,String description, Long elementClassroomsId);
}
