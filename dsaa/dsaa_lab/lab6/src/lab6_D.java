import java.io.*;
import java.util.*;
public class lab6_D {
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            long[] heap = new long[N+1];
            int front = 0;
            for(int j=1;j<N+1;j++){
                heap[j] = in.nextInt();
                front++;
                int point = j;
                while (point > 1) {
                    if (heap[point] < heap[point / 2]) {
                        long t = heap[point / 2];
                        heap[point / 2] = heap[point];
                        heap[point] = t;
                        point = point / 2;
                    } else {
                        break;
                    }
                }
            }
            long sum = 0;
            while(front>1) {
                long p = heap[1];
                heap[1] = heap[front];
                heap[front] = 0;
                int point = 1;
                while(2*point<front){
                    long min = 0;
                    int index = 0;
                    if (2 * point + 1 < front) {
                        if (heap[2 * point] > heap[2 * point + 1]) {
                            min = heap[2 * point + 1];
                            index = 2 * point + 1;
                        } else {
                            min = heap[2 * point];
                            index = 2 * point;
                        }
                    } else {
                        min = heap[2 * point];
                        index = 2 * point;
                    }
                    if(heap[point]>min){
                        long t = min;
                        heap[index] = heap[point];
                        heap[point] = t;
                        point = index;
                    }else{
                        break;
                    }
                }
                front--;
                long q = heap[1];
                heap[1] = heap[front];
                heap[front]=0;
                point = 1;
                while(2*point<front){
                    long min = 0;
                    int index = 0;
                    if (2 * point + 1 < front) {
                        if (heap[2 * point] > heap[2 * point + 1]) {
                            min = heap[2 * point + 1];
                            index = 2 * point + 1;
                        } else {
                            min = heap[2 * point];
                            index = 2 * point;
                        }
                    } else {
                        min = heap[2 * point];
                        index = 2 * point;
                    }
                    if(heap[point]>min){
                        long t = min;
                        heap[index] = heap[point];
                        heap[point] = t;
                        point = index;
                    }else{
                        break;
                    }
                }
                front--;
                long num = p+q;
                front++;
                point = front;
                heap[point] = num;
                while (point > 1) {
                    if (heap[point] < heap[point / 2]) {
                        long t = heap[point / 2];
                        heap[point / 2] = heap[point];
                        heap[point] = t;
                        point = point / 2;
                    } else {
                        break;
                    }
                }
                sum += num;
            }
            out.println(sum);
        }
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








