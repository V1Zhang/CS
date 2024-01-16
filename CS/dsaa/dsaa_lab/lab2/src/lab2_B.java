import java.io.*;
import java.util.*;
public class lab2_B {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        int n = in.nextInt();
        long[] A = new long[n];
        long[] temp = new long[n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            A[i] = in.nextLong();
        }
        MergeSort(A,temp,0,n-1);
        if (n % 2 == 0) {
            ans = A[n / 2-1] + A[n / 2];
        } else {
            ans = 2 * A[(n + 1) / 2-1];
        }
        out.println(ans);
        out.close();
    }

    public static void MergeSort(long[] list,long[] temp,int left,int right) {
        if (left<right) {
            int mid = (left+right)/2;
            MergeSort(list,temp,left,mid);
            MergeSort(list,temp,mid+1,right);
            merge(left, right, mid, list, temp);
        }
    }
    public static void merge(int left, int right, int mid, long[] list, long[] temp) {
        int i = left;
        int j = mid+1;
        int k = 0;
        while(i <= mid && j <= right){
            if(list[i] < list[j]){
                temp[k++] = list[i++];
            }else{
                temp[k++] = list[j++];
            }
        }
        while(i <= mid){
            temp[k++] = list[i++];
        }
        while(j <= right){
            temp[k++] = list[j++];
        }
        for(int t=0;t<k;t++){
            list[left+t] = temp[t];
        }
    }
}
class QReader1 {
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

class QWriter1 implements Closeable {
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








