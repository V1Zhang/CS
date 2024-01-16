import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class lab6_F {
    public static void main(String[] args) {
        QReader5 in = new QReader5();
        QWriter5 out = new QWriter5();
        int n = in.nextInt();
        nodeF[] nodes = new nodeF[n+1];
        for(int i=1;i<n+1;i++){
            nodes[i] = new nodeF();
        }
        for(int i=0;i<n-1;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u].children.add(nodes[v]);
            nodes[v].children.add(nodes[u]);
        }
        int index = 1;
        int max = 0;
        for(int i=1;i<n+1;i++) {
            nodes[i].e = in.nextInt();
            if(nodes[i].e>max) {
                max = nodes[i].e;
                index = i;
            }
        }
        nodeF[] queue = new nodeF[n];
        int front = 0;
        queue[0] = nodes[index];
        nodes[index].flag = true;
        int rear = 1;
        while(front!=rear) {
            for (int s = 0; s < queue[front].children.size(); s++) {
                if (!queue[front].children.get(s).flag) {
                    queue[rear] = queue[front].children.get(s);
                    queue[front].children.get(s).flag = true;
                    queue[front].real.add(queue[front].children.get(s));
                    rear++;
                }
            }
            front++;
        }
        long sum = 0;
        traverse_up(nodes[index]);
        for(int i=0;i<nodes[index].real.size();i++){
            traverse_down(nodes[index].real.get(i));
        }
            int first = 0;
            nodeF first_node = new nodeF();
            int second = 0;
            nodeF second_node = new nodeF();
            for(int i=0;i<nodes[index].real.size();i++){
                if(nodes[index].real.get(i).e>first){
                    first_node = nodes[index].real.get(i);
                    first = nodes[index].real.get(i).e;
                }
            }
            first_node.e = nodes[index].e;
            traverse_down(first_node);
            if(nodes[index].real.size()>1) {
                for (int i = 0; i < nodes[index].real.size(); i++) {
                    if (nodes[index].real.get(i).e > second && nodes[index].real.get(i)!=first_node) {
                        second_node = nodes[index].real.get(i);
                        second = nodes[index].real.get(i).e;
                    }
                }
                second_node.e = nodes[index].e;
                traverse_down(second_node);
            }
            for(int i=1;i<n+1;i++) {
                if (nodes[i].real.isEmpty()) {
                    sum += nodes[i].e;
                }
            }
            if(nodes[index].real.size()==1){
                sum+=nodes[index].e;
            }
            out.println(sum);
            out.close();
        }
    // down-up
    public static void traverse_up(nodeF a){
        for(int i=0; i<a.real.size();i++){
            traverse_up(a.real.get(i));
        }
        int max_son_e = 0;
        for (int i=0;i<a.real.size();i++){
            if(a.real.get(i).e>max_son_e){
                max_son_e = a.real.get(i).e;
            }
        }
        if(max_son_e>a.e){
            a.e=max_son_e;
        }
    }
    // up-down
    public static void traverse_down(nodeF a){
        if(a.real.isEmpty()) return;
        int max = 0;
        int point = 0;
        nodeF max_son;
        for(int i=0; i<a.real.size();i++){
            if(a.real.get(i).e>max){
                max = a.real.get(i).e;
                point = i;
            }
        }
        a.real.get(point).e = a.e;
        for(int i=0;i<a.real.size();i++){
            traverse_down(a.real.get(i));
        }
    }
}
class nodeF{
    boolean flag;
    ArrayList<nodeF> children = new ArrayList<>();
    ArrayList<nodeF> real = new ArrayList<>();
    int e;
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



