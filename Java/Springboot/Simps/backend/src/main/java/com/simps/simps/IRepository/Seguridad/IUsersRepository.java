package com.simps.simps.IRepository.Seguridad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


import com.simps.simps.Dto.Seguridad.ILoginDto;
import com.simps.simps.Dto.Seguridad.IPerfilUserDto;
import com.simps.simps.Dto.Seguridad.IPermissionDto;
import com.simps.simps.Dto.Seguridad.IUsersDto;
import com.simps.simps.Entity.Seguridad.Users;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

import java.util.List;
import java.util.Optional;

public interface IUsersRepository extends IObjectTRepository<Users, Long> {

			@Query(value = " SELECT   "
					+ "			v.route viewRoute,  "
					+ "			v.label viewLabel,  "
					+ "			mo.route moduleRoute,  "
					+ "			mo.description moduleLabel, "
					+ "			mo.icon, "
					+ "			r.description as rol, "
					+ "			CONCAT(pe.first_name, pe.second_last_name) as personName "
					+ "			  "
					+ "		FROM  users u "
					+ "			INNER JOIN persons pe ON u.person_id = pe.id  "
					+ "			INNER JOIN roles r ON r.id = u.role_id  "
					+ "			INNER JOIN views_roles vr ON vr.role_id = r.id  "
					+ "			INNER JOIN views v ON v.id = vr.view_id  "
					+ "			INNER JOIN modules mo ON mo.id = v.module_id  "
					+ "		WHERE   "
					+ "			u.username = :user   "
					+ "			AND u.password = :password "
					+ "			AND u.state = TRUE  "
					+ "			AND r.state = TRUE  "
					+ "			AND v.state = TRUE  "
					+ "			AND mo.state = TRUE ", nativeQuery = true)
			List<IPermissionDto> getPermission(String user, String password);
			
			
			@Query(value = "SELECT  "
					+ "					     u.id as userId,  "
					+ "					     u.state,  "
					+ "					     u.username,  "
					+ "					     COUNT(username) AS quantity  "
					+ "					  FROM  "
					+ "					     users u  "
					+ "					  WHERE  "
					+ "					     u.username = :user AND u.password = :password AND u.state = TRUE "
					+ "						 GROUP BY  u.id, u.state, u.username ", nativeQuery = true)
			Optional<ILoginDto> getLogin(String user, String password);
	

			@Query(value = "SELECT u.id, u.username, u.password, pe.first_name as personId, ro.description as roleId, u.state, u.image " +
		               "FROM users u "
		               + " inner join persons pe on u.person_id = pe.id "
		               + " inner join roles ro on u.role_id = ro.id  " +
		               "WHERE (:search IS NULL OR u.username LIKE '%' || :search || '%' OR  u.password LIKE '%' || :search || '%' OR pe.first_name LIKE '%' || :search || '%' OR pe.second_name LIKE '%' || :search || '%' OR ro.description LIKE '%' || :search || '%')",
		       nativeQuery = true)
		Page<IUsersDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
			+ " 	count(person_id) as quantity "
			+ " FROM "
			+ " 	users "
			+ " WHERE username = :user "
			+ " OR person_id = :personId ", nativeQuery = true)
    Optional<IUsersDto> getValidate(String user, Long personId);
	
	@Query(value = "select  "
			+ " u.username, "
			+ " u.image, "
			+ " r.description as rolDescription, "
			+ " pe.first_name as firstName, "
			+ " pe.first_last_name as firstLastName,"
			+ " pe.id as personId"
			+ " from users u  "
			+ " inner join roles r on u.role_id = r.id "
			+ " inner join persons pe on u.person_id = pe.id "
			+ " where u.id = :userId", nativeQuery = true)
	Optional<IPerfilUserDto> getPerfil(Integer userId);
	
}
