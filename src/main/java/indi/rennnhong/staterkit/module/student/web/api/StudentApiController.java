//package indi.rennnhong.staterkit.module.student.web.api;
//
//import indi.rennnhong.staterkit.common.web.Response;
//import indi.rennnhong.staterkit.module.student.model.entity.Student;
//import indi.rennnhong.staterkit.module.student.service.StudentService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/student")
//public class StudentApiController {
//
//    @Autowired
//    private StudentService studentService;
//
//    /*
//     |===========|
//     |執行crud操作|
//     |===========|
//     */
////    @PostMapping("/search")
////    public DataTablesOutput<Student> doQueryAll(@Valid @RequestBody DataTablesInput input) {
//////        System.out.println(MoreObjects.toStringHelper(condition));
////
////        DataTablesOutput<Student> result = studentDataTableRepository.findAll(input, new StudentSpecification(input));
////
////
////        return result;
////    }
//
//    @GetMapping("{id}")
//    public Response<StudentR> doQueryOne(@PathVariable(value = "id") Long id) {
//        Student student = studentService.findOneById(id);
//        StudentR.StudentRBuilder builder = StudentR.StudentRBuilder.aStudentR();
//        return Response.<StudentR>ok()
//                .setPayload(builder
//                        .withId(student.getId())
//                        .withName(student.getName())
//                        .withAge(student.getAge())
//                        .build());
//    }
//
//    @PostMapping
//    public Response<Long> doCreate(StudentC obj) {
//        Student student = new Student();
//        BeanUtils.copyProperties(obj, student);
//        long newId = studentService.create(student);
//        return Response.<Long>ok().setPayload(newId);
//    }
//
//    @PutMapping("{id}")
//    public Response<Long> doUpdate(@PathVariable("id") Long id, StudentU obj) {
//        Student student = studentService.findOneById(id);
//        BeanUtils.copyProperties(obj, student);
//        long updateId = studentService.update(student);
//        return Response.<Long>ok().setPayload(updateId);
//    }
//
//    @DeleteMapping
//    public Response doDelete(@PathVariable(value = "id") Long id) {
//        studentService.deleteById(id);
//        return Response.<Long>ok();
//    }
//
//}
