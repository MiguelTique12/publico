document.addEventListener("DOMContentLoaded", function() {
    var input = document.getElementById("disabledInput");

    setInterval(function() {
        input.focus();
    }, 900);

    input.addEventListener("input", function() {
        var inputValue = input.value;
        if (inputValue.length > 9) {
            marcarAsistencia();
        }
    });

    
});


function clear(){
    $("#disabledInput").val("");
}

function marcarAsistencia(){

    var rfidInput = $("#disabledInput").val();

    var url = 'http://localhost:9000/simps/api/asistencia/records-attendances/with-rfid/'+rfidInput ;

    $.ajax({
        url: url,
        data: JSON.stringify({
            rfid : rfidInput,
            typeAssistance: 'RFID',
            state : true
        }),
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (result) {


        var personId = result.personId.id;
  
        $.ajax({
            url: 'http://localhost:9000/simps/api/seguridad/persons/' +personId,
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).done(function (item) {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Asistencia Registrada',
                text: "Bienvenido "+item.firstName+" "+item.firstLastName+"!",
                showConfirmButton: false,
                timer: 2000
              })
        })

        clear();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Esta tarjeta no se encuentra registrada', //jqXHR.responseJSON.message,
            footer: 'Intente corregir los datos =C'
        });
        clear();
    });


}

// Código JavaScript en el archivo 

document.addEventListener('DOMContentLoaded', function () {
    var fullscreenButton = document.getElementById('fullscreenButton');
    var iframe = window.frameElement;

    fullscreenButton.addEventListener('click', function () {
        toggleFullscreen(iframe);
    });

    function toggleFullscreen(element) {
        if (!document.fullscreenElement) {
            element.requestFullscreen().catch(err => {
                console.log(`Error al intentar entrar en el modo de pantalla completa: ${err.message}`);
            });
        } else {
            document.exitFullscreen();
        }
    }

    // Escuchar eventos de cambio en el modo de pantalla completa
    document.addEventListener('fullscreenchange', function () {
        if (!document.fullscreenElement) {
            // Actualizar el estado del botón si ya no estamos en modo de pantalla completa
            fullscreenButton.innerText = 'Fullscreen';
        }
    });
});

