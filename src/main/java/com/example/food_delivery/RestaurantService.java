package com.example.food_delivery;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
	
	private final RestaurantRepository restaurantRepository;
	
	public RestaurantService(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		 return restaurantRepository.save(restaurant);
		
	}
	public  List<Restaurant> getAllRestaurants() {
		 return restaurantRepository.findAll();
		
	}
	public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

	public void deleteRestaurant(Long id) {
		// TODO Auto-generated method stub
		restaurantRepository.deleteById(id);
		
	}

	public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
		// TODO Auto-generated method stub
		
		Restaurant existing =  getRestaurantById(id);
		existing.setAddress(restaurant.getAddress());
		existing.setContactNumber(restaurant.getContactNumber());
		return restaurantRepository.save(existing);
	}


}
