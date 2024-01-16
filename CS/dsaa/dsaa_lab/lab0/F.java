import java.io.*;
import java.util.*;
public class F {
    public static void main(String[] args) {
        QReader4 in = new QReader4();
        QWriter4 out = new QWriter4();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            boolean luck = false;
            String s = in.next();
            int a = 0;
            int b = 0;
            int[] tiles = new int[38];
            int[] tiles_copy = new int[38];
            int shunzi = 0;
            int kezi = 0;
            int kezi_copy = 0;
            int quetou = 0;
            for (int j = 0; j < 14; j++) {
                String sub = s.substring(2 * j, 2 * j + 2);
                char A = sub.charAt(1);
                if (A == 'w') {
                    a = 0;
                } else if (A == 'b') {
                    a = 1;
                } else if (A == 's') {
                    a = 2;
                } else {
                    a = 3;
                }
                char B = sub.charAt(0);
                b = Integer.parseInt(String.valueOf(B));
                tiles[a * 10 + b] += 1;
            }
            ArrayList<Integer> pair = new ArrayList<>();
            for (int k = 0; k < 38; k++) {
                if (tiles[k] >= 2) {
                    pair.add(k);
                }
                tiles_copy[k] = tiles[k];
            }
            for (int p = 0; p < pair.size(); p++) {
                for(int c=0;c<38;c++){
                    tiles[c] = tiles_copy[c];
                }
                tiles[pair.get(p)] -= 2;
                quetou = 1;
                kezi = 0;
                shunzi = 0;
                for (int q = 0; q < 38; q++) {
                    while (tiles[q] != 0) {
                        if (tiles[q]%3!=0) {
                            if (q / 10 < 3 && q % 10 <= 7) {
                                if (tiles[q + 1] != 0 && tiles[q + 2] != 0) {
                                    shunzi += 1;
                                    tiles[q] -= 1;
                                    tiles[q + 1] -= 1;
                                    tiles[q + 2] -= 1;
                                } else {
                                    tiles[q] = 0;
                                }
                            }
                            else {
                                tiles[q] = 0;
                            }
                        } else if (tiles[q]%3==0) {
                            kezi += tiles[q] / 3;
                            tiles[q] -= tiles[q];
                        }
                    }
                }
                if (shunzi + kezi == 4 && quetou == 1) {
                    luck = true;
                    break;
                }
            }
            if (!luck) {
                out.println("Bad luck");
            } else {
                out.println("Blessing of Heaven");
            }
        }
        out.close();
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










