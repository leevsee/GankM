package com.leeves.h.GnakM.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Function：
 * Created by h on 2016/10/17.
 *
 * @author Leeves
 */
@Entity
public class BaseData {

    /**
     * _id : 5802dc5e421aa90e714dc97e
     * createdAt : 2016-10-16T09:48:14.52Z
     * desc : 推荐一个 TV 类的 Android App，仿泰捷视频。
     * images : ["http://img.gank.io/3b76e341-7940-485f-a342-02a949365b4a"]
     * publishedAt : 2016-10-17T11:32:00.914Z
     * source : chrome
     * type : Android
     * url : https://github.com/hejunlin2013/TVSample
     * used : true
     * who : 代码家
     */
    @Id
    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    @Transient
    private List<String> images;

    @Generated(hash = 860578296)
    public BaseData(String _id, String createdAt, String desc, String publishedAt,
                    String source, String type, String url, boolean used, String who) {
        this._id = _id;
        this.createdAt = createdAt;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.source = source;
        this.type = type;
        this.url = url;
        this.used = used;
        this.who = who;
    }

    @Generated(hash = 2098951325)
    public BaseData() {
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return this.publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getUsed() {
        return this.used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return this.who;
    }

    public void setWho(String who) {
        this.who = who;
    }

}
