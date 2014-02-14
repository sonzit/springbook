package springbook.user.dao;

import java.sql.SQLException;

import springbook.user.User;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao();
		
		User user = new User();
		user.setId("spring");
		user.setName("스프링");
		user.setPassword("framework");
		
		dao.add(user);
		
		System.out.println(user.getId()  + " 등록 성공");
		
		User user2 = dao.get("spring");
		System.out.println(user2.getName());
		
		System.out.println(user2.getId() + " 조회 성공");
	}
}
