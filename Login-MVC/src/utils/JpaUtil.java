package utils;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;

import entities.Admin;

public class JpaUtil {

	private static EntityManagerFactory emFactory = null;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emFactory == null) {
			Properties props = new Properties();
			props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			props.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/mydb?serverTimezone=UTC");
			props.put(Environment.USER, "root");
			props.put(Environment.PASS, "");
			props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
			
			// create entityManagerFactory
			PersistenceUnitInfo pui = createPersistenceUnitInfo();
			emFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(pui, props);
		}
		return emFactory;
	}

	private static PersistenceUnitInfo createPersistenceUnitInfo() {
		return new PersistenceUnitInfo() {

			@Override
			public String getPersistenceUnitName() {
				return "pu-zoRRiifDkZ";
			}

			@Override
			public List<String> getManagedClassNames() {
				return Arrays.asList(Admin.class.getName());
			}

			@Override
			public PersistenceUnitTransactionType getTransactionType() {
				return PersistenceUnitTransactionType.RESOURCE_LOCAL;
			}

			@Override
			public String getPersistenceProviderClassName() {
				return HibernatePersistenceProvider.class.getName();
			}

			@Override
			public ValidationMode getValidationMode() {
				return null;
			}

			@Override
			public SharedCacheMode getSharedCacheMode() {
				return null;
			}

			@Override
			public Properties getProperties() {
				return null;
			}

			@Override
			public String getPersistenceXMLSchemaVersion() {
				return null;
			}

			@Override
			public URL getPersistenceUnitRootUrl() {
				return null;
			}

			@Override
			public DataSource getNonJtaDataSource() {
				return null;
			}

			@Override
			public ClassLoader getNewTempClassLoader() {
				return null;
			}

			@Override
			public List<String> getMappingFileNames() {
				return null;
			}

			@Override
			public DataSource getJtaDataSource() {
				return null;
			}

			@Override
			public List<URL> getJarFileUrls() {
				return null;
			}

			@Override
			public ClassLoader getClassLoader() {
				return null;
			}

			@Override
			public boolean excludeUnlistedClasses() {
				return false;
			}

			@Override
			public void addTransformer(ClassTransformer arg0) {
			}
		};
	}
}