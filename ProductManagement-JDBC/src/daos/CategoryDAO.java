package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Category;
import utils.JdbcUtil;

public class CategoryDAO {

	public static List<Category> getAll() throws Exception {
		List<Category> cats = new ArrayList<Category>();
		Connection con = JdbcUtil.getConnection();
		Statement st = con.createStatement(); // create statement
		ResultSet rs = st.executeQuery("SELECT * FROM Category"); // execute statement
		while (rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("Name");
			Category cat = new Category(id, name);
			cats.add(cat);
		}
		con.close();
		return cats;
	}

	public static Category getDetails(int id) throws Exception {
		Category cat = null;
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM Category WHERE ID=?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			String name = rs.getString("Name");
			cat = new Category(id, name);
		}
		con.close();
		return cat;
	}

	public static List<Category> search(String keyword) throws Exception {
		List<Category> cats = new ArrayList<Category>();
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM Category WHERE Name LIKE ?");
		pst.setString(1, "%" + keyword + "%");
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("Name");
			Category cat = new Category(id, name);
			cats.add(cat);
		}
		con.close();
		return cats;
	}

	public static boolean insert(Category newCat) throws Exception {
		boolean result = false;
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("INSERT INTO Category(Name) VALUES(?)");
		pst.setString(1, newCat.name);
		int n = pst.executeUpdate();
		if (n > 0) {
			result = true;
		}
		con.close();
		return result;
	}

	public static int insert2newID(Category newCat) throws Exception {
		int result = 0;
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("INSERT INTO Category(Name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, newCat.name);
		int n = pst.executeUpdate();
		if (n > 0) {
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int newID = rs.getInt(1);
				result = newID;
			}
		}
		con.close();
		return result;
	}

	public static boolean update(Category newCat) throws Exception {
		boolean result = false;
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("UPDATE Category SET Name=? WHERE ID=?");
		pst.setString(1, newCat.name);
		pst.setInt(2, newCat.id);
		int n = pst.executeUpdate();
		if (n > 0) {
			result = true;
		}
		con.close();
		return result;
	}

	public static boolean delete(int id) throws Exception {
		boolean result = false;
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pst = con.prepareStatement("DELETE FROM Category WHERE ID=?");
		pst.setInt(1, id);
		int n = pst.executeUpdate();
		if (n > 0) {
			result = true;
		}
		con.close();
		return result;
	}

}