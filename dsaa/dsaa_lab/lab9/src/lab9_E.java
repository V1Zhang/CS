import java.io.*;
import java.util.*;
public class lab9_E {
    public static int time = 0;
    public static ArrayList<Integer> LR = new ArrayList<>();
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        int n = in.nextInt();
        NodeE[] nodes = new NodeE[n + 1];
        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new NodeE();
            nodes[i].index = i;
        }
        int m = in.nextInt();
        Edge[] edges = new Edge[m];
        int e=0;
        int S = in.nextInt();
        NodeE target = nodes[S];
        for(int i=0;i<m;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u].children.add(nodes[v]);
            nodes[v].reverse.add(nodes[u]);
            edges[e] = new Edge(nodes[u], nodes[v]);
            e++;
        }
        NodeE[] stack = new NodeE[n];
        int top = -1;
        for (int i = 1; i <= n; i++) {
            NodeE root = nodes[i];
            if (!root.flag) {
                DFS(root, stack, top);
            }
        }
        int[] L = new int[n+1];
        for(int i=1;i<n+1;i++){
            L[i] = LR.get(n-i);
        }
        NodeE root2 = nodes[L[1]];
        top = -1;
        int pointer = 2;
        int num = 1;
        while(pointer<=n) {
            DFS2(root2, stack, top, num);
            while(nodes[L[pointer]].isVisited){
                pointer++;
                if(pointer>n){
                    break;
                }
            }
            if(pointer<=n) {
                root2 = nodes[L[pointer]];
                num++;
                pointer++;
            }
        }
        int IN[] = new int[num+1];
        int target_color = target.color;
        for(int i=0;i<m;i++){
            NodeE parent = edges[i].parent;
            NodeE child = edges[i].child;
            if(parent.color!= child.color){
                IN[child.color]+=1;
            }

        }
        int cnt = 0;
        for(int i=1;i<num+1;i++){
            if(IN[i]==0){
                cnt++;
            }
        }
        if(IN[target_color]==0) {
            out.println(cnt-1);
        }else{
            out.println(cnt);
        }
        out.close();
    }

    private static void DFS(NodeE node, NodeE[] stack, int top){
        top++;
        stack[top] = node;
        node.flag = true;
        time++;
        node.in = time;
        for(int c=0;c<node.reverse.size();c++) {
            if(!node.reverse.get(c).flag) {
                DFS(node.reverse.get(c), stack, top);
            }
        }
        top--;
        time++;
        node.out = time;
        LR.add(node.index);
    }
    private static void DFS2(NodeE node, NodeE[] stack, int top, int num){
        top++;
        stack[top] = node;
        node.isVisited = true;
        node.color = num;
        for(int c=0;c<node.children.size();c++) {
            if(!node.children.get(c).isVisited) {
                DFS2(node.children.get(c), stack, top, num);
            }
        }
        top--;
    }
}

class NodeE{
    int index;
    ArrayList<NodeE> children = new ArrayList<>();
    ArrayList<NodeE> reverse = new ArrayList<>();
    int color;
    boolean flag = false;
    boolean isVisited = false;
    int in;
    int out;
}
class Edge{
    NodeE parent;
    NodeE child;
    public Edge(NodeE parent, NodeE child){
        this.parent = parent;
        this.child = child;
    }
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








