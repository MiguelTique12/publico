package com.simps.simps.IService.Inventario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simps.simps.Dto.Inventario.ITypesElementsDto;
import com.simps.simps.Entity.Inventario.TypesElements;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

public interface ITypesElementsService extends IBasicMethodsService<TypesElements>{
	/**
     * Recupera una lista paginada de módulos que coincidan con una cadena de búsqueda.
     *
     * @param pageable información sobre la paginación
     * @param search la cadena de búsqueda para filtrar los módulos
     * @return una página de objetos Module que representan los módulos encontrados
     */
    public Page<ITypesElementsDto> getDatatable(Pageable pageable, String search) throws Exception;
}
