package cinema.Service;

import cinema.DTO.Request.DeleteFoodRequest;
import cinema.DTO.Request.NewFoodRequest;
import cinema.DTO.Request.RemakeFoodRequest;
import cinema.DTO.Response.MessageResponse;

public interface IFoodSevices {
    public MessageResponse newFood(NewFoodRequest request);
    public MessageResponse remakeFood(RemakeFoodRequest request);
    public MessageResponse deleteFood(DeleteFoodRequest request);
}
