package com.simps.simps.Controller.Parametrizacion;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simps.simps.Controller.ObjectT.ObjectTController;
import com.simps.simps.Dto.ApiResponseDto;
import com.simps.simps.Entity.Parametrizacion.Subjects;
import com.simps.simps.IService.Parametrizacion.ISubjectsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/parametrizacion/subjects")
public class SubjectsController extends ObjectTController<Subjects>{

	@Autowired
	private ISubjectsService service; 
	
	/**
     * Obtiene los datos para una tabla utilizando paginación y búsqueda.
     *
     * @param page            el número de página
     * @param size            el tamaño de página
     * @param columnOrder     el nombre de la columna para ordenar
     * @param columnDirection la dirección de ordenamiento de la columna (ascendente o descendente)
     * @param search          el término de búsqueda para filtrar los datos de la tabla (opcional)
     * @return ResponseEntity que contiene un objeto ApiResponseDto con los datos de la página y el estado de la respuesta
     */
    @GetMapping("/datatable")
    public ResponseEntity<ApiResponseDto<Page<?>>> datatable(@RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "column_order") String columnOrder,
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
	
}

