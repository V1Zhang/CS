import java.util.Scanner;
public class A {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n1 = scanner.nextInt();
    int[] A = new int[n1];
    for (int i = 0; i < n1; i++) {
        int p = scanner.nextInt();
        A[i] = p;
        }
    int n2 = scanner.nextInt();
    int[] B = new int[n2];
    for (int i = 0; i < n2; i++) {
        int q = scanner.nextInt();
        B[i] = q;
        }
    boolean exist = false;
    for (int i = 0; i < n2; i++) {
        exist = false;
        for(int j = 0; j<n1; j++) {
            if (B[i] == A[j]) {
                exist = true;
                break;
                }
            }
        if(!exist){
            System.out.println("no");
        }
        else{
            System.out.println("yes");
        }
        }
    }
}