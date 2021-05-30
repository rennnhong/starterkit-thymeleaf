package indi.rennnhong.staterkit.module.student.service;

import indi.rennnhong.staterkit.common.service.CrudService;
import indi.rennnhong.staterkit.module.student.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface StudentService {

    public UUID create(Student entity);


    public List<UUID> createBatch(Collection<Student> entities);


    public void delete(Student entity);


    public void deleteById(UUID id);


    public void deleteBatch(Collection<Student> entities);


    public void deleteBatchById(Collection<UUID> longs);


    public UUID update(Student entity);


    public List<UUID> update(Collection<Student> entities);


    public Student findOne(Specification<Student> condition);


    public List<Student> findAll(Specification<Student> condition);


    public Page<Student> findAll(Specification<Student> condition, Pageable pageable);


    public List<Student> findAll(Specification<Student> condition, Sort sort);


    public long count(Specification<Student> condition);


    public Student findOneById(UUID id);

}
