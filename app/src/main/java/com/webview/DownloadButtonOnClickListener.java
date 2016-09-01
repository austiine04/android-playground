package com.webview;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import retrofit2.Retrofit;

public class DownloadButtonOnClickListener implements View.OnClickListener {

    private final HttpService service;
    private Activity activity;

    public DownloadButtonOnClickListener(Activity activity) {
        this.activity = activity;
        service = new Retrofit.Builder()
                .baseUrl("http://www.pdf995.com/")
                .build().create(HttpService.class);
    }

    @Override
    public void onClick(View v) {
        LinearLayout linearLayout = (LinearLayout) v.getParent();
        String url = ((EditText) linearLayout.findViewById(R.id.url)).getText().toString();
        Log.d("DOCUMENT URL", url);
        service.downloadPdf(url).enqueue(new DownloadCallback(activity, "filename.pdf"));
    }
}
