import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class countAnimalsTest {

    @Test
    public void testCountAnimals() {
        Staff staff = new Staff("John", "manager", 30);

        Store store = new Store(staff);

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Labrador", 200, 3, 5000));
        animals.add(new Animal("ClownFish", 50, 1, 200));

        store.countAnimals(animals);
    }
}