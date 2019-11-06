package com.huohuo.mvp.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 金融中的各个模块
 * Created by kennysun on 2019/8/30.
 */

public class ModuleBean {
    private String title;
    public List<ModuleItemBean> moduleItems;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ModuleItemBean> getModuleItems() {
        List<ModuleItemBean> list = new ArrayList<>();
        list.add(new ModuleItemBean());
        list.add(new ModuleItemBean());
        list.add(new ModuleItemBean());
        list.add(new ModuleItemBean());
        list.add(new ModuleItemBean());
        list.add(new ModuleItemBean());
        this.moduleItems = list;
        return moduleItems;
    }

    public void setModuleItems(List<ModuleItemBean> moduleItems) {
        this.moduleItems = moduleItems;
    }
}
