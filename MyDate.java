/*
    Group 3
    Maestro, Syranne Jahziel
    Morada, Justin Paul
    Sumaylo, Regine
*/

public class MyDate {
    
    //instance variables
    private int year;
    private int month;
    private int day;

    // Arrays for month names and day names
    public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    public static final String[] DAYS = {"Sunday    ", "Monday    ", "Tuesday   ", "Wednesday ", "Thursday  ", "Friday    ", "Saturday  "};
    // Array for number of days in each month
    public static final int[] DAYS_IN_MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
    
    // Method to check if a year is a leap year
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Method to check if a date is valid
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
    
    // Method to get the day of the week for a given date
    public static int getDayOfWeek(int year, int month, int day) {
        int totalDays = 0;
        for (int i = 1900; i < year; i++) {
            if (isLeapYear(i)) {
                totalDays += 366;
            } else {
                totalDays += 365;
            }
        }

        for (int i = 1; i < month; i++) {
            int maxDay = DAYS_IN_MONTHS[i - 1];
            if (i == 2 && isLeapYear(year)) {
                maxDay = 29;
            }
            totalDays += maxDay;
        }

        totalDays += day - 1;
        return totalDays % 7;
    }
    
    // Constructor that takes year, month, and day parameters
    public MyDate(int year, int month, int day) {
        setDate(year, month, day);
    }
    
    //Method to set the date
    public void setDate(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            throw new IllegalArgumentException("Invalid year, month, or day!");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    // Setter methods for year, month, and day
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
    
    
    // Getter methods for year, month, and day
    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }
    
    // Override toString method to format date string
    @Override
    public String toString() {
        String dayOfWeek = DAYS[getDayOfWeek(year, month, day)];
        String monthName = MONTHS[month - 1];
        return String.format("%s %d %s %d", dayOfWeek, day, monthName, year);
    }
    
    // Method to get the next day
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
    
    // Method to get the next month
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
    
    // Method to get the next year
    public MyDate nextYear() {
        year++;
        if (month == 2 && isLeapYear(year) && day == 29) {
            day = 28;
        }
        return this;
    }
    
    // Method to get the previous day
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

    // Method to get the previous month
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

    // Method to get the previous year
    public MyDate previousYear() {
        year--;
        if (month == 2 && isLeapYear(year) && day == 29) {
            day = 28;
        }
        return this;
    }
    
    // Main method for testing the MyDate class
    public static void main(String[] args) {
        // Test the MyDate class with sample statements
        
        MyDate d1 = new MyDate(2012, 2, 28);
        System.out.println("Test Date 1");
        System.out.println(d1);             // Tuesday 28 Feb 2012
        System.out.println(d1.nextDay());   // Wednesday 29 Feb 2012
        System.out.println(d1.nextDay());   // Thursday 1 Mar 2012
        System.out.println(d1.nextMonth()); // Sunday 1 Apr 2012
        System.out.println(d1.nextYear());  // Monday 1 Apr 2013
        System.out.println();

        MyDate d2 = new MyDate(2012, 1, 2);
        System.out.println("Test Date 2");
        System.out.println(d2);                  // Monday 2 Jan 2012
        System.out.println(d2.previousDay());    // Sunday 1 Jan 2012
        System.out.println(d2.previousDay());    // Saturday 31 Dec 2011
        System.out.println(d2.previousMonth());  // Wednesday 30 Nov 2011
        System.out.println(d2.previousYear());   // Tuesday 30 Nov 2010
        System.out.println();

        MyDate d3 = new MyDate(2012, 2, 29);
        System.out.println("Test Date 3");
        System.out.println(d3.previousYear()); // Monday 28 Feb 2011
        System.out.println("\n\n");
        
        //MyDate d4 = new MyDate(2099, 11, 31); // Invalid year, month, or day!
        //MyDate d5 = new MyDate(2011, 2, 29); // Invalid year, month, or day!
        
        System.out.println("Dates from 28 Dec 2011 to 2 Mar 2012");
        System.out.println("------------------------------------");
        
        // Test nextDay() in a loop from 28 Dec 2011 to 2 Mar 2012
        MyDate date = new MyDate(2011, 12, 28); // Starting date: 28 Dec 2011
        while (!(date.getYear() == 2012 && date.getMonth() == 3 && date.getDay() == 2)) {
            System.out.println(date);
            date.nextDay();
        }
    }
}
