import 'dart:async';

class DataService {


  static final DataService instance = DataService._init();
  DataService._init() {
    ideaController = StreamController();
  }
  StreamController<String>? ideaController;
  addIdea(String newIdea) {
    ideaController?.add(newIdea);
  }
}