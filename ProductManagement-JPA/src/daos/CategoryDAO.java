package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Category;
import utils.JpaUtil;

public class CategoryDAO {

	public static List<Category> getAll() {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Category> query = em.createQuery("FROM Category", Category.class);
		List<Category> cats = query.getResultList();
		em.close();
		return cats;
	}

	public static Category getDetails(int id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Category cat = em.find(Category.class, id);
		em.close();
		return cat;
	}

	public static List<Category> search(String keyword) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Category> query = em.createQuery("FROM Category AS c WHERE c.name LIKE :k", Category.class);
		query.setParameter("k", "%" + keyword + "%");
		List<Category> cats = query.getResultList();
		em.close();
		return cats;
	}

	public static int insert(Category newCat) {
		int result = 0;
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(newCat);
			em.getTransaction().commit();
			result = newCat.id;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static boolean update(Category newCat) {
		boolean result = false;
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		Category dbCat = em.find(Category.class, newCat.id);
		if (dbCat != null) {
			try {
				em.getTransaction().begin();
				em.merge(newCat);
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
		Category dbCat = em.find(Category.class, id);
		if (dbCat != null) {
			try {
				em.getTransaction().begin();
				em.remove(dbCat);
				em.getTransaction().commit();
				result = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}