package Controllers;


import Model.PCF8591;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PCF8591_Controller {


    @Autowired
    private PCF8591 pcf8591;


    @GetMapping(value = "/pcf8591_values")
    public String getPCF8591_values(){
        int values[] = pcf8591.getAnalogValues();
        JSONObject obj = new JSONObject();
        obj.put("analog value 0:",values[0]);
        obj.put("analog value 1:",values[1]);
        obj.put("analog value 2:",values[2]);
        obj.put("analog value 3:",values[3]);
        return obj.toString();
    }


}
