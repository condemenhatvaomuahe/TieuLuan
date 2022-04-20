package trangqt2004110040;

import java.util.Date;

public class Pottery extends Product {
    private String supplier;
    private Date importDate;

    public Pottery() {
    }

    public Pottery(String iD, String name, int amount, double price, String supplier, Date importDate) {
        super(iD, name, amount, price);
        this.supplier = supplier;
        this.importDate = importDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        if (supplier.isBlank())
            System.out.println("Nhà sản xuất không được để trống. Vui lòng nhập lại!");
        else
            this.supplier = supplier;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        if (importDate.after(ProductList.today))
            System.out.println("Ngày nhập kho không được nằm sau ngày hôm nay. Vui lòng nhập lại!");
        else
            this.importDate = importDate;
    }

    @Override
    public double VAT() {
        return getAmount() * getPrice() * 0.1;
    }

    @Override
    public String grade() {
        if (getAmount() > 50 && storingDays() > 10)
            return "Khó bán";
        return "";
    }

    public long storingDays() {
        Date date = new Date();
        return (date.getTime() - getImportDate().getTime()) / (24 * 3600 * 1000);
    }

    @Override
    public String toString() {
        return "Hàng sành sứ [" + super.toString() + "đ ,Nhà sản xuất = " + supplier + ", Ngày nhập kho = "
                + ProductList.formatter.format(importDate) + "]";
    }

}
