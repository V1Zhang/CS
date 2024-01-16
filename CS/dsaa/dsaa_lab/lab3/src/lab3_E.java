import java.io.*;
import java.util.*;

public class lab3_E {
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        nodeE head = new nodeE(-1000000000);
        nodeE tail = new nodeE(-1000000000);
        int n = in.nextInt();
        nodeE[] NodeList = new nodeE[n];
        for(int i=0;i<n;i++){
            nodeE a = new nodeE(in.nextLong());
            NodeList[i] = a;
        }
        nodeE[] copy = new nodeE[n+2];
        copy[0] = head;
        copy[n+1] = tail;
        nodeE[] temp = new nodeE[n+2];
        System.arraycopy(NodeList, 0, copy, 1, n);
        MergeSort(copy,temp,1,n);
        nodeE cur = head;
        for(int i=1;i<=n;i++){
            cur.next = copy[i];
            copy[i].prev = cur;
            cur = cur.next;
        }
        cur.next = tail;
        tail.prev = cur;


        for(int i=0;i<n-1;i++){
            long diff = 0;
            nodeE e = NodeList[i];
            if (e.prev==head) {
                diff = Math.abs(e.next.num - e.num);
            }else {
                diff = Math.min(Math.abs(e.next.num - e.num), Math.abs(e.prev.num - e.num));
            }
            out.print(diff+" ");
            e = e.next;
            e.prev = e.prev.prev;
            e.prev.next = e;
        }
        out.println("");
        out.close();
    }

    public static void MergeSort(nodeE[] list,nodeE[] temp,int left,int right) {
        if (left<right) {
            int mid = (left+right)/2;
            MergeSort(list,temp,left,mid);
            MergeSort(list,temp,mid+1,right);
            merge(left, right, mid, list, temp);
        }
    }
    public static void merge(int left, int right, int mid, nodeE[] list, nodeE[] temp) {
        int i = left;
        int j = mid+1;
        int k = 0;
        while(i <= mid && j <= right){
            if(list[i].num < list[j].num){
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
class nodeE{
    long num;
    nodeE next;
    nodeE prev;
    public nodeE(long num){
        this.num = num;
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
