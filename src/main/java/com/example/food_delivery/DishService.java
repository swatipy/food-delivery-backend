package com.example.food_delivery;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class DishService {
	
	private final DishRepository dishRepository;

	private final RestaurantRepository restaurantRepository;
	
	public DishService(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
		this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

	public List<Dish> getDishes() {
		// TODO Auto-generated method stub
		return dishRepository.findAll();
	}
	public Dish getDishById(Long Id) {
		// TODO Auto-generated method stub
		return dishRepository.findById(Id).orElseThrow(() -> new RuntimeException("Dish not found"));
	}


	public Dish addDish(Dish dish) {

		if (dish.getRestaurant() == null || dish.getRestaurant().getId() == null) {
			throw new RuntimeException("Restaurant id is required");
		}

		Long restaurantId = dish.getRestaurant().getId();

		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RuntimeException("Restaurant not found"));

		dish.setRestaurant(restaurant);

		return dishRepository.save(dish);
	}
	public Dish updateDish(Long Id,Dish dish) {
		// TODO Auto-generated method stub
		
		Dish existing = getDishById(Id);
		existing.setCategory(dish.getCategory());
		existing.setDescription(dish.getDescription());
		existing.setPrice(dish.getPrice());
		
		
		
		return dishRepository.save(existing);
	}
	
	public void deleteDish(Long Id) {
		// TODO Auto-generated method stub
		dishRepository.deleteById(Id);
		
	}
	public List<Dish> getDishesByRestaurant(Long restaurantId) {
		return dishRepository.findByRestaurantId(restaurantId);
	}
	
	

}
