package Citect.Handlers;

import Citect.DBF.AdvalmDBF;
import Citect.DBF.TrendDBF;
import Citect.DBF.VariableDBF;
import Citect.Excel.ExcelObjectDef;

import java.io.File;

public class ObjectTagGenerator {

    public static void generateVariableDBF(GuardObjectHandler objectHandler, String[] tagObject, File file) {
        /* Help variable to determine if objectdef is found, to avoid having to iterate all objectdef for each tagObject. */
        boolean objectDefFound = false;
        for (ExcelObjectDef objectType : objectHandler.getGuardObjects()) {
            if (objectType.getObjectName().equalsIgnoreCase(tagObject[2])) {
                /* Set helping variable objectdef is found to true, break the for-loop after writing all objectdef tags. */
                objectDefFound = true;
                /* Write all variable dbf tags for the spesific object def. */
                for (String[] objectDef : objectType.getVariableDBF()) {
                    String data[] = new String[31];
                    /* Name - Variable.DBF */
                    data[0] = tagObject[0] + "_" + objectDef[0];
                    /* Type - Variable.DBF */
                    data[1] = objectDef[2];
                    /* UNIT (IODevice) - Variable.DBF */
                    data[2] = objectDef[3];
                    /* ADDR - Variable.DBF */
                    data[3] = tagObject[0] + "_" + objectDef[1];
                    /* If section for use scaling from 'objektregister' is true use values from objektregister tag. */
                    if (objectDef[6].equalsIgnoreCase("true")) {
                        /* Raw zero - Variable.DBF */
                        data[4] = tagObject[6];
                        /* Raw full - Variable.DBF */
                        data[5] = tagObject[7];
                        /* Eng zero - Variable.DBF */
                        data[6] = tagObject[8];
                        /* Eng full - Variable.DBF */
                        data[7] = tagObject[9];
                        /* Eng units - Variable.DBF */
                        data[8] = tagObject[10];
                        /* We don't have any fields for format in 'objektregister', get this from the eng full length */
                        if (tagObject[9].length() > 0) {
                            String format = "";
                            for (int i = 0; i < tagObject[9].length(); i++) {
                                format = format + "#";
                            }
                            format = format + ".#EU";
                            /* Format - Varible.DBF */
                            data[9] = format;
                        }
                    } /* If section for use scaling from 'objektregister' is not true use values from objektdef tag. */ else {
                        /* Raw zero - Variable.DBF */
                        data[4] = objectDef[7];
                        /* Raw full - Variable.DBF */
                        data[5] = objectDef[8];
                        /* Eng zero - Variable.DBF */
                        data[6] = objectDef[9];
                        /* Eng full - Variable.DBF */
                        data[7] = objectDef[10];
                        /* Eng units - Variable.DBF */
                        data[8] = objectDef[11];
                        /* Format - Varible.DBF */
                        data[9] = objectDef[12];
                    }
                    /* Comment - Variable.DBF */
                    data[10] = tagObject[1] + objectDef[5];
                    /* Cluster - Variable.DBF */
                    data[19] = objectDef[4];
                    /* Add a new row with the data to Variable.DBF */
                    VariableDBF.addRow(data, file);
                }
            }
            /* If the object def is found, and the variable dbf has been written, break the for-loop to avoid
            iterating the other objectdef. */
            if (objectDefFound) {
                break;
            }
        }
        /* If object def is not found for this tag, display this in variable dbf. */
        if (!objectDefFound) {
            String[] notSupported = new String[31];
            notSupported[0] = tagObject[0] + "  (" + tagObject[2] + ")";
            notSupported[1] = "OBJDEF not found";
            VariableDBF.addRow(notSupported, file);
        }
    }

    public static void generateAdvalmDBF(GuardObjectHandler objectHandler, String[] tagObject, File file) {
        /* Help variable to determine if objectdef is found, to avoid having to iterate all objectdef for each tagObject. */
        boolean objectDefFound = false;
        for (ExcelObjectDef objectType : objectHandler.getGuardObjects()) {
            if (objectType.getObjectName().equalsIgnoreCase(tagObject[2])) {
                /* Set helping variable objectdef is found to true, break the for-loop after writing all objectdef tags. */
                objectDefFound = true;
                /* Write all advalm dbf tags for the spesific object def. */
                for (String[] objectDef : objectType.getAdvalmDBF()) {
                    String data[] = new String[28];
                    /* TAG - Advalm.DBF */
                    data[0] = tagObject[0] + "_" + objectDef[0];
                    /* NAME - Advalm.DBF */
                    data[1] = tagObject[0];
                    /* DESC - Advalm.DBF */
                    data[2] = tagObject[1];
                    /* EXPR - Advalm.DBF */
                    data[3] = tagObject[0] + "_" + objectDef[1];
                    /* Category - Advalm.DBF */
                    data[4] = objectDef[2];
                    /* Custom 2 - Advalm.DBF */
                    data[12] = objectDef[3];
                    /* Custom 3 - Advalm.DBF */
                    data[13] = objectDef[5];
                    /* Cluster - Advalm.DBF */
                    data[19] = objectDef[4];
                    /* Add a new row with the data to Advalm.DBF */
                    AdvalmDBF.addRow(data, file);
                }
            }
            /* If the object def is found, and the advalm dbf has been written, break the for-loop to avoid
            iterating the other objectdef. */
            if (objectDefFound) {
                break;
            }
        }
        /* If object def is not found for this tag, display this in advalm dbf. */
        if (!objectDefFound) {
            String[] notSupported = new String[28];
            notSupported[0] = tagObject[0] + "  (" + tagObject[2] + ")";
            notSupported[1] = "OBJDEF not found";
            AdvalmDBF.addRow(notSupported, file);
        }
    }

