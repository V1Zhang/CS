import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class C_Main {
    public static void main(String[] args) throws IOException {
        String fileName = "random_input.txt";// 输出文件名
        Random random = new Random();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                for (int T = 0; T < 100; T++) {
                    int N = random.nextInt(1, 100); // 数组长度
                    int Q = random.nextInt(1, 100);  // 查询数量
                    int minValue = 1; // 随机数的最小值
                    int maxValue = 500; // 随机数的最大值
                    writer.write(N + " " + Q); // 写入N和Q
                    writer.newLine();

                    // 生成数组A的元素，确保非递减排序
                    int prevValue = getRandomInt(minValue, maxValue);
                    writer.write(prevValue + " ");
                    for (int i = 1; i < N; i++) {
                        int randomValue = getRandomInt(prevValue, maxValue);
                        writer.write(randomValue + " ");
                        prevValue = randomValue;
                    }
                    writer.newLine();
                    // 生成查询参数
                    for (int i = 0; i < Q; i++) {
                        int x = getRandomInt(minValue, maxValue);
                        int y = getRandomInt(x, maxValue);
                        writer.write(x + " " + y);
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
    public static int getRandomInt(int minValue, int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
}