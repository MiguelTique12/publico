window.addEventListener("load", function() {
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
                location.replace('../index.html');
            }
        });
    }
});
