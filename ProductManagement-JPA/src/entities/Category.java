package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class Category {

	// properties
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int id;
	
	@Column(name="Name")
	public String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	public List<Product> products;

	// contructors
	public Category() {
	}

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
}