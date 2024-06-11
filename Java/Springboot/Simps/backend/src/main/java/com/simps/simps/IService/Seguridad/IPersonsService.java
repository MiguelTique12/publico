package com.simps.simps.IService.Seguridad;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simps.simps.Dto.Seguridad.IPersonsDto;
import com.simps.simps.Entity.Seguridad.Persons;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

public interface IPersonsService extends IBasicMethodsService<Persons>{
	/**
     * Recupera una lista paginada de módulos que coincidan con una cadena de búsqueda.
     *
     * @param pageable información sobre la paginación
     * @param search la cadena de búsqueda para filtrar los módulos
     * @return una página de objetos Module que representan los módulos encontrados
     */
    public Page<IPersonsDto> getDatatable(Pageable pageable, String search) throws Exception;
    
    public Optional<Persons> findByDocument(String document); 
}
