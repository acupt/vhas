package com.swust.vhas.controller;

import com.swust.vhas.model.Author;
import com.swust.vhas.service.AuthorService;
import com.swust.vhas.view.JsonAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    /**
     * 根据作者ID获取作者详细信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public JsonAndView detail(Integer id) {
        Author author = authorService.selectByPrimaryKey(id);
        JsonAndView jsonAndView = new JsonAndView();
        jsonAndView.addData("author", author);
        return jsonAndView;
    }

    /**
     * 根据网站ID和作者在该网站的ID获取作者的热度变化数据
     *
     * @param webId
     * @param uid
     * @return
     */
    @RequestMapping(value = "/authorup", method = RequestMethod.GET)
    @ResponseBody
    public JsonAndView authorup(Integer webId, String uid) {
        JsonAndView jsonAndView = new JsonAndView();
        jsonAndView.addData("authors", authorService.selectUpdate(webId, uid));
        return jsonAndView;
    }

    /**
     * 获取粉丝量前size的作者列表
     *
     * @param size
     * @return
     */
    @RequestMapping(value = "/top", method = RequestMethod.GET)
    @ResponseBody
    public JsonAndView top(Integer size) {
        if (size == null || size < 1)
            size = 20;
        List<Author> authors = authorService.selectTop(size);
        JsonAndView jsonAndView = new JsonAndView();
        jsonAndView.addData("authors", authors);
        return jsonAndView;
    }
}
