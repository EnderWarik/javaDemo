package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>, QuerydslPredicateExecutor<Category> {
}
