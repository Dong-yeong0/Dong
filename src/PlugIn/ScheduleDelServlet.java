package PlugIn;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.EmpDAO;
import common.ScheduleVO;

@WebServlet("/scheduleDelServlet")
public class ScheduleDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ScheduleDelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScheduleVO vo = new ScheduleVO();
		EmpDAO dao = new EmpDAO();
		String title =  request.getParameter("title");
		vo.setTitle(title);
		dao.delSchedule(vo);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
