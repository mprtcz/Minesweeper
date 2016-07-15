package com.mprtcz.minesweeper;

/**
 * Created by Azet on 2016-07-14.
 */
public class App {

    public static void main(String[] args) {
        String testString = "1*34\n4*67\n8901";
        MinesweeperImpl m = new MinesweeperImpl();
        m.setMineField(testString);
        System.out.println(m.toString());
        m.getHintField();
        System.out.println(m.getHintField());


        String testString1 = "1*34123\n4**7123\n890112*";
        m.setMineField(testString1);
        System.out.println(m.getHintField());
    }

}
