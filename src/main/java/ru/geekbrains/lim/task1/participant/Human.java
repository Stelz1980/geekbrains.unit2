package ru.geekbrains.lim.task1.participant;

public class Human implements Participant {

    private final int maxRunDistance;
    private final int maxJumpHeight;

    public Human(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        System.out.print("Человек пробежал ");
        return maxRunDistance >= distance;
     }

    @Override
    public boolean jump(int height) {
        System.out.print("Человек прыгнул ");
        return maxJumpHeight >= height;
    }
}
