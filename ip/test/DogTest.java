import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {
    @Test
    public void testDogConstructor() {
        Dog dog = new Dog(Dog.Breed.Labrador.name(), 200, 3, 5000);

        assertEquals("Labrador", dog.AnimalBreed);
        assertEquals(200, dog.getPrice());
        assertEquals(3, dog.getAge());
        assertEquals(5000, dog.getWeight());
        assertTrue(dog.dirty());

        dog.groom();
        assertFalse(dog.dirty());
    }
}