import java.io.File; //imports File class into program
import java.io.FileNotFoundException; //imports FileNotFoundException exception class into program
import java.util.*; //imports all the class present in java.util package into program

class MobileData //class named MobileData is created
{
    int code; //integer data member named code is declared
    float totalInternet; //float data member named totalInternet is declared
    double totalCallin; //double data member named totalCallin is declared

    public MobileData(int code) //parameterised constructor
    {
        this.code = code; //assigns the input code to the variable code of class
    }

    //method named scanData is defined which accepts a float value as parameter.
    //This input float value is added in the variable totalInternet.
    //totalInternet keeps a track on total internet associated with the country code.
    void scanData(float x)
    {
        totalInternet+=x; //value x is added to totalInternet
    }

    //method named scanData is defined which accepts a double value as parameter.
    //This input double value is added in the variable totalCallin.
    //totalCallin keeps a track on total callin associated with the country code.
    void scanData(double x)
    {
        totalCallin+=x; //value x is added to totalCallin
    }

}

//class named SortByInternet is created which implements the Comparator interface
//This class provides comparison logic based on the total internet.
class SortByInternet implements Comparator<MobileData>
{
    //compare method is implemented here.
    //Used for sorting the objects of MobileData class in descending order of totalInternet
    public int compare(MobileData x, MobileData y)
    {
        return Float.compare(y.totalInternet,x.totalInternet); //returns -1, 0 or 1 to say if it is less than, equal, or greater to the other
    }
}

//class named SortByCallin is created which implements the Comparator interface
//This class provides comparison logic based on the total callin.
class SortByCallin implements Comparator<MobileData>
{
    //compare method is implemented here.
    //Used for sorting the objects of MobileData class in descending order of totalInternet
    public int compare(MobileData x, MobileData y)
    {
        return Double.compare(y.totalCallin,x.totalCallin);  //returns -1, 0 or 1 to say if it is less than, equal, or greater to the other
    }
}
public class Main //class named Main is created
{
    //static method named getSummary is defined here.
    //This method prints the summary of country whose code is passed as an input to the method.
    //Accepts two parameters - 1. code of the country whose summary we want, and
    //2. object of scanner class associated with file in which data is stored
    public static void getSummary(String code, Scanner sc)
    {
        boolean flag=false; //boolean variable named flag is created and initialized with false.
        // This keeps a track whether data of input code exits in database or not.

        System.out.println("\n------------------------------------------------------------------------------------");
        System.out.println("| Country Code   |  smsin   |   smsout   |   callin   |    callout   |   internet  |");
        System.out.println("------------------------------------------------------------------------------------\n");

        sc.nextLine(); //skips the first line in the text file
        while(sc.hasNext()) //runs loop while there is data in text file i.e. scanner has another token in it's input
        {
            String[] k=sc.nextLine().split("\\|"); //reads the next line in file and breaks it around the "|"
            // & then stores it in string array named k

            if(k[0].equals(code)) //if the first element of string array is equal to the input code then it enters if block
            {
                flag=true; //turns the value of flag to true
                System.out.print("\t   "+k[0]); //prints the first element of string array i.e. country code
                for (int i = 1; i <= 5; i++) //runs loop from 1 to 5 to print remaining data in current line stored in string array
                {
                    switch(i) //enhanced switch-case block
                    {
                        case 1 -> System.out.print("\t\t\t" + k[i]); //prints the second element of string array i.e. smsin
                        case 2, 3 -> System.out.print("\t\t " + k[i]); //prints the third and forth element of string array i.e. smsout and callin
                        case 4 -> System.out.print("\t\t" + k[i]); //prints the fifth element of string array i.e. callout
                        case 5 -> System.out.print("\t\t" + k[i]); //prints the last element of string array i.e. Internet
                    }
                }
                System.out.println(); //prints the an empty line
            }
        }
        if(!flag) //if flag remains false then following statement is printed
            System.out.println("\t\t## Data of entered country code doesn't exist in database! ##\n");

    }

