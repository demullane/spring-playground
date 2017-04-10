package com.example;

import com.example.services.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/math")
public class MathController {

	@Autowired
	public MathService mathService;

	@GetMapping("/pi")
	public String getPI() { return mathService.pi(); }

	@GetMapping("/calculate")
	public String getCalculationParams(
			@RequestParam(defaultValue = "add") String operation,
			@RequestParam String x,
			@RequestParam String y) {

		return mathService.calculate(operation, x, y);
	}

	@PostMapping("/sum")
	public String getSumParams(@RequestParam MultiValueMap<String, String> query) {
		return mathService.sum(query);
	}

	@RequestMapping("/volume/{length}/{width}/{height}")
	public String getRectangleVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
		return mathService.rectangleInfo(length, width, height);
	}

	@PostMapping("/area")
	public String getArea(@RequestParam Map<String, String> body) {
		return mathService.area(body);
	}
}