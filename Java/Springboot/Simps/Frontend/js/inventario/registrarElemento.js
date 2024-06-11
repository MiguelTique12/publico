(function (window, document, undefined) {

    var factory = function ($, DataTable) {
        "use strict";

        $('.search-toggle').click(function () {
            if ($('.hiddensearch').css('display') == 'none')
                $('.hiddensearch').slideDown();
            else
                $('.hiddensearch').slideUp();
        });

        /* Set the defaults for DataTables initialisation */
        $.extend(true, DataTable.defaults, {
            dom: "<'hiddensearch'f'>" +
                "tr" +
                "<'table-footer'lip'>",
            renderer: 'material'
        });

        /* Default class modification */
        $.extend(DataTable.ext.classes, {
            sWrapper: "dataTables_wrapper",
            sFilterInput: "form-control input-sm",
            sLengthSelect: "form-control input-sm"
        });

        /* Bootstrap paging button renderer */
        DataTable.ext.renderer.pageButton.material = function (settings, host, idx, buttons, page, pages) {
            var api = new DataTable.Api(settings);
            var classes = settings.oClasses;
            var lang = settings.oLanguage.oPaginate;
            var btnDisplay, btnClass, counter = 0;

            var attach = function (container, buttons) {
                var i, ien, node, button;
                var clickHandler = function (e) {
                    e.preventDefault();
                    if (!$(e.currentTarget).hasClass('disabled')) {
                        api.page(e.data.action).draw(false);
                    }
                };

                for (i = 0, ien = buttons.length; i < ien; i++) {
                    button = buttons[i];

                    if ($.isArray(button)) {
                        attach(container, button);
                    } else {
                        btnDisplay = '';
                        btnClass = '';

                        switch (button) {

                            case 'first':
                                btnDisplay = lang.sFirst;
                                btnClass = button + (page > 0 ?
                                    '' : ' disabled');
                                break;

                            case 'previous':
                                btnDisplay = '<i class="material-icons">chevron_left</i>';
                                btnClass = button + (page > 0 ?
                                    '' : ' disabled');
                                break;

                            case 'next':
                                btnDisplay = '<i class="material-icons">chevron_right</i>';
                                btnClass = button + (page < pages - 1 ?
                                    '' : ' disabled');
                                break;

                            case 'last':
                                btnDisplay = lang.sLast;
                                btnClass = button + (page < pages - 1 ?
                                    '' : ' disabled');
                                break;

                        }

                        if (btnDisplay) {
                            node = $('<li>', {
                                'class': classes.sPageButton + ' ' + btnClass,
                                'id': idx === 0 && typeof button === 'string' ?
                                    settings.sTableId + '_' + button : null
                            })
                                .append($('<a>', {
                                    'href': '#',
                                    'aria-controls': settings.sTableId,
                                    'data-dt-idx': counter,
                                    'tabindex': settings.iTabIndex
                                })
                                    .html(btnDisplay)
                                )
                                .appendTo(container);

                            settings.oApi._fnBindAction(
                                node, {
                                action: button
                            }, clickHandler
                            );

                            counter++;
                        }
                    }
                }
            };

            // IE9 throws an 'unknown error' if document.activeElement is used
            // inside an iframe or frame. 
            var activeEl;

            try {
                // Because this approach is destroying and recreating the paging
                // elements, focus is lost on the select button which is bad for
                // accessibility. So we want to restore focus once the draw has
                // completed
                activeEl = $(document.activeElement).data('dt-idx');
            } catch (e) { }

            attach(
                $(host).empty().html('<ul class="material-pagination"/>').children('ul'),
                buttons
            );

            if (activeEl) {
                $(host).find('[data-dt-idx=' + activeEl + ']').focus();
            }
        };

        /*
         * TableTools Bootstrap compatibility
         * Required TableTools 2.1+
         */
        if (DataTable.TableTools) {
            // Set the classes that TableTools uses to something suitable for Bootstrap
            $.extend(true, DataTable.TableTools.classes, {
                "container": "DTTT btn-group",
                "buttons": {
                    "normal": "btn btn-default",
                    "disabled": "disabled"
                },
                "collection": {
                    "container": "DTTT_dropdown dropdown-menu",
                    "buttons": {
                        "normal": "",
                        "disabled": "disabled"
                    }
                },
                "print": {
                    "info": "DTTT_print_info"
                },
                "select": {
                    "row": "active"
                }
            });

            // Have the collection use a material compatible drop down
            $.extend(true, DataTable.TableTools.DEFAULTS.oTags, {
                "collection": {
                    "container": "ul",
                    "button": "li",
                    "liner": "a"
                }
            });
        }

    }; // /factory

    // Define as an AMD module if possible
    if (typeof define === 'function' && define.amd) {
        define(['jquery', 'datatables'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS
        factory(require('jquery'), require('datatables'));
    } else if (jQuery) {
        // Otherwise simply initialise as normal, stopping multiple evaluation
        factory(jQuery, jQuery.fn.dataTable);
    }

})(window, document);

function updateDateTime() {
    var currentDatetime = new Date().toLocaleString();
    $('#current-datetime').text(currentDatetime);
}

$(document).ready(function () {
    var table = $('#datatable').DataTable({
        language: {
            
        },
        dom: "<'hiddensearch'f><'pdf-button-container'>t" + "<'table-footer'lip'>", // Eliminamos los botones personalizados del DOM
        buttons: [
            {
                extend: 'pdfHtml5',
                text: '<i class="fas fa-file-pdf"></i> Exportar a PDF',
                className: 'custom-pdf-button',
                exportOptions: {
                    columns: [2], // Ajusta el número de columna que deseas personalizar
                },
                customize: function (doc) {
                    // Personaliza el PDF aquí si es necesario
                },
            },
            {
                extend: 'print',
                customize: function (win) {
                    // Personaliza el encabezado de la página
                    $(win.document.body).find('table').addClass('print-table');
                    $(win.document.body).find('thead tr').addClass('print-table-header');
                    // Agrega tu encabezado personalizado
                    $(win.document.body).find('thead').prepend('<tr><th colspan="6">Encabezado Personalizado</th></tr>');
                    // Personaliza el pie de página
                    $(win.document.body).find('tfoot tr').addClass('print-table-footer');
                    // Agrega tu pie de página personalizado
                    $(win.document.body).find('tfoot').prepend('<tr><td colspan="6">Pie de Página Personalizado</td></tr>');
                }
            }
        ],
        initComplete: function () {
            // Agrega una clase específica para impresión a la tabla
            $(this.api().table().container()).addClass('print-table');
        },
        ajax: {
            url: 'http://localhost:9000/simps/api/inventario/elements/datatable?page=0&size=150&column_order=description&column_direction=asc',
            dataSrc: 'data.content'
        },
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json', // Ruta al archivo de localización en español
            sStripClasses: '',
            sSearch: '',
            sSearchPlaceholder: 'Ingrese Algo Para Filtar',
            sLengthMenu: '<span>Columnas por página:</span><select class="browser-default">' +
                '<option value="10">10</option>' +
                '<option value="20">20</option>' +
                '<option value="30">30</option>' +
                '<option value="40">40</option>' +
                '<option value="50">50</option>' +
                '<option value="-1">All</option>' +
                '</select></div>'
        },
        columns: [
            {
                "targets": -1,
                "data": null,
                "render": function (data, type, row, meta) {
                    // La variable "meta.row" contiene el índice de la fila actual
                    var rowIndex = meta.row + 1; // Sumar 1 para obtener un índice basado en 1
                    return rowIndex;
                }
            },
            { "data": "name" },
            {
                data: "image",
                render: function (data, type, row) {
                    // Verifica si el tipo es 'display'. Esto garantiza que la función de renderizado solo se llame durante la visualización.
                    if (type === 'display') {
                        // Crea un elemento de imagen
                        var img = document.createElement('img');
                        // Establece el atributo 'src' del elemento de imagen con el valor base64
                        img.src = data; // Asegúrate de especificar el tipo de imagen adecuado aquí

                        // Establece un ancho y alto si es necesario
                        img.width = 60; // Cambia esto según tus necesidades
                        img.height = 60; // Cambia esto según tus necesidades

                        // Devuelve el elemento de imagen como contenido para la celda
                        return img.outerHTML;
                    }
                    return data;
                }
            },
            { "data": "description" },
            { "data": "brandId" },
            { "data": "typeElementId" },
            {
                "data": "state",
                "render": function (data, type, row) {
                    return data ? 'Activo' : 'Inactivo';
                }
            },

            // Columna de opciones (botones)
            {
                "targets": -1,
                "data": null,
                "render": function (data, type, row) {
                    return `
                    <button class='btn btn-warning edit-button' data-id="${data.id}" style="background-color: #ffc107;">
                    <i class="fa-solid fa-pen-to-square" style="font-size: 13px;"></i>
                </button>
                <button class='btn btn-danger delete-button' data-id="${data.id}" style="background-color: #d9534f;">
                    <i class="fa-solid fa-trash" style="font-size: 13px;"></i>
                </button>
                        `;
                }
            },
        ],
        autoWidth: false,
        "footerCallback": function (row, data, start, end, display) {
            var api = this.api();
            $('#datetime-footer').html('<div><strong>Fecha y hora actual: <span id="current-datetime"></span></strong></div>'); // Texto "Fecha y hora actual" en negrita
            updateDateTime(); // Llama a la función para actualizar la fecha y hora
        },

    });
    // Configura el botón personalizado después de que DataTables se haya inicializado
    $('#export-pdf-button').on('click', function () {
        table.button('.buttons-pdf').trigger(); // Dispara la exportación a PDF
    });

    $('.imprimir').on('click', function () {
        // Aquí puedes realizar las acciones de impresión que necesites.
        // Por ejemplo, puedes abrir una ventana emergente de impresión con el contenido de tu tabla.
        window.print();
    });

    function updateDateTime() {
        var currentDatetime = new Date();
        var formattedDatetime = currentDatetime.toLocaleString(); // Obtiene la fecha y hora en un formato legible

        $('#current-datetime').text(formattedDatetime); // Actualiza el contenido del elemento con la fecha y hora actual
    }
    updateDateTime(); // Llama a la función inicialmente

    setInterval(updateDateTime, 1000);
    $(document).on('click', '.edit-button', function () {
        var id = $(this).data('id');
        findById(id);
    });

    $(document).on('click', '.delete-button', function () {
        var id = $(this).data('id');
        deleteById(id);
    });

});

function loadTable() {
    $('#datatable').DataTable().ajax.reload(); // Recarga los datos del DataTable
}

// Añadir y Modificar
function Add() {
    var id = $("#id").val();
    var actualizar = !!id;

    var url = actualizar ? 'http://localhost:9000/simps/api/inventario/elements/' + id : 'http://localhost:9000/simps/api/inventario/elements';

    var input = document.getElementById('formFile');
    var imagen = $("#formFileHidden").val();
    var cambiarImagen = !!imagen;

    if (input.files && input.files[0]) {
        var file = input.files[0];
        var reader = new FileReader();

        reader.onload = function (e) {
            var base64Image = e.target.result;

            var method = actualizar ? "PUT" : "POST";

            var data = {
                name: $('#name').val(),
                image: base64Image,
                description: $('#description').val(),
                brandId: {
                    "id": $('#brandId').val(),
                },
                typesElements: {
                    "id": $('#typesElements').val(),
                },
                state: $('#state').prop('checked'),
                userCreationId: 1,
                dateCreation: new Date(),
                dateModification: new Date(),
                userModification: 1
            };

            $.ajax({
                url: url,
                data: JSON.stringify(data),
                method: method,
                contentType: 'application/json',
                success: function (result) {
                    var mensaje = actualizar ? '¡Hecho! Elemento Actualizado' : '¡Hecho! Elemento Guardado';

                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: mensaje,
                        showConfirmButton: false,
                        timer: 1500
                    });
                    $('#modal').modal('close');
                    loadTable();
                    clearData();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: jqXHR.responseJSON.message,
                        footer: 'Intente corregir los datos =C'
                    });
                }
            });
        };

        reader.readAsDataURL(file);
    } else if (cambiarImagen) {
        var method = actualizar ? "PUT" : "POST";

        var data = {
            name: $('#name').val(),
            image: imagen,
            description: $('#description').val(),
            brandId: {
                "id": $('#brandId').val(),
            },
            typesElements: {
                "id": $('#typesElements').val(),
            },
            state: $('#state').prop('checked'),
            userCreationId: 1,
            dateCreation: new Date(),
            dateModification: new Date(),
            userModification: 1
        };

        $.ajax({
            url: url,
            data: JSON.stringify(data),
            method: method,
            contentType: 'application/json',
            success: function (result) {
                var mensaje = actualizar ? '¡Hecho! Elemento Actualizado' : '¡Hecho! Elemento Guardado';

                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: mensaje,
                    showConfirmButton: false,
                    timer: 1500
                });
                $('#modal').modal('close');
                loadTable();
                clearData();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: jqXHR.responseJSON.message,
                    footer: 'Intente corregir los datos =C'
                });
            }
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'No se ha seleccionado un archivo de imagen',
            footer: 'Seleccione una imagen para continuar'
        });
    }
}


