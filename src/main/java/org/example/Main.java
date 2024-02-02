package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static int[][][][] table = {
            {{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}}},
            {{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}}},
            {{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}}}
    };

    public static void main(String[] args) {
        generateSudoku();
        play();
    }
    public static void play(){
        drawTable();
        changeNumber();
    }

    public static void drawTable(){
        int i =0;
        int k = 0;
        int y = 1;
        int x = 1;

        System.out.println("   a b c d e f g h i");

        do{
            for (int m = 0; m < table.length; m++) {
                System.out.print(y+ "  ");
                y++;
                for (int j = 0; j < table[i].length; j++) {
                    for (int l = 0; l < table[i][j][k].length; l++) {
                        System.out.print(table[i][j][k][l] + " ");
                    }

                }
                System.out.println();
                k++;
            }
            k = 0;
            i++;
        }while (i < table.length);
    }


    public static void changeNumber(){
        try {
            System.out.println("Sayıyı değiştirmek için önce sütun sonra satır değerini giriniz : ");
            String choose = scanner.nextLine();
            String column = choose.split("")[0];
            int row = Integer.parseInt(choose.split("")[choose.split("").length-1])-1;
            String script = "ABCDEFGHİJKLMNOPQRSTUVWXYZ";
            int col = script.toLowerCase().indexOf(column.toLowerCase());
            System.out.println("Sayı'nın kaç olmasını istiyorsunuz");
            int number = scanner.nextInt();
            if(isSuitable(number,(row < 3 ? 0 : row < 6 ? 1 : 2),(col < 3 ? 0 : col < 6 ? 1 : 2),(col%3),(row % 3)))
                table[row<3 ? 0: row<6 ? 1:2][col<3 ? 0: col<6 ? 1:2][row%3][col%3] = number;
            else System.out.println("Hatalı giriş lütfen tekrar deneyiniz.");
            scanner.nextLine();
            play();
        } catch (NumberFormatException e) {
            System.out.println("Hatalı giriş tekrar deneyiniz.");
            changeNumber();
        }
    }

    public static void generateSudoku(){
        int difficulty = 39;
        int maxValue = 81;

        int i =0;
        int k = 0;
        do{
            for (int m = 0; m < table.length; m++) {
                for (int j = 0; j < table[i].length; j++) {
                    for (int l = 0; l < table[i][j][k].length; l++) {
                        int fill = random.nextInt(maxValue)+1;
                        if(fill > difficulty && fill <= maxValue){
                            maxValue--;
                        }
                        else if(fill <= difficulty){
                            int number;
                            do {
                                number = random.nextInt(9)+1;
                            } while (!isSuitable(number,i,j, k, l));
                            table[i][j][l][k] = number;
                            maxValue--;
                            difficulty--;
                        }
                    }

                }
                k++;
            }
            k = 0;
            i++;
        }while (i < table.length);

    }
    static boolean isSuitable(int number,int blockRow,int block, int col, int row){
        if(number<10 && number>0){
        for (int i = 0; i < 3; i++) {
            String s = blockRow + "-" + block + "-" + row + "-" + col;

            for (int j = 0; j < 3; j++) {

                if(!(s.equals(i + "-" + block + "-" + j + "-" + col))){
                    if(table[i][block][j][col]==number) return false;
                }
            }
            for (int j = 0; j < 3; j++) {
                if(!(s.equals((blockRow)+"-"+i+"-"+row+"-"+j))){
                    if(table[blockRow][i][row][j]==number) return false;
                }
            }
            for (int j = 0; j < 3; j++) {
                if(!(s.equals((blockRow)+"-"+block+"-"+i+"-"+j))){
                    if(table[blockRow][block][i][j]==number) return false;
                }
            }
        }
        }else return false;

        return true;
    }
}