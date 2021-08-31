package com.wscompany.diaret;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.Position.Bias;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
					//세션에 현재 로그인한 유저 정보를 등록
					session.setAttribute("userDataMap", userDataMap);
					model.addAttribute("includePage", "post");
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

	@RequestMapping(value = "/applyImg.do")
	public String applyImg(@RequestParam Map<String, Object> paramMap, HttpServletRequest request) {
		Set<?> pathSet = request.getSession().getServletContext().getResourcePaths("/");

		System.out.println(pathSet);

		//이미지 디코딩
		Decoder decoder =  Base64.getDecoder();
		byte[] binaryImg = decoder.decode((String) paramMap.get("encodedStr"));

		//프로필 이미지 저장할 경로 지정. (프로젝트 내부로 경로를 잡고 싶은데, 잘 안 됨.)
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		int userNum = (Integer) ((Map<String, Object>) session.getAttribute("userDataMap")).get("USER_NUM");
//		String dir = application.getRealPath("resources/userData") + "/" + userNum;
		String dir = contextPath + "/resources/userData/" + userNum;
		System.out.println(dir);
		String file = "temp" + "." + ((String) paramMap.get("exp"));
		System.out.println(file);

		//프로필 이미지 저장
		writeToFile(dir, file, binaryImg);

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;
		Map<String, Object> userDataMap = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			Map<String, Object> imgSrc = new HashMap<String, Object>();
			imgSrc.put("userNum", userNum);
			imgSrc.put("userImg", dir + "/" + file);

			//이미지 저장 주소 위치를 DB에 저장
			sqlSession.update("modify.updateImg", imgSrc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		//세션 정보 갱신
		String userId = (String) ((Map<String, Object>) session.getAttribute("userDataMap")).get("USER_ID");
		userDataMap = sqlSession.selectOne("modify.selectAfterUpdate", userId);

		request.getSession().setAttribute("userDataMap", userDataMap);
		request.setAttribute("includePage", "noPost");

		return "diary";
	}

	@RequestMapping(value = "/doModify.do")
	public String doModify(@RequestParam Map<String, Object> paramMap, HttpSession session, HttpServletRequest request) {
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

	//인코딩 된 이미지 파일을 디코딩하여 저장
	public void writeToFile(String dir, String file, byte[] pData) {
	    if(pData != null){
	    	if( !(new File(dir).isDirectory()) ) {
	    		new File(dir).mkdirs();
	    	}

	    	FileOutputStream lFileOutputStream = null;

	    	try{
	    		File lOutFile = new File(dir, file);
	    		lFileOutputStream = new FileOutputStream(lOutFile);
	    		lFileOutputStream.write(pData);

	    		//여따 저장해도 되는건가..?....
	    		System.out.println(lOutFile.getAbsolutePath() + "에 저장 완료");
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	} finally {
	    		try {
	    			lFileOutputStream.close();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    }
	}

	//이미지 파일 불러오기
	public File readFromFile(String dir, String file) {
	   		File lInFile = new File(dir, file);
	   		if(lInFile.exists()) {
	   			System.out.println(lInFile.getAbsolutePath() + "를 불러옴.");
	   		}
	   		return lInFile;
	}

	//goWrite.do
	@RequestMapping(value = "/goWrite.do")
	public String goWrite(@RequestParam Map<String, Object> paramMap, Model model) {
		System.out.println(paramMap);

		model.addAttribute("includePage", "writePost");
		return "diary";
	}
}
