document.getElementById("btn__email").addEventListener("click", email);
document.getElementById("btn__iniciar_sesion").addEventListener("click", iniciarSesion);
window.addEventListener("resize", anchoPagina);


var contenedor_login_email = document.querySelector(".contenedor__login-email");
var formulario_login = document.querySelector(".formulario__login");
var formulario__email = document.querySelector(".formulario__email");
var caja_trasera_login = document.querySelector(".caja__trasera-login");
var caja_trasera_email = document.querySelector(".caja__trasera-email");

function anchoPagina() {
    if (window.innerWidth > 850) {
        caja_trasera_login.style.display = "block";
        caja_trasera_email.style.display = "block";
    } else {
        caja_trasera_email.style.display = "block";
        caja_trasera_email.style.opacity = "1";
        caja_trasera_login.style.display = "none";
        formulario_login.style.display = "block";
        formulario__email.style.display = "none";
        contenedor_login_email.style.left = "0px";
    }
}

anchoPagina();

function email() {
    if (window.innerWidth > 850) {
        formulario__email.style.display = "block";
        contenedor_login_email.style.left = "410px";
        formulario_login.style.display = "none";
        caja_trasera_email.style.opacity = "0";
        caja_trasera_login.style.opacity = "1";
        contenedor_login_email.style.marginTop = "-60px";
        Clear();
    } else {
        formulario__email.style.display = "block";
        contenedor_login_email.style.left = "0px";
        formulario_login.style.display = "none";
        caja_trasera_email.style.display = "none";
        caja_trasera_login.style.display = "block";
        caja_trasera_login.style.opacity = "1";
    }
}

function iniciarSesion() {
    if (window.innerWidth > 850) {
        formulario__email.style.display = "none";
        contenedor_login_email.style.left = "10px";
        formulario_login.style.display = "block";
        caja_trasera_email.style.opacity = "1";
        caja_trasera_login.style.opacity = "0";
        contenedor_login_email.style.marginTop = "";
    } else {
        formulario__email.style.display = "none";
        contenedor_login_email.style.left = "0px";
        formulario_login.style.display = "block";
        caja_trasera_email.style.display = "block";
        caja_trasera_login.style.display = "none";
    }

}


function login() {
    let usuario = $("#txtuser").val();
    let contrasenia = $("#txtpassword").val();

    sessionStorage.setItem("usuario", usuario);
    sessionStorage.setItem("contrasenia", contrasenia);


    if ((usuario == "") || (contrasenia == "")) {  //COMPRUEBA CAMPOS VACIOS
        Swal.fire({
            icon: 'warning',
            title: 'Advertencia',
            text: 'Revisa que los campos no esten vacios',
            confirmButtonColor: '#0067ac',
        });
    } else {

        $.ajax({
            url: 'http://localhost:9000/simps/api/seguridad/users/login/' + usuario + '/' + contrasenia,
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).done(function (userData) {
            if (userData) {
                if (userData.quantity >= 1) {
                    const nombreUsuario = userData.userName;
                    const usuarioId = userData.userId;
                    sessionStorage.setItem("usuarioId", usuarioId);
                    Swal.fire({
                        icon: 'success',
                        title: 'Bienvenido',
                        text: `${nombreUsuario}`,
                        showDenyButton: true,
                        showCancelButton: false,
                        confirmButtonText: 'Continuar',
                        denyButtonText: `Cancelar`,
                        confirmButtonColor: '#0067ac',
                        allowOutsideClick: false,
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.assign('dashboard.html');

                        } else if (result.isDenied) {
                            Swal.fire('Se canceló el ingreso', '', 'info');
                            Clear();
                        }
                    });
                }
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error de autenticación',
                    text: 'Revisa que los datos ingresados son correctos',
                    confirmButtonColor: '#0067ac',
                    allowOutsideClick: false,
                });
                Clear();
            }
        }).fail(function (jqXHR, textStatus, errorThrown) {
            Swal.fire({
                icon: 'error',
                title: "Error",
                text: jqXHR.responseJSON.message,
            });
        });

    }
}