    //static method named topInternet is defined here.
    //This method prints the top 10 countries having maximum internet usage.
    //It accepts an arraylist of objects of MobileData class as parameter.
    public static void topInternet(ArrayList<MobileData> country)
    {
        country.sort(new SortByInternet()); //sorts the arraylist named country based on the given Comparator passed as an argument
        System.out.println("\n** Top 10 countries which have most internet usage **\n");
        System.out.println("  \tCountry Code : Total Internet Usage");
        System.out.println("----------------------------------------");

        int i=0; //declares and initialises an integer variable named i to 0
        Iterator<MobileData> it=country.iterator(); //object of Iterator class named 'it' is created.
        //This is used to iterate over arraylist of objects of MobileData class.
        while(it.hasNext() && i<10) //runs the loop while there is another object in iterator and i is less than 10
        {
            i++; //increments i
            MobileData temp=it.next(); //stores the next object in 'it' in the temp object of MobileData class.
            System.out.println(i+".\t\t"+temp.code + "\t\t : \t"
                    + "  "+temp.totalInternet); //prints the country code and total internet stored in temp object.
        }
    }

    //static method named topCallin is defined here.
    //This method prints the top 10 countries having maximum callin usage.
    //It accepts an arraylist of objects of MobileData class as parameter.
    public static void topCallin(ArrayList<MobileData> country)
    {
        country.sort(new SortByCallin()); //sorts the arraylist named country based on the given Comparator passed as an argument
        System.out.println("\n** Top 10 countries which have most callin usage **\n");
        System.out.println("  \tCountry Code : Total Callin Usage");
        System.out.println("----------------------------------------");

        int i=0;
        Iterator<MobileData> it=country.iterator();
        while(it.hasNext() && i<10) //runs the loop while there is another object in iterator and i is less than 10
        {
            i++; //increments i
            MobileData temp=it.next(); //stores the next object in 'it' in the temp object of MobileData class.
            System.out.println(i+".\t\t"+temp.code + "\t\t : \t"
                    + "  "+temp.totalCallin); //prints the country code and total callin stored in temp object.
        }
    }

    //static method named fetchData is created.
    //It reads the data from file associated with object of scanner class
    //and then returns an arraylist of objects of MobileData class which
    //contain data associated with country code
    public static ArrayList<MobileData> fetchData(Scanner sc)
    {
        HashSet<Integer> track=new HashSet<>(); //HashSet of type Integer named track is created to keep a track on already added country code
        ArrayList<MobileData> country = new ArrayList<>(); //ArrayList of objects of MobileData class named country is created
        //to add the data associated with all country codes

        sc.nextLine(); //skips first line
        while(sc.hasNext()) //runs loop while there is data in text file i.e. scanner has another token in it's input
        {
            String[] k=sc.nextLine().split("\\|"); //reads the next line in file and breaks it around the "|"
            // & then stores it in string array named k
            if(track.contains(Integer.parseInt(k[0]))) //if the current record country code is already present in the arraylist then
            //this if block will be executed
            {
                for(MobileData m: country) //loops through the arraylist to find the object already having the country code
                {
                    if(m.code==Integer.parseInt(k[0])) //when the object with country code equal to current record country code is found
                    //then this if block is executed
                    {
                        m.scanData(Double.parseDouble(k[3])); //stores the callin of current record in object associated with country code of current record
                        m.scanData(Float.parseFloat(k[5])); //stores the internet of current record in object associated with country code of current record
                        break; //breaks the loop
                    }
                }
            }
            else //if current record country code is not present in hashset then this block will be executed
            {
                track.add(Integer.parseInt(k[0])); //current record country code is added to the hashset
                MobileData m=new MobileData(Integer.parseInt(k[0])); //a new object of MobileData class is created with the current record country code

                m.scanData(Double.parseDouble(k[3])); //stores the callin of current record in object associated with country code of current record
                m.scanData(Float.parseFloat(k[5])); //stores the internet of current record in object associated with country code of current record
                country.add(m); //newly created object is added to the arraylist named country
            }
        }

        return country; //arraylist formed from entire data of text file is returned
    }

