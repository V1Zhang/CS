import java.io.*;
import java.util.*;
public class C {
    public static void main(String[] args) {
        QReader1 in = new QReader1();
        QWriter1 out = new QWriter1();
        int n = in.nextInt();
        for(int x=0; x<n; x++) {
            StringBuilder S = new StringBuilder();
            int a = 0;
            int b = 0;
            int[] query = new int[37];
            for(int y=0; y<13; y++) {
                String k = in.next();
                char A = k.charAt(0);
                if (k.length() == 2) {
                    if (A == 'W') {
                        a = 0;
                    } else if (A == 'T') {
                        a = 1;
                    } else if (A == 'Y') {
                        a = 2;
                    }
                    char B = k.charAt(1);
                    b = Integer.parseInt(String.valueOf (B));
                }
                else {
                    a = 3;
                    if (A == 'E') {
                        b = 0;
                    } else if (A == 'S') {
                        b = 1;
                    } else if (A == 'W') {
                        b = 2;
                    } else if (A == 'N') {
                        b = 3;
                    } else if (A == 'B') {
                        b = 4;
                    } else if (A == 'F') {
                        b = 5;
                    } else if (A == 'Z') {
                        b = 6;
                    }
                }
                query[a * 10 + b] += 1;
            }
            for (int i = 0; i < 37; i++) {
                for (int j = 0; j < query[i]; j++) {
                    String s1 = "";
                    String s2 = String.valueOf(i % 10);
                    if (i / 10 == 0) {
                        s1 = "W";
                    } else if (i / 10 == 1) {
                        s1 = "T";
                    } else if (i / 10 == 2) {
                        s1 = "Y";
                    } else {
                        s2 = "";
                        if (i % 10 == 0) {
                            s1 = "E";
                        } else if (i % 10 == 1) {
                            s1 = "S";
                        } else if (i % 10 == 2) {
                            s1 = "W";
                        } else if (i % 10 == 3) {
                            s1 = "N";
                        } else if (i % 10 == 4) {
                            s1 = "B";
                        } else if (i % 10 == 5) {
                            s1 = "F";
                        } else if (i % 10 == 6) {
                            s1 = "Z";
                        }
                    }
                    if(s2.isEmpty()) {
                        S.append(s1).append(" ");
                    }
                    else{
                        S.append(s1).append(s2).append(" ");
                    }
                }
            }
            out.println(S);
        }
        out.close();
    }
}
    class QReader1 {
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

    class QWriter1 implements Closeable {
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






