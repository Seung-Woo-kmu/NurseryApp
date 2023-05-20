package com.example.dto;

import com.example.domain.nursery.Nursery;
import lombok.Data;

@Data
public class NurseryDto {
    private Long id;
    private String nurseryName;
    private String cityDistrict;
    private String address;
    private int teacherCount;
    private int studentCount;

    public NurseryDto(Nursery nursery) {
        this.id = nursery.getId();
        this.nurseryName = nursery.getNurseryName();
        this.cityDistrict = nursery.getCityDistrict().getName();
        this.address = nursery.getAddress();
        this.teacherCount = nursery.getTeacherCount();
        this.studentCount = nursery.getStudentCount();
    }
}
