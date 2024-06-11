package com.simps.simps.Controller.Justificacion;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simps.simps.Controller.ObjectT.ObjectTController;
import com.simps.simps.Dto.Justifications.IJustificationsDto;
import com.simps.simps.Dto.Justifications.ISubjectsIdDto;
import com.simps.simps.Entity.Justificacion.Justifications;
import com.simps.simps.IService.Justificacion.IJustificationsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/justificaciones/justifications")
public class JustificationsController  extends ObjectTController <Justifications>{

	@Autowired
	private IJustificationsService service;
	
	@Operation(summary = "Consultar Asignaturas Dependiendo el Día", responses = {
		    @ApiResponse(responseCode = "201", description = "Asignaturas Consultadas")
		})
	@GetMapping("/search-subject")
	public List<ISubjectsIdDto> show(@RequestParam(required = true) LocalDate date,@RequestParam(required = true) Long userId) throws Exception {
		return service.findSubjectId(userId, date);
	}
	
	@Operation(summary = "Consultar Asistencia por fecha y/o asignatura", responses = {
		    @ApiResponse(responseCode = "201", description = "Asistencia Consultada")
		})
	@GetMapping("/search-justifications")
	public List<IJustificationsDto> show(@RequestParam(required = false) String date , @RequestParam(required = false) Long subject, @RequestParam(required = true) Long studentId) throws Exception {
		return service.getJustifications(date, subject, studentId);
	}

}
