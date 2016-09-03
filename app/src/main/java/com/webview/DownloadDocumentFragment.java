package com.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadDocumentFragment extends Fragment {


    @OnClick(R.id.button)
    public void setDownloadButtonListener(Button button) {
        button.setOnClickListener(new DownloadButtonOnClickListener(getActivity()));
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
