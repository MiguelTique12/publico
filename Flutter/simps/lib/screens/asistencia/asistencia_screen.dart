import 'package:flutter/material.dart';
import 'package:dropdown_search/dropdown_search.dart';

class AsistenciaScreen extends StatefulWidget {
  const AsistenciaScreen({Key? key}) : super(key: key);

  @override
  State<AsistenciaScreen> createState() => _AsistenciaScreenState();
}

class _AsistenciaScreenState extends State<AsistenciaScreen> {
  DateTime? selectedDate;
  List<String> comboBoxOptions = ['Opción 1', 'Opción 2', 'Opción 3'];


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Asistencia'),
        automaticallyImplyLeading: false, // Oculta la flecha de retroceso
        centerTitle: true, // Centra el título en la AppBar
        backgroundColor: Color.fromARGB(255, 46, 90, 235),
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
                Text(
                  'Fecha de asistencia:',
                  style: TextStyle(fontSize: 13),
                ),
                const SizedBox(width: 8),
                ElevatedButton(
                  onPressed: () => _selectDate(context),
                  child: Text(
                    selectedDate != null
                        ? '${selectedDate!.day}/${selectedDate!.month}/${selectedDate!.year}'
                        : 'Fecha',
                    style: TextStyle(fontSize: 14),
                  ),
                  style: ElevatedButton.styleFrom(
                    primary: Colors.blue,
                    onPrimary: Colors.white,
                  ),
                ),
              ],
            ),
            const SizedBox(height: 16),
            Container(
              constraints:
                  BoxConstraints(maxWidth: 350), // Establece un ancho máximo
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
                dropdownSearchDecoration: InputDecoration(
                  prefixIcon: const Icon(Icons.arrow_drop_down),
                  border: OutlineInputBorder(),
                ),
              ),
            ),
            const SizedBox(height: 16), 
            ElevatedButton(
              onPressed: () {
               
              },
              child: Text('Consultar'),
            ),
            const SizedBox(height: 16), 
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
                          leading: Icon(Icons.arrow_drop_down_circle),
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
                            style: TextButton.styleFrom(primary: Colors.blue),
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
