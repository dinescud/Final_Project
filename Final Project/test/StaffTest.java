import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StaffTest {
    @Test
    public void testStaffConstructor() {
        Staff staff = new Staff("John", "manager", 30);

        assertEquals("John", staff.name);
        assertEquals("manager", staff.job);
        assertEquals(30, staff.getAge());

        Staff youngerStaff = new Staff("Alice", "assistant", 25);
        Staff olderStaff = new Staff("Bob", "supervisor", 35);
        assertEquals(1, olderStaff.compareTo(staff));
        assertEquals(-1, youngerStaff.compareTo(staff));
        assertEquals(0, staff.compareTo(staff));

        try {
            staff.checkAge();
        } catch (InvalidPersonAge e) {
            fail("Unexpected InvalidPersonAge exception for a valid age");
        }
    }
}