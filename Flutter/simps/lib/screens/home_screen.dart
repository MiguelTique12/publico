import 'package:flutter/material.dart';
import 'package:simps/screens/asistencia/asistencia_screen.dart';
import 'package:simps/screens/index_screen.dart';
import 'package:simps/screens/inventario/inventario_screen.dart';
import 'package:simps/screens/justificacion/justificacion_screen.dart';
import 'package:simps/screens/user_profile_screen.dart';

class HomeScreen extends StatefulWidget {
  HomeScreen({Key? key}) : super(key: key);

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();
  String currentModule = 'Saludo'; // Módulo inicial

  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    final bool isLargeScreen = width > 800;

    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        backgroundColor: const Color.fromARGB(255, 1, 52, 218),
        elevation: 0,
        titleSpacing: 0,
        leading: isLargeScreen
            ? null
            : IconButton(
                icon: const Icon(Icons.menu),
                color: const Color.fromARGB(255, 255, 255, 255),
                onPressed: () => _scaffoldKey.currentState?.openDrawer(),
              ),
        title: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Text(
                "S.I.M.P",
                style: TextStyle(
                  color: Color.fromARGB(255, 255, 255, 255),
                  fontWeight: FontWeight.bold,
                ),
              ),
              if (isLargeScreen) Expanded(child: _navBarItems())
            ],
          ),
        ),
        actions: const [
          Padding(
            padding: EdgeInsets.only(right: 16.0),
            child: CircleAvatar(child: _ProfileIcon()),
          )
        ],
      ),
      drawer: isLargeScreen ? null : _drawer(context), // Pasa el contexto aquí
      body: _buildBody(), // Llama a _buildBody para mostrar el contenido
    );
  }

  Widget _drawer(BuildContext context) => Drawer(
        child: Column(
          children: [
            Container(
              height: 240,
              color: const Color.fromARGB(255, 1, 52, 218),
              child: Image.asset(
                'lib/assets/logoSideBar.png',
                width: 310,
                height: 300,
              ),
            ),
            Expanded(
              child: ListView(
                children: [
                  _buildExpansionTile('Asistencias', [
                    'Visualizar Asistencias',
                  ], [
                    const AsistenciaScreen(),
                  ]),
                  _buildExpansionTile('Justificaciones', [
                    'Visualizar Justificaciones',
                  ], [
                    const JustificacionScreen(),
                  ]),
                  _buildExpansionTile('Inventarios', [
                    'Visualizar Inventarios',
                  ], [
                     InventarioScreen(),
                  ]),
                  const Divider(),
                  ..._menuItems.sublist(3).map(
                        (item) => ListTile(
                          onTap: () {
                            _scaffoldKey.currentState?.openEndDrawer();
                          },
                          title: Text(item),
                        ),
                      ),
                ],
              ),
            ),
            Container(
              color: const Color.fromARGB(255, 236, 235, 235),
              child: ListTile(
                onTap: () {
                  _handleLogout(context);
                },
                title: const Row(
                  children: [
                    SizedBox(
                        width: 8), // Espacio entre el ícono y el texto
                    Text(
                      'Cerrar Sesión',
                      style: TextStyle(
                        fontSize: 22,
                        color:
                            Colors.black, // Cambia el color del texto a negro
                      ),
                    ),
                    Icon(
                      Icons.exit_to_app,
                      color: Colors.black, // Cambia el color del ícono a negro
                      size: 30, // Tamaño del ícono
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      );

  Widget _navBarItems() => Row(
        mainAxisAlignment: MainAxisAlignment.end,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: _menuItems
            .map(
              (item) => InkWell(
                onTap: () {
                  changeModule(item);
                },
                child: Padding(
                  padding: const EdgeInsets.symmetric(
                      vertical: 24.0, horizontal: 16),
                  child: Text(
                    item,
                    style: const TextStyle(fontSize: 18),
                  ),
                ),
              ),
            )
            .toList(),
      );

  Widget _buildExpansionTile(
      String title, List<String> subTitles, List<Widget> screens) {
    return ExpansionTile(
      title: Row(
        children: [
          const Icon(
            Icons.assignment,
            color: Colors.black,
            size: 24.0,
          ),
          const SizedBox(width: 8),
          Text(title),
        ],
      ),
      children: List.generate(
        subTitles.length,
        (index) => ListTile(
          title: Text(subTitles[index]),
          onTap: () {
            changeModuleWithScreen(screens[index]);
          },
        ),
      ),
    );
  }

  void changeModule(String moduleName) {
    setState(() {
      currentModule = moduleName;
    });
    Navigator.pop(context); // Cierra el menú lateral
  }

  void changeModuleWithScreen(Widget screen) {
    setState(() {
      currentModule =
          'Custom'; // Cambiar a un valor que no coincide con ningún módulo existente
      _customScreen = screen;
    });
    Navigator.pop(context); // Cierra el menú lateral
  }

  Widget _buildBody() {
    if (_customScreen != null) {
      return _customScreen!;
    }

    switch (currentModule) {
      case 'Saludo':
        return _buildSaludo();
      default:
        return const Center(
          child: Text('Módulo no encontrado'),
        );
    }
  }

  Widget _buildSaludo() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Image.asset(
          'lib/assets/logoColegio.png',
          width: 350,
          height: 350,
        ),
        const SizedBox(height: 16),
        Container(
          margin: const EdgeInsets.symmetric(horizontal: 16.0),
          child: const Column(
            children: [
              Text(
                "¡Bienvenido!",
                style: TextStyle(
                  fontSize: 24,
                  fontWeight: FontWeight.bold,
                  fontFamily: 'Pacifico',
                ),
              ),
              SizedBox(height: 8),
              Text(
                "El sistema S.I.M.P móvil le permitirá sentir una gestión cómoda, optimizada y amigable de su historial. "
                "Esperamos con todos los ánimos disfrutes tu estancia aquí y logremos hacer que nos recomiendes!!"
                " 😊 ",
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontFamily: 'Caveat',
                  fontSize: 16,
                ),
                maxLines: 5,
                overflow: TextOverflow.ellipsis,
              ),
            ],
          ),
        ),
      ],
    );
  }

  void _handleLogout(BuildContext context) {
    // Implementa la lógica para cerrar sesión y redirigir a la IndexScreen.
    Navigator.of(context).pushReplacement(
      MaterialPageRoute(
        builder: (context) => const IndexScreen(),
      ),
    );
  }

  Widget? _customScreen;
}

final List<String> _menuItems = <String>[
  'Asistencias',
  'Justificaciones',
  'Inventarios',
];

class _ProfileIcon extends StatelessWidget {
  const _ProfileIcon({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        _handleProfileNavigation(context);
      },
      child: const Icon(Icons.person),
    );
  }

  void _handleProfileNavigation(BuildContext context) {
    Navigator.push(context, MaterialPageRoute(builder: (context) {
      return UserProfileScreen();
    }));
  }
}
