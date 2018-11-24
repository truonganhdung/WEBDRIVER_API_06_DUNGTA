/* Author: truonganhdung
 * Created Date: 11/23/2018
 * Modified Date: ../../2018
 * */

package java_basic;

public class Java_Exercise {

	static String source = "Automation 01 Testing 345 Tutorials Online 123456";
	static String lowerCase;

	public static void main(String[] args) {

		lowerCase = source.toLowerCase();

		 countCharacter(lowerCase);
		 checkContain(lowerCase, "testing");
		 checkStart(lowerCase, "automation");
		 checkStart(lowerCase, "online");
		 getIndex(lowerCase, "tutorials");
		 replaceString(source, "Online", "Offline");
		 countNumber(lowerCase);
	}

	public static void countCharacter(String source) {
		int count = 0;

		for (int i = 0; i < source.length(); i++) {
			int index = source.indexOf("a");
			source = source.substring(index + 1);
			if (index > -1) {
				count++;
			}
		}
		System.out.println("So luong ki tu la 'a' trong chuoi = " + count);
	}

	public static void checkContain(String source, String check) {
		Boolean checkContain = source.contains(check);
		System.out.println(checkContain);
	}

	public static void checkStart(String source, String check) {
		Boolean checkStart = source.startsWith(check);
		System.out.println(checkStart);
	}

	public static void checkEnd(String source, String check) {
		Boolean checkEnd = source.startsWith(check);
		System.out.println(checkEnd);
	}

	public static void getIndex(String source, String check) {
		int getIndex = source.indexOf(check);
		System.out.println(getIndex);
	}

	public static void replaceString(String source, String oldChar, String newChar) {
		String replaceString = source.replace(oldChar, newChar);
		System.out.println(replaceString);
	}

	public static void countNumber(String source) {
		int count = 0;
		for (int i = 0; i < source.length(); i++) {
			String index = source.substring(i, i + 1);
			try {
				Integer.parseInt(index);
				count++;
			} catch (Exception ex) {
			}
		}
		System.out.println("So luong ki tu la so trong chuoi = " + count);
	}
}
