package com.ken42;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.logging.Logger;
import org.testng.annotations.Test;

public class Pfs_resource {
	public static Logger log = Logger.getLogger("Pfs_portal");
	static int time = 2000;
	private static String PFSurl;

	@Test
	public static void resourceFacultyInitialSteps(String faculty, String url, WebDriver driver, Logger log,
			String[] csvCell)
			throws Exception {
		Utils.login(driver, faculty, url, log, csvCell);
		Utils.checkAcadAndClick(driver, faculty, url, log, csvCell);
		Utils.clickXpath(driver, ActionXpath.faccc, time, "Click on course content", log);
	}

	public static void resourceSubmitForm(String faculty, String url, WebDriver driver, Logger log) throws Exception {
		try {
			Utils.clickXpath(driver, ActionXpath.facssadd, time, "Click of add resource", log);
			Utils.smallSleepBetweenClicks(2);
			Utils.clickXpath(driver, ActionXpath.facccresdescclick, time, "Click on URL resource link", log);
			// Utils.smallSleepBetweenClicks(1);
			// Utils.clickXpath(driver, ActionXpath.facssadd, time, "facssadd");
			Utils.smallSleepBetweenClicks(1);
			// Utils.clickXpath(driver, ActionXpath.facccresdescclick, time,
			// "facccresdescclick");
			Utils.callSendkeys(driver, ActionXpath.facccresurl, "Hello", time, log);
			// Utils.callSendkeys(driver, "//*[@id='tinymce']//p", "Testing", time);
			Utils.clickXpath(driver, ActionXpath.facccressubmitform, time, "Save URL link button", log);
			// log.info("resource create passed ");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning(" Resource create Fuction FAILED  ");
			throw (e);
		}

	}

	public static void resourcePublishAndLogout(String faculty, String url,
			WebDriver driver, String fileName, String Role, Logger log) throws Exception {
		try {

			Utils.clickXpath(driver, "//p[. ='" + fileName + "']/../../.././..//*[local-name()='svg']", time,
					"Select PPT file name", log);
			Utils.clickXpath(driver, ActionXpath.facsspublish, time, "Click on publish button1", log);
			Utils.clickXpath(driver, ActionXpath.facsspublishyes, time, "Click on publish button2", log);
			Utils.bigSleepBetweenClicks(1);
			Utils.logout(driver, url, Role, log);

			// log.info(fileName + " Publish passed ");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning(fileName + " Publish Fuction FAILED  ");
			throw (e);
		}

	}

	public static void resourceStudentViewAndLogout(String faculty, String url,
			WebDriver driver, String fileName, String Role, Logger log) throws Exception {
		try {
			Utils.clickXpath(driver, "//p[.='" + fileName + "']/../../.././..//*[local-name()='svg']", time,
					"Select PPT file name", log);
			Utils.clickXpath(driver, ActionXpath.viewpdf2, time, "Click on View Spreadsheet", log);
			Utils.clickXpath(driver, ActionXpath.learn, time, "click learn", log);
			Utils.logout(driver, url, Role, log);
			// log.info(fileName + "Studentview passed ");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning(fileName + "Studentview Fuction FAILED  ");
			throw (e);
		}
	}

	public static void resourceEdit(String faculty, String url,
			WebDriver driver, String fileName, String Role, Logger log) throws Exception {
		try {
			Utils.clickXpath(driver, "//p[.='" + fileName + "']/../../.././..//*[local-name()='svg']", time,
					"Select PPT file name", log);
			Utils.clickXpath(driver, ActionXpath.facpdfedit, time, "Click on edit button", log);
			Utils.clickXpath(driver, ActionXpath.deletefile, time, "Click on deletefile", log);
		} catch (Exception e) {
			Utils.printException(e);
			log.warning(fileName + "Delete Fuction FAILED  ");
			throw (e);
		}
	}

	public static void resourceDeleteAndLogout(String faculty, String url,
			WebDriver driver, String fileName, String Role, Logger log) throws Exception {
		try {
			Utils.clickXpath(driver, "//p[.='" + fileName + "']/../../.././..//*[local-name()='svg']", time,
					"Select PPT file name", log);
			Utils.clickXpath(driver, ActionXpath.facpdfdelete, time, "Click on Delete button1", log);
			Utils.clickXpath(driver, ActionXpath.facpdfdelete2, time, "Click on delete button2", log);
			Utils.logout(driver, url, Role, log);
			// log.info(fileName + "Delete passed ");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning(fileName + "Delete Fuction FAILED  ");
			throw (e);
		}
	}

