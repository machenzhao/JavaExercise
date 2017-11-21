package Number_01;

public class PrimeNumber {
    // 6：通过循环1-200之间的数，找出其中的素数并打印；

    private static boolean isPrime(int num) {
        int tmp = (int) Math.sqrt(num);
        for (int i = 2; i <= tmp; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int max = 200;
        System.out.println("输出1-" + 200 + "之间的素数：");
        for (int i = 1; i <= max; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }
}