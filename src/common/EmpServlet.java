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
		
		String dept = req.getParameter("dept");
		
		EmpDAO dao = new EmpDAO();
		List<Employee> list = null;
		if(dept == null) {
			list = dao.getEmpList();	
		}else {
			list = dao.getEmpByDept(dept);
		}
		
		
		String jsonData = "[";
		// [{"empId" : "?", "fName":"?", "lName":"?"}, ... ]
		int cnt = 0;
		for(Employee emp : list) {
	         jsonData += "{\"empId\": \"" + emp.getEmployeeId() + "\",\"fName\": \"" + emp.getFirstName()//
             + "\",\"lName\": \"" + emp.getLastName()//
             + "\",\"lName\": \"" + emp.getEmail()//
             + "\",\"lName\": \"" + emp.getSalary()//
             + "\"}";
			if(++cnt == list.size()) {
				continue;
			}
			jsonData += ",";
		}
		jsonData += "]";
		out.print(jsonData);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lastName = req.getParameter("last_name");
		String email = req.getParameter("email");
		String hireDate = req.getParameter("hire_date");
		String jobId = req.getParameter("job_id");
		
		Employee emp = new Employee();
		emp.setLastName(lastName);
		emp.setEmail(email);
		emp.setHireDate(hireDate);
		emp.setJobId(jobId);
		
		EmpDAO dao = new EmpDAO();
		dao.insertEmp(emp);
		
		resp.getWriter().print("<h1>Success</h1>");
		
	}
	
}



