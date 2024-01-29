import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StoreTest {
    @Test
    public void testStoreConstructor() {
        Staff staff = new Staff("John", "manager", 30);

        Store store = new Store(staff);

        assertEquals(staff, store.staff1);

        Animal[] animalArray = store.getAnimalList();
        assertNotNull(animalArray);
        assertEquals(0, animalArray.length);

        store.resetAnimals();
        assertEquals(0, store.getAnimalList().length);
    }
}