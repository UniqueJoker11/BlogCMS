package colin.app.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2015/7/10.
 */
@Controller
public class EmberExampleController{
    @RequestMapping(value = "/ember.html",method = RequestMethod.GET)
    public ModelAndView showEmberPage(HttpServletRequest request){
        ModelAndView emberModel=new ModelAndView("ember");
        List<String> resultList=new ArrayList<String>();
        resultList.add("Cras justo odio");
        resultList.add("Dapibus ac facilisis in");
        resultList.add("Morbi leo risus");
        resultList.add("Porta ac consectetur ac");
        resultList.add("Vestibulum at eros");
        emberModel.addObject("resultList",resultList);
        request.setAttribute("resultList",resultList);
        return emberModel;
    }
}
