package vttp2022.day3.workshop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Repository {
    //create a repository where we gonna pull our data from
    private File repository;

    public Repository(String repo) {
        this.repository = new File(repo); //what does this do
    }

    //get ShoppingCarts method and instantiate
    public List<String> getShoppingCarts() {
        List<String> carts = new LinkedList<>();
        for (String n: repository.list()){
            carts.add(n.replace(".cart", ""));
        }
        return carts;
    }
    
    public void save(Cart cart){ //create a save method where you can create a new file and include a .cart at the back. 
        String cartName = cart.getUsername() + ".cart";
        String saveLocation = repository.getPath() + File.separator + cartName; //what does getpath do in this context?
        File saveFile = new File(saveLocation);
        OutputStream os = null; // again dunnno
        try{
            if(!saveFile.exists()) { //if there is no save file, we create a new file
                Path path = Paths.get(repository.getPath());
                Files.createDirectories(path);
                saveFile.createNewFile();
            }

            os = new FileOutputStream(saveLocation);
            cart.save(os);
            os.flush();
            os.close(); //this part is to overwrite the save
            
        } catch(IOException e) {
            e.printStackTrace(); //read up io exception
        }
    }

    public Cart load(String username) {  //the load function uses inputstream to read the previous file
        String cartName = username + ".cart";
        Cart cart = new Cart(username);
        for(File cartFile: repository.listFiles())
            if(cartFile.getName().equals(cartName)) { //if the name from the cart db matches the cartName, load the fileinput stream. 
                try{
                    InputStream is = new FileInputStream(cartFile);
                    cart.load(is);
                    is.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        return cart;
    }
}
