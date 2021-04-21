package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empList.do")
public class EmpServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		PrintWriter out = resp.getWriter();
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		String jsonData = "[";
		// [{"empId" : "?", "fName":"?", "lName":"?"}, ... ]
		int cnt = 0;
		for(Employee emp : list) {
			jsonData += ("{\"empId\":\""+emp.getEmployeeId()+"\","
					+ "\"fName\":\""+emp.getFirstName()+"\","
					+ "\"lName\":\""+emp.getLastName()+"\","
					+ "\"email\":\""+emp.getEmail()+"\","
					+ "\"salary\":\""+emp.getSalary()+"\"}");
			if(++cnt == list.size()) {
				continue;
			}
			jsonData += ",";
		}
		jsonData += "]";
	}
}
