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
        Game game = new Game();
        String correctTestString = "1234\n4567\n8901";
        try {
            game.checkParamCorectness(Game.getMineFieldLines(correctTestString));
        } catch (IllegalArgumentException iaEx) {
            assertEquals(null, iaEx);
        }
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void checkParamCorrectnessTest_invalid() {
        Game game = new Game();
        String incorrectTestString = "1234\n4567\n801";
        expectedException.expect(IllegalArgumentException.class);
        game.checkParamCorectness(Game.getMineFieldLines(incorrectTestString));
    }

    @Test
    public void checkParamCorrectnessTest_emptyString() {
        Game game = new Game();
        String incorrectTestString = "";
        expectedException.expect(IllegalArgumentException.class);
        game.checkParamCorectness(Game.getMineFieldLines(incorrectTestString));
    }
}
