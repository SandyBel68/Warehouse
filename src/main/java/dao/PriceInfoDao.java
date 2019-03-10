package dao;

import entities.Goods;
import entities.PriceInfo;

import java.util.List;

public interface PriceInfoDao {
    void addPrice (PriceInfo info);

    PriceInfo getByConsignment (int consignmentId);
    
    void changePrice (int consignmentId, int price);

    List<PriceInfo> getAll ();
    
}
