import java.io.*;
import java.util.*;

public class lab3_A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        node head = new node(1000000001,1000000001);
        node tail = new node(-1000000001,-1000000001);
        int n = in.nextInt();
        int m = in.nextInt();
        node cur = head;
        for(int i=0;i<n;i++){
            node a = new node(in.nextInt(),in.nextInt());
            cur.next = a;
            cur = cur.next;
        }
        cur.next = tail;
        cur = head;
        for(int i=0;i<m;i++){
            node temp = new node(in.nextInt(),in.nextInt());
            while(cur.next.exp>=temp.exp){
                cur = cur.next;
            }
            if(cur.exp == temp.exp){
                cur.coe+= temp.coe;
            }else{
                temp.next = cur.next;
                cur.next = temp;
            }
        }
        cur = head.next;
        int k = 0;
        while(cur!=tail){
            k++;
            cur = cur.next;
        }
        out.println(k);
        cur = head.next;
        while(cur!=tail){
            out.println(cur.coe+" "+cur.exp);
            cur = cur.next;
        }
        out.close();
    }
}
class node{
    int coe;
    int exp;
    node next;
    public node(int coe, int exp){
        this.coe = coe;
        this.exp = exp;
    }
}
class QReader {
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

class QWriter implements Closeable {
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
