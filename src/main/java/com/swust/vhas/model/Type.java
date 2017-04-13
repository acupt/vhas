package com.swust.vhas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘杰
 * @time 2016年6月30日 下午1:00:29
 */
public class Type {

    public String name;
    public String parent;// 父级类别
    public long click;// 点击量
    public List<Type> childs;// 子类
    public String uptime;// 更新时间

    public Type() {

    }

    public Type(String name) {
        this.name = name;
        click = 0;
        childs = new ArrayList<Type>();
    }

    public Type(String name, String parent, long click) {
        this(name);
        this.parent = parent;
        this.click = click;
    }

    public void addChild(Type type) {
        childs.add(type);
        click += type.click;
    }
}
