package com.simps.simps.Controller.Seguridad;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simps.simps.Controller.ObjectT.ObjectTController;
import com.simps.simps.Dto.ApiResponseDto;
import com.simps.simps.Entity.Seguridad.Persons;
import com.simps.simps.IService.Seguridad.IPersonsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.MimeMessage;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/seguridad/persons")
public class PersonsController extends ObjectTController<Persons> {

	@Autowired
	private IPersonsService service;
	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * Obtiene los datos para una tabla utilizando paginación y búsqueda.
	 *
	 * @param page            el número de página
	 * @param size            el tamaño de página
	 * @param columnOrder     el nombre de la columna para ordenar
	 * @param columnDirection la dirección de ordenamiento de la columna (ascendente
	 *                        o descendente)
	 * @param search          el término de búsqueda para filtrar los datos de la
	 *                        tabla (opcional)
	 * @return ResponseEntity que contiene un objeto ApiResponseDto con los datos de
	 *         la página y el estado de la respuesta
	 */
	@GetMapping("/datatable")
	public ResponseEntity<ApiResponseDto<Page<?>>> datatable(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size, @RequestParam(name = "column_order") String columnOrder,
			@RequestParam(name = "column_direction") String columnDirection,
			@RequestParam(name = "search", required = false) String search) {
		try {
			List<Order> orders = new ArrayList<>();

			orders.add(new Order(columnDirection.equals("asc") ? Direction.ASC : Direction.DESC, columnOrder));

			return ResponseEntity.ok(new ApiResponseDto<Page<?>>("Datos obtenidos",
					service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<Page<?>>(e.getMessage(), null, false));
		}
	}

	@Operation(summary = "Consultar la solicitud de cuenta mediante el formulario", responses = {
			@ApiResponse(responseCode = "204", description = "Formulario encontrado"),
			@ApiResponse(responseCode = "404", description = "Formulario no encontrado") })
	@GetMapping("/formulario")
	public String mostrarFormulario() {
		return "formulario"; // Renderiza el formulario HTML
	}

	@PostMapping("/enviar-correo")
	public ResponseEntity<String> enviarCorreo(@RequestParam("nombre") String nombre,
			@RequestParam("documento") String documento, @RequestParam("colegio") String colegio,
			@RequestParam("curso") String curso, @RequestParam("correo") String correo,
			@RequestParam("destinatario") String destinatario, @RequestParam("asunto") String asunto,
			@RequestParam("mensaje") String mensaje) throws Exception {
		// Construye el correo electrónico con los datos del formulario
		MimeMessage correoElectronico = javaMailSender.createMimeMessage();
		correoElectronico.addRecipients(RecipientType.TO, destinatario);
		correoElectronico.setSubject(asunto);

		// Aquí asigna el contenido HTML al cuerpo del correo
		String cuerpoCorreo = "<html>    <head>       <title>Solicitud de Cuenta</title>    </head>   "
		        + "				  <body>    <h1 style='color: #333;'>Solicitud de Cuenta</h1>   "
		        + "				  <p style='color: #fd0000;'>Estimado Equipo de Registro,</p>   "
		        + "				  <p style='color: #666;'>Espero que este mensaje les encuentre bien. Mi nombre es <span style='color:black'>"+nombre+"</span> y me gustaría solicitar una cuenta en su plataforma.</p>   "
		        + "				  <table style='width: 100%; border-collapse: collapse;'>        <tr>   "
		        + "				          <th style='padding: 8px; text-align: left; background-color: #f2f2f2;'>Nombre:</th>   "
		        + "				          <td style='padding: 8px; text-align: left;'></td>   "+nombre+"     </tr>   "
		        + "				      <tr>   "
		        + "				          <th style='padding: 8px; text-align: left; background-color: #f2f2f2;'>Documento de Identidad:</th>   "
		        + "				          <td style='padding: 8px; text-align: left;'></td>   "+documento+"     </tr>   "
		        + "				      <tr>   "
		        + "				          <th style='padding: 8px; text-align: left; background-color: #f2f2f2;'>Nombre del Colegio:</th>   "
		        + "				          <td style='padding: 8px; text-align: left;'></td>    "+colegio+"    </tr>   "
		        + "				      <tr>   "
		        + "				          <th style='padding: 8px; text-align: left; background-color: #f2f2f2;'>Curso:</th>   "
		        + "				          <td style='padding: 8px; text-align: left;'></td>   "+curso+"     </tr>   "
		        + "				      <tr>   "
		        + "				          <th style='padding: 8px; text-align: left; background-color: #f2f2f2;'>Correo Electrónico:</th>   "
		        + "				          <td style='padding: 8px; text-align: left;'></td>  "+correo+"      </tr>   "
		        + "				      <tr>   "
		        + "				          <th style='padding: 8px; text-align: left; background-color: #f2f2f2;'>Mensaje Adicional:</th>   "
		        + "				          <td style='padding: 8px; text-align: left;'></td> "+mensaje+"       </tr>   "
		        + "				  </table>   "
		        + "				  <p style='color: black;'>Agradezco su atención y quedo a su disposición para proporcionar cualquier información adicional que requieran. Espero con interés la aprobación de mi solicitud.</p> '  "
		        + "				  </body>    </html>";


		// Establece el contenido HTML en el cuerpo del correo
		correoElectronico.setContent(cuerpoCorreo, "text/html; charset=utf-8");
		

		// Establece el remitente en el encabezado
		correoElectronico.setFrom(correo);

		// Envía el correo electrónico
		javaMailSender.send(correoElectronico);

		// Devuelve una respuesta JSON con el mensaje
		return ResponseEntity.ok("El formulario fue correctamente enviado, Espera tu cuenta pacientemente =D");
	}
	
	@Operation(summary = "Obtener persona por documento", responses = {
		    @ApiResponse(responseCode = "200", description = "Persona encontrada"),
		    @ApiResponse(responseCode = "404", description = "Persona no encontrada")
		})
		@GetMapping("/persona-documento/{document}")
		public ResponseEntity<Persons> getPersonaByDocument(@PathVariable String document) {
		    Optional<Persons> persona = service.findByDocument(document);

		    if (persona.isPresent()) {
		        return ResponseEntity.ok(persona.get());
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
	

	
	
}
