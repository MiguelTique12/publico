package com.simps.simps.IService.Parametrizacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simps.simps.Dto.Parametrizacion.IClassroomsDto;
import com.simps.simps.Entity.Parametrizacion.Classrooms;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

public interface IClassroomsService extends IBasicMethodsService<Classrooms>{

	/**
     * Recupera una lista paginada de módulos que coincidan con una cadena de búsqueda.
     *
     * @param pageable información sobre la paginación
     * @param search la cadena de búsqueda para filtrar los módulos
     * @return una página de objetos Module que representan los módulos encontrados
     */
    public Page<IClassroomsDto> getDatatable(Pageable pageable, String search) throws Exception;
	
}
