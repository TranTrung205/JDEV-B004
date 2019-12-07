import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import daos.CategoryDAO;
import daos.ProductDAO;
import entities.Category;
import entities.Product;

public class MainClass {

	public static void main(String[] args) {
		// Hibernate: disable logging
		Logger log = Logger.getLogger("org.hibernate");
		log.setLevel(Level.OFF);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("----------------------");
			System.out.println("0. Exit");
			System.out.println("1. Get all categories");
			System.out.println("2. Get details a category");
			System.out.println("3. Search category");
			System.out.println("4. Add new a category");
			System.out.println("5. Update a category");
			System.out.println("6. Delete a category");
			System.out.println("7. Get all products");
			System.out.println("8. Get details a product");
			System.out.println("9. Search product");
			System.out.println("10. Add new a product");
			System.out.println("11. Update a product");
			System.out.println("12. Delete a product");
			System.out.print("Enter cmd: ");
			int cmd = scanner.nextInt();
			if (cmd == 0) {
				break;
			} else if (cmd == 1) {
				List<Category> cats = CategoryDAO.getAll();
				for (Category cat : cats) {
					System.out.println(cat.id + " | " + cat.name);
					List<Product> prods = cat.products;
					for (Product prod : prods) {
						System.out.println("\t" + prod.id + " | " + prod.name + " | " + prod.price);
					}
				}
			} else if (cmd == 2) {
				System.out.print("Enter id: ");
				int id = scanner.nextInt();
				Category cat = CategoryDAO.getDetails(id);
				if (cat != null) {
					System.out.println(cat.id + " | " + cat.name);
					List<Product> prods = cat.products;
					for (Product prod : prods) {
						System.out.println("\t" + prod.id + " | " + prod.name + " | " + prod.price);
					}
				}
			} else if (cmd == 3) {
				scanner.nextLine();
				System.out.print("Enter keyword: ");
				String keyword = scanner.nextLine();
				List<Category> cats = CategoryDAO.search(keyword);
				for (Category cat : cats) {
					System.out.println(cat.id + " | " + cat.name);
					List<Product> prods = cat.products;
					for (Product prod : prods) {
						System.out.println("\t" + prod.id + " | " + prod.name + " | " + prod.price);
					}
				}
			} else if (cmd == 4) {
				scanner.nextLine();
				System.out.print("Enter name: ");
				String name = scanner.nextLine();
				Category newCat = new Category(0, name);
				int newID = CategoryDAO.insert(newCat);
				if (newID > 0) {
					System.out.println("INSERT OK! with newID = " + newID);
				} else {
					System.out.println("INSERT KO!");
				}				
			} else if (cmd == 5) {
				System.out.print("Enter id: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter name: ");
				String name = scanner.nextLine();
				Category newCat = new Category(id, name);
				boolean result = CategoryDAO.update(newCat);
				if (result) {
					System.out.println("UPDATE OK!");
				} else {
					System.out.println("UPDATE KO!");
				}
			} else if (cmd == 6) {
				System.out.print("Enter id: ");
				int id = scanner.nextInt();
				boolean result = CategoryDAO.delete(id);
				if (result) {
					System.out.println("DELETE OK!");
				} else {
					System.out.println("DELETE KO!");
				}
			} else if (cmd == 7) {
				List<Product> prods = ProductDAO.getAll();
				for (Product prod : prods) {
					System.out.println(prod.id + " | " + prod.name + " | " + prod.price + " | " + prod.category.name);
				}
			} else if (cmd == 8) {
				System.out.print("Enter id: ");
				int id = scanner.nextInt();
				Product prod = ProductDAO.getDetails(id);
				if (prod != null) {
					System.out.println(prod.id + " | " + prod.name + " | " + prod.price + " | " + prod.category.name);
				}
			} else if (cmd == 9) {
				System.out.print("Enter threshold: ");
				int threshold = scanner.nextInt();
				List<Product> prods = ProductDAO.search(threshold);
				for (Product prod : prods) {
					System.out.println(prod.id + " | " + prod.name + " | " + prod.price + " | " + prod.category.name);
				}
			} else if (cmd == 10) {
				scanner.nextLine();
				System.out.print("Enter name: ");
				String name = scanner.nextLine();
				System.out.print("Enter price: ");
				int price = scanner.nextInt();
				System.out.print("Enter catID: ");
				int catID = scanner.nextInt();
				Product newProd = new Product(0, name, price, catID);
				int newID = ProductDAO.insert(newProd);
				if (newID > 0) {
					System.out.println("INSERT OK! with newID = " + newID);
				} else {
					System.out.println("INSERT KO!");
				}
			} else if (cmd == 11) {
				System.out.print("Enter id: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter name: ");
				String name = scanner.nextLine();
				System.out.print("Enter price: ");
				int price = scanner.nextInt();
				System.out.print("Enter catID: ");
				int catID = scanner.nextInt();
				Product newProd = new Product(id, name, price, catID);
				boolean result = ProductDAO.update(newProd);
				if (result) {
					System.out.println("UPDATE OK!");
				} else {
					System.out.println("UPDATE KO!");
				}
			} else if (cmd == 12) {
				System.out.print("Enter id: ");
				int id = scanner.nextInt();
				boolean result = ProductDAO.delete(id);
				if (result) {
					System.out.println("DELETE OK!");
				} else {
					System.out.println("DELETE KO!");
				}
			}
		}
		scanner.close();
		System.out.println("END PROGRAM!");
	}

}