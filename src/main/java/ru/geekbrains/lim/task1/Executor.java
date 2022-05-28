package ru.geekbrains.lim.task1;

import ru.geekbrains.lim.task1.obstruction.Obstruction;
import ru.geekbrains.lim.task1.obstruction.Treadmill;
import ru.geekbrains.lim.task1.obstruction.Wall;
import ru.geekbrains.lim.task1.participant.Cat;
import ru.geekbrains.lim.task1.participant.Human;
import ru.geekbrains.lim.task1.participant.Participant;
import ru.geekbrains.lim.task1.participant.Robot;

import java.util.Random;

public class Executor {

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
                if (!obstruction.overcome(participant)) {
                    break;
                }
            }
        }
    }
}
