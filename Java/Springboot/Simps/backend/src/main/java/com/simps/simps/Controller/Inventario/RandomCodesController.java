package com.simps.simps.Controller.Inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.simps.simps.Entity.Inventario.RandomsCodes;
import com.simps.simps.IService.Inventario.IRandomsCodesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/inventario/random-codes")
public class RandomCodesController{
	
	@Autowired
	private IRandomsCodesService service;
	
	@Operation(summary = "Crear un nuevo objeto", responses = {
			@ApiResponse(responseCode = "201", description = "Objeto creado") })
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RandomsCodes save(@RequestBody RandomsCodes RandomsCodes) throws Exception {
		return service.save(RandomsCodes);
	}
	
	
	 @GetMapping("/validate/code") // Endpoint para validar el código
	    public Boolean validateCode(@RequestParam Integer code) {
	       return service.validateCode(code);
	    }
	
	
}
