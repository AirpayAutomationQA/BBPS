package com.Airpay.PageObject;

public class Airpay_BBPS_PageObject {

	
	//*************************BBPS  Login *******************************************************
	
			public static final  String  BBPSLoginID = "//input[@id='loginform-username']";
			public static final  String  BBPSLoginPwd = "//input[@id='loginform-password']";			
			public static final  String  BBPSsubmitBtn = "//button[@name='login-button']";	
			public static final  String  BBPSTABLink = "(//a[@href='/bbps/default' and text()='BBPS '])[1]";	
			public static final  String  BBPSDashBoard = "//ul[@id='w9']//li/a[@href='/bbps/default']";	
			public static final  String  BBPSHeaderpage = "//h4[text()='Bharat Bill Payment System']";
			
			public static final  String  BBPSAddingBiller = "//a[@href='/bbps/default/biller']";
			public static final  String  Billers = "//ul[@id='providers']/li/label";
			public static final  String  MobilePrepaidCircle = "//select[@id='circle']";

			public static final  String  RechargeFirstName = "//input[@id='recharge_fname']";
			public static final  String  RechargeLastName = "//input[@id='recharge_lname']";
			public static final  String  RechargeEmailID = "//input[@id='recharge_email']";
			public static final  String  RechargeMobileNumOrSubscribeID = "//input[@id='recharge_Mobile Number' or @id='recharge_Subscriber ID' ]";
			public static final  String  RechargeSubmitbtn = "//input[@id='recharge_Mobile Number' or @id='recharge_Subscriber ID' ]//following::input[@name='submitButton'][1]";
			public static final  String  InvoicePayNo = "//div[@class='invoice-head']";
			public static final  String  SelectPaymentMode = "//select[@id='payment_mode']";
			public static final  String  TermsAndConditionChkbox = "//input[@id='tandc1']";
			public static final  String  payInvoiceRechargebtn = "//button[@id='pay']";
			public static final  String  ONswitchBtn = "//span[@class='yesnoswitch-switch']";

			
			public static final  String  ElectricityFirstName = "//input[@id='fname']";
			public static final  String  ElectricityLastName = "//input[@id='lname']";
			public static final  String  ElectricityEmailID = "//input[@id='email']";
			public static final  String  ElectricityMobileNumOrSubscribeID = "//input[@name='Account ID']";
			public static final  String  ElectricitySubmitbtn = "//input[@id='recharge_Mobile Number' or @id='recharge_Subscriber ID' ]//following::input[@name='submitButton'][1]";
			
			
			
			
			
			
			
			
			
			
			
			

			
			
			
			
			
			
			
			public static final String LogoPaymentPage = "//*[@src='resources/images/airpay-payment-processing-services-logo.png' or @class='mer-logo-txt']";
			public static final String ImgLogo = "(//img)[1]";			
			public static final  String  LogoutIcon = "//*[@class='dropdown-toggle']";	
			public static final  String  CALogOutBtn = "//*[@class='consumerbut']";
			
			public static final String AAdharField ="//*[@id='aadhar']";
			public static final String PANField ="//*[@id='pan']";
			public static final String ProfileSubmtBtn ="//*[@id='profile-submit-button']";
			public static final String ProfileCAErrorMsg ="(//*[@id='aadhar']//following::span[@class='help-block with-errors'])[1]";
			public static final String AadharCadInvalidErMsg ="//*[text()='Aadhar number is invalid']";
			public static final String PanCadInvalidErMsg ="//*[text()='PAN number is invalid']";

	
	
}
