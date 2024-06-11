package com.simps.simps.IService.Parametrizacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simps.simps.Dto.Parametrizacion.IGradesDto;
import com.simps.simps.Entity.Parametrizacion.Grades;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

public interface IGradesService extends IBasicMethodsService<Grades>{

	/**
     * Recupera una lista paginada de módulos que coincidan con una cadena de búsqueda.
     *
     * @param pageable información sobre la paginación
     * @param search la cadena de búsqueda para filtrar los módulos
     * @return una página de objetos Module que representan los módulos encontrados
     */
    public Page<IGradesDto> getDatatable(Pageable pageable, String search) throws Exception;
	
}
