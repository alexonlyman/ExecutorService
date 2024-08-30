package alex_group;

import java.util.HashSet;
import java.util.Set;

public class ComplexTask {

    private final Set<String> house = new HashSet<>();


    public void buildWalls() {
        String str = "строим стены";
        execute(str);

    }

    public void buildWindows() {
        String str = "вставляем окна";
        execute(str);
    }

    public void buildRoof() {
        String str = "строим крышу";
        execute(str);
    }

    public void buildDoors() {
        String str = "вставляем двери";
        execute(str);
    }

    public void buildFloor() {
        String str = "заливаем пол";
        execute(str);
    }

    public void execute(String s) {
        house.add(s);
        System.out.println(Thread.currentThread().getName() + " " + s);

    }

    @Override
    public String toString() {
        return "ComplexTask{" +
                "house=" + house +
                '}';
    }
}
