document.addEventListener('DOMContentLoaded', function() {
  const calendarEl = document.getElementById('calendar');
  const modal = document.getElementById('myModal');
  const btnTabla = document.getElementById('btnTabla');
  const btnCalendario = document.getElementById('btnCalendario');

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
      events: [
        {
          title: 'All Day Event',
          start: '2023-09-01'
        },
        {
          title: 'Long Event',
          start: '2023-09-07',
          end: '2023-09-10'
        },
        {
          groupId: '999',
          title: 'Repeating Event',
          start: '2023-09-09T16:00:00'
        },
        {
          groupId: '999',
          title: 'Repeating Event',
          start: '2023-09-16T16:00:00'
        },
        {
          title: 'Conference',
          start: '2023-09-11',

        },
        {
          title: 'Meeting',
          start: '2023-09-12T10:30:00',
          end: '2023-09-12T12:30:00'
        },
        {
          title: 'Lunch',
          start: '2023-09-12T12:00:00'
        },
        {
          title: 'Meeting',
          start: '2023-09-12T14:30:00'
        },
        {
          title: 'Birthday Party',
          start: '2023-09-13T07:00:00'
        },
        {
          title: 'Click for Google',
          url: 'https://google.com/',
          start: '2023-09-28'
        }],eventClick: function(info) {
          modal.style.display = 'block';
        }
      });
    
      // Agregar el efecto de cambio de tamaño a los botones "Visualizar tabla" y "Visualizar calendario"
      btnTabla.addEventListener('click', function () {
        btnTabla.classList.add('active');
        btnCalendario.classList.remove('active');
      });
    
      btnCalendario.addEventListener('click', function () {
        btnCalendario.classList.add('active');
        btnTabla.classList.remove('active');
      });
    
      // Cuando se haga clic en el botón de cierre, ocultar el modal
      document.getElementById('closeModal').addEventListener('click', function() {
        modal.style.display = 'none';
      });
    
      calendar.render();
    });