package com.heziz.qixia3.network;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sws on 2019-04-30.
 * from:
 * describe:
 */

public class RequestBean<T> implements Serializable {

    private PageBean page;
    private T list;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
