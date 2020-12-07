import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BirthdayCalculator
{
    public static void main(String[] args)
    {
        //get input
        int[] daysInMonths = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        System.out.println("Welcome to the Birthday Calculator!\n");
        System.out.println("What is the number of the month you were born in?");
        int month = getUserInput(12, false);

        System.out.println("What is the number of the day you were born on?");
        int day = getUserInput(daysInMonths[month - 1], false);

        System.out.println("What year were you born in?\n" +
                "Please include all digits. For example, if you were to write 96 here\n" +
                "I will assume you were born in 96 AD, not 1996");
        int year = getUserInput(2019, true);

        //construct all the LocalDate objects needed
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate thisYearBirthday = LocalDate.of(today.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
        LocalDate nextBirthday;

        //determine date of next birthday, mostly checking if it is this year or next year
        int dateComparer = today.compareTo(thisYearBirthday);
        boolean nextBirthdayNextYear = false;
        if(dateComparer > 0)
        {
            nextBirthdayNextYear = true;
        }
        if(nextBirthdayNextYear)
        {
            nextBirthday = LocalDate.of(today.getYear() + 1, birthday.getMonth(), birthday.getDayOfMonth());
        }
        else
        {
            nextBirthday = LocalDate.of(today.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
        }

        //get all values that will be outputted
        String dayOfWeekBornOn = birthday.getDayOfWeek().toString();
        String dayOfWeekThisYear = thisYearBirthday.getDayOfWeek().toString();
        String todayDate = today.toString();
        long daysToBirthday = ChronoUnit.DAYS.between(today, nextBirthday);
        int age = nextBirthday.getYear() - birthday.getYear();

        //print out final answers
        System.out.println("That means you were born on a " + dayOfWeekBornOn);
        System.out.println("This year it falls on a " + dayOfWeekThisYear);
        System.out.println("And since today is " + todayDate);
        if(daysToBirthday == 0)
        {
            System.out.println("Today is your birthday! Happy birthday! You turned " + age + " today!");
        }
        else
        {
            System.out.println("You will be turning " + age + " in " + daysToBirthday + " days");
        }
    }

    private static int getUserInput(int upperBound, boolean closeScanner)
    {
        Scanner in = new Scanner(System.in);
        int input = 0;

        //idiot proof way of getting correct data
        do
        {
            try
            {
                input = in.nextInt();

                if(input < 1 || input > upperBound)
                {
                    System.out.println("Please enter an integer between 1 and " + upperBound + ", inclusive");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter a valid integer");
                in.nextLine();
            }
        }
        while(input <= 0 || input > upperBound);

        if(closeScanner)
        {
            in.close();
        }

        return input;
    }
}
