package trangqt2004110040;

public class Appliance extends Product {
    private int warrantyMonths;
    private double capacity;

    public Appliance() {
    }

    public Appliance(String iD, String name, int amount, double price, int warrantyMonths, double capacity) {
        super(iD, name, amount, price);
        this.warrantyMonths = warrantyMonths;
        this.capacity = capacity;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        if (warrantyMonths < 0)
            System.out.println("Số tháng bảo hành không thể là số âm. Vui lòng nhập lại!");
        else
            this.warrantyMonths = warrantyMonths;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        if (capacity <= 0)
            System.out.println("Công suất phải lớn hơn 0. Vui lòng nhập lại!");
        else
            this.capacity = capacity;
    }

    @Override
    public double VAT() {
        return getAmount() * getPrice() * 0.1;
    }

    @Override
    public String grade() {
        if (getAmount() < 3)
            return "Bán được";
        return "";
    }

    @Override
    public String toString() {
        return "Hàng điện máy [" + super.toString() + "đ, Công suất = " + capacity + ", Tháng bảo hành = "
                + warrantyMonths
                + " tháng]";
    }

}
