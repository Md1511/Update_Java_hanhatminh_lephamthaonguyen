//import com.raven.DAO.UserDAO;
//import com.raven.model.Model_User_Account;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.query.Query;
//
//import java.util.List;
//
//public class TT {
//    private D
//    public static void main(String[] args) {
//
//        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//        Session session = factory.openSession();
//
//        try {
//            session.beginTransaction();
//
//            String hql = "FROM Model_User_Account WHERE userName = :delete";
//            Query query = session.createQuery(hql);
//
//
//
//
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//}
