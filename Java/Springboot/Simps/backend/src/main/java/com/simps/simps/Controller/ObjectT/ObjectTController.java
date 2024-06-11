package com.simps.simps.Controller.ObjectT;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.simps.simps.Dto.ApiResponseDto;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

import org.springframework.web.bind.annotation.RequestBody;


public class ObjectTController<T> {
	
	@Autowired
	private IBasicMethodsService<T> service;

	@Operation(summary = "Obtener todos los objetos", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de objetos obtenida"),
			@ApiResponse(responseCode = "404", description = "No se encontraron objetos") })
	@GetMapping
	public List<T> all() throws Exception {
		return service.all();
	}

	@Operation(summary = "Obtener un objeto por su ID", responses = {
			@ApiResponse(responseCode = "200", description = "Objeto encontrado"),
			@ApiResponse(responseCode = "404", description = "Objeto no encontrado") })
	@GetMapping("{id}")
	public Optional<T> show(@PathVariable Long id) throws Exception {
		return service.findById(id);
	}

	@Operation(summary = "Crear un nuevo objeto", responses = {
			@ApiResponse(responseCode = "201", description = "Objeto creado") })
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ApiResponseDto<T>> save(@RequestBody T object) throws Exception {
		try {
			return ResponseEntity.ok(new ApiResponseDto<T>("Datos actualizados", service.save(object), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<T>(e.getMessage(), null, false));
		}
	}

	@Operation(summary = "Actualizar un objeto existente", responses = {
			@ApiResponse(responseCode = "200", description = "Objeto actualizado"),
			@ApiResponse(responseCode = "404", description = "Objeto no encontrado") })
	@PutMapping("{id}")
	public ResponseEntity<ApiResponseDto<T>> update(@PathVariable Long id, @RequestBody T object) {
		try {
			service.update(id, object);
			return ResponseEntity.ok(new ApiResponseDto<T>("Datos actualizados", null, true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<T>(e.getMessage(), null, false));
		}
	}

	@Operation(summary = "Eliminar un objeto existente", responses = {
			@ApiResponse(responseCode = "204", description = "Objeto eliminado"),
			@ApiResponse(responseCode = "404", description = "Objeto no encontrado") })
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws Exception {
		service.delete(id);
	}

	@Operation(summary = "Revisar los objetos disponibles", responses = {
			@ApiResponse(responseCode = "200", description = "Objetos encontrados"),
			@ApiResponse(responseCode = "404", description = "Objetos no encontrados") })
	@GetMapping("/disponibles")
	public List<T> obtenerObjetosDisponibles() throws Exception {
		// Suponiendo que el método findByEstadoTrue() está definido en la interfaz
		// IGenericService<T>
		return service.findByStateTrue();
	}

}