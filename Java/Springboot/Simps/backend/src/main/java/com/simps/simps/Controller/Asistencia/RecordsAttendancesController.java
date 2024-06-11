package com.simps.simps.Controller.Asistencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.simps.simps.Dto.Asistencia.ICursesFilterDto;
import com.simps.simps.Dto.Asistencia.IFilterStudentsDto;
import com.simps.simps.Dto.Asistencia.ISearchAttendancesDto;
import com.simps.simps.Entity.Asistencia.RecordsAttendances;
import com.simps.simps.IService.Asistencia.IRecordsAttendancesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/asistencia/records-attendances")
public class RecordsAttendancesController {

	@Autowired
	private IRecordsAttendancesService service; 
	
	@PostMapping("/with-rfid/{rfid}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<RecordsAttendances> save(@RequestBody RecordsAttendances recordsAttendances, @PathVariable String rfid) throws Exception {
	    RecordsAttendances savedEntity = service.save(recordsAttendances, rfid);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	
	@Operation(summary = "Consultar Asistencia por fecha y/o asignatura", responses = {
		    @ApiResponse(responseCode = "201", description = "Asistencia Consultada")
		})
	@GetMapping("/search-attendance")
	public List<ISearchAttendancesDto> show(@RequestParam(required = false) String date , @RequestParam(required = false) String subject, @RequestParam(required = true) Long personId) throws Exception {
		return service.getAttendances(date, subject, personId);
	}
	
	@Operation(summary = "Consultar Asistencias ALL", responses = {
		    @ApiResponse(responseCode = "201", description = "Asistencias Consultada")
		})
	@GetMapping("/search-All-attendance")
	public List<ISearchAttendancesDto> show(@RequestParam(required = true) Long personId) throws Exception {
		return service.getAllAttendances(personId);
	}


	    @GetMapping("/get-curses")
	    public List<ICursesFilterDto> getCursesFilter(@RequestParam(required = true) Long gradeId) {
	        return service.getCursesFilter(gradeId);
	    }
	
	
	@Operation(summary = "Consultar nombre de estudiantes", responses = {
		    @ApiResponse(responseCode = "201", description = "Asistencias Consultada")
		})
	@GetMapping("/get-name-students")
    public List<IFilterStudentsDto> getNameStudent(@RequestParam(required = true) Long curseId) {
        return service.getNameStudent(curseId);
    }

	
}



