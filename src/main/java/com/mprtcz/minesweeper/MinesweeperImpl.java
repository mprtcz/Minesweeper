package com.mprtcz.minesweeper;

import org.apache.commons.lang3.tuple.ImmutablePair;

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

        if (lineSize < 1) {
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
        StringJoiner stringJoiner = new StringJoiner("\n");

        if (minesArray == null) {
            throw new IllegalArgumentException("Mine field has not been initialized");
        }

        for (int i = 0; i < minesArray.length; i++) {
            StringBuilder stringRepresentation = new StringBuilder();
            char[] line = minesArray[i];
            for (int j = 0; j < line.length; j++) {
                stringRepresentation.append(calculateNeighbors(j, i));
            }
            stringJoiner.add(stringRepresentation);
        }
        return stringJoiner.toString();
    }

    private String calculateNeighbors(int x, int y) {
        if (minesArray[y][x] == '*') {
            return "*";
        }

        List<Coordinates> coordinatesList = getCoordinatesList(x, y);

        int amountOfAdjacentMines = 0;
        for (Coordinates c : coordinatesList) {
            try {
                if (minesArray[c.y][c.x] == '*') {
                    amountOfAdjacentMines++;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Stack trace for c : " +c);
                e.printStackTrace();
            }
        }
        return String.valueOf(amountOfAdjacentMines);
    }

    private class Coordinates {
        public final int x;
        public final int y;

        Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString(){
            return "x = " +x + " y = " + y +"\n";
        }
    }

    private List<Coordinates> getCoordinatesList(int x, int y) {
        List<Coordinates> coordinatesList = new ArrayList<>();

        List<ImmutablePair<Integer, Integer>> list = new ArrayList<>();

        list.add(new ImmutablePair<>(x - 1, y - 1));
        list.add(new ImmutablePair<>(x, y - 1));
        list.add(new ImmutablePair<>(x + 1, y - 1));
        list.add(new ImmutablePair<>(x - 1, y));
        list.add(new ImmutablePair<>(x + 1, y));
        list.add(new ImmutablePair<>(x - 1, y + 1));
        list.add(new ImmutablePair<>(x, y + 1));
        list.add(new ImmutablePair<>(x + 1, y + 1));

        for (ImmutablePair<Integer, Integer> pair : list) {
            if (checkIfCoordinatesAreWithinTheArray(pair.getLeft(), pair.getRight(), minesArray[0].length, minesArray.length)) {
                coordinatesList.add(new Coordinates(pair.getLeft(), pair.getRight()));
            }
        }

        return coordinatesList;
    }

    private static boolean checkIfCoordinatesAreWithinTheArray(int x, int y, int arrayWidth, int arrayHeight) {
        return !(x > arrayWidth - 1 || x < 0) && !(y > arrayHeight - 1 || y < 0);
    }
}