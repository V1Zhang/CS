import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class duipai {
    public static void main(String[] args) throws IOException {
        String fileName = "random_input_7E.txt";// 输出文件名
        Random random = new Random();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (int T = 0; T < 100; T++) {
                int m = random.nextInt(2, 10000);
                // int k = random.nextInt(1, m);
                int k = 6;
                writer.write(m + " " + k); // 写入N和Q
                writer.newLine();

                int minValue = -100000; // 随机数的最小值
                int maxValue = 100000; // 随机数的最大值
                for (int i = 0; i < m; i++) {
                    long randomValue = getRandomLong(minValue, maxValue);
                    writer.write(randomValue + " ");
                }
                writer.newLine();
                for (int i = 0; i < m-k+1; i++) {
                    int randomNmu = random.nextInt(1,k);
                    writer.write(randomNmu + " ");
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
