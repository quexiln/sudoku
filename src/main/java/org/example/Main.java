package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int[][][][] table = {
            {{{0,1,0},{0,2,0},{0,3,0}},{{0,4,0},{0,5,0},{0,6,0}},{{0,7,0},{0,8,0},{0,9,0}}},
            {{{0,1,0},{0,2,0},{0,3,0}},{{0,4,0},{0,5,0},{0,6,0}},{{0,7,0},{0,8,0},{0,9,0}}},
            {{{0,1,0},{0,2,0},{0,3,0}},{{0,4,0},{0,5,0},{0,6,0}},{{0,7,0},{0,8,0},{0,9,0}}}
    };

    public static void main(String[] args) {

        int i =0;
        int k = 0;

        do{
            for (int m = 0; m < table.length; m++) {
                for (int j = 0; j < table[i].length; j++) {
                    for (int l = 0; l < table[i][j][k].length; l++) {
                        System.out.print(table[i][j][k][l]+" ");
//                        System.out.println(+i + "-" + j + "-" + k + "-" + l);
                    }
                }
                System.out.println();
                k++;
            }
            k = 0;
            i++;
        }while (i < table.length);
    }
}