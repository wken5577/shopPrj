package toy.shop.web.dto.dtorequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderRequestDto {

    private int quantity;
    private Long itemId;
    private String deliveryAddress;
    private String imp_uid;
    private Long paid_amount;

    public OrderRequestDto(int quantity, Long itemId, String deliveryAddress) {
        this.quantity = quantity;
        this.itemId = itemId;
        this.deliveryAddress = deliveryAddress;
    }
}
