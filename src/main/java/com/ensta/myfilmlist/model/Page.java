package com.ensta.myfilmlist.model;

import java.util.List;

public class Page<T> {
    private int number;
    private int size;
    private long total;
    private List<T> data;

    public Page(int number, int size, long total, List<T> data) {
        this.number = number;
        this.size = size;
        this.total = total;
        this.data = data;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
}