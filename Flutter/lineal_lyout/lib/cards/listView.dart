import 'package:flutter/material.dart';

class ListviewExample extends StatefulWidget {
  const ListviewExample({super.key});

  @override
  State<ListviewExample> createState() => _ListviewExampleState();
}

class _ListviewExampleState extends State<ListviewExample> {
  final List<String> fruits = ['Manzana', 'Banana', 'Cereza', 'Uva', 'Naranja', 'Pera', 'Piña', 'Fresa', 'Kiwi', 'Melón'];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
       appBar: AppBar(
          title: Text('Lista de Frutas'),
        ),
        body: ListView.builder(
          itemCount: fruits.length,
          itemBuilder: (context, index) {
            return ListTile(
              leading: Icon(Icons.star), // Icono a la izquierda del texto
              title: Text(fruits[index]),
              onTap: () {
                // Acción al hacer clic en el elemento de la lista
                print('Hiciste clic en ${fruits[index]}');
              },
            );
          },
        ),
    );
  }
}