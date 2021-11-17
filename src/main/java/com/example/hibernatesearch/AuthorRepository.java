package com.example.hibernatesearch;

import org.springframework.data.jpa.repository.JpaRepository;
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
