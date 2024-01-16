import java.io.*;
import java.util.*;
public class lab8_C {
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        int t = in.nextInt();
        for(int i=0;i<t;i++){
            int n = in.nextInt();
            int m = in.nextInt();
            nodeC[] nodes = new nodeC[n+1];
            for (int j = 1; j < n + 1; j++) {
                nodeC a = new nodeC();
                nodes[j] = a;
            }
            for(int j=0;j<m;j++){
                int u = in.nextInt();
                int v = in.nextInt();
                nodes[u].children.add(nodes[v]);
                nodes[v].children.add(nodes[u]);
            }
            nodeC[] queue = new nodeC[n];
            int front = 0;
            queue[0] = nodes[1];
            nodes[1].visited = true;
            nodes[1].star = true;
            int count = 1;
            int rear = 1;
            while (front != rear) {
                for (int k = 0; k < queue[front].children.size(); k++) {
                    if (!queue[front].children.get(k).visited) {
                        queue[rear] = queue[front].children.get(k);
                        queue[front].children.get(k).visited = true;
                        if(!queue[front].star){
                            queue[front].children.get(k).star = true;
                            count++;
                        }
                        rear++;
                    }
                }
                front++;
            }
            if(count<=n/2) {
                out.println(count);
                for (int j = 1; j < n + 1; j++) {
                    if (nodes[j].star) {
                        out.print(j + " ");
                    }
                }
                out.print("\n");
            }else{
                out.println(n - count);
                for (int j = 1; j < n + 1; j++) {
                    if (!nodes[j].star) {
                        out.print(j + " ");
                    }
                }
                out.print("\n");
            }
        }
        out.close();
    }
}
class nodeC{
    ArrayList<nodeC> children = new ArrayList<>();
    boolean visited;
    int path = -1;
    boolean star;
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








