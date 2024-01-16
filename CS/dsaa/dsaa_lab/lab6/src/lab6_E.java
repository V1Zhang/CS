import java.io.*;
import java.util.*;
public class lab6_E {
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        int n = in.nextInt();
        nodeE[] nodes = new nodeE[n+1];
        for(int i=1;i<n+1;i++){
            nodes[i] = new nodeE();
            nodes[i].val = i;
        }
        for (int i = 0; i < n-1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u].children.add(nodes[v]);
            nodes[v].children.add(nodes[u]);
        }
        nodeE[] queue = new nodeE[n];
        queue[0] = nodes[1];
        nodes[1].flag = true;
        nodes[1].level = 0;
        int rear = 1;
        // divide into son-roots problem
        nodeE[] roots = new nodeE[queue[0].children.size()];
        for(int s=0;s<queue[0].children.size();s++){
            roots[s] = queue[0].children.get(s);
            queue[rear] = queue[0].children.get(s);
            queue[0].children.get(s).flag = true;
            queue[0].children.get(s).level=1;
            rear++;
        }
        for(int i=0;i< roots.length;i++){
            nodeE root = roots[i];
            nodeE[] root_queue = new nodeE[n];
            root_queue[0] = root;
            rear = 1;
            int front = 0;
            while(front!=rear) {
                for (int s = 0; s < root_queue[front].children.size(); s++) {
                    if (!root_queue[front].children.get(s).flag) {
                        root_queue[rear] = root_queue[front].children.get(s);
                        root_queue[front].children.get(s).flag = true;
                        root_queue[front].children.get(s).level = root_queue[front].level+1;
                        root.s.add(root_queue[front].children.get(s));
                        rear++;
                    }
                }
                front++;
            }
        }
        int m = in.nextInt();
        int giant = 0;
        for(int i=0;i<m;i++){
            giant = in.nextInt();
            nodes[giant].choosen = true;
        }
        int max = 0;
        for(int i=0;i<roots.length;i++) {
            nodeE root = roots[i];
            int count = 0;
            for (int j = 0; j < root.s.size(); j++) {
                if(root.s.get(j).choosen) {
                    if (root.s.get(j).level == 1) {
                        count = 1;
                    } else {
                        if (root.s.get(j).level <= count + 1) {
                            count++;
                        } else {
                            count = root.s.get(j).level;
                        }
                    }
                }
            }
            max = Math.max(max,count);
        }
        out.println(max);
        out.close();
    }
}

class nodeE{
    int val;
    ArrayList<nodeE> children = new ArrayList<>();
    ArrayList<nodeE> s = new ArrayList<>();
    boolean flag = false;
    boolean choosen = false;
    int level;
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








