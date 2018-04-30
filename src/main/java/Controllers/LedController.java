package Controllers;

import GPIO.GPIO_Handler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LedController {

    private GPIO_Handler gpio_handler;

    @Autowired
    public LedController(GPIO_Handler gpio_handler)
    {
        this.gpio_handler = gpio_handler;
    }

    @GetMapping(path = "/testJson", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> testJson()
    {

            JSONObject entity = new JSONObject();
            entity.put("aa", "bb");


        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/LEDON", method = RequestMethod.POST)
    public void LEDON()
    {
       gpio_handler.setPinHIGH();
        System.out.println("Pin is HIGH");
    }

    @RequestMapping(value = "/LEDOFF", method = RequestMethod.POST)
    public void LEDOFF()
    {
        gpio_handler.setPinLow();
        System.out.println("Pin is LOW");
    }
}
