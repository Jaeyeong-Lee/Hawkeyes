package com.samsung.iomanager;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

class FileIOManagerTest {

    FileIOManager fileIOManager;

    @BeforeEach
    void setup(){
        fileIOManager = new FileIOManager();
    }

    @AfterEach
    void teardown(){
        fileIOManager = null;
    }

    @Test
    void testReadInput(){

        // 테스트 데이터 준비: 제공된 sample input file 사용
        String fileName = "input.txt";

        // 실행
        List<String> actualReturn = fileIOManager.readInput(fileName);

        // assertion
        Assertions.assertNotNull(actualReturn);
        Assertions.assertEquals(actualReturn.size(), 40);

        actualReturn.stream().filter("ADD"::startsWith).forEach(string -> Assertions.assertEquals(10, string.split(",").length));
        actualReturn.stream().filter("SCH"::startsWith).forEach(string -> Assertions.assertEquals(6, string.split(",").length));
        actualReturn.stream().filter("DEL"::startsWith).forEach(string -> Assertions.assertEquals(6, string.split(",").length));
        actualReturn.stream().filter("MOD"::startsWith).forEach(string -> Assertions.assertEquals(8, string.split(",").length));

    }

    @Test
    void testWriteOutput() throws IOException {

        // 테스트 데이터 준비: 결과 파일 준비
        String fileName = "testOutput.txt";
        List<String> testData = new ArrayList<>();
        List<String> actualReturn = new ArrayList<>();
        testData.add("DEL,08117441,BMU MPOSXU,CL3,010-2703-3153,20010215,ADV");
        testData.add("DEL,1");
        testData.add("SCH,02117175,SBILHUT LDEXRI,CL4,010-2814-1699,19950704,ADV");

        // 실행
        fileIOManager.writeOutput(fileName, testData);

        // assertion: 파일 읽어서, 파일의 생성 여부 & 내용 확인
        Assertions.assertTrue(new File(fileName).exists());

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String str;
        while((str = br.readLine()) != null)
            actualReturn.add(str);
        br.close();

        Assertions.assertIterableEquals(testData, actualReturn);
    }
}