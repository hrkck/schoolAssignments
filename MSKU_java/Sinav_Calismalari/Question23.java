package sinavOncesi;

public class Question23 {
        public static void main(String[] args) {
                try {
                        int x = 0;
                        int y = 5 / x;

                        // Aritmatik istisna ile normal istisnanin yeri degistirildi.
                        // boylece " Unreachable block" hatasini duzelttik.
                } catch (ArithmeticException ae)  {
                        System.out.println(" Arithmetic Exception");
                } catch (Exception e)   {
                        System.out.println("Exception");
                }
                System.out.println("finished");
        }
}
