package com.swust.vhas.controller;

import com.swust.vhas.model.Video;
import com.swust.vhas.service.VideoService;
import com.swust.vhas.view.JsonAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    /**
     * 获取排名前size的视频列表
     *
     * @param time  查询记录的开始时间
     * @param size  只取前面size条记录
     * @param order 根据什么排序（点击、收藏等）
     * @param type  视频类型
     * @param web   视频网站
     * @return
     */
    @RequestMapping(value = "/top", method = RequestMethod.GET)
    @ResponseBody
    public JsonAndView top(String time, Integer size, String order, String type, Integer web) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        //根据前台传过来的字段选择查询的开始时间
        if ("day".equals(time)) {
            calendar.add(Calendar.DATE, -1);//从一天前开始查询
        } else if ("week".equals(time)) {
            calendar.add(Calendar.WEEK_OF_YEAR, -1);//一个周前
        } else if ("month".equals(time)) {
            calendar.add(Calendar.MONTH, -1);//一个月前
        } else if ("year".equals(time)) {
            calendar.add(Calendar.YEAR, -1);//一年
        } else {
            calendar = null;
        }
        if (calendar != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time = sdf.format(calendar.getTime());
        }
        //
        if (size == null || size < 1)
            size = 10;
        if (order == null)
            order = "click";
        List<Video> videos = videoService.selectTop(time, size, order, type, web);
        JsonAndView jsonAndView = new JsonAndView();
        jsonAndView.addData("size", size);
        jsonAndView.addData("order", order);
        jsonAndView.addData("type", type);
        jsonAndView.addData("web", web);
        jsonAndView.addData("videos", videos);
        return jsonAndView;
    }

    /**
     * 获取视频种类列表
     *
     * @param webId 网站，不同网站类型不同
     * @return
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    @ResponseBody
    public JsonAndView type(Integer webId) {
        JsonAndView jsonAndView = new JsonAndView();
        jsonAndView.addData("types", videoService.selectAllTypes(webId));
        return jsonAndView;
    }

    /**
     * 获取某个视频种类的热度变化
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/typeup", method = RequestMethod.GET)
    @ResponseBody
    public JsonAndView typeup(String type) {
        JsonAndView jsonAndView = new JsonAndView();
        jsonAndView.addData("type", type);
        jsonAndView.addData("typeup", videoService.selectUpdateByType(type));
        return jsonAndView;
    }

    /**
     * 获取某个视频的热度变化
     *
     * @param webId
     * @param vid
     * @return
     */
    @RequestMapping(value = "/videoup", method = RequestMethod.GET)
    @ResponseBody
    public JsonAndView videoup(Integer webId, String vid) {
        JsonAndView jsonAndView = new JsonAndView();
        jsonAndView.addData("videos", videoService.selectUpdate(webId, vid));
        return jsonAndView;
    }

    /**
     * 获取某个视频的详细信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public JsonAndView detail(Integer id) {
        if (id == null || id <= 0)
            return new JsonAndView(-1, "缺少参数");
        JsonAndView jsonAndView = new JsonAndView();
        jsonAndView.addData("video", videoService.selectByPrimaryKey(id));
        return jsonAndView;
    }

    /**
     * @param id 根据作者id查找所有视频
     * @return
     * @author LiuJie
     * @time 2016年6月30日 下午10:29:06
     */
    @RequestMapping(value = "/works", method = RequestMethod.GET)
    @ResponseBody
    public JsonAndView works(Integer id) {
        if (id == null || id <= 0)
            return new JsonAndView(-1, "缺少参数");
        JsonAndView jsonAndView = new JsonAndView();
        jsonAndView.addData("videos", videoService.selectAllByAuthorId(id));
        return jsonAndView;
    }
}
