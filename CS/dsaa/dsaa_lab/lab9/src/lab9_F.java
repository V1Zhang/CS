import java.io.*;
import java.util.*;
public class lab9_F {
    public static int k;
    public static void main(String[] args) {
        QReader5 in = new QReader5();
        QWriter5 out = new QWriter5();
        int n = in.nextInt();
        NodeF[] nodes = new NodeF[n + 1];
        int m = in.nextInt();
        int p = in.nextInt();
        k = in.nextInt();
        long[][] distance = new long[n + 1][k+1];
        for(int i=1;i<n+1;i++){
            nodes[i] = new NodeF();
            nodes[i].index = i;
            for(int j=0;j<=k;j++) {
                distance[i][j] = Long.MAX_VALUE;
                nodes[i].flag.add(false);
            }
        }
        for(int i=0;i<m;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u].children.add(nodes[v]);
            int w = in.nextInt();
            nodes[u].weight.add(w);
        }
        for(int i=0;i<p;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u].door.add(nodes[v]);
        }
        int S = in.nextInt();
        int T = in.nextInt();
        HeapNodeF[] heap = new HeapNodeF[(k+1)*(n+m+p)+1];
        NodeF source = nodes[S];
        for(int j=0;j<=k;j++) {
            distance[S][j] = 0;
            source.flag.set(j, true);
        }
        heap[1] = new HeapNodeF(source, distance[S][0], 0);
        int count = 1;
        while(count!=0){
            NodeF root = heap[1].node;
            int current_level = heap[1].level;
            while (root.flag.get(current_level)) {
                if(count>=1){
                    heap[1] = heap[count];
                    count--;
                    traverse(count, heap);
                    root = heap[1].node;
                    current_level = heap[1].level;
                }
                else{
                    break;
                }
            }
            root.flag.set(current_level, true);
            for(int i=0;i<root.children.size();i++){
                NodeF child = root.children.get(i);
                if(!child.flag.get(current_level)) {
                    long dist = distance[root.index][current_level] + root.weight.get(i);
                    if (dist < distance[child.index][current_level]) {
                        distance[child.index][current_level] = dist;
                        count++;
                        heap[count] = new HeapNodeF(child, dist, current_level);
                        int point = count;
                        while (point > 1) {
                            if (heap[point].dist < heap[point / 2].dist) {
                                HeapNodeF temp = heap[point / 2];
                                heap[point / 2] = heap[point];
                                heap[point] = temp;
                                point = point / 2;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            for(int i=0;i<root.door.size();i++){
                NodeF child = root.door.get(i);
                if (current_level + 1 <= k) {
                    if(!child.flag.get(current_level+1)) {
                        long dist = distance[root.index][current_level];
                        if (dist < distance[child.index][current_level]) {
                            distance[child.index][current_level + 1] = dist;
                            count++;
                            heap[count] = new HeapNodeF(child, dist, current_level + 1);
                            int point = count;
                            while (point > 1) {
                                if (heap[point].dist < heap[point / 2].dist) {
                                    HeapNodeF temp = heap[point / 2];
                                    heap[point / 2] = heap[point];
                                    heap[point] = temp;
                                    point = point / 2;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        long min = Long.MAX_VALUE;
        for(int i=0;i<=k;i++){
            if(distance[T][i]<min){
                min = distance[T][i];
            }
        }
        out.println(min);
        out.close();
    }

    public static void traverse(int num, HeapNodeF[] heap) {
        int point = 1;
        while (2 * point <= num) {
            HeapNodeF min;
            int index = 0;
            if (2 * point + 1 <= num) {
                if (heap[2 * point].dist > heap[2 * point + 1].dist) {
                    min = heap[2 * point + 1];
                    index = 2 * point + 1;
                } else {
                    min = heap[2 * point];
                    index = 2 * point;
                }
            } else {
                min = heap[2 * point];
                index = 2 * point;
            }
            if (heap[point].dist > min.dist) {
                HeapNodeF t = min;
                heap[index] = heap[point];
                heap[point] = t;
                point = index;
            } else {
                break;
            }
        }
    }
}
class NodeF{
    int index;
    ArrayList<NodeF> children = new ArrayList<>();
    ArrayList<NodeF> door = new ArrayList<>();
    ArrayList<Integer> weight = new ArrayList<>();
    ArrayList<Boolean> flag = new ArrayList<Boolean>();
}
class HeapNodeF {
    public int level;
    public NodeF node;
    public long dist;
    public HeapNodeF(NodeF node, long dist, int level) {
        this.node = node;
        this.dist = dist;
        this.level = level;
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








