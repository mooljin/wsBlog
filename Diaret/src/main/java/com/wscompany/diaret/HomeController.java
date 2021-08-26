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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String doJoin(@RequestParam Map<String, String> paramMap) {

		return "redirect:/";
	}

	//테스트용
	@RequestMapping(value = "/checkId.do")
	@ResponseBody
	public String checkId(@RequestParam Map<String, String> paramMap) {
		logger.info(Thread.currentThread().getStackTrace()[1].getClassName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());

		String isExistId = null;
		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			sqlSession.selectList("join.selectUser", paramMap);
			List<?> userData = sqlSession.selectList("join.selectUser", paramMap);

			if(userData.size() > 0) {
				isExistId = "T";
			} else {
				isExistId = "F";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

		return isExistId;
	}
}
