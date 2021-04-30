package PlugIn;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.EmpDAO;

@WebServlet("/empByDeptServlet")
public class EmpByDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpByDeptServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmpDAO dao = new EmpDAO();
		Map<String, Integer> map = dao.getEmployeeByDept(); // 부서별 사원수 가져오기 위함
		// [ ["Sales", 14], ["부서명" , 사원수] ]
		String json = "[";
		Set<String> keySet = map.keySet();
		Iterator<String> iter = keySet.iterator();
		while(iter.hasNext()) {
			String key = iter.next(); // 부서명 가져옴
			Integer val = map.get(key); // 사원수를 가져온다
			json += "[\""+key+"\","+val+"]";
			if(iter.hasNext()) {	// 또 다른 값이 있으면 , 로 구별
				json += ",";	
			}
		}
		json += "]";
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
