
import dao.PriceInfoDaoImpl;
import entities.PriceInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceInfoDaoTest {

    @Test
    public void addPrice(){
        PriceInfo testPrice = new PriceInfo (50, 1013, 500, "Prepayment", "LTD" );
        PriceInfoDaoImpl.getInstance().addPrice(testPrice);
    }

    @Test
    public void getByConsignment(){
        PriceInfo testPrice = new PriceInfo (50, 1013, 500, "Prepayment", "LTD" );
        final PriceInfo byCons = PriceInfoDaoImpl.getInstance().getByConsignment(1013);
        assertTrue(byCons.equals(testPrice));
    }

    @Test
    public void changePrice(){
        PriceInfoDaoImpl.getInstance().changePrice(1013, 700);
    }
}
