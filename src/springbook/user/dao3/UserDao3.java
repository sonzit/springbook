/**
 * 상속을 통한 확장하여 n사, d사가 각각의 db연결을 하도록
 * 단점 : 수퍼클래스와 밀접한 관계
 * 		   DAO가 추가되면 거기서도 커넥션을 만들어야 함(중복)
 */

package springbook.user.dao3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.User;

public abstract class UserDao3 {
	
	public void add(User user) throws SQLException, ClassNotFoundException {
		Connection c = getConnection();
		
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
		Connection c = getConnection();
		
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
	
	//추상메서드로 만들어 상속하여 구현하도록
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
	
}
