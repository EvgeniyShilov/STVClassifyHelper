import java.io.*;
import java.util.Random;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) throw new IllegalArgumentException("No path in the args");
        final String path = args[0];
        //sort(args);
        //generateFiles(path);
        resizeFiles(path);
    }

    public static void sort(String[] path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        while (true) {
            try {
                Image input = new Image(path + "0\\" + i + ".png");
                ImageView imageView = new ImageView(input, i + ".png");
                String raw;
                while (true) {
                    raw = reader.readLine();
                    if (raw.length() != 0) break;
                }
                final String className = raw.substring(0,1);
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

    public static void generateFiles(String path) throws IOException {
        File train = new File(path + "train.txt");
        File trainDir = new File(path + "train");
        train.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(train);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        for (File subDir : trainDir.listFiles()) {
            for (String filename : subDir.list()) {
                bufferedWriter.write("train/" + subDir.getName() + "/" + filename + " " + subDir.getName());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }

        File val = new File(path + "val.txt");
        File valDir = new File(path + "val");
        val.createNewFile();
        outputStream = new FileOutputStream(val);
        outputStreamWriter = new OutputStreamWriter(outputStream);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        for (File subDir : valDir.listFiles()) {
            for (String filename : subDir.list()) {
                bufferedWriter.write("val/" + subDir.getName() + "/" + filename + " " + subDir.getName());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
    }

    public static void resizeFiles(String path) throws IOException {
        File trainDir = new File(path + "train");
        for (File subDir : trainDir.listFiles())
            for (File file : subDir.listFiles())
                new Image(file).resize(224).save(file);
        File valDir = new File(path + "val");
        for (File subDir : valDir.listFiles())
            for (File file : subDir.listFiles())
                new Image(file).resize(224).save(file);
    }
}
