public class Staff implements Comparable<Staff>{
    String job;
    String name;
    int age;

    public Staff(String name,String job, int age) {
        this.name = name;
        this.job = job;
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    @Override
    public int compareTo(Staff o) {
        if (this.getAge() == o.getAge()) return 0;
        else if (this.getAge() > o.getAge()) return 1;
        else return -1;
    }
    public void checkAge() throws InvalidPersonAge{
        if(this.age < 18) throw new InvalidPersonAge("Invalid age");
    }

    @Override
    public String toString(){
        return this.name + ":\n- job " + this.job + "\n- age " + this.age + "\n";
    }
}