package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class App {
    static String[] wordList = getText();
    static int length = wordList[0].length();
    static String prevLine = null;
    static String nextLine = null;
    static boolean isLeft = false;
    static boolean isRight = false;
    static char top = '\u0000';
    static char topleft = '\u0000';
    static char topright = '\u0000';
    static char left = '\u0000';
    static char right = '\u0000';
    static char bottom = '\u0000';
    static char bottomleft = '\u0000';
    static char bottomright = '\u0000';
    static int total = 0;
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < wordList.length; i++){
            checkVerticalBounds(i);
            for (int j = 0; j < length; j++){
                char currLetter = getLetter(i,j);
                checkHorizontalBounds(j, length);
                if (currLetter != 'X'){
                    continue;
                }
                else{
                    total += getSurroundingLetters(i, j);
                }
            }
        }
        System.out.println(total);
    }

    public static String[] getText(){
        String[] wordList = new String[140];
        try{
            File puzzle = new File("C:\\Users\\grran\\development\\AdventOfCode2024\\Day4\\CeresSearch\\src\\res\\puzzle.txt");
            Scanner sc = new Scanner(puzzle);
            for (int i = 0; i < wordList.length && sc.hasNextLine(); i++){
                wordList[i] = sc.nextLine();
            }
            sc.close();
            return wordList;
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
            return wordList;
        }


    }

    public static char getLetter(int index, int letterIndex){
        return wordList[index].charAt(letterIndex);
    }

    public static void checkVerticalBounds(int i){
        if (i > 0){
            prevLine = wordList[i - 1];
        }
        else{
            prevLine = null;
        }
        if (i < wordList.length - 1){
            nextLine = wordList[i + 1];
        }
        else {
            nextLine = null;
        }
    }

    public static void checkHorizontalBounds(int j, int length){
        if(j == 0){
            isLeft = true;
            isRight = false;
        }
        else if (j == length - 1){
            isRight = true;
            isLeft = false;
        }
        else {
            isLeft = false;
            isRight = false;
        }
        
    }

    public static int getSurroundingLetters(int i, int j){
        if (prevLine != null) {
            top = getLetter(i - 1, j);
            if (!isRight) {
                topright = getLetter(i - 1, j + 1);
            }
            if (!isLeft) {
                topleft = getLetter(i - 1, j - 1);
            }
        }
    
        if (nextLine != null) {
            bottom = getLetter(i + 1, j);
            if (!isRight) {
                bottomright = getLetter(i + 1, j + 1);
            }
            if (!isLeft) {
                bottomleft = getLetter(i + 1, j - 1);
            }
        }
    
        if (!isRight) {
            right = getLetter(i, j + 1);
        }
        if (!isLeft) {
            left = getLetter(i, j - 1);
        }

        return checkSurroundingLetters(i, j);
    }

    public static char getLetterWithDirection(int i, int j, int direction){
        char status = '\u0000';
        if (i < 0 || i >= wordList.length || j < 0 || j >= length) {
            return 'F';
        }
        switch(direction){
            case 0:
                if(!isLeft && prevLine != null){
                    topleft = getLetter(i-1,j-1);
                    status = topleft;
                } else{
                    status = 'F';
                }
                break;
            case 1:
                if(prevLine != null){
                    top = getLetter(i-1,j);
                    status = top;
                } else{
                    status = 'F';
                }
                    break;
            case 2:
                if(!isRight && prevLine != null){
                    topright = getLetter(i-1,j+1);
                    status = topright;
                } else{
                    status = 'F';
                }
                break;
            case 3:
                if(!isRight){
                    right = getLetter(i,j+1);
                    status = right;
                } else{
                    status = 'F';
                }
                    break;
            case 4:
                if(!isRight && nextLine != null){
                    bottomright = getLetter(i+1,j+1);
                    status = bottomright;
                } else{
                    status = 'F';
                }
                break;
            case 5:
                if(nextLine != null){
                    bottom = getLetter(i+1,j);
                    status = bottom;
                } else{
                    status = 'F';
                }
                break;
            case 6:
                if(!isLeft && nextLine != null){
                    bottomleft = getLetter(i+1,j-1);
                    status = bottomleft;
                } else{
                    status = 'F';
                }
                break;
            case 7:
                if(!isLeft){
                    left = getLetter(i, j-1);
                    status = left;
                } else{
                    status = 'F';
                }
                break;
            default:
                status = 'F';
                break;
        }
        return status;
    }

    public static int returnCurrentColumnWithDirection(int i, int direction){
        int row = i;
        switch(direction){
            case 0:
                row -= 1;
                break;
            case 1:
                break;
            case 2:
                row += 1;
                break;
            case 3:
                row += 1;
                break;
            case 4:
                row += 1;
                break;
            case 5:
                break;
            case 6:
                row -= 1;
                break;
            case 7:
                row -= 1;
                break;
            default:
                break;
        }
        return row;
    }

    public static int returnCurrentRowWithDirection(int i, int direction){
        int column = i;
        switch(direction){
            case 0:
                column -= 1;
                break;
            case 1:
                column -= 1;
                break;
            case 2:
                column -= 1;
                break;
            case 3:
                break;
            case 4:
                column += 1;
                break;
            case 5:
                column += 1;
                break;
            case 6:
                column += 1;
                break;
            case 7:
                break;
            default:
                break;
        }
        return column;
    }
    public static int checkSurroundingLetters(int index, int j){
        char[] neighbors = new char[8];
        //starts at topleft and goes clockwise
        neighbors[0] = topleft;
        neighbors[1] = top;
        neighbors[2] = topright;
        neighbors[3] = right;
        neighbors[4] = bottomright;
        neighbors[5] = bottom;
        neighbors[6] = bottomleft;
        neighbors[7] = left;

        int count = 0;

        for (int i = 0; i < neighbors.length; i++){
            
            //index is the initial line
            //if top, index - 1
            //if bottom index + 1
            //j is the initial letter
            //if left j - 1
            //if right j + 1
            if (neighbors[i] == 'M'){
                int row = returnCurrentRowWithDirection(index, i);
                int col = returnCurrentColumnWithDirection(j, i);
                if (row < 0 || col < 0 || col > 140 || row > 140){
                    continue;
                }
                checkVerticalBounds(row);
                checkHorizontalBounds(col, length);
                char status = getLetterWithDirection(row, col, i);
                if(status == 'F' || status != 'A'){
                    continue;
                }
                else{
                    row = returnCurrentRowWithDirection(row, i);
                    col = returnCurrentColumnWithDirection(col, i);
                    if (row < 0 || col < 0 || col > 140 || row > 140){
                        continue;
                    }
                    checkVerticalBounds(row);
                    checkHorizontalBounds(col, length);

                    status = getLetterWithDirection(row, col, i);
                    if(status == 'S'){
                        count++;
                    }
                }
            }
        }
        checkVerticalBounds(index);
        checkHorizontalBounds(j, length);
        return count;
    }
}
