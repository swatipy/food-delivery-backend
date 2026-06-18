package com.example.food_delivery;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin("*")
public class RestaurantController {
	
	private final RestaurantService restaurantService;
	
	public  RestaurantController(RestaurantService restaurantService) {
		
		this.restaurantService = restaurantService;
		
	}
	@PostMapping()
	public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
		return restaurantService.addRestaurant(restaurant);
		
	}
	@GetMapping()
    public List<Restaurant> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}
	@GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
		return restaurantService.getRestaurantById(id);
		
	}
	
	@PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id,@RequestBody Restaurant restaurant) {
		return restaurantService.updateRestaurant(id,restaurant);
		
	}
	@DeleteMapping("/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
		restaurantService.deleteRestaurant(id);
		return "Restaurant deleted";
		
	}

	

}
