package data;

import classs.Categories;
import classs.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<Categories> lstCtg = new ArrayList<>();
    public static List<Product> lstPrd = new ArrayList<>();

    public void setLstCtg(){
        File f = new File("F:\\study\\java\\doc ghi file\\category.txt");
        try  {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lstCtg);
            fos.close();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getLstCtg(){
        File f = new File("F:\\study\\java\\doc ghi file\\category.txt");
        try  {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Categories> data = ( List<Categories>) ois.readObject();
            for (Categories ctgL: data){
                lstCtg.add(ctgL);
            }
            fis.close();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
