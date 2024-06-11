document.addEventListener('DOMContentLoaded', function () {
    const btnTabla = document.getElementById('btnTabla');
    const btnCalendario = document.getElementById('btnCalendario');

    // Establece un estado inicial
    btnTabla.classList.add('active');

    btnTabla.addEventListener('click', function () {
        btnTabla.classList.add('active');
        btnCalendario.classList.remove('active');
    });

    btnCalendario.addEventListener('click', function () {
        btnCalendario.classList.add('active');
        btnTabla.classList.remove('active');
    });
});