//Accion para eliminar un registro seleccionado 
function deleteById(id) {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Estás seguro?',
        text: "El resultado no se puede deshacer",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, Eliminar!',
        cancelButtonText: 'No, Cancelar!',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: 'http://localhost:9000/simps/api/inventario/elements/' + id,
                method: "delete",
                headers: {
                    "Content-Type": "application/json"
                }
            }).done(function (result) {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Hecho! Persona Eliminado',
                    showConfirmButton: false,
                    timer: 1500
                })
                loadTable();

            }).fail(function (jqXHR, textStatus, errorThrown) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: jqXHR.responseJSON.message,
                    footer: 'Intente corregir los datos =C'
                })
            })

        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                'Cancelado',
                'La persona no ha sido eliminada =D',
                'error'
            )
        }
    })

}
// Función para limpiar datos
function clearData() {
    $("#id").val("");
    $("#name").val("");
    $("#description").val("");
    $("#brandId").val("");
    $("#typesElements").val("");
    $("#formFile").val("");
    $("#formFileHidden").val("");

    // Restablecer la vista previa de la imagen y ocultarla
    $("#imagenMostrada").attr("src", "");
    $("#imagenSeleccionada").hide();

    // Ocultar cualquier mensaje de error
    $("#mensajeError").hide();

}