// Funcion para cargar los permisos del usuario
function loadPermission() {
    if (!sessionStorage.getItem('usuario') || !sessionStorage.getItem('contrasenia')) {
        Swal.fire({
            icon: 'error',
            title: 'Acceso no autorizado',
            showDenyButton: false,
            showCancelButton: false,
            confirmButtonText: 'Iniciar Sesión',
            allowOutsideClick: false,
        }).then((result) => {
            if (result.isConfirmed) {
                location.replace('index.html');
            }
        });
    } else {
        let usuario = sessionStorage.getItem("usuario");
        let contrasenia = sessionStorage.getItem("contrasenia");

        $.ajax({
            url: 'http://localhost:9000/simps/api/seguridad/users/permission/' + usuario + '/' + contrasenia,
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).done(function (items) {
            const permissionsByModule = {};

            // Iterar sobre los permisos del usuario
            items.forEach(function (item) {
                const moduleName = item.moduleLabel;
                const moduleIcon = item.icon;
                const viewRoute = item.moduleRoute + '/' + item.viewRoute;
                const viewLabel = item.viewLabel;

                // Si el módulo no existe en el objeto permissionsByModule, créalo
                if (!permissionsByModule[moduleName]) {
                    permissionsByModule[moduleName] = {
                        icono: moduleIcon,
                        views: []
                    };
                }

                // Agregar la vista al módulo correspondiente
                permissionsByModule[moduleName].views.push({
                    route: viewRoute,
                    label: viewLabel
                });
            });

            // Obtener el contenedor de menú
            let menuContainer = document.querySelector(".nav-links");

            // Iterar sobre los módulos y permisos obtenidos
            // Iterar sobre los módulos y permisos obtenidos
            for (const moduleName in permissionsByModule) {
                const moduleData = permissionsByModule[moduleName];
                const moduleIcon = moduleData.icono;
                const moduleViews = moduleData.views;

                // Crear un elemento de menú para el módulo con un menú desplegable
                const moduleItem = document.createElement("li");
                const iocnLinkDiv = document.createElement("div"); // Div para envolver el enlace
                const menuLink = document.createElement("a");
                const subMenu = document.createElement("ul");

                subMenu.className = "sub-menu"; // Agregar la clase "sub-menu" al elemento <ul>
                menuLink.href = "#";
                menuLink.innerHTML = `
                    <i class="icon"><i class="${moduleIcon}"></i></i>
                    <span class="link_name">${moduleName}</span>
                `;

                // Envolver el enlace dentro del div "iocn-link"
                iocnLinkDiv.className = "iocn-link";
                iocnLinkDiv.appendChild(menuLink);

                // Crear el icono para desplegar el menú
                const arrowIcon = document.createElement("i");
                arrowIcon.className = 'bx bxs-chevron-down arrow';

                // Adjuntar el evento clic al icono de la flecha para abrir el menú
                arrowIcon.addEventListener("click", () => {
                    iocnLinkDiv.parentElement.classList.toggle("showMenu");

                });

                // Agregar el icono de desplegar al div "iocn-link"
                iocnLinkDiv.appendChild(arrowIcon);

                // Iterar sobre las vistas y crear elementos de menú para cada vista en el submenú
                moduleViews.forEach(function (view) {
                    const viewItem = document.createElement("li");
                    const viewLink = document.createElement("a");

                    viewLink.href = view.route;
                    viewLink.target = "workSpace";
                    viewLink.textContent = view.label;

                    viewItem.appendChild(viewLink);
                    subMenu.appendChild(viewItem);
                });

                moduleItem.appendChild(iocnLinkDiv); // Agregar el div "iocn-link" al elemento de menú
                moduleItem.appendChild(subMenu);

                // Agregar el elemento de menú al contenedor de menú
                menuContainer.appendChild(moduleItem);

            }
            cargarPerfil();
        });
    }
    // Luego de crear los elementos de menú, llama a abrirmenu() para gestionar los submenús
    abrirmenu();
}

// Funcion para cargas los datos del usuarios
function cargarPerfil() {
    // Obtener el ID del usuario almacenado en sessionStorage
    let usuarioId = sessionStorage.getItem("usuarioId");

    // Realizar solicitud AJAX para obtener los datos del usuario
    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/users/perfil/' + usuarioId, // Reemplaza 'obtener_usuario' con la URL correcta
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (usuario) {
        var image = "https://definicion.de/wp-content/uploads/2019/07/perfil-de-usuario.png";
        
        let rolUsuario;
        sessionStorage.setItem("rolUsuario", rolUsuario);
        // Construir el contenido del perfil del usuario
        var data = `
      <div class="profile-details">
        <div class="profile-content">
          <img src="`+ (usuario.image == null ? image : usuario.image) + `" alt="profileImg">
        </div>
        <div class="name-job">
          <div class="profile_name">`+ usuario.firstName + ` ` + usuario.firstLastName + `</div>
          <div class="job">`+ usuario.rolDescription + `</div>
        </div>
        <i class='bx bx-log-out' onclick="SignOut()"></i>
      </div>
      `;

      var personId = usuario.personId;

      sessionStorage.setItem("personId", personId);
        // Agregar el contenido al elemento con id 'user-menu'
        $('#user-menu').html(data);

    });
}




function SignOut() {
    Swal.fire({
        title: '¡Hasta pronto!',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Cerrar Sesión',
        denyButtonText: `Continuar en la Sesión`,
        confirmButtonColor: '#0067ac',
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {

            // Display a message with an auto-close timer and redirect after it closes
            Swal.fire({
                title: 'Cerrando Sesión',
                text: 'Te esperamos pronto!',
                timer: 3000, // Set the timer to 3 seconds (3000 milliseconds)
                timerProgressBar: false, // Show a progress bar indicating the time remaining
                showConfirmButton: false, // Hide the confirm button
                didOpen: () => {
                    Swal.showLoading()
                    const b = Swal.getHtmlContainer().querySelector('b')
                    timerInterval = setInterval(() => {
                        b.textContent = Swal.getTimerLeft()
                    }, 100)
                },
                willClose: () => {
                    clearInterval(timerInterval)
                }
            }).then(() => {
                sessionStorage.clear();
                window.location.assign('index.html');
            });

            // LimpiarCredenciales();
        } else if (result.isDenied) {
            Swal.fire('Has Decidido Continuar! =D', '', 'info')
            Clear();
        }
    })
}

