package Citect.Excel;

import java.util.ArrayList;

/**
 *
 * @author Vegard Rogne
 */
public class ExcelObjectDef {
    /* arraylist of string array, contains tag objects for variable dbf. */    
    private ArrayList<String[]> variableDBF;
    /* arraylist of string array, contains tag objects for advalm dbf. */   
    private ArrayList<String[]> advalmDBF;
    /* arraylist of string array, contains tag objects for trend dbf. */   
    private ArrayList<String[]> trendDBF;
    private String objectName;
    
    public ExcelObjectDef(String objectName){
        /* The name of the object def, AI_INST etc.. */
        this.objectName = objectName;
        /* Initialize the arraylist, contains tag objects for advalm dbf. */    
        this.advalmDBF = new ArrayList<String[]>();
        /* Initialize the arraylist, contains tag objects for variable dbf. */     
        this.variableDBF = new ArrayList<String[]>();
        /* Initialize the arraylist, contains tag objects for trend dbf. */   
        this.trendDBF =  new ArrayList<String[]>();
    }
    
    
    /**
     * Adds the data to generate a tag to variable DBF from Excel object register. 
     * @param tagData, string array.
     */
    public void addVariableDBFTag(String[] tagData){
        getVariableDBF().add(tagData);
    }
    /**
     * Adds the data to generate a tag to advalm DBF from Excel object register. 
     * @param tagData, string array.
     */
    public void addAdvalmDBFTag(String[] tagData){
        getAdvalmDBF().add(tagData);
    }
    /**
     * Adds the data to generate a tag to trend DBF from Excel object register. 
     * @param tagData, string array.
     */
    public void addTrendDBFTag(String[] tagData){
        getTrendDBF().add(tagData);
    }
    
    /**
     * Returns the arraylist used to generate variable.dbf tags that matches the object name. 
     * @return ArrayList<String[]>.
     */
    public ArrayList<String[]> getVariableDBF() {
        return variableDBF;
    }
    
    /**
     * Returns the arraylist used to generate advalm.dbf tags that matches the object name. 
     * @return ArrayList<String[]>.
     */
    public ArrayList<String[]> getAdvalmDBF() {
        return advalmDBF;
    }
    /**
     * Returns the arraylist used to generate trend.dbf tags that matches the object name. 
     * @return ArrayList<String[]>.
     */
    public ArrayList<String[]> getTrendDBF() {
        return trendDBF;
    }
    /**
     * Returns the name of the object def,
     * casted it to uppercase to prevent type errors in excel document. 
     * @return String, name of the object def.
     */
    public String getObjectName() {
        return objectName.toUpperCase();
    }
    
}
