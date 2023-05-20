package utlities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import fanselect.fancurve.BaseClass;

public class Utility extends BaseClass {
	
	
public static Select dropDown(String idDropDown) {
	WebDriverWait wait = new WebDriverWait(driver,10);
	WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idDropDown))); //driver.findElement(By.id(idDropDown));
	Select dropDown= new Select(select);
	return dropDown;
	
}
public static WebElement waitClickableElement(WebElement element,int time) {
	WebDriverWait wait = new WebDriverWait(driver,time);
	//WebElement elmnt=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
	
	return wait.until(ExpectedConditions.elementToBeClickable(element));

}
public static WebElement waitVisibleElement(WebElement element,int time) {
	WebDriverWait wait = new WebDriverWait(driver,time);
	//WebElement elmnt=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
	
	return wait.until(ExpectedConditions.visibilityOf(element));

}

}
