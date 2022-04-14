package smartcalc;

import smartcalc.arguments.ArgumentsExecutor;
import smartcalc.console.ConsoleInterface;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            new ConsoleInterface().open();
        } else {
            new ArgumentsExecutor().run(args);
        }
    }
}