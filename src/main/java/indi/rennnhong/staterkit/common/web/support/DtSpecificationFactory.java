package indi.rennnhong.staterkit.common.web.support;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Dt查詢條件的工廠類
 */
@Component
public class DtSpecificationFactory {
    @Autowired
    private ListableBeanFactory listableBeanFactory;

    private Map<String, Map<String, String>> dtSpecificationMap;

    // todo 處理反射異常
    public DtSpecification getSpecification(String handlerName, String specificationName, DataTablesInput input) {
        try {
            String controllerName = MessageFormat.format("{0}Controller", StringUtils.capitalize(handlerName));
            String specificationClassName = dtSpecificationMap.get(controllerName).get(specificationName);
            Class clazz = Class.forName(specificationClassName);
            Method setDataTablesInputMethod = ReflectionUtils.findMethod(clazz, "setDataTablesInput", DataTablesInput.class);
            Constructor<DtSpecification> constructor = ReflectionUtils.accessibleConstructor(clazz);
            DtSpecification instance = constructor.newInstance();
            ReflectionUtils.invokeMethod(
                    setDataTablesInputMethod,
                    instance,
                    input
            );
            return instance;
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

    @PostConstruct
    public void init() {
        dtSpecificationMap = new HashMap<>();
        Map<String, Object> controllers = listableBeanFactory.getBeansWithAnnotation(Controller.class);
        for (Map.Entry<String, Object> entry : controllers.entrySet()) {
            Class<?> controllerClass = entry.getValue().getClass();

            String handlerName = controllerClass.getSimpleName();
            //get inner classes
            for (Class<?> controllerInnerClass : controllerClass.getDeclaredClasses()) {
                Class<?>[] interfaces = controllerInnerClass.getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    if (anInterface.equals(DtSpecification.class)) {
                        Map<String, String> dtSpecifications = dtSpecificationMap.get(handlerName);
                        if (Objects.isNull(dtSpecifications)) {
                            dtSpecifications = new HashMap<>();
                            dtSpecificationMap.put(handlerName, dtSpecifications);
                        }
                        dtSpecifications.put(
                                StringUtils.uncapitalize(controllerInnerClass.getSimpleName()),
                                controllerInnerClass.getName());
                    }
                }
            }

        }
//        for (Map.Entry<String, Map<String, String>> entry : dtSpecificationMap.entrySet()) {
//            String controllerName = entry.getKey();
//            Map<String, String> dtSpecifications = entry.getValue();
//            System.out.println(controllerName);
//            dtSpecifications.entrySet().forEach(x -> System.out.println("-" + x));
//            System.out.println("--------------------------------------------------------------");
//        }

    }

}
