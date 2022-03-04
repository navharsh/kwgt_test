package dev.dhanraj.kwgt.test.dashboard;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import android.content.ContextWrapper;
import android.widget.Toast;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "dev.dhanraj.kwgt.test.dashboard";

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
  super.configureFlutterEngine(flutterEngine);
    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
        .setMethodCallHandler(
          (call, result) -> {
            // Note: this method is invoked on the main thread.
            if (call.method.equals("enable")) {
              ContextWrapper aContext = new ContextWrapper(getApplicationContext());
              aContext.getPackageManager().setComponentEnabledSetting(new android.content.ComponentName(aContext, "org.kustom.api.Provider"), android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 1);
              result.success(null);
              Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            } else{
              result.notImplemented();
            }
          }
        );
          }
  }

// enable/disable ContentProvider programmatically
