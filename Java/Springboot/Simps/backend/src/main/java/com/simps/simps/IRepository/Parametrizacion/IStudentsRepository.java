package com.simps.simps.IRepository.Parametrizacion;


import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;


import com.simps.simps.Dto.Parametrizacion.IStudentsDto;
import com.simps.simps.Entity.Parametrizacion.Students;
import com.simps.simps.IRepository.ObjectT.IObjectTRepository;

public interface IStudentsRepository extends IObjectTRepository<Students, Long>{

	@Query(value = "SELECT st.id, cu.name as curseId, TO_CHAR(st.enrollment_date, 'YYYY-MM-DD') as enrollmentDate, us.username as userId, st.state " +
            " FROM students st "
            + " inner join users us on st.user_id = us.id"
            + " inner join curses cu on st.curse_id = cu.id " +
            "WHERE (:search IS NULL OR TO_CHAR(st.enrollment_date, 'YYYY-MM-DD') LIKE '%' || :search || '%' OR us.username LIKE '%' || :search || '%' "
            + " OR cu.name LIKE '%' || :search || '%')",
    nativeQuery = true)
Page<IStudentsDto> getDatatable(Pageable pageable, String search);


	
	@Query(value = "SELECT "
		+ " 	count(user_id) as quantity "
		+ " FROM "
		+ " 	students "
		+ " WHERE user_id = :userId ", nativeQuery = true)
	Optional<IStudentsDto> getValidate(Long userId);
	
}
