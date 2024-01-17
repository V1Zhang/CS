import java.io.*;
import java.util.*;
public class lab7_A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            node[] nodes = new node[n+1];
            for (int j = 1; j < n+1; j++) {
                nodes[j] = new node();
                nodes[j].value = in.nextInt();
            }
            boolean LargerThan = false;
            boolean SmallerThan = false;
            for (int k = 0; k < n - 1; k++) {
                int parent = in.nextInt();
                int child = in.nextInt();
                nodes[child].ischild = true;
                nodes[parent].children.add(nodes[child]);
            }
            node root = null;
            for(int k=1;k<n+1;k++){
                if(!nodes[k].ischild){
                    root = nodes[k];
                    break;
                }
            }
            boolean isHeap = true;
            for (int j = 1; j < n+1; j++) {
                if (nodes[j].children.size() > 2) {
                    isHeap = false;
                    break;
                }
            }
            if (isHeap) {
                boolean find = false;
                node[] queue = new node[n];
                queue[0] = root;
                root.flag_child = true;
                root.level = 1;
                int rear = 1;
                int front = 0;
                int L = 1;
                while(front!=rear) {
                    for (int s = 0; s < queue[front].children.size(); s++) {
                        if (!queue[front].children.get(s).flag_child) {
                            queue[rear] = queue[front].children.get(s);
                            queue[front].children.get(s).flag_child = true;
                            queue[front].children.get(s).level = queue[front].level+1;
                            L = Math.max(queue[front].children.get(s).level,L);
                            rear++;
                        }
                    }
                    front++;
                }
                rear = 1;
                front = 0;
                while(front!=rear) {
                    if (queue[front].level<L-1) {
                        if (queue[front].children.size() != 2){
                            isHeap = false;
                            break;
                        }
                    } else if (queue[front].level==L-1) {
                        if (!find) {
                            if (queue[front].children.size() != 2) {
                                // leaf node or node with only left child
                                find = true;
                            }
                        } else {
                            if (queue[front].children.size() == 1) {
                                isHeap = false;
                                break;
                            }
                        }
                    }
                    for (int s = 0; s < queue[front].children.size(); s++) {
                        if (!queue[front].children.get(s).flag_in) {
                            queue[rear] = queue[front].children.get(s);
                            queue[front].children.get(s).flag_in = true;
                            rear++;
                        }
                    }
                    front++;
                }
            }
            if (isHeap) {
                for (int j = 1; j < n; j++) {
                    node p = nodes[j];
                    for (int k = 0; k < p.children.size(); k++) {
                        if (p.value < p.children.get(k).value) {
                            SmallerThan = true;
                        }
                        if (p.value > p.children.get(k).value) {
                            LargerThan = true;
                        }
                    }
                }
                if(SmallerThan&&LargerThan){
                    isHeap=false;
                }
            }
            if (isHeap) {
                out.println("Case #" + (i + 1) + ": " +  "YES");
            } else {
                out.println("Case #" + (i + 1) + ": " +  "NO");
            }
        }
        out.close();
    }
}
class node {
    int value;
    ArrayList<node> children = new ArrayList<>();
    boolean ischild = false;
    boolean flag_child = false;
    boolean flag_in = false;
    int level;
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








