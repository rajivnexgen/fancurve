package pageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import fanselect.fancurve.BaseClass;
import utlities.Utility;

public class Login extends BaseClass {
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
	
	@FindBy(id="IDC_NEXT")
	WebElement nextPage;
	
	@FindBy(id="IDC_OUT_CH_KA_LABEL")// choose chapter
	WebElement chooseChapter;
	
	@FindBy(id="*.DV.AUSGABE.IDC_OUT_CH_PD_LABEL")// general desc
	WebElement genDescChk;
	
	@FindBy(id="*.DV.AUSGABE.IDC_OUT_CH_SB_LABEL")// wiring diagram check
	WebElement wreDiagChk;
	
	@FindBy(id="*.DV.AUSGABE.IDC_OUT_CH_MB_LABEL")// drawing check
	WebElement drawChk;
	
	@FindBy(id="*.DV.AUSGABE.IDC_OUT_CH_ZB_LABEL")// system component check
	WebElement sysChk;
	
	@FindBy(id="IDC_OUT_RTF")// rtf button element
	WebElement rtfBtn;
	
	@FindBy(xpath="//a[@tabpanel='IDC_TAB_VENTILATORAUSWAHL']") //fan selection button
	WebElement fanSelButton;
	
	@FindBy(id="IDC_ZA02_TABLE_1_7")
	WebElement cfmVerify;
	
	@FindBy(id="IDC_AUSGABE_LIST_TABLE_1_6")
	WebElement cfmVerifyOutput;
	
	public Login() {
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void loginPage() throws InterruptedException {
		
		String [] cfm= {"8000"};
		String [] st= {"650"};
		String art_no="118560/A11";
		int chkFlag=0;
		usLang.click();
		userField.sendKeys("ZAFS27854");
		passField.sendKeys("7t7rlg");
		agbChk.click();
		agbOk.click();
		Thread.sleep(8000);
		zaBluefin.click();
		//Thread.sleep(5000);
		Utility.waitClickableElement(btnFanSel, 10).click();
		
		Select dropdown =Utility.dropDown("VOLUMENSTROM_UNIT");
		
		dropdown.selectByValue("ZA_CFM");
		tot_st.click();
		Thread.sleep(1000);
		
		dropdown =Utility.dropDown("TOTALDRUCK_UNIT");
		dropdown.selectByValue("ZA_PA");
		// loop started
		for(int i=0;i<cfm.length;i++) {
			
		airVolume.clear();
		airVolume.sendKeys(cfm[i]);
		stEdit.clear();
		stEdit.sendKeys(st[i]);
		addSearchCr.click();
		Utility.waitVisibleElement(artEdit, 10);
		artEdit.clear();
		artEdit.sendKeys(art_no);
		
		search.click();
		Thread.sleep(2000);
		System.out.println("input is "+cfm[i]);
		System.out.println("Output is"+cfmVerify.getText().replace(".0", ""));
		Assert.assertEquals(cfm[i], cfmVerify.getText().replace(".0", ""));
		fanSel.click();
		nextPage.click();
		Utility.waitVisibleElement(driver.findElement(By.id("CHART1")), 10);
		Utility.waitVisibleElement(driver.findElement(By.xpath("//a[@tabpanel='IDC_TAB_AUSGABE']")), 10).click();//output link
		if(chkFlag==0) {
		chooseChapter.click();
		genDescChk.click();
		wreDiagChk.click();
		drawChk.click();
		sysChk.click();
		}
		chkFlag=1;
		Assert.assertEquals(cfm[i], cfmVerifyOutput.getText().replace(".0", ""));
		// rtf button click
		Utility.waitVisibleElement(rtfBtn, 7).click();
		Thread.sleep(4000);
		fanSelButton.click();
		}//for loop end
	
	}
	
	
	
}
