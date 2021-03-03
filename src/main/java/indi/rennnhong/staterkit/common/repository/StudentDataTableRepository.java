package indi.rennnhong.staterkit.common.repository;

import indi.rennnhong.staterkit.common.model.entity.Student;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

//@Repository
//public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student>{
//}
@Repository
public interface StudentDataTableRepository extends DataTablesRepository<Student,Long> {
}
