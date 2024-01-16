import java.util.Scanner;
public class lab5_A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i=0;i<T;i++){
            int n = sc.next().length();
            System.out.println((1+n)*n/2);
        }
    }
}