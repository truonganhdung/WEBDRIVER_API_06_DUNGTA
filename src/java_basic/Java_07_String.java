/* Author: truonganhdung
 * Created Date: 11/23/2018
 * Modified Date: ../../2018
 * */

package java_basic;

public class Java_07_String {

	public static void main(String[] args) {
		/* length-------------------------------------------- */
		System.out.println("/* Length -----------------------*/");

		String auto = "auto testing";

		int length = auto.length();
		System.out.println("Do dai cua chuoi = " + length);
		System.out.println("---------------------------------------\n");

		/* charAt-------------------------------------------- */
		System.out.println("/* charAt -----------------------*/");

		String chuoi = "auto testing tutorials";

		char kitu = chuoi.charAt(0);

		System.out.println("Ki tu = " + kitu);
		System.out.println("---------------------------------------\n");

		/* concat-------------------------------------------- */
		System.out.println("/* concat -----------------------*/");

		String testing = auto.concat(" tutorials");

		System.out.println("Noi chuoi = " + testing);
		System.out.println("---------------------------------------\n");

		/* equals-------------------------------------------- */
		System.out.println("/* equals -----------------------*/");

		boolean compareValue = testing.equals(auto);
		System.out.println("So sanh chuoi = " + compareValue);

		compareValue = auto.equals(auto);
		System.out.println("So sanh chuoi = " + compareValue);

		System.out.println("---------------------------------------\n");

		/* index-------------------------------------------- */
		System.out.println("/* index -----------------------*/");

		int index = testing.indexOf("testing");

		System.out.println("Vi tri chuoi 'testing' = " + index);
		System.out.println("---------------------------------------\n");

		/* substring-------------------------------------------- */
		System.out.println("/* substring -----------------------*/");

		String substringStart = testing.substring(index);
		System.out.println("Chuoi con = " + substringStart);

		String substringStartToEnd = testing.substring(8, 14);
		System.out.println("Chuoi con = " + substringStartToEnd);

		System.out.println("---------------------------------------\n");

		/* replace-------------------------------------------- */
		System.out.println("/* replace -----------------------*/");

		String money = "$10";
		money = money.replace("$", "");
		System.out.println("Chuoi thay the = " + money);

		double _money = Double.parseDouble(money);
		System.out.println("Convert to Double = " + _money);

		System.out.println("---------------------------------------\n");

		/* split-------------------------------------------- */
		System.out.println("/* split -----------------------*/");

		String text = "Viewing 72 of 1000 results";

		String[] substring = text.split(" ");
		for (int i = 0; i < substring.length; i++) {
			System.out.println("Vi tri thu = " + i + ": " + substring[i]);
		}

		System.out.println(Extract_Number_From_String(text, 1));
		System.out.println(Extract_Number_From_String(text, 3));

		System.out.println("---------------------------------------\n");

		/* upper/lower-------------------------------------------- */
		System.out.println("/* upper/lower -----------------------*/");

		String upper = testing.toUpperCase();
		System.out.println(upper);
		
		String lower = testing.toLowerCase();
		System.out.println(lower);

		System.out.println("---------------------------------------\n");
		
		/* trim-------------------------------------------- */
		System.out.println("/* trim -----------------------*/");

		testing = "          \t      \n  auto testing tutorials                \n";
		System.out.println(testing);
		
		String trim = testing.trim();
		System.out.println(trim);
		
		boolean check = trim.equals("auto testing tutorials");
		System.out.println("Check after trimming = " + check);

		System.out.println("---------------------------------------\n");
		
		/* convert int./string or double/string or versa----------------- */
		System.out.println("/* convert -----------------------*/");

		int number = 70;
		String value = String.valueOf(number);
		System.out.println("Value string = " + value);
		value = number + "";
		System.out.println("Value string = " + value);
		
		String price = "1000";
		int _price = Integer.parseInt(price);
		System.out.println("Value Integer = " + _price);
	}

	public static int Extract_Number_From_String(String text, int number) {
		String[] substring = text.split(" ");
		int result = Integer.parseInt(substring[number]);
		return result;
	}
}
