package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Goods {
    int idGoods;
    String goodsName;
    String productionGroup;
    String unitOfMeasure;
    String pack;


}
