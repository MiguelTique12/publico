import 'package:flutter/material.dart';

class Constrainlayout extends StatefulWidget {
  const Constrainlayout({super.key});

  @override
  State<Constrainlayout> createState() => _ConstrainlayoutState();
}

class _ConstrainlayoutState extends State<Constrainlayout> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
       appBar: AppBar(
          title: Text('Ejemplo de ConstraintLayout en Flutter'),
        ),
        body: Center(
          child: Container(
            padding: EdgeInsets.all(20.0),
            constraints: BoxConstraints(
              maxWidth: 300.0,
              maxHeight: 300.0,
            ),
            decoration: BoxDecoration(
              border: Border.all(color: Colors.black),
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                // Widget 1
                Container(
                  width: 100.0,
                  height: 100.0,
                  color: Colors.red,
                ),
                // Widget 2
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Container(
                      width: 50.0,
                      height: 50.0,
                      color: Colors.green,
                    ),
                    Container(
                      width: 50.0,
                      height: 50.0,
                      color: Colors.blue,
                    ),
                  ],
                ),
                // Widget 3
                Container(
                  width: 150.0,
                  height: 50.0,
                  color: Colors.yellow,
                ),
              ],
            ),
          ),
        ),
    );
  }
}