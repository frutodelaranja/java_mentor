package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBHelperHibernate {
    private static SessionFactory sessionFactory;
    private static String url = "jdbc:mysql://localhost:3306/java_pp";
    private static String user = "root";
    private static String password = "root";

    private static Configuration getConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", user);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }
    public static SessionFactory getSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
}
