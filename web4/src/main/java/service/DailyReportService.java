package service;

import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private SessionFactory sessionFactory;

    private DailyReportService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService(DBHelper.getSessionFactory());
        }
        return dailyReportService;
    }

    public void addDailyReport() {
        new DailyReportDao(sessionFactory.openSession()).addDailyReport(new DailyReport());
    }

    public void updateDailyReport(Long earnings, Long soldCars) {
        new DailyReportDao(sessionFactory.openSession()).updateDailyReport(earnings, soldCars, getLastReport().getId());
    }

    public List<DailyReport> getAllDailyReports() {
        return new DailyReportDao(sessionFactory.openSession()).getAllDailyReport();
    }


    public DailyReport getLastReport() {
        return new DailyReportDao(sessionFactory.openSession()).getLastReport();
    }

    public boolean isEmpty() {
        return getAllDailyReports().isEmpty();
    }

    public void deleteAll() {
        new DailyReportDao(sessionFactory.openSession()).deleteAll();
    }

}
