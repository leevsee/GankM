package com.leeves.h.GnakM.service;


import com.leeves.h.GnakM.api.ApiManager;
import com.leeves.h.GnakM.model.CategoryData;
import com.leeves.h.GnakM.model.RequestContent;
import com.leeves.h.GnakM.model.RequestData;
import com.leeves.h.GnakM.model.entity.BaseData;
import com.leeves.h.GnakM.model.entity.FengMian;
import com.leeves.h.GnakM.model.entity.GContent;
import com.leeves.h.GnakM.utils.MyDateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Function：
 * Created by h on 2016/11/9.
 *
 * @author Leeves
 */

public class HttpHelper {

    public static final String BASE_URL = "http://gank.io/api/";
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private ApiManager mApiManager;

    private HttpHelper() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mApiManager = mRetrofit.create(ApiManager.class);
    }

    //在访问HttpMethods时创建单例
    private static final HttpHelper INSTANCE = new HttpHelper();

    public static HttpHelper getInstance() {
        return INSTANCE;
    }


    /**
     * 获得category的json数据
     *
     * @param subscriber
     * @param requestNum
     * @param requestPages
     */
    public void goToCategoryData(Subscriber<List<BaseData>> subscriber, String category, int requestNum, int requestPages) {
        mApiManager.getCategoryData(category, requestNum, requestPages)
                .map(new HttpResultCategoryDataFun())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获得某一天所有的json数据
     *
     * @param subscriber
     * @param year
     * @param month
     * @param day
     */
    public void goToDayData(Subscriber<List<BaseData>> subscriber, int year, int month, int day) {
        mApiManager.getDayData(year, month, day)
                .map(new HttpResultDayDateFun())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

//    /**
//     * 获得有数据的日期
//     *
//     * @param subscriber
//     */
//    public void goToDayNum(Subscriber<List<HasDataDay>> subscriber) {
//        mGanApi.getDayNum()
//                .map(new HttpReusltHasDataDayFun())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

    /**
     * 获得首页的json数据
     *
     * @param subscriber
     * @param num
     * @param pages
     */
    public void goToContent(Subscriber<List<FengMian>> subscriber, int num, int pages) {
        mApiManager.getContent(num, pages)
                .map(new HttpReusltToAllContentListFun())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

//    /**
//     * 变换，判断返回值是否fase
//     *
//     * @param <T>
//     */
//    private class HttpResultFun<T> implements Func1<HttpResultData<T>, T> {
//        @Override
//        public T call(HttpResultData<T> tHttpResult) {
//            if (tHttpResult.isError()) {
//                throw new ApiException(100);
//            }
//            return tHttpResult.getResults();
//        }
//    }


    private class HttpResultCategoryDataFun implements Func1<RequestData<List<BaseData>>, List<BaseData>> {

        @Override
        public List<BaseData> call(RequestData<List<BaseData>> listHttpResultData) {
            return listHttpResultData.getResults();
        }
    }

    private class HttpResultDayDateFun implements Func1<RequestContent<CategoryData>, List<BaseData>> {

        @Override
        public List<BaseData> call(RequestContent<CategoryData> dateHttpResultJson) {
            CategoryData date = dateHttpResultJson.getResults();
            date.androidList.addAll(date.iOSList);
            date.androidList.addAll(date.videoList);
            if (date.frontEndList != null) {
                date.androidList.addAll(date.frontEndList);
            }
            if (date.expandList != null) {
                date.androidList.addAll(date.expandList);
            }
            return date.androidList;
        }
    }

//    /**
//     *
//     */
//    private class HttpReusltHasDataDayFun implements Func1<HttpResult<List<String>>, List<HasDataDay>> {
//        @Override
//        public List<HasDataDay> call(HttpResult<List<String>> listHttpResult) {
//            listHttpResult.getResults();
//            MyDateUtils dateUtils = new MyDateUtils();
//            List<String> list = listHttpResult.getResults();
//            List<HasDataDay> datelist = new ArrayList<>();
//            for (int i = 0; i < list.size(); i++) {
//                dateUtils.formatDate(list.get(i));
//                datelist.add(new HasDataDay(dateUtils.getYear(), dateUtils.getMonth(), dateUtils.getDay()));
//            }
//            return datelist;
//        }
//    }

    private class HttpReusltToAllContentListFun implements Func1<RequestData<List<GContent>>, List<FengMian>> {
        @Override
        public List<FengMian> call(RequestData<List<GContent>> listHttpResult) {
            List<GContent> list = listHttpResult.getResults();
            List<FengMian> mFengMian = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                mFengMian.add(new FengMian(list.get(i).getPublishedAt().substring(0, 10)
                        , MyDateUtils.meiZhiImgUrl(list.get(i).getContent()), list.get(i).getTitle()));
            }
            return mFengMian;
        }
    }
}
