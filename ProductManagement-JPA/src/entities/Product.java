package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import daos.CategoryDAO;

@Entity
@Table(name="Product")
public class Product {

	// properties
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int id;
	
	@Column(name="Name")
	public String name;
	
	@Column(name="Price")
	public int price;
	
	@ManyToOne
	@JoinColumn(name="CatID")
	public Category category;
	
	// contructors
	public Product() {
	}
	
	public Product(int id, String name, int price, int catID) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = CategoryDAO.getDetails(catID);
	}
	
}