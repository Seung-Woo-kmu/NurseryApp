package com.example.repository;

import com.example.domain.Comment;
import com.example.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long>  {

}
