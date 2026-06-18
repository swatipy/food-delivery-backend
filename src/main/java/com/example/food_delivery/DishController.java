package com.example.food_delivery;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dishes")
@CrossOrigin("*")

public class DishController {
	
	private final DishService dishService;
	
	public DishController(DishService dishService) {
		this.dishService = dishService;
	}

	@GetMapping()
	public List<Dish> getDishes() {
		return dishService.getDishes();
		
	}
	@PostMapping()
	public Dish addDish(@RequestBody Dish dish) {
		return dishService.addDish(dish);
		
	}

	@PutMapping("/{id}")
	public Dish updateDish(@PathVariable("id") long id,
						   @RequestBody Dish dish) {

		return dishService.updateDish(id, dish);
	}


	@DeleteMapping("/{id}")
	public String deleteDish(@PathVariable("id") long id) {
		dishService.deleteDish(id);
		return "Dish deleted";
	}

	@GetMapping("/restaurant/{restaurantId}")
	public List<Dish> getDishesByRestaurant(
			@PathVariable Long restaurantId) {

		return dishService.getDishesByRestaurant(restaurantId);
	}

}
