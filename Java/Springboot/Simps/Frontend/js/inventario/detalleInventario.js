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





function cargarDatosDesdeBackendYActualizarTabla(inventoryId) {
    cargarElementClassroomIdCantidad = []; // Vacía el arreglo

    var url = 'http://localhost:9000/simps/api/inventario/inventories/filtrar-id/' + inventoryId;

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
            fila.insertCell(2).textContent = item.nameElement;
            fila.insertCell(3).textContent = item.brand;
            fila.insertCell(4).textContent = item.descriptionElement;

            var cantidadCell = fila.insertCell(5);
            var inputCantidad = document.createElement("input");
            inputCantidad.type = "number";
            inputCantidad.disabled = true;
            inputCantidad.style.textAlign = "center"; // Alinea el texto en el centro vertical
            inputCantidad.value = item.amount; // Alinea el texto en el centro vertical
            cantidadCell.appendChild(inputCantidad);

            // Abrir el modal
            $('#modal').modal('open');

        });
    }).fail(function (error) {
        console.error('Error al cargar el inventario:', error);
    });

}