    public static void main(String[] args) throws FileNotFoundException //From here, the execution of program takes place.
    //Here main method handles the checked exception named
    //FileNotFoundException
    {

        File file=new File("./Mobilepad.txt"); //creating File instance to reference text file

        if(!file.exists()) //if file specified does not exist then prints the following sentences
        {
            System.out.println("File specified does not exist!");
            System.out.println("\nThank you!");
            System.out.println("\nProgram coded by:");
            System.out.println("19BCE238 HARSHIL SANGHVI");
            System.out.println("I take up responsibility for the originality and authenticity of my program.");
            System.out.println("Have a good day!");
            return;
        }

        Scanner sc; //object of scanner class named sc is declared
        int code=0; //int variable named code is declared and initialised to zero
        boolean success; //boolean variable named success is declared and intialised to false
        int option=0; //int variable named option is declared and intialised to zero

        sc = new Scanner(file); //creating scanner reference to read file
        ArrayList<MobileData> country = fetchData(sc); //calls fetchData() function.
        //The arraylist of data returned by this function is stored in
        //arraylist named country.

        while(true) //runs infinite loop
        {
            System.out.println("\nEnter the number corresponding to your choice:\n");
            System.out.println("1. Get Summary of country associated with country code");
            System.out.println("2. View Top 10 countries having maximum Internet usage");
            System.out.println("3. View Top 10 countries having maximum Callin usage");
            System.out.println("4. Exit\n");

            success=false; //sets success to false
            while (!success) //runs loop while success is false
            {
                try //try-catch block
                {
                    sc = new Scanner(System.in); //creating scanner reference to take input from user
                    System.out.print("Choice: ");
                    option = sc.nextInt(); //scans the choice from user
                    success = true; //changes value of success to true
                }
                catch (InputMismatchException e) //catches the InputMismatchException thrown by Scanner class
                {
                    System.out.println("\nChoice should be an integer. Please try again!\n");
                }
            }

            if(option==4) //if option entered is 4 then executes following block
            {
                System.out.println("\nThank you!");
                System.out.println("\nProgram coded by:");
                System.out.println("19BCE238 HARSHIL SANGHVI");
                System.out.println("I take up responsibility for the originality and authenticity of my program.");
                System.out.println("Have a good day!");
                break;
            }

            switch (option) //enhanced switch-case block
            {
                case 1 ->
                        {
                            success = false; //changes value of success to false
                            while (!success) //runs loop while success is false
                            {
                                System.out.println("\nPlease enter the country code whose summary you want:");
                                try //try-catch block
                                {
                                    sc = new Scanner(System.in); //creating scanner reference to take input from user
                                    code = sc.nextInt(); //scans the country code from user
                                    success = true; //changes value of success to true
                                }
                                catch (InputMismatchException e) //catches the InputMismatchException thrown by Scanner class
                                {
                                    System.out.println("\nCountry code should be an integer. Please try again!\n");
                                }
                            }
                            sc = new Scanner(file); //creating scanner reference to read file
                            getSummary(Integer.toString(code), sc); //calls getSummary() function.
                            //It gets the summary of country whose code is passed as parameter.
                            //Here the scanner class object is also passed as a parameter.
                        }
                case 2 -> topInternet(country); //calls topInternet function. It prints the top 10 countries having maximum internet usage in arraylist country.
                case 3 -> topCallin(country); //calls topCallin function. It prints the top 10 countries having maximum callin usage in arraylist country.
                default -> System.out.println("\nInvalid option selected! Please try again.");
            }
        }
        sc.close(); //closes the object of scanner class
    }
}
