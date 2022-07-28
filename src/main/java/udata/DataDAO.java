package udata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.DataVO;
import user.User;

public class DataDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public DataDAO() {
		try {
			String url = "jdbc:mysql://localhost:3306/pfolio?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
			String user = "root";
			String password = "smart";

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int write(DataVO vo) {
		String sql = "INSERT INTO udata VALUES(null, ?, ?, now(), ?, ?) ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getDataTitle());
			stmt.setString(2, vo.getUserID());
			stmt.setString(3, vo.getDataContent());
			stmt.setInt(4, 1); // 글의 유효번호.

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return -1;// 오류
	}

	public int getNext() {
		String sql = "SELECT dataID FROM udata ORDER BY dataID DESC";
		try {
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("dataID") + 1;
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<UData> getList(int pageNumber) {
		String sql = "SELECT * FROM udata WHERE dataID < ? AND dataAvailable = 1";
		// 모든 게시글을 조회합니다, 현재 유효번호가 존재하고 새롭게 작성될 게시글 번호보다 작은 모든 게시글 번호를 내림차순 정렬로 최대 10개까지
		// 조회합니다.

		ArrayList<UData> list = new ArrayList<UData>();
		int dataID = getNext() - (pageNumber - 1) * 10;
		// 만약 현재 글이 5개라면 getNext()=6, 1페이지이기 때문에 결과값은 6이 나온다, 6보다 낮은 5개의 게시글이 출력.

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dataID);
			rs = stmt.executeQuery();

			while (rs.next()) {
				// while(rs.next()) = > 결과값이 존재하는 동안 각각의 요소를 각각 담아 하나의 리스트를 완성하여 getList로 return
				UData udata = new UData();
				udata.setDataID(rs.getInt("dataID"));
				udata.setDataTitle(rs.getString("dataTitle"));
				udata.setUserID(rs.getString("userID"));
				udata.setDataDate(rs.getString("dataDate"));

				list.add(udata);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}
}
