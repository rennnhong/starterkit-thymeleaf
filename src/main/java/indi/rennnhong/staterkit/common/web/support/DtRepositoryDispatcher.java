package indi.rennnhong.staterkit.common.web.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * DataTableRepository 委派類
 * 根據傳遞進來的Specification的泛型型別自動委派給該型別的Repository
 */
@Component
public class DtRepositoryDispatcher {

    @Autowired
    private Map<String, DataTablesRepository> dataTableRepositories;

    public static String SUFFIX = "Repository";


    @Autowired
    private DtSpecificationFactory dtSpecificationFactory;

    public DataTablesOutput query(String handlerName, String search, DataTablesInput input) {
        DtSpecification specification = dtSpecificationFactory.getSpecification(handlerName, search, input);
        Type[] genericInterfaces = specification.getClass().getGenericInterfaces();
        Type paramGenericType = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments()[0];
        Class paramGenericType1 = (Class) paramGenericType;
        String entityName = paramGenericType1.getSimpleName();
        DataTablesRepository dataTablesRepository = dataTableRepositories.get(entityName.toLowerCase() + SUFFIX);

        DataTablesOutput dtOutput = dataTablesRepository.findAll(input, specification);

        //回調方法，讓使用者可以使用自定義dto
        List list = specification.setDataResults(dtOutput.getData());

        if(!ObjectUtils.isEmpty(list)) dtOutput.setData(list);

        return dtOutput;
    }


}
