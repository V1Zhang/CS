import java.io.*;
import java.util.*;
public class lab6_B {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        int n = in.nextInt();
        int num = in.nextInt();
        node[] nodes = new node[n+1];
        for(int i=1;i<n+1;i++){
            nodes[i] = new node();
        }
        for(int i=0;i<n-1;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            nodes[u].children.add(nodes[v]);
            nodes[u].w.add(w);
            nodes[v].children.add(nodes[u]);
            nodes[v].w.add(w);
        }
        node[] queue = new node[n];
        int front = 0;
        queue[0] = nodes[1];
        nodes[1].flag = true;
        int rear = 1;
        while(front!=rear) {
            for (int s = 0; s < queue[front].children.size(); s++) {
                if (!queue[front].children.get(s).flag) {
                    queue[rear] = queue[front].children.get(s);
                    queue[front].children.get(s).flag = true;
                    rear++;
                    queue[front].children.get(s).path = queue[front].path + queue[front].w.get(s);
                }
            }
            front++;
        }
        int count = 0;
        for(int i=2;i<n+1;i++){
            if(nodes[i].children.size()==1) {
                if (nodes[i].path == num) {
                    count++;
                }
            }
        }
        out.println(count);
        out.close();
    }
}
class node{
    ArrayList<node> children = new ArrayList<>();
    ArrayList<Integer> w = new ArrayList<>();
    boolean flag = false;
    int path = 0;
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








