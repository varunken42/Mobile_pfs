package com.ken42;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

// import org.apache.commons.lang.ObjectUtils.Null;
// import org.jaxen.function.TrueFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.safari.SafariDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.opencsv.CSVReader;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.logging.*;
import java.util.logging.FileHandler;
import java.text.SimpleDateFormat;

public class Pfs_portal extends Thread {
	private String[] csvLineData;
	private int count;
	private static final Exception Exception = null;
	static int time = 1000;
	static Boolean headless;
	public Logger log = Logger.getLogger("Pfs_portal");
	public static boolean faculty_login_set = false;
	public static boolean student_login_set = false;

	@Override
	public void run() {
		System.out.println("Thread- Started" + Thread.currentThread().getName());
		String threadname = Thread.currentThread().getName();
		System.out.println(threadname);
		try {
			Thread.sleep(1000);
			testPFSPortal(this.csvLineData, this.count);
		} catch (InterruptedException e) {
			log.info("Thread- Exception " + Thread.currentThread().getName());
			System.out.println("Thread- Exception " + Thread.currentThread().getName());
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			log.info("Thread- Exception " + Thread.currentThread().getName());
			System.out.println("Thread- Exception " + Thread.currentThread().getName());
			e.printStackTrace();
		}
		log.info("Thread- END " + Thread.currentThread().getName());
		System.out.println("Thread- END " + Thread.currentThread().getName());
	}

	public Pfs_portal(String[] csvCell, int count, Logger log) {
		this.csvLineData = csvCell;
		this.count = count;
		this.log = log;
	}

	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger("Pfs_portal");
		String folder = "";
		folder = getFolderPath();
		String CSV_PATH = "";
		CSV_PATH = folder + "\\pfs.csv";
		CSVReader csvReader1;
		int ThreadCount = 0;
		csvReader1 = new CSVReader(new FileReader(CSV_PATH));
		String[] csvCell1;
		while ((csvCell1 = csvReader1.readNext()) != null) {
			ThreadCount++;
		}
		System.out.println("Number of threads to start  " + ThreadCount);
		Thread[] threads = new Thread[ThreadCount];

		CSVReader csvReader;
		int count = 0;
		csvReader = new CSVReader(new FileReader(CSV_PATH));

