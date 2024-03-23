package cinema.Service;

import cinema.DTO.Request.GetUserRequest;
import cinema.DTO.Request.NewMovieRequest;
import cinema.DTO.Response.GetAllUserResponse;
import cinema.DTO.Response.GetUserResponse;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.User;

import java.util.List;

public interface IAdminServices {
    public List<GetAllUserResponse> getAllUser(Integer pageNumber, Integer pageSize);
    public GetUserResponse getUserInformation(GetUserRequest request);

}
