package com.mprtcz.minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by Azet on 2016-07-14.
 */
class MinesweeperImpl implements Minesweeper {
    private char[][] minesArray;

    public void setMineField(String mineField) throws IllegalArgumentException {

        checkParamCorrectness(getMineFieldLines(mineField));

        setMineFieldCharArray(getMineFieldLines(mineField));
    }

    public String getHintField() throws IllegalStateException {
        return generateStringFromCharArray();
    }

    static String[] getMineFieldLines(String mineField) {
        return mineField.split("\n");
    }

    void checkParamCorrectness(String[] mineFieldLines) {
        int lineSize = mineFieldLines[0].length();

        if (mineFieldLines.length < 1 || lineSize < 1) {
            throw new IllegalArgumentException("Arguments too short");
        }

        for (String s : mineFieldLines) {
            if (s.length() != lineSize) {
                throw new IllegalArgumentException("Number of arguments not equal for every line.");
            }
        }
    }

    private void setMineFieldCharArray(String[] mineFieldLines) {
        minesArray = new char[mineFieldLines.length][mineFieldLines[0].length()];

        for (int i = 0; i < mineFieldLines.length; i++) {
            String mineFieldLine = mineFieldLines[i];
            for (int j = 0; j < mineFieldLine.length(); j++) {
                minesArray[i][j] = mineFieldLine.charAt(j);
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(minesArray);
    }

    String generateStringFromCharArray() {
        StringJoiner stringJoiner = new StringJoiner("\n", "", "");

        if (minesArray == null) {
            throw new IllegalArgumentException("Mine field has not been initialized");
        }

        for (int i = 0; i < minesArray.length; i++) {
            StringBuilder stringRepresentation = new StringBuilder();
            char[] line = minesArray[i];
            for (int j = 0; j < line.length; j++) {
                stringRepresentation.append(calculateNeighbors(i, j));

            }
            stringJoiner.add(stringRepresentation);
        }
        return stringJoiner.toString();
    }

    private String calculateNeighbors(int x, int y) {

        if (minesArray[x][y] == '*') {
            return "*";
        }

        List<Coordinates> coordinatesList = getCoordinatesList(x, y);

        int amountOfAdjacentMines = 0;
        for (Coordinates c : coordinatesList) {
            try {
                if (minesArray[c.x][c.y] == '*') {
                    amountOfAdjacentMines++;
                }
            } catch (IndexOutOfBoundsException ignored) {}
        }
        return String.valueOf(amountOfAdjacentMines);
    }

    private class Coordinates {
        public final int x;
        public final int y;

        Coordinates(int i, int y) {
            this.x = i;
            this.y = y;
        }
    }

    private List<Coordinates> getCoordinatesList(int x, int y) {
        List<Coordinates> coordinatesList = new ArrayList<>();
        coordinatesList.add(new Coordinates(x - 1, y - 1));
        coordinatesList.add(new Coordinates(x, y - 1));
        coordinatesList.add(new Coordinates(x + 1, y - 1));
        coordinatesList.add(new Coordinates(x - 1, y));
        coordinatesList.add(new Coordinates(x + 1, y));
        coordinatesList.add(new Coordinates(x - 1, y + 1));
        coordinatesList.add(new Coordinates(x, y + 1));
        coordinatesList.add(new Coordinates(x + 1, y + 1));

        return coordinatesList;
    }
}
