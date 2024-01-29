/*
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
public class Input {
    public static ArrayList<Animal> readAnimal() {
        ArrayList<Animal> animalList = new ArrayList<Animal>();
        Dog dog1 = new Dog("Rottweiler", 5, 4000);
        Dog dog2 = new Dog("German Shepard", 2, 2000);
        Fish fish1 = new Fish("Golden Fish", 1, 230);
        animalList.add(dog1);
        animalList.add(dog2);
        animalList.add(fish);
        return animalList;
    }
}
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class Input {
    InputStream consoleInputStream;
    Scanner scanner;
    Input(){
        this.consoleInputStream = System.in;
        this.scanner = new Scanner(System.in);
    }

    public int nextInt(int minBound, int maxBound) {
        return ThreadLocalRandom.current().nextInt(minBound, maxBound);
    }

    public ArrayList<Animal> getRandomAnimal(int n){
        Random random = new Random();
        ArrayList<Animal> AnimalList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int randomPrice = random.nextInt(500, 999); // Example price range: $500 to $1500
            int randomAge = random.nextInt(1, 10); // Example age range: 1 to 10 years
            int randomWeight = random.nextInt(100, 1099); // Example weight range: 100 to 1100 kg

            Dog.Breed[] dogbreeds = Dog.Breed.values();
            Fish.Breed[] fishbreeds = Fish.Breed.values();

            int randomType = random.nextInt(1, 3);
            if (randomType == 1) {
                String randomBreed = dogbreeds[random.nextInt(dogbreeds.length)].toString();
                AnimalList.add(new Dog(randomBreed, randomPrice, randomAge, randomWeight));
            }
            if (randomType == 2) {
                String randomBreed = fishbreeds[random.nextInt(fishbreeds.length)].toString();
                AnimalList.add(new Fish(randomBreed, randomPrice, randomAge, randomWeight));
            }

        }
        for (Animal a : AnimalList) System.out.println(a);
        return AnimalList;
    }
}