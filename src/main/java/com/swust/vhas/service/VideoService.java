package com.swust.vhas.service;

import com.swust.vhas.dao.VideoDao;
import com.swust.vhas.model.Type;
import com.swust.vhas.model.Video;
import com.swust.vhas.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoService {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    @Qualifier("videoDao")
    private VideoDao videoDao;

    private static List<Type> types = new ArrayList<Type>();

    private static long lastTypeUpdate = 0;

    public int deleteByPrimaryKey(Integer id) {
        return videoDao.deleteByPrimaryKey(id);
    }

    public int insert(Video record) {
        return videoDao.insert(record);
    }

    public int insertSelective(Video record) {
        return videoDao.insertSelective(record);
    }

    public Video selectByPrimaryKey(Integer id) {
        return videoDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Video record) {
        return videoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Video record) {
        return videoDao.updateByPrimaryKey(record);
    }

    public List<Video> selectTop(String beginTime, Integer size, String order, String type, Integer web) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("beginTime", beginTime);
        map.put("size", size);
        map.put("order", order);
        if (exitType(type))
            map.put("type", type);
        map.put("webId", web);
        return videoDao.selectTop(map);
    }

    public List<Type> selectAllTypes(Integer webId) {
        if (System.currentTimeMillis() - lastTypeUpdate < 1000 * 60) {
            logger.info("selectAllTypes use cache " + JsonUtil.toJson(types));
            return types;
        }
        lastTypeUpdate = System.currentTimeMillis();
        types = videoDao.selectAllTypes(webId);
        logger.info("selectAllTypes update " + JsonUtil.toJson(types));
        return types;
    }

    public List<Type> selectUpdateByType(String type) {
        return videoDao.selectUpdateByType(type);
    }

    public List<Video> selectUpdate(Integer webId, String vid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("webId", webId);
        map.put("vid", vid);
        return videoDao.selectUpdate(map);
    }

    public List<Video> selectAllByAuthorId(Integer id) {
        return videoDao.selectAllByAuthorId(id);
    }

    private boolean exitType(String type) {
        for (Type type2 : selectAllTypes(null)) {
            if (type2.name.equals(type))
                return true;
        }
        return false;
    }

}
