import 'package:flutter/material.dart';
import 'package:untitled/DataService.dart';
import 'package:untitled/untitled.dart';

void main() {
  runApp(const MyApp());
  Untitled.instance.configureChannel();
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        home: Scaffold(
          appBar: AppBar(
            title: const Text('Idea from Native Android'),
          ),
          body: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                ElevatedButton(
                    onPressed: () async{
                      await Untitled.instance.platformVersion;
                    },
                    child: const Text("data")),
                const Idea(),
              ],
            ),
          ),
        ));
  }
}

class Idea extends StatelessWidget {
  const Idea({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return StreamBuilder(
        stream: DataService.instance.ideaController!.stream,
        builder: (context, snapshot) {
          if (snapshot.data != null) {
            return Text(
              snapshot.data.toString(),
              style: Theme.of(context).textTheme.headline4,
            );
          }

          return Text(
            "Waiting for new idea",
            style: Theme.of(context).textTheme.headline4,
          );
        });
  }
}
