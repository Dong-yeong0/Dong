package ajax;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/jquery/uploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init call()"); // 최조로 한번만 실행
	}

//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("service call()"); //get post 상관없이 호출 
//		Enumeration<String> enumer = req.getHeaderNames();
//		while(enumer.hasMoreElements()) {
//			String key = enumer.nextElement();
//			String val = req.getHeader(key);
//			System.out.println(key + " : " + val);
//		}
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet call()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost call()");
		String path = "c:/tmp";
		
		ServletContext sc = this.getServletContext();
		path = sc.getRealPath("upload"); //웹서버가 실행되는 실제 주소위치로 변환 / 서버 상 실제경로
		
//		MultipartRequest multi = new MultipartRequest(요청정보, "저장위치", 파일사이즈, 인코딩타입(UTF-8));
		MultipartRequest multi = new MultipartRequest(request, // 요청정보
																							path , // 저장위치
																							5 * 1024 * 1024, // 용량
																							"UTF-8", // 인코딩
																							new DefaultFileRenamePolicy());

		Enumeration en = multi.getFileNames(); //올라간 파일의 이름 읽기
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String fileName = multi.getFilesystemName(name);
			System.out.println("name : " + name + ", fileName" + fileName);
		}

	}

}
