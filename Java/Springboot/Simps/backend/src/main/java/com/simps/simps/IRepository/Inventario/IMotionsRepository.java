package com.simps.simps.IRepository.Inventario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Inventario.IMotionClassroomAmountDto;
import com.simps.simps.Dto.Inventario.IMotionsDto;
import com.simps.simps.Dto.Inventario.IValidateExistDto;
import com.simps.simps.Entity.Inventario.Motions;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IMotionsRepository  extends IObjectTRepository<Motions, Long>{
	@Query(value = "SELECT mo.id, mo.amount, mo.date, mo.classrooms_destination_id as classroomsDestinationId, mo.element_classrooms_id as elementClassroomsId, mo.user_id as userId, mo.state " +
            "FROM motions mo"
            + " inner join classrooms cl on mo.classrooms_destination_id = cl.id "
            + "	inner join elements_classrooms ec on mo.element_classrooms_id = ec.id"
            + " inner join elements el on ec.element_id = el.id "
            + "	inner join users us on mo.user_id = us.id "
            + "	inner join persons pe on us.person_id = pe.id " +
            "WHERE (:search IS NULL OR mo.amount LIKE '%' || :search || '%' OR mo.date LIKE '%' || :search || '%'"
            + " OR cl.name LIKE '%' || :search || '%' OR el.name LIKE '%' || :search || '%' OR pe.first_name LIKE '%' || :search || '%'"
            + " pe.second_name LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IMotionsDto> getDatatable(Pageable pageable, String search);



	@Query(value = "SELECT "
		+ " 	count(amount) as quantity "
		+ " FROM "
		+ " 	motions "
		+ " WHERE amount = :amount "
	    + " and date = :date "
	    + " and  classrooms_destination_id = :classroomsDestinationId "
	    + " and  element_classrooms_id = :elementClassroomId "
	    + " and  user_id = :userId",nativeQuery = true)
	Optional<IMotionsDto> getValidate(Long elementClassroomId, Integer amount, LocalDateTime date, Long classroomsDestinationId, Long userId);

	@Query(value = "select  "
			+ " 		cl.name as classroomName, "
			+ " 		ec.id as elementClassroomId, "
			+ " 		ec.element_id, "
			+ "			e.name as elementName, "
			+ "			ec.classroom_id, "
			+ "			ec.amount as amountQuantity "
			+ "		from motions mo "
			+ "			inner join elements_classrooms ecm on mo.element_classrooms_id = ecm.id "
			+ "			inner join classrooms cl on mo.classrooms_destination_id = cl.id "
			+ "			inner join elements_classrooms ec on cl.id = ec.classroom_id "
			+ "			inner join elements e on ec.element_id = e.id "
			+ "		where mo.classrooms_destination_id = ec.classroom_id "
			+ "			and ecm.element_id = ec.element_id limit 1", nativeQuery = true)
	Optional<IMotionClassroomAmountDto> getAmountByClassroomDestination();
	
	
		@Query(value = " SELECT COUNT(mo.id) AS quantity "
				+ " 	FROM motions mo  "
				+ " 	  inner join classrooms c_destination ON mo.classrooms_destination_id = c_destination.id  "
				+ " 	 inner JOIN elements_classrooms ec_destination ON c_destination.id = ec_destination.classroom_id  "
				+ " 	inner join elements_classrooms ec ON mo.element_classrooms_id = ec.id  "
				+ " 	WHERE mo.classrooms_destination_id = :classroomDestination  "
				+ " 	And mo.element_classrooms_id = :elementClassroomId  "
				+ " 		AND ec_destination.element_id = ec.element_id "
				+ " limit 1", nativeQuery = true)
		Optional<IValidateExistDto> validateIfTheElementExist(Long elementClassroomId, Long classroomDestination);
	
	
}
