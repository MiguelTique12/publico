import 'package:flutter/material.dart';

class Framelayout extends StatefulWidget {
  const Framelayout({super.key});

  @override
  State<Framelayout> createState() => _FramelayoutState();
}

class _FramelayoutState extends State<Framelayout> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
       appBar: AppBar(
          title: Text('Ejemplo de Stack en Flutter'),
        ),
        body: Stack(
          fit: StackFit.expand,
          children: <Widget>[
            // Fondo degradado
            Container(
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  colors: [Colors.blue, Colors.purple],
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                ),
              ),
            ),
            // Cuadrado en la esquina superior izquierda
            Positioned(
              top: 20,
              left: 20,
              child: Container(
                width: 100,
                height: 100,
                color: Colors.yellow,
                child: Center(
                  child: Text(
                    'Superior Izquierda',
                    style: TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
            ),
            // Cuadrado en la esquina inferior derecha
            Positioned(
              bottom: 20,
              right: 20,
              child: Container(
                width: 100,
                height: 100,
                color: Colors.green,
                child: Center(
                  child: Text(
                    'Inferior Derecha',
                    style: TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
    );
  }
}