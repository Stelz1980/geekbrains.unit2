package ru.geekbrains.lim.task1;

import java.util.Random;

public class HomeWorkApp {

    public static void main(String[] args) {

        Random random = new Random();
        Participant[] participants = {
                new Human(random.nextInt(500), random.nextInt(3)),
                new Robot(random.nextInt(500), random.nextInt(3)),
                new Cat(random.nextInt(500), random.nextInt(3))
        };

        Obstruction[] obstructions = {
                new Wall(random.nextInt(3)),
                new Treadmill(random.nextInt(500))
        };

        for (Participant participant : participants) {
            for (Obstruction obstruction : obstructions) {
                if (!obstruction.move(participant)) {
                    break;
                }
            }
        }
    }
}
