function consultarAsistencias() {
    const fecha = $('#fecha').val();
    const asignatura = $('#subject').val();
    const tarjetasContainer = $('#tarjetas-container');
    var personId = sessionStorage.getItem("personId");
    // Realizar una solicitud Ajax
    // Comienza construyendo la URL con el parámetro obligatorio personId
    var url = `http://localhost:9000/simps/api/asistencia/records-attendances/search-attendance?personId=${personId}`;

    // Verifica si al menos uno de los campos (fecha o asignatura) está lleno
    if (!fecha && !asignatura) {
        // Muestra una alerta de SweetAlert2
        Swal.fire({
            icon: 'error',
            title: 'Campos vacíos',
            text: 'Debe llenar al menos uno de los campos (Fecha o Asignatura)',
        });
        return; // Detén la ejecución si no se llenó ningún campo
    }

    // Agrega la fecha a la URL si está presente
    if (fecha) {
        url += `&date=${fecha}`;
    }

    // Agrega la asignatura a la URL si está presente
    if (asignatura) {
        url += `&subject=${asignatura}`;
    }

    $.ajax({
        url: url,
        type: 'GET',
        success: function (data) {
            if (data.length === 0) {
                // Si no se encontraron asistencias, muestra una alerta con SweetAlert2
                Swal.fire({
                    icon: 'info',
                    title: 'No se encontraron asistencias',
                    text: 'No hay registros de asistencias disponibles.',
                });
            } else {
                // Limpia el contenedor de tarjetas
                tarjetasContainer.html('');

                data.forEach(function (item) {
                    const tarjeta = $('<div class="ag-courses_item"></div>');
                    const tarjetaLink = $(`<a href="#" class="ag-courses-item_link"></a>`);
                    const tarjetaBg = $(`<div class="ag-courses-item_bg"></div>`);

                    // Extraer la fecha en el formato deseado
                    const formattedDate = formatDate(new Date(item.date));
                    const tarjetaDateBox = $(`
                    <div class="ag-courses-item_date-box">
                        Fecha:
                        <span class="ag-courses-item_date">
                            ${formattedDate}
                        </span>
                    </div>
                `);
                    const tarjetaTitle = $(`
                    <div class="ag-courses-item_title">
                        ${item.description}
                    </div>
                `);

                    // Construir la estructura de la tarjeta
                    tarjetaLink.append(tarjetaBg);
                    tarjetaLink.append(tarjetaTitle);
                    tarjetaLink.append(tarjetaDateBox);
                    tarjeta.append(tarjetaLink);

                    tarjeta.click(function () {
                        // Formatear la fecha a "yyyy-mm-dd" (asumiendo que formattedDate está en "dd.mm.yyyy")
                        const parts = formattedDate.split('.');
                        const formattedDateForInput = `${parts[2]}-${parts[1]}-${parts[0]}`;
                        console.log(item.name);
                        $('#subjectId').val(item.name);
                        // Establecer la fecha en el campo #date
                        $('#date').val(formattedDateForInput);
                        $('#entryTime').val(item.entryTime); // Hora asistida

                        $('#description').val(item.description); // Descripción
                        $('#typeAssistance').val(item.typeAssistance); // Tipo de Asistencia
                        // Abre el modal
                        $('#modal').modal('show');
                    });
                    // Agregar la tarjeta al contenedor
                    tarjetasContainer.append(tarjeta);
                    const Toast = Swal.mixin({
                        toast: true,
                        position: 'top-end',
                        showConfirmButton: false,
                        timer: 3000,
                        timerProgressBar: true,
                        didOpen: (toast) => {
                            toast.addEventListener('mouseenter', Swal.stopTimer)
                            toast.addEventListener('mouseleave', Swal.resumeTimer)
                        }
                    })

                    Toast.fire({
                        icon: 'success',
                        title: 'Asistencia consultada'
                    })
                });
            }
        },
        error: function (error) {
            console.log('Error en la solicitud: ' + error);
        },
    });
}

// Función para formatear la fecha
function formatDate(date) {
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    // Formatear la fecha como dd.mm.yyyy
    return `${day}.${month}.${year}`;
}
