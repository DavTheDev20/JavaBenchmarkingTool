import com.google.common.base.Stopwatch;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        int testIterations = 10_000_000;
        benchmarkTest(testIterations);

    }

    public static void benchmarkTest(int iterations) throws IOException {
        ResultLogger resultLogger = new ResultLogger();
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        System.out.println(hardware.getProcessor().getProcessorIdentifier().getName());
        System.out.println(systemInfo.getOperatingSystem());
        Stopwatch timer = Stopwatch.createStarted();
        for (int i = 0; i<=iterations; i++) {
            findVariable();
        }
        Stopwatch testLength = timer.stop();

        String resultString = "Your PC took " + testLength + " to run through " + iterations + " iterations.";

        System.out.println(resultString);
        resultLogger.logResults("Test took " + testLength);
    }

    public static void findVariable() {
        double x = Math.random() * 1_000_000;
        double z = Math.random() * 1_000_000;
        double var = 0;
        String equationString = "";

        String[] operations = {"Addition", "Subtraction", "Multiplication", "Division"};

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

        if (testOperation.equals("Addition")) {
            equationString = x + " + " + var + " = " + z;
        }  else if (testOperation.equals("Subtraction")) {
            equationString = x + " - " + var + " = " + z;
        } else if (testOperation.equals("Multiplication")) {
            equationString = x + " × " + var + " = " + z;
        } else if (testOperation.equals("Division")) {
            equationString = x + " ÷ " + var + " = " + z;
        }

//        System.out.println(equationString);
    }
}
