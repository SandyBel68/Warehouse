package dao;

import entities.Consignment;
import entities.Goods;

import java.util.List;

public interface GoodsDao {
    void addGoods (Goods good);

    Goods getByName (String goodsName);

    Goods getById (int goodsId);

    List<Goods> getAll ();
    
    
}