	public static void resorcesdelete(String faculty, String url,
			WebDriver driver, String[] csvCell, String Role, Logger log, String Prefix, String xpath) throws Exception {
		try {
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.smallSleepBetweenClicks(5);
			Utils.clickXpath(driver, xpath, time, "View Resources" + xpath, log);
			Utils.smallSleepBetweenClicks(1);
			List<WebElement> elements = driver.findElements(By.xpath("(//*[contains(text(), '" + Prefix + "')])"));
			int count = elements.size();
			System.out.println("Count is: " + count);
			while (count > 0) {
				Utils.smallSleepBetweenClicks(4);
				Utils.clickXpath(driver,
						"(//*[contains(text(), '" + Prefix + "')]/../../../../..//*[local-name()='svg'])[1]", time,
						"Select LINK file name", log);
				Utils.clickXpath(driver, ActionXpath.facpdfdelete, time, "Click on Delete button1", log);
				Utils.clickXpath(driver, ActionXpath.facpdfdelete2, time, "Click on delete button2", log);
				count--;
			}
			Utils.logout(driver, url, Role, log);
		} catch (Exception e) {
			Utils.printException(e);
			log.warning(" Publish Fuction FAILED  ");
			throw (e);
		}

	}

	@Test(priority = 41)
	public static void testSpreadsheetCreateViewDelete(String student, String faculty, String url,
			String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {

			String SpreadSheetFile = "";
			String folder = "";
			folder = Pfs_portal.getFolderPath();
			SpreadSheetFile = folder + "\\demo.xlsx";

			System.out.println("TC-41:  SpreadSheet resource Create View delete Test case Started");

			Utils.smallSleepBetweenClicks(1);
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			Utils.smallSleepBetweenClicks(1);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");

			// String returnArray[] = new String[2];
			// returnArray = Utils.getClassSubjectAndSection(driver, url,"resource");
			// String programconverted = returnArray[0];
			// String subject = returnArray[1];

			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facssclick, time, "facssclick", log);
			resourceSubmitForm(faculty, url, driver, log);

			String fileName = "Excel_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			driver.findElement(By.xpath("//input[@accept='.xlsx,.xls']")).sendKeys(SpreadSheetFile);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			Utils.smallSleepBetweenClicks(1);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facssopen, time, "Click on SS SVG", log);

			resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);

