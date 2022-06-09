package vttp2022.day3.workshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class Cart {
    //First we need to create a contents array and a username
    private List<String> contents = new LinkedList<>();
    private String username;

    //Create a cart object that holds the username
    public Cart(String name) {
        this.username = name;
    }

    //Get method to pull a username
    public String getUsername() {
        return username;
    }

    /*if it's just one line of code after a for or if statement,
     you don't need the curly braces. you can have it here like I do but it saves characters if you don't add it .*/
    public void add(String item) {
        //for add, if there is an item that matches something already present in the cart, return a null
        for(String inCart: contents)
            if(inCart.equals(item)){
                return;
            }
            //else, add the item to the Linked list contents
            contents.add(item);
    }

    public String delete(int index) {
        //if the number of elements in the contents list is larger than the index, remove the index number indicated
        //.size is the equivalent of .length to an array. When retrieving this value in an array, use length.
        if(index < contents.size()) {
            return contents.remove(index);
        }
        //We return "nothing" because we want to use the word nothing in our print statement.
        return "nothing"; 
    }
    //Taking in an InputStream is like taking in a file. When taking a file as a stream, you need to take the correct reader to read a stream
    public void load(InputStream is) throws IOException { //Do i need to throw an IO exception? Ask Kenneth
        InputStreamReader isr = new InputStreamReader(is); //InputStreamReader basically helps us read characters from a file
        BufferedReader br = new BufferedReader(isr); //idk what this means yet
        String item;
        while((item = br.readLine()) !=null) { //i
            contents.add(item);
        }
        //You must br close before you isr close or you'll throw runtime errors. For the input stream readers it's first in last out. (But why?)
        br.close();
        isr.close();
    }
    
    //When you want to read a file it's input stream
    //BUT when you want to save, it's output stream. The terminology of io seems to be a little bit reversed. 
    public void save(OutputStream os) throws IOException { 
        OutputStreamWriter ows = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(ows);
        for(String item: contents) //no need curly braces cos 1 line of code
            bw.write(item + "\n");

        //Still don't understand how this works
        //for output we need to flush before we close. But for output stream the closing sequence doesn't require any fixed order.  
        ows.flush();
        bw.flush();

        ows.close();
        bw.close();
    }

    public List<String> getContents() { //get contents is basically to retireve the contents linked list
        return contents;
    }
}


//When dealing with io, you need to claim back your resources. You mu

// write codes to read file, delete file, read directories
//A program uses an input stream to read data from a source, one item at a time
//A program uses an output stream to write data to a destination, one item at time --> This is why when saving you use output streams
//Buffered input streams read data from a memory area known as a buffer; the native input API is called only when the buffer is empty. 
//Similarly, buffered output streams write data to a buffer, and the native output API is called only when the buffer is full.
