package com.example.domain.nursery;

import com.example.domain.ExistTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nursery extends ExistTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nurseryName;

    @Column
    private CityDistrict cityDistrict;

    @Column
    private String address;

    @Column
    private int teacherCount;

    @Column
    private int studentCount;

    public Nursery(String nurseryName, CityDistrict cityDistrict, String address, int teacherCount, int studentCount) {
        this.nurseryName = nurseryName;
        this.cityDistrict = cityDistrict;
        this.address = address;
        this.teacherCount = teacherCount;
        this.studentCount = studentCount;
    }
}
