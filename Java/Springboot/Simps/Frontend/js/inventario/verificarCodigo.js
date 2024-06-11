function darAcceso() {
    let code = $('#code').val();

    if (code == "") { 
        Swal.fire({
            icon: 'warning',
            title: 'Advertencia',
            text: 'Revisa que los campos no esten vacios',
            confirmButtonColor: '#0067ac',
        });
    } else {

        $.ajax({
            url: "http://localhost:9000/simps/api/inventario/random-codes/validate/code?code=" + code,
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).done(function (codeData) {
            if (codeData) {
                if (codeData) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Ahora puedes administrar el inventario',
                        showDenyButton: false,
                        showCancelButton: false,
                        confirmButtonText: 'Continuar',
                        denyButtonText: `Cancelar`,
                        confirmButtonColor: '#0067ac',
                        allowOutsideClick: false,
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.assign('realizarInventario.html');
                            var divContainer = document.querySelector('.row.datatable');
                            if (divContainer) {
                                divContainer.style.marginTop = '170px';
                              }

                        } else if (result.isDenied) {
                            Swal.fire('Se canceló el ingreso', '', 'info');
                            Clear();
                        }
                    });
                }
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'El código no es valido',
                    text: 'Revisa que los datos ingresados son correctos o que el código aún sea valido',
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

function clear(){
    $('#code').val("");
}