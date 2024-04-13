import java.util.Arrays;

public class MyDate {
    private int year;
    private int month;
    private int day;

    public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    public static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public static final int[] DAYS_IN_MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public MyDate(int year, int month, int day) {
        setDate(year, month, day);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean isValidDate(int year, int month, int day) {
        if (year < 1 || year > 9999 || month < 1 || month > 12) {
            return false;
        }

        int maxDay = DAYS_IN_MONTHS[month - 1];
        if (month == 2 && isLeapYear(year)) {
            maxDay = 29; // February has 29 days in a leap year
        }

        return day >= 1 && day <= maxDay;
    }

    public void setDate(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            throw new IllegalArgumentException("Invalid year, month, or day!");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void setYear(int year) {
        if (year < 1 || year > 9999) {
            throw new IllegalArgumentException("Invalid year!");
        }
        this.year = year;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month!");
        }
        this.month = month;
    }

    public void setDay(int day) {
        int maxDay = DAYS_IN_MONTHS[this.month - 1];
        if (this.month == 2 && isLeapYear(this.year)) {
            maxDay = 29;
        }
        if (day < 1 || day > maxDay) {
            throw new IllegalArgumentException("Invalid day!");
        }
        this.day = day;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public String toString() {
        String dayOfWeek = DAYS[getDayOfWeek(year, month, day)];
        String monthName = MONTHS[month - 1];
        return String.format("%s %d %s %d", dayOfWeek, day, monthName, year);
    }

    public MyDate nextDay() {
        if (day < DAYS_IN_MONTHS[month - 1] || (month == 2 && isLeapYear(year) && day < 29)) {
            day++;
        } else {
            day = 1;
            if (month < 12) {
                month++;
            } else {
                month = 1;
                year++;
            }
        }
        return this;
    }

    public MyDate nextMonth() {
        if (month < 12) {
            month++;
        } else {
            month = 1;
            year++;
        }
        if (day > DAYS_IN_MONTHS[month - 1]) {
            day = DAYS_IN_MONTHS[month - 1];
        }
        return this;
    }

    public MyDate nextYear() {
        year++;
        if (month == 2 && isLeapYear(year) && day == 29) {
            day = 28;
        }
        return this;
    }

    public MyDate previousDay() {
        if (day > 1) {
            day--;
        } else {
            if (month > 1) {
                month--;
            } else {
                month = 12;
                year--;
            }
            day = DAYS_IN_MONTHS[month - 1];
        }
        return this;
    }

    public MyDate previousMonth() {
        if (month > 1) {
            month--;
        } else {
            month = 12;
            year--;
        }
        if (day > DAYS_IN_MONTHS[month - 1]) {
            day = DAYS_IN_MONTHS[month - 1];
        }
        return this;
    }

    public MyDate previousYear() {
        year--;
        if (month == 2 && isLeapYear(year) && day == 29) {
            day = 28;
        }
        return this;
    }

    public static int getDayOfWeek(int year, int month, int day) {
        int totalDays = 0;
        for (int y = 1900; y < year; y++) {
            if (isLeapYear(y)) {
                totalDays += 366;
            } else {
                totalDays += 365;
            }
        }

        for (int m = 1; m < month; m++) {
            int maxDay = DAYS_IN_MONTHS[m - 1];
            if (m == 2 && isLeapYear(year)) {
                maxDay = 29;
            }
            totalDays += maxDay;
        }

        totalDays += day - 1;
        return totalDays % 7;
    }

    public static void main(String[] args) {
        // Test the MyDate class with sample statements
        MyDate d1 = new MyDate(2012, 2, 28);
        System.out.println(d1);             // Tuesday 28 Feb 2012
        System.out.println(d1.nextDay());   // Wednesday 29 Feb 2012
        System.out.println(d1.nextDay());   // Thursday 1 Mar 2012
        System.out.println(d1.nextMonth()); // Sunday 1 Apr 2012
        System.out.println(d1.nextYear());  // Monday 1 Apr 2013

        MyDate d2 = new MyDate(2012, 1, 2);
        System.out.println(d2);                  // Monday 2 Jan 2012
        System.out.println(d2.previousDay());    // Sunday 1 Jan 2012
        System.out.println(d2.previousDay());    // Saturday 31 Dec 2011
        System.out.println(d2.previousMonth());  // Wednesday 30 Nov 2011
        System.out.println(d2.previousYear());   // Tuesday 30 Nov 2010

        MyDate d3 = new MyDate(2012, 2, 29);
        System.out.println(d3.previousYear()); // Monday 28 Feb 2011

        // Test nextDay() in a loop from 28 Dec 2011 to 2 Mar 2012
        MyDate date = new MyDate(2011, 12, 28); // Starting date: 28 Dec 2011
        while (!(date.getYear() == 2012 && date.getMonth() == 3 && date.getDay() == 2)) {
            System.out.println(date);
            date.nextDay();
        }
    }
}
