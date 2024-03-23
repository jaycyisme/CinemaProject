package cinema.DTO.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RemakeFoodRequest {
    private Integer foodId;
    private Double price;
    private String description;
    private String image;
    private String nameOfFood;
    private Boolean isActive;
}
