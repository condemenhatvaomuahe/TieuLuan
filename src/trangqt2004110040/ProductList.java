package trangqt2004110040;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProductList {
        static Scanner scanner = new Scanner(System.in);
        static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        static Date today = new Date();
        private List<Product> product = product();

        public void enterToContinue() {
                System.out.println(
                                "-------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Nhấn Enter để tiếp tục!");
                scanner.nextLine();
        }

        private void Cancel(String s) {
                String[] args = null;
                if (s.equalsIgnoreCase("@cancel")) {
                        try {
                                ProductManagement.main(args);
                        } catch (ParseException e) {
                                e.printStackTrace();
                        }
                }
        }

        private ArrayList<Product> product() {
                ArrayList<Product> prod = null;
                prod = FileHandling.readProduct();
                return prod;
        }

        private boolean idIsValid(String id) {
                id.trim();
                for (Product pr : product) {
                        if (pr.getiD().equalsIgnoreCase(id)) {
                                System.out.println("Mã hàng hóa đã tồn tại. Vui lòng nhập lại!");
                                return false;
                        }
                }
                if ((id.startsWith("A") || id.startsWith("F") || id.startsWith("P")) == false) {
                        System.out.println("Mã loại hàng không hợp lệ. Vui lòng nhập lại!");
                        return false;
                }
                String str = id.substring(1);
                if (str.length() != 6) {
                        System.out.println("Độ dài mã hàng hóa không hợp lệ. Vui lòng nhập lại!");
                        return false;
                }
                try {
                        Double.parseDouble(str);
                        return true;
                } catch (NumberFormatException e) {
                        System.out.println(
                                        "Mã hàng hóa chỉ được chứa chữ số ngoại trừ mã loại hàng. Vui lòng nhập lại!");
                        return false;
                }
        }

        private Date inputDate() {
                Date date;
                try {
                        String s = scanner.nextLine();
                        date = formatter.parse(s);
                        Cancel(s);
                } catch (ParseException e) {
                        System.out.println("Ngày, tháng, năm không hợp lệ. Vui lòng nhập lại!");
                        return inputDate();
                }
                return date;
        }

        private String inputString() {
                String str;
                do {
                        str = scanner.nextLine();
                        Cancel(str);
                        if (str.isBlank())
                                System.out.println("Không được để trống. Vui lòng nhập lại!");
                } while (str.isBlank());
                return str;
        }

        private double inputNumber() {
                double number = 0;
                String str = null;
                try {
                        str = scanner.nextLine();
                        number = Double.parseDouble(str);
                } catch (NumberFormatException e) {
                        Cancel(str);
                        System.out.println("Vui lòng nhập số!");
                        return inputNumber();
                }
                return number;
        }

        public void addProduct() {
                String id = "", name = "";
                int amount;
                double price;
                do {
                        System.out.println(
                                        "Mã hàng phải bắt đầu bằng 1 trong 3 ký tự: A, P hoặc F. Và kết thúc với 6 chữ số.");
                        System.out.println("Với: A - Hàng điện máy      P - Hàng sành sứ        F - Hàng thực phẩm");
                        System.out.println("Ví dụ: A220000");
                        System.out.println("Nhập mã hàng hóa:");
                        id = inputString();
                } while (idIsValid(id) == false);
                System.out.println("Nhập tên hàng hóa:");
                name = inputString();
                do {
                        System.out.println("Nhập số lượng tồn kho:");
                        amount = (int) inputNumber();
                        if (amount < 0)
                                System.out.println("Số lượng tồn kho phải lớn hơn hoặc bằng 0. Vui lòng nhập lại!");
                } while (amount < 0);
                do {
                        System.out.println("Nhập đơn giá: ");
                        price = inputNumber();
                        if (price <= 0)
                                System.out.println("Đơn giá phải lớn hơn 0. Vui lòng nhập lại!");
                } while (price <= 0);
                switch (id.charAt(0)) {
                        case 'F': {
                                String supplier = "";
                                Date mfg = new Date(), exp = new Date();
                                System.out.println("Nhập ngày sản xuất: ");
                                do {
                                        mfg = inputDate();
                                        if (mfg.after(today))
                                                System.out.println(
                                                                "Ngày sản xuất không được nằm sau ngày hôm nay. Vui lòng nhập lại!");
                                } while (mfg.after(today));
                                do {
                                        System.out.println("Nhập ngày hết hạn: ");
                                        exp = inputDate();
                                        if (exp.before(mfg))
                                                System.out.println(
                                                                "Ngày hết hạn phải nằm sau hoặc cùng với ngày sản xuất. Vui lòng nhập lại!");
                                } while (exp.before(mfg));
                                System.out.println("Nhập tên nhà sản xuất:");
                                supplier = inputString();
                                Food food = new Food(id, name, amount, price, mfg, exp, supplier);
                                product.add(food);
                        }
                                break;
                        case 'A': {
                                int warrantyMonths;
                                double capacity;
                                do {
                                        System.out.println("Nhập số tháng bảo hành: ");
                                        warrantyMonths = (int) inputNumber();
                                        if (warrantyMonths < 0)
                                                System.out.println(
                                                                "Số tháng bảo hành phải lớn hơn hoặc bằng 0. Vui lòng nhập lại");
                                } while (warrantyMonths < 0);
                                do {
                                        System.out.println("Nhập công suất (kW):");
                                        capacity = inputNumber();
                                        if (capacity <= 0)
                                                System.out.println(
                                                                "Công suất phải lớn hơn 0. Vui lòng nhập lại");
                                } while (capacity <= 0);
                                Appliance appliance = new Appliance(id, name, amount, price, warrantyMonths, capacity);
                                product.add(appliance);
                        }
                                break;
                        case 'P': {
                                String supplier = "";
                                Date importDate = new Date();
                                System.out.println("Nhập nhà sản xuất: ");
                                supplier = inputString();
                                do {
                                        System.out.println("Nhập ngày nhập kho: ");
                                        importDate = inputDate();
                                        if (importDate.after(today))
                                                System.out.println(
                                                                "Ngày nhập kho không được nằm sau ngày hôm nay. Vui lòng nhập lại!");
                                } while (importDate.after(today));
                                Pottery pottery = new Pottery(id, name, amount, price, supplier, importDate);
                                product.add(pottery);
                        }
                                break;
                }
                FileHandling.writeProduct(product);
                System.out.println("Nhập mặt hàng thành công!");
                enterToContinue();
        }

        public void printProduct() {
                System.out.printf("%-10s %-10s %-35s %-5s %-15s %-15s %-20s\n", "STT", "Mã hàng", "Tên hàng hóa",
                                "SL", "Đơn giá", "VAT", "Đánh giá");
                for (Product pr : product) {
                        System.out.printf("%-10d %-10s %-35s %-5d %-15.2f %-15.2f %-20s\n", product.indexOf(pr) + 1,
                                        pr.getiD(), pr.getName(), pr.getAmount(), pr.getPrice(), pr.VAT(), pr.grade());
                }
                enterToContinue();
        }

        public void printFood() {
                System.out.printf("%-10s %-35s %-5s %-15s %-20s %-15s %-20s %-15s %-20s\n", "Mã hàng",
                                "Tên hàng hóa", "SL", "Đơn giá", "Ngày SX", "Ngày hết hạn", "Nhà cung cấp",
                                "VAT", "Đánh giá");
                for (Product pr : product) {
                        if (pr instanceof Food) {
                                Food f = new Food();
                                f = (Food) pr;
                                System.out.printf("%-10s %-35s %-5d %-15.3f %-20s %-15s %-20s %-15.2f %-20s\n",
                                                f.getiD(), f.getName(), f.getAmount(), f.getPrice(),
                                                formatter.format(f.getMFG()), formatter.format(f.getEXP()),
                                                f.getSupplier(), pr.VAT(), pr.grade());
                        }
                }
                enterToContinue();
        }

        public void printAppliance() {
                System.out.printf("%-10s %-35s %-5s %-15s %-20s %-15s %-15s %-20s\n", "Mã hàng", "Tên hàng hóa",
                                "SL", "Đơn giá", "Tháng bảo hành", "Công suất", "VAT", "Đánh giá");
                for (Product pr : product) {
                        if (pr instanceof Appliance) {
                                Appliance a = new Appliance();
                                a = (Appliance) pr;
                                System.out.printf("%-10s %-35s %-5d %-15.3f %-20d %-15f %-15.2f %-20s\n", a.getiD(),
                                                a.getName(), a.getAmount(), a.getPrice(), a.getWarrantyMonths(),
                                                a.getCapacity(), pr.VAT(), pr.grade());
                        }
                }
                enterToContinue();
        }

        public void printPottery() {
                System.out.printf("%-10s %-35s %-5s %-15s %-20s %-15s %-15s %-20s\n", "Mã hàng", "Tên hàng hóa",
                                "SL", "Đơn giá", "Nhà sản xuất", "Ngày nhập kho", "VAT", "Đánh giá");
                for (Product pr : product) {
                        if (pr instanceof Pottery) {
                                Pottery p = new Pottery();
                                p = (Pottery) pr;
                                System.out.printf("%-10s %-35s %-5d %-15.3f %-20s %-15s %-15.2f %-20s\n", p.getiD(),
                                                p.getName(), p.getAmount(), p.getPrice(), p.getSupplier(),
                                                formatter.format(p.getImportDate()), pr.VAT(), pr.grade());
                        }
                }
                enterToContinue();
        }

        public void deleteViaIndex() {
                printProduct();
                int index;
                String yn;
                scanner.nextLine();
                do {
                        System.out.println("Nhập STT mặt hàng muốn xóa:");
                        index = (int) (inputNumber() - 1);
                        if(index > (product.size()-1) || index<0)
                        	System.out.println("Số thứ tự không tồn tại. Vui lòng nhập lại!");
                } while (index > (product.size()-1) || index<0);
                System.out.println(product.get(index));
                do {
                        System.out.println("Bạn có chắc muốn xóa mặt hàng này khỏi kho?(y/n)");
                        yn = inputString();
                        if (yn.equalsIgnoreCase("n"))
                                break;
                } while ((yn.equalsIgnoreCase("n") || yn.equalsIgnoreCase("y")) == false);
                if (yn.equalsIgnoreCase("y")) {
                        product.remove(index);
                        System.out.println("Xóa mặt hàng thành công. ");
                } else
                        System.out.println("Đã hủy xóa.");
                FileHandling.writeProduct(product);
                enterToContinue();
        }

        public void deleteViaID() {
                int index = searchByID();
                String yn;
                do {
                        System.out.println("Bạn có chắc muốn xóa mặt hàng này khỏi kho?(y/n)");
                        yn = inputString();
                        if (yn.equalsIgnoreCase("n"))
                                break;
                } while ((yn.equalsIgnoreCase("n") || yn.equalsIgnoreCase("y")) == false);
                if (yn.equalsIgnoreCase("y")) {
                        product.remove(index);
                        System.out.println("Xóa mặt hàng thành công. ");
                } else
                        System.out.println("Đã hủy xóa.");
                FileHandling.writeProduct(product);
                enterToContinue();
        }

        public void editViaID() {
                int index = -1;
                String id;
                do {
                        System.out.println("Nhập mã hàng muốn sửa thông tin:");
                        scanner.nextLine();
                        id = inputString();
                        for (Product pr : product) {
                                if (pr.getiD().equalsIgnoreCase(id)) {
                                        index = product.indexOf(pr);
                                        break;
                                }
                        }
                        if (index == -1)
                                System.out.println("Mã hàng bạn đã nhập không tồn tại. Vui lòng nhập lại!");
                } while (index == -1);
                System.out.println(product.get(index));
                editInfo(index);
                FileHandling.writeProduct(product);
                enterToContinue();
        }

        public void editViaIndex() {
                printProduct();
                int index;
                do {
                        System.out.println("Nhập STT mặt hàng muốn sửa thông tin:");
                        index = (int) (inputNumber() - 1);
                        if (index > product.size())
                                System.out.println("STT không tồn tại. Vui lòng nhập lại!");
                } while (index > product.size());
                System.out.println(product.get(index));
                editInfo(index);
                FileHandling.writeProduct(product);
                enterToContinue();
        }

        private void editInfo(int index) {
                int n = -1, m;
                do {
                        m = 6;
                        System.out.println("1. Tên hàng hóa");
                        System.out.println("2. Số lượng tồn kho");
                        System.out.println("3. Đơn giá");
                        switch (product.get(index).getiD().charAt(0)) {
                                case 'F': {
                                        System.out.println("4. Ngày sản xuất");
                                        System.out.println("5. Ngày hết hạn");
                                        System.out.println("6. Tên nhà sản xuất");
                                        System.out.println("7. Chỉnh sửa toàn bộ");
                                        m = 7;

                                }
                                        break;
                                case 'A': {
                                        System.out.println("4. Số tháng bảo hành");
                                        System.out.println("5. Công suất (kW)");
                                        System.out.println("6. Chỉnh sửa toàn bộ");
                                }
                                        break;
                                case 'P': {
                                        System.out.println("4. Nhà sản xuất");
                                        System.out.println("5. Ngày nhập kho");
                                        System.out.println("6. Chỉnh sửa toàn bộ");
                                }
                                        break;
                        }
                        System.out.println("0. Hủy chỉnh sửa");
                        n = (int) inputNumber();
                        if (n >= 0 && n <= m) {
                                switch (product.get(index).getiD().charAt(0)) {
                                        case 'F': {
                                                product.set(index, editFood(n, m, index));
                                                FileHandling.writeProduct(product);
                                        }
                                                break;
                                        case 'A': {
                                                product.set(index, editAppliance(n, m, index));
                                                FileHandling.writeProduct(product);
                                        }
                                                break;
                                        case 'P': {
                                                product.set(index, editPottery(n, m, index));
                                                FileHandling.writeProduct(product);
                                        }
                                }
                        } else
                                System.out.println("Vui lòng chọn số hợp lệ!");
                } while (n < 0 || n > m);
                if(n!=0)
                	System.out.println("Chỉnh sửa thành công!");
                else
                	System.out.println("Đã hủy chỉnh sửa!");
        }

        private Product editProduct(int n, int m, int index) {
                Product prod = (Product) product.get(index);
                switch (n) {
                        case 1: {
                                System.out.println("Nhập tên hàng hóa mới:");
                                prod.setName(inputString());
                        }
                                break;
                        case 2: {
                                int amount = -1;
                                do {
                                        System.out.println("Nhập số lượng tồn kho mới:");
                                        amount = (int) inputNumber();
                                        prod.setAmount(amount);
                                } while (amount < 0);
                        }
                                break;
                        case 3: {
                                double price;
                                do {
                                        System.out.println("Nhập đơn giá mới:");
                                        price = inputNumber();
                                        prod.setPrice(price);
                                } while (price <= 0);
                        }
                                break;
                }
                if (n == m) {
                        System.out.println("Nhập tên hàng hóa mới:");
                        prod.setName(inputString());
                        int amount = -1;
                        do {
                                System.out.println("Nhập số lượng tồn kho mới:");
                                amount = (int) inputNumber();
                                prod.setAmount(amount);
                        } while (amount < 0);
                        double price;
                        do {
                                System.out.println("Nhập đơn giá mới:");
                                price = inputNumber();
                                prod.setPrice(price);
                        } while (price <= 0);
                }
                return prod;
        }

        private Pottery editPottery(int n, int m, int index) {
                Pottery pottery = (Pottery) product.get(index);
                pottery = (Pottery) editProduct(n, m, index);
                switch (n) {
                        case 4: {
                                System.out.println("Nhập nhà sản xuất mới:");
                                pottery.setSupplier(inputString());
                        }
                                break;
                        case 5: {
                                Date date = null;
                                do {
                                        System.out.println(
                                                        "Nhập ngày nhập mới:");
                                        date = inputDate();
                                        pottery.setImportDate(date);
                                } while (date.after(today) || date == null);
                        }
                                break;
                        case 6: {
                                System.out.println("Nhập nhà sản xuất mới:");
                                pottery.setSupplier(inputString());
                                Date date = null;
                                do {
                                        System.out.println(
                                                        "Nhập ngày nhập mới:");
                                        date = inputDate();
                                        pottery.setImportDate(date);
                                } while (date.after(today) || date == null);
                        }
                                break;
                }
                return pottery;
        }

        private Appliance editAppliance(int n, int m, int index) {
                Appliance appliance = (Appliance) product.get(index);
                appliance = (Appliance) editProduct(n, m, index);
                switch (n) {
                        case 4: {
                                int warrantyMonths;
                                do {
                                        System.out.println(
                                                        "Nhập số tháng bảo hành mới:");
                                        warrantyMonths = (int) inputNumber();
                                        appliance.setWarrantyMonths(
                                                        warrantyMonths);
                                } while (warrantyMonths < 0);
                        }
                                break;
                        case 5: {
                                double capacity;
                                do {
                                        System.out.println(
                                                        "Nhập công suất mới:");
                                        capacity = inputNumber();
                                        appliance.setCapacity(capacity);
                                } while (capacity <= 0);
                        }
                                break;
                        case 6: {
                                int warrantyMonths;
                                do {
                                        System.out.println(
                                                        "Nhập số tháng bảo hành mới:");
                                        warrantyMonths = (int) inputNumber();
                                        appliance.setWarrantyMonths(
                                                        warrantyMonths);
                                } while (warrantyMonths < 0);
                                double capacity;
                                do {
                                        System.out.println(
                                                        "Nhập công suất mới:");
                                        capacity = inputNumber();
                                        appliance.setCapacity(capacity);
                                } while (capacity <= 0);
                        }
                                break;
                }
                return appliance;
        }

        private Food editFood(int n, int m, int index) {
                Food food = (Food) product.get(index);
                food = (Food) editProduct(n, m, index);
                switch (n) {
                        case 4: {
                                Date date = null;
                                do {
                                        System.out.println(
                                                        "Nhập ngày sản xuất mới:");
                                        date = inputDate();
                                        food.setMFG(date);
                                } while (date.after(food.getEXP())
                                                || date == null
                                                || date.after(today));
                        }
                                break;
                        case 5: {
                                Date date = null;
                                do {
                                        System.out.println(
                                                        "Nhập ngày hết hạn mới:");
                                        date = inputDate();
                                        food.setEXP(date);
                                } while (date.before(food.getMFG())
                                                || date == null);
                        }
                                break;
                        case 6: {
                                System.out.println("Nhập nhà cung cấp mới:");
                                food.setSupplier(inputString());
                        }
                                break;
                        case 7: {
                                Date date = null;
                                do {
                                        System.out.println(
                                                        "Nhập ngày sản xuất mới:");
                                        date = inputDate();
                                        food.setMFG(date);
                                } while (date.after(food.getEXP())
                                                || date == null
                                                || date.after(today));
                                date = null;
                                do {
                                        System.out.println(
                                                        "Nhập ngày hết hạn mới:");
                                        date = inputDate();
                                        food.setEXP(date);
                                } while (date.before(food.getMFG())
                                                || date == null);
                                System.out.println("Nhập nhà cung cấp mới:");
                                food.setSupplier(inputString());
                        }
                }
                return food;
        }

        public int searchByID() {
                int index = -1;
                scanner.nextLine();
                do {
                        System.out.println("Nhập mã hàng hóa:");
                        String id = inputString();
                        for (Product pr : product) {
                                if (pr.getiD().equalsIgnoreCase(id)) {
                                        index = product.indexOf(pr);
                                        break;
                                }
                        }
                        if (index == -1)
                                System.out.println("Mã hàng bạn đã nhập không tồn tại. Vui lòng nhập lại!");
                } while (index == -1);
                System.out.println("Thông tin sản phẩm:");
                System.out.println(product.get(index).toString());
                return index;
        }

        public void searchByPrice() {
                int index = -1;
                scanner.nextLine();
                System.out.println("Nhập đơn giá tối thiểu:");
                int min = (int) inputNumber();
                int max = min - 1;
                do {
                        System.out.println("Nhập đơn giá tối đa:");
                        max = (int) inputNumber();
                        if (max < min)
                                System.out.println("Giá tối đa không thể bé hơn giá tối thiểu. Vui lòng nhập lại!");
                } while (max < min);
                for (Product pr : product) {
                        if (pr.getPrice() <= max && pr.getPrice() >= min) {
                                System.out.println(pr.toString());
                                index = product.indexOf(pr);
                        }
                }
                if (index == -1)
                        System.out.println("Không có mặt hàng nào nằm trong khoảng giá này.");
                enterToContinue();
        }

        public void gradingStatistics() {
                int unrated = 0, difficultToSell = 0, bestSelling = 0;
                for (Product pr : product) {
                        switch (pr.grade()) {
                                case "Bán được":
                                        bestSelling++;
                                        break;
                                case "Khó bán":
                                        difficultToSell++;
                                        break;
                                case "":
                                        unrated++;
                                        break;
                                default:
                                        break;
                        }
                }
                System.out.println("Thống kê số liệu hàng trong kho theo đánh giá mức độ bán buôn:");
                System.out.println("- Hàng bán được: " + bestSelling);
                System.out.println("- Hàng khó bán: " + difficultToSell);
                System.out.println("- Hàng chưa được đánh giá: " + unrated);
        }

        public void yearlyStatisticsPottery() {
                int i = 0, l = 0;
                int[] count = new int[product.size()], totalAmount = new int[product.size()];
                Double[] totalPrice = new Double[product.size()];
                String[] year = new String[product.size()];
                for (Product pr : product) {
                        if (pr instanceof Pottery) {
                                Pottery pottery = (Pottery) pr;
                                String[] ymd = formatter.format(pottery.getImportDate()).split("/");
                                if (l == 0) {
                                        year[i] = ymd[2];
                                        totalAmount[i] = pr.getAmount();
                                        totalPrice[i] = pr.getAmount() * pr.getPrice();
                                        count[i] = 1;
                                } else {
                                        boolean existed = false;
                                        int index = -1;
                                        for (int j = 0; j <= i; j++) {
                                                if (year[j].equalsIgnoreCase(ymd[2])) {
                                                        existed = true;
                                                        index = j;
                                                }
                                        }
                                        if (existed) {
                                                count[index]++;
                                                totalAmount[index] += pr.getAmount();
                                                totalPrice[index] += pr.getAmount() * pr.getPrice();
                                        } else {
                                                i++;
                                                year[i] = ymd[2];
                                                totalAmount[i] = pr.getAmount();
                                                totalPrice[i] = pr.getAmount() * pr.getPrice();
                                                count[i] = 1;
                                        }
                                }
                                l++;
                        }
                }
                for (int j = 0; year[j] != null; j++) {
                        System.out.println("Năm nhập kho " + year[j] + ":");
                        System.out.println(" - Số mặt hàng: " + count[j]);
                        System.out.println(" - Tổng số lượng hàng hóa: " + totalAmount[j]);
                        System.out.println(" - Tổng giá trị hàng hóa: " + totalPrice[j]);
                }
                enterToContinue();
        }

        public void yearlyStatisticsFoodMFG() {
                int i = 0, l = 0;
                int[] count = new int[product.size()], totalAmount = new int[product.size()];
                Double[] totalPrice = new Double[product.size()];
                String[] year = new String[product.size()];
                for (Product pr : product) {
                        if (pr instanceof Food) {
                                Food pottery = (Food) pr;
                                String[] ymd = formatter.format(pottery.getMFG()).split("/");
                                if (l == 0) {
                                        year[i] = ymd[2];
                                        totalAmount[i] = pr.getAmount();
                                        totalPrice[i] = pr.getAmount() * pr.getPrice();
                                        count[i] = 1;
                                } else {
                                        boolean existed = false;
                                        int index = -1;
                                        for (int j = 0; j <= i; j++) {
                                                if (year[j].equalsIgnoreCase(ymd[2])) {
                                                        existed = true;
                                                        index = j;
                                                }
                                        }
                                        if (existed) {
                                                count[index]++;
                                                totalAmount[index] += pr.getAmount();
                                                totalPrice[index] += pr.getAmount() * pr.getPrice();
                                        } else {
                                                i++;
                                                year[i] = ymd[2];
                                                totalAmount[i] = pr.getAmount();
                                                totalPrice[i] = pr.getAmount() * pr.getPrice();
                                                count[i] = 1;
                                        }
                                }
                                l++;
                        }
                }
                for (int j = 0; year[j] != null; j++) {
                        System.out.println("Năm sản xuất " + year[j] + ":");
                        System.out.println(" - Số mặt hàng: " + count[j]);
                        System.out.println(" - Tổng số lượng hàng hóa: " + totalAmount[j]);
                        System.out.println(" - Tổng giá trị hàng hóa: " + totalPrice[j]);
                }
                enterToContinue();
        }

        public void yearlyStatisticsFoodEXP() {
                int i = 0, l = 0;
                int[] count = new int[product.size()], totalAmount = new int[product.size()];
                Double[] totalPrice = new Double[product.size()];
                String[] year = new String[product.size()];
                for (Product pr : product) {
                        if (pr instanceof Food) {
                                Food pottery = (Food) pr;
                                String[] ymd = formatter.format(pottery.getEXP()).split("/");
                                if (l == 0) {
                                        year[i] = ymd[2];
                                        totalAmount[i] = pr.getAmount();
                                        totalPrice[i] = pr.getAmount() * pr.getPrice();
                                        count[i] = 1;
                                } else {
                                        boolean existed = false;
                                        int index = -1;
                                        for (int j = 0; j <= i; j++) {
                                                if (year[j].equalsIgnoreCase(ymd[2])) {
                                                        existed = true;
                                                        index = j;
                                                }
                                        }
                                        if (existed) {
                                                count[index]++;
                                                totalAmount[index] += pr.getAmount();
                                                totalPrice[index] += pr.getAmount() * pr.getPrice();
                                        } else {
                                                i++;
                                                year[i] = ymd[2];
                                                totalAmount[i] = pr.getAmount();
                                                totalPrice[i] = pr.getAmount() * pr.getPrice();
                                                count[i] = 1;
                                        }
                                }
                                l++;
                        }
                }
                for (int j = 0; year[j] != null; j++) {
                        System.out.println("Năm hết hạn " + year[j] + ":");
                        System.out.println(" - Số mặt hàng: " + count[j]);
                        System.out.println(" - Tổng số lượng hàng hóa: " + totalAmount[j]);
                        System.out.println(" - Tổng giá trị hàng hóa: " + totalPrice[j]);
                }
                enterToContinue();
        }

        public void report() {
                int potteryCount = 0, applianceCount = 0, foodCount = 0;
                int potteryAmount = 0, applianceAmount = 0, foodAmount = 0;
                double potterySum = 0, applianceSum = 0, foodSum = 0;
                int a = 0, b = 0, c = 0;
                for (Product pr : product) {
                        if (pr instanceof Pottery) {
                                potteryCount++;
                                potteryAmount += pr.getAmount();
                                potterySum += pr.getAmount() * pr.getPrice();
                                if (pr.grade().equalsIgnoreCase("Khó bán"))
                                        a++;
                        }
                        if (pr instanceof Food) {
                                foodCount++;
                                foodAmount += pr.getAmount();
                                foodSum += pr.getAmount() * pr.getPrice();
                                if (pr.grade().equalsIgnoreCase("Khó bán"))
                                        b++;
                        }
                        if (pr instanceof Appliance) {
                                applianceCount++;
                                applianceAmount += pr.getAmount();
                                applianceSum += pr.getAmount() * pr.getPrice();
                                if (pr.grade().equalsIgnoreCase("Bán được"))
                                        c++;
                        }
                }
                System.out.println("Báo cáo tổng kho:");
                System.out.println(" > Hàng điện máy:");
                System.out.println("    - Số lượng mặt hàng: " + applianceCount);
                System.out.println("    - Tổng số lượng hàng hóa: " + applianceAmount);
                System.out.println("    - Tổng giá trị trong kho: " + applianceSum);
                System.out.println("    - Số lượng hàng khó bán: " + c);
                System.out.println(" > Hàng thực phẩm:");
                System.out.println("    - Số lượng mặt hàng: " + foodCount);
                System.out.println("    - Tổng số lượng hàng hóa: " + foodAmount);
                System.out.println("    - Tổng giá trị trong kho: " + foodSum);
                System.out.println("    - Số lượng hàng khó bán: " + b);
                System.out.println(" > Hàng sành sứ:");
                System.out.println("    - Số lượng mặt hàng: " + potteryCount);
                System.out.println("    - Tổng số lượng hàng hóa: " + potteryAmount);
                System.out.println("    - Tổng giá trị trong kho: " + potterySum);
                System.out.println("    - Số lượng hàng bán được: " + a);
                System.out.println(" > Toàn bộ kho:");
                System.out.println("    - Số lượng mặt hàng: " + product.size());
                System.out.println("    - Tổng số lượng hàng hóa: " + (applianceAmount + foodAmount + potteryAmount));
                System.out.println("    - Tổng giá trị trong kho: " + (applianceSum + foodSum + potterySum));
                System.out.println("    - Số lượng hàng khó bán: " + c + b);
                System.out.println("    - Số lượng hàng bán được: " + a);
                enterToContinue();
        }

        public void sortByNameAccending() {
                Product temp;
                for (int i = 0; i < product.size(); i++) {
                        for (int j = i + 1; j < product.size(); j++) {
                                if (product.get(i).getName().compareTo(product.get(j).getName()) > 0) {
                                        temp = product.get(i);
                                        product.set(i, product.get(j));
                                        product.set(j, temp);
                                }
                        }
                }
                FileHandling.writeProduct(product);
                System.out.println("Sắp xếp thành công!");
        }

        public void sortByIDAccending() {
                Product temp;
                for (int i = 0; i < product.size(); i++) {
                        for (int j = i + 1; j < product.size(); j++) {
                                if (product.get(i).getiD().compareTo(product.get(j).getiD()) > 0) {
                                        temp = product.get(i);
                                        product.set(i, product.get(j));
                                        product.set(j, temp);
                                }
                        }
                }
                FileHandling.writeProduct(product);
                System.out.println("Sắp xếp thành công!");
        }

        public void sortByPriceAccending() {
                Product temp;
                for (int i = 0; i < product.size(); i++) {
                        for (int j = i + 1; j < product.size(); j++) {
                                if (product.get(i).getPrice() > product.get(j).getPrice()) {
                                        temp = product.get(i);
                                        product.set(i, product.get(j));
                                        product.set(j, temp);
                                }
                        }
                }
                FileHandling.writeProduct(product);
                System.out.println("Sắp xếp thành công!");
        }

        public void sortByVATAccending() {
                Product temp;
                for (int i = 0; i < product.size(); i++) {
                        for (int j = i + 1; j < product.size(); j++) {
                                if (product.get(i).VAT() > product.get(j).VAT()) {
                                        temp = product.get(i);
                                        product.set(i, product.get(j));
                                        product.set(j, temp);
                                }
                        }
                }
                FileHandling.writeProduct(product);
                System.out.println("Sắp xếp thành công!");
        }

        public void sortByNameDecending() {
                Product temp;
                for (int i = 0; i < product.size(); i++) {
                        for (int j = i + 1; j < product.size(); j++) {
                                if (product.get(i).getName().compareTo(product.get(j).getName()) < 0) {
                                        temp = product.get(i);
                                        product.set(i, product.get(j));
                                        product.set(j, temp);
                                }
                        }
                }
                FileHandling.writeProduct(product);
                System.out.println("Sắp xếp thành công!");
        }

        public void sortByIDDecending() {
                Product temp;
                for (int i = 0; i < product.size(); i++) {
                        for (int j = i + 1; j < product.size(); j++) {
                                if (product.get(i).getiD().compareTo(product.get(j).getiD()) < 0) {
                                        temp = product.get(i);
                                        product.set(i, product.get(j));
                                        product.set(j, temp);
                                }
                        }
                }
                FileHandling.writeProduct(product);
                System.out.println("Sắp xếp thành công!");
        }

        public void sortByPriceDecending() {
                Product temp;
                for (int i = 0; i < product.size(); i++) {
                        for (int j = i + 1; j < product.size(); j++) {
                                if (product.get(i).getPrice() < product.get(j).getPrice()) {
                                        temp = product.get(i);
                                        product.set(i, product.get(j));
                                        product.set(j, temp);
                                }
                        }
                }
                FileHandling.writeProduct(product);
                System.out.println("Sắp xếp thành công!");
        }

        public void sortByVATDecending() {
                Product temp;
                for (int i = 0; i < product.size(); i++) {
                        for (int j = i + 1; j < product.size(); j++) {
                                if (product.get(i).VAT() < product.get(j).VAT()) {
                                        temp = product.get(i);
                                        product.set(i, product.get(j));
                                        product.set(j, temp);
                                }
                        }
                }
                FileHandling.writeProduct(product);
                System.out.println("Sắp xếp thành công!");
        }
}
