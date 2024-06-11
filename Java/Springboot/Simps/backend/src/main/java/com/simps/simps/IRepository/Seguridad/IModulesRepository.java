package com.simps.simps.IRepository.Seguridad;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Seguridad.IModulesDto;
import com.simps.simps.Entity.Seguridad.Modules;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IModulesRepository extends IObjectTRepository<Modules, Long>{

	
	@Query(value = "SELECT id, code, route, description, state, icon "
            + "FROM modules "
            + "WHERE (:search IS NULL OR code LIKE '%' || :search || '%' OR route LIKE '%' || :search || '%' OR description LIKE '%' || :search || '%')",
      nativeQuery = true)
Page<IModulesDto> getDatatable(Pageable pageable, String search);



	
	@Query(value = "SELECT "
			+ " 	count(code) as quantity "
			+ " FROM "
			+ " 	modules "
			+ " WHERE code = :code "
			+ " OR description = :description"
			+ " OR route = :route ", nativeQuery = true)
    Optional<IModulesDto> getValidate(String code, String route, String description);
}
