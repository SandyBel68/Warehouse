import dao.ConsignmentDaoImpl;
import entities.Consignment;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ConsignmentDaoTest {

    @Test
    public void addConsignment() {
        Date date = Date.valueOf("2019-03-08");
        Consignment testCon = new Consignment(1013,106, 500, date, "In stock");
        ConsignmentDaoImpl.getInstance().addConsignment(testCon);
    }

    @Test
   public void changeStatus() {
        ConsignmentDaoImpl.getInstance().changeStatus(1000, "Shipped");
   }

   @Test
   public void getById() {
       Date date = Date.valueOf("2019-03-08");
       Consignment testCon = new Consignment(1011,106,500, date, "In stock");
       final Consignment byId = ConsignmentDaoImpl.getInstance().getById(1011);
       assertTrue(byId.equals(testCon));
 }

}
