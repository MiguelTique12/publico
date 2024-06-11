package com.simps.simps.IService.Inventario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simps.simps.Dto.Inventario.IBrandsDto;
import com.simps.simps.Entity.Inventario.Brands;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

public interface IBrandsService extends IBasicMethodsService<Brands>{
	/**
     * Recupera una lista paginada de módulos que coincidan con una cadena de búsqueda.
     *
     * @param pageable información sobre la paginación
     * @param search la cadena de búsqueda para filtrar los módulos
     * @return una página de objetos Module que representan los módulos encontrados
     */
    public Page<IBrandsDto> getDatatable(Pageable pageable, String search) throws Exception;
}
