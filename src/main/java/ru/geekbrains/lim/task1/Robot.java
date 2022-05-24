package ru.geekbrains.lim.task1;

public class Robot implements Participant {

    private final int maxRunDistance;
    private final int maxJumpHeight;

    public Robot(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        System.out.print("Робот пробежал ");
        return maxRunDistance >= distance;
    }

    @Override
    public boolean jump(int height) {
        System.out.print("Робот прыгнул ");
        return maxJumpHeight >= height;
    }
}
