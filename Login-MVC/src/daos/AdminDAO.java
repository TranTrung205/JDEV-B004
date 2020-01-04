package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Admin;
import utils.JdbcUtil;
import utils.JpaUtil;

public class AdminDAO {

	public static boolean checkAdmin(String username, String password) throws Exception {
		// JDBC
		Connection con = JdbcUtil.getConnection();
		String sql = "SELECT * FROM admin WHERE Username=? AND PASSWORD=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, username);
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkAdmin2(String username, String password) {
		// Hibernate JPA
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		String hql = "FROM Admin AS a WHERE a.username=:u AND a.password=:p";
		TypedQuery<Admin> query = em.createQuery(hql, Admin.class);
		query.setParameter("u", username);
		query.setParameter("p", password);
		List<Admin> result = query.getResultList();
		if (result.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}