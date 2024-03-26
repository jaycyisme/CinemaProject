package cinema.Service;

import cinema.DTO.Request.Food.DeleteFoodRequest;
import cinema.DTO.Request.Food.NewFoodRequest;
import cinema.DTO.Request.Food.RemakeFoodRequest;
import cinema.DTO.Response.MessageResponse;

public interface IFoodSevices {
    public MessageResponse newFood(NewFoodRequest request);
    public MessageResponse remakeFood(RemakeFoodRequest request);
    public MessageResponse deleteFood(DeleteFoodRequest request);
}
