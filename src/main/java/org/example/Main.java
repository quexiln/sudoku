package org.example;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int[][][][] table = {
            {{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}}},
            {{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}}},
            {{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}},{{0,0,0},{0,0,0},{0,0,0}}}
    };

    public static void main(String[] args) {
        play();
    }
    public static void play(){
        if (table.equals(0)){
                System.out.println("Tebrikler Kazandınız");
        }
        else{
        drawTable();
        changeNumber();}
    }

    public static void drawTable(){
        int i =0;
        int k = 0;
        int y = 1;

        System.out.println("   a b c d e f g h i");

        do{
            for (int m = 0; m < table.length; m++) {
                System.out.print(y+ "  ");
                y++;
                for (int j = 0; j < table[i].length; j++) {

                    for (int l = 0; l < table[i][j][k].length; l++) {
                        System.out.print(table[i][j][k][l]+" ");
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
        System.out.println("Sayıyı değiştirmek için önce sütun sonra satır değerini giriniz : ");
        String choose = scanner.nextLine();
        String column = choose.split("")[0];
        int row = Integer.parseInt(choose.split("")[choose.split("").length-1])-1;
        String script = "ABCDEFGHİJKLMNOPQRSTUVWXYZ";
        int col = script.toLowerCase().indexOf(column.toLowerCase());
        System.out.println("Sayı'nın kaç olmasını istiyorsunuz");
        int number = scanner.nextInt();
        if(isSuitable(number,col,row)) table[row<3 ? 0: row<6 ? 1:2][col<3 ? 0: col<6 ? 1:2][row%3][col%3] = number;
        else System.out.println("Hatalı giriş lütfen tekrar deneyiniz.");
        scanner.nextLine();
        play();
    }
    static boolean isSuitable(int number, int col, int row){
        if(number<10 && number>0){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!(((row < 3 ? 0 : row < 6 ? 1 : 2) + "-" + (col < 3 ? 0 : col < 6 ? 1 : 2) + "-" + (row % 3) + "-" + (col % 3))
                        .equals(i + "-" + (col < 3 ? 0 : col < 6 ? 1 : 2) + "-" + j + "-" + (col%3)))){
                    if(table[i][col<3 ? 0: col<6 ? 1:2][j][col%3]==number) return false;
                }
            }
            for (int j = 0; j < 3; j++) {
                if(!(((row < 3 ? 0 : row < 6 ? 1 : 2) + "-" + (col < 3 ? 0 : col < 6 ? 1 : 2) + "-" + (row % 3) + "-" + (col % 3))
                        .equals((row<3 ? 0: row<6 ? 1:2)+"-"+i+"-"+(row%3)+"-"+j))){
                    if(table[row<3 ? 0: row<6 ? 1:2][i][row%3][j]==number) return false;
                }
            }
            for (int j = 0; j < 3; j++) {
                if(!(((row < 3 ? 0 : row < 6 ? 1 : 2) + "-" + (col < 3 ? 0 : col < 6 ? 1 : 2) + "-" + (row % 3) + "-" + (col % 3))
                        .equals((row<3 ? 0: row<6 ? 1:2)+"-"+(col < 3 ? 0 : col < 6 ? 1 : 2)+"-"+i+"-"+j))){
                    if(table[row<3 ? 0: row<6 ? 1:2][(col < 3 ? 0 : col < 6 ? 1 : 2)][i][j]==number) return false;
                }
            }
        }
        }else return false;
        return true;
    }
}