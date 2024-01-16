import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class lab4D_Main {
    public static void main(String[] args) throws IOException {
        String fileName = "random_input_4D.txt";// 输出文件名
        Random random = new Random();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(String.valueOf(5));
            writer.newLine();
            for (int T = 0; T < 5; T++) {
                int n = 10;
                writer.write(String.valueOf(n));
                writer.newLine();
                for (int i = 1; i < 11; i++) {
                    int a = random.nextInt(1, n+1);

                    writer.write(a + " ");
                }
                writer.newLine();
            }
            System.out.println("随机输入数据已生成并保存到 " + fileName);

            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 生成指定范围内的随机整数
    public static long getRandomLong(long minValue, long maxValue) {
        Random random = new Random();
        return random.nextLong(maxValue - minValue + 1) + minValue;
    }
}

