var cargarElementClassroomIdCantidad = [];

app.directive('editableTd', [function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            element.css("cursor", "pointer");
            element.attr('contenteditable', 'true');

            element.bind('blur keyup change', function () {
                scope.lista[attrs.row][attrs.field] = element.text();
            });

            element.bind('click', function () {
                document.execCommand('selectAll', false, null)
            });
        }
    };
}]);

// Función para actualizar la hora y la fecha en el footer de la tabla
function actualizarHoraYFecha() {
    var footerContent = document.getElementById('footer-content');
    var fechaHoraActual = new Date();
    footerContent.textContent = 'Fecha y hora actual: ' + fechaHoraActual.toLocaleString();

    // Llama a la función para actualizar la hora y la fecha cada segundo (1000 ms)
    setInterval(actualizarHoraYFecha, 1000);
}





function cargarDatosDesdeBackendYActualizarTabla() {
    cargarElementClassroomIdCantidad = []; // Vacía el arreglo
    var selectedClassroomId = document.getElementById('classroomId').value;

    if (selectedClassroomId) {

        var url = 'http://localhost:9000/simps/api/inventario/inventories/inventario-salon/' + selectedClassroomId;

        $.ajax({
            url: url,
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).done(function (inventario) {
            // Obtén el cuerpo de la tabla
            var tablaBody = document.getElementById('tabla-body');

            // Limpia cualquier contenido existente en la tabla
            tablaBody.innerHTML = '';

            // Recorre los datos del inventario y agrega filas a la tabla
            inventario.forEach(function (item, index) {
                var fila = tablaBody.insertRow(index);
                fila.insertCell(0).textContent = index + 1;
                // Crea un elemento img para mostrar la imagen en la celda
                var imagenCell = fila.insertCell(1);
                var imagen = document.createElement('img');
                // Establece un ancho y alto si es necesario
                imagen.width = 60;
                imagen.height = 60;
                imagen.src = item.image;
                imagenCell.appendChild(imagen);
                fila.insertCell(2).textContent = item.name;
                fila.insertCell(3).textContent = item.brand;
                fila.insertCell(4).textContent = item.description;

                var cantidadCell = fila.insertCell(5);
                var inputCantidad = document.createElement("input");
                inputCantidad.type = "number";
                inputCantidad.disabled = false;
                inputCantidad.style.textAlign = "center"; // Alinea el texto en el centro vertical
                cantidadCell.appendChild(inputCantidad);

                // Crear un objeto para los datos del elemento
                var elemento = {
                    elementsClassroomsId: {
                        id: parseInt(item.elementClassroomId)
                    },
                    amount: 0  // Inicializa la cantidad en 0
                };

                cargarElementClassroomIdCantidad.push(elemento);

                inputCantidad.onblur = function () {
                    // Obtiene el nuevo valor del campo de entrada
                    var nuevoValor = parseInt(inputCantidad.value);

                    if (!isNaN(nuevoValor)) {
                        // Actualiza el valor en el objeto correspondiente del arreglo
                        cargarElementClassroomIdCantidad[index].amount = nuevoValor;
                        console.log(cargarElementClassroomIdCantidad);
                    } else {
                        console.warn('El valor ingresado en el campo de cantidad no es un número válido.');
                    }

                    // Actualiza el valor en el objeto correspondiente del arreglo
                    cargarElementClassroomIdCantidad[index].amount = nuevoValor;



                };
            });
        }).fail(function (error) {
            console.error('Error al cargar el inventario:', error);
        });
    } else {
        console.warn('Por favor, seleccione un salón válido.');
    }
}

// Esta función se llama cuando se cambia la opción del select
function cargarInventario() {
    cargarDatosDesdeBackendYActualizarTabla();
}




function guardarInventario() {
    var selectedClassroomId = document.getElementById('classroomId').value;

    if (!selectedClassroomId) {
        // Muestra una notificación de SweetAlert si no se ha seleccionado un salón
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Por favor, seleccione un salón antes de guardar el inventario.',
        });
        return; // Detén la función si no se ha seleccionado un salón
    }
    
    let usuarioId = parseInt(sessionStorage.getItem("usuarioId"));
    var urlInventories = 'http://localhost:9000/simps/api/inventario/inventories';

    $.ajax({
        url: urlInventories,
        data: JSON.stringify({
            userId: {
                id: usuarioId
            }
        }),
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (data) {
        console.log(data);
        var inventarioId = data.data.id;

        var urlAmountElements = 'http://localhost:9000/simps/api/inventario/amounts-elements';

        console.log(cargarElementClassroomIdCantidad);

        // Preguntar al usuario si desea enviar el inventario
        Swal.fire({
            title: '¿Desea enviar el inventario?',
            text: 'Puede cancelar para seguir llenando o confirmar para enviar el inventario.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Enviar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                // Recorre el arreglo para hacer varios registros en la tabla
                cargarElementClassroomIdCantidad.forEach(function (elemento) {

                    // Datos que se van a mandar en el JSON
                    var amountsElementsData = {
                        amounts: elemento.amount,
                        elementsClassroomsId: elemento.elementsClassroomsId,
                        inventoriesId: {
                            id: inventarioId
                        },
                        state: true
                    };

                    $.ajax({
                        url: urlAmountElements,
                        method: 'POST',
                        data: JSON.stringify(amountsElementsData),
                        headers: {
                            "Content-Type": "application/json"
                        }
                    }).done(function (amountsElementsResult) {
                        // Usando jQuery para seleccionar los input y establecer su valor en 0
                        $('input[type="number"]').val(0);
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                        console.log("error en cantidad elementos")
                    });
                });

                // Mostrar una alerta de éxito después de enviar el inventario
                Swal.fire({
                    icon: 'success',
                    title: 'Éxito',
                    text: 'El inventario se ha registrado exitosamente. Puede consultarlo en la bitácora de inventarios.',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Aceptar'
                });
            }
        });
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.error("Error en la solicitud para crear inventario:", textStatus, errorThrown);
    });
}