			// Student part starts
			Utils.login(driver, student, url, log, csvCell);
			Utils.checkAcadAndClick(driver, faculty, url, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.learn, time, "learn", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();

			Utils.smallSleepBetweenClicks(1);

			Utils.clickXpath(driver, ActionXpath.viewss, time, "viewss", log);
			resourceStudentViewAndLogout(faculty, url, driver, fileName, Role, log);
			// Student part ends

			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.facssopen, time, "facspreadsheetopen", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-41: SpreadSheet resource Create View delete Test Case PASSED \n");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-41: SpreadSheet resource Create View delete Test Case FAILED \n");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 42)
	public static void testPPTCreateViewDelete(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {

			String folder = "";
			folder = Pfs_portal.getFolderPath();
			String PPT_file = "";
			PPT_file = folder + "\\demo.pptx";

			System.out.println("TC-42:  PPT resource Create View delete Test case Started");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");

			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facpptclick, time, "facpptclick", log);
			resourceSubmitForm(faculty, url, driver, log);
			String fileName = "PPT_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			driver.findElement(By.xpath("//input[@accept='.ppt,.pptx']")).sendKeys(PPT_file);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facpptfopen, time, "facpptfopen", log);
			resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);

			Utils.login(driver, student, url, log, csvCell);
			Utils.checkAcadAndClick(driver, faculty, url, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.learn, time, "learn", log);

			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();

			Utils.clickXpath(driver, ActionXpath.viewppt, time, "viewppt", log);
			resourceStudentViewAndLogout(faculty, url, driver, fileName, Role, log);

			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.facpptfopen, time, "facpptfopen", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-42: PPT resource Create View delete Test Case PASSED \n");

		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-42: PPT resource Create View delete Test Case FAILED \n");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 43)
	public static void testPDFCreateViewDelete(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			String PDF_file = "";
			String folder = "";
			folder = Pfs_portal.getFolderPath();
			PDF_file = folder + "\\demo.pdf";

			System.out.println("TC-43:  Create PDF resource publish and delete PDF");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");

			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facccrespdf, time, "facccrespdf", log);
			resourceSubmitForm(faculty, url, driver, log);

			String fileName = "PDF_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			driver.findElement(By.xpath("//input[@accept='.pdf']"))
					.sendKeys(PDF_file);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facpdfopen, time, "facpdfopen", log);
			resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);

			// Now verify in student
			Utils.login(driver, student, url, log, csvCell);
			Utils.checkAcadAndClick(driver, faculty, url, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.learn, time, "learn", log);

			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();

			Utils.clickXpath(driver, ActionXpath.viewpdf, time, "viewpdf", log);
			resourceStudentViewAndLogout(faculty, url, driver, fileName, Role, log);

			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.facpdfopen, time, "facpdfopen", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-43: Create PDF resource publish and delete PDF PASSED \n");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-43: Create PDF resource publish and delete PDF Test Case FAILED \n");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 44)
	public static void testVideoCreateViewDelete(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			String Video_file = "";
			String folder = "";
			folder = Pfs_portal.getFolderPath();
			Video_file = folder + "\\demo.mp4";

			System.out.println("TC-44:  Create Video resource create view  and delete");
			log.info("TC-44:  Create Video resource create view  and delete Started");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");

			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facvideoclick, time, "facvideoclick", log);
			resourceSubmitForm(faculty, url, driver, log);
			String fileName = "Video_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			driver.findElement(By.xpath("//input[@accept='.mp4']"))
					.sendKeys(Video_file);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facvideoopen, time, "facvideoopen", log);
			resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);

			// Student to verify
			Utils.login(driver, student, url, log, csvCell);
			Utils.smallSleepBetweenClicks(1);
			Utils.checkAcadAndClick(driver, faculty, url, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.learn, time, "Click on learnlearn", log);

			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();

			Utils.clickXpath(driver, ActionXpath.viewvideo, time, "Click on video", log);
			resourceStudentViewAndLogout(faculty, url, driver, fileName, Role, log);

			// Faculty to delete
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.facvideoopen, time, "facvideoopen", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-44: Create Video resource create view  and delete PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-44: Create Video resource create view  and delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 45)
	public static void testLinkCreateViewDelete(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-45:  Link resource Create View delete Test case Started");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");

			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.faclinkclick, time, "faclinkclick", log);
			resourceSubmitForm(faculty, url, driver, log);
			String fileName = "Link_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			Utils.callSendkeys(driver, ActionXpath.faclinkexternal, url, time, log);
			Utils.scrollUpOrDown(driver, time);
			Utils.scrollUpOrDown(driver, time);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);

			if (Utils.publishlink(url)) {
				Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
				driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
				Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
				Utils.smallSleepBetweenClicks(2);
				driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
				Utils.clickXpath(driver, ActionXpath.viewlink, time, "faclinkopen", log);
				resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);
			} else {
				Utils.logout(driver, url, Role, log);
			}

			Utils.login(driver, student, url, log, csvCell);
			Utils.checkAcadAndClick(driver, faculty, url, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.learn, time, "learn", log);

			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();

			Utils.clickXpath(driver, ActionXpath.viewlink, time, "viewlink", log);
			resourceStudentViewAndLogout(faculty, url, driver, fileName, Role, log);

			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.faclinkopen, time, "faclinkopen", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-45 Link resource Create View delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-45: Link resource Create View delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 46)
	public static void testSpreadsheetFileType(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-46:  Test SpreadSheet resource Create View delete Test case Started");
			// if (Utils.checkBimtech(url)) {
			// log.info("TC-45 Test Spreadsheet is not supported on Bimtech");
			// return;
			// }
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facssclick, time, "facssclick", log);

			Utils.clickXpath(driver, ActionXpath.facssadd, time, "Click of add resource", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//input[@accept='.xlsx,.xls']"))
					.sendKeys("C:\\Users\\Public\\Documents\\demo.pdf");
			Utils.smallSleepBetweenClicks(1);
			Utils.smallSleepBetweenClicks(2);
			WebElement s = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[1]"));
			String kenm = s.getText();
			String noSpaceStr = kenm.replaceAll("\\s", "");
			String name = "AnErrorOccuredUnsupportedFileFormat.Pleaseuploadonly.xlsx,.xlsformat.";

			if (noSpaceStr.equals(name)) {
				System.out.println("It is not the excel file");
			} else {
				System.out.println("File uploaded");
			}

			driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[2]/button/span[1]")).click();
			Utils.logout(driver, url, faculty, log);
			log.info("TC-46: Test Spreadsheet File type Test Case PASSED");

		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-46 Test Spreadsheet File type Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 47)
	public static void testPPTFileType(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-47:  Test PPT File type");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facpptclick, time, "facpptclick", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facssadd, time, "Click of add resource", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//input[@accept='.ppt,.pptx']"))
					.sendKeys("C:\\Users\\Public\\Documents\\demo.pdf");
			Utils.smallSleepBetweenClicks(2);

			WebElement s = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[1]"));
			String kenm = s.getText();
			String noSpaceStr = kenm.replaceAll("\\s", ""); // using built in method
			String name = "AnErrorOccuredUnsupportedFileFormat.Pleaseuploadonly.ppt,.pptxformat.";
			if (noSpaceStr.equals(name)) {
				System.out.println("It is not the PPT file");
			} else {
				System.out.println("File uploaded");
			}
			driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[2]/button/span[1]")).click();
			Utils.logout(driver, url, faculty, log);
			log.info("TC-47: Test PPT File type Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-47: Test PPT File type Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 48)
	public static void testPDFFileType(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-48:  Test PDF File type Test Case");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facccrespdf, time, "facccrespdf", log);
			Utils.clickXpath(driver, ActionXpath.facssadd, time, "Click of add resource", log);
			Utils.smallSleepBetweenClicks(2);

			driver.findElement(By.xpath("//input[@accept='.pdf']")).sendKeys("C:\\Users\\Public\\Documents\\demo.pptx");
			Utils.smallSleepBetweenClicks(2);
			WebElement s = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[1]"));
			String kenm = s.getText();
			String noSpaceStr = kenm.replaceAll("\\s", ""); // using built in method
			String name = "AnErrorOccuredUnsupportedFileFormat.Pleaseuploadonly.pdfformat.";
			if (noSpaceStr.equals(name)) {
				System.out.println("It is not the PDF file");
			} else {
				System.out.println("File uploaded");
			}
			driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[2]/button/span[1]")).click();
			Utils.logout(driver, url, faculty, log);
			log.info("TC-48: Test PDF File type Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-48:Test PDF File type Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 49)
	public static void testVideoFileType(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-49: Test Video File type Test Case");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facvideoclick, time, "facvideoclick", log);
			Utils.clickXpath(driver, ActionXpath.facssadd, time, "Click of add resource", log);

			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//input[@accept='.mp4']")).sendKeys("C:\\Users\\Public\\Documents\\demo.pdf");
			Utils.smallSleepBetweenClicks(2);

			WebElement s = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[1]"));
			String kenm = s.getText();
			String noSpaceStr = kenm.replaceAll("\\s", ""); // using built in method
			String name = "AnErrorOccuredUnsupportedFileFormat.Pleaseuploadonly.mp4format.";
			if (noSpaceStr.equals(name)) {
				System.out.println("It is not the Video file");
			} else {
				System.out.println("File uploaded");
			}
			driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div[2]/button/span[1]")).click();
			Utils.logout(driver, url, faculty, log);
			log.info("TC-49: Test Video File type Test Case PASSED \n");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-49:Test Video File type Test Case FAILED \n");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 50)
	public static void testFacultyFilterResource(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-50:  PPT resource Filter Option View Test case Started");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.bigSleepBetweenClicks(1);

			String p = Utils.getTEXT(driver, ActionXpath.facResourceFilterDiv, log, "pptfilter");
			System.out.println("p" + p);
			if (p.contains("Pdf") && (p.contains("Presentations")) && (p.contains("Videos")) && (p.contains("Links"))) {

			} else {
				System.out.println(" All resource are not Presnet Quiting the Test. ");
				log.warning("TC-50 PPt resource Filter Option View Test Case FAILED \n");
				Utils.logout(driver, url, Role, log);
			}
			Utils.clickXpath(driver, ActionXpath.faccFilter, time, "Clik ont he Filter button", log);
			Utils.clickXpath(driver, ActionXpath.faccFilterClear, time, "Clear all the filter ", log);
			Utils.clickXpath(driver, ActionXpath.faccFilter, time, "Appling the Filter to click the filter button",
					log);
			Utils.clickXpath(driver, ActionXpath.faccPPTOPen, time, "CLick on the filter Resource type span", log);
			Utils.clickXpath(driver, ActionXpath.faccPPTCheckBox, time, "select the PPT Check box ", log);
			Actions qwe = new Actions(driver);
			qwe.moveByOffset(40, 40).click().perform();

			String p2 = Utils.getTEXT(driver, ActionXpath.facResourceFilterDiv, log, "pptfilter");
			if (p2.contains("Presentations") && (!p2.contains("pdf")) && (!p2.contains("Spreadsheet"))
					&& (!p2.contains("Videos")) && (!p2.contains("Links"))) {

			} else {
				log.warning(" TC-50: PPT resource Filter Option View FAILED it does not contain all the tabs\n\n");
			}
			Utils.executeLongWait(url);
			Utils.logout(driver, url, Role, log);
			Utils.smallSleepBetweenClicks(1);
			log.info("TC-50: PPT resource Filter Option View Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-50: PPT resource Filter Option View Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 51)
	public static void testFacultyFilterPDFResource(String student, String faculty, String url, String Browser,
			String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-51:  PDF resource Filter Option View Test case Started");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.bigSleepBetweenClicks(1);
			String p = Utils.getTEXT(driver, ActionXpath.facResourceFilterDiv, log, "pdffilter");
			System.out.println("p" + p);
			if (p.contains("Pdf") && (p.contains("Presentations")) && (p.contains("Videos")) && (p.contains("Links"))) {

			} else {
				System.out.println(" All resource are not Presnet Quiting the Test. ");
				Utils.logout(driver, url, Role, log);
				log.warning("TC-51: PDF resource Filter Option View Test Case FAILED");
			}
			Utils.clickXpath(driver, ActionXpath.faccFilter, time, "click on the filter button", log);

			Utils.clickXpath(driver, ActionXpath.faccFilterClear, time, "click the clear all on the filter ", log);
			Utils.clickXpath(driver, ActionXpath.faccFilter, time, "click on the filter to open to apply the filter ",
					log);
			Utils.clickXpath(driver, ActionXpath.faccPPTOPen, time, "Open the reosurce Filter  span opnen", log);
			Utils.clickXpath(driver, ActionXpath.faccPDFCheckBox, time, "click the PDF checkbox", log);
			Actions qwe = new Actions(driver);
			qwe.moveByOffset(40, 40).click().perform();

			String p2 = Utils.getTEXT(driver, ActionXpath.facResourceFilterDiv, log, "pdffilter");
			if (p2.contains("Pdf") && (!p2.contains("Presentations")) && (!p2.contains("Spreadsheet"))
					&& (!p2.contains("Videos")) && (!p2.contains("Links"))) {

			} else {
				log.warning(" TC-51: PDF resource Filter Option View FAILED it does not contain all the tabs\n\n");
			}
			Utils.executeLongWait(url);
			Utils.logout(driver, url, Role, log);
			Utils.smallSleepBetweenClicks(1);
			log.info("TC-51: PDF resource Filter Option View Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-51: PDF resource Filter Option View Test Case FAILED");
			Utils.logout(driver, Role, url, log);

		}
	}

	@Test(priority = 52)
	public static void testFacultyFilterVideoResource(String student, String faculty, String url, String Browser,
			String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-52:  Video resource Filter Option View Test case Started");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.bigSleepBetweenClicks(1);
			String p = Utils.getTEXT(driver, ActionXpath.facResourceFilterDiv, log, "videofilter");
			System.out.println("p" + p);
			if (p.contains("Pdf") && (p.contains("Presentations")) && (p.contains("Videos")) && (p.contains("Links"))) {

			} else {
				System.out.println(" All resource are not Presnet Quiting the Test. ");
				log.warning("TC-52 Video resource Filter Option View Test Case FAILED \n");
				Utils.logout(driver, url, Role, log);
			}
			Utils.clickXpath(driver, ActionXpath.faccFilter, time, "Clik ont he Filter button", log);
			Utils.clickXpath(driver, ActionXpath.faccFilterClear, time, "Clear all the filter", log);
			Utils.clickXpath(driver, ActionXpath.faccFilter, time, "Appling the Filter to click the filter button",
					log);
			Utils.clickXpath(driver, ActionXpath.faccPPTOPen, time, "CLick on the filter Resource type span", log);
			Utils.clickXpath(driver, ActionXpath.faccVideoCheckBox, time, "select the Viedo Check box", log);
			Actions qwe = new Actions(driver);
			qwe.moveByOffset(40, 40).click().perform();

			String p2 = Utils.getTEXT(driver, ActionXpath.facResourceFilterDiv, log, "videofilter");
			if (p2.contains("Videos") && (!p2.contains("Presentations")) && (!p2.contains("Spreadsheet"))
					&& (!p2.contains("pdf")) && (!p2.contains("Links"))) {
				System.out.println(" TC-52: Video resource Filter Option Contains Video test case PASSED \n\n");
			} else {
				log.warning(" TC-52: Video resource Filter Option View FAILED it does not contain all the tabs\n\n");
			}
			Utils.logout(driver, url, Role, log);
			Utils.smallSleepBetweenClicks(1);
			log.info("TC-52: Video resource Filter Option View Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-52: Video resource Filter Option View Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 53)
	public static void testFacultyFilterLinksResource(String student, String faculty, String url, String Browser,
			String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-53:  Links resource Filter Option View Test case Started");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.bigSleepBetweenClicks(1);
			String p = Utils.getTEXT(driver, ActionXpath.facResourceFilterDiv, log, "linkfilter");
			System.out.println("p" + p);
			if (p.contains("Pdf") && (p.contains("Presentations")) && (p.contains("Videos")) && (p.contains("Links"))) {

			} else {
				System.out.println(" All resource are not Presnet Quiting the Test. ");
				log.warning("TC-53 Links resource Filter Option View Test Case FAILED \n");
				Utils.logout(driver, url, Role, log);
			}
			Utils.clickXpath(driver, ActionXpath.faccFilter, time, "Clik ont he Filter button", log);
			Utils.clickXpath(driver, ActionXpath.faccFilterClear, time, "Clear all the filter", log);
			Utils.clickXpath(driver, ActionXpath.faccFilter, time, "Appling the Filter to click the filter button",
					log);
			Utils.clickXpath(driver, ActionXpath.faccPPTOPen, time, "CLick on the filter Resource type span", log);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(5,5)");
			Utils.clickXpath(driver, ActionXpath.faccLinksCheckBox, time, "Selec the Link Check Box filter", log);
			Actions qwe = new Actions(driver);
			qwe.moveByOffset(40, 40).click().perform();

			String p2 = Utils.getTEXT(driver, ActionXpath.facResourceFilterDiv, log, "linkfilter");
			if (p2.contains("Links") && (!p2.contains("Presentations")) && (!p2.contains("Spreadsheet"))
					&& (!p2.contains("pdf")) && (!p2.contains("Videos"))) {
				System.out.println(" TC-53: Links resource Filter Option Contains Links test case PASSED \n\n");
			} else {
				log.warning(" TC-53: Links resource Filter Option View FAILED it does not contain all the tabs\n\n");
			}
			Utils.executeLongWait(url);
			Utils.logout(driver, url, Role, log);
			Utils.smallSleepBetweenClicks(1);
			log.info("TC-53: Links resource Filter Option View Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-53: Links resource Filter Option View Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 54)
	public static void CreatepublisheditdeltePDF(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			String PDF_file = "";
			String folder = "";
			folder = Pfs_portal.getFolderPath();
			PDF_file = folder + "\\demo.pdf";

			System.out.println("TC-54:  Create PDF resource publish Edit and delete PDF");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");
			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facccrespdf, time, "facccrespdf", log);
			resourceSubmitForm(faculty, url, driver, log);
			String fileName = "PDF_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			driver.findElement(By.xpath("//input[@accept='.pdf']"))
					.sendKeys(PDF_file);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facpdfopen, time, "facpdfopen", log);
			resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.viewpdf, time, "facpdfopen", log);
			resourceEdit(faculty, url, driver, fileName, Role, log);
			driver.findElement(By.xpath("//input[@accept='.pdf']"))
					.sendKeys("C:\\Users\\Public\\Documents\\demo1.pdf");
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("window.scrollBy(-200,-200)");
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.viewpdf, time, "facpdfopen", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-54 PDF resource Create Publish Edit delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-54: PDF resource Create Publish Edit delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 55)
	public static void CreatepublisheditdeltePPT(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			String PPT_file = "";
			String folder = "";
			folder = Pfs_portal.getFolderPath();
			PPT_file = folder + "\\demo.pptx";

			System.out.println("TC-55:  Create PPT resource publish Edit and delete PDF");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");

			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facpptclick, time, "facpptclick", log);
			resourceSubmitForm(faculty, url, driver, log);
			String fileName = "PPT_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			driver.findElement(By.xpath("//input[@accept='.ppt,.pptx']")).sendKeys(PPT_file);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facpptfopen, time, "facpptfopen", log);
			resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facpptfopen, time, "facpdfopen", log);
			resourceEdit(faculty, url, driver, fileName, Role, log);
			driver.findElement(By.xpath("//input[@accept='.ppt,.pptx']"))
					.sendKeys("C:\\Users\\Public\\Documents\\demo1.pptx");
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("window.scrollBy(-200,-200)");
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.facpptfopen, time, "facpdfopen", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-55 PPT resource Create Publish Edit delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-55: PPT resource Create Publish Edit delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 56)
	public static void CreatepublisheditdelteVideo(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			String Video_file = "";
			String folder = "";
			folder = Pfs_portal.getFolderPath();
			Video_file = folder + "\\demo.mp4";

			System.out.println("TC-56:  Create Video resource publish Edit and delete PDF");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");

			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facvideoclick, time, "facvideoclick", log);
			resourceSubmitForm(faculty, url, driver, log);
			String fileName = "Video_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			driver.findElement(By.xpath("//input[@accept='.mp4']"))
					.sendKeys(Video_file);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facvideoopen, time, "facvideoopen", log);
			resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facvideoopen, time, "facpdfopen", log);
			resourceEdit(faculty, url, driver, fileName, Role, log);
			driver.findElement(By.xpath("//input[@accept='.mp4']"))
					.sendKeys("C:\\Users\\Public\\Documents\\demo1.mp4");
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("window.scrollBy(-200,-200)");
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.facvideoopen, time, "facpdfopen", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-56: Video resource Create Publish Edit delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-56: Video resource Create Publish Edit delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 57)
	public static void CreatepublisheditdelteSpreadsheet(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			String SpreadSheetFile = "";
			String folder = "";
			folder = Pfs_portal.getFolderPath();
			SpreadSheetFile = folder + "\\demo.xlsx";

			System.out.println("TC-57:  Create Spreadsheet resource publish Edit and delete PDF");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			Utils.smallSleepBetweenClicks(1);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");
			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.facssclick, time, "facssclick", log);
			resourceSubmitForm(faculty, url, driver, log);

			String fileName = "Excel_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			driver.findElement(By.xpath("//input[@accept='.xlsx,.xls']")).sendKeys(SpreadSheetFile);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			Utils.smallSleepBetweenClicks(1);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facssopen, time, "Click on SS SVG", log);

			resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);

			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.facssopen, time, "facssopen", log);
			resourceEdit(faculty, url, driver, fileName, Role, log);
			driver.findElement(By.xpath("//input[@accept='.xlsx,.xls']"))
					.sendKeys("C:\\Users\\Public\\Documents\\demo1.xlsx");
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("window.scrollBy(-200,-200)");
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.facssopen, time, "facssopen", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-57 Spreadsheet resource Create Publish Edit delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-57: Spreadsheet resource Create Publish Edit delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 58)
	public static void CreatepublisheditdelteLink(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			String PDF_file = "";
			String folder = "";
			folder = Pfs_portal.getFolderPath();
			PDF_file = folder + "\\demo.pdf";

			System.out.println("TC-58:  Create Link resource publish Edit and delete PDF");
			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			String program, subject;
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			Utils.clickXpath(driver, ActionXpath.programselect, time, "click on program select", log);
			program = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[1]", log, "program");
			Utils.clickXpath(driver, ActionXpath.course, time, "click on subject", log);
			Utils.clickXpath(driver, ActionXpath.courseselect, time, "click on select subject", log);
			subject = Utils.getTEXT(driver, "(//*[. and @aria-haspopup='listbox'])[2]", log, "subject");

			Utils.clickXpath(driver, ActionXpath.facccres, time, "facccres", log);
			Utils.clickXpath(driver, ActionXpath.faclinkclick, time, "faclinkclick", log);
			resourceSubmitForm(faculty, url, driver, log);
			String fileName = "Link_" + Utils.generateRandom();
			Utils.callSendkeys(driver, ActionXpath.facpptname, fileName, time, log);
			Utils.callSendkeys(driver, ActionXpath.faclinkexternal, url, time, log);
			Utils.scrollUpOrDown(driver, time);
			Utils.scrollUpOrDown(driver, time);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);

			if (Utils.publishlink(url)) {
				Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
				driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
				Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
				Utils.smallSleepBetweenClicks(2);
				driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
				Utils.clickXpath(driver, ActionXpath.viewlink, time, "faclinkopen", log);
				resourcePublishAndLogout(faculty, url, driver, fileName, Role, log);
			} else {
				Utils.logout(driver, url, Role, log);
			}

			resourceFacultyInitialSteps(faculty, url, driver, log, csvCell);
			Utils.clickXpath(driver, ActionXpath.program, time, "click on program", log);
			driver.findElement(By.xpath("//li[@data-value='" + program + "']")).click();
			Utils.clickXpath(driver, ActionXpath.subject, time, "click on subject", log);
			Utils.smallSleepBetweenClicks(2);
			driver.findElement(By.xpath("//li[text()='" + subject + "']")).click();
			Utils.clickXpath(driver, ActionXpath.viewlink, time, "viewlink", log);
			Utils.clickXpath(driver, "//p[.='" + fileName + "']/../../.././..//*[local-name()='svg']", time,
					"Select PPT file name", log);
			Utils.clickXpath(driver, ActionXpath.facpdfedit, time, "Click on edit button", log);
			Utils.cleartext(driver, ActionXpath.faclinkexternal);
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("window.scrollBy(-200,-200)");
			Utils.callSendkeys(driver, ActionXpath.faclinkexternal, "WWW.Google.com", time, log);
			Utils.clickXpath(driver, ActionXpath.facccressubmit, time, "facccressubmit", log);
			Utils.clickXpath(driver, ActionXpath.facccressubmityes, time, "facccressubmityes", log);
			Utils.clickXpath(driver, ActionXpath.viewlink, time, "viewlink", log);
			resourceDeleteAndLogout(faculty, url, driver, fileName, Role, log);
			log.info("TC-58: Link resource Create Publish Edit delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-58: Link resource Create Publish Edit delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 76)
	public static void DeleteResourcesPPT(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-76: PPT Delete Test case Started");
			String Prefix = "PPT_";
			String xpath = ActionXpath.facpptfopen;
			resorcesdelete(faculty, url, driver, csvCell, Role, log, Prefix, xpath);
			log.info("TC-76: PPT Delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-76: PPT Delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 77)
	public static void DeleteResourcesExcel(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-77:  Excel Delete test case started");
			String Prefix = "Excel_";
			String xpath = ActionXpath.facssopen;
			resorcesdelete(faculty, url, driver, csvCell, Role, log, Prefix, xpath);
			log.info("TC-77: Excel Delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-77: Excel Delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 78)
	public static void DeleteResourcesPDF(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-78:  PDF Delete test case started");
			String Prefix = "PDF_";
			String xpath = ActionXpath.facpdfopen;
			resorcesdelete(faculty, url, driver, csvCell, Role, log, Prefix, xpath);
			log.info("TC-78: PDF Delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-78: PDF Delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 79)
	public static void DeleteResourcesVideo(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-79:  Video Delete test case started");
			String Prefix = "Video_";
			String xpath = ActionXpath.facvideoopen;
			resorcesdelete(faculty, url, driver, csvCell, Role, log, Prefix, xpath);
			log.info("TC-79: Video Delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-79: Video Delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

	@Test(priority = 80)
	public static void DeleteResourcesLink(String student, String faculty,
			String url, String Browser, String Role, WebDriver driver, Logger log, String[] csvCell) throws Exception {
		try {
			System.out.println("TC-80:  Link Delete Test case started");
			String Prefix = "Link_";
			String xpath = ActionXpath.viewlink;
			resorcesdelete(faculty, url, driver, csvCell, Role, log, Prefix, xpath);
			log.info("TC-80: Link Delete Test Case PASSED");
		} catch (Exception e) {
			Utils.printException(e);
			log.warning("TC-80: Link Delete Test Case FAILED");
			Utils.logout(driver, url, Role, log);
		}
	}

}
