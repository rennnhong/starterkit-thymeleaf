package indi.rennnhong.staterkit.common.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

@Controller
public class CommonController {

    /**
     * 處理新增結果
     *
     * @param module  模塊名
     * @param request
     * @return
     */
    @PostMapping(value = "{module}/**/result/success", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response createSuccess(@PathVariable("module") String module, HttpServletRequest request) {
        System.out.println(MessageFormat.format("{0}新增成功", module));
        System.out.println(request.getServletPath());
        Object payload = request.getAttribute("payload");
        return Response.ok().setPayload(payload);
    }

    /**
     * 處理修改結果
     *
     * @param module  模塊名
     * @param request
     * @return
     */
    @PutMapping(value = "{module}/**/result/success", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response updateSuccess(@PathVariable("module") String module, HttpServletRequest request) {
        System.out.println(MessageFormat.format("{0}修改成功", module));
        System.out.println(request.getServletPath());
        Object payload = request.getAttribute("payload");
        return Response.ok().setPayload(payload);
    }
}
