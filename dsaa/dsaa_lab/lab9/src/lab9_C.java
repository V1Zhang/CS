import java.io.*;
import java.util.*;
public class lab9_C{
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        int n = in.nextInt();
        int m = in.nextInt();
        NodeC[][] matrix = new NodeC[n+1][m+1];
        int[][] gain = new int[n+1][m+1];
        long max = 0;
        for (int i=1;i<n+1;i++) {
            for (int j = 1; j < m + 1; j++) {
                NodeC node = new NodeC();
                node.x = i;
                node.y = j;
                matrix[i][j] = node;
                node.c = in.nextInt();
            }
        }
        for (int i=1;i<n+1;i++){
            for (int j=1;j<m+1;j++) {
                if (i - 1 > 0) {
                    matrix[i][j].children.add(matrix[i - 1][j]);
                }
                if (i + 1 < n + 1) {
                    matrix[i][j].children.add(matrix[i + 1][j]);
                }
                if (j - 1 > 0) {
                    matrix[i][j].children.add(matrix[i][j - 1]);
                }
                if (j + 1 < m + 1) {
                    matrix[i][j].children.add(matrix[i][j + 1]);
                }
            }
        }
        HeapNodeC[] heap = new HeapNodeC[m * n+1];
        heap[1] = new HeapNodeC(matrix[1][1], 0);
        gain[1][1] = 0;
        int count = 1;
        NodeC source = matrix[1][1];
        source.isVisited = true;
        while (count != 0) {
            NodeC root = heap[1].node;
            while (root.isVisited) {
                if (count >= 1) {
                    heap[1] = heap[count];
                    count--;
                    traverse(count, heap);
                    root = heap[1].node;
                } else {
                    break;
                }
            }
            root.isVisited = true;
            for (int i = 0; i < root.children.size(); i++) {
                if(!root.children.get(i).isVisited) {
                    int mul = root.c * root.children.get(i).c;
                    if (mul > gain[root.children.get(i).x][root.children.get(i).y]) {
                        count++;
                        heap[count] = new HeapNodeC(root.children.get(i), mul);
                        gain[root.children.get(i).x][root.children.get(i).y] = mul;
                        int point = count;
                        while (point > 1) {
                            if (heap[point].dist > heap[point / 2].dist) {
                                HeapNodeC temp = heap[point / 2];
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
        for (int i=1;i<n+1;i++){
            for (int j=1;j<m+1;j++) {
                max+=gain[i][j];
            }
        }
        out.print(max);
        out.close();
    }
    public static void traverse(int num, HeapNodeC[] heap) {
        int point = 1;
        while (2 * point <= num) {
            HeapNodeC max;
            int index = 0;
            if (2 * point + 1 <= num) {
                if (heap[2 * point].dist < heap[2 * point + 1].dist) {
                    max = heap[2 * point + 1];
                    index = 2 * point + 1;
                } else {
                    max = heap[2 * point];
                    index = 2 * point;
                }
            } else {
                max = heap[2 * point];
                index = 2 * point;
            }
            if (heap[point].dist < max.dist) {
                HeapNodeC t = max;
                heap[index] = heap[point];
                heap[point] = t;
                point = index;
            } else {
                break;
            }
        }
    }
}
class NodeC{
    int x;
    int y;
    int c;
    ArrayList<NodeC> children = new ArrayList<>();
    boolean isVisited = false;
}
class HeapNodeC {
    public NodeC node;
    public long dist;
    public HeapNodeC(NodeC parent, long dist) {
        this.node = parent;
        this.dist = dist;
    }
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








