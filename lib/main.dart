import 'dart:io';

import 'package:flutter_play_asset_delivery/flutter_play_asset_delivery.dart';

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Future<File>? image;
  Future<File>? image2;
  Future<File>? image3;
  Future<File>? image4;
  Future<File>? image5;
  Future<File>? image6;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: ListView(
          children: <Widget>[
            TextButton(
                onPressed: () {
                  image = FlutterPlayAssetDelivery.getAssetFile(
                      "A_Better_Weather.kwgt.zip");
                },
                child: const Text('1')),
            FutureBuilder(
              future: image,
              builder: (_, AsyncSnapshot<File> snapshot) {
                if (!snapshot.hasData) return const CircularProgressIndicator();
                if (!snapshot.hasError) {
                  return const CircularProgressIndicator(
                    color: Colors.red,
                    value: 1,
                  );
                }
                return const Text('dn');
              },
            ),
            TextButton(
                onPressed: () {
                  image2 = FlutterPlayAssetDelivery.getAssetFile(
                      "kuper_calendar.kwgt");
                },
                child: const Text('2')),
            FutureBuilder(
              future: image2,
              builder: (_, AsyncSnapshot<File> snapshot) {
                if (!snapshot.hasData) return const CircularProgressIndicator();
                if (!snapshot.hasError) {
                  return const CircularProgressIndicator(
                    color: Colors.red,
                    value: 1,
                  );
                }
                return const Text('dn');
              },
            ),
            TextButton(
                onPressed: () {
                  image3 =
                      FlutterPlayAssetDelivery.getAssetFile("kuper_music.kwgt");
                },
                child: const Text('3')),
            FutureBuilder(
              future: image3,
              builder: (_, AsyncSnapshot<File> snapshot) {
                if (!snapshot.hasData) return const CircularProgressIndicator();
                if (!snapshot.hasError) {
                  return const CircularProgressIndicator(
                    color: Colors.red,
                    value: 1,
                  );
                }
                return const Text('dn');
              },
            ),
            TextButton(
                onPressed: () {
                  image4 =
                      FlutterPlayAssetDelivery.getAssetFile("kuper_strip.kwgt");
                },
                child: const Text('4')),
            FutureBuilder(
              future: image4,
              builder: (_, AsyncSnapshot<File> snapshot) {
                if (!snapshot.hasData) return const CircularProgressIndicator();
                if (!snapshot.hasError) {
                  return const CircularProgressIndicator(
                    color: Colors.red,
                    value: 1,
                  );
                }
                return const Text('dn');
              },
            ),
            TextButton(
                onPressed: () {
                  image5 =
                      FlutterPlayAssetDelivery.getAssetFile("kuper_type.kwgt");
                },
                child: const Text('5')),
            FutureBuilder(
              future: image5,
              builder: (_, AsyncSnapshot<File> snapshot) {
                if (!snapshot.hasData) return const CircularProgressIndicator();
                if (!snapshot.hasError) {
                  return const CircularProgressIndicator(
                    color: Colors.red,
                    value: 1,
                  );
                }
                return const Text('dn');
              },
            ),
            TextButton(
                onPressed: () {
                  image6 = FlutterPlayAssetDelivery.getAssetFile(
                      "kuper_unread_counter.kwgt");
                },
                child: const Text('6')),
            FutureBuilder(
              future: image6,
              builder: (_, AsyncSnapshot<File> snapshot) {
                if (!snapshot.hasData) return const CircularProgressIndicator();
                if (!snapshot.hasError) {
                  return const CircularProgressIndicator(
                    color: Colors.red,
                    value: 1,
                  );
                }
                return const Text('dn');
              },
            ),
          ],
        ),
      ),
    );
  }
}
