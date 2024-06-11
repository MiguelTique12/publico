package com.simps.simps.IService.Inventario;

import com.simps.simps.Entity.Inventario.RandomsCodes;

public interface IRandomsCodesService  {

	RandomsCodes save(RandomsCodes randomsCodes) throws Exception;
	
	Boolean validateCode(Integer code);
	
}
