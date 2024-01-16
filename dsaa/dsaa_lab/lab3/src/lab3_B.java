import java.io.*;
import java.util.*;

public class lab3_B {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        double_node head = new double_node(10001);
        double_node tail = new double_node(-10001);
        int T = in.nextInt();
        for(int i=0;i<T;i++){
            double_node cur = head;
            head.next = tail;
            tail.prev = head;
            int n = in.nextInt();
            String s = in.next();
            for(int j=0;j<n;j++){
                if (s.charAt(j)=='r') {
                    if(j<n-1 && Character.isDigit(s.charAt(j+1))) {
                        if(cur.next==tail) {
                            double_node a = new double_node(Integer.parseInt(String.valueOf(s.charAt(++j))));
                            cur.next = a;
                            a.prev = cur;
                            a.next = tail;
                            tail.prev = a;
                        }else{
                            cur.next.value = Integer.parseInt(String.valueOf(s.charAt(++j)));
                        }
                    }
                } else if (s.charAt(j)=='I') {
                    cur = head;
                } else if (s.charAt(j)=='H') {
                    if(cur!=head){
                        cur = cur.prev;
                    }
                } else if (s.charAt(j)=='L') {
                    if(cur.next!=tail){
                        cur = cur.next;
                    }
                } else if (s.charAt(j)=='x') {
                    if(cur.next!=tail) {
                        cur.next = cur.next.next;
                        cur.next.prev = cur;
                    }
                }else{
                    double_node a = new double_node(Integer.parseInt(String.valueOf(s.charAt(j))));
                    if(cur.next==tail) {
                        cur.next = a;
                        a.prev = cur;
                        a.next = tail;
                        tail.prev = a;
                    }else{
                        double_node temp = cur.next;
                        cur.next = a;
                        a.prev = cur;
                        a.next = temp;
                        temp.prev = a;
                    }
                    cur = cur.next;
                }
            }
            cur = head.next;
            while(cur!=tail) {
                out.print(cur.value);
                cur = cur.next;
            }
            out.println("");
        }
        out.close();
    }
}
class double_node{
    int value;
    double_node next;
    double_node prev;
    public double_node(int value){
        this.value = value;
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
