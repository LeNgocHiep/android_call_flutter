
import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:untitled/DataService.dart';

class Untitled {
  static const MethodChannel _channel = MethodChannel('untitled');

  Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static final Untitled instance = Untitled._init();
  Untitled._init();

  void configureChannel() {
    _channel.setMethodCallHandler(methodHandler); // set method handler
  }

  Future<dynamic> methodHandler(MethodCall call) async {
    final String idea = call.arguments;

    switch (call.method) {
      case "showNewIdea": // this method name needs to be the same from invokeMethod in Android
        DataService.instance.addIdea(idea); // you can handle the data here. In this example, we will simply update the view via a data service
       if (kDebugMode) {
         print("object");
       }
       return true;
      default:
        if (kDebugMode) {
          print('no method handler for method ${call.method}');
        }
    }
  }
}
