package ru.geekbrains.lim.task1;

public class Treadmill implements Obstruction{
    int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean move(Participant participant) {
        if (participant.run(distance)) {
            System.out.println("успешно " + distance + " метров");
            return true;
        } else {
            System.out.println("не успешно "  + distance + " метров");
            return false;
        }
    }
}
