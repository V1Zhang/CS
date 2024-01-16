import java.io.*;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        int N = in.nextInt();
        int Q = in.nextInt();
        long[] A = new long[N+1];
        for(int i=1;i<N+1;i++) {
            A[i] = in.nextLong();
        }
        for(int i=0;i<Q;i++){
            long x = in.nextLong();
            long y = in.nextLong();
            int left_index = 0;
            int right_index = 0;
            int left = 1;
            int right = N;
            if(x<A[1]){
                left_index = 0;
            } else if (x>A[N]) {
                left_index = N+1;
            } else {
                while (right - left > 1) {
                    int mid = (left + right) / 2;
                    if (A[mid] == x) {
                        if (A[mid + 1] == A[mid]) {
                            left = mid + 1;
                        } else {
                            left_index = mid;
                            break;
                        }
                    } else if (A[mid] > x) {
                        right = mid;
                    } else if (A[mid] < x) {
                        left = mid;
                    }
                }
                if (right - left <= 1) {
                    if (x == A[left]) {
                        left_index = left;
                    }
                    if (x == A[right]) {
                        left_index = right;
                    }
                    if (x != A[left] && x != A[right]) {
                        left_index = left;
                    }
                }

            }
            left = 1;
            right = N;
            if (y > A[N]) {
                right_index = N + 1;
            } else if (y<A[1]) {
                left_index = 0;
            } else {
                while (right - left > 1) {
                    int mid = (left + right) / 2;
                    if (A[mid] == y) {
                        if (A[mid - 1] == A[mid]) {
                            right = mid - 1;
                        } else {
                            right_index = mid;
                            break;
                        }
                    } else if (A[mid] > y) {
                        right = mid;
                    } else if (A[mid] < y) {
                        left = mid;
                    }
                }
                if (right - left <= 1) {
                    if (y == A[right]) {
                        right_index = right;
                    }
                    if (y == A[left]) {
                        right_index = left;
                    }
                    if (y != A[left] && y != A[right]) {
                        right_index = right;
                    }
                }
            }
            int num = right_index - left_index;
            if(num>1){
                out.println("YES "+(num-1));
            }
            else{
                out.println("NO");
            }
        }
        out.close();
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








