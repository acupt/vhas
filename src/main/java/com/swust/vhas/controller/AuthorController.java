package com.swust.vhas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swust.vhas.model.Author;
import com.swust.vhas.service.AuthorService;
import com.swust.vhas.view.JsonAndView;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public JsonAndView detail(Integer id) {
		Author author = authorService.selectByPrimaryKey(id);
		JsonAndView jsonAndView = new JsonAndView();
		jsonAndView.addData("author", author);
		return jsonAndView;
	}

	@RequestMapping(value = "/authorup", method = RequestMethod.GET)
	@ResponseBody
	public JsonAndView authorup(Integer webId, String uid) {
		JsonAndView jsonAndView = new JsonAndView();
		jsonAndView.addData("authors", authorService.selectUpdate(webId, uid));
		return jsonAndView;
	}

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
