import java.io.*;
import java.util.*;
public class lab7_D {
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int N = in.nextInt();
        nodeD[] linklist = new nodeD[N+2];
        nodeD head = new nodeD();
        nodeD tail = new nodeD();
        linklist[0] = head;
        linklist[N+1] = tail;
        nodeD[] heap = new nodeD[N+1];
        for(int i=1;i<N+1;i++){
            nodeD node = new nodeD();
            node.index = i;
            node.flag = false;
            node.val = in.nextLong();
            heap[i] = node;
            linklist[i] = node;
            linklist[i].prev = linklist[i-1];
            linklist[i-1].next = linklist[i];
            int point = i;
            while (point > 1) {
                // if values of two nodes are equal, the node with minimal index should at front
                // but as my function construct the heap using the inputs one by one, no need to discuss that case
                if (heap[point].val < heap[point / 2].val) {
                    nodeD temp = heap[point / 2];
                    heap[point / 2] = heap[point];
                    heap[point] = temp;
                    point = point / 2;
                } else {
                    break;
                }
            }
        }
        linklist[N].next = tail;
        tail.prev = linklist[N];


        nodeD root;
        int count = N;
        int num = N;
        while(count!=1){
            root = heap[1];
            while(root.flag){
                heap[1] = heap[num];
                num--;
                traverse(num,heap);
                root = heap[1];
            }
            long before = -1;
            long after = -1;
            nodeD die;
            if(root.prev!=head) {
                before = (root.val ^ root.prev.val)+1;
            }
            if(root.next!=tail) {
                after = (root.val ^ root.next.val)+1;
            }
            if(before>=after){
                root.val = before;
                root.prev.flag = true;
                die = root.prev;
            }else{
                root.val = after;
                root.next.flag = true;
                die = root.next;
            }
            die.prev.next = die.next;
            die.next.prev = die.prev;
            count--;
            if(count!=1) {
                traverse(num, heap);
            }
        }
        out.println(heap[1].val);
        out.close();
    }
    public static void traverse(int num, nodeD[] heap) {
        int point = 1;
        while (2 * point <= num) {
            nodeD min;
            int index = 0;
            if (2 * point + 1 <= num) {
                if (heap[2 * point].val > heap[2 * point + 1].val) {
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
            if (heap[point].val > min.val || (heap[point].val == min.val && heap[point].index > min.index)) {
                nodeD t = min;
                heap[index] = heap[point];
                heap[point] = t;
                point = index;
            } else {
                break;
            }
        }
    }
}
class nodeD{
    long val;
    boolean flag;
    int index;
    nodeD prev;
    nodeD next;
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








