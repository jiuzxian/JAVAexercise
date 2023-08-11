import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreate {
    public static void main(String[] args) {
        String relativePath = "./example.txt"; // 相对于项目根目录的路径
        String content = "Hello, this is my custom content!"; // 要写入文件的内容

        try {
            File file = new File(relativePath);
            if (file.createNewFile()) {
                System.out.println("File created successfully!");

                // 写入内容到文件
                FileWriter writer = new FileWriter(file);
                writer.write(content);
                writer.close();

                System.out.println("Content written to the file.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



