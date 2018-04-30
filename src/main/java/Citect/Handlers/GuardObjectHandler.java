package Citect.Handlers;

import Citect.DBF.AdvalmDBF;
import Citect.DBF.TrendDBF;
import Citect.DBF.VariableDBF;
import Citect.Excel.ExcelObjectDef;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GuardObjectHandler {
    /* Array list of string array, all citect tags from excel file. */
    private ArrayList<String[]> guardCitectTags;
    /* Array list of ExcelObjectDef objects, all citect objects from excel file. */
    private ArrayList<ExcelObjectDef> guardCitectObjects;

    public GuardObjectHandler() {
        /* Array list of string array, all objects from excel file. */
        this.guardCitectTags = new ArrayList<String[]>();
        /* Array list of ExcelObject def objects, all citect objects from excel file. */
        this.guardCitectObjects = new ArrayList<ExcelObjectDef>();
    }
    
    
    
    public void addObject(ExcelObjectDef object) {
        /* Add single object from excel file to the arraylist. */
        getGuardObjects().add(object);
    }

    public ArrayList<ExcelObjectDef> getGuardObjects() {
        return guardCitectObjects;
    }

    public void removeAllGuardCitectObjects(){
        /* Removes all tags from the arraylist. */
        ArrayList<ExcelObjectDef> guardObject = getGuardObjects();
        guardObject.clear();
    }
    
    

    public void addTag(String[] object) {
        /* Add single tag from excel file to the arraylist. */
        getGuardTags().add(object);
    }

    public ArrayList<String[]> getGuardTags() {
        return guardCitectTags;
    }

    public void removeAllGuardCitectTags(){
        /* Removes all objects from the arraylist. */
        ArrayList<String[]> guardObject = getGuardTags();
        guardObject.clear();
    }


    public void generateAllCitectTags(File variable,File advalm,File trend) {
                    /* Create file (by giving fields) for Variable.DBF. */
                    VariableDBF.createFields(variable);
                    /* Create file (by giving fields) for Trend.DBF. */
                    TrendDBF.createFields(trend);
                    /* Create file (by giving fields) for Advalm.DBF. */
                    AdvalmDBF.createFields(advalm);
                    /* Generate all tags for Citects, the whole object list is passed on to object tag generator. */
                    for (String[] object : getGuardTags()) {
                        /* Generate Variable DBF tags for single object. */
                        ObjectTagGenerator.generateVariableDBF(this,object, variable);
                        /* Generate Advalm DBF tags for single object. */
                        ObjectTagGenerator.generateAdvalmDBF(this,object, advalm);
                        /* Generate Trend DBF tags for single object. */
                        ObjectTagGenerator.generateTrendDBF(this,object, trend);
                    }
    }


}
