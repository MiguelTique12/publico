function loadModules(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/modules',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione Un Módulo--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.description+`</option>
                        `;
        })
        $("#moduleId").html(registros);   
    })
}
function loadViews(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/views',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione Una Vista--</option>`;
        items.forEach(function (item, index, array) {
            registros += `<option value="${item.id}" data-view-id="${item.id}">${item.label}</option>`;
        })
        $("#viewId").html(registros);   
    })
}
function loadRoles(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/roles',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione Un Rol--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.description+`</option>
                        `;
        })
        $("#roleId").html(registros);   
    })
}
function loadPersons(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/persons',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione Una Persona--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.firstName+`</option>
                        `;
        })
        $("#personId").html(registros);   
    })
}

function loadBrand(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/inventario/brands',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione una marca--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#brandId").html(registros);   
    })
}

function loadTypesElements(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/inventario/typesElements',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione uun tipo elemento--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#typesElements").html(registros);   
    })
}

function loadElement(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/inventario/elements',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione un elemento--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#elementId").html(registros);   
    })
}

function loadClassroom(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/classrooms',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione un salon--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#classroomId").html(registros);   
    })
}

function loadPerson(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/persons',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione una persona--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.firstName+`</option>
                        `;
        })
        $("#personId").html(registros);   
    })
}


function loadRole(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/roles',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione un rol--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.description+`</option>
                        `;
        })
        $("#roleId").html(registros);   
    })
}


function loadModule(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/modules',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione un modulo--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.description+`</option>
                        `;
        })
        $("#moduleId").html(registros);   
    })
}


function loadHeadquater(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/headquaters',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione una sede--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#headquaterId").html(registros);   
    })
}



function loadFloors(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/floors',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione una sede--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.floorNumber+`</option>
                        `;
        })
        $("#floorId").html(registros);   
    })
}


function loadInstitute(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/institutes',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione una institusion--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#instituteId").html(registros);   
    })
}

function loadTeachers(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/teachers',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione profesor--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.userId.personId.firstName+ ` `+item.userId.personId.secondName+` `+item.userId.personId.firstLastName+`</option>
                        `;
        })
        $("#teacherId").html(registros);   
    })
}


function loadTechniques(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/techniques',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione una tecnica--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#techniqueId").html(registros);   
    })
}


function loadGrades(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/grades',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione un grado--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#gradeId").html(registros);   
    })
}



function  loadClassrooms(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/classrooms',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione un salon --</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#classroomId").html(registros);   
    })
}


function loadCurses(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/curses',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione un curso --</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#curseName").html(registros);   
    })
}

function    loadUsers(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/seguridad/users',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione un usuario --</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
            <option value="`+item.id+`"> `+item.personId.firstName+`</option>
                        `;
        })
        $("#userId").html(registros);   
    })
}


function loadSubjects(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/subjects',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione--</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
                            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#subjectId").html(registros);   
    })
}

function convertirDia(diaIngles) {
    const dias = {
        'MONDAY': 'Lunes',
        'TUESDAY': 'Martes',
        'WEDNESDAY': 'Miércoles',
        'THURSDAY': 'Jueves',
        'FRIDAY': 'Viernes',
        'SATURDAY': 'Sábado',
        'SUNDAY': 'Domingo'
    };

    // Convierte a mayúsculas y traduce el día
    const diaEsp = dias[diaIngles.toUpperCase()];

    return diaEsp;
}

function loadSchendules() {
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/schendules',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione--</option>`;
        items.forEach(function (item, index, array) {
            const diaEsp = convertirDia(item.day);
            registros += `                       
                <option value="` + item.id + `">` + item.startTime + ` - ` + item.endTime + ` - ` + diaEsp + `</option>
            `;
        })
        $("#schenduleId").html(registros);
    })
}



function  loadClassroomId(){
    $.ajax({
        url: 'http://localhost:9000/simps/api/parametrizacion/classrooms',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = `<option selected="" selected disabled hidden value="">--Seleccione un salon --</option>`;
        items.forEach(function (item, index, array) {
            registros += `                       
            <option value="`+item.id+`">`+item.name+`</option>
                        `;
        })
        $("#classroomDestinationId").html(registros);   
    })
}