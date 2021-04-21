package common;

import java.util.List;
public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		for(Employee emp : list) {
			System.out.println(emp.getEmployeeId()+','+emp.getFirstName());
		}
 	}

}
