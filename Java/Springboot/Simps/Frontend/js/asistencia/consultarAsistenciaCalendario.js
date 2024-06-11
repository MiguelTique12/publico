document.addEventListener('DOMContentLoaded', function () {
  const calendarEl = document.getElementById('calendar');
  const modal = document.getElementById('modal');
  const dataStore = {}; // Objeto para almacenar los datos
  const calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    initialDate: new Date(),
    headerToolbar: {
      left: 'prev,next,today',
      center: 'title',
      right: 'dayGridMonth,timeGridDay'
    },
    
    buttonText: {
      today: 'Hoy',
      month: 'Meses',
      week: 'Semanas',
      day: 'Días'
    },
    locale: 'es',
    events: function (fetchInfo, successCallback, failureCallback) {
      var personId = sessionStorage.getItem("personId");
      $.ajax({
        url: 'http://localhost:9000/simps/api/asistencia/records-attendances/search-All-attendance?personId=' + personId,
        type: 'GET',
        success: function (response) {
          dataStore.events = response; // Almacena los datos en el objeto
          const events = response.map(event => ({
            title: event.description,
            start: event.date,
            color: event.description === 'Ingresó a tiempo' ? 'green' : 'yellow',
          }));
          successCallback(events);
        },
        error: function (xhr, status, error) {
          failureCallback(error);
        }
      });
    },
    eventClick: function (info) {
      // Abre el modal "modal"
      modal.style.display = "block";

      // Rellena el modal "modal" con información del evento
      document.getElementById('date').value = info.event.start.toISOString().slice(0, 10);
      document.getElementById('entryTime').value = info.event.start.toISOString().slice(11, 16);
      document.getElementById('description').value = info.event.title;
      console.log(dataStore);
      // Accede a los datos almacenados en dataStore y asigna el valor al campo 'subject'
      const eventDataItem = dataStore.events.find(event => {
        const eventDate = new Date(event.date);
        const eventHours = eventDate.getHours();
        const eventMinutes = eventDate.getMinutes();
        const eventSeconds = eventDate.getSeconds();
      
        const startHours = info.event.start.getHours();
        const startMinutes = info.event.start.getMinutes();
        const startSeconds = info.event.start.getSeconds();
      
        return (
          eventHours === startHours &&
          eventMinutes === startMinutes &&
          eventSeconds === startSeconds
        );
      });
      
      console.log(eventDataItem);
      
      if (eventDataItem) {
        document.getElementById('subject').value = eventDataItem.name;
      }
      document.getElementById('typeAssistance').value = eventDataItem.typeAssistance;
      
      // Puedes agregar más campos aquí si es necesario

      // Cierra el modal "modal" al hacer clic en la "X"
      const closeModal = document.getElementById('closeModal');
      closeModal.onclick = function () {
        modal.style.display = "none";
      };

      // Cierra el modal "modal" al hacer clic en cualquier otro lugar fuera del modal
      window.onclick = function (event) {
        if (event.target === modal) {
          modal.style.display = "none";
        }
      };
    },
  });
  calendar.render();
});

