package com.huohuo.mvp.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ModuleBean {
    private String title;
    private Integer type;
    public List<ModuleItemBean> moduleItems;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ModuleItemBean> getModuleItems() {
        return moduleItems;
    }

    public void setModuleItems(List<ModuleItemBean> moduleItems) {
        this.moduleItems = moduleItems;
    }

    @Override
    public String toString() {
        return "ModuleBean{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", moduleItems=" + moduleItems +
                '}';
    }
}
