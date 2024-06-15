import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class PokeApi extends StatefulWidget {
  const PokeApi({super.key});

  @override
  State<PokeApi> createState() => _PokeApiState();
}

class _PokeApiState extends State<PokeApi> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('PokeAPI ejemplo'),
      ),
      body: Center(
        child: FutureBuilder<Pokemon>(
          future: fetchPokemon(),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return CircularProgressIndicator();
            } else if (snapshot.hasError) {
              return Text('Error: ${snapshot.error}');
            } else {
              return Text('Pokemon: ${snapshot.data!.name}');
            }
          },
        ),
      ),
    );
  }
}

class Pokemon {
  final String name;

  Pokemon({required this.name});

  factory Pokemon.fromJson(Map<String, dynamic> json) {
    return Pokemon(
      name: json['name'],
    );
  }
}

Future<Pokemon> fetchPokemon() async {
  final response =
      await http.get(Uri.parse('https://pokeapi.co/api/v2/pokemon/dsd'));
  if (response.statusCode == 200) {
    return Pokemon.fromJson(jsonDecode(response.body));
  } else {
    throw Exception('Fallo la carga del pokemon');
  }
}
