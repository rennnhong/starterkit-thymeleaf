package indi.rennnhong.staterkit.module.student.repository;

import indi.rennnhong.staterkit.module.student.model.entity.Student;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>, DataTablesRepository<Student,Long>{
}
