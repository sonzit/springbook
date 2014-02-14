/**
 * 상속이 아닌 독립적인 클래스로 분리
 * 전략패턴
 */

package springbook.user.dao6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.User;

public class UserDao6 {
	private ConnectionMaker connectionMaker;
	
	//constructor
	public UserDao6(ConnectionMaker connectionMaker) {
		super();
		this.connectionMaker = connectionMaker;
	}


	public void add(User user) throws SQLException, ClassNotFoundException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	

//	private Connection getConnection() throws ClassNotFoundException, SQLException {
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		return DriverManager.getConnection(
//				"jdbc:oracle:thin:@localhost:1521:xe", "java", "java");
//	}

	public User get(String id) throws SQLException, ClassNotFoundException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"select * from users where id = ?");
		ps.setString(1, id);
		
		User user = new User();
		ResultSet rs = ps.executeQuery();
		rs.next();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		return user;
	}
	
}
