package app.api.controller;

import app.entity.Dept;
import app.entity.Emp;
import app.entity.EmpRequestDto;
import app.repository.DeptRepository;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RestController
public class EmpAPIController {

    private final EmpRepository empRepository;
    private final DeptRepository deptRepository;


    @GetMapping("/api/emp/test")
    public ResponseEntity test() {
        return ResponseEntity.ok(null);
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
                    .body(Collections.singletonMap("msg", "NOT FOUND w/ EMPNO"));
        }
    }
}