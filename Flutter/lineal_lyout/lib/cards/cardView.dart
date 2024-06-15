import 'package:flutter/material.dart';

class Cardview extends StatefulWidget {
  const Cardview({super.key});

  @override
  State<Cardview> createState() => _CardviewState();
}

class _CardviewState extends State<Cardview> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
       appBar: AppBar(
          title: Text('Ejemplo de CardView en Flutter'),
        ),
        body: Center(
          child: Card(
            elevation: 5, // Elevación de la tarjeta
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(10.0), // Bordes redondeados
            ),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: <Widget>[
                // Imagen de la tarjeta
                Image.network(
                  'https://via.placeholder.com/150',
                  width: double.infinity, // Ancho de la imagen igual al ancho de la tarjeta
                  height: 150,
                  fit: BoxFit.cover, // Ajustar la imagen al tamaño de la tarjeta
                ),
                // Título de la tarjeta
                ListTile(
                  title: Text(
                    'Título de la Tarjeta',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                ),
                // Descripción de la tarjeta
                Padding(
                  padding: EdgeInsets.all(10.0),
                  child: Text(
                    'Descripción de la tarjeta. Ejemplo visual de como es una CardView en Flutter.',
                    textAlign: TextAlign.center,
                  ),
                ),
              ],
            ),
          ),
        ),
    );
  }
}