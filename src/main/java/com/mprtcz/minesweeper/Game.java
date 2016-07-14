package com.mprtcz.minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Azet on 2016-07-14.
 */
public class Game {
    private char[][] minesArray;

    public void setMineField(String mineField) throws IllegalArgumentException {

        checkParamCorectness(getMineFieldLines(mineField));

        setMineFieldCharArray(getMineFieldLines(mineField));

    }

    public String getHintField() throws IllegalStateException {
        return generateStringFromCharArray();
    }

    static String[] getMineFieldLines(String mineField){
        return mineField.split("\n");
    }

    void checkParamCorectness(String[] mineFieldLines){
        int lineSize = mineFieldLines[0].length();

        if(mineFieldLines.length<2 || lineSize < 2){
            throw new IllegalArgumentException("Arguments too short");
        }

        for (String s : mineFieldLines) {
            if (s.length() != lineSize) {
                throw new IllegalArgumentException("Number of arguments not equal for every line.");
            }
        }
    }

    private void setMineFieldCharArray(String[] mineFieldLines){
        minesArray = new char[mineFieldLines.length][mineFieldLines[0].length()];

        for (int i = 0; i < mineFieldLines.length; i++) {
            String mineFieldLine = mineFieldLines[i];
            for (int j = 0; j < mineFieldLine.length(); j++) {
                minesArray[i][j] = mineFieldLine.charAt(j);
            }
        }
    }

    @Override
    public String toString(){
        return Arrays.deepToString(minesArray);
    }

    String generateStringFromCharArray(){
        StringBuilder stringRepresentation = new StringBuilder();

        for (int i = 0; i < minesArray.length; i++) {
            char[] line = minesArray[i];
            for (int j = 0; j < line.length; j++) {
                stringRepresentation.append(calcuateNeighbors(i, j));
            }
            stringRepresentation.append("\n");
        }

        return stringRepresentation.toString();
    }

    String calcuateNeighbors(int i, int j){

        if(minesArray[i][j]=='*'){
            return "*";
        }

        List<Coordinates> coordinatesList = getCoordinatesList(i, j);

        int amountOfNieghboringMines =0;
        for(Coordinates c : coordinatesList){
            try{
            if(minesArray[c.getI()][c.getJ()]=='*'){
                amountOfNieghboringMines++;
            }
            } catch (IndexOutOfBoundsException ignored){}
        }

        return String.valueOf(amountOfNieghboringMines);
    }

    private class Coordinates{
        int i;
        int j;

        public Coordinates(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    List<Coordinates> getCoordinatesList(int i, int j){
        List<Coordinates> coordinatesList = new ArrayList<>();
        coordinatesList.add(new Coordinates(i-1, j-1));
        coordinatesList.add(new Coordinates(i, j-1));
        coordinatesList.add(new Coordinates(i+1, j-1));
        coordinatesList.add(new Coordinates(i-1, j));
        coordinatesList.add(new Coordinates(i+1, j));
        coordinatesList.add(new Coordinates(i-1, j+1));
        coordinatesList.add(new Coordinates(i, j+1));
        coordinatesList.add(new Coordinates(i+1, j+1));

        return coordinatesList;
    }
}
