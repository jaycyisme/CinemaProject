package cinema.Service;

import cinema.DTO.Request.User.DeleteUserRequest;
import cinema.DTO.Request.User.GetUserRequest;
import cinema.DTO.Request.User.CreateUserRequest;
import cinema.DTO.Request.User.RemakeUserInformationRequest;
import cinema.DTO.Response.GetAllUserResponse;
import cinema.DTO.Response.GetUserResponse;
import cinema.DTO.Response.MessageResponse;

import java.util.List;

public interface IAdminServices {
    public List<GetAllUserResponse> getAllUser(Integer pageNumber, Integer pageSize);
    public GetUserResponse getUserInformation(GetUserRequest request);
    public MessageResponse addUser(CreateUserRequest request);
    public MessageResponse remakeUser(RemakeUserInformationRequest request);
    public MessageResponse deleteUser(DeleteUserRequest request);
}
