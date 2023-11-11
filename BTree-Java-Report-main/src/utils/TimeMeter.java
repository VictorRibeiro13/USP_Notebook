package utils;

public class TimeMeter {
    private static long init;

    public static void init() {
        init = System.currentTimeMillis();
    }

    public static void end(String method) {
        long end = System.currentTimeMillis();
        System.out.println("The average time of the method \"" + method + "\" was: " + (end - init) / 1000F + " second(s).");
    }
}
