import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class lab4F_Main {
    public static void main(String[] args) throws IOException {
        String fileName = "random_input_4F.txt";// 输出文件名
        Random random = new Random();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(String.valueOf(5));
            writer.newLine();
            for (int T = 0; T < 5; T++) {
                int k = random.nextInt(10);
                int n = random.nextInt(20)+10;
                writer.write(String.valueOf(k));
                writer.write(" ");
                writer.write(String.valueOf(n-1));
                writer.newLine();
                for (int i = 1; i < n; i++) {
                    int a = random.nextInt(10);
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