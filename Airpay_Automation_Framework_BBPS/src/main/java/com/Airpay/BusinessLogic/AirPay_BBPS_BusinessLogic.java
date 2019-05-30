package com.Airpay.BusinessLogic;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.Airpay.PageObject.Airpay_BBPS_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;

 public class AirPay_BBPS_BusinessLogic extends Airpay_BBPS_PageObject 
 {
	public WebDriver driver;
	public static String TC_ID = "";
	ElementAction Assert = new ElementAction();
	public AirPay_BBPS_BusinessLogic(WebDriver driver, String TC_ID)
	{
		Log.info("AirPay_payment_Mode_Wallet_BusinessLogic");
		this.driver = driver;
		this.TC_ID=TC_ID;
	}
	
	/**
	 * @author sakole
	 * @throws Exception
	 */	
	public void BBPS_Login() throws Exception{
		try{
			
			String MA_URL = Excel_Handling.Get_Data(TC_ID, "BBPS_URL").trim();
			driver.navigate().to(MA_URL);
			Assert.waitForPageToLoad(driver);
			//Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MA_UserID, Excel_Handling.Get_Data(TC_ID, "MA_UserID").trim(), "MA Panel USer ID");
			if(Assert.isElementDisplayed(driver, BBPSLoginID, "USER ID")){
				Assert.inputText(driver, BBPSLoginID, Excel_Handling.Get_Data(TC_ID, "BBPS_USER_ID").trim(), "BBPS USer ID");
				Assert.inputText(driver, BBPSLoginPwd, Excel_Handling.Get_Data(TC_ID, "BBPS_PWD").trim(), "BBPS PWD");
				Extent_Reporting.Log_report_img("Login Details entered", "Passed", driver);
				Assert.clickButton(driver,BBPSsubmitBtn, "Sign In button");
				Assert.waitForPageToLoad(driver);
				Thread.sleep(10000);
			}else{
				Extent_Reporting.Log_Fail("BBPS_USER_ID  field does not exist", "Failed", driver);
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("BBPS_USER_ID field does not exist", "Failed", driver);
			throw new Exception("BBPS_USER_ID page issue");
		}
	}
	
	
	public void BBPS_Op(String DashBoard,String Comment) throws Throwable {
		try{ 
			Log.info("Navigated to Page Verify");	
			Assert.waitForPageToLoad(driver);
			Thread.sleep(2000);
			if(Assert.isElementDisplayed(driver, DashBoard, Comment ))
			{         	
				Assert.Javascriptexecutor_forClick(driver, DashBoard, Comment);
				Extent_Reporting.Log_report_img(Comment+" is exist", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail(Comment+" does not exis",	"Failed",driver);
				Log.error(Comment+" not successfully displayed");
				throw new Exception(Comment+" does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("BBPS tab does not exis",	"Failed",driver);
			Log.error("BBPS tab not successfully displayed");
			e.printStackTrace();
			throw new Exception("BBPS tab does not exist displayed");
		}
	}
	
	
	public static String billPay  = "";	
	public void BBPS_PayMode(String Comment) throws Exception {
		try{ 
			
			billPay=Excel_Handling.Get_Data(TC_ID, "BillPaymentSystem").trim();
			Log.info("Navigated to Page Verify");	
			Assert.waitForPageToLoad(driver);
			Thread.sleep(2000);
			if(Assert.isElementDisplayed(driver, "//ul[@class='thlist']/li/div/a/h3[text()='"+billPay+"']", Comment ))
			{         	
				Assert.Clickbtn(driver, "//ul[@class='thlist']/li/div/a/h3[text()='"+billPay+"']", Comment);
				Extent_Reporting.Log_report_img(Comment+" is exist", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail(Comment+" does not exis",	"Failed",driver);
				Log.error(Comment+" not successfully displayed");
				throw new Exception(Comment+" does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("BBPS tab does not exis",	"Failed",driver);
			Log.error("BBPS tab not successfully displayed");
			e.printStackTrace();
			throw new Exception("BBPS tab does not exist displayed");
		}
	}
	
	public void BBPS_Display(String DashBoard,String Comment) throws Exception {
		try{ 
			Log.info("Navigated to Page Verify");	
			Assert.waitForPageToLoad(driver);
			Thread.sleep(2000);
			if(Assert.isElementDisplayed(driver, DashBoard, Comment ))
			{  
				Extent_Reporting.Log_Pass(Comment +" Is exist", "Passed");
				Extent_Reporting.Log_report_img(Comment+" is exist", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail(Comment+" does not exis",	"Failed",driver);
				Log.error(Comment+" not successfully displayed");
				throw new Exception(Comment+" does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("BBPS tab does not exis",	"Failed",driver);
			Log.error("BBPS tab not successfully displayed");
			e.printStackTrace();
			throw new Exception("BBPS tab does not exist displayed");
		}
	}
	
	public void MobilePay_Mode() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);		
			List<WebElement> MobilePay = driver.findElements(By.xpath(Billers));
			int ChannelsCnt = MobilePay.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{
				WebElement ChannelsName = MobilePay.get(i);
				String name = ChannelsName.getText().trim();
				if(Excel_Handling.Get_Data(TC_ID, "Billers").trim().contains(name)){
					ChannelsName.click();
					Extent_Reporting.Log_Pass("Biller name is :" +name, "Passed");			
					Extent_Reporting.Log_report_img("Biller name is", "ScreenShot", driver);
					break;
				}
				if(i==ChannelsCnt){
					Extent_Reporting.Log_Fail("Biller name is does not exist", "Failed", driver);
				}
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Tab does not exist",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	
	public void CircleArea(String MobilePrepaidCircle,String DropValue) throws Exception{
		try{
			
			if(Assert.isElementDisplayed(driver, MobilePrepaidCircle, "MobilePrepaidCircle")){
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, MobilePrepaidCircle, DropValue, MobilePrepaidCircle);
				Extent_Reporting.Log_Pass(DropValue+" as selected", "Passed");
				Extent_Reporting.Log_report_img("Screen print", "Passed", driver);

			}else {
				Extent_Reporting.Log_Fail("Circle Drop Down Does not exist", "Failed", driver);
			}
			
		}catch(Exception e)	
			{
				Extent_Reporting.Log_Fail("Circle Drop Down Does not exist", "Failed", driver);
				Log.error("Tab does not exist");
				e.printStackTrace();
				throw new Exception("Tab does not exist");
			}
	     }
	
	
	
	public void RechargeDetails() throws Exception{
		try{
			
			if(Assert.isElementDisplayed(driver, RechargeFirstName, "Aadhar Field or PAN Number Error")){							
				Assert.inputText(driver, RechargeFirstName, Excel_Handling.Get_Data(TC_ID, "RechargeFirstName").trim(), "Recharge First Name");
				Assert.inputText(driver, RechargeLastName, Excel_Handling.Get_Data(TC_ID, "RechargeLastName").trim(), "Recharge last Name");
				Assert.inputText(driver, RechargeEmailID, Excel_Handling.Get_Data(TC_ID, "RechargeEmailID").trim(), "Recharge Email id");
				Assert.inputText(driver, RechargeMobileNumOrSubscribeID, Excel_Handling.Get_Data(TC_ID, "EmailOrSubScriberID").trim(), "Subscribe id or Mobile number");
				Extent_Reporting.Log_Pass("Respective Details exist", "Passed");
				Extent_Reporting.Log_report_img("Screen print", "Passed", driver);
			}else {
				Extent_Reporting.Log_Fail("Customer Details fields does not exist", "Failed", driver);
			}
			
		}catch(Exception e)	
			{
				Extent_Reporting.Log_Fail("Recharge Details fields Does not exist","Failed",driver);
				e.printStackTrace();
				throw new Exception("Details fields missed");
			}
	     }
	
	public static String InvoicePayNos="";
	public void InvoicepayDetails() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, InvoicePayNo, "Invoice pay"))
			{
				InvoicePayNos = driver.findElement(By.xpath(InvoicePayNo)).getText().split(":")[1].trim();
				Extent_Reporting.Log_Pass("Invoice Pay No is :"+InvoicePayNos,"Passed");
				Extent_Reporting.Log_report_img("Invoice Screen", "Passed", driver);
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, SelectPaymentMode, Excel_Handling.Get_Data(TC_ID, "Payment_Mode").trim(), "Payment Mode");
				Assert.Clickbtn(driver, TermsAndConditionChkbox, "Check box");
				
			}else {
				Extent_Reporting.Log_Fail("Invoice Pay field does not exisy", "Failed", driver);
				
			}			
		}        
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Invoice Pay field does not exisy", "Failed", driver);
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	public static String MAPanelWindow = "";
	public  static String[] browser =null;
	public static String child = null;

	public void TermsAndCondition()throws Exception{
		try{

			Log.info("Navigating To Net Banking Page");	
			Assert.waitForPageToLoad(driver);
			//((JavascriptExecutor) driver).executeScript("window.open();");
			Set<String> handles = driver.getWindowHandles();
			browser =	handles.toArray(new String[0]);
			System.out.println("Number of browsers opened are"+ browser.length);
			for (int i=0; i<handles.size();i++)
			{
				try
				{
					driver.switchTo().window(browser[i]);
					System.out.println(driver.getCurrentUrl());
					child =driver.getCurrentUrl();
					System.out.println("child:"+child);
					if(child.contains(Excel_Handling.Get_Data(TC_ID, "Invoice_Link").trim())){
						//if(child.contains("https://sotc.nowpay.co.in/invoice/")){
						System.out.println(driver.getCurrentUrl()+"found");
					/*	
						Assert.isElementDisplayed(driver, "//*[contains(text(),'Booking/Invoice No')]", "Booking Invoice Page");
						Extent_Reporting.Log_Pass("Pay Now Invoice page is exist", "passed");
						Extent_Reporting.Log_report_img("Invoice Page", "Passed", driver);
						//WebElement hitLocation = driver.findElement(By.className(AcceptTermsAndCondition));
						new Actions(driver)
						.moveToElement(hitLocation, 1, 1)
						.click()
						.perform();
						//Assert.Clickbtn(driver, CASubmitBtn, "Submit button");
						Card_Details_Options();
						Verify_SCOT_MerchantLogo();						
						driver.getWindowHandle();						
						Assert.waitForPageToLoad(driver);	*/					
						break;
					}
				}
				catch(Throwable t)
				{
					System.out.println("Browser not opened");
				}
			}		





		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("SCOT Logo does not exist",	"Failed",driver);
			Log.error("SCOT Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Logo does not displayed");
		}
	}
	
	
	
}