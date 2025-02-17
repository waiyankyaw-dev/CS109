package model;

import view.GameoverFrame;
import view.VictoryFrame;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class GridNumber {
    JFrame f;
    private final int X_COUNT;   //row
    private final int Y_COUNT;   //column

    private int[][] numbers;     //boards  4*4

    private int YourScore = 0;
    private int YourHighScore = 0;
    //private int[] WinScores = {16, 512, 1024, 2048, 4096};
    private int score = 0;
    private int redoScore = 0;
    private int higherScore = 0;

    long startTime = 0;
    long endTime = 0;



    long elapsedTime = 0;

    long hours = 0;
    long minutes = 0;
    long seconds = 0;

    private int[][] tempNumbers;
    //    private boolean[] LOSS_CHECK= {true,true,true};
    private boolean[] LOSS_CHECK_inside;
    private boolean[] LOSS_CHECK;

    private boolean Lost = false;

    private boolean canMove = false;

    private boolean[]slideSide;
    boolean sliiiiide=true;

//    private boolean Lost = false;

    private int winScore;
    private int steps;


    static Random random = new Random();

    private String username;
    private int size;

    private boolean gameOver;
    private boolean gameWin;

    private int Difficulty = 1;


    public GridNumber(int xCount, int yCount, String username, int size, int winScore, int Difficulty) {
        this.Difficulty = Difficulty;

        System.out.println("DIFFICULTY from constructor" + Difficulty);
        System.out.println(winScore + "from gridnumber");
        System.out.println(username + "from gridnumber");
        System.out.println(size + "from gridnumber");
        this.winScore = winScore;
//        System.out.println(winScore);
//        System.out.println(size);
        this.size = size;
        this.username = username;
        System.out.println(username);
        this.X_COUNT = xCount;
        this.Y_COUNT = yCount;
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];
        this.tempNumbers = new int[this.X_COUNT][this.Y_COUNT];

        System.out.println(xCount + "xcount");
        this.LOSS_CHECK_inside = new boolean[xCount];
        this.LOSS_CHECK = new boolean[xCount];
        this.gameOver = false;
        this.gameWin = false;
        this.slideSide = new boolean[xCount];

        //call method
//        this.initialNumbers();   //need to create a spawn

//        if(!username.isEmpty()){
//            this.load(username, username, size); // the first username is filename, the second one is folder name, automatic load
//        }

        if(username.isEmpty()){
            this.initialNumbers();

        }

        if(this.Difficulty == 2){
            fakeBlocks();
        }
//        GameOver();
        this.steps = 0;

        System.out.println("Difficulty from girdnumber" + Difficulty);


    }

    //
    public void randomNextTile() {
        int i = random.nextInt(X_COUNT);
        int j = random.nextInt(X_COUNT);
        while (numbers[i][j] != 0) {
            i = random.nextInt(X_COUNT);
            j = random.nextInt(X_COUNT);
        }
        numbers[i][j] = random.nextInt(10) < 9 ? 2 : 4; // 90% chance to get 2
        System.out.println("spawn created");
    }

