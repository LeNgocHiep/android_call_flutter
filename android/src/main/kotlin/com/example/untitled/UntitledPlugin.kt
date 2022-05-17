package com.example.untitled

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


/** UntitledPlugin */
class UntitledPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  init {
    // define in constructor
  }
  lateinit var context: Context
  private object Holder { val INSTANCE = UntitledPlugin() }

  companion object {
    @JvmStatic
    fun getInstance(): UntitledPlugin{
      return Holder.INSTANCE
    }
  }
  fun showNewIdea(idea: String) {
    channel.invokeMethod("showNewIdea", idea)
  }
  fun configureChannel(flutterEngine: FlutterEngine,contextEngine: Context) {
    context = contextEngine
    channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "untitled")
    channel.setMethodCallHandler(this)
  }

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "untitled")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      val noti = NotificationHelper()
      noti.createChannel(getInstance().context)
      noti.showNotification(getInstance().context)
//      val myReceiver = NotificationReceiver()
//      val intentFilter = IntentFilter(BROADCAST)
//      context.registerReceiver(myReceiver, intentFilter)
//      val intent = Intent(BROADCAST)
//      context.sendBroadcast(intent)
//      showNewIdea("Hiep")


      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
