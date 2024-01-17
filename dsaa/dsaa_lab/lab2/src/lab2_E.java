import java.io.*;
import java.util.*;
public class lab2_E {
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        int n = in.nextInt();
        long[] height = new long[n];
        for (int i = 0; i < n; i++) {
            height[i] = in.nextLong();
        }
        MergeSort(height);
        int k_index = n/3;
        long k = height[k_index];
        int big = k_index;
        int small = 0;
        long[] seat = new long[n];
        if(height[k_index]==height[k_index-1]){
            int new_index = 0;
            for (int i = 0; i < n; i++) {
                if(height[i]<height[k_index]){
                    new_index++;
                }
            }
            big = new_index;
            for (int i = 0; i < n && big < n; i++) {
                if (i % 3 == 0 && small < new_index) {
                    seat[i] = height[small++];
                } else {
                    seat[i] = height[big++];
                }
            }
        }
        else {
            for (int i = 0; i < n && big < n; i++) {
                if (i % 3 == 0 && small < k_index) {
                    seat[i] = height[small++];
                } else {
                    seat[i] = height[big++];
                }
            }
        }
        out.println(k);
        for (int i = 0; i < n; i++) {
            out.print(seat[i] + " ");
        }
        out.close();
    }
    public static void MergeSort(long[] list) {
        if (list.length > 1) {
            long[] first = new long[list.length / 2];
            System.arraycopy(list, 0, first, 0, list.length / 2);
            MergeSort(first);
            int second_len = list.length - list.length / 2;
            long[] second = new long[second_len];
            System.arraycopy(list, list.length / 2, second, 0, second_len);
            MergeSort(second);
            merge(first, second, list);
        }
    }

    public static void merge(long[] first, long[] second, long[] temp) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < first.length + second.length; k++) {
            if (i < first.length && (j >= second.length || first[i] <= second[j])) {
                temp[k] = first[i];
                i++;
            } else {
                temp[k] = second[j];
                j++;
            }
        }
    }
}
class QReader4 {
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

class QWriter4 implements Closeable {
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








