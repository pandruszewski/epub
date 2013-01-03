package com.adobe.epubcheck.test;

import com.adobe.epubcheck.tool.Checker;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonPartEquals;


public class script_Test
{
  private SecurityManager originalManager;

  @Before
  public void setUp() throws Exception
  {
    this.originalManager = System.getSecurityManager();
    System.setSecurityManager(new NoExitSecurityManager());
  }

  @After
  public void tearDown() throws Exception
  {
    System.setSecurityManager(this.originalManager);
  }

  @Test
  public void scriptPropertiesTest() throws Exception
  {
    runScriptTest("properties");
  }

  private void runScriptTest(String testName) throws Exception
  {
    String[] args = new String[5];
    URL inputUrl = script_Test.class.getResource("scripts/" + testName);
    String inputPath = inputUrl.getPath();
    String outputPath = inputPath + "/../" + testName + "_actual_results.json";
    args[0] = inputPath;
    args[1] = "-mode";
    args[2] = "exp";
    args[3] = "--output";
    args[4] = outputPath;
    int result = Checker.run(args);
    Assert.assertEquals("Return code", result, 0);
    File actualOutput = new File(outputPath);
    Assert.assertTrue("Output file is missing.", actualOutput.exists());
    URL expectedUrl = script_Test.class.getResource("scripts/" + testName + "_expected_results.json");
    File expectedOutput = new File(expectedUrl.getPath());
    Assert.assertTrue("Expected file is missing.", expectedOutput.exists());
    Reader expectedReader = new FileReader(expectedOutput);
    Reader actualReader = new FileReader(actualOutput);
    assertJsonPartEquals(expectedReader, actualReader, "listOfMessage");
    expectedReader.close();
    actualReader.close();
  }
}
