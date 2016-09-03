package com.webview;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

public class DownloadCompleteBroadcastReceiver extends BroadcastReceiver {

    private DownloadManager downloadManager;

    public DownloadCompleteBroadcastReceiver(DownloadManager downloadManager, long enqueue) {
        this.downloadManager = downloadManager;
        long enqueue1 = enqueue;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {

            long downloadId = intent.getLongExtra(
                    DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            Cursor c = downloadManager.query(query);

            if (c.moveToFirst()) {
                int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                    String uriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    Intent displayIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
                    context.startActivity(displayIntent);
                }
            }
        }
    }
}
