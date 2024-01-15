import java.util.*;

public class Animal implements needGroom, changeWater, Comparable<Animal>{
    String AnimalBreed;
    int price;
    int age;
    int weight;
    boolean isGroomed = false;
    boolean cleanWater = false;

    public Animal(String animalBreed, int price, int age, int weight) {
        AnimalBreed = animalBreed;
        this.price = price;
        this.age = age;
        this.weight = weight;
    }

    static Output output = new Output();
    Input input = new Input();

    public Animal() {

    }

    public static void totalWeight(ArrayList<Animal> animals){
        int total = 0;
        for(Animal a : animals){
            total += a.weight;
        }
        output.printMessageNl("\nTotal weight of animals in store: " + total);
    }

    public int getAge(){ return this.age;}
    public int getWeight(){ return this.weight;}
    public int getPrice(){ return this.price;}
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int compareTo(Animal o) {
        if (this.getWeight() == o.getWeight()) return 0;
        else if (this.getWeight() > o.getWeight()) return 1;
        else return -1;
    }

    @Override
    public boolean dirtyWater(){
        return cleanWater;
    }
    @Override
    public void change() {
        cleanWater = true;
    }

    @Override
    public boolean dirty() {
        return isGroomed;
    }
    @Override
    public void groom() {
        isGroomed = true;
    }

    public List<Animal> heavierThan3kg(List<Animal> animals) {
        List<Animal> Sorted = new ArrayList<>();
        for(Animal animal : animals ){
            if(animal.getWeight() > 3000) Sorted.add(animal);
        } return Sorted;
    }

    public void sortByWeight(ArrayList<Animal> animals){
        animals.sort(Comparator.comparing(Animal::getWeight));
    }

    public static Map<String, Integer> groupByBreed(ArrayList<Animal> animals) {
        Map<String, Integer> animalMap = new HashMap<>();
        for(Animal animal : animals ){
            if(animalMap.containsKey(animal.AnimalBreed)){
                animalMap.put(animal.AnimalBreed, animalMap.get(animal.AnimalBreed) + 1);
            }else {
                animalMap.put(animal.AnimalBreed, 1);
            }
        }
        return animalMap;
    }


    public void checkWeight() throws InvalidAnimalWeight {
        if(this.weight > 7000) throw new InvalidAnimalWeight("Invalid weight");
    }

    @Override
    public String toString(){
        return this.AnimalBreed + ":\n- price " + this.price + "\n- age " + this.age + "\n- weight " + this.weight + "\n";
    }

    public Animal createAnimal() {
        Scanner scanner = new Scanner(System.in);

        output.printMessageNl("\n Choose the type of animal:");
        output.printMessageNl("1. Dog");
        output.printMessageNl("2. Fish");

        int animalType = getUserInput(scanner, "\n Enter your choice (1 or 2): ", 1, 2);

        String animalBreed;

        if (animalType == 1) {
            output.printMessage("\n Enter dog breed (Rottweiller, Golden, Bischon, Husky, Samoyed, Yorkshire, Labrador): ");
            while (true) {
                animalBreed = scanner.nextLine().trim();
                output.printMessageNl(animalBreed);
                try {
                    if (isValidDogBreed(animalBreed)) {
                        break;
                    } else {
                        output.printMessage("\n Invalid breed. Please enter a valid dog breed: ");
                    }
                } catch (IllegalArgumentException e) {
                    output.printMessage("\n Invalid breed. Please enter a valid dog breed: ");
                }
            }
        } else {
            output.printMessage("\n Enter fish breed (ClownFish, Koi, GoldenFish, BubbleEye, Ryukin):");
            while (true) {
                animalBreed = scanner.nextLine().trim();
                try {
                    if (isValidFishBreed(animalBreed)) {
                        break;
                    } else {
                        output.printMessage("\n Invalid breed. Please enter a valid fish breed: ");
                    }
                } catch (IllegalArgumentException e) {
                    output.printMessage("\n Invalid breed. Please enter a valid fish breed: ");
                }
            }
        }
        int price = getUserInput(scanner, "Enter price (must be greater than 100): ", 101);
        int age = getUserInput(scanner, "Enter age (must be between 0 and 20): ", 0, 20);
        int weight = getUserInput(scanner, "Enter weight: ");

        Animal newAnimal = new Animal(animalBreed, price, age, weight);
        return newAnimal;
    }

    public int getUserInput(Scanner scanner, String prompt, int... validRange) {
        int userInput;
        do {
            output.printMessage(prompt);
            while (!scanner.hasNextInt()) {
                output.printMessageNl("Invalid input. Please enter a valid number.");
                scanner.next(); // consume the invalid input
            }
            userInput = scanner.nextInt();
            if (validRange.length == 0) {
                break; // No range validation needed
            }
            if (validRange.length == 1 && userInput >= validRange[0]) {
                break; // Validate against minimum value
            }
            if (validRange.length == 2 && userInput >= validRange[0] && userInput <= validRange[1]) {
                break; // Validate against range
            }
            output.printMessageNl("Invalid input. Please enter a valid number.");
        } while (true);

        scanner.nextLine();
        return userInput;
    }

    public boolean isValidDogBreed(String breed) {
        try {
            Dog.Breed.valueOf(breed);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isValidFishBreed(String breed) {
        try {
            Fish.Breed.valueOf(breed);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getAnimalBreed() {
        return AnimalBreed;
    }
}
