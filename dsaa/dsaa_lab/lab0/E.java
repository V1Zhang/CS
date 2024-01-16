import java.io.*;
import java.util.*;
public class E {
    public static void main(String[] args) {
        QReader3 in = new QReader3();
        QWriter3 out = new QWriter3();
        int T = in.nextInt();
        for(int t=0;t<T;t++) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int row = 2*b+(2*c+1);
        int col = 2*b+(2*a+1);
        char[][] cubic = new char[row][col];
            for(int i=0;i<=b;i++) {
                for(int j=0;j<=a;j++) {
                    cubic[2*i][2*b-2*i+2*j]='+';
                    if(i!=b) {
                        cubic[2 * i + 1][2 * b - 2 * i + 2 * j-1] = '/';
                        if(j!=a){
                            cubic[2 * i][2 * b - 2 * i + 2 * j + 1] = '-';
                            cubic[2*i+1][2*b-2*i+2*j]='.';
                        }
                    }
                }
            }
            for(int i=0;i<=c;i++){
                for(int j=0;j<=a;j++){
                    cubic[2*b+2*i][2*j]='+';
                    if(i!=c){
                        cubic[2*b+2*i+1][2*j]='|';
                        if(j!=a){
                            cubic[2*b+2*i+1][2*j+1]='.';
                        }
                    }
                    if(j!=a){
                        cubic[2*b+2*i][2*j+1]='-';
                    }
                }
            }
            for(int i=0;i<=b;i++){
                for(int j=0;j<=c;j++){
                    cubic[2*i+2*j][col-1-2*i]='+';
                    if(i!=b){
                        if(j!=c){
                            cubic[2*i+2*j+1][col-1-2*i]='|';
                            cubic[2*i+2*j+2+1][col-1-2*i-1]='/';
                            cubic[2*i+2*j+2][col-1-2*i-1]='.';
                        }
                    }
                }
            }
            for(int l=0;l<row;l++){
                for(int w=0;w<col;w++){
                    if(cubic[l][w]=='\u0000'){
                        cubic[l][w]='.';
                    }
                    out.print(cubic[l][w]);
                }
                out.println(" ");
            }
        }
        out.close();
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










