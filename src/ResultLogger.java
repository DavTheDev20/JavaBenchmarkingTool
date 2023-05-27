import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ResultLogger {
    public FileWriter writer = new FileWriter("results.txt", true);

    public ResultLogger() throws IOException {
    }

    public void logResults(String result) {
        try {
            this.writer.write(LocalDateTime.now() + ": " + result + '\n');
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
