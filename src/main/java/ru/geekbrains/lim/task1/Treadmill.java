package ru.geekbrains.lim.task1;

public class Treadmill implements Obstruction{
    private final int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean overcome(Participant participant) {
        if (participant.run(distance)) {
            System.out.println("успешно " + distance + " метров");
            return true;
        } else {
            System.out.println("не успешно "  + distance + " метров");
            return false;
        }
    }
}
