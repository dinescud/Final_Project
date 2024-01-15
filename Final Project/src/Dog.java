public class Dog extends Animal implements needGroom{
    public Dog(String AnimalBreed, int price, int age, int weight) {
        super(AnimalBreed, price, age, weight);
    }
    private boolean dirty = true;
    @Override
    public boolean dirty() {
        return dirty;
    }

    @Override
    public void groom() {
        dirty = false;
    }

    enum Breed{
        Rottweiller, Golden, Bischon, Husky, Samoyed, Yorkshire, Labrador,
    }

}
