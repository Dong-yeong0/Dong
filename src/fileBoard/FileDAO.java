package fileBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBcon;

public class FileDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	// Update
	public boolean updateFile(FileVO vo) {
		conn = DBcon.getConnect();
		String sql = "update file_board set author = ?, title= ? , file_name= ? where num= ?";
		int modifyCnt = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getAuthor());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getFileName());
			psmt.setInt(4, vo.getNum());
			modifyCnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return modifyCnt == 0 ? false : true; // 0 이면 false 1 아니면 true
	}

	// DB삭제
	public void delFile(FileVO vo) {
		conn = DBcon.getConnect();
		String sql = "delete from file_board where num = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getNum());
			int n = psmt.executeUpdate();
			System.out.println(n + "건 삭제됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 한건 조회
	public FileVO getFile(int num) { // num 값으로 한건 조회.
		conn = DBcon.getConnect();
		FileVO file = new FileVO();
		String sql = "select * from file_board where num = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			rs = psmt.executeQuery();
			if (rs.next()) {
				file.setNum(rs.getInt("num"));
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setTitle(rs.getString("title"));
				file.setFileName(rs.getString("file_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return file;
	}

	public List<FileVO> getFileList() {
		conn = DBcon.getConnect();
		List<FileVO> list = new ArrayList<FileVO>();
		try {
			psmt = conn.prepareStatement("select * from file_board");
			rs = psmt.executeQuery();
			while (rs.next()) {
				FileVO vo = new FileVO();
				vo.setNum(rs.getInt("num"));
				vo.setAuthor(rs.getString("author"));
				vo.setDay(rs.getString("day"));
				vo.setTitle(rs.getString("title"));
				vo.setFileName(rs.getString("file_name"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public FileVO getInsertKeyVal(FileVO vo) {
		conn = DBcon.getConnect();
		// 신규번호, 한건 입력, 한건조회
		String selectKey = "select nvl(max(num),0)+1 from file_board";
		String insertSql = "insert into file_board values(?,?,?,?,to_char(sysdate, 'YYYY-MM-DD'))";
		String selectSql = "select * from file_board where num = ?";
		FileVO file = new FileVO();
		int key = 0;
		try {
			// 키 값을 가져오는 부분
			psmt = conn.prepareStatement(selectKey);
			rs = psmt.executeQuery();
			if (rs.next()) {
				key = rs.getInt(1);
			}
			// 새로운 키 값으로 한 건 입력
			psmt = conn.prepareStatement(insertSql);
			psmt.setInt(1, key);
			psmt.setString(2, vo.getAuthor());
			psmt.setString(3, vo.getTitle());
			psmt.setString(4, vo.getFileName());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력.");

			// 신규 입력된 전체 정보를 가져온다
			psmt = conn.prepareStatement(selectSql);
			psmt.setInt(1, key);
			rs = psmt.executeQuery();
			if (rs.next()) {
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setFileName(rs.getString("file_name"));
				file.setNum(rs.getInt("num"));
				file.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return file;
	}

	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
