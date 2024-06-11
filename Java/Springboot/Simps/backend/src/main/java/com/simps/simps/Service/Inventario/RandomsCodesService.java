package com.simps.simps.Service.Inventario;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Inventario.IRandomCodesDto;
import com.simps.simps.Entity.Inventario.RandomsCodes;
import com.simps.simps.IRepository.Inventario.IRandomCodesRepository;
import com.simps.simps.IService.Inventario.IRandomsCodesService;

import jakarta.validation.Valid;

@Service
public class RandomsCodesService implements IRandomsCodesService {

	@Autowired
	private IRandomCodesRepository repository;

	@Override
	public RandomsCodes save(@Valid RandomsCodes randomsCodes) throws Exception {
		
		LocalDateTime now = LocalDateTime.now();
		randomsCodes.setDateCreation(now);
	   
	    return repository.save(randomsCodes);
	}

	@Override
	public Boolean validateCode(Integer code) {
	    Optional<IRandomCodesDto> validation = repository.validateCode(code);

	    if (validation.isPresent()) {
	        Integer randomCode = validation.get().getCode();
	        Boolean stateCode = validation.get().getState();
	        LocalTime now = LocalTime.now();
	        LocalTime endTime = validation.get().getDateCreation().plusMinutes(30);

	        if (now.isBefore(endTime) && code.equals(randomCode) && stateCode) {
	            // Obtener el objeto RandomsCodes correspondiente
	            Long id = validation.get().getId();
	            Optional<RandomsCodes> randomCodeId = repository.findById(id);

	            if (randomCodeId.isPresent()) {
	                RandomsCodes randomCodeObj = randomCodeId.get();
	                // Actualizar el estado del código a false
	                randomCodeObj.setState(false);
	                // Guardar la actualización en la base de datos
	                repository.save(randomCodeObj);
	            }

	            return true;
	        }
	    }

	    // En caso de que no se encuentre la validación, se retorna false
	    return false;
	}



	
	
	
}
