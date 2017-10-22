import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) throw new IllegalArgumentException("No path in the args");
        final String path = args[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        while (true) {
            try {
                Image input = new Image(path + "0\\" + i + ".png");
                ImageView imageView = new ImageView(input, i + ".png");
                final String className = reader.readLine();
                imageView.dispose();
                final String classPath = path + "classes\\" + className;
                File classDir = new File(classPath);
                if (!classDir.exists()) classDir.mkdir();
                input.save(classPath + "\\" + new Random().nextInt() + ".png");
            } catch (IOException ignored) {
                break;
            } finally {
                i++;
            }
        }
    }
}
