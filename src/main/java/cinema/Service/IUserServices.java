package cinema.Service;

import cinema.DTO.Request.ChangePasswordRequest;
import cinema.DTO.Response.ListMovieReponse;
import cinema.DTO.Response.MessageResponse;

import java.util.List;

public interface IUserServices {
    public MessageResponse changePassword(ChangePasswordRequest request);
}
