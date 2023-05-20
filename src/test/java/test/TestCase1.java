package test;

import org.testng.annotations.Test;

import fanselect.fancurve.BaseClass;
import pageObject.Login;

public class TestCase1 extends BaseClass {
	@Test
	public  void test1() throws InterruptedException {
		driverLoad();
		Login lg= new Login();
		lg.loginPage();
		
	}
}
