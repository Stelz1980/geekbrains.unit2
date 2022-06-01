package ru.geekbrains.lim.task1.obstruction;

import ru.geekbrains.lim.task1.participant.Participant;

public class Wall implements Obstruction {

    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Participant participant) {
        return participant.jump(height);
    }
}
