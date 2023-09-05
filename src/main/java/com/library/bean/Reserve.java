package com.library.bean;

import java.io.Serializable;
import java.util.Date;

public class Reserve implements Serializable {
    private long Reserve_id;
    private long book_id;
    private long reader_id;
    private Date Reserve_time;
    private Date Get_time;
    public long getReaderId() {
        return reader_id;
    }

    public void setReaderId(long reader_id) {
        this.reader_id = reader_id;
    }

    public long getBookId() {
        return book_id;
    }

    public void setBookId(long book_id) {
        this.book_id = book_id;
    }

    public Date getReserve_time() {
        return Reserve_time;
    }

    public void setReserve_time(Date reserve_time) {
        this.Reserve_time = Reserve_time;
    }
    public Date getGet_time() {
        return Get_time;
    }

    public void setGet_time(Date Get_time) {
        this.Get_time = Get_time;
    }

    public long getReserve_id() {
        return Reserve_id;
    }
}
