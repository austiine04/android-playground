package com.webview;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.DOWNLOAD_SERVICE;

public class DownloadDocumentFragment extends Fragment {

    @BindView(R.id.url) TextView textView;

    private DownloadManager downloadManager;

    @OnClick(R.id.button)
    public void setDownloadButtonListener() {
        downloadManager = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(textView.getText().toString()));
        downloadManager.enqueue(request);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.download_document_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
