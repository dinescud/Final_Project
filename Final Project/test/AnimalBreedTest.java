import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AnimalBreedTest {

        @Test
        public void testGroupByBreed() {
            Animal animal1 = new Animal("Dog", 150, 3, 5000);
            Animal animal2 = new Animal("Cat", 120, 2, 3000);
            Animal animal3 = new Animal("Dog", 200, 4, 6000);
            Animal animal4 = new Animal("Fish", 80, 1, 1000);

            ArrayList<Animal> animals = new ArrayList<>();
            animals.add(animal1);
            animals.add(animal2);
            animals.add(animal3);
            animals.add(animal4);

            Map<String, Integer> result = Animal.groupByBreed(animals);

            assertEquals(2, result.get("Dog"));
            assertEquals(1, result.get("Cat"));
            assertEquals(1, result.get("Fish"));
            assertEquals(3, result.size());
        }
}