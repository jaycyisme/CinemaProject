package cinema.DTO.Request.Food;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteFoodRequest {
    private Integer foodId;
}
