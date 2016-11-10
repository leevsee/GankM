package com.leeves.h.GnakM.biz.iml;

import android.util.Log;

import com.leeves.h.GnakM.biz.GetOneDay;
import com.leeves.h.GnakM.biz.OnRequestListener;
import com.leeves.h.GnakM.model.entity.BaseData;
import com.leeves.h.GnakM.service.HttpHelper;

import java.util.List;

import rx.Subscriber;

/**
 * Function：
 * Created by h on 2016/11/10.
 *
 * @author
 */

public class GetOneDayiml implements GetOneDay {
    private static final String TAG = GetOneDayiml.class.getSimpleName();

    private Subscriber mSubscriber;
    @Override
    public void loadOneDay(int year, int month, int date, final OnRequestListener listener) {
            mSubscriber = new Subscriber<List<BaseData>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    listener.onFaild();
                    Log.i(TAG, "onError: ====="+e.getMessage());
                }

                @Override
                public void onNext(List<BaseData> baseDatas) {
                    listener.onSuccess(baseDatas);
                }


            };
        HttpHelper.getInstance().goToDayData(mSubscriber,year,month,date);
    }
}
