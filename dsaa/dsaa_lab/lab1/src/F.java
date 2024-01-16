import java.io.*;
import java.util.StringTokenizer;
public class F {
    public static void main(String[] args) {
        QReader5 in = new QReader5();
        QWriter5 out = new QWriter5();
        long x1 = in.nextLong();
        long y1 = in.nextLong();
        long x2 = in.nextLong();
        long y2 = in.nextLong();
        int N = in.nextInt();
        long t = 0;
        String[] dir = new String[N+1];
        int[][] step = new int[2][N+1];
        String s = in.nextLine();
        for(int i=1;i<N+1;i++){
            dir[i] = s.substring(i-1,i);
            switch (dir[i]) {
                case "U":
                    step[0][i] = step[0][i-1];
                    step[1][i] = step[1][i-1]+1;
                    break;
                case "D":
                    step[0][i] = step[0][i-1];
                    step[1][i] = step[1][i-1]-1;
                    break;
                case "L":
                    step[0][i] = step[0][i-1]-1;
                    step[1][i] = step[1][i-1];
                    break;
                case "R":
                    step[0][i] = step[0][i-1]+1;
                    step[1][i] = step[1][i-1];
                    break;
            }
        }
        long left = 0;
        long right = (long) Math.pow(10,16);
        long mid = (left+right)/2;
        while(right>left){
            mid = (right + left) / 2;
            if(Check(mid,x1,y1,x2,y2,N,step)){
                right = mid;
            }else{
                left = mid+1;
            }
            t = (left+right)/2;
        }
        if(t==Math.pow(10,16)) {
            out.println(-1);
        }else{
            out.println(t);
        }
        out.close();
    }
    public static boolean Check(long t,long x1,long y1,long x2,long y2,int N,int[][] step){
        long a = t/N;
        int b = (int) (t%N);
        x2+=a*step[0][N]+step[0][b];
        y2+=a*step[1][N]+step[1][b];
        if(Math.abs(x1-x2)+Math.abs(y1-y2)<=t) {
            return true;
        }else{
            return false;
        }
    }
}
class QReader5 {
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

class QWriter5 implements Closeable {
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








