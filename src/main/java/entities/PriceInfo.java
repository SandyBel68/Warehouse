package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class PriceInfo {
    int id;
    int idConsignment;
    int consignmentPrice;
    String payCondition;
    String consumer;
}
