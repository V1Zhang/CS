import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class F_Main {
    public static void main(String[] args) throws IOException {
        String fileName = "random_input_2E.txt";// 输出文件名
        Random random = new Random();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (int T = 0; T < 100; T++) {
                int n = random.nextInt(1, 100000);
                int p = random.nextInt(1, 20);
                int q = random.nextInt(1, 200000);
                int minValue = 1; // 随机数的最小值
                int maxValue = 1000000000; // 随机数的最大值
                writer.write(n + " " + p + " " + q); // 写入N和Q
                writer.newLine();

                long prevValue = getRandomLong(minValue, maxValue);
                writer.write(prevValue + " ");
                for (int i = 1; i < n; i++) {
                    long randomValue = getRandomLong(prevValue, maxValue);
                    writer.write(randomValue + " ");
                    prevValue = randomValue;
                    writer.newLine();
                }

                writer.newLine();
                System.out.println("随机输入数据已生成并保存到 " + fileName);
            }
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
