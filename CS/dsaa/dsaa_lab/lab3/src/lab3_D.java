import java.io.*;
import java.util.*;

public class lab3_D {
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        nodeD head = new nodeD(-1000000000);
        nodeD tail = new nodeD(-1000000000);
        int T = in.nextInt();
        for(int i=0;i<T;i++) {
            int n = in.nextInt();
            int[] ans = new int[(n+1) / 2];
            nodeD[] NodeList = new nodeD[n];
            for (int j = 0; j < n; j++) {
                nodeD a = new nodeD(in.nextInt());
                NodeList[j] = a;
            }
            nodeD[] copy = new nodeD[n + 2];
            copy[0] = head;
            copy[n + 1] = tail;
            nodeD[] temp = new nodeD[n + 2];
            System.arraycopy(NodeList, 0, copy, 1, n);
            MergeSort(copy, temp, 1, n);
            nodeD cur = head;
            for(int p=1;p<=n;p++){
                cur.next = copy[p];
                copy[p].prev = cur;
                cur = cur.next;
            }
            cur.next = tail;
            tail.prev = cur;

            nodeD mid = copy[(n+1)/2];
            int k=n-1;
            while(k>=0){
                ans[k/2] = mid.value;
                if(k>=2) {
                    nodeD odd = NodeList[k];
                    nodeD even = NodeList[k - 1];
                    if (odd.value >= mid.value && even.value >= mid.value) {
                        mid = mid.prev;
                    } else if (odd.value <= mid.value && even.value <= mid.value) {
                        mid = mid.next;
                    }
                    odd = odd.next;
                    odd.prev = odd.prev.prev;
                    odd.prev.next = odd;

                    even = even.next;
                    even.prev = even.prev.prev;
                    even.prev.next = even;
                }
                k -= 2;
            }

            for (int t = 0; t < ans.length; t++) {
                out.print(ans[t]+" ");
            }
            out.println("");
        }
        out.close();
    }

    public static void MergeSort(nodeD[] list,nodeD[] temp,int left,int right) {
        if (left<right) {
            int mid = (left+right)/2;
            MergeSort(list,temp,left,mid);
            MergeSort(list,temp,mid+1,right);
            merge(left, right, mid, list, temp);
        }
    }
    public static void merge(int left, int right, int mid, nodeD[] list, nodeD[] temp) {
        int i = left;
        int j = mid+1;
        int k = 0;
        while(i <= mid && j <= right){
            if(list[i].value < list[j].value
            ){
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
class nodeD{
    int value;
    nodeD next;
    nodeD prev;
    public nodeD(int value){
        this.value = value;
    }
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
