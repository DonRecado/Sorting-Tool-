import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    private Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static Time noon() {
        return of(12, 0, 0);
    }

    public static Time midnight() {
        return of(0, 0, 0);
    }

    public static Time ofSeconds(long seconds) {
        int numberOfDays = (int) seconds / 86400;
        int numberOfHours = (int) (seconds % 86400) / 3600;
        int numberOfMinutes = (int) ((seconds % 86400) % 3600) / 60;
        int numberOfSeconds = (int) ((seconds % 86400) % 3600) % 60;
        return of(numberOfHours, numberOfMinutes, numberOfSeconds);

    }

    public static Time of(int hour, int minute, int second){
        if(hour >= 24 || hour < 0 || minute >= 60 || minute < 0 || second >= 60 || second < 0) {
            return null;
        }
        return new Time(hour, minute, second);
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }
}