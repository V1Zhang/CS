import java.io.*;
import java.util.*;
public class D {
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int n = in.nextInt();
        int S = in.nextInt();
        long count = 0;
        long[] nums =  new long[n+1];
        for (int i = 1; i <= n; i++) {
            nums[i] = in.nextInt();
        }
        for (int i = 1; i < n; i++) {
            long add1 = nums[i];
            for(int j=n;j>i+1;j--){
                long add2 = nums[j];
                int left = i;
                int right = j;
                while(right-left>1) {
                    int mid = (left+right)/2;
                    if (nums[mid]==S-add1-add2){
                        count++;
                        if (nums[mid-1]==nums[mid] && mid-1>left) {
                            for (int p = mid - 1; p > left; p--) {
                                if (nums[p]==nums[mid]) {
                                    count++;
                                } else break;
                            }
                        }
                        if (nums[mid+1]==nums[mid] && mid+1<right) {
                            for (int q = mid + 1; q < right; q++) {
                                if (nums[q]==nums[mid]) {
                                    count++;
                                } else break;
                            }
                        }
                        break;
                    } else if (nums[mid]>S-add1-add2) {
                        right = mid;
                    } else if (nums[mid]<S-add1-add2) {
                        left = mid;
                    }
                }
            }
        }
        out.println(count);
        out.close();
    }
}
class QReader3 {
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

class QWriter3 implements Closeable {
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