		String[] csvCell;
		while ((csvCell = csvReader.readNext()) != null) {
			if (count == 0) {
				count = count + 1;
				continue;
			}
			// testPFSPortal(csvCell);
			Thread t = new Pfs_portal(csvCell, count, log);
			threads[count] = t;
			threads[count].setName("T" + String.valueOf(count + 1));
			t.start();
			Utils.smallSleepBetweenClicks(8);
			count++;
		}
		// SendMail.sendEmail(logFileName);
	}

	public static void checkStudentIsLoggedIn(WebDriver driver, String studentEmail, String PFSurl, Logger log,
			String[] csvCell)
			throws Exception {
		// if (!student_login_set) {
		// Utils.smallSleepBetweenClicks(1);
		// Utils.login(driver, studentEmail, PFSurl, log,csvCell);
		// student_login_set = true;
		// }
		boolean signInPresent = false;
		signInPresent = driver.findElements(By.xpath("//*[text()='Sign in']")).size() > 0;
		if (signInPresent) {
			Utils.goBackToHome(driver, PFSurl, log);
			log.info("Hey Student not logged in let's login with ID " + studentEmail);
			Utils.login(driver, studentEmail, PFSurl, log, csvCell);
			Utils.smallSleepBetweenClicks(4);
		} else {
			log.info("It's not in login page");
		}
	}

	public static void checkFacultyIsLoggedIn(WebDriver driver, String facultyEmail, String PFSurl, Logger log,
			String[] csvCell)
			throws Exception {

		boolean signInPresent = false;
		signInPresent = driver.findElements(By.xpath("//*[text()='Sign in']")).size() > 0;
		if (signInPresent) {
			Utils.goBackToHome(driver, PFSurl, log);
			log.info("Hey Faculty not logged in let's login with ID " + facultyEmail);
			Utils.login(driver, facultyEmail, PFSurl, log, csvCell);
			Utils.smallSleepBetweenClicks(4);
		} else {
			// log.warning("Faculty login for test case 17-38 failed somewhere");
			System.out.println("$$$$$$$$$$$$$ No Sign in page");
		}
	}

	// Created a function so this can be threaded
	public static void testPFSPortal(String[] csvCell, int count) throws Exception {
		Logger log = Logger.getLogger("Pfs_portal" + count);
		String folder = "";
		folder = getFolderPath();
		String logFileName = "";
		boolean append = false;
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		logFileName = String.format(folder + "\\Testresult_%s.HTML", timeStamp);
		FileHandler logFile = new FileHandler(logFileName, append);
		logFile.setFormatter(new MyHtmlFormatter());
		log.addHandler(logFile);
		WebDriver driver = null;

		// boolean faculty_login_set = false;
		// boolean student_login_set = false;
		String PFSurl = csvCell[0];
		String facultyEmail = csvCell[1];
		String studentEmail = csvCell[2];
		String Role = "";
		String Browser = csvCell[3];
		String From = csvCell[4];
		String To = csvCell[5];
		String studentName = csvCell[6];
		// String headless = csvCell[7];
		int from = Integer.parseInt(From);
		int to = Integer.parseInt(To);
		headless = getHeadless(csvCell);
		if ((from < 1 || from > 84 || to < 1 || to > 84) || (to < from)) {
			log.warning("The range specificed is incorrect it has to be between 1 and 68");
			log.warning("Please correct the From and To Columns in CSV file and run again");
			System.exit(1);
		}

		driver = initDriver(Browser, PFSurl);

		if ((from >= 1 && to <= 16)) {
			// Utils.login(driver, studentEmail, PFSurl, log,csvCell);
			Role = "student";
			student_login_set = true;
		} else if ((from >= 17 && to <= 39)) {
			// Utils.login(driver, facultyEmail, PFSurl, log,csvCell);
			Role = "faculty";
			faculty_login_set = true;
		} else if ((from >= 1 && to <= 84) && (to >= 1 && to <= 84)) {
			student_login_set = false;
			faculty_login_set = false;
		}

		log.info("**********************Testing for  Portal  " + PFSurl);
		// Below If will execute all Student related test cases
		for (int i = from; i <= to; i++) {
			switch (i) {
				case 1:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudent(PFSurl, driver, log); // TC-1
					break;
				case 2:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentEnrollment(PFSurl, driver, log); // TC-2
					break;
				case 3:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentAcademic(PFSurl, driver, log); // TC-3
					break;
				case 4:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentExamination(PFSurl, driver, log); // TC-4
					break;
				case 5:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentAttendance(PFSurl, driver, log); // TC-5
					break;
				case 6:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentTimeTable(PFSurl, driver, log); // TC-6
					break;
				case 7:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentFees(PFSurl, driver, log); // TC-7
					break;
				case 8:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentFeedback(PFSurl, driver, log); // TC-8
					break;
				case 9:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentStudentStatus(PFSurl, driver, log); // TC-9
					break;
				case 10:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentRaiseCase(studentEmail, facultyEmail, PFSurl, driver, log); // TC-10
					break;
				case 11:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentMakeRequest(studentEmail, facultyEmail, PFSurl, driver, log); // TC-11
					break;
				case 12:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentEvent(studentEmail, facultyEmail, PFSurl, driver, log); // TC-12
					break;
				case 13:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentEditProfile(PFSurl, driver, log); // TC-13
					break;
				case 14:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentEditEducationDetails(PFSurl, driver, log); // TC-14
					break;
				case 15:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentEditAddress(PFSurl, driver, log); // TC-15
					break;
				case 16:
					checkStudentIsLoggedIn(driver, studentEmail, PFSurl, log, csvCell);
					Pfs_student.testStudentSignout(PFSurl, driver, log); // TC-16
					break;
				case 17:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFaculty(PFSurl, driver, log); // TC-17
					break;
				case 18:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyQuestionBank(PFSurl, driver, log); // TC-18
					break;
				case 19:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyCourseContent(PFSurl, driver, log); // TC-19
					break;
				case 20:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyExamination(PFSurl, driver, log); // TC-20
					break;
				case 21:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyMYStudent(PFSurl, driver, log); // TC-21
					break;
				case 22:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyAttendance(PFSurl, driver, log); // TC-22
					break;
				case 23:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFaculityTimetable(PFSurl, driver, log); // TC-23
					break;
				case 24:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyService(PFSurl, driver, log); // TC-24
					break;
				case 25:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyRaiseCase(studentEmail, facultyEmail, PFSurl, driver, log); // TC-25
					break;
				case 26:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyMakeRequest(studentEmail, facultyEmail, PFSurl, driver, log); // TC-26
					break;
				case 27:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyEvent(PFSurl, driver, log); // TC-27
					break;
				case 28:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testfacultyEditProfile(studentEmail, facultyEmail, PFSurl,
							driver, log); // TC-28
					break;
				case 29:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testfacultyEditAddress(studentEmail, facultyEmail, PFSurl,
							driver, log); // TC-29
					break;
				case 30:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testfacultyEditAcademicDetails(studentEmail, facultyEmail,
							PFSurl, driver, log); // TC-30
					break;
				case 31:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testfacultyEditEXPERIENCE(studentEmail, facultyEmail,
							PFSurl, driver, log); // TC-31
					break;
				case 32:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testfacultyEditRESEARCHPublication(studentEmail, facultyEmail,
							PFSurl, driver, log); // TC-32
					break;
				case 33:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testfacultyEditOTHERS(studentEmail, facultyEmail, PFSurl,
							driver, log); // TC-33
					break;
				case 34:
					// checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log,csvCell);
					// Pfs_faculty.testfacultyEditBook(studentEmail, facultyEmail, PFSurl, driver,
					// log); // TC-34
					break;
				case 35:
					// checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log,csvCell);
					// Pfs_faculty.testfacultyEditProfessionalAssociation(studentEmail,
					// facultyEmail, PFSurl, driver, log); // TC-35
					break;
				case 36:
					// checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log,csvCell);
					// Pfs_faculty.testfacultyOthers(studentEmail, facultyEmail, PFSurl, driver,
					// log); // TC-36
					break;
				case 37:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyDashboard(studentEmail, facultyEmail, PFSurl, driver, log); // TC-37
					break;
				case 38:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyQuestionPaper(studentEmail, facultyEmail, PFSurl, driver, log); // TC-38
					break;
				case 39:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultySignout(PFSurl, driver, log); // TC-39
					break;
				case 40:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultyEventFilter(PFSurl, driver, log); // TC-70
					break;
				case 41:
					Pfs_resource.testSpreadsheetCreateViewDelete(studentEmail,
							facultyEmail, PFSurl, Browser, Role, driver, log, csvCell); // TC-40
					break;
				case 42:
					Pfs_resource.testPPTCreateViewDelete(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-41
					break;
				case 43:
					Pfs_resource.testPDFCreateViewDelete(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-42
					break;
				case 44:
					Pfs_resource.testVideoCreateViewDelete(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-43
					break;
				case 45:
					Pfs_resource.testLinkCreateViewDelete(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-44
					break;
				case 46:
					Pfs_resource.testSpreadsheetFileType(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-45
					break;
				case 47:
					Pfs_resource.testPPTFileType(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-46
					break;
				case 48:
					Pfs_resource.testPDFFileType(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-47
					break;
				case 49:
					Pfs_resource.testVideoFileType(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-48
					break;
				case 50:
					Pfs_resource.testFacultyFilterResource(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-49
					break;
				case 51:
					Pfs_resource.testFacultyFilterPDFResource(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-50
					break;
				case 52:
					Pfs_resource.testFacultyFilterVideoResource(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-51
					break;
				case 53:
					Pfs_resource.testFacultyFilterLinksResource(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-52
					break;
				case 54:
					Pfs_resource.CreatepublisheditdeltePDF(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-71
					break;
				case 55:
					Pfs_resource.CreatepublisheditdeltePPT(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-71
					break;
				case 56:
					Pfs_resource.CreatepublisheditdelteVideo(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-71
					break;
				case 57:
					Pfs_resource.CreatepublisheditdelteSpreadsheet(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-71
					break;
				case 58:
					Pfs_resource.CreatepublisheditdelteLink(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-71
					break;
				case 59:
					Pfs_activity.testFilterActivityAssignment(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-60
					break;
				case 60:
					Pfs_activity.testFilterActivityAssement(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-61
					break;
				case 61:
					Pfs_activity.testFilterActivityForum(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-62
					break;
				case 62:
					if (to < 63) {
						Pfs_activity.testForumCreatePublishViewDelete(studentEmail, facultyEmail,
								PFSurl, Browser, Role, driver, log, csvCell); // TC-55
					} else {
						log.info("TC-63: Forum Create,Publish,View,delete Skipped as it's subset of next test case");
					}
					break;
				case 63:
					Pfs_activity.testForumCreatePublishViewDeleteDiscussion(studentEmail, facultyEmail,
							PFSurl, Browser, Role, driver, log, csvCell);// TC-59
					break;

				case 64:
					Pfs_activity.testForumCreatePublishEditDelete(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-63
					break;
				case 65:
					Pfs_activity.testForumCreateunPublishEditDelete(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-64
					break;

				case 66:
					if (to < 67) {
						Pfs_activity.testAssessmentCreatePublishViewDelete(studentEmail, facultyEmail,
								PFSurl, Browser, Role, driver, log, csvCell); // TC-53
					} else {
						log.info(
								"TC-67: Assessment Create,Publish,View,delete Skipped as it's subset of next test case");
					}
					break;
				case 67:
					Pfs_activity.testassesmentAttemptview(studentEmail, facultyEmail,
							PFSurl, Browser, Role, driver, log, csvCell);// TC-61
					break;

				case 68:
					Pfs_activity.testassesmenteditdelete(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-65
					break;
				case 69:
					Pfs_activity.testassesmentpublisheditview(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-66
					break;
				case 70:
					Pfs_activity.testFAssignmentCreatePublishViewDelete(studentEmail, facultyEmail,
							PFSurl, Browser, Role, driver, log, csvCell); // TC-54
					break;
				case 71:
					if (to < 72) {
						Pfs_activity.testFAssignmentCreatePublishsubmissionfileuploadchecking(studentEmail,
								facultyEmail,
								PFSurl, Browser, Role, driver, log, csvCell); // TC-56
					} else {
						log.info(
								"TC-72:Assignment Create,Publish,View,delete Skipped as it's subset of next test case");
					}
					break;
				case 72:
					Pfs_activity.testFAssignmentCreatePublishsubmissiongradecheck(studentEmail, facultyEmail,
							PFSurl, Browser, Role, driver, studentName, log, csvCell); // TC-57
					break;
				case 73:
					Pfs_activity.testFAssignmentCreateEditDelete(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-67
					break;
				case 74:
					Pfs_activity.testFAssignmentCreatepublishEditDelete(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-68
					break;
				case 75:
					Pfs_activity.testAttemptview(studentEmail, facultyEmail, PFSurl,
							Browser, Role, driver, log, csvCell); // TC-69
					break;
				case 76:
					Pfs_resource.DeleteResourcesPPT(studentName, facultyEmail, PFSurl, Browser, Role, driver, log,
							csvCell);
					break;
				case 77:
					Pfs_resource.DeleteResourcesExcel(studentName, facultyEmail, PFSurl, Browser, Role, driver, log,
							csvCell);
					break;
				case 78:
					Pfs_resource.DeleteResourcesPDF(studentName, facultyEmail, PFSurl, Browser, Role, driver, log,
							csvCell);
					break;
				case 79:
					Pfs_resource.DeleteResourcesVideo(studentName, facultyEmail, PFSurl, Browser, Role, driver, log,
							csvCell);
					break;
				case 80:
					Pfs_resource.DeleteResourcesLink(studentName, facultyEmail, PFSurl, Browser, Role, driver, log,
							csvCell);
					break;
				case 81:
					Pfs_activity.DeleteResourcesAssignments(studentName, facultyEmail, PFSurl, Browser, Role, driver,
							log, csvCell);
					break;
				case 82:
					Pfs_activity.DeleteResourcesAssessment(studentName, facultyEmail, PFSurl, Browser, Role, driver,
							log, csvCell);
					break;
				case 83:
					Pfs_activity.DeleteResourcesForum(studentName, facultyEmail, PFSurl, Browser, Role, driver, log,
							csvCell);
					break;
				case 84:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultymystudentprofileadmissiondetails(PFSurl, driver, log); // TC-84
					break;
				case 85:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultymystudentprofilepresonaldetails(PFSurl, driver, log); // TC-85
					break;
				case 86:
					checkFacultyIsLoggedIn(driver, facultyEmail, PFSurl, log, csvCell);
					Pfs_faculty.testFacultymystudentprofileaddresdetails(PFSurl, driver, log); // TC-86
					break;
				default:
					throw Exception;
			}
		}
		quitDriver(driver, PFSurl);
		log.info("***************** COMPLETED TESTTING OF PORTAL" + PFSurl);
		SendMail.sendEmail(logFileName);
	}

	@BeforeSuite
	public static WebDriver initDriver(String Browser, String url) throws Exception {

		try {
			WebDriver driver = null;
			String folder = "";
			folder = getFolderPath();
			String ChromeDriver = folder + "\\chromedriver.exe";
			String EdgeDriver = folder + "\\msedgedriver.exe";
			String FirefoxDriver = folder + "\\geckodriver.exe";
			System.out.println("Browser is ****" + Browser);
			System.out.println("URL is " + url);
			if ("chrome".equals(Browser)) {
				System.setProperty("webdriver.chrome.driver", ChromeDriver);
				ChromeOptions op = new ChromeOptions();
				if (headless) {
					op.addArguments("--headless", "--window-size=1920,1080");
				} else {
					op.addArguments("--disable-notifications");
				}
				// op.addArguments("--headless");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(op);
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			} else if ("edge".equals(Browser)) {
				System.setProperty("webdriver.edge.driver", EdgeDriver);
				driver = new EdgeDriver();
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			} else if ("firefox".equals(Browser)) {
				System.setProperty("webdriver.edge.driver", FirefoxDriver);
				FirefoxOptions fx = new FirefoxOptions();
				fx.addArguments("--disable-notifications");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(fx);
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			} else if ("safari".equals(Browser)) {
				driver = new SafariDriver();
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			}
			System.out.println("********************" + url);
			if (driver != null) {
				driver.get(url);
				driver.manage().window().maximize();
				return (driver);
			}
		} catch (Exception e) {
			Utils.printException(e);
			// log.warning("UNABLE TO LAUNCH BROWSER");
			System.exit(01);
		}
		return null;
	}

	@AfterSuite
	public static void quitDriver(WebDriver driver, String Url) throws Exception {
		// log.info("Completed testing of portal" + Url);
		System.out.println("Qutting driver");
		driver.quit();
		Utils.smallSleepBetweenClicks(1);
	}

	public static String getFolderPath() throws Exception {
		try {
			String folder = "";
			InputStream folderPath = Pfs_portal.class.getResourceAsStream("folder.csv");
			CSVReader csvFolderPath = new CSVReader(new InputStreamReader(folderPath, "UTF-8"));
			String[] csvCell_folder;
			while ((csvCell_folder = csvFolderPath.readNext()) != null) {
				folder = csvCell_folder[0];
			}
			System.out.println(folder);
			return folder;
		} catch (Exception e) {
			Utils.printException(e);
		}
		return null;
	}

	public static Boolean getHeadless(String[] csvCell) throws Exception {
		try {
			String headless = csvCell[7];
			if ("TRUE".equals(headless)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Utils.printException(e);
		}
		return false;
	}
}