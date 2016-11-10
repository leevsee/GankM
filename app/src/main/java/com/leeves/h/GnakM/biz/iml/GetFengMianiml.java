package com.leeves.h.GnakM.biz.iml;


import android.util.Log;

import com.leeves.h.GnakM.biz.GetFengMian;
import com.leeves.h.GnakM.biz.OnRequestListener;
import com.leeves.h.GnakM.model.entity.FengMian;
import com.leeves.h.GnakM.service.HttpHelper;

import java.util.List;

import rx.Subscriber;

/**
 * Function：
 * Created by h on 2016/11/9.
 *
 * @author Leeves
 */

public class GetFengMianiml implements GetFengMian {
    private static final String TAG = GetFengMianiml.class.getSimpleName();
    private List<FengMian> mFengMian;
    private Subscriber mSubscriber;

    @Override
    public void loadFengMian(int num, int page, final OnRequestListener listener) {

        mSubscriber = new Subscriber<List<FengMian>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: "+e.getMessage());
                listener.onFaild();
            }

            @Override
            public void onNext(List<FengMian> list) {
                Log.i(TAG, "onNext: ");
                listener.onSuccess(list);
            }
        };
        HttpHelper.getInstance().goToContent(mSubscriber, num, page);
    }
}
