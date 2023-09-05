package com.library.service;

import com.library.bean.Reserve;
import com.library.dao.ReserveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReserveService {

    @Autowired
    public ReserveDao reserveDao;
    public boolean reserveBookOne(long bookId,long readerId){
        return reserveDao.reserveBookOne(bookId,readerId)>0;
    }
    public boolean reserveBookTwo(long bookId) {
        return reserveDao.reserveBookTwo(bookId)>0;
    }
    public boolean reserveBook(long bookId,long readerId) {
        return reserveDao.reserveBookOne(bookId, readerId)>0 && reserveDao.reserveBookTwo(bookId)>0;
    }
    public boolean takeBook(long bookId,long readerId){

        return reserveDao.takeBookOne(bookId,readerId)>0 && reserveDao.takeBookTwo(readerId)>0;
    }
    public ArrayList<Reserve> myReserveList(final long reader_id){
        return reserveDao.reader_reserveList(reader_id);
    }
    public ArrayList<Reserve> bookReserveList(final long book_id){
        return reserveDao.book_reserveList(book_id);
    }
}
