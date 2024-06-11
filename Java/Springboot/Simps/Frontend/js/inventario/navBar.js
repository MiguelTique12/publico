var str = '#len'; //increment by 1 up to 1-nelemnts
$(document).ready(function () {
    var i, stop;
    i = 1;
    stop = 4; //num elements
    setInterval(function () {
        if (i > stop) {
            return;
        }
        $('#len' + (i++)).toggleClass('bounce');
    }, 500)
}
);

function generarCodigo() {
    // Genera un número aleatorio de cuatro cifras
    var codigoAleatorio = Math.floor(1000 + Math.random() * 9000);

    let url = 'http://localhost:9000/simps/api/inventario/random-codes'
    var fechaActual = new Date();
    var fechaFormateada = fechaActual.toISOString(); // Formatea la fecha a ISO (yyyy-MM-ddTHH:mm:ss.sssZ)
    
    var data = {
        code: codigoAleatorio,
        state: true,
        dateCreation: fechaFormateada
    };

    $.ajax({
        url: url,
        data: JSON.stringify(data),
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (result) {
        var mensaje = 'El código aleatorio es este';

        Swal.fire({
            position: 'center',
            icon: 'success',
            title: codigoAleatorio,
            text: mensaje,
            timer: 4000, // Set the timer to 3 seconds (3000 milliseconds)
            timerProgressBar: true, // Show a progress bar indicating the time remaining
        });
    }).fail(function (jqXHR, textStatus, errorThrown) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: jqXHR.responseJSON.message,
            footer: 'Por favor vuelva a intentar'
        });
    });
}


function validarRol(){
    let usuarioId = sessionStorage.getItem("usuarioId");

    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/users/' + usuarioId, // Reemplaza 'obtener_usuario' con la URL correcta
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (usuarioData) {
        if (usuarioData) {
            if (usuarioData.roleId.description === 'Estudiante') {
              var divContainer = document.querySelector('.datatable');
          
              if (divContainer) {
                divContainer.style.marginTop = '150px';
              }
            }
          } else {
            console.log('La data viene vacía');
          }
          
    });
    
}

document.addEventListener("DOMContentLoaded", function() {
    validarRol();
  });