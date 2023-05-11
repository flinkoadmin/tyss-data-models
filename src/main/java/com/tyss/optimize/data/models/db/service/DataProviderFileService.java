package com.tyss.optimize.data.models.db.service;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.data.models.db.model.DataProvider;
import com.tyss.optimize.data.models.db.model.SheetData;
import com.tyss.optimize.data.models.dto.ResponseDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

    public ResponseDTO readFileXls(String filePath) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        DataProvider dataProvider = new DataProvider();

        String fileExt = FilenameUtils.getExtension(filePath);

        if (Objects.nonNull(fileExt) && StringUtils.isNotEmpty(fileExt)) {
            fileExt = fileExt.toLowerCase();
            if (Arrays.asList("xls", "xlsx", "csv", "numbers").contains(fileExt)) {
                responseDTO = readFromXls(dataProvider, filePath);
            }
        } else {
            return invalidFileFormatResponse();
        }
        return responseDTO;
    }

    private ResponseDTO readFromXls(DataProvider dataProvider, String filePath) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
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
                responseDTO = getSheetDataList(sheet);
                if (CommonConstants.FAILURE.equalsIgnoreCase(responseDTO.getStatus())) {
                    return responseDTO;
                }
                List<List<String>> sheetDataTable = (List<List<String>>) responseDTO.getResponseObject();
                if (!CollectionUtils.isEmpty(sheetDataTable))
                    sheetData.setHeader(sheetDataTable.get(0));
            }
            sheetDataList.add(sheetData);
        }
        dataProvider.setSheetdata(sheetDataList);
        responseDTO.setResponseObject(dataProvider);
        return responseDTO;
    }

    public ResponseDTO viewFileData(String filePath, String sheetName) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        DataProvider dataProvider = new DataProvider();

        String fileExt = FilenameUtils.getExtension(filePath);

        if (Objects.nonNull(fileExt) && StringUtils.isNotEmpty(fileExt)) {
            fileExt = fileExt.toLowerCase();
            if (Arrays.asList("xls", "xlsx", "csv", "numbers").contains(fileExt)) {
                responseDTO = viewFileData(dataProvider, filePath, sheetName);
            }
        } else {
            return invalidFileFormatResponse();
        }
        return responseDTO;
    }

    private ResponseDTO viewFileData(DataProvider dataProvider, String filePath, String sheetName) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
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
            responseDTO = getSheetDataList(sheet);
            if (CommonConstants.FAILURE.equalsIgnoreCase(responseDTO.getStatus())) {
                return responseDTO;
            }
            List<List<String>> sheetDataTable = (List<List<String>>) responseDTO.getResponseObject();
            String jsonString = getJSONStringFromList(sheetDataTable);
            dataProvider.setExcelSheetData(jsonString);
            responseDTO.setResponseObject(dataProvider);
        }
        return responseDTO;
    }

    public ResponseDTO getSheetDataList(Sheet sheet) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<List<String>> totalRows = new ArrayList<List<String>>();
        int firstCellNum = 0;
        int lastCellNum = 0;
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        if (lastRowNum >= 0 && firstRowNum == 0) {
            for (int i = firstRowNum; i < lastRowNum + 1; i++) {
                Row row = sheet.getRow(i);
                if (i == 0) {
                    if (Objects.nonNull(row)) {
                        firstCellNum = row.getFirstCellNum();
                        lastCellNum = row.getLastCellNum();
                    } else
                        return invalidFileFormatResponse();
                }
                List<String> rowDataList = new ArrayList<>();
                for (int j = firstCellNum; j < lastCellNum; j++) {
                    if (Objects.nonNull(row)) {
                        if (null != row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)) {
                            Cell cell = row.getCell(j);
                            int cellType = cell.getCellType().getCode();
                            if (cellType == CellType.BLANK.getCode()) {
                                rowDataList.add("");
                            } else if (cellType == CellType.NUMERIC.getCode()) {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                                    rowDataList.add(dateFormatter.format(cell.getDateCellValue()));
                                }else{
                                    double numberValue = cell.getNumericCellValue();
                                    String stringCellValue = BigDecimal.valueOf(numberValue).toPlainString();
                                    rowDataList.add(stringCellValue);
                                }
                            } else if (cellType == CellType.STRING.getCode()) {
                                String cellValue = cell.getStringCellValue();
                                rowDataList.add(cellValue);
                            } else if (cellType == CellType.BOOLEAN.getCode()) {
                                boolean numberValue = cell.getBooleanCellValue();
                                String stringCellValue = String.valueOf(numberValue);
                                rowDataList.add(stringCellValue);
                            } else {
                                if (cell.getCellType() == CellType.FORMULA) {
                                    if (cell.getCachedFormulaResultType().getCode() == CellType.NUMERIC.getCode()) {
                                        double numberValue = cell.getNumericCellValue();
                                        String stringCellValue = BigDecimal.valueOf(numberValue).toPlainString();
                                        rowDataList.add(stringCellValue);
                                    } else if (cell.getCachedFormulaResultType().getCode() == CellType.STRING.getCode()) {
                                        String cellValue = cell.getStringCellValue();
                                        rowDataList.add(cellValue);
                                    } else if (cell.getCachedFormulaResultType().getCode() == CellType.BOOLEAN.getCode()) {
                                        boolean numberValue = cell.getBooleanCellValue();
                                        String stringCellValue = String.valueOf(numberValue);
                                        rowDataList.add(stringCellValue);
                                    }
                                }
                            }
                        }
                    } else {
                        rowDataList.add("");
                    }
                }
                totalRows.add(rowDataList);
            }
            responseDTO.setResponseObject(totalRows);
        } else {
            return invalidFileFormatResponse();
        }
        return responseDTO;
    }


    private static String getJSONStringFromList(List<List<String>> dataTable) {
        String excelData = "";
        String columnValue = "";

        if (dataTable != null) {
            int rowCount = dataTable.size();

            if (rowCount > 1) {
                JSONArray tableJSONArray = new JSONArray();
                List<String> headerRow = dataTable.get(0);
                int columnCount = headerRow.size();
                for (int i = 1; i < rowCount; i++) {
                    LinkedHashMap<Object, Object> jsonOrderedMap = new LinkedHashMap<Object, Object>();
                    List<String> dataRow = dataTable.get(i);
                    for (int j = 0; j < columnCount; j++) {
                        String columnName = headerRow.get(j);
                        if (null != dataRow.get(j)) {
                            columnValue = dataRow.get(j);
                        }
                        String s = columnValue.replaceAll("\\s+", " ");
                        jsonOrderedMap.put(columnName, s.substring(0, Math.min(s.length(), 100)));
                    }
                    tableJSONArray.add(jsonOrderedMap);
                }
                excelData = tableJSONArray.toString().replaceAll("////", "");
            }
        }
        return excelData;
    }

    private ResponseDTO invalidFileFormatResponse() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponseCode(HttpStatus.BAD_REQUEST.value());
        responseDTO.setStatus(CommonConstants.FAILURE);
        responseDTO.setMessage("Invalid File Format");
        return responseDTO;
    }
}
