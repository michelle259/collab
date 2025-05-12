package app.api.controller;

import app.entity.Dept;
import app.entity.Emp;
import app.entity.EmpRequestDto;
import app.repository.DeptRepository;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class EmpAPIController {

    private final EmpRepository empRepository;
    private final DeptRepository deptRepository;

    @GetMapping("/api/emps")
    public Object getAllEmployees() {
        List<Emp> employees = empRepository.findAll();
        if (employees.isEmpty()) {
            return Map.of("msg", "사원정보가 존재하지 않습니다");
        }
        return employees;
    }

    @GetMapping("/api/{pid}")
    public Object getEmployee(@PathVariable Integer pid) {
        Optional<Emp> employee = empRepository.findById(pid);
        if (employee.isEmpty()) {
            return Map.of("msg", "사원정보가 존재하지 않습니다");
        }
        return employee;


    }

    @PutMapping("/api/emp/{empno}")
    public ResponseEntity<Object> editEmp(@PathVariable Integer empno, @RequestBody EmpRequestDto newempdto) {
        if (empRepository.existsById(empno)) {
            Emp emp = empRepository.findById(empno).get();

            emp.setEname(newempdto.getEname());
            emp.setJob(newempdto.getJob());
            emp.setMgr(newempdto.getMgr());
            emp.setHiredate(newempdto.getHiredate());
            emp.setSal(newempdto.getSal());
            emp.setComm(newempdto.getComm());

            Dept dept = deptRepository.findById(newempdto.getDeptno()).orElse(null);
            emp.setDept(dept);

            empRepository.save(emp);

            return ResponseEntity.ok(emp);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("msg", "사원정보가 존재하지 않습니다"));
        }
    }
}