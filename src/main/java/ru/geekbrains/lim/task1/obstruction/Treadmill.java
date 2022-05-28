package ru.geekbrains.lim.task1.obstruction;

import ru.geekbrains.lim.task1.participant.Participant;

public class Treadmill implements Obstruction{
    private final int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean overcome(Participant participant) {
        return participant.run(distance);
    }
}
