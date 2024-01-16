import java.io.*;
import java.util.*;
public class lab8_D {
    public static int time = 0;
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int T = in.nextInt();
        for(int t=0;t<T;t++){
            int n = in.nextInt();
            int m = in.nextInt();
            nodeD[] nodes = new nodeD[n+1];
            for (int i = 1; i < n + 1; i++) {
                nodeD a = new nodeD();
                nodes[i] = a;
            }
            for(int i=0;i<n-1;i++){
                int x = in.nextInt();
                int y = in.nextInt();
                nodes[y].children.add(nodes[x]);
                nodes[x].isChildren=true;
            }
            nodeD root = null;
            for(int i=1;i<n+1;i++){
                if(!nodes[i].isChildren){
                    root = nodes[i];
                }
            }
            nodeD[] stack = new nodeD[n];
            int top = -1;
            DFS(root, stack, top);

            for(int i=0;i<m;i++){
                int x = in.nextInt();
                int y = in.nextInt();
                if(nodes[x].in>=nodes[y].in && nodes[x].out<=nodes[y].out) {
                    out.println("Yes");
                }else {
                    out.println("No");
                }
            }
        }
        out.close();
    }


    private static void DFS(nodeD node, nodeD[] stack, int top){
        top++;
        stack[top] = node;
        time++;
        node.in = time;
        for(int c=0;c<node.children.size();c++) {
            DFS(node.children.get(c), stack, top);
        }
        top--;
        time++;
        node.out = time;
    }
}
class nodeD{
    ArrayList<nodeD> children = new ArrayList<>();
    boolean isChildren;
    int in;
    int out;
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








