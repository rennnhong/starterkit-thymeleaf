package indi.rennnhong.staterkit.module.student.service.impl;

import indi.rennnhong.staterkit.common.service.CrudService;
import indi.rennnhong.staterkit.module.student.model.entity.Student;
import indi.rennnhong.staterkit.module.student.repository.StudentRepository;
import indi.rennnhong.staterkit.module.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository repository;

    @Autowired
    public void setRepository(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long create(Student entity) {
        Student student = repository.save(entity);
        return student.getId();
    }

    @Override
    public List<Long> createBatch(Collection<Student> entities) {
        List<Student> students = repository.saveAll(entities);
        return convertToId(students);
    }

    @Override
    public void delete(Student entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteBatch(Collection<Student> entities) {
        repository.deleteInBatch(entities);
    }

    @Override
    public void deleteBatchById(Collection<Long> longs) {
        List<Student> allById = repository.findAllById(longs);
        repository.deleteInBatch(allById);
    }


    @Override
    public Long update(Student entity) {
        Student student = repository.save(entity);
        return student.getId();
    }

    @Override
    public List<Long> update(Collection<Student> entities) {
        List<Student> students = repository.saveAll(entities);
        return convertToId(students);
    }

    @Override
    public Student findOne(Specification<Student> condition) {
        return repository.findOne(condition).get();
    }

    @Override
    public List<Student> findAll(Specification<Student> condition) {
        return repository.findAll(condition);
    }

    @Override
    public Page<Student> findAll(Specification<Student> condition, Pageable pageable) {
        return repository.findAll(condition, pageable);
    }

    @Override
    public List<Student> findAll(Specification<Student> condition, Sort sort) {
        return repository.findAll(condition, sort);
    }

    @Override
    public long count(Specification<Student> condition) {
        return repository.count(condition);
    }

    private List<Long> convertToId(List<Student> students) {
        return students.stream().map(student -> student.getId()).collect(Collectors.toList());
    }

    @Override
    public Student findOneById(Long id) {
        return repository.findById(id).get();
    }
}
