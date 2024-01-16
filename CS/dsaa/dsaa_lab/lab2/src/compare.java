import java.io.*;
import java.util.*;
public class compare {
    public static void main(String[] args) {
        QReader9 in = new QReader9();
        QWriter9 out = new QWriter9();
        int n = in.nextInt();
        long[][] nums = new long[3][n];
        int p = in.nextInt();
        int q = in.nextInt();
        long[][] temp = new long[3][n];
        long[] sum = new long[n];
        long ans = 0;
        long temp_sum = 0;
        boolean quit = false;
        for(int i=0;i<n;i++){
            nums[0][i] = in.nextLong();
            nums[1][i] = in.nextLong();
            nums[2][i] = nums[0][i]-nums[1][i];
            if(q==0){
                ans+=nums[1][i];
                quit = true;
            }
        }
        if(!quit) {
            MergeSort(nums, temp, 0, n - 1);
            for (int i = 0; i < Math.min(q-1,n); i++) {
                if (nums[2][i] > 0) {
                    nums[1][i] = nums[0][i];
                }
            }
            for(int i=0;i<n;i++){
                temp_sum += nums[1][i];
            }
            for (int i = 0; i < n; i++) {
                if (i < q - 1) {
                    sum[i] = temp_sum + nums[0][i]*(long) Math.pow(2, p) - nums[1][i] - nums[1][Math.min(q-1,n-1)] + Math.max(nums[0][Math.min(q-1,n-1)], nums[1][Math.min(q-1,n-1)]);
                } else {
                    sum[i] = temp_sum + nums[0][i]*(long) Math.pow(2, p) - nums[1][i];
                }
            }
            for(int i=0;i<n;i++){
                ans = Math.max(ans,sum[i]);
            }
        }
        out.println(ans);
        out.close();
    }
    public static void MergeSort(long[][] list,long[][] temp,int left,int right) {
        if (left<right) {
            int mid = (left+right)/2;
            MergeSort(list,temp,left,mid);
            MergeSort(list,temp,mid+1,right);
            merge(left, right, mid, list, temp);
        }
    }
    public static void merge(int left, int right, int mid, long[][] list, long[][] temp) {
        int i = left;
        int j = mid+1;
        int k = 0;
        while(i <= mid && j <= right){
            if(list[2][i] > list[2][j]){
                temp[0][k] = list[0][i];
                temp[1][k] = list[1][i];
                temp[2][k] = list[2][i];
                i++;
                k++;
            }else{
                temp[0][k] = list[0][j];
                temp[1][k] = list[1][j];
                temp[2][k] = list[2][j];
                j++;
                k++;
            }
        }
        while(i <= mid){
            temp[0][k] = list[0][i];
            temp[1][k] = list[1][i];
            temp[2][k] = list[2][i];
            i++;
            k++;
        }
        while(j <= right){
            temp[0][k] = list[0][j];
            temp[1][k] = list[1][j];
            temp[2][k] = list[2][j];
            j++;
            k++;
        }
        for(int t=0;t<k;t++){
            list[0][left+t] = temp[0][t];
            list[1][left+t] = temp[1][t];
            list[2][left+t] = temp[2][t];
        }
    }
}
class QReader9 {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter9 implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}








