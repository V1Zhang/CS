import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E {
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        while (in.hasNext()) {
            long L = in.nextLong();
            int n = in.nextInt();
            int m = in.nextInt();
            long arr[] = new long[n + 2];
            arr[n + 1] = L;
            long[] diff = new long[n + 1];
            long ans = L;
            for (int i = 1; i <= n; i++) {
                arr[i] = in.nextLong();
            }
            Arrays.sort(arr);
            for (int i = 1; i <= n+1; i++) {
                diff[i - 1] = arr[i] - arr[i - 1];
            }
            long right = L;
            long left = 0;
            long mid = (right + left) / 2;
            while (right > left) {
                mid = (right + left) / 2;
                if (checkAns(mid, m, diff)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
                ans = (right + left) / 2;;
            }
            out.println(ans);
        }
        out.close();
    }
    public static boolean checkAns(long dis, int m, long[] diff) {
        long d = dis;
        int i = 0;
        int count = 1;
        while (i < diff.length) {
            if(dis<diff[i]){
                count = m+1;
                break;
            } else if (d - diff[i] >= 0) {
                d -= diff[i];
                i++;
            } else {
                count++;
                d = dis;
            }
        }
        if (count <= m) {
            return true;
        } else {
            return false;
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








