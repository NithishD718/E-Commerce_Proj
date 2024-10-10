package Util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

public class ExcelUtil {
    public static Workbook workbook;
    public static String currentFilePath;
    public static HashMap<Integer , String> headers = new HashMap<>();


    public ExcelUtil(String filePath) throws IOException {
        try(FileInputStream file = new FileInputStream(filePath))
        {
               currentFilePath = filePath;
               workbook = new XSSFWorkbook(file);
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    public static Sheet getSheet(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null) throw new IllegalArgumentException("Error : The sheet " + sheetName + " does not exist in the workbook");
        else return sheet;
    }

    public void persistDataToExcel(String sheetName , Function<Object,Map<String , Object>>  persistObjectFunction,Object object) {
        try {
           Map<String,Object> persistObject = persistObjectFunction.apply(object);
            Sheet sheet = getSheet(sheetName);
            int rowCount = sheet.getLastRowNum() + 1;

                Row row = sheet.createRow(rowCount++);
                int cellCount = 0;
                for (Map.Entry<String, Object> entry : persistObject.entrySet()) {
                    Object cellValue = entry.getValue();
                        Cell cell = row.createCell(cellCount++);
                        if (cellValue instanceof Boolean) cell.setCellValue((Boolean) cellValue);
                        else if (cellValue instanceof Integer) cell.setCellValue((Integer) cellValue);
                        else if (cellValue instanceof Double) cell.setCellValue((Double) cellValue);
                        else cell.setCellValue(String.valueOf(cellValue));
                }
            write();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void write() {
        try(FileOutputStream outputStream = new FileOutputStream(currentFilePath)){
            workbook.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public  HashMap<String, Object> readExcelData(String sheetName , Function<Row,HashMap<String,Object>> callback , String keyColumn , String keyColumnValue) throws IllegalArgumentException
    {
        HashMap<String,Object> fetchedResult = new HashMap<>();
        try {
            int keyColumnIndex = -1;
            Sheet sheet = getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow)               //get the index of the target column which will be used to select the target row
            {
                headers.put(cell.getColumnIndex(), cell.getStringCellValue());  //Storing headers index and value
                if (cell.getStringCellValue().equalsIgnoreCase(keyColumn)) {
                    keyColumnIndex = cell.getColumnIndex();
                }
            }
            if(keyColumnIndex >=0) {
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue;
                    Cell cell = row.getCell(keyColumnIndex);
                    if (cell != null && cell.getStringCellValue().equalsIgnoreCase(keyColumnValue)) {
                        fetchedResult = callback.apply(row);
                        return fetchedResult;
                    }
                }
            }
            else {
                System.out.println("The target column is not in the sheet");
            }
        }
        catch (Exception e)
        {
            throw e;
        }
      return fetchedResult;
    }
    public static HashMap<String,Object> fetchPersitedObjects(Row row)
    {
        HashMap<String,Object> fetchedObjects = new HashMap<>();
        try {
            for (Map.Entry<Integer, String> headerObj : ExcelUtil.headers.entrySet()) {
                int columnIndex = headerObj.getKey();
                String columnValue = headerObj.getValue();
                Cell cell = row.getCell(columnIndex);
                if (cell.getCellType() == CellType.BOOLEAN)
                    fetchedObjects.put(columnValue, cell.getBooleanCellValue());
                else if (cell.getCellType() == CellType.NUMERIC) {
                    if (DateUtil.isCellDateFormatted(cell)) fetchedObjects.put(columnValue, cell.getDateCellValue());
                    else fetchedObjects.put(columnValue, cell.getNumericCellValue());
                } else if (cell.getCellType() == CellType.STRING)
                    fetchedObjects.put(columnValue, cell.getStringCellValue());
                else if (cell.getCellType() == CellType.BLANK)
                    fetchedObjects.put(columnValue, "");
                else
                    fetchedObjects.put(columnValue, "");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return fetchedObjects;
    }

    public static HashMap<String,Object> preparePersistObjects(Object obj) {
        LinkedHashMap<String,Object> persistedMap = new LinkedHashMap<>();
        try {
            Class<?> clazz = obj.getClass();
            if(obj.getClass().getSuperclass() != null) {
                clazz = clazz.getSuperclass();   //if superclass is present then it is first priority
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true); //This is to make private fields accessible
                    String fieldName = field.getName();
                    Object fieldValue = field.get(obj);
                    persistedMap.put(fieldName, fieldValue);
                }
                clazz = obj.getClass();
            }
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {   //For current class
                    field.setAccessible(true); //This is to make private fields accessible
                    String fieldName = field.getName();
                    Object fieldValue = field.get(obj);
                    persistedMap.put(fieldName, fieldValue);
                }
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
         return persistedMap;
    }
}
