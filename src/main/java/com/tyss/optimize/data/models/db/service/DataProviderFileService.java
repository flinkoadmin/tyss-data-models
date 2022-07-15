package com.tyss.optimize.data.models.db.service;

import com.tyss.optimize.data.models.db.model.DataProvider;
import com.tyss.optimize.data.models.db.model.SheetData;
import com.tyss.optimize.data.models.dto.StorageInfo;
import com.tyss.optimize.data.models.dto.storage.StorageConfig;
import com.tyss.optimize.data.models.dto.storage.StorageConfigFactory;
import com.tyss.optimize.data.models.dto.storage.StorageConfigService;
import com.tyss.optimize.data.models.dto.storage.StorageManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;


@Service
@Slf4j
public class DataProviderFileService {

    @Autowired
    StorageConfigService storageConfigService;


    public DataProvider readFile(String filePath) throws Exception {
        DataProvider dataProvider = new DataProvider();

        String fileExt = FilenameUtils.getExtension(filePath);

        switch (fileExt.toLowerCase()) {
            case "xls":
                readFromXLS(dataProvider, filePath);
                break;
            case "xlsx":
                readFromXLS(dataProvider, filePath);
                break;
            case "csv":
                readFromXLS(dataProvider, filePath);
                break;
            case "numbers":
                readFromXLS(dataProvider, filePath);
                break;
            default:
        }

        return dataProvider;
    }

