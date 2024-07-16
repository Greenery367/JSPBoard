package com.tenco.tboard.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tenco.tboard.model.User;
import com.tenco.tboard.repository.interfaces.UserRepository;
import com.tenco.tboard.util.DBUtil;

public class UserRepositoryImpl implements UserRepository {

	// CRUD 쿼리문 선언 
	// 유저 추가 쿼리
	private static final String INSERT_USER_SQL=" INSERT INTO users(username,password,email) values (? , ? , ?)";
	// 유저 삭제 쿼리
	private static final String DELETE_USER_SQL=" DELETE FROM users where id = ? ";
	// 이름으로 유저 검색 쿼리
	private static final String SELECT_USER_BY_USERNAME=" SELECT * from users where username = ? ";
	// 이름, 비밀번호로 유저 검색 쿼리
	private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD=" SELECT * FROM users where username= ? AND password = ? ";
	// 전체 유저 확인
	private static String SELECT_ALL_USERS= " SELECT * FROM users ";
	
	@Override
	public int addUser(User user) {
		try (Connection conn=DBUtil.getConnection()){
			// 트랜잭션 시작
			conn.setAutoCommit(false);
			// Service 객체 :
			// username 중복 확인 필요
			// email 중복 확인 필요
			
			try (PreparedStatement pstmt=conn.prepareStatement(INSERT_USER_SQL)){
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getEmail());
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public void deleteUser(int id) {
		try (Connection conn=DBUtil.getConnection()){
			// 트랜잭션 시작
			conn.setAutoCommit(false);
			// 실제로 사용자가 존재하는지 여부 확인 필요
			// 하지만 delete 는 오류가 거의 없기 때문에, 넣지 않아도 ㅇ
			// int rowCount를 사용해 확인할 수 있다.
			try (PreparedStatement pstmt=conn.prepareStatement(DELETE_USER_SQL)){
				pstmt.setInt(1,id);
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUserByUsername(String username) {
		User user=null;
		try (Connection conn=DBUtil.getConnection()){
			// SELECT-트랜잭션 처리 x
			PreparedStatement pstmt=conn.prepareStatement(SELECT_USER_BY_USERNAME);
			pstmt.setString(1, username);
			pstmt.executeQuery();
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				user=User.builder().id(rs.getInt("id"))
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.email(rs.getString("email"))
						.createdAt(rs.getTimestamp("created_at"))
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		User user=null;
		try (Connection conn=DBUtil.getConnection()){
			// SELECT-트랜잭션 처리 x
			PreparedStatement pstmt=conn.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.executeQuery();
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				user=User.builder().id(rs.getInt("id"))
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.email(rs.getString("email"))
						.createdAt(rs.getTimestamp("created_at"))
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users=new ArrayList<>();
		try (Connection conn=DBUtil.getConnection()){
			// SELECT-트랜잭션 처리 x
			PreparedStatement pstmt=conn.prepareStatement(SELECT_ALL_USERS);
			pstmt.executeQuery();
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				User user=User.builder().id(rs.getInt("id"))
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.email(rs.getString("email"))
						.createdAt(rs.getTimestamp("created_at"))
						.build();
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

}
