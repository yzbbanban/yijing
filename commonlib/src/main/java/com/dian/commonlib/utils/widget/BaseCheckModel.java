package com.dian.commonlib.utils.widget;

import java.io.Serializable;

/**
 * Created by dian on 2019/3/4.
 */

public class BaseCheckModel implements Serializable{
    protected boolean check;
    protected String title;

    public BaseCheckModel(boolean check, String title) {
        this.check = check;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
