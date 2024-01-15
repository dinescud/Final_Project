import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeavierThan3KGTest {

    @Test
    public void testHeavierThan3kg() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Dog", 200, 2, 5000));
        animals.add(new Animal("Cat", 150, 3, 2000));
        animals.add(new Animal("Fish", 100, 1, 4000));

        Animal animal = new Animal();
        List<Animal> result = animal.heavierThan3kg(animals);

        assertEquals(2, result.size());

        for (Animal a : result) {
            assertEquals(true, a.getWeight() > 3000);
        }
    }
}
