package pageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import fanselect.fancurve.BaseClass;
import utlities.Utility;

public class FanDetail extends BaseClass{
	 static int glCounter;
	int globalRow=0, globalCol=0;
	
	@FindBy(xpath="//img[@id='IDC_LAN_EN']")
	WebElement usLang;
	
	@FindBy(xpath="//input[@id='IDC_EDIT_USERID']")
	WebElement userField;
	
	@FindBy(xpath="//input[@id='IDC_EDIT_PASSWD']")
	WebElement passField;
	
	@FindBy(xpath="//input[@id='IDC_AGB_CHECK']")
	WebElement agbChk;
	
	@FindBy(xpath="//button[@id='IDC_AGB_OK']")
	WebElement agbOk;
	
	@FindBy(xpath="//img[@id='IDC_BILD_3']")
	WebElement zaBluefin;
	
	@FindBy(xpath="//button[@id='IDC_GOTO_ZA02']")
	WebElement btnFanSel;
	
	@FindBy(xpath="//td[@id='IDC_ZA02_BP_TABLE_STATIDRUCK_1']")
	WebElement tot_st;
	
	@FindBy(xpath="//input[@id='VOLUMENSTROM_EDIT']")
	WebElement airVolume;
	
	@FindBy(xpath="//input[@id='TOTALDRUCK_EDIT']")
	WebElement stEdit;
	
	@FindBy(id="IDC_WAB")
	WebElement addSearchCr;
	
	@FindBy(id="ZA_ARTIKELNR_EDIT")
	WebElement artEdit;
	
	@FindBy(id="IDC_SEARCH")
	WebElement search;
	
	@FindBy(id="FC_IDC_ZA02_TABLE_1_0")
	WebElement fanSel;
	
	@FindBy(id="IDC_ZA02_TABLE_1_7")
	WebElement getCfm;
	
	@FindBy(id="IDC_ZA02_TABLE_1_9")
	WebElement getStat;
	
	@FindBy(id="IDC_ZA02_TABLE_1_19")
	WebElement getRpm;
	
	@FindBy(id="IDC_ZA02_TABLE_1_13")
	WebElement getEff;
	
	@FindBy(id="IDC_ZA02_TABLE_1_12")
	WebElement getWatt;
	
	public FanDetail() {
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void search(String cfm, String st, String art_no) throws InterruptedException {
		String retCfm="", reStat="", rpm="", eff="", kw="";
Select dropdown =Utility.dropDown("VOLUMENSTROM_UNIT");
		
		dropdown.selectByValue("ZA_CFM");
		if(chkTotStatic==0) {
		tot_st.click();
		chkTotStatic=1;
		}
//		Thread.sleep(1000);
		
		
		dropdown =Utility.dropDown("TOTALDRUCK_UNIT");
		dropdown.selectByValue("ZA_PA");
		// loop started
//		for(int i=0;i<cfm.length;i++) {
//		Thread.sleep(500);
		airVolume.clear();
		airVolume.sendKeys(cfm);
		stEdit.clear();
		stEdit.sendKeys(st);
//		Thread.sleep(500);
		addSearchCr.click();
		try {
		artEdit.clear();
//		Thread.sleep(500);
		artEdit.sendKeys(art_no);
		}
		catch(StaleElementReferenceException e) {
			PageFactory.initElements(driver, this);
			artEdit.clear();
//			Thread.sleep(500);
			artEdit.sendKeys(art_no);
		}
		search.click();
//		Thread.sleep(2000);
		for(int i=1;i<=12;i++) {
		try {
			 
			retCfm=getCfm.getText().replace(".0", "");
			reStat=getStat.getText();
			rpm = getRpm.getText();
			eff = getEff.getText();
			kw = getWatt.getText();
			Assert.assertEquals(retCfm,cfm);
			break;
				
			}
		catch(AssertionError ex) {
			Thread.sleep(300);
			continue;
		}
		catch(StaleElementReferenceException e) {
			PageFactory.initElements(driver, this);
			retCfm=getCfm.getText();
			reStat=getStat.getText();
			rpm = getRpm.getText();
			eff = getEff.getText();
			kw = getWatt.getText();
		}
		}
		Assert.assertEquals(retCfm,cfm);
		
	
//	Thread.sleep(2000);
	System.out.println("cfm-"+retCfm+" stat-"+reStat+" rpm-"+rpm+" eff-"+eff+" watt"+kw+" couter- "+glCounter);
	glCounter=glCounter+1;
	}
}
