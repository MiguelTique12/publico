package com.simps.simps.Service.Justificacion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Justifications.IJustificationsDto;
import com.simps.simps.Dto.Justifications.ISubjectsIdDto;
import com.simps.simps.Entity.Justificacion.Justifications;
import com.simps.simps.IRepository.Justificacion.IJustificationsRepository;
import com.simps.simps.IService.Justificacion.IJustificationsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

@Service
public class JustificationsService extends ObjectTService<Justifications> implements IJustificationsService{

	@Autowired
	private IJustificationsRepository repository;
	
	@Override
	public Justifications save(Justifications object) throws Exception {
		return repository.save(object);
	}

	@Override
	public List<ISubjectsIdDto> findSubjectId(Long userId, LocalDate date) {
		
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		String day = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();
		
		
		return repository.getSubjectId(day, userId);
	}

	@Override
	public List<IJustificationsDto> getJustifications(String date, Long subjectId, Long studentId) {
		// TODO Auto-generated method stub
		return repository.getJustifications(date, subjectId, studentId);
	}



}
