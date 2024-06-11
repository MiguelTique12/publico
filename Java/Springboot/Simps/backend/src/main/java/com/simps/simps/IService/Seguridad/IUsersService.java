package com.simps.simps.IService.Seguridad;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simps.simps.Dto.Seguridad.ILoginDto;
import com.simps.simps.Dto.Seguridad.IPerfilUserDto;
import com.simps.simps.Dto.Seguridad.IPermissionDto;
import com.simps.simps.Dto.Seguridad.IUsersDto;
import com.simps.simps.Entity.Seguridad.Users;
import com.simps.simps.IService.ObjectT.IBasicMethodsService;

public interface IUsersService extends IBasicMethodsService<Users>{
	/**
     * Recupera una lista paginada de módulos que coincidan con una cadena de búsqueda.
     *
     * @param pageable información sobre la paginación
     * @param search la cadena de búsqueda para filtrar los módulos
     * @return una página de objetos Module que representan los módulos encontrados
     */
    public Page<IUsersDto> getDatatable(Pageable pageable, String search) throws Exception;
    
    
	 /**
     * Recupera los permisos de un usuario específico.
     *
     * @param user el nombre de usuario
     * @param password la contraseña del usuario
     * @return una lista de objetos IPermissionDto que representan los permisos del usuario
     */
    public List<IPermissionDto> getPermission(String user, String password) throws Exception;

    /**
     * Recupera los datos de inicio de sesión de un usuario específico.
     *
     * @param user el nombre de usuario
     * @param password la contraseña del usuario
     * @return el objeto ILoginDto que representa los datos de inicio de sesión del usuario, o un Optional vacío si no se encuentra ningún usuario con las credenciales proporcionadas
     */
    public Optional<ILoginDto> getLogin(String user, String password) throws Exception;
    
    public Optional<IPerfilUserDto> getPerfil(Integer userId);
}
