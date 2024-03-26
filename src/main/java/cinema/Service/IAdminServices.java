package cinema.Service;

import cinema.DTO.Request.GetUserRequest;
import cinema.DTO.Response.GetAllUserResponse;
import cinema.DTO.Response.GetUserResponse;

import java.util.List;

public interface IAdminServices {
    public List<GetAllUserResponse> getAllUser(Integer pageNumber, Integer pageSize);
    public GetUserResponse getUserInformation(GetUserRequest request);

}
