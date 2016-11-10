package com.leeves.h.GnakM.api;


import com.leeves.h.GnakM.model.CategoryData;
import com.leeves.h.GnakM.model.RequestContent;
import com.leeves.h.GnakM.model.RequestData;
import com.leeves.h.GnakM.model.entity.BaseData;
import com.leeves.h.GnakM.model.entity.GContent;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Function：
 * Created by h on 2016/11/9.
 *
 * @author Leeves
 */

public interface ApiManager {

    /**
     * 获得某一个类的数据
     * @param category 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * @param requestNum 请求个数 数字，大于0
     * @param requestPages 第几页：数字，大于0
     * @return
     */
    @GET("data/{category}/{requestNum}/{requestPages}")
    Observable<RequestData<List<BaseData>>> getCategoryData(
            @Path("category") String category,
            @Path("requestNum") int requestNum,
            @Path("requestPages") int requestPages
    );

    /**
     * 获得year年month月day日的数据
     * @param year 年
     * @param month 月
     * @param day 日
     * @return
     */
    @GET("day/{year}/{month}/{day}")
    Observable<RequestContent<CategoryData>> getDayData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day
    );

    /**
     * 获得num天的数据
     * @param num 请求个数 数字，大于0
     * @param pages 第几页：数字，大于0
     * @return
     */
    @GET("history/content/{num}/{pages}")
    Observable<RequestData<List<GContent>>> getContent(
            @Path("num") int num,
            @Path("pages") int pages
    );


}
