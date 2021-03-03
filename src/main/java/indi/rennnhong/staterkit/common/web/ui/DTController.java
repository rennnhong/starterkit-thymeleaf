package indi.rennnhong.staterkit.common.web.ui;

import indi.rennnhong.staterkit.common.model.entity.Student;
import indi.rennnhong.staterkit.common.repository.StudentDataTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

@RestController
@RequestMapping("{path}/dt/")
public class DTController {

    @Autowired
    private StudentDataTableRepository studentDataTableRepository;

    /*
    todo 修改package name注入方式
     */
    @Value("indi.rennnhong.staterkit.common.web.ui")
    private String basePackage;


    @PostMapping("/search")
    public DataTablesOutput<Student> doQueryAll(@PathVariable("path") String path, @Valid @RequestBody DataTablesInput input) {
        String controllerName = MessageFormat.format("{0}Controller", StringUtils.capitalize(path));
        String specificationClassName =
                MessageFormat.format("{0}.{1}${2}", basePackage, controllerName, "DTSpecification");
        System.out.println(specificationClassName);

        try {
            Class clazz = Class.forName(specificationClassName);
            Constructor<Specification> specificationConstructor = ReflectionUtils.accessibleConstructor(clazz, DataTablesInput.class);
            Specification specification = specificationConstructor.newInstance(input);
            DataTablesOutput<Student> result = studentDataTableRepository.findAll(input, specification);
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
