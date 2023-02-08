package com.ken42;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

import org.testng.annotations.Test;

public class Pfs_faculty {
	public static Logger log = Logger.getLogger("Pfs_portal");
	static int time = 2000;

	@Test(priority = 17)
	public static void testFaculty(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println("TC-17:  Starting FACULTY PORTAL Academic tab test case executation\n");
			Utils.goBackToHome(driver, url, log);
			Utils.bigSleepBetweenClicks(1);
			WebElement l = driver.findElement(By.tagName("body"));
			String p = l.getText();
			if (p.contains("Students") && p.contains("Classes Conducted")
					&& p.contains("Assignments") && p.contains("Schedule") && p.contains("MY CLASSES")) {
				log.info(" TC-17: Faculty Home tab test case PASSED");
			} else {
				log.warning(" TC-17: Faculty Home tab test case FAILED it does not contain all the tabs\n\n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Thread.sleep(time);
			log.warning("TC-17: Faculty ACADEMIC Test case FAILED \n");
		}
	}

	@Test(priority = 18)
	public static void testFacultyQuestionBank(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println(" TC-18:  Faculty Starting QuestionBank Tab test case Executation");
			Utils.goBackToHome(driver, url, log);
			if (Utils.checkLtsta(url)) {
				Utils.clickXpath(driver, ActionXpath.facClickacademicsltsta, time, "open the span on Academics", log);
			} else {
				Utils.clickXpath(driver, ActionXpath.openFacdevnosbm, time, "open the acadmics for nsom & bmtech", log);
			}
			Utils.clickXpath(driver, ActionXpath.facqb, time, "click  the Question bank", log);
			Utils.bigSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facaddque, time, "clcik on the add Question manualy", log);
			Utils.bigSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facqueback, time, "go back", log);
			log.info("TC-18 : Faculty QuestionBank click BACK button Test Case PASSED \n ");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-18: Faculty QuestionBank click BACK button Test Case FAILED \n");
		}
	}

	@Test(priority = 19)
	public static void testFacultyCourseContent(String url, WebDriver driver, Logger log) throws Exception {
		try {
			Utils.goBackToHome(driver, url, log);
			System.out.println("TC-19: Faculty Course Content Test Execution  Started ");
			if (Utils.checkLtsta(url)) {
				Utils.clickXpath(driver, ActionXpath.facClickacademicsltsta, time, "open academics sapn on the ltsta",
						log);
			} else {
				Utils.clickXpath(driver, ActionXpath.openFacltsta, time, "open span on acadmics on the ltsta", log);
			}
			Utils.clickXpath(driver, ActionXpath.faccc, time, "click on the Course content", log);
			Utils.clickXpath(driver, ActionXpath.facccactivity, time, "clck on activity button ", log);
			Utils.clickXpath(driver, ActionXpath.facassessmentrelative, time,
					"select the activity option named was fourm", log);
			Utils.clickXpath(driver, ActionXpath.facaddactivityrelative, time, "click to add fourm", log);
			Utils.clickXpath(driver, ActionXpath.facccAsscancel, time, "cancel it ", log);
			Utils.clickXpath(driver, ActionXpath.faccAssYes, time, "confirm to cancel", log);
			Utils.clickXpath(driver, ActionXpath.faccc, time, "click on the course content", log);
			Utils.clickXpath(driver, ActionXpath.FaccClickResource, time, "Click Resource", log);
			Utils.clickXpath(driver, ActionXpath.facrescancel, time, "cancel the resources", log);
			log.info("TC-19:  Faculty Course Content Test PASSED \n");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-19:  Faculty Course Content Test FAILED \n");
		}
	}

	@Test(priority = 20)
	public static void testFacultyExamination(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println("TC-20:     Faculty Examination Test Executation Statred");
			Utils.goBackToHome(driver, url, log);
			if (Utils.checkUrlToSkipTest(url)) {
				log.info("TC_20 Faculty Examination Skipping this test as this is not applicable for this portal\n\n");
				return;
			}
			Utils.clickXpath(driver, ActionXpath.facexam, time, "Click on the Examination span", log);
			// Utils.clickXpath(driver, ActionXpath.facexamarrow, time, "facexamarrow");
			// Utils.clickXpath(driver, ActionXpath.facexamdropdown, time, "Examination
			// naroow dropdown");
			// Utils.clickXpath(driver, ActionXpath.facexamexam, time, "facexamexam");
			// Utils.clickXpath(driver, ActionXpath.facexamdate, time, "facexamdate");
			// Utils.clickXpath(driver, ActionXpath.faceexamtime, time, "faceexamtime");
			log.info("TC-20: Faculty Examanation test cases PASSED... \n ");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-20: Faculty Examanation test cases FAILED \n");
		}
	}

	@Test(priority = 21)
	public static void testFacultyMYStudent(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println("TC-21:   Faculty My Students Test Executation Started");
			Utils.goBackToHome(driver, url, log);
			if (Utils.checkLtsta(url)) {
				Utils.clickXpath(driver, ActionXpath.faccMyStudentltsta, time, "open the my student on ltsta", log);
			} else {
				Utils.clickXpath(driver, ActionXpath.faccMyStudent, time,
						"open the commom for all portal expect ltsta", log);
			}
			Utils.bigSleepBetweenClicks(2);
			WebElement l = driver.findElement(By.tagName("body"));
			String p = l.getText();
			// log.info(p);
			// System.out.println(p);
			if (p.contains("My Students") && p.contains("Subjects")) {
				log.info(" TC-21: Faculty My Student  tab test case PASSED \n\n");
			} else {
				log.warning(" TC-21: Faculty My Student  tab test case FAILED it does not contain all the tabs\n\n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-21: Faculty My Student  tab test case FAILED \n");
		}
	}

	@Test(priority = 22)
	public static void testFacultyAttendance(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println("TC-22 :    Faculty Attendance Test Executation Startred ");
			Utils.goBackToHome(driver, url, log);
			if (Utils.checkLtsta(url)) {
				Utils.clickXpath(driver, ActionXpath.facattendanceforltsta, time, "click Attendance tab", log);
			} else {
				Utils.clickXpath(driver, ActionXpath.facatt, time, "click Attendance tab", log);
			}
			Utils.clickXpath(driver, ActionXpath.faccAttendahis, time, "Click attendance history", log);
			log.info("TC-22 : Faculty Attendance Test Executation PASSED \n ");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-22 : Faculty Attendance Test case FAILED \n");
		}
	}

	@Test(priority = 23)
	public static void testFaculityTimetable(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println("TC-23 :    Faculty Timetable Test Executation Started ");
			Utils.goBackToHome(driver, url, log);
			if (Utils.checkLtsta(url)) {
				Utils.clickXpath(driver, ActionXpath.facClickTimetableltsta, time, "facClickTimetable", log);
			} else {
				Utils.clickXpath(driver, ActionXpath.facClickTimetable, time, "facClickTimetable", log);
			}
			Utils.clickXpath(driver, ActionXpath.facttmonth, time, "facttmonth", log);
			// Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facttweek, time, "facttweek", log);
			// Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facttday, time, "facttday", log);
			// Utils.smallSleepBetweenClicks(1);
			log.info("TC-23 : Faculty Timetable test case PASSED \n ");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-23 : Faculty Timetable test case FAILED \n");
		}
	}

	@Test(priority = 24)
	public static void testFacultyService(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println("TC-24:  Faculty Service Test case Started");
			Utils.goBackToHome(driver, url, log);
			// Utils.bigSleepBetweenClicks(1);
			Utils.clickOnFacultyService(driver, url, log);
			Utils.smallSleepBetweenClicks(1);
			if (Utils.checknsom(url)) {
				Utils.clickXpath(driver, ActionXpath.FacRaisequerybutton, time, "Click on Raise case button", log);
			} else {
				Utils.clickXpath(driver, ActionXpath.FacRaisebutton, time, "Click on Raise case button", log);
			}
			Utils.smallSleepBetweenClicks(1);
			// Utils.scrollUpOrDown(driver, 300);
			// Utils.clickXpath(driver, ActionXpath.facCancelSer, time, "cancel");
			Utils.smallSleepBetweenClicks(1);
			log.info("TC-24: Faculty Service test cancel button Test case PASSED \n ");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-24: Faculty Service test cancel button Test case FAILED \n");
		}
	}

	@Test(priority = 25)
	public static void testFacultyRaiseCase(String student, String faculty, String url, WebDriver driver, Logger log)
			throws Exception {
		try {
			System.out.println("TC-25 Faculty Service Raise A Case ");
			Utils.goBackToHome(driver, url, log);
			Utils.clickOnFacultyService(driver, url, log);
			Utils.smallSleepBetweenClicks(1);
			if (Utils.checknsom(url)) {
				Utils.clickXpath(driver, ActionXpath.FacRaisequerybutton, time, "Click on Raise case button", log);
			} else {
				Utils.clickXpath(driver, ActionXpath.FacRaisebutton, time, "Click on Raise case button", log);
			}
			Utils.smallSleepBetweenClicks(1);
			if (Utils.raisecase(url)) {
				Utils.clickXpath(driver, ActionXpath.Raisebutton, time, "Raise case button", log);
			}
			Utils.callSendkeys(driver, ActionXpath.inputSub, "Regd Error on Inviligation Secation", time, log);
			Utils.callSendkeys(driver, ActionXpath.FacDesc,
					"while i need to regd on the inviligation section m unable to do bcz its showing the system admin Error Sever not availbale 404 error.",
					time, log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.SubmitRaise, time, "Submit the case", log);
			log.info("TC-25: Faculty service Status  Raise Case PASSED \n");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-25: Faculty service Status  Raise Case FAILED \n");
		}
	}

	@Test(priority = 26)
	public static void testFacultyMakeRequest(String student, String faculty, String url, WebDriver driver, Logger log)
			throws Exception {
		try {
			System.out.println("TC-26: Starting Faculty make a request test case");
			Utils.goBackToHome(driver, url, log);
			Utils.clickOnFacultyService(driver, url, log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facMakeRButtondevNsome, time, "Click on Make a request button", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facMakeReqButtonSecond, time, "Click on Second Make a request button",
					log);
			Utils.smallSleepBetweenClicks(1);
			Utils.callSendkeys(driver, ActionXpath.makeSubjectIn, "5 days Leave Request ", time, log);
			Utils.callSendkeys(driver, ActionXpath.makedesc,
					"hi ...i want to take the 5 days leave bcz of some helath issue  m not availbe on this days some medical emergency plz approved my rqst... Thanks & regards Aditya .",
					time, log);
			Utils.clickXpath(driver, ActionXpath.MakeBtn, time, "Submit the Request", log);
			Utils.smallSleepBetweenClicks(1);
			log.info("TC-26: Faculty service Make a request PASSED \n");

		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-26: Faculty service make a request Case FAILED \n");
		}
	}

	@Test(priority = 27)
	public static void testFacultyEvent(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println("TC-27: Faculty Portal Event Tab Test case Started");
			Utils.goBackToHome(driver, url, log);
			if (Utils.checkLtsta(url)) {
				Utils.clickXpath(driver, ActionXpath.faccEventltsta, time, "facEvent", log);
			} else {
				Utils.clickXpath(driver, ActionXpath.faccEvent, time, "facEvent", log);
			}
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.faceventlocation, time, "faceventlocation", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.faceventlocationselect, time, "faceventlocationselect", log);
			Utils.callSendkeys(driver, ActionXpath.FaccSearch, "Ganesh", time, log);
			log.info("TC-27: Faculty Event Test case Executation PASSED \n");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-27: Faculty Event Test case FAILED \n");
		}
	}

	@Test(priority = 28)
	public static void testfacultyEditProfile(String student, String faculty, String url, WebDriver driver, Logger log)
			throws Exception {
		try {

			System.out.println(" TC-28:   Faculty Starting PersonalDetails Started  case executation");

			if (Utils.skipthefacultyprofile(url)) {
				log.info(
						"TC-28 Skip Faculty Profile \n\n");
				return;

			}
			Utils.goBackToHome(driver, url, log);

			Utils.clickXpath(driver, ActionXpath.singinintial, time, "Select faculty initial icon", log);
			Utils.clickXpath(driver, ActionXpath.faccprofile, time, "click on profile", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.testfaculty, time, "testfaculty", log);
			Utils.smallSleepBetweenClicks(1);
			// Utils.clickXpath(driver, ActionXpath.facpdedit, time, "facpdedit", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.faccgender, time, "faccgender", log);
			Utils.clickXpath(driver, ActionXpath.faccselgender, time, "faccselgender", log);
			Utils.callSendkeys(driver, ActionXpath.facpddob, "02-02-2020", time, log);
			Utils.bigSleepBetweenClicks(1);
			Utils.cleartext(driver, ActionXpath.facpdnationality);
			Utils.callSendkeys(driver, ActionXpath.facpdnationality, "INDIA", time, log);
			Utils.cleartext(driver, ActionXpath.faccph);
			Utils.callSendkeys(driver, ActionXpath.faccph, "7010195163", time, log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.faccadd, time, "faccadd", log);
			Utils.clickXpath(driver, ActionXpath.facdpsave, time, "facdpsave", log);
			//// Utils.bigSleepBetweenClicks(1);
			log.info("  TC-28: Faculty Starting PersonalDetails Completed test case PASSED  \n");

		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-28: Faculty Starting PersonalDetails test case FAILED \n");
		}
	}

	@Test(priority = 29)
	public static void testfacultyEditAddress(String student, String faculty, String url, WebDriver driver, Logger log)
			throws Exception {
		try {
			System.out.println(" TC-29 :   Faculty Faculty Edit Address Started  case executation");

			System.out.println(" TC-29:   Faculty Starting PersonalDetails Started  case executation");

			if (Utils.skipthefacultyprofile(url)) {
				log.info(
						"TC-29 Skip Faculty Edit Address Faculty Profile \n\n");
				return;

			} else {

				Utils.goBackToHome(driver, url, log);

				// Utils.goBackToHome(driver, url, log);
				Utils.clickXpath(driver, ActionXpath.FCCportal, time, "facSelectPrtoSignout", log);
				Utils.clickXpath(driver, ActionXpath.faccProfile, time, "facprofile", log);
				Utils.clickXpath(driver, ActionXpath.address, time, "addressdetais", log);
				Utils.clickXpath(driver, ActionXpath.facdpaddedit, time, "facdpaddedit", log);
				Utils.clickXpath(driver, ActionXpath.facdptype, time, "facdptype", log);
				Utils.clickXpath(driver, ActionXpath.FaccfaccTypeSelect, time, "facdptypeselect", log);
				Utils.callSendkeys(driver, ActionXpath.faccAddress, "Coimbatore", time, log);
				Utils.callSendkeys(driver, ActionXpath.faccPincode, "600001", time, log);
				Utils.clickXpath(driver, ActionXpath.facccountry, time, "facdpcountry", log);
				Utils.clickXpath(driver, ActionXpath.faccSelectCountry2, time, "facdpcountrysselect", log);

				Utils.clickXpath(driver, ActionXpath.facccountry, time, "facdpcountry", log);
				Utils.clickXpath(driver, ActionXpath.faccSelectCountry, time, "facdpcountrysselect", log);

				Utils.clickXpath(driver, ActionXpath.faccstate, time, "faccstate", log);
				Utils.clickXpath(driver, ActionXpath.faccSelectState2, time, "faccSelectState", log);
				Utils.clickXpath(driver, ActionXpath.faccstate, time, "faccstate", log);
				Utils.clickXpath(driver, ActionXpath.faccSelectState, time, "faccSelectState", log);

				Utils.clickXpath(driver, ActionXpath.faccCity, time, "faccCity", log);
				Utils.clickXpath(driver, ActionXpath.faccSelectCity, time, "faccSelectCity", log);

				Utils.clickXpath(driver, ActionXpath.faccSaveaddress, time, "facdpaddsave", log);
				//// Utils.bigSleepBetweenClicks(1);
				log.info(" TC-29: Faculty edit Address Details Completed test case PASSED  \n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-29: Faculty edit Address Details test case FAILED \n");
		}
	}

	@Test(priority = 30)
	public static void testfacultyEditAcademicDetails(String student, String faculty, String url, WebDriver driver,
			Logger log) throws Exception {
		try {

			System.out.println(" TC-30:   Faculty Starting Edit Academic Details Started  case executation");

			if (Utils.skipthefacultyprofile(url)) {
				log.info(
						"TC-30 Skip Edit Details Faculty Profile \n\n");
				return;

			} else {
				Utils.goBackToHome(driver, url, log);

				// Utils.goBackToHome(driver, url, log);
				Utils.smallSleepBetweenClicks(2);
				Utils.clickXpath(driver, ActionXpath.FCCportal, time, "facSelectPrtoSignout", log);
				Utils.clickXpath(driver, ActionXpath.faccProfile, time, "facprofile", log);
				Utils.clickXpath(driver, ActionXpath.faccacadmics, time, "faccacadmics", log);
				Utils.smallSleepBetweenClicks(2);
				boolean addrow = false;
				addrow = driver.findElements(By.xpath("(//*[text()='Add Row'])")).size() > 0;
				if (addrow) {
					Utils.clickXpath(driver, ActionXpath.faccaddrow, time, "faccaddrow", log);
					Utils.clickXpath(driver, ActionXpath.facdplevel, time, "facdplevel", log);
					Utils.clickXpath(driver, ActionXpath.facdplevelselect, time, "facdplevelselect", log);
					Utils.clickXpath(driver, ActionXpath.facdpadcountry, time, "facdpadcountry", log);
					Utils.clickXpath(driver, ActionXpath.facdpadcountryselect, time, "facdpadcountryselect", log);
					Utils.callSendkeys(driver, ActionXpath.facdpaduniversity, "ANNA", time, log);
					Utils.callSendkeys(driver, ActionXpath.facdpadyear, "2020", time, log);

				} else {
					Utils.clickXpath(driver, ActionXpath.facdplevel, time, "facdplevel", log);
					Utils.clickXpath(driver, ActionXpath.facdplevelselect, time, "facdplevelselect", log);
					Utils.clickXpath(driver, ActionXpath.facdpadcountry, time, "facdpadcountry", log);
					Utils.clickXpath(driver, ActionXpath.facdpadcountryselect, time, "facdpadcountryselect", log);
					Utils.callSendkeys(driver, ActionXpath.facdpaduniversity, "ANNA", time, log);
					Utils.callSendkeys(driver, ActionXpath.facdpadyear, "2020", time, log);

				}
				Utils.smallSleepBetweenClicks(1);
				Utils.clickXpath(driver, ActionXpath.faccsave, time, "faccsave", log);

				Utils.smallSleepBetweenClicks(1);
				log.info(" TC-30 : Academic Details Completed test case PASSED  \n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-30 : Academic Details test case FAILED \n");
		}
	}

	@Test(priority = 31)
	public static void testfacultyEditEXPERIENCE(String student, String faculty, String url, WebDriver driver,
			Logger log) throws Exception {
		try {

			System.out.println(" TC-31: Faculty Starting Edit Experience Started  case executation");

			if (Utils.skipthefacultyprofile(url)) {
				log.info(
						"TC-31 Skip Edit Experience Faculty Profile \n\n");
				return;

			} else {

				Utils.goBackToHome(driver, url, log);

				System.out.println(" TC:31 :  Faculty Starting Edit Experience Started  case executation");
				Utils.goBackToHome(driver, url, log);
				Utils.clickXpath(driver, ActionXpath.FCCportal, time, "facclickonT", log);
				//// Utils.bigSleepBetweenClicks(1);
				Utils.clickXpath(driver, ActionXpath.facclickonprofile, time, "facclickonprofile", log);
				Utils.smallSleepBetweenClicks(1);
				Utils.clickXpath(driver, ActionXpath.faccexpe, time, " faccexpe", log);
				// Utils.clickXpath(driver, ActionXpath.facdpreedit, time, "facdpreedit", log);
				// Utils.clickXpath(driver, ActionXpath.facdpreadd, time, "facdpreadd", log);
				Utils.smallSleepBetweenClicks(1);
				Utils.scrollUpOrDown(driver, 300);
				Utils.callSendkeys(driver, ActionXpath.faccinst, "IIT", time, log);
				Utils.callSendkeys(driver, ActionXpath.faccposition, "teching", time, log);
				Utils.callSendkeys(driver, ActionXpath.faccrole, "techer", time, log);
				Utils.callSendkeys(driver, ActionXpath.faccduration, "10", time, log);
				Utils.callSendkeys(driver, ActionXpath.faccdecs, "professor", time, log);
				Utils.clickXpath(driver, ActionXpath.faccsave1, time, "faccsave2", log);
				Utils.smallSleepBetweenClicks(2);
				// Utils.callSendkeys(driver, ActionXpath.faccexpeindu, "IITbangalugru", time,
				// log);
				Utils.callSendkeys(driver, ActionXpath.faccinstindu, "IITbangalugru", time, log);
				Utils.callSendkeys(driver, ActionXpath.faccpositionindu, "techer", time, log);
				Utils.callSendkeys(driver, ActionXpath.faccroleindu, "11", time, log);
				Utils.callSendkeys(driver, ActionXpath.faccdurationindu, "11", time, log);
				Utils.clickXpath(driver, ActionXpath.faccsave2indu, time, "faccsave2", log);

				Utils.clickXpath(driver, ActionXpath.faccpacco, time, "faccpacco", log);
				Utils.callSendkeys(driver, ActionXpath.proname, "11", time, log);
				Utils.callSendkeys(driver, ActionXpath.prolink, "https://portal-dev.ken42.com", time, log);
				Utils.callSendkeys(driver, ActionXpath.prolink, "sample", time, log);
				Utils.clickXpath(driver, ActionXpath.prosave, time, "prosave", log);

				log.info(" TC-31:  Faculty Starting Edit Experience Started  test case PASSED  \n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-31 :Faculty Starting Edit Experience Started  test case test case FAILED \n");
		}
	}

	@Test(priority = 32)
	public static void testfacultyEditRESEARCHPublication(String student, String faculty, String url, WebDriver driver,
			Logger log) throws Exception {
		try {

			System.out.println(" TC-32:   Faculty Starting Edit Research Started  case executation");

			if (Utils.skipthefacultyprofile(url)) {
				log.info(
						"TC-32 Edit Research Skip Faculty Profile \n\n");
				return;

			} else {
				Utils.goBackToHome(driver, url, log);

				System.out.println(" TC-32 : RESEARCH PUBLICATION Started  case executation");
				Utils.goBackToHome(driver, url, log);
				Utils.clickXpath(driver, ActionXpath.FCCportal, time, "facSelectPrtoSignout", log);
				Utils.clickXpath(driver, ActionXpath.faccProfile, time, "facprofile", log);
				// Utils.clickXpath(driver, ActionXpath.resechersvg,
				// time,"facclickonRESEARCHSUPERVISIONpublish", log);
				Utils.scrollUpOrDown(driver, 300);
				Utils.clickXpath(driver, ActionXpath.resecher, time, "resecher", log);
				Utils.clickXpath(driver, ActionXpath.resechersvg, time, "resechersvg", log);
				Utils.callSendkeys(driver, ActionXpath.resname, "Surya", time, log);
				Utils.callSendkeys(driver, ActionXpath.rechelink, "https://portal-dev.ken42.com", time, log);
				Utils.callSendkeys(driver, ActionXpath.recherdec, "Sample Desc", time, log);
				Utils.smallSleepBetweenClicks(1);
				Utils.clickXpath(driver, ActionXpath.rechpub, time, "rechpub", log);
				Utils.smallSleepBetweenClicks(1);
				boolean addrow1 = false;
				addrow1 = driver.findElements(By.xpath("(//*[text()='Add Row'])")).size() > 0;
				if (addrow1) {
					Utils.clickXpath(driver, ActionXpath.recshadd, time, "recshadd", log);
					Utils.callSendkeys(driver, ActionXpath.rechnam, "name", time, log);
					Utils.callSendkeys(driver, ActionXpath.rechlink, "https://portal-dev.ken42.com", time, log);
					Utils.callSendkeys(driver, ActionXpath.rechdecs, "sample", time, log);
					Utils.clickXpath(driver, ActionXpath.rechsave, time, "rechsave", log);

				} else {
					Utils.callSendkeys(driver, ActionXpath.rechnam, "publication", time, log);
					Utils.callSendkeys(driver, ActionXpath.rechlink, "https://portal-dev.ken42.com", time, log);
					Utils.callSendkeys(driver, ActionXpath.rechdecs, "sample", time, log);
					Utils.clickXpath(driver, ActionXpath.rechsave, time, "rechsave", log);

				}
				Utils.smallSleepBetweenClicks(1);
				boolean addrow2 = false;
				addrow2 = driver.findElements(By.xpath("(//*[text()='Add Row'])")).size() > 0;
				if (addrow1) {
					Utils.callSendkeys(driver, ActionXpath.rechnam, "publication", time, log);
					Utils.callSendkeys(driver, ActionXpath.rechlink, "https://portal-dev.ken42.com", time, log);
					Utils.callSendkeys(driver, ActionXpath.rechdecs, "sample", time, log);
					Utils.clickXpath(driver, ActionXpath.rechsave, time, "rechsave", log);

				} else {
					Utils.callSendkeys(driver, ActionXpath.rechnam, "publication", time, log);
					Utils.callSendkeys(driver, ActionXpath.rechlink, "https://portal-dev.ken42.com", time, log);
					Utils.callSendkeys(driver, ActionXpath.rechdecs, "sample", time, log);
					Utils.clickXpath(driver, ActionXpath.rechsave, time, "rechsave", log);
				}
				Utils.clickXpath(driver, ActionXpath.reschconfrence, time, "reschbook", log);
				Utils.callSendkeys(driver, ActionXpath.nameconfrence, "bookname", time, log);
				Utils.clickXpath(driver, ActionXpath.confrencelink, time, "booklink", log);
				Utils.clickXpath(driver, ActionXpath.confrencedecsti, time, "bookdecsti", log);
				Utils.clickXpath(driver, ActionXpath.confrencesave, time, "booksave", log);

				Utils.clickXpath(driver, ActionXpath.reschbook, time, "reschbook", log);
				Utils.callSendkeys(driver, ActionXpath.bookname, "bookname", time, log);
				Utils.clickXpath(driver, ActionXpath.booklink, time, "booklink", log);
				Utils.clickXpath(driver, ActionXpath.bookdecsti, time, "bookdecsti", log);
				Utils.clickXpath(driver, ActionXpath.booksave, time, "booksave", log);
				log.info("  TC-32: Faculty edit RESEARCH PUBLICATION  test case PASSED  \n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-32: Faculty edit RESEARCH PUBLICATION test case FAILED \n");
		}
	}

	@Test(priority = 33)
	public static void testfacultyEditOTHERS(String student, String faculty, String url, WebDriver driver,
			Logger log) throws Exception {
		try {

			System.out.println(" TC-33: Faculty Starting Faculty Edid Others Started  case executation");

			if (Utils.skipthefacultyprofile(url)) {
				log.info(
						"TC-33 Faculty Edid Others Skip Faculty Profile \n\n");
				return;

			} else {

				Utils.goBackToHome(driver, url, log);
				System.out.println(" TC-33 :   Faculty edit Conference Started  case executation");
				Utils.goBackToHome(driver, url, log);
				Utils.clickXpath(driver, ActionXpath.FCCportal, time, "facSelectPrtoSignout", log);
				Utils.clickXpath(driver, ActionXpath.faccProfile, time, "facprofile", log);
				Utils.scrollUpOrDown(driver, 300);
				Utils.clickXpath(driver, ActionXpath.others, time, "others", log);
				Utils.clickXpath(driver, ActionXpath.otherssvg, time, "otherssvg", log);
				// Utils.clickXpath(driver, ActionXpath.facdpreaddconfernece, time,
				// "facdpreaddconfernece", log);
				Utils.callSendkeys(driver, ActionXpath.othername, "Sample", time, log);
				Utils.callSendkeys(driver, ActionXpath.otherlink, "https://portal-dev.ken42.com", time, log);
				Utils.callSendkeys(driver, ActionXpath.othersdes, "Sample Desc", time, log);
				Utils.clickXpath(driver, ActionXpath.othersave, time, "othersave", log);
				Utils.smallSleepBetweenClicks(1);
				log.info(" TC-33 : Faculty edit Conference Completed test case PASSED \n\n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			Thread.sleep(time);
			log.warning("TC-33 : Conference test case FAILED \n\n");
		}
	}

	@Test(priority = 34)
	public static void testfacultyEditBook(String student, String faculty, String url, WebDriver driver, Logger log)
			throws Exception {
		try {
			if (Utils.skipthefacultyprofile(url)) {
				log.info(
						"TC-34 Faculty Edit book Skip Faculty Profile \n\n");
				return;

			} else {
				System.out.println(" TC:34 :   Book Started  case executation");
				Utils.goBackToHome(driver, url, log);
				Utils.clickXpath(driver, ActionXpath.FCCportal, time, "facSelectPrtoSignout", log);
				Utils.clickXpath(driver, ActionXpath.faccProfile, time, "facprofile", log);
				Utils.smallSleepBetweenClicks(1);
				Utils.clickXpath(driver, ActionXpath.facdpbook, time, "facdpbook", log);
				Utils.smallSleepBetweenClicks(1);
				Utils.clickXpath(driver, ActionXpath.facdpbookedit, time, "facdpbookedit", log);
				Utils.smallSleepBetweenClicks(1);
				Utils.clickXpath(driver, ActionXpath.facdpbookadd, time, "facdpbookadd", log);
				Utils.smallSleepBetweenClicks(1);
				Utils.callSendkeys(driver, ActionXpath.facdpbookname, "Sample", time, log);
				Utils.callSendkeys(driver, ActionXpath.facdpbooklink, "https://portal-dev.ken42.com", time, log);
				Utils.callSendkeys(driver, ActionXpath.facdpbookdesc, "Sample Desc", time, log);
				Utils.clickXpath(driver, ActionXpath.faccSaveNsombm, time, "faccSaveNsombm", log);
				Utils.bigSleepBetweenClicks(1);
				log.info(" TC-34: Faculty edit Book Completed test case PASSED  \n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-34: Faculty edit Book test case FAILED \n");
		}
	}

	@Test(priority = 35)
	public static void testfacultyEditProfessionalAssociation(String student, String faculty, String url,
			WebDriver driver, Logger log) throws Exception {
		try {
			if (Utils.skipthefacultyprofile(url)) {
				log.info(
						"TC-35 Faculty ProfessionalAssociation Skip Faculty Profile \n\n");
				return;

			} else {
				Utils.goBackToHome(driver, url, log);
				System.out.println(" TC-35 :   Professional Association Started  case executation");
				Utils.clickXpath(driver, ActionXpath.FCCportal, time, "facSelectPrtoSignout", log);
				Utils.clickXpath(driver, ActionXpath.faccProfile, time, "facprofile", log);
				Utils.clickXpath(driver, ActionXpath.facdpprof, time, "facdpprof", log);
				Utils.clickXpath(driver, ActionXpath.facdpprofedit, time, "facdpprofedit", log);
				Utils.clickXpath(driver, ActionXpath.facdpprofadd, time, "facdpprofadd", log);
				Utils.callSendkeys(driver, ActionXpath.facdpprofname, "Sample", time, log);
				Utils.callSendkeys(driver, ActionXpath.facdpproflink, "https://portal-dev.ken42.com", time, log);
				Utils.callSendkeys(driver, ActionXpath.facdpprofdesc, "Sample Desc", time, log);
				Utils.clickXpath(driver, ActionXpath.facdpprofsave, time, "facdpprofsave", log);
				Utils.smallSleepBetweenClicks(1);
				log.info("  TC-35 : Faculty profile edit Professional Association Completed test case PASSED..  \n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-35 : Faculty profile edit Professional Association test case FAILED \n\n");
		}
	}

	@Test(priority = 36)
	public static void testfacultyOthers(String student, String faculty, String url, WebDriver driver, Logger log)
			throws Exception {
		try {
			if (Utils.skipthefacultyprofile(url)) {
				log.info(
						"TC-35 Faculty others Skip Faculty Profile \n\n");
				return;

			} else {
				System.out.println(" TC-36 :   Faculty edit Others Started  case executation");
				Utils.goBackToHome(driver, url, log);
				Utils.clickXpath(driver, ActionXpath.FCCportal, time, "facSelectPrtoSignout", log);
				Utils.clickXpath(driver, ActionXpath.faccProfile, time, "facprofile", log);
				Utils.clickXpath(driver, ActionXpath.facdpother, time, "facdpother", log);
				Utils.clickXpath(driver, ActionXpath.facdpotheredit, time, "facdpotheredit", log);
				Utils.clickXpath(driver, ActionXpath.facdpotheradd, time, "facdpotheradd", log);
				Utils.callSendkeys(driver, ActionXpath.facdpothername, "Sample", time, log);
				Utils.callSendkeys(driver, ActionXpath.facdpotherlink, "https://portal-dev.ken42.com", time, log);
				Utils.callSendkeys(driver, ActionXpath.facdpotherdesc, "Sample Desc", time, log);
				Utils.clickXpath(driver, ActionXpath.facdpothersave, time, "facdpothersave", log);
				Utils.smallSleepBetweenClicks(1);
				log.info(" TC-36: Faculty edit Others Completed test case PASSED \n\n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-36 : Faculty edit Others test case FAILED \n\n");
		}
	}

	@Test(priority = 37)
	public static void testFacultyDashboard(String student, String faculty, String url, WebDriver driver, Logger log)
			throws Exception {
		try {
			System.out.println("TC-37 Faculty DASHBOARD test executation \n");
			Utils.goBackToHome(driver, url, log);
			if (Utils.checkLtsta(url)) {
				Utils.clickXpath(driver, ActionXpath.facClickacademicsltsta, time, "open academics sapn on the ltsta",
						log);
			} else {
				Utils.clickXpath(driver, ActionXpath.openFacltsta, time, "open span on acadmics on the ltsta", log);
			}
			Utils.clickXpath(driver, ActionXpath.clickFacDashdevnosbm, time, "Dashboard", log);
			Utils.bigSleepBetweenClicks(1);
			WebElement l = driver.findElement(By.tagName("body"));
			String p = l.getText();
			if (p.contains("Overview") && p.contains("Activities")) {
				log.info(" TC-37: Faculty My Student  tab test case PASSED \n\n");
			} else {
				log.warning(" TC-37: Faculty My Student  tab test case FAILED it does not contain all the tabs\n\n");
			}
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-37: Faculty DASHBOARD  FAILED \n");
		}
	}

	@Test(priority = 38)
	public static void testFacultyQuestionPaper(String student, String faculty, String url, WebDriver driver,
			Logger log) throws Exception {
		try {
			System.out.println("TC-38 Faculty QUESTION PAPER test executation \n");
			Utils.goBackToHome(driver, url, log);

			if (Utils.checkLtsta(url)) {
				Utils.clickXpath(driver, ActionXpath.facClickacademicsltsta, time, "open academics sapn on the ltsta",
						log);
			} else {
				Utils.clickXpath(driver, ActionXpath.openFacltsta, time, "open span on acadmics on the ltsta", log);
			}
			Utils.clickXpath(driver, ActionXpath.facqb, time, "Question ", log);
			Utils.clickXpath(driver, ActionXpath.facaddmanual, time, "Add anual", log);
			Utils.clickXpath(driver, ActionXpath.facquesub, time, "facqueclassselect", log);

			Utils.clickXpath(driver, ActionXpath.facquesubselect, time, "Question Bank Select SUbject ", log);
			Utils.clickXpath(driver, ActionXpath.faccnext, time, "Next", log);
			if (Utils.questionbank(url)) {
				Utils.clickXpath(driver, ActionXpath.facccresdescclick, time, "Click on URL resource link", log);
				Utils.smallSleepBetweenClicks(1);
				Utils.callSendkeys(driver, ActionXpath.facccresurl, "Question", time, log);
				Utils.clickXpath(driver, ActionXpath.facccressubmitform, time, "Save URL link button", log);
				// Utils.callSendkeys(driver, ActionXpath.faccquestion, "Question", time, log);
				if (Utils.questionname(url)) {
					System.out.println("Question name skipped");
				} else {
					Utils.callSendkeys(driver, ActionXpath.faccquestionname, "Question Name", time, log);
				}
				Utils.callSendkeys(driver, ActionXpath.facquestionpurpose, "Question Purpose", time, log);
				Utils.clickXpath(driver, ActionXpath.facquestionlevel, time, "facquestionlevel", log);
				Utils.clickXpath(driver, ActionXpath.faclevelselect, time, "faclevelselect", log);
				Utils.callSendkeys(driver, ActionXpath.facquestionmark, "90", time, log);

			} else {
				Utils.callSendkeys(driver, ActionXpath.faccquestion, "Question", time, log);
				if (Utils.uploadimage(url)) {
					String Image_file = "";
					String folder = "";
					folder = Pfs_portal.getFolderPath();
					Image_file = folder + "\\demo.jpg";
					driver.findElement(By.xpath("//input[@accept='.png,.jpg,.jpeg']"))
							.sendKeys(Image_file);
				} else {
					Utils.callSendkeys(driver, ActionXpath.faccquestionname, "Question time", time, log);

				}
			}
			Utils.cleartext(driver, ActionXpath.faccmarks);
			Utils.smallSleepBetweenClicks(1);
			if (Utils.publishlink(url)) {
				Utils.clickXpath(driver, ActionXpath.Option1Insert1, time, "click on option1", log);
				Utils.callSendkeys(driver, ActionXpath.Qurl1, "test1", time, log);
				Utils.clickXpath(driver, ActionXpath.faccoptionsave1, time, "save option1", log);
				Utils.callSendkeys(driver, ActionXpath.Qfacfeedback1, "Excellent", time, log);
				Utils.clickXpath(driver, ActionXpath.Option2Insert2, time, "click on option2", log);
				Utils.callSendkeys(driver, ActionXpath.Qurl2, "test2", time, log);
				Utils.clickXpath(driver, ActionXpath.faccoptionsave2, time, "save option2", log);
				Utils.callSendkeys(driver, ActionXpath.Qfacfeedback2, "Good", time, log);
				Utils.clickXpath(driver, ActionXpath.Option3Insert3, time, "click on option1", log);
				Utils.callSendkeys(driver, ActionXpath.Qurl3, "test3", time, log);
				Utils.clickXpath(driver, ActionXpath.faccoptionsave3, time, "save option3", log);
				Utils.callSendkeys(driver, ActionXpath.Qfacfeedback3, "Better", time, log);

			} else {
				Utils.smallSleepBetweenClicks(1);
				Utils.callSendkeys(driver, ActionXpath.faccmarks, "1", time, log);
				Utils.smallSleepBetweenClicks(1);
				Utils.callSendkeys(driver, ActionXpath.faccoption1, "modi", time, log);
				Utils.smallSleepBetweenClicks(1);
				Utils.callSendkeys(driver, ActionXpath.feedback1, "Super", time, log);
				Utils.smallSleepBetweenClicks(1);
				Utils.callSendkeys(driver, ActionXpath.faccoption2, "sachin", time, log);
				Utils.callSendkeys(driver, ActionXpath.feedback2, "vg", time, log);
				Utils.callSendkeys(driver, ActionXpath.faccoption3, "anand", time, log);
				Utils.callSendkeys(driver, ActionXpath.feedback3, "good", time, log);
			}
			Utils.smallSleepBetweenClicks(1);

			Utils.clickXpath(driver, ActionXpath.numberofchoice, time, "No of chocice", log);
			Utils.callSendkeys(driver, ActionXpath.feedbackofcrtans, "super", time, log);
			Utils.smallSleepBetweenClicks(1);
			Utils.callSendkeys(driver, ActionXpath.feefbacofincorrect, "improve", time, log);
			Utils.smallSleepBetweenClicks(1);
			Utils.callSendkeys(driver, ActionXpath.generalfeedback, "gain know", time, log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.facsaveandfinish, time, "Finished", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.scrollUpOrDown(driver, time);
			Utils.scrollUpOrDown(driver, time);
			Utils.clickXpath(driver, ActionXpath.facqueback, time, "BAck", log);
			log.info("TC-38: Faculty QUESTION PAPER TEST CASE PASSED \n");

		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-38: Faculty QUESTION PAPER  FAILED \n");
		}
	}

	@Test(priority = 39)
	public static void testFacultySignout(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println(" TC-39:  Faculty  View Profile test Executation Started");
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);

			// Utils.clickXpath(driver, ActionXpath.FCCportal, time, "facselectpro", log);
			// Utils.clickXpath(driver, ActionXpath.facprofile, time, "facprofile", log);

			// Utils.clickXpath(driver, ActionXpath.FCCportal, time, "Click of faculty pic",
			// log);
			// Utils.clickXpath(driver, ActionXpath.facsignOut, time, "facsignOut", log);
			Utils.smallSleepBetweenClicks(1);
			log.info(" TC-39: Faculty View Profile and Sign out Test Case PASSED \n ");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-39: Faculty View Profile and Sign out Test Case FAILED \n");
		}
	}

	@Test(priority = 40)
	public static void testFacultyEventFilter(String url, WebDriver driver, Logger log) throws Exception {
		try {
			System.out.println("TC-70: Faculty Portal Event Filter Tab Test case Started");
			Utils.goBackToHome(driver, url, log);
			Utils.clickXpath(driver, ActionXpath.eventfilter, time, "facEvent", log);
			// Utils.clickXpath(driver, ActionXpath.faccEvent, time, "facEvent", log);
			Utils.clickXpath(driver, ActionXpath.eventfilterclear, time, "facEvent", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.callSendkeys(driver, ActionXpath.eventfiltersecrech, "Ganesh Chaturthi", time, log);
			Utils.clickXpath(driver, ActionXpath.faceventlocation, time, "faceventlocation", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.faceventlocationselect, time, "faceventlocationselect", log);
			// Utils.callSendkeys(driver, ActionXpath.FaccSearch, "Ganesh", time, log);
			Utils.clickXpath(driver, ActionXpath.eventfiltercatgery, time, "eventfiltercatgery", log);
			Utils.clickXpath(driver, ActionXpath.eventsfiltercatgeryselect, time, "eventsfiltercatgeryselect", log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.eventsfiltertype, time, "eventsfiltertype", log);
			Utils.clickXpath(driver, ActionXpath.eventsfiltertypeseclect, time, "eventsfiltertypeseclect", log);
			log.info("TC-40: Faculty Event Filter Test case Executation PASSED \n");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-40: Faculty Event Filter Test case FAILED \n");
		}
	}

	@Test(priority = 84)
	public static void testFacultymystudentprofileadmissiondetails(String url, WebDriver driver, Logger log)
			throws Exception {
		try {
			System.out.println(" TC-84:Faculty Mystudent Profile ADMISSION DETAILS test Executation Started");
			Utils.goBackToHome(driver, url, log);
			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.mystudent, time, "mystudent", log);
			Utils.callSendkeys(driver, ActionXpath.mystudentserch, "Test Student", time, log);

			Utils.smallSleepBetweenClicks(1);
			Utils.clickXpath(driver, ActionXpath.mystudentprofileclick, time, "mystudentprofileclick", log);
			Utils.callSendkeys(driver, ActionXpath.mystudentyoj, "Raj", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudenthoues, "CBE", time, log);
			Utils.bigSleepBetweenClicks(2);
			Utils.callSendkeys(driver, ActionXpath.mystudentvpaopted, "bangalore", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentecapted, "rurel", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentadhar, "1234567890", time, log);
			Utils.smallSleepBetweenClicks(1);
			Utils.callSendkeys(driver, ActionXpath.mystudentage, "25", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentallery, "NO", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentdrivername, "kumar", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentphoneno, "250111998", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentphotofamilyhistory, "yes", time, log);
			Utils.clickXpath(driver, ActionXpath.mystudentsubmit, time, "mystudentsubmit", log);
			// Utils.clickXpath(driver, ActionXpath.facsignOut, time, "facsignOut", log);
			Utils.smallSleepBetweenClicks(1);
			log.info(" TC-84: Faculty Mystudent Profile ADMISSION DETAILS  Test Case PASSED \n ");
		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-84: Faculty Mystudent Profile ADMISSION DETAILS Test Case FAILED \n");
		}
	}

	@Test(priority = 85)
	public static void testFacultymystudentprofilepresonaldetails(String url, WebDriver driver, Logger log)
			throws Exception {
		try {
			System.out.println(" TC-85:Faculty Mystudent Profile PERSONAL DETAILS test Executation Started");
			Utils.goBackToHome(driver, url, log);
			Utils.smallSleepBetweenClicks(2);
			Utils.clickXpath(driver, ActionXpath.mystudent, time, "mystudent", log);
			Utils.smallSleepBetweenClicks(2);
			Utils.callSendkeys(driver, ActionXpath.mystudentserch, "Test Student", time, log);
			Utils.clickXpath(driver, ActionXpath.mystudentprofileclick, time, "mystudentprofileclick", log);
			Utils.clickXpath(driver, ActionXpath.mystudentpersonal, time, "mystudentpersonal", log);
			Utils.callSendkeys(driver, ActionXpath.mystudentperfather, "hari", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentmother, "janaki", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentprimarycontant, "567896543", time, log);
			Utils.callSendkeys(driver, ActionXpath.bloodgroup, "AB+", time, log);
			Utils.callSendkeys(driver, ActionXpath.alternatorcontact, "345698765", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentmailid, "hari@gmail.com", time, log);
			Utils.callSendkeys(driver, ActionXpath.fatherocupation, "Army", time, log);
			Utils.callSendkeys(driver, ActionXpath.moterocupation, "home maker", time, log);
			Utils.callSendkeys(driver, ActionXpath.anualincome, "5-lash", time, log);
			Utils.callSendkeys(driver, ActionXpath.emercycontant, "453666655", time, log);
			Utils.clickXpath(driver, ActionXpath.personalsubit, time, "personalsubit", log);
			Utils.smallSleepBetweenClicks(1);
			log.info(" TC-85: Faculty Mystudent Profile PERSONAL DETAILS and  Test Case PASSED \n ");

		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-85: Faculty Mystudent Profile PERSONAL  DETAILS Test Case FAILED \n");
		}
	}

	@Test(priority = 86)
	public static void testFacultymystudentprofileaddresdetails(String url, WebDriver driver, Logger log)
			throws Exception {
		try {
			System.out.println(" TC-86:Faculty Mystudent Profile Address Details Test Executation Started");
			Utils.goBackToHome(driver, url, log);
			Utils.smallSleepBetweenClicks(2);
			Utils.clickXpath(driver, ActionXpath.mystudent, time, "mystudent", log);
			Utils.callSendkeys(driver, ActionXpath.mystudentserch, "Test Student", time, log);
			Utils.clickXpath(driver, ActionXpath.mystudentprofileclick, time, "mystudentprofileclick", log);
			Utils.smallSleepBetweenClicks(2);
			// Utils.callSendkeys(driver, ActionXpath.adderwsdetails, "hari", time, log);
			Utils.clickXpath(driver, ActionXpath.adderwsdetails, time, "adderwsdetails", log);
			Utils.callSendkeys(driver, ActionXpath.streetaddres, "renganathapuram", time, log);
			Utils.callSendkeys(driver, ActionXpath.cityaddres, "CBE-BANGALORE", time, log);
			Utils.callSendkeys(driver, ActionXpath.postalcode, "7642", time, log);
			Utils.callSendkeys(driver, ActionXpath.mystudentstate, "KT", time, log);
			Utils.callSendkeys(driver, ActionXpath.countrydetails, "IND", time, log);
			Utils.callSendkeys(driver, ActionXpath.streetperment, "Truchi", time, log);
			Utils.callSendkeys(driver, ActionXpath.cityperment, "BANGALORE RUREL", time, log);
			Utils.callSendkeys(driver, ActionXpath.postalcodeperment, "IPO", time, log);
			Utils.callSendkeys(driver, ActionXpath.permentstate, "KARNATAKA", time, log);
			Utils.callSendkeys(driver, ActionXpath.permentcountary, "hari", time, log);
			Utils.clickXpath(driver, ActionXpath.personalsubit, time, "personalsubit", log);
			Utils.smallSleepBetweenClicks(1);
			log.info(" TC-86: Faculty Mystudent Profile Address Details  and  Test Case PASSED \n ");

		} catch (Exception e) {
			Utils.printException(e);
			Utils.goBackToHome(driver, url, log);
			Utils.logout(driver, url, "faculty", log);
			log.warning("TC-86: Faculty Mystudent Profile Address Details  Test Case FAILED \n");
		}
	}
}