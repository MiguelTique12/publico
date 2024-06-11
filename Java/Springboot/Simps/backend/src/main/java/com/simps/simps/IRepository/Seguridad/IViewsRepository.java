package com.simps.simps.IRepository.Seguridad;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Seguridad.IViewsDto;
import com.simps.simps.Entity.Seguridad.Views;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IViewsRepository extends IObjectTRepository<Views, Long> {

	@Query(value = "SELECT v.id, v.code ,v.route, v.label, v.state, m.description as module "
            + "FROM views v "
            + "INNER JOIN modules m ON m.id = v.module_id "
            + "WHERE (:search IS NULL OR v.route LIKE '%' || :search || '%' OR v.label LIKE '%' || :search || '%' OR m.description LIKE '%' || :search || '%')",
      nativeQuery = true)
Page<IViewsDto> getDatatable(Pageable pageable, String search);

	
	@Query(value = "SELECT "
		+ " 	count(code) as quantity "
		+ " FROM "
		+ " 	views "
		+ " WHERE code = :code "
		+ " OR route = :route ", nativeQuery = true)
	Optional<IViewsDto> getValidate(String code, String route);
}