function Clear() {
    let userElement = document.getElementById('txtuser');
    let passwordElement = document.getElementById('txtpassword');
    $("#name").val("");
    $("#document").val("");
    $("#school").val("");
    $("#curse").val("");
    $("#mail").val("");
    $("#message").val("");
    if (userElement && passwordElement) {
        userElement.value = '';
        passwordElement.value = '';
    }
}

function solicitarCuenta() {
    var nombreCompleto = $('#name').val();
    var documentoPersona = $('#document').val();
    var colegioPersona = $('#school').val();
    var cursoPersona = $('#curse').val();
    var correoPersona = $('#mail').val();
    var asuntoPersona = 'Solicitud de cuenta';
    var comprobarCampo = $('#message').val();
    var asunto;
    if (comprobarCampo == "") {
        asunto = 'Solicitud de cuenta';
    } else {
        asunto = comprobarCampo;
    }

    if ((nombreCompleto == "") || (documentoPersona == "") || (colegioPersona == "") || (cursoPersona == "")
        || (correoPersona == "") || (asunto == "")) {  //COMPRUEBA CAMPOS VACIOS
        Swal.fire({
            icon: 'warning',
            title: 'Advertencia',
            text: 'Revisa que los campos no esten vacios',
            confirmButtonColor: '#0067ac',
        });
    } else {

        var url = 'http://localhost:9000/simps/api/seguridad/persons/enviar-correo?nombre=' + nombreCompleto + '&documento=' + documentoPersona + '&colegio=' + colegioPersona + '&curso=' + cursoPersona + '&correo=' + correoPersona + '&destinatario=simpsoriginal%40gmail.com&asunto=' + asuntoPersona + '&mensaje=' + asunto;

        var data = {
            nombre: nombreCompleto,
            documento: documentoPersona,
            colegio: colegioPersona,
            curso: cursoPersona,
            correo: correoPersona,
            destinatario: 'simpsoriginal@gmail.com',
            asunto: asuntoPersona,
            mensaje: asunto
        };

        $.ajax({
            url: url,
            data: JSON.stringify(data),
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            beforeSend: mostrarCargando()
        }).done(function (result) {
            var mensaje = 'Se ha enviado correctamente la solicitud'
            var textSuccess = 'Un administrador leerá tu petición lo antes posible, te pedimos que esperes '

            Swal.fire({
                position: 'center',
                icon: 'success',
                title: mensaje,
                text: textSuccess,
                showConfirmButton: true,
                timer: 5500,
                timerProgressBar: true
            });
            Clear()
        }).fail(function (jqXHR, textStatus, errorThrown) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: jqXHR.responseJSON.message,
                footer: 'Intente corregir los datos =C'
            });
        });
    }
}

function mostrarCargando(){
    Swal.fire({
        title: 'Enviando Correo',
        text: 'podria tardar unos segundos!',
        timer: 3000, // Set the timer to 3 seconds (3000 milliseconds)
        timerProgressBar: false, // Show a progress bar indicating the time remaining
        showConfirmButton: false, // Hide the confirm button
        didOpen: () => {
            Swal.showLoading()
            const b = Swal.getHtmlContainer().querySelector('b')
            timerInterval = setInterval(() => {
                b.textContent = Swal.getTimerLeft()
            }, 100)
        },
        willClose: () => {
            clearInterval(timerInterval)
        }
    }).then(() => {
        
    });
}

// Función para mostrar la alerta estilo tarjeta
function mostrarAlerta() {
    // Comprobar si es la primera visita
    if (localStorage.getItem('bandera') !== "false") {
      Swal.fire({
        toast: true,
        position: 'bottom-start', // Posición a la izquierda de la pantalla
        showConfirmButton: false,
        confirmButtonColor: "#364e7c",
        timer: 10000, // Duración en milisegundos (3 segundos)
        timerProgressBar: true,
        html: '<div style="background-color: white; border-radius: 10px; padding: 10px;">' +
            '<h3>Bienvenido</h3>' +
            '<p>Si deseas saber más acerca de nosotros, haz click en nuestra imagen.</p>' +
            '</div'
      });
      // Marcar la página como visitada
      localStorage.setItem('bandera', "false");
    }
  }
  
  // Llama a la función para mostrar la alerta al cargar la página
  document.addEventListener("DOMContentLoaded", mostrarAlerta);
  
  // Asigna un evento click a la imagen para mostrar la alerta nuevamente
  document.querySelector('.image img').addEventListener("click", sobreNosotros);
  
  function sobreNosotros() {
    window.location.assign('sobreNosotros.html');
  }