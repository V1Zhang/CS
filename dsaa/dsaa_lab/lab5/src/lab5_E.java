import java.io.*;
import java.util.*;
public class lab5_E {
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        String s1 = in.next();
        String s2 = in.next();
        int ans = 0;
        int len = s1.length();
        if (s1.length() > s2.length()) {
            len = s2.length();
        }
        int left = 0;
        int right = len;
        int mid = (left + right) / 2;
        while(right>=left) {
                mid = (left + right) / 2;
                long multiply = 1;
                long[] hash = new long[s1.length()-mid+1];
                long[] temp = new long[s1.length()-mid+1];
                for (int k = 0; k < mid - 1; k++) {
                    multiply *= 139;
                }
                for (int j = 0; j < s1.length()-mid+1; j++) {
                    if(j==0) {
                        for (int k = 0; k < mid; k++) {
                            hash[0] = hash[0] * 139 + s1.charAt(j+k);
                        }
                    }else{
                        hash[j] = (hash[j-1]-s1.charAt(j-1)*multiply)*139+s1.charAt(j+mid-1);
                    }
                }
                MergeSort(hash, temp, 0, s1.length()-mid);
                boolean check = false;
                long compare = 0;
                for (int j = 0; j < s2.length()-mid+1; j++) {
                    if (j == 0) {
                        for (int k = 0; k < mid; k++) {
                            compare = compare * 139 + s2.charAt(j+k);
                        }
                    } else {
                        compare = (compare - s2.charAt(j-1) * multiply)*139 + s2.charAt(j + mid - 1);
                    }
                    int l = 0;
                    int r = s1.length() - mid;
                    while (r >= l) {
                        int m = (l + r) / 2;
                        if (compare > hash[m]) {
                            l = m+1;
                        } else if (compare < hash[m]) {
                            r = m-1;
                        } else {
                            check = true;
                            break;
                        }
                    }
                    if(check) {
                        break;
                    }
                }
                if (check) {
                    left = mid+1;
                    ans = mid;
                } else {
                    right = mid-1;
                }
        }
        out.println(ans);
        out.close();
    }
    public static void MergeSort(long[] list, long[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            MergeSort(list, temp, left, mid);
            MergeSort(list, temp, mid + 1, right);
            merge(left, right, mid, list, temp);
        }
    }
    public static void merge(int left, int right, int mid, long[] list, long[] temp) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (list[i] < list[j]) {
                temp[k++] = list[i++];
            } else {
                temp[k++] = list[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = list[i++];
        }
        while (j <= right) {
            temp[k++] = list[j++];
        }
        for (int t = 0; t < k; t++) {
            list[left + t] = temp[t];
        }
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








