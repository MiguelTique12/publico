package com.simps.simps.IService.Seguridad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simps.simps.Dto.Seguridad.IModulesDto;
import com.simps.simps.Entity.Seguridad.Modules;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

public interface IModulesService extends IBasicMethodsService<Modules>{
	/**
     * Recupera una lista paginada de módulos que coincidan con una cadena de búsqueda.
     *
     * @param pageable información sobre la paginación
     * @param search la cadena de búsqueda para filtrar los módulos
     * @return una página de objetos Module que representan los módulos encontrados
     */
    public Page<IModulesDto> getDatatable(Pageable pageable, String search) throws Exception;
}
