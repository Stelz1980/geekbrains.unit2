package ru.geekbrains.lim.task1.participant;

public class Cat implements Participant {

    private final int maxRunDistance;
    private final int maxJumpHeight;

    public Cat(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        System.out.print("Кот пробежал ");
        return maxRunDistance >= distance;
    }

    @Override
    public boolean jump(int height) {
        System.out.print("Кот прыгнул ");
        return maxJumpHeight >= height;
    }
}
