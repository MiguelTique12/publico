package com.simps.simps.IRepository.Inventario;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Inventario.IMotionInInventoryDto;
import com.simps.simps.Entity.Inventario.AmountsElements;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IAmountsElementsRepository extends IObjectTRepository<AmountsElements, Long>{
	
	//Validar si el inventario es igual que el inventario base
	
	@Query(value = "SELECT "
			+ "			count(ae.amount) as quantityOne "
			+ "		FROM amounts_elements ae "
			+ "			inner join elements_classrooms ec on ae.element_classrooom_id = ec.id "
			+ "		WHERE :amount = ec.amount "
			+ "			AND :elementClassroomId = ec.id ", nativeQuery = true)
	Optional<IMotionInInventoryDto> getValidateInventory(Integer amount, Long elementClassroomId);
	
	//Valida si hubo un movimiento en el inventario
	
	@Query(value = "SELECT  "
			+ "			Count(mot.date) as quantityTwo "
			+ "		FROM  "
			+ "			motions mot "
			+ "			inner join elements_classrooms ec on mot.element_classrooms_id = ec.id "
			+ "			inner join elements e on ec.element_id = e.id "
			+ "			inner join classrooms cl on ec.classroom_id = cl.id "
			+ "			inner join amounts_elements ae ON ae.element_classrooom_id = ec.id "
			+ "			inner join inventories i ON i.id = ae.inventory_id "
			+ "		WHERE to_char(mot.date, 'yyyy-MM-dd') =  :dateSearch  "
			+ "			 and mot.element_classrooms_id = ae.element_classrooom_id "
			+ "		GROUP BY mot.id "
			+ "		order by mot.id desc "
			+ "		limit 1", nativeQuery = true)
	Optional<IMotionInInventoryDto> getValidateMotion(String dateSearch);
	
	
	//Valida si hubo un daño perdida en el inventario
	@Query(value = " SELECT   "
			+ "	 		Count(dl.date) as quantityThree "
			+ "	 	FROM   "
			+ "	 		damages_losses dl  "
			+ "	 		inner join elements_classrooms ec on dl.element_classrooms_id = ec.id  "
			+ "	 		inner join elements e on ec.element_id = e.id  "
			+ "	 		inner join classrooms cl on ec.classroom_id = cl.id  "
			+ "	 		inner join amounts_elements ae ON ae.element_classrooom_id = ec.id  "
			+ "	 		inner join inventories i ON i.id = ae.inventory_id  "
			+ "	 	WHERE to_char(dl.date, 'yyyy-MM-dd') = :dateSearch "
			+ "	 		and dl.element_classrooms_id = ae.element_classrooom_id  "
			+ "	 	GROUP BY dl.id  "
			+ "	 	order by dl.id desc  "
			+ "	 	limit 1 ", nativeQuery = true)
	Optional<IMotionInInventoryDto> getValidateDamageLoss(String dateSearch);
	

	

	
}
