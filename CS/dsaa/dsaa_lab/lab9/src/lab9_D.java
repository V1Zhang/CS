import java.io.*;
import java.util.*;
public class lab9_D {
    public static int k;
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int n = in.nextInt();
        NodeD[] nodes = new NodeD[n + 1];
        int m = in.nextInt();
        k = in.nextInt();
        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new NodeD();
            nodes[i].time = new int[k+1];
        }
        int c = in.nextInt();
        for(int i=1;i<n+1;i++){
            nodes[i].color = in.nextInt();
            for(int j=0;j<=k;j++){
                nodes[i].time[j] = Integer.MAX_VALUE;
            }
        }
        for(int i=0;i<m;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            nodes[u].children.add(nodes[v]);
            nodes[v].children.add(nodes[u]);
        }
        NodeD[] queue = new NodeD[n];
        for(int i=1;i<k+1;i++){
            for(int j=1;j<n+1;j++){
                nodes[j].flag = false;
            }
            int front = 0;
            int rear = 0;
            for(int j=1;j<n+1;j++){
                if(nodes[j].color==i){
                    queue[rear] = nodes[j];
                    nodes[j].flag = true;
                    nodes[j].time[i] = 0;
                    rear++;
                }
            }
            while (front != rear) {
                for (int k = 0; k < queue[front].children.size(); k++) {
                    if (!queue[front].children.get(k).flag) {
                        queue[rear] = queue[front].children.get(k);
                        queue[front].children.get(k).flag = true;
                        if(queue[front].time[i] + 1<queue[front].children.get(k).time[i]) {
                            queue[front].children.get(k).time[i] = queue[front].time[i] + 1;
                        }
                        rear++;
                    }
                }
                front++;
            }
        }
        for(int i=1;i<n+1;i++){
            int[] temp = new int[k+1];
            MergeSort(nodes[i].time, temp, 1,k);
            int cost = 0;
            for(int j=1;j<=c;j++){
                cost+=nodes[i].time[j];
            }
            out.print(cost+" ");
        }
        out.close();
    }
    public static void MergeSort(int[] list,int[] temp,int left,int right) {
        if (left<right) {
            int mid = (left+right)/2;
            MergeSort(list,temp,left,mid);
            MergeSort(list,temp,mid+1,right);
            merge(left, right, mid, list, temp);
        }
    }
    public static void merge(int left, int right, int mid, int[] list, int[] temp) {
        int i = left;
        int j = mid+1;
        int k = 0;
        while(i <= mid && j <= right){
            if(list[i] < list[j]){
                temp[k++] = list[i++];
            }else{
                temp[k++] = list[j++];
            }
        }
        while(i <= mid){
            temp[k++] = list[i++];
        }
        while(j <= right){
            temp[k++] = list[j++];
        }
        for(int t=0;t<k;t++){
            list[left+t] = temp[t];
        }
    }
}
class NodeD{
    int color;
    ArrayList<NodeD> children = new ArrayList<>();
    int[] time;
    boolean flag = false;
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








