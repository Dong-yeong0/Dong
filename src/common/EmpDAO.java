package common;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;
	
	public Employee insertEmpBySeq(Employee emp) {
		conn = DBcon.getConnect();
		
		Employee emp1 = new Employee();
		
		String sql1 = "select employees_seq.nextval from dual";
		String sql2 = "insert into emp_temp(employee_id, last_name, email, hire_date, job_id)"
								+"values (?, ?, ?, ?, ?)";
		try {
			int empId = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			if(rs.next()) {
				empId = rs.getInt(1);
				
			}
			
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1,empId);
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJobId());
			int r = psmt.executeUpdate();
			System.out.println(r+ "건 입력됨");	
			
			// 입력한 값을 반환해주기 위해서 emp1에 넣어줌
			emp1.setEmployeeId(empId);
			emp1.setEmail(emp.getEmail());
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
			System.out.println(r+ "건 입력됨");	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(psmt != null)
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public List<Employee> getEmpByDept(String dept){
		// 사원 정보를 가지고 오는 처리.
				String sql = "select * from emp_temp where department_id = " + dept + "order by employee_id";
				conn = DBcon.getConnect();
				List<Employee> employees = new ArrayList<Employee>();
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						Employee emp = new Employee();
						emp.setEmployeeId(rs.getInt("employee_id"));
						emp.setFirstName(rs.getString("first_name"));
						emp.setLastName(rs.getString("last_name"));
						emp.setEmail(rs.getString("email"));
						emp.setSalary(rs.getInt("salary"));
						
						employees.add(emp);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(rs != null)
						try {
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					if(stmt != null)
						try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					if(conn != null)
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				
				employees.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		return employees;
	}

}
