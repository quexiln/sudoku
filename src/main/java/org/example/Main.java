package org.example;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static Random random = new Random();

//    public static void generateSudoku(){
//        int difficulty = 50;
//        int maxValue = 81;
//
//        int i =0;
//        int k = 0;
//        do{
//            for (int m = 0; m < table.length; m++) {
//                for (int j = 0; j < table[i].length; j++) {
//                    for (int l = 0; l < table[i][j][k].length; l++) {
//                        int fill = random.nextInt(maxValue)+1;
//                        if(fill > difficulty && fill <= maxValue){
//                            maxValue--;
//                        }
//                        else if(fill <= difficulty){
//                            int number;
//                            do {
//                                number = random.nextInt(9)+1;
//                            } while (!isSuitable(number,i,j, k, l));
//                            table[i][j][l][k] = number;
//                            maxValue--;
//                            difficulty--;
//                        }
//                    }
//
//                }
//                k++;
//            }
//            k = 0;
//            i++;
//        }while (i < table.length);
//
//    }
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
        int k = 0;
        int y = 1;
        int x = 1;

        System.out.println("   a b c d e f g h i");

        for (int i = 0; i < table.length; i++) {
            for (int m = 0; m < table.length; m++) {
                System.out.print(y+ "  ");
                y++;
                for (int j = 0; j < table[i].length; j++) {
                    for (int l = 0; l < table[i][j][k].length; l++) {
                        System.out.print(table[i][j][k][l] + " ");
//                        +(i + "-" + j + "-" + k + "-" + l)+" | "
//                        +isSuitable(table[i][j][k][l],i,j,k,l)+" | "
//                        +(table[i][j][k][l] == 0 ? isSuitable(table[i][j][k][l],i,j,k,l): "" )+" | "
                    }

                }
                System.out.println();
                k++;
            }
            k = 0;
        }
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
            if( (Boolean) isSuitable(number,(row < 3 ? 0 : row < 6 ? 1 : 2),(col < 3 ? 0 : col < 6 ? 1 : 2),(row % 3),(col%3)).getFirst())
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
        int i =0;
        int k = 0;
        loop : do{
            for (int m = 0; m < table.length; m++) {
                for (int j = 0; j < table[i].length; j++) {
                    for (int l = 0; l < table[i][j][k].length; l++) {
                        List<Integer> generated = new ArrayList<>();
                        int number;
                        do {
                            number = random.nextInt(9) + 1;

                            if (!generated.contains(number)) {
                                generated.add(number);

                                if (!((Boolean) isSuitable(number, i, j, k, l).getFirst()) && generated.size() == 9) {
                                    List<Integer> block = (List<Integer>) isSuitable(number, i, j, k, l).get(1);
                                    if (!(block.get(0) == null)) {
                                        i = block.get(0);
                                        j = block.get(1);
                                        k = block.get(2);
                                        l = block.get(3);
                                        continue loop;
                                    }
                                }

                            }
                        } while (!((Boolean) isSuitable(number, i, j, k, l).getFirst()));
                        table[i][j][k][l] = number;
                    }
                }
                if (2==k){
                    k=0;
                }
                else k++;

            }
            k = 0;
            i++;
        }while (i < table.length);
        i = 0;
        int difficulty = 1;
        int maxValue = 81;
        do{
            for (int m = 0; m < table.length; m++) {
                for (int j = 0; j < table[i].length; j++) {
                    for (int l = 0; l < table[i][j][k].length; l++) {
                        int fill = random.nextInt(maxValue)+1;
                        if(fill > difficulty && fill <= maxValue){
                            maxValue--;
                        }
                        else if(fill <= difficulty){
                            table[i][j][k][l] = 0;
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



    static List<Object> isSuitable(int number, int blockRow, int block, int row, int col){
        List<Object> returnValue = new ArrayList<>();
        returnValue.add(true);
        returnValue.add(Arrays.asList(null,null,null,null));
        if(number<10 && number>0){
        for (int i = 0; i < 3; i++) {
            String s = blockRow + "-" + block + "-" + row + "-" + col;

            for (int j = 0; j < 3; j++) {
                if(!(s.equals(i + "-" + block + "-" + j + "-" + col))){
                    if(table[i][block][j][col]==number) {
                        returnValue.set(0, false);
                        returnValue.set(1,Arrays.asList(i ,block , j ,col));
                    }
                }
            }
            for (int j = 0; j < 3; j++) {
                if(!(s.equals((blockRow)+"-"+i+"-"+row+"-"+j))){
                    if(table[blockRow][i][row][j]==number) {
                        returnValue.set(0, false);
                        returnValue.set(1,Arrays.asList(blockRow ,i , row ,j));
                    }
                }
            }
            for (int j = 0; j < 3; j++) {
                if(!(s.equals((blockRow)+"-"+block+"-"+i+"-"+j))){
                    if(table[blockRow][block][i][j]==number) {
                        returnValue.set(0, false);
                        returnValue.set(1,Arrays.asList(blockRow ,block , i ,j));
                    }
                }
            }
        }
        }else returnValue.set(0,false);

        return returnValue;
    }
}