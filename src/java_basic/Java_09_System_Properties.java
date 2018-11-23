/* Author: truonganhdung
 * Created Date: 11/23/2018
 * Modified Date: ../../2018
 * */

package java_basic;

public class Java_09_System_Properties {

	public static void main(String[] args) {
		String rootFolder = System.getProperty("user.dir");
		
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");
		
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.version"));
		System.out.println(System.getProperty("user.name"));
	}
}
