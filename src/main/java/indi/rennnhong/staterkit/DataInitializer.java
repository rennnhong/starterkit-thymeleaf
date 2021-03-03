package indi.rennnhong.staterkit;

import com.github.javafaker.Faker;
import indi.rennnhong.staterkit.common.model.entity.Student;
import indi.rennnhong.staterkit.common.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 產生假資料
 */
@Component
public class DataInitializer {

    @Autowired
    StudentRepository studentRepository;

    @PostConstruct
    public void init() {
        Faker faker = new Faker();
        for (long i = 1; i <= 1000; i++) {
            Student student = new Student();
//            student.setId(i);
            student.setName(faker.name().name());
            student.setAge(faker.number().numberBetween(10,50));
            studentRepository.save(student);
        }


    }

}
