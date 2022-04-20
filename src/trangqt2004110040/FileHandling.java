package trangqt2004110040;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {

    public static void writeProduct(List<Product> product) {
        try {
            FileOutputStream fos = new FileOutputStream("ExistedProducts.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(product);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> readProduct() {
        ArrayList<Product> prod = new ArrayList<Product>();
        try {
            FileInputStream fis = new FileInputStream("ExistedProducts.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            prod = (ArrayList<Product>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception ex) {
            System.out.println("Lỗi đọc file: " + ex);
        }
        return prod;
    }

}
