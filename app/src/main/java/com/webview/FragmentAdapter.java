package com.webview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            DownloadDocumentFragment downloadDocumentFragment = new DownloadDocumentFragment();
            return downloadDocumentFragment;
        }
        return new ListFilesFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
