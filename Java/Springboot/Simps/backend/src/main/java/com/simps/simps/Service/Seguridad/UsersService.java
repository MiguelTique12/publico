package com.simps.simps.Service.Seguridad;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simps.simps.Dto.Seguridad.ILoginDto;
import com.simps.simps.Dto.Seguridad.IPerfilUserDto;
import com.simps.simps.Dto.Seguridad.IPermissionDto;
import com.simps.simps.Dto.Seguridad.IUsersDto;
import com.simps.simps.Entity.Seguridad.Persons;
import com.simps.simps.Entity.Seguridad.Users;
import com.simps.simps.IRepository.Seguridad.IUsersRepository;
import com.simps.simps.IService.Seguridad.IUsersService;
import com.simps.simps.Service.ObjectT.ObjectTService;

import jakarta.validation.Valid;

@Service
public class UsersService extends ObjectTService<Users> implements IUsersService{

	@Autowired
	private IUsersRepository repository;

	@Override
	public Page<IUsersDto> getDatatable(Pageable pageable, String search) throws Exception {
		// TODO Auto-generated method stub
		return repository.getDatatable(pageable, search);
	}

	@Override
	public Users save(@Valid Users users) throws Exception {
		Persons persons = users.getPersonId();
        Long personId = persons.getId();
		
		Optional<IUsersDto> op = repository.getValidate(users.getUserName(), personId);
    	if (op.get().getQuantity()>=1) {
            throw new Exception("Validar datos, ya existe registro con este código o ruta.");
        }
    	return  repository.save(users);
    	
	}
	
	/**
     * Obtiene los permisos de un usuario y contraseña especificados.
     *
     * @param user     el nombre de usuario
     * @param password la contraseña del usuario
     * @return una lista de objetos IPermissionDto que representan los permisos del usuario
     * @throws Exception si ocurre un error al obtener los permisos
     */
    @Override
    public List<IPermissionDto> getPermission(String user, String password) throws Exception {
        return repository.getPermission(user, password);
    }

    /**
     * Obtiene los detalles de inicio de sesión de un usuario y contraseña especificados.
     *
     * @param user     el nombre de usuario
     * @param password la contraseña del usuario
     * @return un Optional que contiene un objeto ILoginDto que representa los detalles de inicio de sesión del usuario,
     *         o un Optional vacío si no se encontró el usuario o la contraseña no coincide
     * @throws Exception si ocurre un error al obtener los detalles de inicio de sesión
     */    
    @Override
    public Optional<ILoginDto> getLogin(String user, String password) throws Exception {
        return repository.getLogin(user, password);
    }

	@Override
	public Optional<IPerfilUserDto> getPerfil(Integer userId) {
		// TODO Auto-generated method stub
		return repository.getPerfil(userId);
	}    
}
