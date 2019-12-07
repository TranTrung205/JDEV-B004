package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Product;
import utils.JpaUtil;

public class ProductDAO {
	
	public static List<Product> getAll() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Product> query = em.createQuery("FROM Product", Product.class);
		List<Product> prods = query.getResultList();
		em.close();
		return prods;
	}
	
	public static Product getDetails(int id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Product prod = em.find(Product.class, id);
		em.close();
		return prod;
	}
	
	public static List<Product> search(int threshold) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Product> query = em.createQuery("FROM Product AS p WHERE p.price < :t", Product.class);
		query.setParameter("t", threshold);
		List<Product> prods = query.getResultList();
		em.close();
		return prods;
	}
	
	public static int insert(Product newProd) {
		int result = 0;
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(newProd);
			em.getTransaction().commit();
			result = newProd.id;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static boolean update(Product newProd) {
		boolean result = false;
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Product dbProd = em.find(Product.class, newProd.id);
		if (dbProd != null) {
			try {
				em.getTransaction().begin();
				em.merge(newProd);
				em.getTransaction().commit();
				result = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static boolean delete(int id) {
		boolean result = false;
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Product dbProd = em.find(Product.class, id);
		if (dbProd != null) {
			try {
				em.getTransaction().begin();
				em.remove(dbProd);
				em.getTransaction().commit();
				result = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
}