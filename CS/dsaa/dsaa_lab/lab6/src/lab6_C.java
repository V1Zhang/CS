import java.io.*;
import java.util.*;
public class lab6_C {
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        int T = in.nextInt();
        for(int i=0;i<T;i++) {
            int N = in.nextInt();
            int[] pre_order = new int[N];
            int[] in_order = new int[N];
            for(int j=0;j<N;j++){
                pre_order[j] = in.nextInt();
            }
            for(int j=0;j<N;j++){
                in_order[j] = in.nextInt();
            }
            nodeC[] nodes = new nodeC[N + 1];
            for (int j = 1; j < N + 1; j++) {
                nodes[j] = new nodeC();
                nodes[j].val = j;
            }
            nodeC root =  nodes[pre_order[0]];
            traversal(0,N-1,0,N-1,pre_order,in_order,nodes);
            PostOrder(root,out);
            out.println("");
        }
        out.close();
    }
    public static void traversal(int pl, int pr, int il, int ir, int[] pre_order, int[] in_order, nodeC[] nodes){
        int k=il;
        while(k<=ir){
            if(in_order[k]==pre_order[pl]){
                break;
            }else {
                k++;
            }
        }
        nodeC root = nodes[pre_order[pl]];
        if(k>il){
            traversal(pl+1,k-il+pl,il,k-1,pre_order,in_order,nodes);
            root.left = nodes[pre_order[pl+1]];
        }
        if(k<ir){
            traversal(k-il+pl+1,pr,k+1,ir,pre_order,in_order,nodes);
            root.right = nodes[pre_order[k-il+pl+1]];
        }
    }
    public static void PostOrder(nodeC root, QWriter2 out) {
        if (root != null) {
            PostOrder(root.left,out);
            PostOrder(root.right,out);
            out.print(root.val + " ");
        }
    }
}
class nodeC{
    nodeC left;
    nodeC right;
    int val;
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