    public static void generateTrendDBF(GuardObjectHandler objectHandler, String[] tagObject, File file) {
        /* Help variable to determine if objectdef is found, to avoid having to iterate all objectdef for each tagObject. */
        boolean objectDefFound = false;
        for (ExcelObjectDef objectType : objectHandler.getGuardObjects()) {
            if (objectType.getObjectName().equalsIgnoreCase(tagObject[2])) {
                /* Set helping variable objectdef is found to true, break the for-loop after writing all objectdef tags. */
                objectDefFound = true;
                /* Write all trend dbf tags for the spesific object def. */
                for (String[] objectDef : objectType.getTrendDBF()) {
                    String data[] = new String[32];
                    /* Name - Trend.DBF */
                    data[0] = tagObject[0] + "_" + objectDef[0];
                    /* EXPR - Trend.DBF */
                    data[1] = tagObject[0] + "_" + objectDef[1];
                    /* SAMPLEPER - Trend.DBF */
                    data[3] = objectDef[2];
                    /* Help variable to determine if eng units is found in variable.dbf, to avoid having to iterate all other tags */
                    boolean engUnitsFromVariableFound = false;
                    /* Get ENG_UNITS and Format from variable.dbf, if the tag is found. */
                    for (String[] engUnits : objectType.getVariableDBF()) {
                        /* If the trend tag is found in variable dbf, get eng units and format from this. */
                        if (objectDef[1].equalsIgnoreCase(engUnits[0])) {
                            /* Set helping variable found to true, break the for-loop after writing eng unit and format. */
                            engUnitsFromVariableFound = true;
                            /* if the variable tag found uses scaling from 'objektregister, proceed. */
                            if (engUnits[6].equalsIgnoreCase("true")) {
                                /* Eng units - Trend.DBF */
                                data[6] = tagObject[10];
                                /* We don't have any fields for format in 'objektregister', get this from the eng full length */
                                if (tagObject[9].length() > 0) {
                                    String format = "";
                                    for (int i = 0; i < tagObject[9].length(); i++) {
                                        format = format + "#";
                                    }
                                    format = format + ".#EU";
                                    /* Format - Trend.DBF */
                                    data[7] = format;
                                }
                            }
                            /* if the variable tag found uses scaling from 'Objektdef', proceed. */
                            else {
                                /* Get eng units for the variable.dbf tag, and use this in variable dbf. */
                                /* Eng units - Trend.DBF */
                                data[6] = engUnits[11];
                                /* Format - Trend.DBF */
                                data[7] = engUnits[12];
                            }
                        }
                        if (engUnitsFromVariableFound) {
                            break;
                        }
                    }
                    /* FILENAME - Trend.DBF */
                    data[8] = objectDef[3];
                    /* Files - Trend.DBF */
                    data[9] = objectDef[5];
                    /* Time - Trend.DBF */
                    data[10] = objectDef[6];
                    /* PERIOD - Trend.DBF */
                    data[11] = objectDef[7];
                    /* COMMENT - Trend.DBF */
                    data[12] = tagObject[1];
                    /* TYPE - Trend.DBF */
                    data[13] = "TRN_PERIODIC";
                    /* STORMETHOD - TREND.DBF */
                    data[21] = "Scaled (2-byte samples)";
                    /* Cluster - Trend.DBF */
                    data[22] = objectDef[4];
                    /* Add a new row with the data to Trend.DBF */
                    TrendDBF.addRow(data, file);
                }
            }
            /* If the object def is found, and the Trend dbf has been written, break the for-loop to avoid
            iterating the other objectdef. */
            if (objectDefFound) {
                break;
            }
        }
        /* If object def is not found for this tag, display this in Trend dbf. */
        if (!objectDefFound) {
            String[] notSupported = new String[32];
            notSupported[0] = tagObject[0] + "  (" + tagObject[2] + ")";
            notSupported[1] = "OBJDEF not found";
            TrendDBF.addRow(notSupported, file);
        }
    }

}
