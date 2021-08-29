package com.wscompany.diaret;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
				sqlSession.insert("join.insert", paramMap);
				isExistId = "F";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

		return isExistId;
	}

	@RequestMapping(value = "/doLogin.do")
	public String doLogin(@RequestParam Map<String, String> paramMap, Model model, HttpSession session) {
		String nextPage = null;

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			Map<String, Object> userDataMap = sqlSession.selectOne("main.selectUser", paramMap);

			if(userDataMap != null) {
				if(userDataMap.get("USER_PW").equals(paramMap.get("userPw"))) {
					session.setAttribute("userDataMap", userDataMap);
					model.addAttribute("includePage", "noPost");
					model.addAttribute("login", "success");
					nextPage = "diary";
				} else {
					//로그인 실패 시 알람을 띄우기 위한 변수
					model.addAttribute("login", "failed");
					nextPage = "main";
				}
			} else {
				model.addAttribute("login", "failed");
				nextPage = "main";
			}

			//(String) userDataMap.get(key)

//			if(userDataMap.size() > 0) {
//				//사용자에게 입력받고 인코딩한 비밀번호와
//				//원래 DB에 인코딩 되어있던 계정 비밀번호와 일치한지 확인
//				if(userDataMap.get("USER_PW").equals(paramMap.get("userPw"))) {
//					//가져오는 변수 순서 : USER_TEL, USER_NICK, USER_NUM, USER_EMAIL, USER_ID, USER_NAME, USER_PW
//					//3번째인 USER_NUM만 int로 저장하며 나머지는 String type
//					UserData userData = new UserData();
//					Set<String> keys = userDataMap.keySet();
//					Iterator<String> itr = keys.iterator();
//
//					//EL으로 값을 가져오려면 getter가 필요함.
//					String key = itr.next();
//					userData.setUserTel((String) userDataMap.get(key));
//					key = itr.next();
//					userData.setUserNick((String) userDataMap.get(key));
//					key = itr.next();
//					userData.setUserNum(((Integer) userDataMap.get(key)));
//					key = itr.next();
//					userData.setUserEmail((String) userDataMap.get(key));
//					key = itr.next();
//					userData.setUserId((String) userDataMap.get(key));
//					key = itr.next();
//					userData.setUserName((String) userDataMap.get(key));
//					//pw는 확인 용도일뿐, 굳이 가져올 필요 없음.
//
//
//					model.addAttribute("userData", userData);
//					nextPage = "diary";
//					//to_do_list : 게시글 정보 불러오기, 게시글 페이지 만들고 이동시키기
//				} else {
//					System.out.println("비번 불일치");
//					nextPage = "main";
//				}
//
//			} else {
//				//아이디가 없으면 main으로 이동
//				nextPage = "main";
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return nextPage;
	}

	@RequestMapping(value = "/goModify.do")
	public String goModify(Model model) {
		model.addAttribute("includePage", "modify");

		return "diary";
	}

	@RequestMapping(value = "/cancelModify.do")
	public String cancelModify(Model model) {
		model.addAttribute("includePage", "noPost");

		return "diary";
	}

	//"initializeImg.do"

	//"applyImg.do"

	@RequestMapping(value = "/doModify.do")
	public String doModify(@RequestParam Map<String, Object> paramMap, HttpSession session, HttpServletRequest request) {
		System.out.println(paramMap);
		String userPw = (String) paramMap.get("userPw");

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;
		Map<String, Object> userDataMap = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			if(userPw == null || userPw.equals("")) {
				sqlSession.update("modify.updateUserWithoutPw", paramMap);
			} else {
				sqlSession.update("modify.updateUser", paramMap);
			}
			//세션 정보 갱신
			userDataMap = sqlSession.selectOne("modify.selectAfterUpdate", paramMap.get("userId"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		session.setAttribute("userDataMap", userDataMap);
		request.setAttribute("includePage", "noPost");

		return "diary";
	}

	@RequestMapping(value = "/doWithdraw.do")
	public String doWithdraw(HttpServletRequest request, HttpSession session) {
		Map<String, Object> userDataMap = (Map<String, Object>) session.getAttribute("userDataMap");
		String userId = (String) userDataMap.get("USER_ID");

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			sqlSession.delete("modify.deleteUser", userId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

		request.getSession().invalidate();
		request.getSession(true);

		return "main";
	}

	@RequestMapping(value = "/doLogout.do")
	public String doLogout(HttpServletRequest request) {
		request.getSession().invalidate();
		request.getSession(true);

		return "main";
	}
}
