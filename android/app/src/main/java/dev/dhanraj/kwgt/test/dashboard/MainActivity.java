package dev.dhanraj.kwgt.test.dashboard;



import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import android.os.Environment;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.google.android.play.core.assetpacks.AssetPackLocation;
import com.google.android.play.core.assetpacks.AssetPackManager;
import com.google.android.play.core.assetpacks.AssetPackManagerFactory;
import com.google.android.play.core.assetpacks.AssetPackState;
import com.google.android.play.core.assetpacks.AssetPackStateUpdateListener;
import com.google.android.play.core.assetpacks.AssetPackStates;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;


import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "dev.dhanraj.kwgt.test.dashboard";
    private AssetPackManager assetPackManager;
    private final String TAG = "MainActivity";

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
  super.configureFlutterEngine(flutterEngine);
    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
        .setMethodCallHandler(
          (call, result) -> {
            if (call.method.equals("download_asset_pack")) {
                        String asset_name = call.argument("asset_name");
                        registerListener(asset_name);
                    } else{
                        Toast.makeText(this, "Error.", Toast.LENGTH_SHORT).show();
                    }
          }
        );
  }

      private void registerListener(String asset_name) {
        // dialog.showProgresDialog();
        String onDemandAssetPackPath = getAbsoluteAssetPath(asset_name, "");
      

        if (onDemandAssetPackPath == null ) {
            assetPackManager.registerListener(assetPackStateUpdateListener);
            List<String> assetPackList = new ArrayList<>();
            assetPackList.add(asset_name);
            assetPackManager.fetch(assetPackList);
        } else {
            Toast.makeText(this, "Error.", Toast.LENGTH_SHORT).show();
        }
      }

    AssetPackStateUpdateListener assetPackStateUpdateListener = new AssetPackStateUpdateListener() {
        @Override
        public void onStateUpdate(AssetPackState state) {
            switch (state.status()) {
                case AssetPackStatus.PENDING:
                    // Log.i(TAG, "Pending");
                    break;

                case AssetPackStatus.DOWNLOADING:
                    long downloaded = state.bytesDownloaded();
                    long totalSize = state.totalBytesToDownload();
                    double percent = 100.0 * downloaded / totalSize;
                    // Log.i(TAG, "PercentDone=" + String.format("%.2f", percent));
                    break;

                case AssetPackStatus.TRANSFERRING:
                    // 100% downloaded and assets are being transferred.
                    // Notify user to wait until transfer is complete.
                    break;

                case AssetPackStatus.COMPLETED:
                    // Asset pack is ready to use. Start the Game/App.
                    // initClickedAssetPack();
                    break;

                case AssetPackStatus.FAILED:
                    // Request failed. Notify user.
                    // Log.e(TAG, String.valueOf(state.errorCode()));
                    break;

                case AssetPackStatus.CANCELED:
                    // Request canceled. Notify user.
                    break;

                case AssetPackStatus.WAITING_FOR_WIFI:
                    // showWifiConfirmationDialog();
                    break;

                case AssetPackStatus.NOT_INSTALLED:
                    // Asset pack is not downloaded yet.
                    break;
                case AssetPackStatus.UNKNOWN:
                    // The Asset pack state is unknown
                    break;
            }

        }
    };


    private String getAbsoluteAssetPath(String assetPack, String relativeAssetPath) {
        AssetPackLocation assetPackPath = assetPackManager.getPackLocation(assetPack);

        if (assetPackPath == null) {
            // asset pack is not ready
            return null;
        }

        String assetsFolderPath = assetPackPath.assetsPath();
        // equivalent to: FilenameUtils.concat(assetPackPath.path(), "assets");
        String assetPath = FilenameUtils.concat(assetsFolderPath, relativeAssetPath);
        return assetPath;
    }
//   String link = call.argument("link");
//                         String filename = call.argument("filename");
//                         downloadImageNew(filename,link);
//     private void downloadImageNew(String filename, String downloadUrlOfImage){
//         try{
//             DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//             Uri downloadUri = Uri.parse(downloadUrlOfImage);
//             DownloadManager.Request request = new DownloadManager.Request(downloadUri);
//             request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
//                     .setAllowedOverRoaming(false)
//                     .setTitle(filename)
//                     .setDescription("Downloading wallpaper")
//                     .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
//                     .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//                     .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,File.separator + "WallCanic Walls"+ File.separator + filename + ".jpg");
//             dm.enqueue(request);
//             Toast.makeText(this, "Image download started.", Toast.LENGTH_SHORT).show();
//         }catch (Exception e){
//             Toast.makeText(this, "Image download failed." + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//         }
//     }
}
