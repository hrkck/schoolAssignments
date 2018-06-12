package sinavOncesi;

// CTRL + SHIFT + F otomatik duzeltmeee

public class Question5 {
        private static void doSomething(int[] intArray) {
                int listeUzunlugu = intArray.length;
                int temp = 0;
                // Bubble Sort
                for (int i = 0; i < listeUzunlugu; i++) {
                        for (int j = 1; j < (listeUzunlugu - i); j++) {
                                if (intArray[j - 1] > intArray[j]) {
                                        temp = intArray[j - 1];
                                        intArray[j - 1] = intArray[j];
                                        intArray[j] = temp;
                                }
                        }
                }
        }

        public static void main(String args[]) {
                int[] items = new int[] { 5, 90, 35, 45, 150, 3 };
                doSomething(items);
                for (int i = 0; i < items.length; i++) {
                        System.out.print(items[i]);
                        if (i != items.length - 1) {
                                System.out.print(",");
                        }
                }
        }
}
