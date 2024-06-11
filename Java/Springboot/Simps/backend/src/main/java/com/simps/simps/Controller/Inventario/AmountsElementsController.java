package com.simps.simps.Controller.Inventario;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simps.simps.Controller.ObjectT.ObjectTController;
import com.simps.simps.Entity.Inventario.AmountsElements;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/inventario/amounts-elements")
public class AmountsElementsController  extends ObjectTController<AmountsElements>{
	
	

}
