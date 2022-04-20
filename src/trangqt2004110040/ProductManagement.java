package trangqt2004110040;

import java.text.ParseException;
import java.util.InputMismatchException;

public class ProductManagement {

    private static int inputOption() {
        int option = -1;
        try {
            option = ProductList.scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Vui lòng nhập số!");
            ProductList.scanner.nextLine();
//            inputOption();
        }
        return option;
    }

    public static void main(String[] args) throws ParseException {
        ProductList productList = new ProductList();
        int Option = -1;
        do {
            System.out.println("MENU QUẢN LÝ KHO HÀNG:");
            System.out.println("1. Thêm một mặt hàng vào kho");
            System.out.println("2. Xóa một mặt hàng khỏi kho");
            System.out.println("3. Chỉnh sửa thông tin hàng hóa");
            System.out.println("4. In ra thông tin hàng hóa");
            System.out.println("5. Tìm kiếm hàng hóa");
            System.out.println("6. Thống kê");
            System.out.println("7. Báo cáo");
            System.out.println("8. Sắp xếp");
            System.out.println("0. Thoát");
            System.out.print("Nhập chức năng bạn muốn thao tác (0~8):   ");
            Option = inputOption();
            ProductList.scanner.nextLine();
            if (Option == 0)
                break;
            System.out.println();
            System.out.println("*** Để thoát ra Menu chính lúc đang nhập dữ liệu, hãy nhập '@cancel'***");
            switch (Option) {
                case 1: {
                    productList.addProduct();
                }
                    break;
                case 2: {
                    int option1;
                    do {
                        System.out.println("1. Xóa theo mã hàng hóa");
                        System.out.println("2. Xóa theo số thứ tự");
                        System.out.println("0. Về Menu chính");
                        option1 = inputOption();
                        switch (option1) {
                            case 1: {
                                productList.deleteViaID();
                            }
                                break;
                            case 2: {
                                productList.deleteViaIndex();
                            }
                                break;
                            case 0:
                                break;
                            default:
                                break;
                        }
                    } while (option1 != 0);
                }
                    break;
                case 3: {
                    int option1;
                    do {
                        System.out.println("1. Chỉnh sửa theo mã hàng hóa");
                        System.out.println("2. Chỉnh sửa theo số thứ tự");
                        System.out.println("0. Về Menu chính");
                        option1 = inputOption();
                        switch (option1) {
                            case 1: {
                                productList.editViaID();
                            }
                                break;
                            case 2: {
                                productList.editViaIndex();
                            }
                                break;
                            case 0:
                                break;
                            default:
                                break;
                        }
                    } while (option1 != 0);
                }
                    break;
                case 4: {
                    int option1;
                    do {
                        System.out.println("1. In các hàng hóa thực phẩm");
                        System.out.println("2. In các hàng hóa điện máy");
                        System.out.println("3. In các hàng hóa sành sứ");
                        System.out.println("4. In tất cả hàng hóa");
                        System.out.println("0. Về Menu chính");
                        option1 = inputOption();
                        switch (option1) {
                            case 1: {
                                productList.printFood();
                                ;
                            }
                                break;
                            case 2: {
                                productList.printAppliance();
                            }
                                break;
                            case 3: {
                                productList.printPottery();
                            }
                                break;
                            case 4: {
                                productList.printProduct();
                            }
                                break;
                            case 0:
                                break;
                            default:
                                break;
                        }
                    } while (option1 != 0);
                }
                    break;
                case 5: {
                    int option1;
                    do {
                        System.out.println("1. Tìm kiếm bằng mã hàng hóa");
                        System.out.println("2. Tìm kiếm bằng khoảng giá");
                        System.out.println("0. Về Menu chính");
                        option1 = inputOption();
                        switch (option1) {
                            case 1: {
                                productList.searchByID();
                            }
                                break;
                            case 2: {
                                productList.searchByPrice();
                            }
                                break;
                            case 0:
                                break;
                            default:
                                break;
                        }
                    } while (option1 != 0);
                }
                    break;
                case 6: {
                    int option1;
                    do {
                        System.out.println("1. Thống kê thường niên");
                        System.out.println("2. Thống kê theo đánh giá");
                        System.out.println("0. Về Menu chính");
                        option1 = inputOption();
                        switch (option1) {
                            case 1: {
                                int option2;
                                do {
                                    System.out.println("1. Thống kê thường niên hàng thực phẩm");
                                    System.out.println("2. Thống kê thường niên hàng sành sứ");
                                    System.out.println("0. Hủy");
                                    option2 = inputOption();
                                    switch (option2) {
                                        case 1: {
                                            int option3;
                                            do {
                                                System.out.println("1. Thống kê theo năm sản xuất");
                                                System.out.println("2. Thống kê theo năm hết hạn");
                                                System.out.println("0. Hủy");
                                                option3 = inputOption();
                                                switch (option3) {
                                                    case 1: {
                                                        productList.yearlyStatisticsFoodMFG();
                                                    }
                                                        break;
                                                    case 2: {
                                                        productList.yearlyStatisticsFoodEXP();
                                                    }
                                                        break;
                                                    case 0:
                                                        break;
                                                }
                                            } while (option3 != 0);
                                        }
                                            break;
                                        case 2: {
                                            productList.yearlyStatisticsPottery();
                                        }
                                            break;
                                        case 0:
                                            break;
                                    }
                                } while (option2 != 0);
                            }
                                break;
                            case 2: {
                                productList.gradingStatistics();
                            }
                                break;
                            case 0:
                                break;
                            default:
                                break;
                        }
                    } while (option1 != 0);
                }
                    break;
                case 7: {
                    productList.report();
                }
                    break;
                case 8: {
                    int option1;
                    do {
                        System.out.println("1. Sắp xếp theo tên");
                        System.out.println("2. Sắp xếp theo mã hàng");
                        System.out.println("3. Sắp xếp theo đơn giá");
                        System.out.println("4. Sắp xếp theo VAT");
                        System.out.println("0. Thoát");
                        option1 = inputOption();
                        int option2 = -1;
                        if(option1>=1&&option1<=4) {
                        	do {
                            System.out.println("1. Tăng dần");
                            System.out.println("2. Giảm dần");
                            System.out.println("0. Hủy");
                            option2 = inputOption();
                        	} while (option2 < 0 || option2 > 2);
                        switch (option1) {
                            case 1: {
                                switch (option2) {
                                    case 1: {
                                        productList.sortByNameAccending();
                                    }
                                        break;
                                    case 2: {
                                        productList.sortByNameDecending();
                                    }
                                        break;
                                }
                            }
                                break;
                            case 2: {
                                switch (option2) {
                                    case 1: {
                                        productList.sortByIDAccending();
                                    }
                                        break;
                                    case 2: {
                                        productList.sortByIDDecending();
                                    }
                                        break;
                                }
                            }
                                break;
                            case 3: {
                                switch (option2) {
                                    case 1: {
                                        productList.sortByPriceAccending();
                                    }
                                        break;
                                    case 2: {
                                        productList.sortByPriceDecending();
                                    }
                                        break;
                                }
                            }
                                break;
                            case 4: {
                                switch (option2) {
                                    case 1: {
                                        productList.sortByVATAccending();
                                    }
                                        break;
                                    case 2: {
                                        productList.sortByVATDecending();
                                    }
                                        break;
                                }
                            }
                                break;
                            case 0:
                                break;
                        }
                        System.out.println("Danh sách hàng hóa sau khi đã sắp xếp:");
                        productList.printProduct();
                        ProductList.scanner.nextLine();
                        } else if(option1!=0) System.out.println("Không hợp lệ!");
                    } while (option1 != 0);
                }
            }
        } while (Option != 0);
    }
}
