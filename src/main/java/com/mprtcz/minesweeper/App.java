package com.mprtcz.minesweeper;

/**
 * Created by Azet on 2016-07-14.
 */
public class App {

    public static void main(String[] args) {
        String testString = ".*..\n.*..\n....";
        System.out.println("Input String:\n\n" + testString+"\n");
        Minesweeper m = new MinesweeperImpl();
        m.setMineField(testString);
        System.out.println("Output field:\n\n" +m.getHintField() +"\n");


        String testString1 = ".*.....\n.**....\n......*";

        System.out.println("Input String:\n\n" + testString1+"\n");
        m.setMineField(testString1);
        System.out.println("Output field:\n\n" +m.getHintField() +"\n");

        String testString2 = ".*..........\n.*.*........\n.....*...*.*\n........*...\n.*.......**.\n.*.......***\n";

        System.out.println("Input String:\n\n" + testString2+"\n");
        m.setMineField(testString2);
        System.out.println("Output field: \n\n" +m.getHintField() +"\n");
    }
}
