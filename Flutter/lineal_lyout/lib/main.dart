import 'package:flutter/material.dart';
import 'cards.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Login Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const LoginPage(),
    );
  }
}

class LoginPage extends StatelessWidget {
  const LoginPage({super.key});

  @override
  Widget build(BuildContext context) {
    final screenWidth = MediaQuery.of(context).size.width;
    final boxWidth = screenWidth * 0.8;

    return Scaffold(
      body: Center(
        child: ListView(
          children: [
            const SizedBox(height: 70.0),
            Column(
              children: [
                SizedBox(
                  height: 130.0,
                  child: Column(
                    children: [
                      Image.asset(
                        'lib/assets/images/Flutter_logo.png',
                        height: 60.0,
                      ),
                      const SizedBox(height: 15.0),
                      const Text(
                        'My App',
                        style: TextStyle(
                            fontSize: 24.0, fontWeight: FontWeight.bold),
                      ),
                    ],
                  ),
                ),
                const SizedBox(height: 10.0),
                Container(
                  width: screenWidth * 0.6,
                  height: 15.0,
                  color: Colors.grey,
                ),
                const SizedBox(height: 20.0),
                SizedBox(
                  width: boxWidth,
                  child: const TextField(
                    decoration: InputDecoration(labelText: 'Usuario'),
                  ),
                ),
                const SizedBox(height: 20.0),
                SizedBox(
                  width: boxWidth,
                  child: const TextField(
                    obscureText: true,
                    decoration: InputDecoration(labelText: 'Contraseña'),
                  ),
                ),
                const SizedBox(height: 20.0),
                SizedBox(
                  width: boxWidth,
                  child: ElevatedButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) =>
                                const Cards()), //Pasar a la siguiente pagina
                      );
                    },
                    style: ElevatedButton.styleFrom(
                      backgroundColor:
                          Colors.grey, 
                    ),
                    child: const Text('Iniciar sesión',
                        style: TextStyle(color: Colors.white)),
                  ),
                ),
                const SizedBox(height: 20.0),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    ElevatedButton(
                      onPressed: () {
                        // Acción para el botón de Facebook
                      },
                      style: ElevatedButton.styleFrom(
                        backgroundColor: const Color.fromARGB(
                            255, 4, 96, 235), // Azul oscuro de Facebook
                        padding: const EdgeInsets.symmetric(
                            horizontal: 20.0, vertical: 16.0),
                        shape: RoundedRectangleBorder(
                          // Reduce el borde del botón
                          borderRadius: BorderRadius.circular(5),
                          side: const BorderSide(
                              color: Color.fromARGB(255, 4, 96, 235),
                              width: 1), // Define el grosor y color del borde
                        ),
                      ),
                      child: Row(
                        children: [
                          Image.asset(
                            'lib/assets/images/facebook.webp',
                            height: 24.0,
                            width: 24.0,
                          ),
                          const SizedBox(width: 8.0),
                          const Text('Facebook',
                              style: TextStyle(color: Colors.white)),
                        ],
                      ),
                    ),
                    ElevatedButton(
                      onPressed: () {
                        // Acción para el botón de Twitter
                      },
                      style: ElevatedButton.styleFrom(
                        backgroundColor:
                            const Color(0xFF1da1f2), 
                        padding: const EdgeInsets.symmetric(
                            horizontal: 20.0, vertical: 16.0),
                        shape: RoundedRectangleBorder(
                          // Reduce el borde del botón
                          borderRadius: BorderRadius.circular(5),
                          side: const BorderSide(
                              color: Color(0xFF1da1f2),
                              width: 1),
                        ),
                      ),
                      child: Row(
                        children: [
                          Image.asset(
                            'lib/assets/images/twitter.png',
                            height: 24.0,
                            width: 24.0,
                          ),
                          const SizedBox(width: 16.0),
                          const Text('Twitter',
                              style: TextStyle(color: Colors.white)),
                        ],
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
