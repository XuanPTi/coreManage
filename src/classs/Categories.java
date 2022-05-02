package classs;

import data.Data;

import interfaces.IGenerics;

import java.io.Serializable;
import java.util.Scanner;

public class Categories implements IGenerics<Categories>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	int catalogId;
	String catalogName;
	String descriptions;
	boolean catalogStatus;
	int parentId;


	public Categories() {
		super();
	}

	public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus, int parentId) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.descriptions = descriptions;
		this.catalogStatus = catalogStatus;
		this.parentId = parentId;
	}


	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) throws Exception {
		if(catalogId < 0) {
			throw new Exception("Catalog Id must a integer");
		}
		for (Categories ctg : Data.lstCtg) {
			if(ctg.getCatalogId() == catalogId) {
				throw new Exception("Catagory Id alreadly exists!");
			}
		}
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) throws Exception {
		if(catalogName.length() <4 || catalogName.length() > 10) {
			throw new Exception("Catagory Name must have 6 to 30 character");
		}
		this.catalogName = catalogName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) throws Exception{
		if(descriptions.replaceAll(" ", "").length() != descriptions.length()) {
			throw new Exception("Descriptions not null");
		}
		this.descriptions = descriptions;
	}

	public boolean isCatalogStatus() {
		return catalogStatus;
	}

	public void setCatalogStatus(boolean catalogStatus) throws Exception {
		if(catalogStatus == true || catalogStatus == false) {
			throw new Exception("Catagory status only two values true or false");
		}
		this.catalogStatus = catalogStatus;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) throws Exception {
		if(parentId == 0) {
			this.parentId = 0;
			return;
		}
		for (Categories ctgr : Data.lstCtg) {
			if(ctgr.getCatalogId() == parentId) {
				this.parentId = parentId;
				return;
			}
		}
		this.parentId = parentId;
	}

	@Override
	public void ingputData(Categories t) {
		// TODO Auto-generated method stub
		boolean check = true;
		do {
			try {
				System.out.print("Enter Id: ");
				this.setCatalogId(new Scanner(System.in).nextInt());
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
				this.setCatalogName(new Scanner(System.in).nextLine());
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
				this.setDescriptions(new Scanner(System.in).nextLine());
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
				String key = new Scanner(System.in).nextLine();
				if(key.equals("TRUE") || key.equals("true") || key.equals("false") || key.equals("FALSE")) {
					this.catalogStatus = Boolean.parseBoolean(key);
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
				System.out.print("Enter Parend: ");
				this.setParentId(Integer.parseInt(new Scanner(System.in).nextLine()));
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				check = false;
			}
		} while (!check);
	}

	@Override
	public void dislapyData(Categories t) {
		// TODO Auto-generated method stub
//		System.out.println("+-------------------------------------------------------------+");
//		System.out.println("| ID |   Name   |  Description  | ParenId |       Status      |");
//		System.out.println("+-------------------------------------------------------------+");
//		System.out.println("| 1 | tap hoa | nhap khau | 0 | không hoat động");
		System.out.printf("| %2d | %8s | %-13s | %-7d | %-18s| \n",this.catalogId,this.catalogName,this.descriptions,this.parentId,(this.catalogStatus ? "Hoạt động" : "Không hoạt động"));
//		System.out.println("+-------------------------------------------------------------+");


	}

	@Override
	public void calProfit(Categories t) {
		// TODO Auto-generated method stub

	}
}
