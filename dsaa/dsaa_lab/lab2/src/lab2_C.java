import java.io.*;
import java.util.*;
public class lab2_C {
    public static long sum = 0;
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            sum = 0;
            int n = in.nextInt();
            int[] num = new int[n];
            for (int j = 0; j < n; j++) {
                num[j] = in.nextInt();
            }
            MergeSort(num);
            out.println(sum);
        }
        out.close();
    }
    public static void MergeSort(int[] list) {
        if(list.length>1) {
            int[] first = new int[list.length / 2];
            System.arraycopy(list, 0, first, 0, list.length / 2);
            MergeSort(first);
            int second_len = list.length - list.length / 2;
            int[] second = new int[second_len];
            System.arraycopy(list, list.length / 2, second, 0, second_len);
            MergeSort(second);
            sum += merge(first, second, list);
        }
    }
    public static long merge(int[] first, int[] second, int[] temp) {
        int i = 0;
        int j = 0;
        long count = 0;
        for (int k = 0; k < first.length + second.length; k++) {
            if (i < first.length && (j >= second.length || first[i] <= second[j])) {
                temp[k] = first[i];
                i++;
            } else {
                temp[k] = second[j];
                if(i< first.length && first[i]>second[j]){
                    count+=(j+first.length-k);
                }
                j++;
            }
        }
        return count;
    }
}

class QReader2 {
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

class QWriter2 implements Closeable {
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








