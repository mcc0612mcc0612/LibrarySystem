package com.library.dao;

import com.library.bean.Reserve;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class ReserveDao {
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;
    private final static String NAMESPACE = "com.library.dao.ReserveDao.";
    public int reserveBookOne(final long book_id, final long reader_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("book_id", book_id);
        map.put("reader_id", reader_id);
        return sqlSessionTemplate.insert(NAMESPACE + "reserveBookOne", map);
    }

    public int reserveBookTwo(final long book_id) {
        return sqlSessionTemplate.update(NAMESPACE + "reserveBookTwo", book_id);
    }
    public int takeBookOne(final long book_id,final long reader_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("book_id", book_id);
        map.put("reader_id", reader_id);
        return sqlSessionTemplate.update(NAMESPACE + "takeBookOne", map);
    }
    public int takeBookTwo(final long reader_id) {
        return sqlSessionTemplate.update(NAMESPACE + "takeBookTwo", reader_id);
    }
    public ArrayList<Reserve> reader_reserveList(final long reader_id) {
        List<Reserve> result = sqlSessionTemplate.selectList(NAMESPACE + "reader_reserveList",reader_id);
        if(result == null) {
            return new ArrayList<Reserve>();
        }
        return (ArrayList<Reserve>) result;
    }
    public ArrayList<Reserve> book_reserveList(final long book_id) {
        List<Reserve> result = sqlSessionTemplate.selectList(NAMESPACE + "book_reserveList",book_id);
        if(result == null) {
            return new ArrayList<Reserve>();
        }
        return (ArrayList<Reserve>) result;
    }

    public ArrayList<Reserve> readerBook_reserveList(final long reader_id, final long book_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("book_id", book_id);
        map.put("reader_id", reader_id);
        List<Reserve> result = sqlSessionTemplate.selectList(NAMESPACE + "readerBook_reserveList",map);
        if(result == null) {
            return new ArrayList<Reserve>();
        }
        return (ArrayList<Reserve>) result;
    }
}
