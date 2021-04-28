package ajax;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	Connection conn;
	Statement stmt;
	ResultSet rs;

	public List<Member> getMemList() {
		// 조회
		String sql = "select * from member";
		List<Member> list = new ArrayList<Member>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
