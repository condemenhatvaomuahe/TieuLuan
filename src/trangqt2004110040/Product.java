package trangqt2004110040;

import java.io.Serializable;

public abstract class Product implements Serializable {
    private String iD;
    private String name;
    private int amount;
    private double price;

    public Product() {
    }

    public Product(String iD, String name, int amount, double price) {
        this.iD = iD;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getiD() {
        return iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank())
            System.out.println("Tên hàng hóa không được để trống. Vui lòng nhập lại!");
        else
            this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount < 0)
            System.out.println("Số lượng tồn kho phải lớn hơn hoặc bằng 0. Vui lòng nhập lại");
        else
            this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0)
            System.out.println("Giá tiền phải lớn hơn 0. Vui lòng nhập lại!");
        else
            this.price = price;
    }

    public abstract double VAT();

    public abstract String grade();

    @Override
    public String toString() {
        return "Mã hàng = " + iD + ", Tên = " + name + ", Số lượng = " + amount + ", Đơn giá = " + price;
    }

}
