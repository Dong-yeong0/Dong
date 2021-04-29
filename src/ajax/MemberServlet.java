package ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBcon;

@WebServlet("/jquery/memberServlet.do") // 실제 주소
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 조회작업.
		response.setContentType("text/html;charset=UTF-8");

		// 조회 sql
		Connection conn = DBcon.getConnect();
		ResultSet rs = null;
		PreparedStatement psmt = null;
		String json = "[";
		String sql = "select member_id, member_name, member_age from member";
		try {
			psmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String memId = rs.getString(1);
				String memName = rs.getString(2);
				int memAge = rs.getInt(3);
				
				// 조회 결과를 json 형식으로 변환 [
				// {"id":1,"name":"hong","age":20}
				// {"id":1,"name":"hong","age":20}
				// {"id":1,"name":"hong","age":20}]
				json += "{\"Id\":\"" + memId + "\"" + ",\"Name\":\"" + memName + "\"" + ",\"Age\":" + memAge + "}";
				if (!rs.isLast())
					json += ",";
			}
			json += "]";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (psmt != null)
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		// 결과를 response.getWriter().print(); 출력
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String p1 = request.getParameter("m_id");
		String p2 = request.getParameter("m_name");
		String p3 = request.getParameter("m_age");

		Connection conn = DBcon.getConnect();
		PreparedStatement psmt = null;
		String sql = "insert into member values(?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, p1);
			psmt.setString(2, p2);
			psmt.setString(3, p3);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null)
				try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		// {"id":1, "name":"hong", "age":20}
		String json = "{\"id\":\""+p1+"\",\"name\":\""+p2+"\",\"age\":\""+p3+"\"}";
		response.getWriter().print(json);
	}

}
