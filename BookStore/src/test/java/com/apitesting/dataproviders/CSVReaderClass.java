//package com.apitesting.dataproviders;
//
//import com.apitesting.utils.FileConstants;
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvValidationException;
//import org.testng.annotations.DataProvider;
//
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
//public class CSVReaderClass {
//
//
//    @DataProvider(name = "CSVTestData")
//    public Object[][] getTestData(){
//
//        Object[][] objArray = null;
//        Map<String,String> map = null;
//        List<Map<String,String>> testDataList = null;
//
//        try {
//            CSVReader csvReader = new CSVReader(new FileReader(FileConstants.CSV_TEST_DATA));
//
//            testDataList = new ArrayList<Map<String,String>>();
//
//            String[] line = null;
//
//            int count = 0;
//
//            while((line = csvReader.readNext())!=null) {
//
//                if(count == 0) {
//                    count++;
//                    continue;
//                }
//
//                map = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
//
//                map.put("username", line[0]);
//                map.put("password", line[1]);
//
//
//                testDataList.add(map);
//            }
//
//            objArray = new Object[testDataList.size()][1];
//
//            for (int i = 0; i < testDataList.size(); i++) {
//                objArray[i][0] = testDataList.get(i);
//            }
//
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (CsvValidationException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        return objArray;
//
//    }
//}