// Encontrar por Id 

function findById(id) {
    $.ajax({
        url: 'http://localhost:9000/simps/api/inventario/elements/' + id,
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (item) {
        $("#id").val(item.id)
        $("#name").val(item.name)
        $("#image").val(item.image)
        $("#description").val(item.description)
        $("#brandId").val(item.brandId.id)
        $("#typesElements").val(item.typesElements.id)
        if (item.state == true) {
            $('#state').prop('checked', true);
        } else {
            $('#state').prop('checked', false);
        }
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Consulta realizada con éxito',
            showConfirmButton: false,
            timer: 1200
        })
        // Abrir el modal
        $('#modal').modal('open');
    })
}

function mostrarImagen() {
    const input = document.getElementById('formFile');
    const imagenMostrada = document.getElementById('imagenMostrada');
    const imagenSeleccionada = document.getElementById('imagenSeleccionada');
    const mensajeError = document.getElementById('mensajeError');

    if (input.files && input.files[0]) {
        const file = input.files[0];
        const fileType = file.type;
        const validImageTypes = ['image/jpeg', 'image/png', 'image/gif'];


        const reader = new FileReader();
        reader.onload = function (e) {
            const colorThief = new ColorThief();
            const image = new Image();
            image.src = e.target.result;
            image.onload = function () {
                const dominantColor = colorThief.getColor(image);
                const rgbColor = `rgb(${dominantColor[0]}, ${dominantColor[1]}, ${dominantColor[2]})`;
                imagenMostrada.src = e.target.result;
                imagenSeleccionada.style.display = 'block';
                mensajeError.style.display = 'none';
                imagenMostrada.style.boxShadow = `5px 5px 10px ${rgbColor}`;
            };
        };
        reader.readAsDataURL(file);


    }
}