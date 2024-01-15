import java.util.ArrayList;
import java.util.Comparator;

public class Store {
    Staff staff1;
    ArrayList<Animal> animalList = new ArrayList<>();

    Output output = new Output();
    public Store(Staff staff1) {
        this.staff1 = staff1;
    }

    private void sortAnimals(){
        animalList.sort(Comparator.naturalOrder());
    }

    public void resetAnimals(){
        animalList.clear();
    }

    public void removeAnimalIdx(int idx){
        animalList.remove(idx);
    }

    public void addAnimal(Animal animal){
        try{
            animal.checkWeight();
        }catch(InvalidAnimalWeight e){
            switch (e.getMessage()){
                case "Invalid age" -> System.out.println("Invalid age: " + animal.getAge());
                case "Invalid weight" -> System.out.println("Invalid weight: " + animal.getWeight());
            }
        }
        for(Animal a : animalList){
            if(a.equals(animal)){
                a.setPrice(animal.getPrice());
                return;
            }
        }
        animalList.add(animal);
        sortAnimals();
    }

    public void countAnimals(ArrayList<Animal>  animals){
        int total=0;
        for(Animal a : animals){
            total += 1;
        }
        output.printMessageNl("\nNumber of animals in store: " + total);
    }

    public Animal[] getAnimalList() {
        return animalList.toArray(new Animal[0]);
    }

    public void setAnimalList(ArrayList<Animal> animalList) {
        resetAnimals();
        for(Animal animal : animalList){
            this.addAnimal(animal);
        }
    }
}