//    public void fakeInitialNumbers() {
//
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = 0; j < numbers[i].length; j++) {
//                int powerNumber = random.nextInt(4);
//                if(powerNumber == 0){
//                    powerNumber++;
//                }
//                numbers[i][j] = random.nextInt(2) == 0 ? (int) Math.pow(2, powerNumber) : 0;
//            }
//
//        }
//    }


    public void fakeBlocks(int number) {
        int i = random.nextInt(X_COUNT);
        int j = random.nextInt(X_COUNT);
        while (numbers[i][j] != 0) {
            i = random.nextInt(X_COUNT);
            j = random.nextInt(X_COUNT);
        }
        numbers[i][j] = number; // 90% chance to get 2
        System.out.println("spawn created");
    }

    public void fakeBlocks(){
        fakeBlocks(6);
        fakeBlocks(12);
        fakeBlocks(14);
        fakeBlocks(18);
        fakeBlocks(20);


    }

    public void initialNumbers() {
//        startTime = System.currentTimeMillis();
        int randFirstRow = random.nextInt(X_COUNT);
        int randFirstCol = random.nextInt(X_COUNT);
        int randSecondRow = random.nextInt(X_COUNT);
        int randSecondCol = random.nextInt(X_COUNT);

        //second location must not equal to first location
        while (randSecondRow == randFirstRow && randSecondCol == randFirstCol) {
            randSecondRow = random.nextInt(X_COUNT);
            randSecondCol = random.nextInt(X_COUNT);
        }

        //create random two tiles(2 and 4) in first and second location
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if (randFirstRow == i && randFirstCol == j) {
                    numbers[i][j] = 2;
                }
                if (randSecondRow == i && randSecondCol == j) {
                    numbers[i][j] = 4;
                }
            }

        }
    }

    //restart
    public void restart() {
        this.steps = 0;
        this.score = 0;
        System.out.println("Restart run");
        for (int[] number : numbers) {
            Arrays.fill(number, 0);
        }
        initialNumbers();
//        fakeBlocks();
//        fakeInitialNumbers();
        printNumber();
        if(Difficulty == 2) {
            fakeBlocks();
        }
    }


    public void moveRight() {
        storeTempArray();
        redoScore = score;
        for(int i = 0; i<numbers.length; i++){
            slideSide[i] = false;
        }

        //for sorting/colliding
        for (int i = 0; i < numbers.length; i++) {
            // checkSTUCK();

            int count0 =0;
            int countN =0;

            for(int j=0; j<numbers.length; j++){
                int count4 =0;
                for(int n=0; n<numbers.length; n++){
                    if(numbers[i][n]==0){
                        count4++;
                    }
                }System.out.println(count4);
                if(count4==numbers.length){slideSide[i] = false;break;}
                if(numbers[i][numbers.length-1]==0){
                    slideSide[i]=true;
                    count0 =0;
                    countN =0;
                    System.out.println("can slide as end is 0");
                    break;

                }
                if(j+1==numbers.length){break;}/**checks for out of bounds*/
                if(numbers[i][j]==0){
                    count0++;
                    System.out.println("here is 0   "+ count0);
                }else if(numbers[i][j]>0){
                    if(numbers[i][j]==numbers[i][j+1]){
                        slideSide[i]=true;
                        System.out.println("check fusion");
                    }
                    if(numbers[i][j+1]==0){
                        countN=0;
                        slideSide[i]=true;


                    }else{countN++;}
                    if(j==numbers.length-1&&countN>0){
                        System.out.println("Ncount    "+countN);
                        slideSide[i]=false;
                        break;
                    }
                }

            }
            for(int j =numbers.length-1; j > 0; j--) {
                for (int k = numbers.length - 1; k >= 0; k--) {//moving 0s to the left before starting to add the numbers

                    if (k - 1 < 0) {//stacking
                        // System.out.println('1');
                        break;
                    }
                    if (numbers[i][k] == 0) {
                        numbers[i][k] += numbers[i][k - 1];
                        numbers[i][k - 1] = 0;
                        // sliiiiide=true;
                    }
                }//end for k
            }//end for j



            System.out.println(slideSide[i]);



            printNumber();
        } //end for i

        //for fusing
        for (int i = 0; i < numbers.length; i++) {
            int TempScore = 0;
            boolean slide = true;
            for (int j = numbers.length - 1; j >= 0; j--) {
                if (j - 1 < 0) {//fusing

                    break;
                } else if (numbers[i][j - 1] == numbers[i][j]) {//&& numbers[i][k]!=0 && numbers[i][k-1]!=0
                    int highCombinedNumber = numbers[i][j] += numbers[i][j - 1];
                    numbers[i][j - 1] = 0;
                    score += highCombinedNumber;
                    if(score>higherScore) {
                        higherScore = score;
                    }
                    // if(TempScore > YourScore){YourScore =TempScore;}
                }//else{slide=false;}
                //changing temp score
                if(TempScore < numbers[i][j]){
                    TempScore =numbers[i][j];
                    System.out.println("TEMPSCORE-----------"+TempScore+"-----------");
                }
            }//end for j
            // if(slide==false){slideSideFUSE[i]=false;}

            if(TempScore >= YourScore){YourScore =TempScore;}//changing the player score
            if(YourScore >= YourHighScore){YourHighScore =YourScore;}//changing the players high score


            //stacking after fusing
            for (int j = numbers.length - 1; j > 0; j--) {
                if (numbers[i][j] == 0) {
                    numbers[i][j] += numbers[i][j - 1];
                    numbers[i][j - 1] = 0;}
                //endif
            }//end for j
        }//end for i



        int countr=0;
        for(int i =0; i< slideSide.length;i++){
            if(slideSide[i]==true){
                System.out.println(slideSide[i]);
                countr++;
            }
        }//end for

        if(countr>0){
            System.out.println(countr);
            randomNextTile();
            steps++;
            canMove = true;
        }else {
            System.out.println('0');
        }
        System.out.println("WaiScore: " + score + "-----------");

        GameWin();
        GameOver();

    }//end method
    public void moveLeft() {
        storeTempArray();
        redoScore = score;
        for(int i = 0; i<numbers.length; i++){
            slideSide[i] = false;
        }
        //sorting
        for (int i = 0; i < numbers.length; i++) {
            int count0 =0;
            int countN =0;
            for(int j=numbers.length-1; j>=0; j--){

                int count4 =0;
                for(int n=0; n<numbers.length; n++){
                    if(numbers[i][n]==0){
                        count4++;
                    }
                }System.out.println(count4);
                if(count4==numbers.length){slideSide[i] = false;break;}

                if(numbers[i][0]==0){
                    slideSide[i]=true;
                    System.out.println("can slide as end is 0");
                    break;

                }
                if(j-1<0){break;}/**checks for out of bounds*/
                if(numbers[i][j]==0){
                    count0++;
                    System.out.println("here is 0   "+ count0);
                }else if(numbers[i][j]>0){
                    if(numbers[i][j-1]==numbers[i][j]){
                        slideSide[i]=true;
                        System.out.println("check fusion");
                    }
                    if(numbers[i][j-1]==0){
                        countN=0;
                        slideSide[i]=true;

                    }else{countN++;}
                    if(j==0&&countN>0){
                        System.out.println("Ncount    "+countN);
                        slideSide[i]=false;
                        break;
                    }
                }

            }

            for (int j =0 ; j<numbers.length; j++) {

                for (int k =0 ; k <numbers.length; k++) {//moving 0s to the left before starting to add the numbers
                    if (k + 1 == numbers.length) {
                        break;
                    } else if(numbers[i][k]==0) {
                        numbers[i][k] += numbers[i][k+1];
                        numbers[i][k+1] = 0;
                    }//end if
                }//end for k

            }//end for j

            System.out.println(slideSide[i]);
            printNumber();
        }//end for i

        //fusing
        for (int i = 0; i < numbers.length; i++) {
            int TempScore =0;
            for (int k =0 ; k <numbers.length; k++) {
                if(k +1== numbers.length){
                    break;
                }
                else if (numbers[i][k + 1] == numbers[i][k]) {
                    int highCombinedNumber = numbers[i][k] += numbers[i][k + 1];
                    numbers[i][k + 1] = 0;//end for i
                    score += highCombinedNumber;
                    if(score>higherScore) {
                        higherScore = score;
                    }
                }
                //Setting the temp score
                if(TempScore < numbers[i][k]){
                    TempScore =numbers[i][k];
                    System.out.println("TEMPSCORE-----------"+TempScore+"-----------");
                }
            }//end for k

            //changing the player score
            if(TempScore >= YourScore){YourScore =TempScore;}
            if(YourScore >= YourHighScore){YourHighScore =YourScore;}//changing the players high score

            for (int j =0 ; j<numbers.length; j++) {
                if(j+1==numbers.length ){break;}
                if (numbers[i][j] == 0) {
                    numbers[i][j] += numbers[i][j + 1];
                    numbers[i][j + 1] = 0;
                }//end if
            }//end for k
        }//end for i

        int countr=0;
        for(int i =0; i< slideSide.length;i++){
            if(slideSide[i]==true){
                System.out.println(slideSide[i]);
                countr++;
            }
        }//end for

        if(countr>0){
            System.out.println(countr);
            randomNextTile();
            steps++;
            canMove = true;
        }else {
            System.out.println('0');
        }

        System.out.println("WaiScore: " + score + "-----------");

        GameWin();
        GameOver();

        //to check if lost

    }//end method

    public void moveUp() {
        storeTempArray();
        redoScore = score;
        for(int i = 0; i<numbers.length; i++){
            slideSide[i] = false;
        }


        //sorting
        for (int i = 0; i < numbers.length; i++) {
            int count0 =0;
            int countN =0;
            for(int j=numbers.length-1; j>=0; j--){

                int count4 =0;
                for(int n=0; n<numbers.length; n++){
                    if(numbers[n][i]==0){
                        count4++;
                    }
                }System.out.println(count4);
                if(count4==numbers.length){slideSide[i] = false;break;}

                if(numbers[0][i]==0){
                    slideSide[i]=true;
                    System.out.println("can slide as end is 0");
                    break;
                }

                if(j-1<0){break;}/**checks for out of bounds*/

                if(numbers[j][i]==0){
                    count0++;
                    System.out.println("here is 0   "+ count0);
                }else if(numbers[j][i]>0){
                    if(numbers[j][i]==numbers[j-1][i]){
                        slideSide[i]=true;
                        System.out.println("check fusion");
                    }
                    if(numbers[j-1][i]==0){
                        slideSide[i]=true;

                    }
                    if(j==0&&countN>0){
                        slideSide[i]=false;
                        break;
                    }
                }
            }
            for (int j=0; j < numbers.length; j++) {

                for (int k = 0; k < numbers.length; k++) {//for loop for shifting 0s
                    if (k + 1 == numbers.length) {
                        break;
                    } else if (numbers[k][i] == 0) {
                        numbers[k][i] += numbers[k + 1][i];
                        numbers[k + 1][i] = 0;
                    }//end if//end if
                }//end for k
            }
        }
//fusing
        for (int i = 0; i < numbers.length; i++) {
            int TempScore =0;
            for (int j=0; j < numbers.length; j++) {
                //for loop for adding the numbers or not
                if (j +1  == numbers.length) {
                    break;
                }else if(numbers[j][i] == numbers[j+1][i]){
                    int highCombinedNumber = numbers[j][i] += numbers[j+1][i];
                    numbers[j+1][i] = 0;
                    score += highCombinedNumber;
                    if(score>higherScore) {
                        higherScore = score;
                    }
                }

                //Setting the temp score
                if(TempScore <= numbers[j][i]){
                    TempScore =numbers[j][i];
                    System.out.println("TEMPSCORE-----------"+TempScore+"-----------");
                }//end if
            }//end for j

            if(TempScore >= YourScore){YourScore =TempScore;}
            if(YourScore >= YourHighScore){YourHighScore =YourScore;}//changing the players high score

            for (int j=0; j < numbers.length; j++) {
                for (int k = 0; k < numbers.length; k++) {//for loop for shifting 0s
                    if (k + 1 == numbers.length) {
                        break;
                    } else if (numbers[k][i] == 0) {
                        numbers[k][i] += numbers[k + 1][i];
                        numbers[k + 1][i] = 0;
                    }//end if//end if
                }//end for k
            }

            System.out.println(slideSide[i]);
            printNumber();
        }//end for i

        //to check if lost
        int countr=0;
        for(int i =0; i< slideSide.length;i++){
            if(slideSide[i]==true){
                System.out.println(slideSide[i]);
                countr++;
            }
        }//end for

        if(countr>0){
            System.out.println(countr);
            randomNextTile();
            steps++;
            canMove = true;
        }else {
            System.out.println('0');
        }

        System.out.println("WaiScore: " + score + "-----------");

        GameWin();
        GameOver();
    }//end method

    public void moveDown() {
        storeTempArray();
        redoScore = score;
        for(int i = 0; i<numbers.length; i++){
            slideSide[i] = false;
        }

        for (int i = 0; i < numbers.length; i++) {

            int count0 =0;
            int countN =0;

            for(int j=0; j<numbers.length; j++){
                int count4 =0;
                for(int n=0; n<numbers.length; n++){
                    if(numbers[n][i]==0){
                        count4++;
                    }
                }System.out.println(count4);
                if(count4==numbers.length){slideSide[i] = false;break;}
                if(numbers[numbers.length-1][i]==0){
                    slideSide[i]=true;

                    System.out.println("can slide as end is 0");
                    break;

                }
                if(j+1==numbers.length){break;}/**checks for out of bounds*/
                if(numbers[j][i]==0){
                    count0++;
                    System.out.println("here is 0   "+ count0);
                }else if(numbers[j][i]>0){
                    if(numbers[j+1][i]==numbers[j][i]){
                        slideSide[i]=true;
                        System.out.println("check fusion");
                    }
                    if(numbers[j+1][i]==0){
                        countN=0;
                        slideSide[i]=true;


                    }else{countN++;}
                    if(j==numbers.length-1&&countN>0){
                        System.out.println("Ncount    "+countN);
                        slideSide[i]=false;
                        break;
                    }
                }

            }
            for (int j = numbers.length - 1; j >= 0; j--) {
                for (int k = numbers.length - 1; k >= 0; k--) {
                    //for loop for adding the numbers or not
                    if (k - 1 < 0) {
                        break;
                    } else if (numbers[k][i] == 0) {
                        numbers[k][i] += numbers[k - 1][i];
                        numbers[k - 1][i] = 0;
                    }//end if//end if
                }//end for k

            }////end for j
        }//end for i

        //fusing
        for (int i = 0; i < numbers.length; i++) {
            int TempScore = 0;
            for (int j = numbers.length - 1; j >= 0; j--) {
                if (j - 1 <0) {
                    break;
                } else if(numbers[j][i] == numbers[j-1][i]){
                    int highCombinedNumber = numbers[j][i] += numbers[j-1][i];
                    numbers[j-1][i] = 0;
                    score += highCombinedNumber;
                    if(score>higherScore) {
                        higherScore = score;
                    }
                }//end if//end if//end if

                //Setting the temp score
                if(TempScore <= numbers[j][i]){
                    TempScore =numbers[j][i];
                    System.out.println("TEMPSCORE-----------"+TempScore+"-----------");}
            }//end for j

            //setting up YOUR SCORE
            if(TempScore >= YourScore){YourScore =TempScore;}
            if(YourScore >= YourHighScore){YourHighScore =YourScore;}//changing the players high score

            for (int j = numbers.length - 1; j >= 0; j--) {
                if(j-1<0){break;}
                if (numbers[j][i] == 0) {
                    numbers[j][i] += numbers[j - 1][i];
                    numbers[j - 1][i] = 0;}//end if//end if
            }//end for j

            System.out.println(slideSide[i]);
            printNumber();
        }//end for i

        //to check if lost
        int countr=0;
        for(int i =0; i< slideSide.length;i++){
            if(slideSide[i]==true){
                System.out.println(slideSide[i]);
                countr++;
            }
        }//end for

        if(countr>0){
            System.out.println(countr);
            randomNextTile();
            steps++;
            canMove = true;
        }else {
            System.out.println('0');
        }

        System.out.println("WaiScore: " + score + "-----------");

        GameWin();
        GameOver();
    }//enf method

    //todo: finish the method of four direction moving.
