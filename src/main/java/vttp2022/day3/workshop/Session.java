package vttp2022.day3.workshop;

import java.io.Console;
import java.util.List;

public class Session {
    //

    public static final String LIST = "list";
    public static final String CARTS = "carts";
    public static final String ADD = "add";
    public static final String DELETE = "del";
    public static final String LOAD  = "load";
    public static final String USERS = "users";
    public static final String SAVE = "save";
    public static final String END = "end";
    public static final String LOGIN = "login";

    private Repository repository;
    private Cart currCart;

    public Session(Repository repo) {
        this.repository = repo;
    }
        
    public void start() {
        //you can use scanner instead of console. *Note to self: Read up what is scanner.
        Console cons = System.console();
        boolean stop = false;
        currCart = new Cart("anon");

        //Define the while loop to ensure that program runs until end command is given
        while (!stop) {
            String input = cons.readLine("> ");
            String[] terms = input.split(" ");

            switch(terms[0]) {
                case CARTS: 
                    System.out.println("List of shopping carts");
                    break;

                case LIST: 
                    System.out.printf("Items in %s's shopping cart\n", currCart.getUsername());
                    printList(currCart.getContents());
                    break; //printList function does a getContents
                    
                
                case ADD:
                    int before = currCart.getContents().size();
                    for(int i = 1; i < terms.length; i++) //the terms is what is typed in the console. You need to add + something
                        currCart.add(terms[i]); //i starts at 1, meaning its the second term passed, so it refers to the second word. 
                    int addedCount = currCart.getContents().size() - before; //added count is suze after addtision
                    System.out.printf("Added %d item(s) to %s's cart\n", addedCount, currCart.getUsername());
                    break;
                    //I'll flag that currently the add function doesn't split the commas, so if you write horse, it will include the comma. 

                case DELETE:
                    int idx = Integer.parseInt(terms[1]); //parse the number into an integer. label it idx (i.e. the 2 in 2. apple) 
                    String item = currCart.delete(idx - 1 ); //item that is being deleted is currcart (n-1) in the list
                    System.out.printf("Removed %s from %s's cart\n", item, currCart.getUsername());
                    break;

               

                case USERS:
                    List<String> allCarts = repository.getShoppingCarts(); //print all the ShoppingCarts from the repository
                    System.out.println(allCarts);
                    break;

                case SAVE: 
                    repository.save(currCart); //save does the io stream and buffer stream business
                    System.out.println("You have saved successfully uwu!^-^");

                    break;

                case LOGIN:
                    currCart = new Cart(terms[1]);
                    System.out.printf("Success! Welcome back to your cart %s!\n", terms[1]); //This just prints a message there's no call

                
                case LOAD:
                    currCart = repository.load(currCart.getUsername());
                    System.out.printf("Loaded %s's shopping cart. There are %d item(s)\n", currCart.getUsername(), currCart.getContents().size());
                //We place the load below the login and we remove the break so that when one logs in, it automatically loads the users list
                    break;

                
                case END: 
                    stop = true;
                    break;

                default:
                    System.err.printf("Unknown input : %s\n", terms[0]); //If none of the cases match, print an error message
            }

            System.out.println(""); //this is purely aesthetic
            
        } //exit loop
        
        System.out.println("Thank you for shopping with us!"); //after you leave the loop, print a thank you message and exit the program . 
    }
        public void printList(List<String> list) { //can this go up before the switch statement in terms of neatness??
            //If the list is empty, print no record found. 
            if(list.size() <=0) {
                System.out.println("No record found!");
                return; 
            }

            for (int i=0; i <list.size(); i++) {
                System.out.printf("%d. %s\n", (i+1), list.get(i));
        }
    }

}
