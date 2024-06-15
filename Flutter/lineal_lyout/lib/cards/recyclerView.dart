import 'package:flutter/material.dart';

class Recyclerview extends StatefulWidget {
  const Recyclerview({super.key});

  @override
  State<Recyclerview> createState() => _RecyclerviewState();
}

class _RecyclerviewState extends State<Recyclerview> {
  final List<String> items = List<String>.generate(100, (index) => 'Elemento $index');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: Text('Ejemplo de RecyclerView en Flutter'),
        ),
        body: ListView.builder(
          itemCount: items.length,
          itemBuilder: (context, index) {
            return ListTile(
              title: Text(items[index]),
            );
          },
        ),
    );
  }
}