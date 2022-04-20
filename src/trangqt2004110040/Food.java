package trangqt2004110040;

import java.util.Date;

public class Food extends Product {
    private Date MFG, EXP;
    private String supplier;

    public Food() {
    }

    public Food(String iD, String name, int amount, double price, Date mFG, Date eXP, String supplier) {
        super(iD, name, amount, price);
        MFG = mFG;
        EXP = eXP;
        this.supplier = supplier;
    }

    public Date getMFG() {
        return MFG;
    }

    public void setMFG(Date mFG) {
        if (mFG.after(ProductList.today))
            System.out.println("Ngày sản xuất không được nằm sau ngày hiện tại. Vui lòng nhập lại!");
        else if (mFG.after(getEXP()))
            System.out.println("Ngày sản xuất không được nằm sau ngày hết hạn. Vui lòng nhập lại!");
        else
            this.MFG = mFG;
    }

    public Date getEXP() {
        return EXP;
    }

    public void setEXP(Date eXP) {
        if (eXP.before(getMFG()))
            System.out.println("Ngày hết hạn không được nằm sau ngày sản xuất. Vui lòng nhập lại!");
        else
            this.EXP = eXP;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public double VAT() {
        return getAmount() * getPrice() * 0.05;
    }

    @Override
    public String grade() {
        if (isExpired())
            return "Khó bán";
        return "";
    }

    private boolean isExpired() {
        Date date = new Date();
        if (getEXP().before(date))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Hàng thực phẩm [" + super.toString() + "đ , NSX = " + ProductList.formatter.format(MFG) + ", HSD = "
                + ProductList.formatter.format(EXP) + ", Nhà cung cấp = "
                + supplier + "]";
    }

}
