package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Consignment {
    int idConsignment;
    int idGoods;
    int quantity;
    Date productionDate;
    String status;

}
