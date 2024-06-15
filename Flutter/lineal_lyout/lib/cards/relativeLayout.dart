import 'package:flutter/material.dart';

class RelativeLayout extends StatefulWidget {
  const RelativeLayout({super.key});

  @override
  State<RelativeLayout> createState() => _RelativeLayoutState();
}

class _RelativeLayoutState extends State<RelativeLayout> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
       appBar: AppBar(
          title: Text('RelativeLayout en Flutter'),
        ),
        body: Center(
          child: Stack(
            children: <Widget>[
              // Widget inferior (cuadrado azul)
              Container(
                width: 200,
                height: 200,
                color: Colors.blue,
              ),
              // Widget superior (cuadrado rojo), posicionado relativamente
              Positioned(
                top: 50,
                left: 50,
                child: Container(
                  width: 100,
                  height: 100,
                  color: Colors.red,
                ),
              ),
            ],
          ),
        ),
    );
  }
}