package cinema.Service.ServiceImpl;

import cinema.DTO.Request.Food.DeleteFoodRequest;
import cinema.DTO.Request.Food.NewFoodRequest;
import cinema.DTO.Request.Food.RemakeFoodRequest;
import cinema.DTO.Response.MessageResponse;
import cinema.Entity.Bill;
import cinema.Entity.BillFood;
import cinema.Entity.BillTicket;
import cinema.Entity.Food;
import cinema.Repository.BillFoodRepo;
import cinema.Repository.BillRepo;
import cinema.Repository.BillTicketRepo;
import cinema.Repository.FoodRepo;
import cinema.Service.IFoodSevices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodServicesImpl implements IFoodSevices {
    private final FoodRepo foodRepo;

    private final BillFoodRepo billFoodRepo;

    private final BillRepo billRepo;

    private final BillTicketRepo billTicketRepo;

    @Override
    public MessageResponse newFood(NewFoodRequest request) {
        Food food = new Food();
        food.setPrice(request.getPrice());
        food.setDescription(request.getDescription());
        food.setImage(request.getImage());
        food.setNameOfFood(request.getNameOfFood());
        food.setActive(request.getIsActive());

        foodRepo.save(food);
        return MessageResponse.builder().message("Add New Food Success").build();
    }

    @Override
    public MessageResponse remakeFood(RemakeFoodRequest request) {
        Optional<Food>foodOptional = foodRepo.findById(request.getFoodId());
        if (foodOptional.isEmpty()) {
            return MessageResponse.builder().message("Food not found").build();
        }

        Food food = foodOptional.get();
        food.setPrice(request.getPrice());
        food.setDescription(request.getDescription());
        food.setImage(request.getImage());
        food.setNameOfFood(request.getNameOfFood());
        food.setActive(request.getIsActive());

        foodRepo.save(food);
        return MessageResponse.builder().message("Remake Food Success").build();
    }

    @Override
    public MessageResponse deleteFood(DeleteFoodRequest request) {
        Optional<Food>foodOptional = foodRepo.findById(request.getFoodId());
        if (foodOptional.isEmpty()) {
            return MessageResponse.builder().message("Food not found").build();
        }

        Food food = foodOptional.get();
        for (BillFood billFood : billFoodRepo.findAll()) {
            if (billFood.getFood().equals(food)) {
                for (Bill bill : billRepo.findAll()) {
                    if (bill.getBillFoods().equals(billFood)) {
                        for (BillTicket billTicket : billTicketRepo.findAll()) {
                            if (billTicket.getBill().equals(bill)) {
                                billTicketRepo.delete(billTicket);
                            }
                        }
                        billRepo.delete(bill);
                    }
                }
                billFoodRepo.delete(billFood);
            }
        }
        foodRepo.delete(food);
        return MessageResponse.builder().message("Delete Food Success").build();
    }
}
