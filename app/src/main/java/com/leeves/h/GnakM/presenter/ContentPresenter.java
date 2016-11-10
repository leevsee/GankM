package com.leeves.h.GnakM.presenter;

import com.leeves.h.GnakM.biz.GetOneDay;
import com.leeves.h.GnakM.biz.OnRequestListener;
import com.leeves.h.GnakM.biz.iml.GetOneDayiml;
import com.leeves.h.GnakM.ui.view.ContentView;
import com.leeves.h.GnakM.utils.MyDateUtils;

import java.util.List;

/**
 * Function：
 * Created by h on 2016/11/10.
 *
 * @author
 */

public class ContentPresenter {
    private static final String TAG = ContentPresenter.class.getSimpleName();
    private ContentView mContentView;
    private GetOneDay mGetOneDay;
    private String data;
    private String url;


    public ContentPresenter(ContentView contentView,String data,String url) {
        mContentView = contentView;
        mGetOneDay = new GetOneDayiml();
        this.data = data;
        this.url = url;
    }

    public void loadContentData() {
        mGetOneDay.loadOneDay(MyDateUtils.toGetYear(data), MyDateUtils.toGetMonth(data), MyDateUtils.toGetDay(data), new OnRequestListener() {
            @Override
            public void onSuccess(List list) {
                mContentView.showContentData(list);
            }

            @Override
            public void onFaild() {
                mContentView.showContentData(null);
            }
        });

    }
}
