package com.wscompany.diaret;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/")
	public String goMain() {
		return "main";
	}

	@RequestMapping(value = "/goJoin.do")
	public String goJoin() {
		return "join";
	}

	@RequestMapping(value = "/doJoin.do")
	public String doJoin(@RequestParam Map<String, String> para) {
		return "redirect:/";
	}
}
