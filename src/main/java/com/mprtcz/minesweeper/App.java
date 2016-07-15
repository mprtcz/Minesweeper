package com.mprtcz.minesweeper;

/**
 * Created by Azet on 2016-07-14.
 */
public class App {

    public static void main(String[] args) {
        String testString = ".*..\n.*..\n....";
        MinesweeperImpl m = new MinesweeperImpl();
        m.setMineField(testString);
        System.out.println(m.toString());
        m.getHintField();
        System.out.println(m.getHintField());


        String testString1 = ".*.....\n.**....\n......*";
        m.setMineField(testString1);
        System.out.println(m.getHintField());
    }

}
