package sinavOncesi;

import java.util.ArrayList;

public class Question7 {
        public static ArrayList<Integer> sum(int[][] multiArray) {
                ArrayList<Integer> sumList = new ArrayList<>();
                int sum = 0;

                for (int i = 0; i < multiArray.length; i++) {
                        for (int j = 0; j < multiArray[i].length; j++) {
                                sum += multiArray[i][j];

                                // ********************************
                                // Hangi index'te ne var?
                                System.out.print(i);
                                // indexleri topladigi icin syso'yu ikiye bolduk
                                System.out.println(j + " = " + sum);
                                // ********************************
                        }
                        // ic kume toplami bittikten sonra listeye ekliyoruz:
                        sumList.add(sum);
                }

                return sumList;
        }

        public static void main(String[] args) {
                int[][] multi = new int[][] {
                        { 1, 2, 3 }, { 4, 5, 6, 8 }, { 7, 9 }
                };
                ArrayList<Integer> sumList = sum(multi);
                System.out.println(sumList);
        }
}
