import 'package:flutter/material.dart';

class LinealLayout extends StatefulWidget {
  const LinealLayout({super.key});

  @override
  State<LinealLayout> createState() => _LinealLayoutState();
}

class _LinealLayoutState extends State<LinealLayout> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: Text('LinearLayout Ejemplo'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text('Ejemplo de LinearLayout', style: TextStyle(fontSize: 20)),
              SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.blue,
                    child: Center(
                      child: Text('Elemento 1', style: TextStyle(color: Colors.white)),
                    ),
                  ),
                  SizedBox(width: 20),
                  Container(
                    width: 100,
                    height: 100,
                    color: Colors.green,
                    child: Center(
                      child: Text('Elemento 2', style: TextStyle(color: Colors.white)),
                    ),
                  ),
                ],
              ),
            ],
          ),
        )
    );
  }
}