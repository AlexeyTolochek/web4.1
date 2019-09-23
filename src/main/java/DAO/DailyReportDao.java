package DAO;

import model.Car;
import model.DailyReport;
import util.DayReportBuffer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> reportBuffers = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return reportBuffers;
    }

    public void addSoldCar(Car car) {
      DayReportBuffer dayReportBuffer = DayReportBuffer.getInstance();
      Long balanseEarnings = dayReportBuffer.getEarnings() + car.getPrice();
      Long balanseSoldCars = dayReportBuffer.getSoldCars() + 1;
      dayReportBuffer.setEarnings(balanseEarnings);
      dayReportBuffer.setSoldCars(balanseSoldCars);
    }

    public DailyReport getLastReport() {

        session.beginTransaction();
        Query query = session.createQuery("from DailyReport order by id desc");
        query.setMaxResults(1);
        DailyReport last = (DailyReport) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return last;
    }

    public void deleteAllReport() {
        session.beginTransaction();
        session.createQuery("DELETE from DailyReport").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void createNewDayReport() {
        DayReportBuffer dayReportBuffer = DayReportBuffer.getInstance();
        DailyReport report = new DailyReport(dayReportBuffer.getEarnings(), dayReportBuffer.getSoldCars());
        session.beginTransaction();
        session.save(report);
        session.getTransaction().commit();
        session.close();
    }
}
