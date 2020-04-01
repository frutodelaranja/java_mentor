package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public void addDailyReport(DailyReport report) {

        Transaction transaction = session.beginTransaction();
        session.save(report);
        transaction.commit();
        session.close();
    }

    public void updateDailyReport(Long earnings, Long soldCars, Long id) {

        Transaction transaction = session.beginTransaction();
        session.createQuery("update DailyReport set earnings= :earnings, soldCars= :soldCars where id= :id")
                .setParameter("earnings", earnings)
                .setParameter("soldCars", soldCars)
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
        session.close();

    }

    public DailyReport getLastReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReport = session.createQuery("FROM DailyReport order by id DESC").list();
        transaction.commit();
        session.close();
        return dailyReport.get(0);
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }

    public void deleteAll() {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();
        transaction.commit();
        session.close();
    }

}
