package com.swust.vhas.service;

import com.swust.vhas.dao.AuthorDao;
import com.swust.vhas.model.Author;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    @Qualifier("authorDao")
    private AuthorDao authorDao;

    public int deleteByPrimaryKey(Integer id) {
        return authorDao.deleteByPrimaryKey(id);
    }

    public int insert(Author record) {
        return authorDao.insert(record);
    }

    public int insertSelective(Author record) {
        return authorDao.insertSelective(record);
    }

    public Author selectByPrimaryKey(Integer id) {
        return authorDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Author record) {
        return authorDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Author record) {
        return authorDao.updateByPrimaryKey(record);
    }

    public List<Author> selectUpdate(Integer webId, String uid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("webId", webId);
        map.put("uid", uid);
        List<Author> list = authorDao.selectUpdate(map);
        logger.info("author update " + webId + " " + uid + " " + list.size());
        return list;
    }

    public List<Author> selectTop(Integer size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", size);
        return authorDao.selectTop(map);
    }

}
