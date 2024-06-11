package com.simps.simps.IRepository.Seguridad;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.simps.simps.Dto.Seguridad.IRolesDto;
import com.simps.simps.Entity.Seguridad.Roles;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IRolesRepository extends IObjectTRepository<Roles, Long>  {

	@Query(value = "SELECT ro.id, ro.code, ro.description, ro.state, v.label as viewId " +
            " FROM roles ro "
            + " inner join views_roles vr on ro.id = vr.role_id"
            + " inner join views v on vr.view_id = v.id " +
            "WHERE (:search IS NULL OR ro.code LIKE '%' || :search || '%' OR ro.description LIKE '%' || :search || '%'"
            + " OR v.label LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IRolesDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
			+ " 	count(code) as quantity "
			+ " FROM "
			+ " 	roles "
			+ " WHERE code = :code "
			+ " OR description = :description ", nativeQuery = true)
    Optional<IRolesDto> getValidate(String code, String description);
	
}
