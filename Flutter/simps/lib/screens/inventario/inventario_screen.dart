import 'package:flutter/material.dart';
import 'package:dropdown_search/dropdown_search.dart';

class InventarioScreen extends StatefulWidget {
  const InventarioScreen({Key? key}) : super(key: key);

  @override
  State<InventarioScreen> createState() => _InventarioScreenState();
}

class _InventarioScreenState extends State<InventarioScreen> {
  bool isImageVisible =
      false; // Declarar la variable en la parte superior de la clase
  DateTime? selectedDate;
  List<String> comboBoxOptions = ['Opción 1', 'Opción 2', 'Opción 3'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Inventario'),
        automaticallyImplyLeading: false,
        centerTitle: true,
        backgroundColor: const Color.fromARGB(255, 46, 90, 235),
      ),
      body: Align(
        alignment: Alignment.topCenter,
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const SizedBox(height: 16),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Icon(Icons.calendar_today),
                  const SizedBox(width: 8),
                  const Text(
                    'Fecha del inventario:',
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
                      foregroundColor: Colors.white,
                      backgroundColor: Colors.blue,
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 16),
              Container(
                constraints: const BoxConstraints(maxWidth: 350),
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
              const SizedBox(height: 16),
              ElevatedButton(
                onPressed: () {
                  // Agrega aquí la lógica para realizar la consulta
                  // Puedes mostrar un diálogo o realizar cualquier acción necesaria.
                },
                child: const Text('Consultar'),
              ),
              ExpansionTile(
                title: Card(
                  clipBehavior: Clip.antiAlias,
                  child: Column(
                    children: [
                      ListTile(
                        leading: const Icon(Icons.arrow_drop_down_circle),
                        title: const Text('12/10/2023'),
                        subtitle: Text(
                          'Encargado: Jimmy David Aguirre',
                          style:
                              TextStyle(color: Colors.black.withOpacity(0.6)),
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
                            style: TextButton.styleFrom(
                                foregroundColor: Colors.blue),
                            onPressed: () {},
                            child: const Text('Consultar Detalles'),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
                trailing: Icon(
                  isImageVisible
                      ? Icons.keyboard_arrow_up
                      : Icons.keyboard_arrow_down,
                ),
                onExpansionChanged: (expanded) {
                  setState(() {
                    isImageVisible = expanded;
                  });
                },
                children: [
                  if (isImageVisible)
                    Image.asset(
                      'lib/assets/inventario/anomalia.jpg',
                      fit: BoxFit
                          .cover, // Ajusta la imagen al tamaño de la tarjeta
                      height: 200, // Controla la altura de la imagen
                    ),
                ],
              ),
               ExpansionTile(
                title: Card(
                  clipBehavior: Clip.antiAlias,
                  child: Column(
                    children: [
                      ListTile(
                        leading: const Icon(Icons.arrow_drop_down_circle),
                        title: const Text('12/10/2023'),
                        subtitle: Text(
                          'Encargado: Jimmy David Aguirre',
                          style:
                              TextStyle(color: Colors.black.withOpacity(0.6)),
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
                            style: TextButton.styleFrom(
                                foregroundColor: Colors.blue),
                            onPressed: () {},
                            child: const Text('Consultar Detalles'),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
                trailing: Icon(
                  isImageVisible
                      ? Icons.keyboard_arrow_up
                      : Icons.keyboard_arrow_down,
                ),
                onExpansionChanged: (expanded) {
                  setState(() {
                    isImageVisible = expanded;
                  });
                },
                children: [
                  if (isImageVisible)
                    Image.asset(
                      'lib/assets/inventario/anomalia.jpg',
                      fit: BoxFit
                          .cover, // Ajusta la imagen al tamaño de la tarjeta
                      height: 200, // Controla la altura de la imagen
                    ),
                ],
              ),
               ExpansionTile(
                title: Card(
                  clipBehavior: Clip.antiAlias,
                  child: Column(
                    children: [
                      ListTile(
                        leading: const Icon(Icons.arrow_drop_down_circle),
                        title: const Text('12/10/2023'),
                        subtitle: Text(
                          'Encargado: Jimmy David Aguirre',
                          style:
                              TextStyle(color: Colors.black.withOpacity(0.6)),
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
                            style: TextButton.styleFrom(
                                foregroundColor: Colors.blue),
                            onPressed: () {},
                            child: const Text('Consultar Detalles'),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
                trailing: Icon(
                  isImageVisible
                      ? Icons.keyboard_arrow_up
                      : Icons.keyboard_arrow_down,
                ),
                onExpansionChanged: (expanded) {
                  setState(() {
                    isImageVisible = expanded;
                  });
                },
                children: [
                  if (isImageVisible)
                    Image.asset(
                      'lib/assets/inventario/anomalia.jpg',
                      fit: BoxFit
                          .cover, // Ajusta la imagen al tamaño de la tarjeta
                      height: 200, // Controla la altura de la imagen
                    ),
                ],
              ),
            ],
          ),
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
