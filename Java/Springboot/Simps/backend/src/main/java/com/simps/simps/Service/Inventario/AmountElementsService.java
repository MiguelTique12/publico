package com.simps.simps.Service.Inventario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Inventario.IMotionInInventoryDto;
import com.simps.simps.Entity.Inventario.AmountsElements;
import com.simps.simps.Entity.Inventario.ElementsClassrooms;
import com.simps.simps.Entity.Inventario.Inventories;
import com.simps.simps.IRepository.Inventario.IAmountsElementsRepository;
import com.simps.simps.IRepository.Inventario.IInventoriesRepository;
import com.simps.simps.IService.Inventario.IAmountsElementsService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class AmountElementsService extends ObjectTService<AmountsElements> implements IAmountsElementsService {

	@Autowired
	private IAmountsElementsRepository repository;
	@Autowired
	private IInventoriesRepository inventoriesRepository;

	@Override
	public AmountsElements save(@Valid AmountsElements amountsElements) throws Exception {
		LocalDateTime today = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = today.format(formatter);

		ElementsClassrooms elementClassroom = amountsElements.getElementsClassroomsId();
		Long elementsClassroomsId = elementClassroom.getId();
		// Primero, guardamos el amountsElements en la base de datos
		AmountsElements savedAmountsElements = repository.save(amountsElements);

		// Luego, realizamos la validación con respecto a la base de datos actualizada
		Optional<IMotionInInventoryDto> opI = repository.getValidateInventory(amountsElements.getAmounts(),
				elementsClassroomsId);
		Optional<IMotionInInventoryDto> opM = repository.getValidateMotion(formattedDate);
		Optional<IMotionInInventoryDto> opD = repository.getValidateDamageLoss(formattedDate);

		if (opI.isPresent() && opI.get().getQuantityOne() < 1) {
			Inventories inventariosToUpdate = savedAmountsElements.getInventoriesId();
			Long invententarioModificar = inventariosToUpdate.getId();

			Inventories inventariosToUpdate2 = inventoriesRepository.findById(invententarioModificar).orElse(null);
			if (inventariosToUpdate != null) {
				inventariosToUpdate.setDate(LocalDateTime.now());
				inventariosToUpdate.setUserId(inventariosToUpdate2.getUserId());
				inventariosToUpdate.setInventorySuccess(false);
				inventariosToUpdate.setDescription("Hay una anormalidad respecto a inventarios anteriores");
				inventoriesRepository.save(inventariosToUpdate);
			}
		}
		if (opM.isPresent() && opM.get().getQuantityTwo() >= 1) {
			Inventories inventariosToUpdate = savedAmountsElements.getInventoriesId();
			Long invententarioModificar = inventariosToUpdate.getId();

			Inventories inventariosToUpdate2 = inventoriesRepository.findById(invententarioModificar).orElse(null);
			if (inventariosToUpdate != null) {
				inventariosToUpdate.setDate(LocalDateTime.now());
				inventariosToUpdate.setUserId(inventariosToUpdate2.getUserId());
				inventariosToUpdate.setInventorySuccess(false);
				inventariosToUpdate.setDescription("Hubo un movimiento en el inventario");
				inventoriesRepository.save(inventariosToUpdate);
			}
		}

		if (opD.isPresent() && opD.get().getQuantityThree() >= 1) {
			Inventories inventariosToUpdate = savedAmountsElements.getInventoriesId();
			Long invententarioModificar = inventariosToUpdate.getId();

			Inventories inventariosToUpdate2 = inventoriesRepository.findById(invententarioModificar).orElse(null);
			if (inventariosToUpdate != null) {
				inventariosToUpdate.setDate(LocalDateTime.now());
				inventariosToUpdate.setUserId(inventariosToUpdate2.getUserId());
				inventariosToUpdate.setInventorySuccess(false);
				inventariosToUpdate.setDescription("Hubo un daño o perdida en el inventario");
				inventoriesRepository.save(inventariosToUpdate);
			}
		}
		if (opD.isPresent() && opM.isPresent() && opD.get().getQuantityThree() >= 1
				&& opM.get().getQuantityTwo() >= 1) {
			Inventories inventariosToUpdate = savedAmountsElements.getInventoriesId();
			Long invententarioModificar = inventariosToUpdate.getId();

			Inventories inventariosToUpdate2 = inventoriesRepository.findById(invententarioModificar).orElse(null);
			if (inventariosToUpdate != null) {
				inventariosToUpdate.setDate(LocalDateTime.now());
				inventariosToUpdate.setUserId(inventariosToUpdate2.getUserId());
				inventariosToUpdate.setInventorySuccess(false);
				inventariosToUpdate.setDescription("Hubo un daño/perdida y movimiento en el inventario");
				inventoriesRepository.save(inventariosToUpdate);
			}
		}

		return savedAmountsElements;
	}

}
