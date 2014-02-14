package springbook.user.dao6;

public class DaoFactory {
	public UserDao6 userDao6() {
		return new UserDao6(new DConnectionMaker());
	}
}
