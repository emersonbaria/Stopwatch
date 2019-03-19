package au.edu.jcu.cp3406.stopwatch;

public class Stopwatch {
    private int hours, minutes, seconds=0, secs;

    public Stopwatch() {

    }


    public void tick() {
        ++seconds;
        hours = seconds / 3600;
        minutes = (seconds % 3600) / 60;
        secs = seconds % 60;
    }

    public String toString() {
        String format = String.format("%02d:%02d:%02d", hours, minutes, secs);
        return format;
    }

    public void reset() {
        seconds=0;
        minutes=0;
        hours=0;
    }

}
