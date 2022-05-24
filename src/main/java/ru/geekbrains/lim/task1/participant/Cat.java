package ru.geekbrains.lim.task1.participant;

public class Cat implements Participant {


    private final String PARTICIPANT = "Кот";
    private final int maxRunDistance;
    private final int maxJumpHeight;

    public Cat(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        if (maxRunDistance >= distance) {
            System.out.println(PARTICIPANT + " пробежал успешно " + distance + " метров" );
            return true;

        } else {
            System.out.println(PARTICIPANT + " не смог пробежать " + distance + " метров");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (maxJumpHeight >= height) {
            System.out.println(PARTICIPANT + " прыгнул успешно на " + height + " метров");
            return true;
        } else {
            System.out.println(PARTICIPANT + " не смог прыгнуть успешно на " + height + " метров");
            return false;
        }
    }
}
