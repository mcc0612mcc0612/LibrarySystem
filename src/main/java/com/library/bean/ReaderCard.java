package com.library.bean;

import java.io.Serializable;

public class ReaderCard implements Serializable {

    private long reader_id;
    private String username;
    private String password;
    private int borrow_time;
    private int reserve_time;

    public long getReaderId() {
        return reader_id;
    }

    public void setReaderId(long reader_id) {
        this.reader_id = reader_id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public int getBorrow_Time() {
        return borrow_time;
    }

    public void setBorrow_time(int borrow_time) {
        this.borrow_time = borrow_time;
    }

    public int getReserve_time() {
        return reserve_time;
    }

    public void setReserve_time(int reserve_time) {
        this.reserve_time = reserve_time;
    }
}
