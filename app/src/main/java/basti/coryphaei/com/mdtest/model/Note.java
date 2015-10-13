package basti.coryphaei.com.mdtest.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bowen on 2015/10/11.
 */
public class Note implements Serializable{

    private long id;
    private String title;
    private String content;
    private String backgroundColor;
    private boolean isLike;
    private Date createDate;
    private Date notifyDate;
    private String Tag;
    private int type;//内容类型，1为随手记，2为备忘录

    public Note() {
    }


    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setNotifyDate(Date notifyDate) {
        this.notifyDate = notifyDate;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isLike() {
        return isLike;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getNotifyDate() {
        return notifyDate;
    }

    public String getTag() {
        return Tag;
    }

}

