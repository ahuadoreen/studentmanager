package com.example.studentmanager.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ListWithPageData<T> {
    @Schema(example = "1")
    private int pageCount;
    @Schema(example = "8")
    private long total;
    private List<T> list;

    public int getPageCount() {
        return pageCount;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getList() {
        return list;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
