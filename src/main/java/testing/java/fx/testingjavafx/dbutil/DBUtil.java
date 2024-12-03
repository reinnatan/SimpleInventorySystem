package testing.java.fx.testingjavafx.dbutil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {
    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sqlite-jpa-unit");
        return emf.createEntityManager();
    }
}
