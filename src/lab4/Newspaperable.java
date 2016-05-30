package lab4;

import lab1.Category;
import lab1.News;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Proggeo on 5/29/2016.
 */
public interface Newspaperable extends Remote{
    public String hello() throws RemoteException;
    public void addCategory(int id, String name) throws RemoteException, SQLException;
    public void deleteCategory(int id) throws RemoteException, SQLException;
    public void addNews(int id, int id_cat, String name) throws RemoteException, SQLException;
    public void deleteNews(int id) throws RemoteException, SQLException;
    public void editNews(int id, int id_cat, String name) throws RemoteException, SQLException;
    public int countNews() throws RemoteException;
    public News getNewsById(int id) throws RemoteException;
    public ArrayList<News> getNewsByCategory(int id_cat) throws RemoteException;
    public ArrayList<Category> getCategories() throws RemoteException;
}
