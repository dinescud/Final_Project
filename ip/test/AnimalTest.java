import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    public void testAnimalConstructor() {
        Animal animal = new Animal("Labrador", 200, 3, 5000);

        assertEquals("Labrador", animal.AnimalBreed);
        assertEquals(200, animal.getPrice());
        assertEquals(3, animal.getAge());
        assertEquals(5000, animal.getWeight());
        assertFalse(animal.dirtyWater());
        assertFalse(animal.dirty());

        animal.groom();
        assertTrue(animal.dirty());
        animal.change();
        assertTrue(animal.dirtyWater());
    }
}