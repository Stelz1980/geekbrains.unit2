package ru.geekbrains.lim.task1;

public class Cat implements Participant {

    private int maxRunDistance;
    private int maxJumpHeight;

    public Cat(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }

    public int getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public void setMaxJumpHeight(int maxJumpHeight) {
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
