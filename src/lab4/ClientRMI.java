package lab4;

import lab1.Category;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Proggeo on 5/29/2016.
 */
public class ClientRMI {
    public static void main(String[] args) throws
            MalformedURLException, RemoteException, NotBoundException {
        Newspaperable s =
                (Newspaperable) Naming.lookup("//127.0.0.1/SayHello");
        String message = s.hello();
        System.out.println(message);
        ArrayList<Category> categories = s.getCategories();
        for (Category category : categories) {
            System.out.println(category.getId() + "  " + category.getName());
        }
    }
}