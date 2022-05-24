package ru.geekbrains.lim.task1.obstruction;

import ru.geekbrains.lim.task1.participant.Participant;

public class Wall implements Obstruction {
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Participant participant) {
        if (participant.jump(height)) {
            System.out.println("успешно " + height + " метров");
            return true;
        } else {
            System.out.println("не успешно " + height + " метров");
            return false;
        }
    }
}
