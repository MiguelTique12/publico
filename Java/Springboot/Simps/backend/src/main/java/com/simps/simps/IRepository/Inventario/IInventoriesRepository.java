package com.simps.simps.IRepository.Inventario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Inventario.IInventoriesDto;
import com.simps.simps.Dto.Inventario.IInventoryByIdDto;
import com.simps.simps.Dto.Inventario.IInventoryFilterDto;
import com.simps.simps.Entity.Inventario.Inventories;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IInventoriesRepository  extends IObjectTRepository<Inventories, Long>{
	
	@Query(value = "SELECT in.id, in.date, in.description, in.user_id as userId, in.state " +
            "FROM inventories in "
            + "	inner join users us on in.user_id = us.id "
            + "	inner join persons pe on us.person_id = pe.id " +
            "WHERE (:search IS NULL OR in.date LIKE '%' || :search || '%' OR in.description LIKE '%' || :search || '%'"
            + " OR pe.first_name LIKE '%' || :search || '%' OR pe.second_name LIKE '%' || :search || '%')",
    nativeQuery = true)
	Page<IInventoriesDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
		+ " 	count(date) as quantity "
		+ " FROM "
		+ " 	inventories "
		+ " WHERE date= :date "
		+ " and description = :description"
	    + " and user_id = :userId ",nativeQuery = true)
	Optional<IInventoriesDto> getValidate(LocalDateTime date, String description , Long userId);
	
	//El metodo de aquí abajo se encarga de mostrarme los inventarios por fecha
	
	@Query(value = " Select "
			+ "		i.id as inventoryId,  "
			+ "		i.date,  "
			+ "		i.description,  "
			+ "		pe.first_name as firstName,  "
			+ "		pe.second_name as secondName,  "
			+ "		pe.first_last_name as firstLastName, "
			+ "		ec.classroom_id as classroomId, "
			+ "		e.name as nameElement,"
			+ "		br.name as brandId,"
			+ "		e.description as descriptionElement,"
			+ "		e.image,  "
			+ "		ae.amount  "
			+ "	from inventories i  "
			+ "		 inner join amounts_elements ae on i.id = ae.inventory_id "
			+ "		 inner join elements_classrooms ec on ae.element_classrooom_id = ec.id "
			+ "		 inner join elements e ON e.id = ec.element_id"
			+ "		 inner join brands br on e.brand_id = br.id "
			+ "		 inner join users u ON i.user_id = u.id "
			+ "		 inner join persons pe on u.person_id = pe.id "
			+ "	WHERE (:date is null or TO_CHAR(i.date, 'YYYY-MM-DD') = :date) AND   "
			+ "		  (:classroomId is null or ec.classroom_id = :classroomId) ", nativeQuery = true)
	List<IInventoryFilterDto> getInventories(Long classroomId, String date);
	
	
	
	//El metodo de aquí abajo se encarga de mostrarme los inventarios detalladamente gracias a su inventoryId
	
		@Query(value = " Select "
				+ "		i.id as inventoryId,  "
				+ "		i.date,  "
				+ "		i.description,  "
				+ "		pe.first_name as firstName,  "
				+ "		pe.second_name as secondName,  "
				+ "		pe.first_last_name as firstLastName, "
				+ "		ec.classroom_id as classroomId, "
				+ "		e.name as nameElement,"
				+ "		br.name as brandId,"
				+ "		e.description as descriptionElement,"
				+ "		e.image,  "
				+ "		ae.amount  "
				+ "	from inventories i  "
				+ "		 inner join amounts_elements ae on i.id = ae.inventory_id "
				+ "		 inner join elements_classrooms ec on ae.element_classrooom_id = ec.id "
				+ "		 inner join elements e ON e.id = ec.element_id"
				+ "		 inner join brands br on e.brand_id = br.id "
				+ "		 inner join users u ON i.user_id = u.id "
				+ "		 inner join persons pe on u.person_id = pe.id "
				+ "	WHERE i.id = :inventoryId ", nativeQuery = true)
		List<IInventoryFilterDto> getInventoriesId(Long inventoryId);
	
	
	//El metodo de aquí abajo se encarga de mostrarme la lista de elementos de un salón para hacer, 
	//el inventario de él, o sea, si pongo el salón 1001, me mostrará los elementos del salón 1001 para hace el inventario
	//de ellos
	
	@Query(value = "SELECT "
			+ "			ec.id as elementClassroomId,"
			+ "			e.id as elementId, "
			+ "			e.image, "
			+ "			e.name, "
			+ "			e.description, "
			+ "			b.name as brand"
			+ "		FROM elements e "
			+ "			inner join brands b on e.brand_id = b.id "
			+ "			inner join elements_classrooms ec on e.id = ec.element_id "
			+ "		WHERE ec.classroom_id= :classroomId", nativeQuery = true)
	List<IInventoryByIdDto> getInventory(Integer classroomId);
	
	
	
}





