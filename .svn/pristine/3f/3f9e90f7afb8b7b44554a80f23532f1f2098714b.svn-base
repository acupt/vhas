package com.swust.vhas.dao;

import java.util.List;
import java.util.Map;

import com.swust.vhas.model.Type;
import com.swust.vhas.model.Video;

public interface VideoDao {

	int deleteByPrimaryKey(Integer id);

	int insert(Video record);

	int insertSelective(Video record);

	Video selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Video record);

	int updateByPrimaryKey(Video record);

	Video exist(Map<String, Object> map);

	List<Video> selectTop(Map<String, Object> map);

	List<Type> selectAllTypes(Integer webId);

	List<Type> selectUpdateByType(String type);

	List<Video> selectUpdate(Map<String, Object> map);

	List<Video> selectAllByAuthorId(Integer id);

}