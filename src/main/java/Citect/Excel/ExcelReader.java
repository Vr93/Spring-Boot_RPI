package Citect.Excel;

import Citect.Handlers.GuardObjectHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 *
 * @author Vegard Rogne
 */
public class ExcelReader {
    
    private GuardObjectHandler objectHandler;
    
    public ExcelReader(){
        this.objectHandler = new GuardObjectHandler();
    }

    public void readExcel(File file) {
        try {
            /* Create workbook from the excel file. */
            Workbook workbook = WorkbookFactory.create(file);
            /* Iterate all sheets looking for "objektregister". */
            for (Sheet sheet : workbook) {
                /* Cast sheetname to lower case to avoid error parsing this if characters are uppercase. */
                String sheetNameLowerCase = sheet.getSheetName().toLowerCase();
                /* if "objektregister" is found, parse this sheet. */
                if (sheetNameLowerCase.contains("objektregister")) {
                    /* Iterate all rows in "objektregister" sheet. */
                    for (Row row : sheet) {
                        getObjectregisters(row,objectHandler);
                    }
                }
                /* Parse objectdef excel sheets. */
                getObjectDefinitions(sheet);
                
            }

            /* close the excel document after reading it. */
            workbook.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getObjectregisters(Row row, GuardObjectHandler objectHandler) throws Exception {
        /* Create dataformater to help read excel document.*/
        DataFormatter formatter = new DataFormatter();
        /* Check if the row has a object type (M_DIR_STR, AI_INST etc...), if length is greater than zero, proceed to create tag object.
                          Or does not contain "Objekttype PLS" in object type. This is the header for the objektregister sheet. */
        String tagType = formatter.formatCellValue(row.getCell(2));
        if ((tagType.length() > 0) && (!tagType.contains("Objekttype PLS"))) {
            /* Create string array to store the cell values. */
            String[] object = new String[11];
            /* Add tag name to string array. */
            String tagName = formatter.formatCellValue(row.getCell(0));
            object[0] = tagName;
            /* Add tag description to string array. */
            String tagDescription = formatter.formatCellValue(row.getCell(1));
            object[1] = tagDescription;
            /* Add object type to string array. */
            String objectType = formatter.formatCellValue(row.getCell(2));
            object[2] = objectType;
            /* Add IO Device to string array. */
            String IODevice = formatter.formatCellValue(row.getCell(3));
            object[3] = IODevice;
            /* Add raw zero to string array. */
            String rawZero = formatter.formatCellValue(row.getCell(6));
            object[6] = rawZero;
            /* Add full zero to string array. */
            String fullZero = formatter.formatCellValue(row.getCell(7));
            object[7] = fullZero;
            /* Add raw eng to string array. */
            String rawEng = formatter.formatCellValue(row.getCell(8));
            object[8] = rawEng;
            /* Add full eng to string array. */
            String fullEng = formatter.formatCellValue(row.getCell(9));
            object[9] = fullEng;
            /* Add units to string array. */
            String units = formatter.formatCellValue(row.getCell(10));
            object[10] = units;
            /* Send the string array to GuardObjectHandler. */
            objectHandler.addTag(object);
        }

    }
    
    private void getObjectDefinitions(Sheet sheet){
        String sheetName = sheet.getSheetName().toLowerCase();
        if(sheetName.contains("objektdef")){
            String objectName = sheetName.substring(10, sheetName.length());
             ExcelObjectDef objectDef = new ExcelObjectDef(objectName);
             DataFormatter formatter = new DataFormatter();
             boolean variableDBFTagIteration = false;
             boolean advalmDBFTagIteration = false;
             boolean trendDBFTagIteration = false;
            for (Row row : sheet) {
                 String tagType = formatter.formatCellValue(row.getCell(0)).toLowerCase();
                   if(tagType.contains("variable")){
                       variableDBFTagIteration = true;
                       advalmDBFTagIteration = false;
                       trendDBFTagIteration = false;
                   }    
                   else if(tagType.contains("advalm")){
                       variableDBFTagIteration = false;
                       advalmDBFTagIteration = true;
                       trendDBFTagIteration = false;
                   } 
                   else if(tagType.contains("trend")){
                       variableDBFTagIteration = false;
                       advalmDBFTagIteration = false;
                       trendDBFTagIteration = true;
                   } 
                   /* Variable DBF section, first check if the row is not empty and is not the header for variable dbf. */
                   if((variableDBFTagIteration)&&(!tagType.equalsIgnoreCase(""))&&(!tagType.contains("variable"))){
                       String tagData[] = new String[14];
                       /* Tag name. */
                       tagData[0] = formatter.formatCellValue(row.getCell(1));
                       /* PLS addr */
                       tagData[1] = formatter.formatCellValue(row.getCell(2));
                       /* Data type. */
                       tagData[2] = formatter.formatCellValue(row.getCell(3));
                       /* IODevice. */
                       tagData[3] = formatter.formatCellValue(row.getCell(4));
                       /* Cluster. */
                       tagData[4] = formatter.formatCellValue(row.getCell(5));
                       /* Object comment. */
                       tagData[5] = formatter.formatCellValue(row.getCell(6));
                       /* If customScaling is set to true, it will use cell 6 - 10 to generate raw min, raw full etc in variable dbf. 
                       Else it will use these values as defined in objektregister. */
                       tagData[6] = formatter.formatCellValue(row.getCell(7));
                       /* Custom raw zero. */
                       tagData[7] = formatter.formatCellValue(row.getCell(8));
                       /* Custom raw full. */
                       tagData[8] = formatter.formatCellValue(row.getCell(9));
                       /* Custom eng zero. */
                       tagData[9] = formatter.formatCellValue(row.getCell(10));
                       /* Custom eng full. */
                       tagData[10] = formatter.formatCellValue(row.getCell(11));
                       /* Custom units. */
                       tagData[11] = formatter.formatCellValue(row.getCell(12));
                       /* Custom format. */
                       tagData[12] = formatter.formatCellValue(row.getCell(13));
                       /* After reading the variable dbf row, send this to objectDef. */ 
                       objectDef.addVariableDBFTag(tagData);
                   }
                   /* Advalm DBF section, first check if the row is not empty and is not the header for variable dbf. */
                   else if((advalmDBFTagIteration)&&(!tagType.equalsIgnoreCase(""))&&(!tagType.contains("advalm"))){
                       String tagData[] = new String[6];
                       /* Tag name. */
                       tagData[0] = formatter.formatCellValue(row.getCell(1));
                       /* EXPR */
                       tagData[1] = formatter.formatCellValue(row.getCell(2));
                       /* Category. */
                       tagData[2] = formatter.formatCellValue(row.getCell(3));
                       /* Custom 2. */
                       tagData[3] = formatter.formatCellValue(row.getCell(4));
                       /* Cluster. */
                       tagData[4] = formatter.formatCellValue(row.getCell(5));
                       /* Custom 3. */
                       tagData[5] = formatter.formatCellValue(row.getCell(6));
                       /* After reading the advalm dbf row, send this to objectDef. */ 
                       objectDef.addAdvalmDBFTag(tagData);
                   }
                    /* Trend DBF section, first check if the row is not empty and is not the header for variable dbf. */
                   else if((trendDBFTagIteration)&&(!tagType.equalsIgnoreCase(""))&&(!tagType.contains("trend"))){
                       String tagData[] = new String[8];
                       /* Tag name. */
                       tagData[0] = formatter.formatCellValue(row.getCell(1));
                       /* EXPR */
                       tagData[1] = formatter.formatCellValue(row.getCell(2));
                       /* SAMPLEPER. */
                       tagData[2] = formatter.formatCellValue(row.getCell(3));
                       /* Filename. */
                       tagData[3] = formatter.formatCellValue(row.getCell(4));
                       /* Cluster. */
                       tagData[4] = formatter.formatCellValue(row.getCell(5));
                       /* Files. */
                       tagData[5] = formatter.formatCellValue(row.getCell(6));
                       /* Time. */
                       tagData[6] = formatter.formatCellValue(row.getCell(7));
                       /* Period. */
                       tagData[7] = formatter.formatCellValue(row.getCell(8));
                        /* After reading the trend dbf row, send this to objectDef. */ 
                       objectDef.addTrendDBFTag(tagData);
                   }
            }
            /* After iterating the objectdef sheet, and collected all variable,trend,advalm etc.. 
            send this to objectHandler to be stored. */
            
            getObjectHandler().addObject(objectDef);
        }
    }

    public GuardObjectHandler getObjectHandler() {
        return objectHandler;
    }
    
    
    
    
}
