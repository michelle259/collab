package app.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmpApiController {
	
	private final EmpRepository empRepository;
	@GetMapping("/api/emp-test")
	public String empTest() {
		return "emp api controller";
	}
	
	
	@PostMapping("/api/emp")
	public Emp registerEmp(@RequestBody Emp emp) {
		
		return empRepository.save(emp);	
	}
}
