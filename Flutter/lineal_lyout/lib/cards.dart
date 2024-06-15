import 'package:flutter/material.dart';
import 'cards/cardView.dart';
import 'cards/constrainLayout.dart';
import 'cards/frameLayout.dart';
import 'cards/linealLayout.dart';
import 'cards/listView.dart';
import 'cards/recyclerView.dart';
import 'cards/relativeLayout.dart';
import 'cards/pokeApi.dart';

class Cards extends StatelessWidget {
  const Cards({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
       
      ),
      body: ListView(
        children: [
          // Tarjeta 1: LinearLayout
          _buildCard(
            context: context,
            title: 'LinearLayout',
            description: 'El LinearLayout organiza los elementos de forma lineal, ya sea en horizontal o vertical.',
            image: 'lib/assets/images/ejemplo.png',
            nextPage: const LinealLayout()

          ),
          // Tarjeta 2: RelativeLayout
          _buildCard(
            context: context,
            title: 'RelativeLayout',
            description: 'El RelativeLayout permite posicionar los elementos de forma relativa unos a otros.',
            image: 'lib/assets/images/ejemplo.png',
            nextPage: const RelativeLayout()
          ),
          // Tarjeta 3: FrameLayout
          _buildCard(
            context: context,
            title: 'FrameLayout',
            description: 'El FrameLayout coloca los elementos uno encima del otro, ocupando todo el espacio disponible.',
            image: 'lib/assets/images/ejemplo.png',
            nextPage: const Framelayout()
          ),
          // Tarjeta 4: ConstraintLayout
          _buildCard(
            context: context,
            title: 'ConstraintLayout',
            description: 'El ConstraintLayout es un layout flexible que permite definir relaciones entre los elementos.',
            image: 'lib/assets/images/ejemplo.png',
            nextPage: const Constrainlayout()
          ),
          // Tarjeta 5: CardView
          _buildCard(
            context: context,
            title: 'CardView',
            description: 'El CardView es un contenedor que muestra contenido en una tarjeta con bordes redondeados.',
            image: 'lib/assets/images/ejemplo.png',
            nextPage: const Cardview()
          ),
          // Tarjeta 6: RecyclerView
          _buildCard(
            context: context,
            title: 'RecyclerView',
            description: 'El RecyclerView es una vista de lista avanzada que recicla las celdas de forma eficiente.',
            image: 'lib/assets/images/ejemplo.png',
            nextPage: const Recyclerview()
          ),
          // Tarjeta 7: ListView
          _buildCard(
            context: context,
            title: 'ListView',
            description: 'El ListView es una vista de lista simple que muestra elementos de forma vertical.',
            image: 'lib/assets/images/ejemplo.png',
            nextPage: const ListviewExample()
          ),
            _buildCard(
            context: context,
            title: 'PokeApi',
            description: 'API DE POKEMON.',
            image: 'lib/assets/images/ejemplo.png',
            nextPage: const PokeApi()

          ),
        ],
      ),
    );
  }

  // Método para construir una tarjeta con el tema específico
  Widget _buildCard({required BuildContext context, required String title, required String description, required String image, required Widget nextPage}) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => nextPage),
        );
      },
      child: Card(
        elevation: 5,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10.0),
        ),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Image.asset(
              image,
              width: double.infinity,
              height: 150,
              fit: BoxFit.cover,
            ),
            ListTile(
              title: Text(
                title,
                style: const TextStyle(fontWeight: FontWeight.bold),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(10.0),
              child: Text(
                description,
                textAlign: TextAlign.center,
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                IconButton(
                  icon: const Icon(Icons.favorite_border),
                  onPressed: () {
                    // Acción al hacer clic en el icono de "Me gusta"
                  },
                ),
                IconButton(
                  icon: const Icon(Icons.share),
                  onPressed: () {
                    // Acción al hacer clic en el icono de compartir
                  },
                ),
                IconButton(
                  icon: const Icon(Icons.more_vert),
                  onPressed: () {
                    // Acción al hacer clic en el icono de tres puntos
                  },
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
