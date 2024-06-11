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

var usersId = "";




function guardarDanoPerdida() {

    var description = $('#description').val();
    var valorSeleccionado = $("input[name='tipoMovimiento']:checked").val();
   

    if (
        (cargarElementClassroomIdCantidad.length === 0) || (description === "") || (valorSeleccionado == "")
    ) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: "Por favor llene todos los campos",
            footer: 'Intente corregir los datos =C'
        });
    } else {
        var urlMotions = "http://localhost:9000/simps/api/inventario/damagesLosses"
        var type = valorSeleccionado === "1" ? true : false;
        cargarElementClassroomIdCantidad.forEach(function (elemento) {

            var amountsElementsData = {
                amount: elemento.amount,
                elementClassroomId: elemento.elementsClassroomsId,
                description: description,
                state: true,
                type: type
            };

            $.ajax({
                url: urlMotions,
                method: 'POST',
                data: JSON.stringify(amountsElementsData),
                headers: {
                    "Content-Type": "application/json"
                }
            }).done(function (amountsElementsResult) {
                Swal.fire({
                    icon: 'success',
                    text: 'Movimiento Registrado',
                    customClass: {
                        container: 'custom-swal-container',
                        text: 'custom-swal-text'
                    },
                    toast: true,
                    position: 'top-start', // Cambiamos la posición a la parte superior
                    showConfirmButton: false,
                    timer: 3000, // 3 segundos
                    timerProgressBar: true, // Muestra la barra de progreso del temporizador
                    willClose: function () {
                        // Acciones a realizar cuando se cierra la alerta
                    }
                });
            }).fail(function (jqXHR, textStatus, errorThrown) {
                console.log("error en cantidad elementos")
            });

        });
    }
}
