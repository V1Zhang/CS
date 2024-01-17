import java.io.*;
import java.util.*;
public class lab9_A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        Node[] nodes = new Node[n+1];
        for(int i=1;i<n+1;i++){
            nodes[i] = new Node();
            nodes[i].index = i;
        }
        int m = in.nextInt();
        long min = Long.MAX_VALUE;
        long[] distance = new long[n+1];
        for (int i=1;i<n+1;i++){
            distance[i] = Long.MAX_VALUE;
        }
        for(int i=0;i<m;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u].children.add(nodes[v]);
            long w = in.nextLong();
            nodes[u].weight.add(w);
        }
        Node source = nodes[1];
        HeapNode[] heap = new HeapNode[m+n];
        distance[1] = 0;
        heap[1] = new HeapNode(source, distance[1]);
        int count = 1;
        while(count!=0){
            Node root = heap[1].node;
            for(int i=0;i<root.children.size();i++){
                Node child = root.children.get(i);
                if(!child.flag) {
                    long dist = distance[root.index] + root.weight.get(i);
                    if (dist < distance[child.index]) {
                        distance[child.index] = dist;
                    }
                    count++;
                    heap[count] = new HeapNode(child, dist);
                    int point = count;
                    while (point > 1) {
                        if (heap[point].dist < heap[point / 2].dist) {
                            HeapNode temp = heap[point / 2];
                            heap[point / 2] = heap[point];
                            heap[point] = temp;
                            point = point / 2;
                        } else {
                            break;
                        }
                    }
                }
            }
            root.flag = true;
            while (root.flag) {
                if(count>=1){
                    heap[1] = heap[count];
                    count--;
                    traverse(count, heap);
                    root = heap[1].node;
                }
                else{
                    break;
                }
            }
        }
        min = distance[n];
        if(min==0){
            min = -1;
        }
        out.print(min);
        out.close();
    }

    public static void traverse(int num, HeapNode[] heap) {
        int point = 1;
        while (2 * point <= num) {
            HeapNode min;
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
                HeapNode t = min;
                heap[index] = heap[point];
                heap[point] = t;
                point = index;
            } else {
                break;
            }
        }
    }
}
class Node{
    int index;
    ArrayList<Node> children = new ArrayList<>();
    ArrayList<Long> weight = new ArrayList<>();
    Node parent;
    boolean flag = false;
}
class HeapNode {
    public Node node;
    public long dist;
    public HeapNode(Node node, long dist) {
        this.node = node;
        this.dist = dist;
    }
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








