package com.simps.simps.IService.Justificacion;

import java.time.LocalDate;
import java.util.List;

import com.simps.simps.Dto.Justifications.IJustificationsDto;
import com.simps.simps.Dto.Justifications.ISubjectsIdDto;
import com.simps.simps.Entity.Justificacion.Justifications;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

public interface IJustificationsService extends IBasicMethodsService<Justifications>{

	List<ISubjectsIdDto> findSubjectId(Long userId, LocalDate date);
	
	List<IJustificationsDto> getJustifications(String date, Long subjectId, Long studentId);
	
}
