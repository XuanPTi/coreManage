package classs;

import data.Data;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Manager {
    public static Scanner sc = new Scanner(System.in);
    public static Data data = new Data();

    public static void main(String[] args) {
        Manager m = new Manager();
        m.menu(sc);
        data.getLstCtg();
        data.setLstCtg();
    }

    private void menu(Scanner sc) {
        Boolean check = true;
        do {
            System.out.println("---------------------------MANAGER--------------------------");
            System.out.println("1. Manager Catagory");
            System.out.println("2. Manager Product");
            System.out.println("3. Exit");
            System.out.print("Your Choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    menuCategory(sc);

                    break;
                case 2:
                    menuProduct(sc);
                    break;
                case 3:
                    System.exit(0);

                default:
                    System.err.println("Your choice is wrong, please again!");
                    break;
            }
        } while (check);
    }

    /* this's program menu catalog have manager and all */

    private void menuCategory(Scanner sc) {
        Boolean check = true;
        do {
            System.out.println("------------------------MANAGER CATAGORY------------------------");
            System.out.println("1. List Category");
            System.out.println("2. Add Category");
            System.out.println("3. Edit Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Sreach Category");
            System.out.println("6. Return");
            System.out.print("Your Choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    listCategory(sc);
                    break;
                case 2:
                    addCategory(sc);
                    break;
                case 3:
                    editCategory(sc);
                    break;
                case 4:
                    deleteCategory(sc);
                    break;
                case 5:
                    sreachCategory(sc);
                    break;
                case 6:
                    return;
                default:
                    System.err.println("Your choice is wrong, please again!");
                    break;
            }
        } while (check);
    }

    private void addCategory(Scanner sc) {
        System.out.println("-------------------Add new diretory------------------");
        System.out.print("Enter number catalog: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            Categories ctC = new Categories();
            ctC.ingputData(ctC);
            Data.lstCtg.add(ctC);
            data.setLstCtg();
            System.out.println("Input Successfully..!");
        }
    }

    private void editCategory(Scanner sc) {
        // by id
        System.out.print("Enter catalog edit by id: ");
        int key = Integer.parseInt(sc.nextLine());
        for (Categories ctgSr : Data.lstCtg) {
            if (ctgSr.getCatalogId() == key) {
                try {
                    System.out.print("Enter Name Edit: ");
                    ctgSr.setCatalogName(sc.nextLine());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    System.out.print("Enter Description Edit: ");
                    ctgSr.setDescriptions(sc.nextLine());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    System.out.print("Enter Id Edit: ");
                    ctgSr.setParentId(Integer.parseInt(sc.nextLine()));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println("Update Catalog have id " + key + " Successufully");
        }
    }

    private void listCategory(Scanner sc) {
        Boolean check = true;
        do {
            System.out.println("------------------------LIST CATEGORY------------------------");
            System.out.println("1. Directory tree list ");
            System.out.println("2. Information catalog detail");
            System.out.println("3. All List catalog");
            System.out.println("4. Return");
            System.out.print("Your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    treeCategory();
                    break;
                case 2:
                    detailCategory(sc);
                    break;
                case 3:
                    allListCategory(sc);

                    break;
                case 4:
                    return;
                default:
                    System.err.println("Please Again... !");
                    break;
            }
        } while (check);
    }

    private void treeCategory() {

        List<Categories> lv1 = Data.lstCtg.stream().filter(ctg -> ctg.getParentId() == 0).collect(Collectors.toList());
        for (int i = 1; i <= lv1.size(); i++) {
            Categories ctv1 = lv1.get(i - 1);
            System.out.println(i + ": " + ctv1.getCatalogName());

            List<Categories> lv2 = Data.lstCtg.stream().filter(ctg1 -> ctg1.getParentId() == ctv1.getCatalogId())
                    .collect(Collectors.toList());
            for (int j = 1; j <= lv2.size(); j++) {
                Categories ctv2 = lv2.get(j - 1);
                System.out.println("\t" + i + "." + j + ": " + ctv2.getCatalogName());

                List<Categories> lv3 = Data.lstCtg.stream().filter(ctg2 -> ctg2.getParentId() == ctv2.getCatalogId())
                        .collect(Collectors.toList());
                for (int k = 1; k <= lv3.size(); k++) {
                    Categories ctv3 = lv3.get(k - 1);
                    System.out.println("\t \t" + i + "." + j + "." + k + ": " + ctv3.getCatalogName());

                }
            }
        }
    }

    public static void detailCategory(Scanner sc) {
        // information catalog detail by name category
        System.out.print("Enter key sreach by name: ");
        String key = sc.nextLine();
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("| ID |   Name   |  Description  | ParenId |       Status      |");
        System.out.println("+-------------------------------------------------------------+");
        for (Categories ctg : Data.lstCtg) {
            if (ctg.getCatalogName().contains(key)) {
                ctg.dislapyData(ctg);
            }
        }
        System.out.println("+-------------------------------------------------------------+");
    }

    private void allListCategory(Scanner sc) {
        // Programing by collection
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("| ID |   Name   |  Description  | ParenId |       Status      |");
        System.out.println("+-------------------------------------------------------------+");
        Data.lstCtg.forEach(ctg -> ctg.dislapyData(ctg));
        System.out.println("+-------------------------------------------------------------+");
    }

    private void deleteCategory(Scanner sc) {
        // delete by id
        System.out.print("Enter key sreach delete: ");
        int key = Integer.parseInt(sc.nextLine());
        for (Categories ctgDel : Data.lstCtg) {
            List<Categories> list1 = Data.lstCtg;
            if (ctgDel.getCatalogId() == key) {
                list1.remove(key);
            }
        }
        System.out.println("Delete Successfully !");
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("| ID |   Name   |  Description  | ParenId |       Status      |");
        System.out.println("+-------------------------------------------------------------+");
        Data.lstCtg.forEach(ctg -> ctg.dislapyData(ctg));
        System.out.println("+-------------------------------------------------------------+");
    }

    private void sreachCategory(Scanner sc) {
        // sreach by name catagory

        System.out.print("Enter key sreach by name: ");
        String key = sc.nextLine();

        System.out.println("+-------------------------------------------------------------+");
        System.out.println("| ID |   Name   |  Description  | ParenId |       Status      |");
        System.out.println("+-------------------------------------------------------------+");
        for (Categories ctg : Data.lstCtg) {
            if (ctg.getCatalogName().contains(key)) {
               ctg.dislapyData(ctg);
            }
        }
        System.out.println("+-------------------------------------------------------------+");
    }

    /*--------------------------------Product-------------------------------*/

    private void menuProduct(Scanner sc) {
        Boolean check = true;
        do {
            System.out.println("---------------------------MANAGER PRODUCT--------------------------");
            System.out.println("1. Add product new");
            System.out.println("2. Calculate product profit");
            System.out.println("3. Display information product");
            System.out.println("4. Sort product");
            System.out.println("5. Update information product");
            System.out.println("6. Update status product");
            System.out.println("7. Return");
            System.out.print("Your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    calculateProduct(sc);
                    break;
                case 3:
                    displayProduct(sc);
                    break;
                case 4:
                    sortProduct(sc);
                    break;
                case 5:
                    updateInfor(sc);
                    break;
                case 6:
                    updateStatus(sc);
                    break;
                case 7:
                    return;
                default:
                    System.err.println("Please Again...!");
                    break;
            }
        } while (check);
    }

    private void addProduct(Scanner sc) {

        System.out.print("Enter Number Product: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            Product prd = new Product();
            prd.ingputData(prd);
            Data.lstPrd.add(prd);
            System.out.println("Add new Successfully...!");
        }
    }

    private void calculateProduct(Scanner sc) {
        for (Product prd : Data.lstPrd) {
            prd.calProfit(prd);
        }
        System.out.println("Calculator profit product Successfully..!");
    }

    private void displayProduct(Scanner sc) {
        Boolean check = true;
        System.out.println("---------------------------INFORMATION PRODUCT--------------------------");
        System.out.println("1. Display product by directory");
        System.out.println("2. Display product detail");
        System.out.println("3. All product");
        System.out.println("4. Return");
        System.out.print("Your choice: ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                displayProductbyCatalog(sc);
                break;
            case 2:
                displayProductbyName(sc);
                break;
            case 3:
                allProduct(sc);
                break;
            case 4:
                return;
            default:
                System.err.println("Please Again....!");
                break;
        }
    }

    private void displayProductbyCatalog(Scanner sc) {
        // display product by directory of catalogies
        System.out.print("Enter Catalog sreach: ");
        String key = sc.nextLine();

        System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
        System.out.println("| Mã SP |    Tên SP    |     Title      |      Mô Tả      |   Danh Mục SP   |     Stauts     |  Giá Nhập  |  Giá Bán  |  Lợi Nhuận |");
        System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
        for (Product prd : Data.lstPrd) {
            if (prd.getCatolog().getCatalogName().contains(key)) {
                prd.dislapyData(prd);
            }
        }
        System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
    }

    private void displayProductbyName(Scanner sc) {
        // display information product by sreach name
        System.out.print("Enter key product sreach name: ");
        String key = sc.nextLine();
        System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
        System.out.println("| Mã SP |    Tên SP    |     Title      |      Mô Tả      |   Danh Mục SP   |     Stauts     |  Giá Nhập  |  Giá Bán  |  Lợi Nhuận |");
        System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
        for (Product prd : Data.lstPrd) {
            if (prd.getProductName().contains(key)) {
                Data.lstPrd.forEach(t -> t.dislapyData(prd));
            }
        }
        System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
    }

    private void allProduct(Scanner sc) {
        System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
        System.out.println("| Mã SP |    Tên SP    |     Title      |      Mô Tả      |   Danh Mục SP   |     Stauts     |  Giá Nhập  |  Giá Bán  |  Lợi Nhuận |");
        System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
        for (Product prd : Data.lstPrd) {
            prd.dislapyData(prd);
        }
        System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
    }

    private void sortProduct(Scanner sc) {
        Boolean check = true;
        do {
            System.out.println("\"---------------------------SORT PRODUCT--------------------------");
            System.out.println("1. Sort product by export price ascending");
            System.out.println("2. Sort product by profit descending");
            System.out.println("3. Return");
            System.out.print("Your Choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
                    System.out.println("| Mã SP |    Tên SP    |     Title      |      Mô Tả      |   Danh Mục SP   |     Stauts     |  Giá Nhập  |  Giá Bán  |  Lợi Nhuận |");
                    System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
                    sortProductAscending(sc);
                    break;
                case 2:
                    System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
                    System.out.println("| Mã SP |    Tên SP    |     Title      |      Mô Tả      |   Danh Mục SP   |     Stauts     |  Giá Nhập  |  Giá Bán  |  Lợi Nhuận |");
                    System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
                    sortProductDescending(sc);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Please Again...!");
                    break;
            }
        } while (check);
    }

    private void sortProductAscending(Scanner sc) {
        // sort by export
        Data.lstPrd.sort((x, y) -> {
            return Double.compare((double)x.getExportPrice(), (double)y.getExportPrice());
        });
        for (Product prd : Data.lstPrd) {
            prd.dislapyData(prd);
        }


    }

    private void sortProductDescending(Scanner sc) {
        // sort by profit
        Data.lstPrd.sort((t, k) -> {
            return Double.compare((double)k.getExportPrice(), (double)t.getExportPrice());
        });
        Data.lstPrd.forEach(t -> t.dislapyData(t));
    }

    private void updateInfor(Scanner sc) {

        // update by id product
        System.out.println("Enter Id is update product");
        String k = sc.nextLine();
        for (Product prd : Data.lstPrd) {
            if (prd.getProductId().contains(k)) {
                try {
                    System.out.print("Edit Name Product: ");
                    prd.setProductName(sc.nextLine());

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e.getMessage());
                }
                try {
                    System.out.print("Edit Title Product: ");
                    prd.setTitle(sc.nextLine());

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e.getMessage());
                }
                try {
                    System.out.print("Edit Description Product: ");
                    prd.setDescriptions(sc.nextLine());

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e.getMessage());
                }
                try {
                    System.out.print("Edit Import price: ");
                    prd.setImportPrice(Float.parseFloat(sc.nextLine()));

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e.getMessage());
                }
                try {
                    System.out.print("Edit Export price: ");
                    prd.setImportPrice(Float.parseFloat(sc.nextLine()));

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Update Successfully..!");
    }

    private void updateStatus(Scanner sc) {
        // id product
        System.out.print("Sreach changer status by id: ");
        String key = sc.nextLine();
        for (Product ctp : Data.lstPrd) {
            if (ctp.getProductId().equals(key)) {
                ctp.setProductStatus(!ctp.isProductStatus());
                System.out.println("Update Successfully....!");
            }
        }
    }
}
