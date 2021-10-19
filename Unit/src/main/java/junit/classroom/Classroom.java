package junit.classroom;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Евгений", 10, Sex.MALE));
        users.add(new User("Марина", 9, Sex.FEMALE));
        users.add(new User("Алина", 11, Sex.FEMALE));
    }

    public static List<User> getAllUsers(){
        return users;
    }

    public static boolean hasUsers(){
        return !users.isEmpty();
    }

    public static User getUserByName(String name){
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public static boolean hasUsersOlderThan(int age) {
        for (User user : users) {
            if (user.getAge() > age) {
                return true;
            }
        }
        return false;
    }

    public static User deleteAllUsers() {
        throw new UnsupportedOperationException("Operation Not Supported");
    }
}
