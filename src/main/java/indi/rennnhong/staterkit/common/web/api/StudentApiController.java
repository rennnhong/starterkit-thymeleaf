package indi.rennnhong.staterkit.common.web.api;

import indi.rennnhong.staterkit.common.model.entity.Student;
import indi.rennnhong.staterkit.common.service.StudentService;
import indi.rennnhong.staterkit.common.web.request.StudentC;
import indi.rennnhong.staterkit.common.web.request.StudentU;
import indi.rennnhong.staterkit.common.web.response.Response;
import indi.rennnhong.staterkit.common.web.response.StudentR;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.StringUtils.hasText;

@RestController
@RequestMapping("api/student")
public class StudentApiController {

    @Autowired
    private StudentService studentService;

    /*
     |===========|
     |執行crud操作|
     |===========|
     */
//    @PostMapping("/search")
//    public DataTablesOutput<Student> doQueryAll(@Valid @RequestBody DataTablesInput input) {
////        System.out.println(MoreObjects.toStringHelper(condition));
//
//        DataTablesOutput<Student> result = studentDataTableRepository.findAll(input, new StudentSpecification(input));
//
//
//        return result;
//    }

    @GetMapping("{id}")
    public Response<StudentR> doQueryOne(@PathVariable(value = "id") Long id) {
        Student student = studentService.findOneById(id);
        StudentR.StudentRBuilder builder = StudentR.StudentRBuilder.aStudentR();
        return Response.<StudentR>ok()
                .setPayload(builder
                        .withId(student.getId())
                        .withName(student.getName())
                        .withAge(student.getAge())
                        .build());
    }

    @PostMapping
    public Response<Long> doCreate(StudentC obj) {
        Student student = new Student();
        BeanUtils.copyProperties(obj, student);
        long newId = studentService.create(student);
        return Response.<Long>ok().setPayload(newId);
    }

    @PutMapping("{id}")
    public Response<Long> doUpdate(@PathVariable("id") Long id, StudentU obj) {
        Student student = studentService.findOneById(id);
        BeanUtils.copyProperties(obj, student);
        long updateId = studentService.update(student);
        return Response.<Long>ok().setPayload(updateId);
    }

    @DeleteMapping
    public Response doDelete(@PathVariable(value = "id") Long id) {
        studentService.deleteById(id);
        return Response.<Long>ok();
    }

//    private static class StudentSpecification implements Specification<Student> {
//        private final Integer id;
//        private final String name;
//        private final Integer age;
//
//        StudentSpecification(DataTablesInput input) {
//            String idFilter = input.getColumn("id").getSearch().getValue();
//            String nameFilter = input.getColumn("name").getSearch().getValue();
//            String ageFilter = input.getColumn("age").getSearch().getValue();
//            id = (hasText(idFilter)) ? Integer.parseInt(idFilter) : null;
//            name = (hasText(nameFilter)) ? nameFilter : null;
//            age = (hasText(ageFilter)) ? Integer.parseInt(ageFilter) : null;
//        }
//
//        @Override
//        public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//            List<Predicate> predicates = Lists.newArrayList();
//            if (Objects.nonNull(id)) predicates.add(cb.equal(root.get("id"), id));
//            if (Objects.nonNull(name)) predicates.add(cb.equal(root.get("name"), name));
//            if (Objects.nonNull(age)) predicates.add(cb.equal(root.get("age"), age));
//
//            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//        }
//
//    }

}
