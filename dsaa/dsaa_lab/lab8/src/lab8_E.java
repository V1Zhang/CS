import java.io.*;
import java.util.*;
public class lab8_E {
    public static int ans;
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        int T = in.nextInt();
        for(int t=0;t<T;t++){
            ans = 0;
            int N = in.nextInt();
            int M = in.nextInt();
            nodeE[][] matrix = new nodeE[N+2][M+2]; // y-N x-M
            for(int i=0;i<N+2;i++) {
                for (int j = 0; j < M + 2; j++) {
                    matrix[i][j] = new nodeE();
                }
            }
            for(int i=1;i<N+1;i++){
                for(int j=1;j<M+1;j++){
                    matrix[i][j].val = in.nextInt();
                }
            }
            int sum = 0;
            DFS(1,1, N, M, matrix, sum);
            out.println(ans);
        }
        out.close();
    }
    private static void DFS(int x, int y, int N, int M, nodeE[][] matrix, int sum) {
        if (!matrix[x - 1][y - 1].flag && !matrix[x - 1][y].flag && !matrix[x - 1][y + 1].flag && !matrix[x][y - 1].flag && !matrix[x][y + 1].flag && !matrix[x + 1][y - 1].flag && !matrix[x + 1][y].flag && !matrix[x + 1][y + 1].flag) {
            for (int i = 0; i < 2; i++) {
                if (i == 1) { //choose
                    sum += matrix[x][y].val;
                    matrix[x][y].flag = true;
                }
                //else: do not choose
                if (y < M) {
                    DFS(x, y + 1, N, M, matrix, sum);
                } else if (y == M && x < N) {
                    DFS(x + 1, 1, N, M, matrix, sum);
                } else {
                    if (sum > ans) {
                        ans = sum;
                    }
                }
                if (i == 1) {
                    sum -= matrix[x][y].val;
                    matrix[x][y].flag = false;
                }
            }
        } else {
            //else: do not choose
            if (y < M) {
                DFS(x, y + 1, N, M, matrix, sum);
            } else if (y == M && x < N) {
                DFS(x + 1, 1, N, M, matrix, sum);
            } else {
                if (sum > ans) {
                    ans = sum;
                }
            }
        }
    }
}
class nodeE{
    int val;
    boolean flag = false;
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








