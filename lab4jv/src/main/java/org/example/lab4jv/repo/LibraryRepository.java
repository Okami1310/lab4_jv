package org.example.lab4jv.repo;

import org.example.lab4jv.data.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

}
