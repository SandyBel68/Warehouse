import dao.GoodsDaoImpl;
import entities.Goods;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoodsDaoTest {
    @Test
    void addGoods (){
      Goods testGood = new Goods(120, "BR-90", "Border", "pcs", "pallet" );
        GoodsDaoImpl.getInstance().addGoods(testGood);
    }

    @Test
    void getByName (){
        Goods testGood = new Goods(120, "BR-90", "Border", "pcs", "pallet" );
        final Goods byName = GoodsDaoImpl.getInstance().getByName("BR-90");
        assertTrue(byName.equals(testGood));
    }

    @Test
    void getById (){
        Goods testGood = new Goods(120, "BR-90", "Border", "pcs", "pallet" );
        final Goods byId = GoodsDaoImpl.getInstance().getById(120);
        assertTrue(byId.equals(testGood));
    }

}
