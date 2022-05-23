package ru.geekbrains.lim.task1;

public class Wall implements Obstruction{
    int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean move(Participant participant) {
        if (participant.jump(height)) {
            System.out.println("успешно "  + height + " метров");
            return true;
        } else {
            System.out.println("не успешно "  + height + " метров");
            return false;
        }
    }
}
