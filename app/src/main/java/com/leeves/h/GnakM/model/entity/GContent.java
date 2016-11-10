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
public class GContent {

    @Id
    private String _id;
    private String content;
    private String publishedAt;
    private String title;

    @Generated(hash = 330826737)
    public GContent(String _id, String content, String publishedAt, String title) {
        this._id = _id;
        this.content = content;
        this.publishedAt = publishedAt;
        this.title = title;
    }

    @Generated(hash = 2114052380)
    public GContent() {
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedAt() {
        return this.publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
