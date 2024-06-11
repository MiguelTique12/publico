package com.simps.simps.IService.Inventario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simps.simps.Dto.Inventario.IInventoriesDto;
import com.simps.simps.Dto.Inventario.IInventoryByIdDto;
import com.simps.simps.Dto.Inventario.IInventoryFilterDto;
import com.simps.simps.Entity.Inventario.Inventories;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

public interface IInventoriesService extends IBasicMethodsService<Inventories>{
	/**
     * Recupera una lista paginada de módulos que coincidan con una cadena de búsqueda.
     *
     * @param pageable información sobre la paginación
     * @param search la cadena de búsqueda para filtrar los módulos
     * @return una página de objetos Module que representan los módulos encontrados
     */
    public Page<IInventoriesDto> getDatatable(Pageable pageable, String search) throws Exception;
    
    public List<IInventoryFilterDto> getInventories(Long classroomId, String date);
    
    public List<IInventoryByIdDto> getInventory(Integer classroomId) throws Exception;
    
    public List<IInventoryFilterDto> getInventoriesId (Long inventoryId) throws Exception;
}
