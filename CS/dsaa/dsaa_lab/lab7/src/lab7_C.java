import java.io.*;
import java.util.*;
public class lab7_C {
    public static void main(String[] args) {
        QReader2 in = new QReader2();
        QWriter2 out = new QWriter2();
        int N = in.nextInt();
        long[] A = new long[N+1];
        int M = in.nextInt();
        long[] B = new long[M+1];
        int K = in.nextInt();
        int k = 0;
        for (int i = 1; i < N+1; i++) {
            A[i] = in.nextLong();
        }
        for (int j = 1; j < M+1; j++) {
            B[j] = in.nextLong();
        }
        long[] temp = new long[N];
        MergeSort(A, temp, 1, N);
        nodeC[] heap = new nodeC[M + 1];
        for (int j = 1; j < M + 1; j++) {
            heap[j] = new nodeC();
            heap[j].val =  A[1] * B[j];
            heap[j].i = 1;
            heap[j].j = j;
            int point = j;
            while (point > 1) {
                if (heap[point].val < heap[point / 2].val) {
                    nodeC t = heap[point / 2];
                    heap[point / 2] = heap[point];
                    heap[point] = t;
                    point = point / 2;
                } else {
                    break;
                }
            }
        }
        int[] num = new int[M+1];
        Arrays.fill(num, N-1);
        if (k < K) {
            out.print(heap[1].val + " ");
            k++;
        }
        int count = M;
        while(count>0) {
            if (num[heap[1].j] > 0) {
                heap[1].val = A[heap[1].i + 1] * B[heap[1].j];
                num[heap[1].j]--;
                heap[1].i = heap[1].i + 1;
                traverse(count, heap);
                if (k < K) {
                    out.print(heap[1].val + " ");
                    k++;
                }
            } else {
                heap[1] = heap[count];
                traverse(count, heap);
                count--;
                if (k < K) {
                    out.print(heap[1].val + " ");
                    k++;
                }
            }
            if (k >= K) {
                break;
            }
        }
        out.close();
    }
    public static void traverse(int num, nodeC[] heap){
        int point = 1;
        while (2 * point <= num) {
            nodeC min;
            int index = 0;
            if (2 * point + 1 <= num) {
                if (heap[2 * point].val > heap[2 * point + 1].val) {
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
            if (heap[point].val > min.val) {
                nodeC t = min;
                heap[index] = heap[point];
                heap[point] = t;
                point = index;
            } else {
                break;
            }
        }
    }
    public static void MergeSort(long[] list,long[] temp,int left,int right) {
        if (left<right) {
            int mid = (left+right)/2;
            MergeSort(list,temp,left,mid);
            MergeSort(list,temp,mid+1,right);
            merge(left, right, mid, list, temp);
        }
    }
    public static void merge(int left, int right, int mid, long[] list, long[] temp) {
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
class nodeC{
    int i;
    int j;
    long val;
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








