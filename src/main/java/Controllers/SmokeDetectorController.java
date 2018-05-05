package Controllers;


import Model.I2C_Smoke_Detector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmokeDetectorController {


    @Autowired
    private I2C_Smoke_Detector smoke_detector;


    @GetMapping(value = "/smokedetectorvalue")
    public String getSmokeDetectorValue(){
        return String.valueOf(smoke_detector.getAnalogValue0());
    }
}
