package classs;

import data.Data;
import interfaces.IGenerics;

import java.util.Scanner;

public class Product implements IGenerics<Product> {
	String productId;
	String productName;
	String title;
	float importPrice;
	float exportPrice;
	float profit;
	String descriptions;
	boolean productStatus;
	Categories catolog;

	public Product() {
		super();
	}

	public Product(String productId, String productName, String title, float importPrice, float exportPrice,
				   float profit, String descriptions, boolean productStatus, Categories catolog) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.title = title;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.profit = profit;
		this.descriptions = descriptions;
		this.productStatus = productStatus;
		this.catolog = catolog;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) throws Exception{
		if(!productId.toUpperCase().startsWith("C")) {
			throw new Exception("The first character must start with the letter C");
		}
		if(productId.length() <4 || productId.length() > 4) {
			throw new Exception("Product id only have 4 character");
		}
		for (Product prt : Data.lstPrd) {
			if(prt.getProductId().equals(productId)) {
				throw new Exception("Product Id alrealy exists");
			}
		}
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) throws Exception {
		if(productName.length() <6 || productName.length() >20) {
			throw new Exception("Product name must have 6 to 20 character");
		}
		this.productName = productName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws Exception{
		if(title.length() <6 || title.length() >20) {
			throw new Exception("Title must have 6 to 20 character");
		}
		this.title = title;
	}

	public float getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(float importPrice) throws Exception {
		if(importPrice < 0) {
			throw new Exception("Import price is float and more than 0");
		}
		this.importPrice = importPrice;
	}

	public float getExportPrice() {
		return exportPrice;
	}

	public void setExportPrice(float exportPrice) throws Exception {
		if(exportPrice < (importPrice +(exportPrice*MIN_INTEREST_RATE))) {
			throw new Exception("Export price must more than"+(importPrice +(exportPrice*MIN_INTEREST_RATE)));
		}
		this.exportPrice = exportPrice;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) throws Exception {
		if(descriptions.replaceAll(" ", "").length() != descriptions.length()) {
			throw new Exception("Description Not null");
		}
		this.descriptions = descriptions;
	}

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public Categories getCatolog() {
		return catolog;
	}

	public void setCatolog(Categories catolog)  {

		this.catolog = catolog;
	}

	@Override
	public void ingputData(Product t) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		do {
			try {
				System.out.print("Enter ID: ");
				this.setProductId(sc.nextLine());
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				check = false;
			}
		} while (!check);
		do {
			try {
				System.out.print("Enter Name: ");
				this.setProductName(sc.nextLine());
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				check = false;
			}
		} while (!check);
		do {
			try {
				System.out.print("Enter Title: ");
				this.setTitle(sc.nextLine());
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				check = false;
			}
		} while (!check);
		do {
			try {
				System.out.print("Import Price: ");
				this.setImportPrice	(Float.parseFloat(sc.nextLine()));
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				check = false;
			}
		} while (!check);
		do {
			try {
				System.out.print("Export Price: ");
				this.setExportPrice(Float.parseFloat(sc.nextLine()));
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				check = false;
			}
		} while (!check);
		do {
			try {
				System.out.print("Enter Description: ");
				this.setDescriptions(sc.nextLine());
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				check = false;
			}
		} while (!check);
		do {
			try {
				System.out.print("Enter Status: ");
				String key = sc.nextLine();
				if(key.equals("TRUE") || key.equals("true") || key.equals("FALSE") || key.equals("false")) {
					this.productStatus = Boolean.parseBoolean(key);
				}
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				check = false;
			}
		} while (!check);
		do {
			try {
				System.out.print("Enter Catalog: ");
				int key = Integer.parseInt(sc.nextLine());
				Categories c = null;
				for (Categories ctgct : Data.lstCtg) {
					if(ctgct.getCatalogId() == key) {
						c = ctgct;
					}
					this.catolog = c;
				}

				break;
			} catch (NullPointerException e) {
				// TODO: handle exception

				System.out.println(e.getMessage());
				check = false;
			}
		} while (check);
	}

	@Override
	public  void dislapyData(Product t) {
//
//		System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
//		System.out.println("| Mã SP |    Tên SP    |     Title      |      Mô Tả      |   Danh Mục SP   |     Stauts     |  Giá Nhập  |  Giá Bán  |  Lợi Nhuận |");
		System.out.printf("| %5s |  %10s  |  %13s |  %-14s | %-15s | %14s | %10.1f | %9.1f | %10.1f |",this.productId, this.productName, this.title,this.descriptions,this.catolog.getCatalogName(),(this.isProductStatus() ? "Hoạt Động" : "Không hoạt động"), this.importPrice, this.exportPrice, this.profit);
//		System.out.println("+-------+--------------+----------------+-----------------+-----------------+----------------+------------+-----------+------------+");
//        System.out.printf("| Mã SP: %s \t \t | Tên SP: %s \t \tS| Title: %s \n", this.productId, this.productName, this.title);
//        System.out.println("| Mô Tả: " + this.descriptions + "\t | Danh Mục SP: " + this.catolog.getCatalogName() + "\t| Trạng Thái SP: " + (this.isProductStatus() ? "Hoạt Động" : "Không hoạt động"));
//        System.out.printf("| Giá Nhập: %8.1f \t | Giá Bán: %8.1f \t \t| Lợi Nhuận:  %4.1f \n ", this.importPrice, this.exportPrice, this.profit);

	}

	@Override
	public void calProfit(Product t) {
		// TODO Auto-generated method stub
		this.profit = this.exportPrice - this.importPrice;

	}

}
