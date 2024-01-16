import java.io.*;
import java.util.*;

public class lab3_C {
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        nodeC head = new nodeC(10001);
        nodeC tail = new nodeC(-10001);
        int T = in.nextInt();
        for(int i=0;i<T;i++) {
            int N = in.nextInt();
            int M = in.nextInt();
            nodeC[] NodeList = new nodeC[N];
            head.next = tail;
            nodeC cur = head;
            for (int j = 0; j < N; j++) {
                int num = in.nextInt();
                nodeC a = new nodeC(num);
                cur.next = a;
                a.prev = cur;
                a.next = tail;
                tail.prev = a;
                cur = cur.next;
                NodeList[num] = cur;
            }
            for (int k = 0; k < M; k++) {
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();
                nodeC left = NodeList[x2].prev;
                nodeC right = NodeList[y2].next;
                NodeList[x1].prev.next = NodeList[x2];
                NodeList[x2].prev = NodeList[x1].prev;
                if(NodeList[y1].next!=NodeList[x2]){
                    NodeList[y2].next = NodeList[y1].next;
                    NodeList[y1].next.prev = NodeList[y2];
                    left.next = NodeList[x1];
                    NodeList[x1].prev = left;
                }else{
                    NodeList[y2].next = NodeList[x1];
                    NodeList[x1].prev = NodeList[y2];
                }
                right.prev = NodeList[y1];
                NodeList[y1].next = right;
            }

            cur = head.next;
            while (cur != tail) {
                out.print(cur.value + " ");
                cur = cur.next;
            }
            out.println("");
        }
        out.close();
    }
}
class nodeC{
    int value;
    nodeC next;
    nodeC prev;
    public nodeC(int value){
        this.value = value;
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
