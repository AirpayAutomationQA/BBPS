package com.Airpay.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.Airpay.BusinessLogic.AirPay_BBPS_BusinessLogic;
import com.Airpay.BusinessLogic.SinglePager_PaymentPage_BusinessLogic;
import com.Airpay.InitialSetup.Driver_Setup;
import com.Airpay.PageObject.Airpay_BBPS_PageObject;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.Log;

public class TC_PP_BBPS_Electricity_Add extends Driver_Setup{
	public static WebDriver webDriver = null;
	public static String tcID = null;
	
	@Test(priority=1)
	public void setup()
	{
		Log.info("Setup the variable for Test");
		webDriver = driver; 
		tcID = TC_ID;
		Log.info("Setup completed for the variable");
	}
	@Test(priority = 2)
	public void TC_TestCaseName() throws Throwable {
		try {
			Log.info("Script Starts..");
			AirPay_BBPS_BusinessLogic CA_panel = new AirPay_BBPS_BusinessLogic(driver, TC_ID);
			CA_panel.BBPS_Login();
			CA_panel.BBPS_Op(Airpay_BBPS_PageObject.BBPSTABLink,"BBPS Tab");
			CA_panel.BBPS_Op(Airpay_BBPS_PageObject.BBPSDashBoard,"BBPS DashBoard");
			CA_panel.BBPS_Display(Airpay_BBPS_PageObject.BBPSHeaderpage, "Bharat Bill");
			CA_panel.BBPS_Op(Airpay_BBPS_PageObject.BBPSAddingBiller,"Add Biller");
			CA_panel.BBPS_PayMode(AirPay_BBPS_BusinessLogic.billPay);
			CA_panel.BBPS_Op(Airpay_BBPS_PageObject.ONswitchBtn,"Add Biller");			
			CA_panel.MobilePay_Mode();
		
			
			
			CA_panel.CircleArea(AirPay_BBPS_BusinessLogic.MobilePrepaidCircle, Excel_Handling.Get_Data(TC_ID, "BillCircle").trim());
			CA_panel.BBPS_Op("//span[@id='"+Excel_Handling.Get_Data(tcID, "PlanPrice").trim()+"']","BBPS DashBoard");
			CA_panel.RechargeDetails();
			CA_panel.BBPS_Op(Airpay_BBPS_PageObject.RechargeSubmitbtn,"Submit");
			CA_panel.InvoicepayDetails();
			CA_panel.BBPS_Op(Airpay_BBPS_PageObject.payInvoiceRechargebtn,"Pay");			
			SinglePager_PaymentPage_BusinessLogic AirPay_Local = new SinglePager_PaymentPage_BusinessLogic(driver, TC_ID);
			AirPay_Local.Card_Details_Options();				
			if(AirPay_Local.SandBoxMode("You are in Sandbox mode (Your account will not be charged)")==true)
			{
				AirPay_Local.Credit_cardProvidingValues();
				AirPay_Local.Cash_paymentSuccessMesg();
			}else{
				AirPay_Local.FooterVerify();
				AirPay_Local.FailedTransaction();					
			}
			
		} catch (Exception e) {
			Log.error(e.getMessage());
			System.out.println(e);
		}
	}
	@AfterTest
	public void tearDown()
	{
		if(webDriver != null)
			webDriver.close();
	}
}

