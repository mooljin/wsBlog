package com.wscompany.diaret;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;

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

	//테스트용
	@RequestMapping(value = "/test.do")
	public HashMap<String, Object> test(String id, ModelMap model) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		SqlSessionFactory dao = null;
		List resultList = null;
		SqlSession sqlSession = null;

		try {
			dao = sqlSessionFactory.getObject();
			sqlSession = dao.openSession();

			System.out.println(sqlSession.selectOne("join.selectUser", id));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

		return resultMap;
	}
}
