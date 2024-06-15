import 'package:flutter/material.dart';
import 'package:dropdown_search/dropdown_search.dart';

class JustificacionScreen extends StatefulWidget {
  const JustificacionScreen({super.key});

  @override
  State<JustificacionScreen> createState() => _JustificacionScreenState();
}

class _JustificacionScreenState extends State<JustificacionScreen> {
 DateTime? selectedDate;
  List<String> comboBoxOptions = ['Opción 1', 'Opción 2', 'Opción 3'];


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Justificación'),
        automaticallyImplyLeading: false, // Oculta la flecha de retroceso
        centerTitle: true, // Centra el título en la AppBar
        backgroundColor: const Color.fromARGB(255, 46, 90, 235),
      ),
      body: Align(
        alignment: Alignment.topCenter, // Centra en la parte superior
        child: Column(
          crossAxisAlignment:
              CrossAxisAlignment.center, // Centra horizontalmente
          children: [
            const SizedBox(height: 16),
            Row(
              mainAxisAlignment:
                  MainAxisAlignment.center, // Centra horizontalmente
              children: [
                const Icon(Icons.calendar_today),
                const SizedBox(width: 8),
                const Text(
                  'Fecha De La Justificación:',
                  style: TextStyle(fontSize: 13),
                ),
                const SizedBox(width: 8),
                ElevatedButton(
                  onPressed: () => _selectDate(context),
                  child: Text(
                    selectedDate != null
                        ? '${selectedDate!.day}/${selectedDate!.month}/${selectedDate!.year}'
                        : 'Fecha',
                    style: const TextStyle(fontSize: 14),
                  ),
                  style: ElevatedButton.styleFrom(
                    foregroundColor: Colors.white, backgroundColor: Colors.blue,
                  ),
                ),
              ],
            ),
            const SizedBox(height: 16),
            Container(
              constraints:
                  const BoxConstraints(maxWidth: 350), // Establece un ancho máximo
              child: DropdownSearch<String>(
                mode: Mode.MENU,
                items: comboBoxOptions,
                validator: (String? value) {
                  if (value == null || value.isEmpty) {
                    return 'Por favor ingrese algo';
                  }
                  return null;
                },
                label: 'Selecciona Una Asignatura',
                showClearButton: true,
                dropdownSearchDecoration: const InputDecoration(
                  prefixIcon: Icon(Icons.arrow_drop_down),
                  border: OutlineInputBorder(),
                ),
              ),
            ),
            const SizedBox(height: 16), // Espacio entre el Dropdown y el botón
            ElevatedButton(
              onPressed: () {
                // Agrega aquí la lógica para realizar la consulta
                // Puedes mostrar un diálogo o realizar cualquier acción necesaria.
              },
              child: const Text('Consultar'),
            ),
             const SizedBox(height: 16), // Espacio entre el Dropdown y el botón
            Column(
              children: [
                Card(
                  clipBehavior: Clip.antiAlias,
                  child: Column(
                    children: [
                      GestureDetector(
                        onTap: () {
                        },
                        child: ListTile(
                          leading: const Icon(Icons.arrow_drop_down_circle),
                          title: const Text('12/10/2023'),
                          subtitle: Text(
                            'Encargado: Jimmy David Aguirre',
                            style:
                                TextStyle(color: Colors.black.withOpacity(0.6)),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: Text(
                          'Greyhound divisively hello coldly wonderfully marginally far upon excluding.',
                          style:
                              TextStyle(color: Colors.black.withOpacity(0.6)),
                        ),
                      ),
                      ButtonBar(
                        alignment: MainAxisAlignment.start,
                        children: [
                          TextButton(
                            style: TextButton.styleFrom(foregroundColor: Colors.blue),
                            onPressed: () {
                              // Realiza alguna acción
                            },
                            child: const Text('Consultar Detalles'),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  Future<void> _selectDate(BuildContext context) async {
    final DateTime? pickedDate = await showDatePicker(
      context: context,
      initialDate: selectedDate ?? DateTime.now(),
      firstDate: DateTime(2000),
      lastDate: DateTime(2101),
    );
    if (pickedDate != null && pickedDate != selectedDate) {
      setState(() {
        selectedDate = pickedDate;
      });
    }
  }
}