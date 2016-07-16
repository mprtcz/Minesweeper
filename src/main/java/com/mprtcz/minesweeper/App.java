package com.mprtcz.minesweeper;

/**
 * Created by Azet on 2016-07-14.
 */
public class App {

    public static void main(String[] args) {
        String testString = ".*..\n.*..\n....";
        System.out.println("testString :\n" + testString);
        Minesweeper m = new MinesweeperImpl();
        m.setMineField(testString);
        System.out.println("Output field: \n" +m.getHintField() +"\n");


        String testString1 = ".*.....\n.**....\n......*";

        System.out.println("testString1 :\n" + testString1);
        m.setMineField(testString1);
        System.out.println("Output field: \n" +m.getHintField() +"\n");
    }
}
