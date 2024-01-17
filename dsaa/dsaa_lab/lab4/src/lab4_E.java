import java.io.*;
import java.util.*;

public class lab4_E {
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        int m = in.nextInt();
        node[] A = new node[2000000];
        int count = 0;
        int num = in.nextInt();
        while(num!=-1){
            A[count] = new node(count,num);
            num = in.nextInt();
            count++;
        }
        node[] queue = new node[count];
        int[] ans = new int[count];
        queue[0] = A[0];
        int rear = 0;
        int front = 0;
        int point = 1;
        int ans_point = 0;
        while(point<count){
            if (A[point].value >= queue[rear].value){
                while(rear>=front && A[point].value >= queue[rear].value){
                    queue[rear].value = 0;
                    rear--;
                }
                rear++;
                queue[rear] = A[point];
                point++;
            }else{
                rear++;
                queue[rear] = A[point];
                point++;
            }
            if(point>=m){
                while(point-queue[front].index>m){
                    front++;
                }
                ans[ans_point] = queue[front].value;
                ans_point++;
            }
        }
        int sum = ans[0];
        for(int i=1;i<ans_point;i++){
            sum = sum^ans[i];
        }
        out.println(sum);
        out.close();
    }
}
class node{
    int index;
    int value;
    public node(int index, int value){
        this.index = index;
        this.value = value;
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
