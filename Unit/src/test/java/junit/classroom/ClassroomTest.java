package junit.classroom;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class ClassroomTest {

    @Test
    public void testAssertEquals() {
        //создаем тестовые данные
        User user = new User("Евгений", 10, Sex.MALE);
        User user1 = new User("Марина", 9, Sex.FEMALE);
        User user2 = new User("Алина", 11, Sex.FEMALE);

        //создаем список expected и заполняем его данными нашего метода
        List<User> expected = Classroom.getAllUsers();

        //создаем список actual в него помещаем данные для сравнения
        //то что мы предпологиаем метод должен вернуть
        List<User> actual = new ArrayList<>();
        actual.add(user);
        actual.add(user1);
        actual.add(user2);

        //запускаем тест, в случае если список expected и actual не будут равны
        //тест будет провален, о результатах теста читаем в консоли
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAssertArrayEquals() {
        char[] expected = {'J', 'u', 'n', 'i', 't'};
        char[] actual = "Junit".toCharArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAssertTrue() {
        boolean hasUsers = Classroom.hasUsers();

        assertTrue(hasUsers);
    }

    @Test
    public void testAssertFalse() {
        boolean hasUsers = Classroom.hasUsersOlderThan(20);

        assertFalse(hasUsers);
    }

    @Test
    public void testAssertNotNull() {
        User user = Classroom.getUserByName("Евгений");

        assertNotNull(user);
    }

    @Test
    public void testAssertNull() {
        User user = Classroom.getUserByName("Олег");

        assertNull(user);
    }

    @Test
    public void testAssertSame() {
        User expected = Classroom.getUserByName("Марина");
        User actual = Classroom.getUserByName("Марина");

        assertSame(expected, actual);
    }

    @Test
    public void testAssertNotSame() {
        User expected = Classroom.getUserByName("Алина");
        User actual = new User("Алина", 11, Sex.FEMALE);

        assertNotSame(expected, actual);
    }

    @Test
    public void testAssertFail() {
        try {
            Classroom.deleteAllUsers();
            fail("Exception not thrown");
        } catch (UnsupportedOperationException e) {
            assertEquals("Operation Not Supported", e.getMessage());
        }
    }

    @Test
    public void testAssertThat() {
        List<User> users = Classroom.getAllUsers();
        User expected = new User("Евгений", 10, Sex.MALE);

        assertThat(users, CoreMatchers.hasItems(expected));
    }
}