$(document).ready(function () {
  var zindex = 10;

  $("div.card").click(function (e) {
    e.preventDefault();

    var isShowing = false;

    if ($(this).hasClass("show")) {
      isShowing = true
    }

    if ($("div.cards").hasClass("showing")) {
      // a card is already in view
      $("div.card.show")
        .removeClass("show");

      if (isShowing) {
        // this card was showing - reset the grid
        $("div.cards")
          .removeClass("showing");
      } else {
        // this card isn't showing - get in with it
        $(this)
          .css({ zIndex: zindex })
          .addClass("show");

      }

      zindex++;

    } else {
      // no cards in view
      $("div.cards")
        .addClass("showing");
      $(this)
        .css({ zIndex: zindex })
        .addClass("show");

      zindex++;
    }

  });
});


///////////////////////////OTRA COSA\\\\\\\\\\\\\\\\\\\\
function consultarInventario() {
  var classroomId = $("#classroomId").val();
  var inputDate = $("#date").val();

  // Valida si al menos uno de los campos está lleno
  if (!inputDate && !classroomId) {
    // Ninguno de los campos está lleno, muestra una alerta con SweetAlert2
    Swal.fire({
      icon: 'warning',
      title: 'Campos vacíos',
      text: 'Debes llenar al menos uno de los campos (Fecha o Salón) para consultar el inventario.',
    });
    return; // No realiza la consulta si no hay campos llenos
  }

  if (inputDate) {
    var formattedDate = formatInputDate(inputDate);
  }

  const formContainer = document.getElementById('formContainer');
  const cards = document.getElementById('cards');
  formContainer.style.width = '70%';
  formContainer.style.height = '0';
  formContainer.style.transition = 'all 0.5s';
  formContainer.style.top = '0'; // Establece la posición en la parte superior
  formContainer.innerHTML = '';

  setTimeout(() => {
    formContainer.style.height = '10%';
    formContainer.style.width = '70%';
    formContainer.style.marginTop = "2%";
    formContainer.style.transition = 'all 0.5s';
    formContainer.innerHTML = "<div class='header'> <i class='bx bx-arrow-back' onclick='cerrarFormulario()' style='margin-top: -40px;'></i><div style='text-align: center; margin-top: -50px;'><h2>Inventario consultado</h2></div><i class='bx bx-box' style='margin-top: -40px;'></i> </div>";

    // Utiliza un conjunto para hacer un seguimiento de los inventoryId procesados
    const processedIds = new Set();

    // Construir la URL de la solicitud AJAX de manera condicional
    let ajaxUrl = 'http://localhost:9000/simps/api/inventario/inventories/filtrar-inventario';

    if (formattedDate) {
      ajaxUrl += `?date=${formattedDate}`;
      if (classroomId) {
        ajaxUrl += `&classroomId=${classroomId}`;
      }
    } else if (classroomId) {
      ajaxUrl += `?classroomId=${classroomId}`;
    }
    // Realiza la solicitud AJAX para cargar dinámicamente las tarjetas
    $.ajax({
      url: ajaxUrl,
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .done(function (data) {
        // Limpia el contenedor de tarjetas antes de agregar nuevas tarjetas
        cards.innerHTML = '';

        // Procesa y agrega las tarjetas en función de la respuesta del servidor
        data.forEach(function (item) {
          // Si el inventoryId ya ha sido procesado, no se crea una tarjeta adicional
          if (!processedIds.has(item.inventoryId)) {
            const card = createCard(item); // Crea una tarjeta basada en los datos del elemento
            $('#cards').append(card); // Agrega la tarjeta al contenedor
            processedIds.add(item.inventoryId); // Agrega el inventoryId al conjunto de procesados
          }
        });
        clear();
      })
      .fail(function (error) {
        // Maneja los errores de la solicitud
        console.error('Error en la solicitud:', error);
      });

  }, 500);
}


// Función para crear una tarjeta dinámica basada en los datos del servidor
function createCard(item) {
  // Define la imagen y la descripción de la tarjeta en función de la descripción recibida
  let cardImage;
  if (item.description === 'Inventario hecho con normalidad') {
    cardImage = '<img src="../../assets/img/inventario/verificaciones/correcto.jpg" alt="">';
  } else if (item.description === 'Hay una anormalidad respecto a inventarios anteriores') {
    cardImage = '<img src="../../assets/img/inventario/verificaciones/erroneo.jpg" alt="">';
  } else {
    cardImage = '<img src="../../assets/img/inventario/verificaciones/anomalia.jpg" alt="">';
  }

  // Formatea la fecha para mostrar solo la fecha, no la hora
  const formattedDate = new Date(item.date).toLocaleDateString();


  localStorage.setItem('inventoryId', item.inventoryId);

  // Crea la tarjeta con los datos recibidos
  const card = `
    <div class="card">
      <div class="card__image-holder">
        ${cardImage}
      </div>
      <div class="card-title">
        <a class="toggle-info btn" onclick="cargarDatosDesdeBackendYActualizarTabla(`+item.inventoryId+`)">
          <span class="left"></span>
          <span class="right"></span>
        </a>
        <h2>${formattedDate}</h2>
        <small>${item.description}</small>
      </div>
    </div>
  `;

  return card;
}

function formatInputDate(inputDate) {
  const date = new Date(inputDate); // La fecha del input ya no tiene hora
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = (date.getDate() + 1).toString().padStart(2, '0');
  return `${year}-${month}-${day}`;
}


function cerrarFormulario() {
  const formContainer = document.getElementById('formContainer');
  const cards = document.getElementById('cards');
  formContainer.style.width = '70%';
  formContainer.style.height = '0';
  formContainer.style.transition = 'all 1s';
  formContainer.innerHTML = '';
  cards.innerHTML = '';
  // Eliminar el ícono y restaurar el contenido original
  setTimeout(() => {
    formContainer.style.height = '70%';
    formContainer.style.transition = 'all 0.5s';
    formContainer.classList.add('center-form'); // Agrega la clase para centrar
    formContainer.innerHTML = `
      <h2>Consulta de Inventario</h2>
      <div class="form-group">
          <label for="dateInput">Seleccione una fecha</label>
          <input type="date" class="form-control" id="date">
      </div>
      <div class="form-group">
          <label for="classroomId">Seleccione un salón</label>
          <div class="input-field">
              <select id="classroomId" name="classroomId" class="form-control">
                 
              </select>
          </div>
      </div>
      <button class="btn btn-primary" id="consultButton" onclick="consultarInventario()">Consultar Inventario</button>
    `;
    loadClassroom();
  }, 500);
}

function clear() {
  $("#classroomId").val("");
  $("#date").val("");
}
