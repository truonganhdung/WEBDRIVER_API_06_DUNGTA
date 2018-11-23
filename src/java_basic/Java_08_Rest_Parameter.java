/* Author: truonganhdung
 * Created Date: 11/23/2018
 * Modified Date: ../../2018
 * */

package java_basic;

public class Java_08_Rest_Parameter {

	public static void main(String[] args) {
		String locator_00 = "//div[text() = 'New Customer']";
		String locator_01 = "//div[text() = '%s']";
		String locator_02 = "//div[text() = '%s']//button[text()='%s']";
		String locator_03 = "//div[text() = '%s']//button[text()='%s']//a[text()='%s']";
		String locator_04 = "//div[text() = '%s']//button[text()='%s']//a[text()='%s']//button[text()='%s'";

		clickToElement(locator_00);
		clickToElement(locator_01, "New Customer");
		clickToElement(locator_02, "New Customer", "Save");
		clickToElement(locator_03, "New Customer", "Save", "Cancel");
		clickToElement(locator_04, "New Customer", "Save", "Cancel", "Delete");

	}

	public static void clickToElement(String locator) {
		System.out.println(locator);
	}

	public static void clickToElement(String locator, String value) {
		locator = String.format(locator, value);
		System.out.println(locator);
	}

	public static void clickToElement(String locator, String value01, String value02) {
		locator = String.format(locator, value01, value02);
		System.out.println(locator);
	}

	public static void clickToElement(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		System.out.println(locator);
	}
}
