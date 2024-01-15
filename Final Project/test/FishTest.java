import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class FishTest {
    @Test
    public void testFishConstructor() {
        Fish fish = new Fish(Fish.Breed.ClownFish.name(), 50, 1, 200);

        assertEquals("ClownFish", fish.AnimalBreed);
        assertEquals(50, fish.getPrice());
        assertEquals(1, fish.getAge());
        assertEquals(200, fish.getWeight());
        assertFalse(fish.dirtyWater());

        fish.change();
        assertTrue(fish.dirtyWater());
    }
}