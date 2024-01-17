import java.io.*;
import java.util.*;
public class lab8_B {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            node[] nodes = new node[n + 1];
            for (int i = 1; i < n + 1; i++) {
                node a = new node();
                nodes[i] = a;
            }
            int m = in.nextInt();
            int s = in.nextInt();
            for (int i = 0; i < m; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                nodes[x].children.add(nodes[y]);
                nodes[y].children.add(nodes[x]);
            }
            node[] queue = new node[n];
            int front = 0;
            queue[0] = nodes[s];
            nodes[s].visited = true;
            nodes[s].path = 0;
            int rear = 1;
            while (front != rear) {
                for (int k = 0; k < queue[front].children.size(); k++) {
                    if (!queue[front].children.get(k).visited) {
                        queue[rear] = queue[front].children.get(k);
                        queue[front].children.get(k).visited = true;
                        queue[front].children.get(k).path = queue[front].path + 1;
                        rear++;
                    }
                }
                front++;
            }
            for (int i = 1; i < n + 1; i++) {
                out.print(nodes[i].path + " ");
            }
            out.print('\n');
        }
        out.close();
    }
}
class node{
    ArrayList<node> children = new ArrayList<>();
    boolean visited;
    int path = -1;
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








