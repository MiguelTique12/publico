package com.simps.simps.Controller.Inventario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simps.simps.Controller.ObjectT.ObjectTController;
import com.simps.simps.Dto.ApiResponseDto;
import com.simps.simps.Dto.Inventario.IInventoryByIdDto;
import com.simps.simps.Dto.Inventario.IInventoryFilterDto;
import com.simps.simps.Entity.Inventario.Inventories;
import com.simps.simps.IService.Inventario.IInventoriesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/inventario/inventories")
public class InventoriesCotroller extends ObjectTController<Inventories> {
	@Autowired
	private IInventoriesService service;

	/**
	 * Obtiene los datos para una tabla utilizando paginación y búsqueda.
	 *
	 * @param page            el número de página
	 * @param size            el tamaño de página
	 * @param columnOrder     el nombre de la columna para ordenar
	 * @param columnDirection la dirección de ordenamiento de la columna (ascendente
	 *                        o descendente)
	 * @param search          el término de búsqueda para filtrar los datos de la
	 *                        tabla (opcional)
	 * @return ResponseEntity que contiene un objeto ApiResponseDto con los datos de
	 *         la página y el estado de la respuesta
	 */
	@GetMapping("/datatable")
	public ResponseEntity<ApiResponseDto<Page<?>>> datatable(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size, @RequestParam(name = "column_order") String columnOrder,
			@RequestParam(name = "column_direction") String columnDirection,
			@RequestParam(name = "search", required = false) String search) {
		try {
			List<Order> orders = new ArrayList<>();

			orders.add(new Order(columnDirection.equals("asc") ? Direction.ASC : Direction.DESC, columnOrder));

			return ResponseEntity.ok(new ApiResponseDto<Page<?>>("Datos obtenidos",
					service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Page<?>>(e.getMessage(), null, false));
		}
	}

	@Operation(summary = "Obtener inventarios por fechas y/o salón", responses = {
			@ApiResponse(responseCode = "200", description = "Inventarios obtenidos"),
			@ApiResponse(responseCode = "404", description = "No se encontraron inventarios de esa fecha o salón") })
	@GetMapping("filtrar-inventario")
	public ResponseEntity<?> getInventory(@RequestParam(required = false) Long classroomId,
			@RequestParam(required = false) String date) {
		try {
			List<IInventoryFilterDto> inventories = service.getInventories(classroomId, date);
			return ResponseEntity.ok(inventories);
		} catch (Exception e) {
			ApiResponseDto<?> errorResponse = new ApiResponseDto<>(e.getMessage(), null, false);
			return ResponseEntity.internalServerError().body(errorResponse);
		}
	}

	@Operation(summary = "Obtener inventarios por salón", responses = {
			@ApiResponse(responseCode = "200", description = "Inventario obtenidos"),
			@ApiResponse(responseCode = "404", description = "No se encontraron inventarios de esa fecha") })
	@GetMapping("inventario-salon/{classroomId}")
	public ResponseEntity<?> getInventory(@PathVariable Integer classroomId) {
		try {
			List<IInventoryByIdDto> inventories = service.getInventory(classroomId);
			return ResponseEntity.ok(inventories);
		} catch (Exception e) {
			ApiResponseDto<?> errorResponse = new ApiResponseDto<>(e.getMessage(), null, false);
			return ResponseEntity.internalServerError().body(errorResponse);
		}
	}

	@Operation(summary = "Obtener inventarios por ID", responses = {
			@ApiResponse(responseCode = "200", description = "Inventario obtenidos"),
			@ApiResponse(responseCode = "404", description = "No se encontraron inventarios de ese ID") })
	@GetMapping("/filtrar-id/{inventoryId}")
	public ResponseEntity<?> getInventoriesId(@PathVariable Long inventoryId) {
		try {
			List<IInventoryFilterDto> inventories = service.getInventoriesId(inventoryId);
			return ResponseEntity.ok(inventories);
		} catch (Exception e) {
			ApiResponseDto<?> errorResponse = new ApiResponseDto<>(e.getMessage(), null, false);
			return ResponseEntity.internalServerError().body(errorResponse);
		}
	}

}
