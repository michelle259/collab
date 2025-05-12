package app.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpRequestDto {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private LocalDate hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
}