package indi.rennnhong.staterkit.module.student.web.ui;

import com.google.common.collect.Lists;
import indi.rennnhong.staterkit.common.web.Response;
import indi.rennnhong.staterkit.common.web.support.DtSpecification;
import indi.rennnhong.staterkit.module.student.model.entity.Student;
import indi.rennnhong.staterkit.module.student.service.StudentService;
import indi.rennnhong.staterkit.module.student.web.command.StudentCreateCommand;
import indi.rennnhong.staterkit.module.student.web.command.StudentUpdateCommand;
import indi.rennnhong.staterkit.util.ThymeleafPathUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
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

import static indi.rennnhong.staterkit.util.ThymeleafPathUtils.FormType.*;
import static org.springframework.util.StringUtils.hasText;

@Controller
@RequestMapping(value = {"student", "/", ""})
public class StudentController {

    @Value("${custom.ui.thymeleaf.root-path}")
    private String basePath;

    @Value("student")
    private String modulePath;


    private StudentService studentService;

    @GetMapping
    public String page(Model model) {
        String[] path = {"level1", "level2", "level3"};
        model.addAttribute("list", Lists.newArrayList(path));
        return ThymeleafPathUtils.buildPath(modulePath) + "/" + modulePath;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public String showDetailView(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findOneById(id);
        model.addAttribute("student", student);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, DETAIL);
    }

    @GetMapping("/create")
    public String showCreateView(Model model) {
        StudentCreateCommand formData = new StudentCreateCommand();
        model.addAttribute("formData", formData);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, CREATE);
    }

    @GetMapping("{id}/update")
    public String showUpdateView(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findOneById(id);
        StudentUpdateCommand formData = new StudentUpdateCommand();
        BeanUtils.copyProperties(student, formData);
        model.addAttribute("formData", formData);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, UPDATE);
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
            request.setAttribute("id", student.getId());
            return "forward:/student/create/success";
        }
        return ThymeleafPathUtils.buildFragmentPath(modulePath, CREATE);
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

        return ThymeleafPathUtils.buildFragmentPath(modulePath, UPDATE);
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

    //注意:要加public，不然反射調用setDataTablesInput時會報錯
    public static class SimpleQuery implements DtSpecification<Student> {
        private Integer id;
        private String name;
        private Integer age;

        @Override
        public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = Lists.newArrayList();
            if (Objects.nonNull(id)) predicates.add(cb.equal(root.get("id"), id));
            if (Objects.nonNull(name)) predicates.add(cb.equal(root.get("name"), name));
            if (Objects.nonNull(age)) predicates.add(cb.equal(root.get("age"), age));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }

        @Override
        public void setDataTablesInput(DataTablesInput input) {
            String idFilter = input.getColumn("id").getSearch().getValue();
            String nameFilter = input.getColumn("name").getSearch().getValue();
            String ageFilter = input.getColumn("age").getSearch().getValue();
            id = (hasText(idFilter)) ? Integer.parseInt(idFilter) : null;
            name = (hasText(nameFilter)) ? nameFilter : null;
            age = (hasText(ageFilter)) ? Integer.parseInt(ageFilter) : null;
        }
    }

}