    private void readFromXLS(DataProvider dataProvider, String filePath) throws Exception {
        List<SheetData> sheetDataList = new ArrayList<>();
        StorageConfig storageConfig = storageConfigService.getStorageConfig();
        String bucketName = storageConfig.getInputs().getBucketName();
        String storageType = storageConfig.getType();
        StorageInfo storageInfoFile = new StorageInfo();
        storageInfoFile.setFilePath(List.of(filePath));
        storageInfoFile.setType(storageType);
        storageInfoFile.setBucketName(bucketName);
        storageInfoFile.setUpdateSrc(false);
        StorageManager storageManager = StorageConfigFactory.getStorageManager(storageType);
        InputStream inputStream = storageManager.getObjectStream(storageInfoFile, filePath);
        //FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        for (int index = 0; index < workbook.getNumberOfSheets(); index++) {
            SheetData sheetData = new SheetData();
            Sheet sheet = workbook.getSheetAt(index);
            int beginningRow = sheet.getFirstRowNum() + 1;
            for (int rowIndex = beginningRow; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                if (beginningRow > 1) {
                    Row row = sheet.getRow(beginningRow);
                    if (row != null) {
                        beginningRow = beginningRow + 1;
                        break;
                    }
                }
                beginningRow++;
            }
            sheetData.setSheetName(sheet.getSheetName());
            sheetData.setFromRow(((Integer) (beginningRow)).toString());
            sheetData.setToRow(((Integer) (sheet.getLastRowNum() + 1)).toString());
            sheetDataList.add(sheetData);
        }
        dataProvider.setSheetdata(sheetDataList);
    }

    private void readFromCSV(DataProvider dataProvider) {

    }

    private void readFromNumbers(DataProvider dataProvider) {

    }

    public DataProvider readFileXls(String filePath) throws Exception {
        DataProvider dataProvider = new DataProvider();

        String fileExt = FilenameUtils.getExtension(filePath);

        switch (fileExt.toLowerCase()) {
            case "xls":
                readFromXls(dataProvider, filePath);
                break;
            case "xlsx":
                readFromXls(dataProvider, filePath);
                break;
            case "csv":
                readFromXls(dataProvider, filePath);
                break;
            case "numbers":
                readFromXls(dataProvider, filePath);
                break;
            default:
        }
        return dataProvider;
    }

    private void readFromXls(DataProvider dataProvider, String filePath) throws Exception {
        List<SheetData> sheetDataList = new ArrayList<>();
        StorageConfig storageConfig = storageConfigService.getStorageConfig();
        String bucketName = storageConfig.getInputs().getBucketName();
        String storageType = storageConfig.getType();
        StorageInfo storageInfoFile = new StorageInfo();
        storageInfoFile.setFilePath(List.of(filePath));
        storageInfoFile.setType(storageType);
        storageInfoFile.setBucketName(bucketName);
        storageInfoFile.setUpdateSrc(false);
        StorageManager storageManager = StorageConfigFactory.getStorageManager(storageType);
        InputStream inputStream = storageManager.getObjectStream(storageInfoFile, filePath);
        //FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(inputStream);

        for (int index = 0; index < workbook.getNumberOfSheets(); index++) {
            SheetData sheetData = new SheetData();
            Sheet sheet = workbook.getSheetAt(index);
            int beginningRow = sheet.getFirstRowNum() + 1;

            sheetData.setSheetName(sheet.getSheetName());
            sheetData.setFromRow(((Integer) (beginningRow)).toString());
            sheetData.setToRow(((Integer) (sheet.getLastRowNum() + 1)).toString());

            if (null != sheet && sheet.getSheetName() != null && sheet.getSheetName().length() > 0) {
                List<List<String>> sheetDataTable = getSheetDataList(sheet);
                String jsonString = getJSONStringFromList(sheetDataTable);
                sheetData.setHeader(sheetDataTable.get(0));
            }
            sheetDataList.add(sheetData);
        }
        dataProvider.setSheetdata(sheetDataList);
    }

    public DataProvider viewFileData(String filePath, String sheetName) throws Exception {
        DataProvider dataProvider = new DataProvider();

        String fileExt = FilenameUtils.getExtension(filePath);

        switch (fileExt.toLowerCase()) {
            case "xls":
                viewFileData(dataProvider, filePath ,sheetName);
                break;
            case "xlsx":
                viewFileData(dataProvider, filePath ,sheetName);
                break;
            case "csv":
                viewFileData(dataProvider, filePath ,sheetName);
                break;
            case "numbers":
                viewFileData(dataProvider, filePath ,sheetName);
                break;
            default:
        }

        return dataProvider;
    }

    private void viewFileData(DataProvider dataProvider, String filePath , String sheetName) throws Exception {
        StorageConfig storageConfig = storageConfigService.getStorageConfig();
        String bucketName = storageConfig.getInputs().getBucketName();
        String storageType = storageConfig.getType();
        StorageInfo storageInfoFile = new StorageInfo();
        storageInfoFile.setFilePath(List.of(filePath));
        storageInfoFile.setType(storageType);
        storageInfoFile.setBucketName(bucketName);
        storageInfoFile.setUpdateSrc(false);
        StorageManager storageManager = StorageConfigFactory.getStorageManager(storageType);
        InputStream inputStream = storageManager.getObjectStream(storageInfoFile, filePath);
        //FileInputStream fis = new FileInputStream(new File(filePath));
        Sheet sheet = new XSSFWorkbook(inputStream).getSheet(sheetName);

        if (null != sheet && sheet.getSheetName() != null && sheet.getSheetName().length() > 0) {
            List<List<String>> sheetDataTable = getSheetDataList(sheet);
            String jsonString = getJSONStringFromList(sheetDataTable);
            dataProvider.setExcelSheetData(jsonString);
        }
    }

    public List<List<String>>  getSheetDataList(Sheet sheet) {

        List<List<String>> totalRows = new ArrayList<List<String>>();
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        if (lastRowNum > 0) {
            for (int i = firstRowNum; i <lastRowNum+1; i++) {
               Row  row = sheet.getRow(i);

                int firstCellNum = row.getFirstCellNum();
                int lastCellNum = row.getLastCellNum();

                List<String> rowDataList = new ArrayList<String>();
                for (int j = firstCellNum; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (null != row.getCell(j)) {
                        int cellType = cell.getCellType().getCode();
                        if (cellType == CellType.BLANK.getCode()) {
                            rowDataList.add("");
                        } else if (cellType == CellType.NUMERIC.getCode()) {
                            double numberValue = cell.getNumericCellValue();
                            String stringCellValue = BigDecimal.valueOf(numberValue).toPlainString();
                            rowDataList.add(stringCellValue);

                        } else if (cellType == CellType.STRING.getCode()) {
                            String cellValue = cell.getStringCellValue();
                            rowDataList.add(cellValue);
                        } else if (cellType == CellType.BOOLEAN.getCode()) {
                            boolean numberValue = cell.getBooleanCellValue();

                            String stringCellValue = String.valueOf(numberValue);

                            rowDataList.add(stringCellValue);

                        }
                    }
                    if(null == row.getCell(j))
                        rowDataList.add("");

                }
                totalRows.add(rowDataList);
            }
        }
        return totalRows;
    }




    private static String getJSONStringFromList(List<List<String>> dataTable) {
        Set<String> colSet = new HashSet<>();
        String excelData = "";
        String columnValue="";

        if (dataTable != null) {
            int rowCount = dataTable.size();

            if (rowCount > 1) {
                JSONArray tableJSONArray = new JSONArray();
                List<String> headerRow = dataTable.get(0);
                int columnCount = headerRow.size();
                 for (int i = 1 ; i < rowCount; i++) {
                    LinkedHashMap<Object, Object> jsonOrderedMap = new LinkedHashMap<Object, Object>();
                    List<String> dataRow = dataTable.get(i);
                    for (int j = 0; j < columnCount; j++) {
                        String columnName = headerRow.get(j);
                        if(null != dataRow.get(j)) {
                            columnValue = dataRow.get(j);
                        }

                            String s =  columnValue.replaceAll("\\s+", " ");
                        jsonOrderedMap.put(columnName,s.substring(0, Math.min(s.length(), 100)));

                        }

                    tableJSONArray.add(jsonOrderedMap);
                }
                excelData = tableJSONArray.toString().replaceAll("////", "");
            }
        }
        return excelData;
    }

}
