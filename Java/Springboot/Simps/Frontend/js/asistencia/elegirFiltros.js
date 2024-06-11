$(document).ready(function () {
    // Agrega un controlador de eventos "change" al elemento #gradeId
    $('#gradeId').on('change', function () {
        buscarCurso();
    });
});

function buscarCurso() {
    gradeId = $('#gradeId').val();
    $.ajax({
        url: 'http://localhost:9000/simps/api/asistencia/records-attendances/get-curses?gradeId=' + gradeId,
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (items) {
        var registros = '<option selected="" selected disabled hidden value="">--Seleccione un curso--</option>';
        items.forEach(function (item, index, array) {
            registros += '<option value="' + item.curseId + '">' + item.curseName + '</option>';
        });
        $('#curseId').html(registros);
    });
}


$(document).ready(function () {
    // Agrega un controlador de eventos "change" al elemento #gradeId
    $('#curseId').on('change', function () {
        buscarEstudiantes();
    });
});

function buscarEstudiantes(){

    curseId = $('#curseId').val();
    $.ajax({
        url: 'http://localhost:9000/simps/api/asistencia/records-attendances/get-name-students?curseId='+curseId,
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).done(function (items) {
        var registros = '<option selected="" selected disabled hidden value="">--Seleccione Un Estudiante--</option>';
        items.forEach(function (item, index, array) {
            registros += '<option value="' + item.personId + '">' + item.personName + '</option>';
        });
        $('#studentId').html(registros);
    });
}

function consultarAsistencia(){

    personId = $('#studentId').val();
    
    sessionStorage.setItem("personId", personId);

     // Redirige a otra página HTML
     window.location.href = "navBarAsistencia.html";
}