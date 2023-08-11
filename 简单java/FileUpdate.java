import java.io.*;
import java.util.*;

public class FileUpdate {
    public static void main(String[] args) {
        String relativePath = "./example.txt"; // 相对于源代码的路径

        // 读取文件
        List<String> lines = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(relativePath))) {
            while (reader.hasNextLine()) {
                String line=reader.nextLine();
                lines.add(line);
                System.out.println("before: "+line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when reading the file.");
            e.printStackTrace();
        }

        // 修改内容
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String reversedLine = new StringBuilder(line).reverse().toString();
            lines.set(i, reversedLine);
        }

        // 保存修改后的内容
        try (PrintWriter writer = new PrintWriter(new FileWriter(relativePath))) {
            for (String line : lines) {
                writer.println(line);
                System.out.println("after: "+line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the file.");
            e.printStackTrace();
        }
    }
}

