package com.wscompany.diaret;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

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

			//세션에 설정할 유저 데이터
			Map<String, Object> userDataMap = sqlSession.selectOne("main.selectUser", paramMap);

			if(userDataMap != null) {
				if(userDataMap.get("USER_PW").equals(paramMap.get("userPw"))) {
					//로그인 성공 시

					updatePostAndCategoryInSession(sqlSession, userDataMap, session);

					//세션에 현재 로그인한 유저 정보를 등록
					session.setAttribute("userDataMap", userDataMap);

					//key : postCategory
					//value : 카테고리에 소속된 post : postCategory, postNum, postTitle
					HashMap<String, HashMap<Integer, String>> classifiedPostData = (HashMap<String, HashMap<Integer, String>>) session.getAttribute("classifiedPostData");
					Collection<HashMap<Integer, String>> posts = classifiedPostData.values();

					if(posts == null || posts.size() == 0) {
						//보유 중인 게시글이 없으면 새 글 작성 안내 페이지로 이동
						model.addAttribute("includePage", "noPost");
					} else {
						//보유 중인 게시글이 있으면 가장 최신 게시글을 보여줌.
						Map<String, Object> latestPost = sqlSession.selectOne("main.loadLatestPost", userDataMap.get("USER_NUM"));
						model.addAttribute("focusedPost", latestPost);
						model.addAttribute("includePage", "post");
					}
					model.addAttribute("login", "success");
					nextPage = "diary";
				} else {
					//비밀번호 불일치로 로그인 실패 시 알람을 띄우기 위한 변수
					model.addAttribute("login", "failed");
					nextPage = "main";
				}
			} else {
				//존재하지 않는 아이디로 로그인 실패 시 알람을 띄우기 위한 변수
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

	@RequestMapping(value = "/initializeImg.do")
	public String initializeImg(Model model, HttpSession session) {

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			Map<String, Object> userDataMap = (Map<String, Object>) session.getAttribute("userDataMap");

			sqlSession.update("modify.initializeImg", (Integer) userDataMap.get("USER_NUM"));

			//세션 정보 갱신
			String userId = (String) userDataMap.get("USER_ID");
			userDataMap = sqlSession.selectOne("modify.selectAfterUpdate", userId);

			session.setAttribute("userDataMap", userDataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("includePage", "noPost");

		return "diary";
	}

	@RequestMapping(value = "/applyImg.do")
	public String applyImg(@RequestParam Map<String, Object> paramMap, HttpServletRequest request) {
		System.out.println(paramMap.get("userImgExp"));
		System.out.println(paramMap.get("encodedStr"));
		//이미지 디코딩
		Decoder decoder =  Base64.getDecoder();
		byte[] binaryImg = decoder.decode((String) paramMap.get("encodedStr"));


		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;
		Map<String, Object> userDataMap = null;

		try {
			int userNum = (Integer) ((Map<String, Object>) request.getSession().getAttribute("userDataMap")).get("USER_NUM");
			String exp = (String) paramMap.get("userImgExp");
			//프로필 이미지 저장할 경로 지정. (프로젝트 내부로 경로를 잡고 싶은데, 잘 안 됨.)
			// "/" : webapp을 가리킴.
			// getResourcePaths("/") : webapp/* 밑에 있는 파일들을 반환.
			// getResourcePaths("/resources/") : webapp/resources/* 에 있는 파일들을 반환.
			//다만 이 url은 github에 있는 프로젝트 폴더에 저장되는 것이 아닌
			//이클립스 workspace에 있는 프로젝트 폴더에 저장됨.
			URL url= request.getSession().getServletContext().getResource("/resources/userData");

			//원하는 위치 : D:/git/wsBlog/Diaret/src/main/webapp/resources/userData/4/profile.png (프로젝트 폴더를 옮겨도 지장없게끔)
//			String path = url.getPath();
			String path = "D:/git/wsBlog/Diaret/src/main/webapp/resources/userData";

//			String dir = application.getRealPath("resources/userData") + "/" + userNum;

			//프로필 이미지 저장
			writeToFile(path, binaryImg, userNum, exp);

			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			paramMap.put("userNum", userNum);

			//이미지 확장자를 DB에 저장
			sqlSession.update("modify.updateImg", paramMap);

			String userId = (String) ((Map<String, Object>) request.getSession().getAttribute("userDataMap")).get("USER_ID");
			userDataMap = sqlSession.selectOne("modify.selectAfterUpdate", userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		//세션 정보 갱신

		request.getSession().setAttribute("userDataMap", userDataMap);
		request.setAttribute("includePage", "noPost");

		//프로필 반영이 너무 느림. 한 20초는 기다려야 반영됨.

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

		//세션 만료가 안됨.
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

	@RequestMapping(value = "/goWrite.do")
	public String goWrite(int postNum, Model model) {

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;
		Map<String, Object> postDataMap = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			postDataMap = sqlSession.selectOne("diary.loadPost", postNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("postDataMap", postDataMap);
		model.addAttribute("includePage", "writePost");
		return "diary";
	}

	@RequestMapping(value = "/goPost.do")
	public String goPost(HttpServletRequest request) {
		//게시글 검색, 또는 좌측 목록에서 게시글을 누르면 해당 게시글로 이동

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;
		Map<String, Object> postDataMap = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			postDataMap = sqlSession.selectOne("diary.loadPost", Integer.parseInt(request.getParameter("postNum")));
			System.out.println(postDataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("focusedPost", postDataMap);
		request.setAttribute("includePage", "post");

		return "diary";
	}

	@RequestMapping(value = "/goSearch.do")
	public String goSearch(@RequestParam Map<String, Object> paramMap, Model model, HttpSession session) {
		//게시글 검색, 또는 좌측 목록에서 게시글을 누르면 해당 게시글로 이동

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;
		List<Object> postDataMap = null;
		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();
			Map<String, Object> userDataMap = (Map<String, Object>) session.getAttribute("userDataMap");
			paramMap.put("postUserNum", userDataMap.get("USER_NUM"));

			postDataMap = sqlSession.selectList("diary.searchPost", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("postDataMap", postDataMap);
		model.addAttribute("includePage", "searchResult");

		return "diary";
	}

	@RequestMapping(value = "/modifyCategory.do")
	@ResponseBody
	public String modifyCategory(@RequestParam Map<String, Object> paramMap, HttpSession session) {
		String isExistCategory = "T";

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			Map<String, Object> userDataMap = (Map<String, Object>) session.getAttribute("userDataMap");
			paramMap.put("postUserNum", userDataMap.get("USER_NUM"));

			String selectedCategory = sqlSession.selectOne("diary.selectCategory", paramMap);

			if( !(selectedCategory.equals((String) paramMap.get("currentCategory"))) ) {
				isExistCategory = "F";
				sqlSession.update("diary.updateCategory", paramMap);

				//세션 업데이트 - 게시글
				updatePostAndCategoryInSession(sqlSession, userDataMap, session);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isExistCategory;
	}

	@RequestMapping(value = "/deleteCategory.do")
	@ResponseBody
	public void deleteCategory(@RequestParam Map<String, Object> paramMap, HttpSession session) {

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

			Map<String, Object> userDataMap = (Map<String, Object>) session.getAttribute("userDataMap");
			paramMap.put("postUserNum", userDataMap.get("USER_NUM"));

			sqlSession.delete("diary.deleteCategory", paramMap);

			//세션 업데이트 - 게시글
			updatePostAndCategoryInSession(sqlSession, userDataMap, session);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/savePost.do")
	@ResponseBody
	public void savePost(@RequestParam Map<String, Object> paramMap, HttpSession session) {

		System.out.println(paramMap.keySet().toString());
		System.out.println(paramMap.get("contentHtml"));

		SqlSessionFactory temp = null;
		SqlSession sqlSession = null;

		try {
			temp = sqlSessionFactory.getObject();
			sqlSession = temp.openSession();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//인코딩 된 이미지 파일을 저장(미완성)
	public void writeToFile(String path, byte[] pData, int userNum, String exp) {
		String profilePath = "profile." + exp;
		File dir = new File(path + "/" + userNum);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(dir, profilePath));
			fos.write(pData);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//세션 업데이트 - 게시글, 카테고리
	public void updatePostAndCategoryInSession(SqlSession sqlSession, Map<String, Object> userDataMap, HttpSession session) {
		//diary.jsp에서 보여줄 게시글 목록 데이터
		//list(map POST_CATEGORY, POST_TITLE, POST_NUM)
		List<Object> postDataList = sqlSession.selectList("main.loadPostTitles", (Integer) userDataMap.get("USER_NUM"));

		//diary.jsp에서 보여줄 카테고리 데이터
		List<Object> categoryList = sqlSession.selectList("main.loadCategories", (Integer) userDataMap.get("USER_NUM"));

		//카테고리에 따라 게시글 분류
		HashMap<String, HashMap<Integer, String>> classifiedPostData = new HashMap<String, HashMap<Integer, String>>();

		for(int i=0; i < categoryList.size(); i++) {
			String category = (String) categoryList.get(i);
			classifiedPostData.put(category, new HashMap<Integer, String>());
			HashMap<Integer, String> postsInCategory = classifiedPostData.get(category);
			for(int j=0; j < postDataList.size(); j++) {
				Map<String, Object> post = (Map<String, Object>) postDataList.get(j);
				if(((String) post.get("POST_CATEGORY")).equals(category)) {
					postsInCategory.put((Integer) post.get("POST_NUM"), (String) post.get("POST_TITLE"));
				}
			}
		}

		//세션에 현재 등록된 게시글, 카테고리 정보를 등록
		session.setAttribute("classifiedPostData", classifiedPostData);
	}
}
