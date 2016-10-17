package com.yeon.guestbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestBookDao {

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url,"webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}
		return conn;
	}

	// insert vo
	public void insert(GuestbookVo gvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = " insert into guestbook values( guestbook_seq.nextval , ?, sysdate,?,  ? )";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, gvo.getName());
			pstmt.setString(2, gvo.getPassword());
			pstmt.setString(3, gvo.getContent());
			
			pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// show datas
	public List<GuestbookVo> getlist() {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GuestbookVo vo =null;
		try {
			conn = getConnection();
			String sql = "select no, name, reg_date, password, content from guestbook ORDER BY no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				 vo = new GuestbookVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setReg_date(rs.getString(3));
				vo.setPassword(rs.getString(4));
				vo.setContent(rs.getString(5));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	// delete vo
	public void delete(GuestbookVo gvo) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		try {
			conn = getConnection();
			String sql = "DELETE guestbook where no = ?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setLong(1, gvo.getNo());
			
			pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("error: "+ e);
		}
	}

}
