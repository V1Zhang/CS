import java.io.*;
import java.util.*;

public class lab3_F {
    public static void main(String[] args) {
        QReader5 in = new QReader5();
        QWriter5 out = new QWriter5();
        nodeF head = new nodeF(-1000000001);
        nodeF tail = new nodeF(1000000001);
        int T = in.nextInt();
        for(int i=0;i<T;i++) {
            int n = in.nextInt();
            nodeF[] NodeList = new nodeF[n+2];
            NodeList[0] = head;
            for (int j = 1; j <= n; j++) {
                nodeF a = new nodeF(in.nextInt());
                NodeList[j] = a;
            }
            NodeList[n+1] = tail;
            nodeF cur = NodeList[0];
            for(int p=0;p<n+1;p++){
                cur.next = NodeList[p+1];
                NodeList[p+1].prev = cur;
                cur = cur.next;
            }
            // find decreasing points
            cur = head.next;
            nodeF[] temp = new nodeF[n];
            nodeF[] temp2 = new  nodeF[n];
            int count = 0;
            int count2 = 0;
            while (cur!=tail) {
                if (cur.value > cur.next.value) {
                    cur.flag = false;
                    cur.next.flag = false;
                }
                cur = cur.next;
            }
            // find left and right points
            cur = head;
            while(cur!=tail){
                if(cur.flag && !cur.next.flag){
                    temp[count] = cur;
                    count++;
                }
                if(cur.next.flag && !cur.flag){
                    temp2[count2] = cur.next;
                    count2++;
                }
                cur = cur.next;
            }
            // connect
            for(int j=0;j<count;j++){
                temp[j].next = temp2[j];
                temp2[j].prev = temp[j];
            }
            int record = count;
            while(record!=0) {
                for (int j = 0; j < record; j++) {
                    nodeF left = temp[j];
                    nodeF right = temp[j].next;
                    while (left!=tail.next && right != tail.next) {
                        if (right.flag) {
                            if (left.value > right.value) {
                                left.flag = false;
                                right.flag = false;
                            } else {
                                break;
                            }
                        }
                        left = left.next;
                        right = right.next;
                    }
                }
                count = 0;
                count2 = 0;
                int temp_record = 0;
                for(int j=0;j<record;j++) {
                    if (temp[j] != head) {
                        if (!temp[j].flag && temp[j].prev.flag) {
                            temp[count] = temp[j].prev;
                            count++;
                            temp_record++;
                        }
                    }
                    if (temp2[j] != tail) {
                        if (!temp2[j].flag && temp2[j].next.flag) {
                            temp2[count2] = temp2[j].next;
                            count2++;
                            temp_record++;
                        }
                    }
                }
                record = temp_record/2;
                for(int j=0;j<record;j++) {
                    temp[j].next = temp2[j];
                    temp2[j].prev = temp[j];
                }
            }
            cur = head.next;
            boolean exist = false;
            while(cur!=tail) {
                if(cur.flag) {
                    out.print(cur.value + " ");
                    exist = true;
                }
                cur = cur.next;
            }
            if(!exist){
                out.print(" ");
            }
            out.println("");
        }
        out.close();
    }
}
class nodeF{
    int value;
    nodeF next;
    nodeF prev;
    boolean flag = true;
    public nodeF(int value){
        this.value = value;
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


