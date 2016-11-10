package com.leeves.h.GnakM.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Function：
 * Created by h on 2016/10/17.
 *
 * @author Leeves
 */

@Entity
public class FengMian {

    @Id
    private String date;
    private String meiZhiImgUrl;
    private String title;

    @Generated(hash = 513055195)
    public FengMian(String date, String meiZhiImgUrl, String title) {
        this.date = date;
        this.meiZhiImgUrl = meiZhiImgUrl;
        this.title = title;
    }

    @Generated(hash = 1185943110)
    public FengMian() {
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeiZhiImgUrl() {
        return this.meiZhiImgUrl;
    }

    public void setMeiZhiImgUrl(String meiZhiImgUrl) {
        this.meiZhiImgUrl = meiZhiImgUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
