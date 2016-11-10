package com.leeves.h.GnakM.presenter;


import android.util.Log;

import com.leeves.h.GnakM.biz.GetFengMian;
import com.leeves.h.GnakM.biz.OnRequestListener;
import com.leeves.h.GnakM.biz.iml.GetFengMianiml;
import com.leeves.h.GnakM.ui.view.FenMianView;

import java.util.List;

/**
 * Function：
 * Created by h on 2016/11/9.
 *
 * @author Leeves
 */

public class FengMianPresenter {
    private static final String TAG = FengMianPresenter.class.getSimpleName();
    private FenMianView mFenMianView;
    private GetFengMian mGetFengMian;
    private int nowPages = 1;
    private int pagesFlag;

    public FengMianPresenter(FenMianView fenMianView) {
        mFenMianView = fenMianView;
        mGetFengMian = new GetFengMianiml();
    }

    public void loadData() {
        mGetFengMian.loadFengMian(8, nowPages, new OnRequestListener() {
            @Override
            public void onSuccess(List list) {
                Log.i(TAG, "onSuccess: 1=====" + nowPages);
                mFenMianView.showFenMianData(nowPages, list);
                nowPages += 1;
                Log.i(TAG, "onSuccess: 2=====" + nowPages);
            }

            @Override
            public void onFaild() {
                Log.i(TAG, "onFaild: 1=====" + nowPages);
                mFenMianView.showFenMianData(1, null);
                Log.i(TAG, "onFaild: 2=====" + nowPages);
            }
        });

    }

    public void onDestroy() {
        mFenMianView = null;
    }
}
