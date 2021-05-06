package fileBoard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getFileDelServlet")
public class GetFileDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetFileDelServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String num = request.getParameter("num");
		int fileNum = Integer.parseInt(num);
		
		FileDAO dao = new FileDAO();
		FileVO vo = new FileVO();
		
		vo.setNum(fileNum);
		dao.delFile(vo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
