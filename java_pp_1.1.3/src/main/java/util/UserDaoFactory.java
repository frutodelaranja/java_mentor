package util;

import dao.UserDao;
import dao.UserHibernateDao;
import dao.UserJdbcDao;

import java.io.*;

public class UserDaoFactory {
    DaoType type;

    public UserDaoFactory() {
        this.type = getProperty();
    }

    public UserDao getDao(){
        UserDao dao = null;
        switch (type){
            case JDBC:
                dao = new UserJdbcDao();
                break;
            case HIBERNATE:
                dao = new UserHibernateDao();
                break;
        }
        return dao;

    }
    private DaoType getProperty(){
        String result = "";
        File file = new File("/home/evgeny/dev/java_mentor/java_pp_1.1.3/src/main/resources/daotype.properties");
        try (BufferedReader reader = new BufferedReader(new FileReader(file));){
            result = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result.contains("jdbc")){
            return DaoType.JDBC;
        }else if (result.contains("hibernate")){
            return DaoType.HIBERNATE;
        }
        return null;
    }
}

