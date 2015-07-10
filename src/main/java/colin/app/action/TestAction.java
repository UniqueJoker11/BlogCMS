package colin.app.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2015/7/8.
 */
@Controller
public class TestAction {
    @RequestMapping(value = "/ember.action",method = RequestMethod.GET)
    public Object getListContent(){
     List<String> resultList=new ArrayList<String>();
        resultList.add("Cras justo odio");
        resultList.add("Dapibus ac facilisis in");
        resultList.add("Morbi leo risus");
        resultList.add("Porta ac consectetur ac");
        resultList.add("Vestibulum at eros");
        return resultList;
    }
}
