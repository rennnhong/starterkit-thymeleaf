package indi.rennnhong.staterkit.common.web.ui;

import com.google.common.collect.Lists;
import indi.rennnhong.staterkit.common.model.entity.Student;
import indi.rennnhong.staterkit.common.service.StudentService;
import indi.rennnhong.staterkit.common.web.command.StudentCreateCommand;
import indi.rennnhong.staterkit.common.web.command.StudentUpdateCommand;
import indi.rennnhong.staterkit.common.web.response.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequestMapping(value = {"student","/",""})
public class StudentController {
    @GetMapping
    public String page(Model model) {
        String[] path = {"level1","level2","level3"};
        model.addAttribute("list",Lists.newArrayList(path));
        return "student";
    }

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public String showDetailView(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findOneById(id);
        model.addAttribute("student", student);
        return "/student_form_detail :: detail_form";
    }

    @GetMapping("/create")
    public String showCreateView(Model model) {
        StudentCreateCommand formData = new StudentCreateCommand();
        model.addAttribute("formData", formData);
        return "/student_form_create :: create_form";
    }

    @GetMapping("{id}/update")
    public String showUpdateView(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findOneById(id);
        StudentUpdateCommand formData = new StudentUpdateCommand();
        BeanUtils.copyProperties(student, formData);
        model.addAttribute("formData", formData);
        return "/student_form_update :: update_form";
    }

    @PostMapping
    public String doCreate(
            HttpServletRequest request,
            @Valid @ModelAttribute("formData") StudentCreateCommand formData,
            BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            Student student = new Student();
            BeanUtils.copyProperties(formData, student);
            studentService.create(student);
//            model.addAttribute("student", student);
//            return "/student_form_detail :: detail_form";
            request.setAttribute("id", student.getId());
            return "forward:/student/create/success";
        }
        return "/student_form_create :: create_form";
    }

    @PutMapping("{id}")
    public String doUpdate(
            HttpServletRequest request,
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("formData") StudentUpdateCommand formData,
            BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            Student student = studentService.findOneById(id);
            BeanUtils.copyProperties(formData, student);
            studentService.update(student);
            request.setAttribute("id", student.getId());
            return "forward:/student/update/success";
        }

        return "/student_form_update :: update_form";
    }

    @PostMapping(value = "/create/success", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response createSuccess(HttpServletRequest request) {
        Long id = (Long) request.getAttribute("id");
        return Response.ok().setPayload(id);
    }

    @PutMapping(value = "/update/success", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response updateSuccess(HttpServletRequest request) {
        Long id = (Long) request.getAttribute("id");
        return Response.ok().setPayload(id);
    }

    static class DTSpecification implements Specification<Student> {
        private final Integer id;
        private final String name;
        private final Integer age;

        public DTSpecification(DataTablesInput input) {
            String idFilter = input.getColumn("id").getSearch().getValue();
            String nameFilter = input.getColumn("name").getSearch().getValue();
            String ageFilter = input.getColumn("age").getSearch().getValue();
            id = (hasText(idFilter)) ? Integer.parseInt(idFilter) : null;
            name = (hasText(nameFilter)) ? nameFilter : null;
            age = (hasText(ageFilter)) ? Integer.parseInt(ageFilter) : null;
        }

        @Override
        public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = Lists.newArrayList();
            if (Objects.nonNull(id)) predicates.add(cb.equal(root.get("id"), id));
            if (Objects.nonNull(name)) predicates.add(cb.equal(root.get("name"), name));
            if (Objects.nonNull(age)) predicates.add(cb.equal(root.get("age"), age));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }

    }

}
