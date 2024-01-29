public class Fish extends Animal implements changeWater{
    public Fish(String AnimalBreed,int price, int age, int weight) {
        super(AnimalBreed, price, age, weight);
    }
    private boolean cleanWater = false;
    @Override
    public boolean dirtyWater() {
        return cleanWater;
    }
    @Override
    public void change() {
        cleanWater = true;
    }

    enum Breed{
        ClownFish, Koi, GoldenFish, BubbleEye, Ryukin
    }

}
