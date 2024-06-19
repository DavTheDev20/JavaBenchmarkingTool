import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        int testIterations = 100_000_000;
//        System.out.println("\nTest Starting...\n");
        System.out.println("\n" +
                "       _                    ____                  _                          _      _______          _ \n" +
                "      | |                  |  _ \\                | |                        | |    |__   __|        | |\n" +
                "      | | __ ___   ____ _  | |_) | ___ _ __   ___| |__  _ __ ___   __ _ _ __| | __    | | ___   ___ | |\n" +
                "  _   | |/ _` \\ \\ / / _` | |  _ < / _ \\ '_ \\ / __| '_ \\| '_ ` _ \\ / _` | '__| |/ /    | |/ _ \\ / _ \\| |\n" +
                " | |__| | (_| |\\ V / (_| | | |_) |  __/ | | | (__| | | | | | | | | (_| | |  |   <     | | (_) | (_) | |\n" +
                "  \\____/ \\__,_| \\_/ \\__,_| |____/ \\___|_| |_|\\___|_| |_|_| |_| |_|\\__,_|_|  |_|\\_\\    |_|\\___/ \\___/|_|\n" +
                "                                                                                                       ");
        benchmarkTest(testIterations);
        System.out.println("Test Complete");

    }

    public static void benchmarkTest(int iterations) throws IOException {
        ResultLogger resultLogger = new ResultLogger();
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        System.out.println(hardware.getProcessor().getProcessorIdentifier().getName());
        System.out.println(systemInfo.getOperatingSystem());

        List<Double> testResults = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            long startTime = System.nanoTime();
            // Stopwatch timer = Stopwatch.createStarted();
            for (int x = 0; x <= iterations; x++) {
                findVariable();
            }
            // Stopwatch testLength = timer.stop();
            long endTime = System.nanoTime();
            double testLength = (double) (endTime - startTime) / 1_000_000_000;
            testResults.add(i, testLength);

            String resultString = "Your PC took " + String.format("%.2f", testLength) + "s" + " to run through "
                    + iterations + " iterations.";

            System.out.println(resultString);
        }

        double total = 0;
        for (double timeResult : testResults) {
            total += timeResult;
        }
        double testingAverage = total / testResults.size();

        resultLogger
                .logResults("Test took an average of " + String.format("%.3f", testingAverage) + "s over 10 tests.");
    }

    public static void findVariable() {
        double x = Math.random() * 1_000_000;
        double z = Math.random() * 1_000_000;
        double var = 0;

        String[] operations = { "Addition", "Subtraction", "Multiplication", "Division" };

        String testOperation = operations[(int) Math.round(Math.random() * 3)];

        switch (testOperation) {
            case "Addition":
                if (x > z)
                    var = x - z;
                else
                    var = z - x;
                break;
            case "Subtraction":
                var = x + z;
                break;
            case "Multiplication":
                if (x > z)
                    var = x / z;
                else
                    var = z / x;
                break;
            case "Division":
                var = x * z;
                break;
            default:
                return;
        }
    }
}
