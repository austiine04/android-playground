package com.webview;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class DownloadCallback implements retrofit2.Callback<okhttp3.ResponseBody> {

    private String fileName;
    private Activity activity;

    public DownloadCallback(Activity activity, String fileName) {
        this.activity = activity;
        this.fileName = Environment.DIRECTORY_DOWNLOADS.concat("/" + fileName);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        //TODO: check out android force download, using AndroidDownloadManager with Intent or Tape and picasso
        Log.d("FILE_NAME", fileName);
        File file = Environment.getExternalStoragePublicDirectory(fileName);

        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        Log.d("Writing file", fileName);
        writeFileToDownloadsFolder(response, file);
    }

    private void writeFileToDownloadsFolder(Response<ResponseBody> response, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] buffer = response.body().bytes();
            fileOutputStream.write(buffer);
            fileOutputStream.flush();
            fileOutputStream.close();
            Log.d("Done writing file", fileName);
        } catch (IOException exception) {
            Log.d("ERROR WRITING FILE", fileName);
            exception.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.d("ERROR", "DOWNLOADING FILE");
    }
}
