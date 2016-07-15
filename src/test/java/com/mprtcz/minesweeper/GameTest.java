package com.mprtcz.minesweeper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Azet on 2016-07-14.
 */
public class GameTest {

    @Test
    public void checkParamCorrectnessTest_correct() {
        MinesweeperImpl minesweeperImpl = new MinesweeperImpl();
        String correctTestString = "1234\n4567\n8901";
        try {
            minesweeperImpl.checkParamCorectness(MinesweeperImpl.getMineFieldLines(correctTestString));
        } catch (IllegalArgumentException iaEx) {
            assertEquals(null, iaEx);
        }
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void checkParamCorrectnessTest_invalid() {
        MinesweeperImpl minesweeperImpl = new MinesweeperImpl();
        String incorrectTestString = "1234\n4567\n801";
        expectedException.expect(IllegalArgumentException.class);
        minesweeperImpl.checkParamCorectness(MinesweeperImpl.getMineFieldLines(incorrectTestString));
    }

    @Test
    public void checkParamCorrectnessTest_emptyString() {
        MinesweeperImpl minesweeperImpl = new MinesweeperImpl();
        String incorrectTestString = "";
        expectedException.expect(IllegalArgumentException.class);
        minesweeperImpl.checkParamCorectness(MinesweeperImpl.getMineFieldLines(incorrectTestString));
    }

    @Test
    public void generateStringFromCharArrayTest_validOneMine(){
        MinesweeperImpl minesweeperImpl = new MinesweeperImpl();
        String correctTestString = "1234\n5*78\n9012";
        String expectedOutput = "1110\n1*10\n1110";
        minesweeperImpl.setMineField(correctTestString);
        String output = minesweeperImpl.generateStringFromCharArray();
        assertEquals(output, expectedOutput);
    }

    @Test
    public void generateStringFromCharArrayTest_validTwoMines(){
        MinesweeperImpl minesweeperImpl = new MinesweeperImpl();
        String correctTestString = "1234\n5*7*\n9012";
        String expectedOutput = "1121\n1*2*\n1121";
        minesweeperImpl.setMineField(correctTestString);
        String output = minesweeperImpl.generateStringFromCharArray();
        assertEquals(output, expectedOutput);
    }

    @Test
    public void generateStringFromCharArrayTest_validMany(){
        MinesweeperImpl minesweeperImpl = new MinesweeperImpl();
        String correctTestString = "***.\n****\n****";
        String expectedOutput = "***3\n****\n****";
        minesweeperImpl.setMineField(correctTestString);
        String output = minesweeperImpl.generateStringFromCharArray();
        assertEquals(output, expectedOutput);
    }
}
