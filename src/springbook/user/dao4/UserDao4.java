/**
 * 상속이 아닌 독립적인 클래스로 분리
 * UserDao4 수정하지 않으면 d사나 n사가 독립적인 db연결을 할 수 없다.
 */

package springbook.user.dao4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.User;

public class UserDao4 {
	private SimpleConnectionMaker simpleConnectionMaker;
	
	//constructor
	public UserDao4() {
		super();
		this.simpleConnectionMaker = new SimpleConnectionMaker();
	}


	public void add(User user) throws SQLException, ClassNotFoundException {
		Connection c = simpleConnectionMaker.makeNewConnection();
		
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
		Connection c = simpleConnectionMaker.makeNewConnection();
		
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
