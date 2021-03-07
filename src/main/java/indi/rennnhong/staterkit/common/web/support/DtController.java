package indi.rennnhong.staterkit.common.web.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 統一處理DataTables的server side查詢
 */
@RestController
@RequestMapping("{path}/search")
public class DtController {

    @Autowired
    DtRepositoryDispatcher dispatcher;

    /*
    todo 修改package name注入方式，之後會考慮改成使用自定義annotation加在spectication上
     */
    @Value("indi.rennnhong.staterkit.module.student.web.ui")
    private String basePackage;


    @PostMapping("/{specification}")
    public DataTablesOutput doQueryAll(
            @PathVariable("path") String path,
            @PathVariable("specification") String specificationName,
            @Valid @RequestBody DataTablesInput input) {
        return dispatcher.query(path, specificationName, input);
    }
}
