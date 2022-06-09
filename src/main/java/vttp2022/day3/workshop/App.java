package vttp2022.day3.workshop;

/*the App file contains the main method. For stuff that only requires one single file (i.e. the workshop day 1 create shopping cart), you'll only need this one App file.
 
*/


/**
 * Hello world!
 *
 */
public class App 
{
    private static String defaultDb =  "db"; //ask kenneth why this one static //what happens if i change //nothing is defaulting kenneth why
    public static void main( String[] args )
    {
        //print out first argument db name used to create the directory
        if (args.length> 0){
            if(args[0] != null) { //if there is a command to the console, print out the first then
                System.out.println( args[0]);
                App.defaultDb = args[0]; //It runs without app ask kenneth why he put the app. there

            }
           
        } 
        System.out.println(defaultDb); 
        Repository repo = new Repository(defaultDb); //
        Session session = new Session(repo); //
        session.start(); //main method to start the loop 
    }
}
