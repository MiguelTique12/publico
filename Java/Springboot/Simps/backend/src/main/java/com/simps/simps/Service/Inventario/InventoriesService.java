package com.simps.simps.Service.Inventario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Inventario.IInventoriesDto;
import com.simps.simps.Dto.Inventario.IInventoryByIdDto;
import com.simps.simps.Dto.Inventario.IInventoryFilterDto;
import com.simps.simps.Entity.Inventario.Inventories;
import com.simps.simps.Entity.Seguridad.Users;
import com.simps.simps.IRepository.Inventario.IInventoriesRepository;
import com.simps.simps.IService.Inventario.IInventoriesService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class InventoriesService extends ObjectTService<Inventories> implements IInventoriesService{

	@Autowired
	private IInventoriesRepository repository;

	@Override
	public Page<IInventoriesDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}

    
	public Inventories save(@Valid Inventories inventories) throws Exception {
		
		Users users = inventories.getUserId();
        Long userId = users.getId();
        
       inventories.setDate(LocalDateTime.now());
		
    	inventories.setDescription("Inventario hecho con normalidad");
    	inventories.setInventorySuccess(true);
    	
		Optional<IInventoriesDto> op = repository.getValidate(inventories.getDate(), inventories.getDescription() ,userId);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	
    	// Si no se cumple ninguna condición manda a descripción el valor "Inventario normal" 
    	return repository.save(inventories);
    	
    	
	}


	@Override
	public List<IInventoryFilterDto> getInventories(Long classroomId, String date) {
		// TODO Auto-generated method stub
		return repository.getInventories(classroomId, date);
	}


	@Override
	public List<IInventoryByIdDto> getInventory(Integer classroomId) throws Exception {
		// TODO Auto-generated method stub
		return repository.getInventory(classroomId);
	}


	@Override
	public List<IInventoryFilterDto> getInventoriesId(Long inventoryId) throws Exception {
		// TODO Auto-generated method stub
		return repository.getInventoriesId(inventoryId);
	}



}
