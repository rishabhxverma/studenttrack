package ca.sfu.cmpt276.as2.studenttrack.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {
    List<Student> findByName(String name);
    Student findById(int sid);
}