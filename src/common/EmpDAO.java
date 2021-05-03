package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;

	public void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public Employee insertEmpBySeq(Employee emp) {
		conn = DBcon.getConnect();

		Employee emp1 = new Employee();

		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "insert into emp_temp(employee_id, first_name, last_name, email, hire_date, job_id, salary, department_id)"
				+ "values (?, ?, ?, ?, ?, ?, ?, 50)";
		try {
			int empId = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				empId = rs.getInt(1);

			}
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, empId);
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getHireDate());
			psmt.setString(6, emp.getJobId());
			psmt.setInt(7, emp.getSalary());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨");

			// 입력한 값을 반환해주기 위해서 emp1에 넣어줌
			emp1.setEmployeeId(empId);
			emp1.setEmail(emp.getEmail());
			emp1.setFirstName(emp.getFirstName());
			emp1.setSalary(emp.getSalary());
			emp1.setLastName(emp.getLastName());
			emp1.setHireDate(emp.getHireDate());
			emp1.setJobId(emp.getJobId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp1;
	}

	public void insertEmp(Employee emp) {
		String sql = "insert into emp_temp(employee_id, last_name, email, hire_date, job_id) "
				+ "values ((select max(employee_id)+1 from emp_temp), ?,?,?,?)";
		conn = DBcon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getLastName());
			psmt.setString(2, emp.getEmail());
			psmt.setString(3, emp.getHireDate());
			psmt.setString(4, emp.getJobId());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public List<Employee> getEmpByDept(String dept) {
		// 사원 정보를 가지고 오는 처리.
		String sql = "select * from emp_temp where department_id = " + dept + "order by employee_id";
		conn = DBcon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setSalary(rs.getInt("salary"));
				employees.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return employees;
	}

	public List<Employee> getEmpList() {
		// 사원 정보를 가지고 오는 처리.
		String sql = "select * from emp_temp order by employee_id";
		conn = DBcon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setSalary(rs.getInt("salary"));

				employees.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return employees;
	}

	// getEmployeesList()
	public List<Employee> getEmployeesList() {
		// empl_demo
		String sql = "select * from empl_demo order by employee_id";
		conn = DBcon.getConnect();
		List<Employee> employees = new ArrayList<Employee>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setSalary(rs.getInt("salary"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				employees.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return employees;
	}// getEmployeeList()

	public  Map<String, Integer> getEmployeeByDept() {
		// 부서명, 사원수
		Map<String, Integer> map = new HashMap<>();
		String sql = "select d.department_name, count(1)\r\n"//
				+ "from empl_demo e, departments d\r\n"//
				+ "where e.department_id = d.department_id\r\n"//
				+ "group by d.department_name";
		conn = DBcon.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				map.put(rs.getString(1),rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return map;
	}
	
	// 스케줄 정보를 가지고 오는 메소드.
	public List<ScheduleVO> getScheduleList(){
		String sql = "select * from schedule";
		conn = DBcon.getConnect();
		List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ScheduleVO vo = new ScheduleVO();
				vo.setTitle(rs.getString("title"));
				vo.setStartDay(rs.getString("start_day"));
				vo.setEndDay(rs.getString("end_day"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				close();
			}
		}
		return list;
	}
	// 한건 입력.
	public void insertSchedule(ScheduleVO vo) {
		String sql = "insert into schedule values(?,?,?)";
		conn = DBcon.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getStartDay());
			psmt.setString(3, vo.getEndDay());
			int n = psmt.executeUpdate();
			System.out.println(n + "건 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	//한건 삭제
	public void delSchedule(ScheduleVO vo) {
		String sql = "delete from schedule where title = ?";
		conn = DBcon.getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			int n = psmt.executeUpdate();
			System.out.println(n + "건 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
