package cinema.DTO.Request.Food;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NewFoodRequest {
    private Double price;
    private String description;
    private String image;
    private String nameOfFood;
    private Boolean isActive;
}
