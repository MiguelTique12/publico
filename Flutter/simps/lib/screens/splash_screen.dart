import 'package:flutter/material.dart';
import 'dart:async';

import 'package:simps/screens/index_screen.dart';

class SplashScreen extends StatefulWidget {
  @override
  _SplashScreenState createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  double _opacity = 1.0;
  double _logoSize = 250.0;

  @override
  void initState() {
    super.initState();

    // Agrega un temporizador para mostrar la pantalla de inicio durante un tiempo determinado (por ejemplo, 2 segundos).
    Timer(Duration(seconds: 2), () {
      _startAnimation();
    });
  }

  void _startAnimation() {
    Future.delayed(Duration(milliseconds: 300), () {
      setState(() {
        _opacity = 0.5;
        _logoSize = 100.0;
      });
    });

    Future.delayed(Duration(milliseconds: 600), () {
      Navigator.of(context).push(MaterialPageRoute(
        builder: (BuildContext context) => const IndexScreen(),
      ));
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            AnimatedOpacity(
              opacity: _opacity,
              duration: Duration(milliseconds: 300), // Duración de la animación de opacidad
              child: AnimatedContainer(
                duration: Duration(milliseconds: 300), // Duración de la animación de tamaño
                width: _logoSize,
                height: _logoSize,
                child: Image.asset(
                  'lib/assets/logoSimps.png', // Ruta de tu imagen de logo
                  width: _logoSize, // Ancho deseado
                  height: _logoSize, // Alto deseado
                ),
              ),
            ),
            SizedBox(height: 20), // Espacio entre el logo y el texto
            AnimatedOpacity(
              opacity: _opacity,
              duration: Duration(milliseconds: 300), // Duración de la animación de opacidad
              child: Text(
                'Bienvenido 🔥🔥',
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