//    public void moveRight() {
//        storeTempArray();
//        redoScore = score;
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = numbers.length - 1; j > 0; j--) {
//                for (int k = numbers.length - 1; k >= 0; k--) {//moving 0s to the left before starting to add the numbers
//                    if (k - 1 < 0) {//stacking
//                        // System.out.println('1');
//                        break;
//                    }
//                    if (numbers[i][k] == 0) {
//                        numbers[i][k] += numbers[i][k - 1];
//                        numbers[i][k - 1] = 0;
//                    }
//                }//end for k
//            }//end for j
//
//        } //end for i
//
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = numbers.length - 1; j > 0; j--) {
//                if (j - 1 < 0) {//fusing
//                    // System.out.println('1');
//                    break;
//                } else if (numbers[i][j - 1] == numbers[i][j]) {//&& numbers[i][k]!=0 && numbers[i][k-1]!=0
//                    int highCombinedNumber = numbers[i][j] += numbers[i][j - 1];
//                    numbers[i][j - 1] = 0;
//                    score += highCombinedNumber;
//                    // System.out.println('3');}*/
//                }
//
//
//            }//end for j
//            for (int j = numbers.length - 1; j > 0; j--) {
//                if (numbers[i][j] == 0) {
//                    numbers[i][j] += numbers[i][j - 1];
//                    numbers[i][j - 1] = 0;
//                }
//            }
//        }//end for i
//        System.out.println("WaiScore: " + score + "-----------");
//
//        GameWin();
//        GameOver();
//
//        if (checkBoardFull()) {
//            randomNextTile();
//        }
//        printNumber();
//    }//end method
//
//    public void moveLeft() {
//        storeTempArray();
//        redoScore = score;
//        //sorting
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = 0; j < numbers.length; j++) {
//
//                for (int k = 0; k < numbers.length; k++) {//moving 0s to the left before starting to add the numbers
//                    if (k + 1 == numbers.length) {
//                        break;
//                    } else if (numbers[i][k] == 0) {
//                        numbers[i][k] += numbers[i][k + 1];
//                        numbers[i][k + 1] = 0;
//                    }//end if
//                }//end for k
//
//                //adding the numbers
//            }//end for j
//
//        }//end for i
//
//        //fusing
//        for (int i = 0; i < numbers.length; i++) {
//            for (int k = 0; k < numbers.length; k++) {
//                if (k + 1 == numbers.length) {
//                    break;
//                } else if (numbers[i][k + 1] == numbers[i][k]) {
//                    int highCombinedNumber = numbers[i][k] += numbers[i][k + 1];
//                    numbers[i][k + 1] = 0;//end for i
//                    score += highCombinedNumber;
//                }
//            }//end for k
//            for (int j = 0; j < numbers.length; j++) {
//                if (j + 1 == numbers.length) {
//                    break;
//                }
//                if (numbers[i][j] == 0) {
//                    numbers[i][j] += numbers[i][j + 1];
//                    numbers[i][j + 1] = 0;
//                }//end if
//            }//end for k
//
//        }//end for i
//
//        System.out.println("WaiScore: " + score + "-----------");
//
//        GameWin();
//        GameOver();
//
//        if (checkBoardFull()) {
//            randomNextTile();
//        }
//        printNumber();
//    }//end method
//
//    public void moveUp() {
//        storeTempArray();
//        redoScore = score;
//        //sorting
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = 0; j < numbers.length; j++) {
//
//                for (int k = 0; k < numbers.length; k++) {//for loop for shifting 0s
//                    if (k + 1 == numbers.length) {
//                        break;
//                    } else if (numbers[k][i] == 0) {
//                        numbers[k][i] += numbers[k + 1][i];
//                        numbers[k + 1][i] = 0;
//                    }//end if
//                }//end for k
//            }
//        }
////fusing
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = 0; j < numbers.length; j++) {
//                //for loop for adding the numbers or not
//                if (j + 1 == numbers.length) {
//                    break;
//                } else if (numbers[j][i] == numbers[j + 1][i]) {
//                    int highCombinedNumber = numbers[j][i] += numbers[j + 1][i];
//                    numbers[j + 1][i] = 0;
//                    score += highCombinedNumber;
//                }
//            }//end for j
//            for (int j = 0; j < numbers.length; j++) {
//                if (j + 1 == numbers.length) {
//                    break;
//                }
//                if (numbers[j][i] == 0) {
//                    numbers[j][i] += numbers[j + 1][i];
//                    numbers[j + 1][i] = 0;
//                }//end if
//            }
//            for (int j = 0; j < numbers.length; j++) {
//
//                for (int k = 0; k < numbers.length; k++) {//for loop for shifting 0s
//                    if (k + 1 == numbers.length) {
//                        break;
//                    } else if (numbers[k][i] == 0) {
//                        numbers[k][i] += numbers[k + 1][i];
//                        numbers[k + 1][i] = 0;
//                    }//end if
//                }//end for k
//            }
//
//        }//end for i
//
//        System.out.println("WaiScore: " + score + "-----------");
//
//        GameWin();
//        GameOver();
//
//        if (checkBoardFull()) {
//            randomNextTile();
//        }
//        printNumber();
//    }//end method
//
//    public void moveDown() {
//        storeTempArray();
//        redoScore = score;
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = numbers.length - 1; j >= 0; j--) {
//                for (int k = numbers.length - 1; k >= 0; k--) {
//                    //for loop for adding the numbers or not
//                    if (k - 1 < 0) {
//                        break;
//                    } else if (numbers[k][i] == 0) {
//                        numbers[k][i] += numbers[k - 1][i];
//                        numbers[k - 1][i] = 0;
//                    }
//                }//end for k
//
//            }////end for j
//        }//end for i
//
//        //fusing
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = numbers.length - 1; j >= 0; j--) {
//                if (j - 1 < 0) {
//                    break;
//                } else if (numbers[j][i] == numbers[j - 1][i]) {
//                    int highCombinedNumber = numbers[j][i] += numbers[j - 1][i];
//                    numbers[j - 1][i] = 0;
//                    score += highCombinedNumber;
//                }//end if
//            }//end for j
//            for (int j = numbers.length - 1; j >= 0; j--) {
//                if (j - 1 < 0) {
//                    break;
//                }
//                if (numbers[j][i] == 0) {
//                    numbers[j][i] += numbers[j - 1][i];
//                    numbers[j - 1][i] = 0;
//                }
//            }
//
//        }//end for i
//
//
//        System.out.println("WaiScore: " + score + "-----------");
//
//        GameWin();
//        GameOver();
//
//        if (checkBoardFull()) {
//            randomNextTile();
//        }
//        printNumber();
//    }


    public boolean checkBoardFull() {
        for (int i = 0; i < tempNumbers.length; i++) {
            for (int j = 0; j < tempNumbers.length; j++) {
                if (numbers[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public int getNumber(int i, int j) {
        return numbers[i][j];
    }

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }

    public void printTempNumber() {
        for (int[] line : tempNumbers) {
            System.out.println(Arrays.toString(line));
        }
    }

    public void redo() {
        if(this.steps >0 && canMove){
            this.steps--;
            score = redoScore;
            canMove = false;
            for (int i = 0; i < tempNumbers.length; i++) {
                for (int j = 0; j < tempNumbers.length; j++) {
                    numbers[i][j] = tempNumbers[i][j];
                }
            }
            printNumber();
        }

    }

    //saveFile
    public void save(String filepath, long elapsedTime) {
        if(username.isEmpty()){   //for guest
            System.out.println("empty" + username);
        }else{
//            this.endTime = System.currentTimeMillis();
            this.elapsedTime += elapsedTime;

            hours += (elapsedTime / 3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) %60;
            System.out.println("elapsed time" + elapsedTime+ "      "+minutes +"minutes "+ seconds + "seconds");

//        if(score> higherScore){
//            higherScore = score;
//        }else{
//
//        }

            File f1 = new File("library"+"//"+username);
            boolean bool = f1.mkdir();
//            if(bool){
//                System.out.println("Folder is created successfully");
//            }else{
//                System.out.println("Error Found!");
//            }

            File f2 = new File("library"+"//"+username+"//"+String.valueOf(size));
            boolean bool2 = f2.mkdir();
//            if(bool2){
//                System.out.println("Folder is created successfully");
//            }else{
//                System.out.println("Error Found!");
//            }

            if(score > higherScore) {
                higherScore = score;
                System.out.println("higherScore: " + higherScore);
            }

            try {
                FileWriter writer = new FileWriter("library"+"\\"+username+"\\"+ size+ "\\" +filepath+".txt");
                writer.write(steps +"\n");
                writer.write(score +"\n");
                writer.write(higherScore +"\n");
                writer.write( minutes+"\n");
                writer.write( seconds+"\n");

                for (int i = 0; i < numbers.length; i++) {
                    for (int j = 0; j < numbers.length; j++) {
                        if (j == numbers.length - 1)
                            writer.write(numbers[i][j] + "\n");
                        else writer.write(numbers[i][j] + "-");
                    }
                }
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


    }


    //automatic Load
    public void autoLoad(String filepath,String username,int size) {
        System.out.println(username + size + "from load");
        System.out.println(filepath + "filepathfromload");
        try {
            FileReader fr = new FileReader("library"+"\\"+username+"\\"+ size+"\\" +filepath + ".txt");
            BufferedReader br = new BufferedReader(fr); //read line by line
            steps = Integer.parseInt(br.readLine());
            score = Integer.parseInt(br.readLine());
            higherScore = Integer.parseInt(br.readLine());
            minutes = Integer.parseInt(br.readLine());
            seconds = Integer.parseInt(br.readLine());
            System.out.println(minutes + "from load");
            System.out.println(seconds+"from load");
            for (int i = 0; i < numbers.length; i++) {
                String[] arrayLine = br.readLine().split("-");
//                System.out.println(arrayLine[i]);
                if(arrayLine.length > numbers.length){
                    throw new ArrayIndexOutOfBoundsException();
                }
                for (int j = 0; j < numbers.length; j++) {
//                        System.out.println(Integer.parseInt(arrayLine[i]));
                    if(Integer.parseInt(arrayLine[j]) %2 !=0){
                        throw new NumberFormatException();
                    }
                    numbers[i][j] = Integer.parseInt(arrayLine[j]);
                }
            }
            System.out.println("minutes: " + minutes + "  seconds: " + seconds );
            fr.close();
            printNumber();
            System.out.println("DIFFIULTY"+ Difficulty);

        }
        catch (FileNotFoundException fileNotFoundException) {
//            Jframe fram = new JFrame();
//            JOptionPane.showMessageDialog(this,"file not found");
//            System.out.println("Error:101, file not found");
//            showError("Error:101, File Type Error/File Not Found");
            restart();
        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            System.out.println("Error:102, ChessBoard Error");
            showError("Error:102, ChessBoard Error");

            restart();

        } catch (NumberFormatException numberFormatException){
            System.out.println("Error:103, Number Error");
            showError("Error:103, Number Error");
            restart();
        }
        catch (Exception error) {
//            restart();  //instead of initial numbers, call showup button
            throw new RuntimeException(error);
//            System.out.println(error);

        }
    }



    public void load(String filepath,String username,int size) {

        System.out.println(username + size + "from load");
        System.out.println(filepath + "filepathfromload");
        try {
            FileReader fr = new FileReader("library"+"\\"+username+"\\"+ size+"\\" +filepath + ".txt");
            BufferedReader br = new BufferedReader(fr); //read line by line
            steps = Integer.parseInt(br.readLine());
            score = Integer.parseInt(br.readLine());
            higherScore = Integer.parseInt(br.readLine());
            minutes = Integer.parseInt(br.readLine());
            seconds = Integer.parseInt(br.readLine());
            System.out.println(minutes+"from load");
            System.out.println(seconds+"from load");
            for (int i = 0; i < numbers.length; i++) {
                String[] arrayLine = br.readLine().split("-");
//                System.out.println(arrayLine[i]);
                if(arrayLine.length > numbers.length){
                    throw new ArrayIndexOutOfBoundsException();
                }
                for (int j = 0; j < numbers.length; j++) {
//                        System.out.println(Integer.parseInt(arrayLine[i]));
                    if(Integer.parseInt(arrayLine[j]) %2 !=0){
                        throw new NumberFormatException();
                    }
                    numbers[i][j] = Integer.parseInt(arrayLine[j]);
                }
            }
            System.out.println("minutes: " + minutes + "  seconds: " + seconds );
            fr.close();
            printNumber();
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Error:101, file not found");
            showError("Error:101, File Type Error/File Not Found");
            restart();
        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            System.out.println("Error:102, ChessBoard Error");
            showError("Error:102, ChessBoard Error");

            restart();
        } catch (NumberFormatException numberFormatException){
            System.out.println("Error:103, Number Error");
            showError("Error:103, Number Error");
            restart();
        }
        catch (Exception error) {
//            restart();  //instead of initial numbers, call showup button
            throw new RuntimeException(error);
//            System.out.println(error);

        }
    }

    //manually load
//    public void load(String filepath) {
//        System.out.println(username + size + "from load");
//        try {
//            FileReader fr = new FileReader(filepath + ".txt");
//            BufferedReader br = new BufferedReader(fr); //read line by line
//            score = Integer.parseInt(br.readLine());
//            elapsedTime = Integer.parseInt(br.readLine());
//            for (int i = 0; i < numbers.length; i++) {
//                String[] arrayLine = br.readLine().split("-");
////                System.out.println(arrayLine[i]);
//                for (int j = 0; j < numbers.length; j++) {
////                        System.out.println(Integer.parseInt(arrayLine[i]));
//                    numbers[i][j] = Integer.parseInt(arrayLine[j]);
//                }
//            }
//            fr.close();
//            printNumber();
//        } catch (Exception error) {
//            initialNumbers();
////            throw new RuntimeException(error);
//
//        }
//    }

    public void storeTempArray() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                tempNumbers[i][j] = numbers[i][j];
            }
        }
    }

    public int getSteps() {
        return steps;
    }

    public int getScore() {
        return score;
    }

    public int getHighestScore() {
        return higherScore;
    }

    public void setSteps(int steps){
        this.steps = steps;
    }


    public void GameOver() {
        for(int i = 0; i<numbers.length; i++){
            LOSS_CHECK_inside[i] = true;
        }
        for(int i = 0; i<numbers.length; i++){
            LOSS_CHECK[i] = true;
        }

        //horizontal check
        for(int i=0; i<numbers.length; i++){
            int count1=0;
            for(int j=0; j<numbers.length;j++){
                if(numbers[i][numbers.length-1]==0){
                    LOSS_CHECK[i] = true;
                    break;
                }
                if(j+1==numbers.length){
                    count1++;
                    break;}
                if (numbers[i][j] == 0) {
                    LOSS_CHECK_inside[i] = true;
                    break;

                }else if(numbers[i][j] == numbers[i][j+1]) {
                    LOSS_CHECK_inside[i] = true;
                    break;
                }else{count1++;}//end for i
            }//end for j
            System.out.println(count1+"count1H");
            if(count1==numbers.length){
                LOSS_CHECK_inside[i] = false;
            }
        }//end for i


        //vertical check
        for(int i=0; i<numbers.length; i++){
            int count1=0;
            for(int j=0; j<numbers.length;j++){
                if(numbers[numbers.length-1][i]==0){
                    LOSS_CHECK[i] = true;
                    break;
                }
                if(j+1==numbers.length){
                    count1++;
                    break;}
                if (numbers[j][i] == 0) {
                    LOSS_CHECK[i] = true;
                    break;

                }else if(numbers[j][i] == numbers[j+1][i]) {
                    LOSS_CHECK[i] = true;
                    break;
                }else{count1++;}//end for i
            }//end for j
            System.out.println(count1+"count1V");
            if(count1==numbers.length){
                LOSS_CHECK[i] = false;
            }
        }

        int count_lossesV=0;
        for(int i= 0; i< numbers.length;i++) {
            if (LOSS_CHECK[i]==false){count_lossesV++;}
            System.out.println(LOSS_CHECK_inside[i]+" LOSS INSIDE");
        }

        int count_lossesH=0;
        for(int i= 0; i< numbers.length;i++) {
            if (LOSS_CHECK_inside[i]==false){count_lossesH++;}
            System.out.println(LOSS_CHECK_inside[i]+" LOSS ");
        }//end for
        System.out.println(count_lossesV +"v           h"+count_lossesH);
        if(count_lossesV ==numbers.length && count_lossesH==numbers.length){
            Lost =true;
            System.out.println("------YOU LOSE------");

//            if(!username.isEmpty()){
////                restart();
//                this.elapsedTime = 0;
//                this.hours = 0;
//                this.minutes = 0;
//                this.seconds = 0;
//                save(username, this.elapsedTime);
//
//            }
            new GameoverFrame(username);

        }
    }//end method




//    public void GameOver() {
//        for (int i = 0; i < 4; i++) {
//            LOSS_CHECK_inside[i] = true;
//        }
//        for (int i = 0; i < 4; i++) {
//            LOSS_CHECK[i] = true;
//        }
//        //horizontal check
//        for (int i = 0; i < numbers.length; i++) {
//            int count1 = 0;
//            for (int j = 0; j < numbers.length; j++) {
//                if (j + 1 == numbers.length) {
//                    break;
//                }
//                if (numbers[i][j] == 0) {
//                    break;
//                } else if (numbers[i][j + 1] == numbers[i][j]) {
//                    break;
//                } else {
//                    count1++;
//                }//end for i
//
//            }//end for j
//            if (count1 == numbers.length ) {
//                LOSS_CHECK_inside[i] = false;
//            }
//        }//end for i
//
//
//        //vertical check
//        for (int i = 0; i < numbers.length; i++) {
//            int count1 = 0;
//            for (int j = 0; j < numbers.length; j++) {
//                if (j + 1 == numbers.length) {
//                    break;
//                }
//                if (numbers[j][i] == 0) {
//                    break;
//                } else if (numbers[j + 1][i] == numbers[j][i]) {
//                    break;
//                } else {
//                    count1++;
//                }//end for i
//
//            }//end for j
//            if (count1 == numbers.length ) {
//                LOSS_CHECK[i] = false;
//            }
//        }
//
//        int count_lossesV = 0;
//        for (int i = 0; i < numbers.length; i++) {
//            if (LOSS_CHECK[i] == false) {
//                count_lossesV++;
//            }
////            System.out.println(count_lossesV);
//        }
//
//        int count_lossesH = 0;
//        for (int i = 0; i < numbers.length; i++) {
//            if (LOSS_CHECK_inside[i] == false) {
//                count_lossesH++;
//            }
//        }//end for
//// && count_lossesH==4
//
//        if(count_lossesV ==numbers.length && count_lossesH==numbers.length){
//            Lost =true;
//            System.out.println("------YOU LOOSE------");
//
//        }
////        for (int p=0; p < 4; p++){
////            System.out.println(LOSS_CHECK_inside[p]);
////        }
////        for (int p=0; p < 4; p++){
////            System.out.println(LOSS_CHECK_inside[p]);
////        }
//
//
//    }//end method

    //gamewin
    public void GameWin() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[i][j] == winScore) {
                    System.out.println("You Won-wai");
//                    if(!username.isEmpty()){
//                        restart();
//                        this.elapsedTime = 0;
//                        this.hours = 0;
//                        this.minutes = 0;
//                        this.seconds = 0;
//                        save(username, this.elapsedTime);
//                    }
                    new VictoryFrame(username);
                    this.gameWin = true;
                    break;
                }
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWin() {
        return gameWin;
    }
    public long getMinutes() {
        return minutes;
    }
    public long getSeconds() {
        return seconds;
    }

    public void showError(String message){
        f = new JFrame();
        JOptionPane.showMessageDialog(f,message);
    }
}
