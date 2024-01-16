import java.io.*;
import java.util.*;
public class lab9_B {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        int n = in.nextInt();
        NodeB[] nodes = new NodeB[n + 1];
        long[] distance = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            distance[i] = Long.MAX_VALUE;
            nodes[i] = new NodeB();
            nodes[i].index = i;
        }
        long sum = 0;
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            long w = in.nextLong();
            if(w>0) {
                sum+=w;
            }
            nodes[u].children.add(nodes[v]);
            nodes[v].children.add(nodes[u]);
            nodes[u].weight.add(w);
            nodes[v].weight.add(w);
        }
        HeapNodeB[] heap = new HeapNodeB[m + n];
        heap[1] = new HeapNodeB(nodes[1], 0);
        distance[1] = 0;
        int count = 1;
        NodeB source = nodes[1];
        source.flag = true;
        while (count != 0) {
            NodeB root = heap[1].node;
            while (root.flag) {
                if (count >= 1) {
                    heap[1] = heap[count];
                    count--;
                    traverse(count, heap);
                    root = heap[1].node;
                } else {
                    break;
                }
            }
            root.flag = true;
            for (int i = 0; i < root.children.size(); i++) {
                if(!root.children.get(i).flag) {
                    if (root.weight.get(i) < distance[root.children.get(i).index]) {
                        count++;
                        heap[count] = new HeapNodeB(root.children.get(i), root.weight.get(i));
                        distance[root.children.get(i).index] = root.weight.get(i);
                        int point = count;
                        while (point > 1) {
                            if (heap[point].dist < heap[point / 2].dist) {
                                HeapNodeB temp = heap[point / 2];
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
        for(int i=1;i<n+1;i++){
            if(distance[i]>0){
                sum-=distance[i];
            }
        }
        out.print(sum);
        out.close();
    }
    public static void traverse(int num, HeapNodeB[] heap) {
        int point = 1;
        while (2 * point <= num) {
            HeapNodeB min;
            int index = 0;
            if (2 * point + 1 <= num) {
                if (heap[2 * point].dist > heap[2 * point + 1].dist) {
                    min = heap[2 * point + 1];
                    index = 2 * point + 1;
                }  else {
                    min = heap[2 * point];
                    index = 2 * point;
                }
            } else {
                min = heap[2 * point];
                index = 2 * point;
            }
            if (heap[point].dist > min.dist) {
                HeapNodeB t = min;
                heap[index] = heap[point];
                heap[point] = t;
                point = index;
            } else {
                break;
            }
        }
    }
}
class NodeB{
    int index;
    ArrayList<NodeB> children = new ArrayList<>();
    ArrayList<Long> weight = new ArrayList<>();
    boolean flag = false;
}
class HeapNodeB {
    public NodeB node;
    public long dist;
    public HeapNodeB(NodeB parent, long dist) {
        this.node = parent;
        this.dist = dist;
    }
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








