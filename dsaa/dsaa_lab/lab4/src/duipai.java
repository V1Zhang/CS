import java.util.*;

public class duipai {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] A = new int[n];
        int ans = 1;
        for(int i=0;i<n;i++){
            A[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int min = 2000000000;
                int max = 0;
                for(int p=i;p<=j;p++){
                    min = Math.min(min,A[p]);
                    max = Math.max(max,A[p]);
                }
                if(max-min<=k){
                    ans = Math.max(ans,Math.abs(i-j)+1);
                }
            }
        }
        System.out.println(ans);

    }
}