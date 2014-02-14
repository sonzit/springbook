package springbook.user.dao7;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.User;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao7 dao = context.getBean("userDao", UserDao7.class);
		
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
