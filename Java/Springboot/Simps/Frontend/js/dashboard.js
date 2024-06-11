document.addEventListener("DOMContentLoaded", function () {
    // Recuperar permisos de la URL y decodificarlos
    const urlParams = new URLSearchParams(window.location.search);
    const permisos = JSON.parse(decodeURIComponent(urlParams.get('permisos')));

    // Llama a la función construirMenu con los permisos
    construirMenu(permisos);

    // Otro código relacionado con la página de dashboard

    // ...
});
