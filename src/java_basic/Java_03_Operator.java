/* Author: truonganhdung
 * Created Date: 11/23/2018
 * Modified Date: ../../2018
 * */

package java_basic;

public class Java_03_Operator {

	public static void main(String[] args) {
		/* Assign------------------------------- */
		int time = 10;
		String name = "abc";
		boolean value = true;

		System.out.println(time);
		System.out.println(name);
		System.out.println(value);

		System.out.println("====================");

		/* Math------------------------------- */
		int a, b = 10, c = 5;
		a = b + c;
		System.out.println("Cong: " + a);

		a = b - c;
		System.out.println("Tru: " + a);

		a = b / c;
		System.out.println("Chia lay nguyen: " + a);

		a = b % c;
		System.out.println("Chia lay du: " + a);

		b++;
		System.out.println("tang 1: " + b);

		c--;
		System.out.println("giam 1: " + c);

		System.out.println("====================");

		/* Relation------------------------------- */
		int ten = 10;
		int twenty = 20;
		int thirty = 30;

		System.out.println("Lon hon = " + (ten > twenty));
		System.out.println("Lon hon hoac bang = " + (thirty >= ten));
		System.out.println("Nho hon = " + (thirty < twenty));
		System.out.println("Nho hon hoac bang = " + (ten <= thirty));
		System.out.println("Bang bang = " + (thirty == twenty + ten));
		System.out.println("Khac bang = " + (thirty != ten));

		System.out.println("====================");

		/* Logic------------------------------- */
		boolean value1 = true;
		boolean value2 = false;

		System.out.println("Ca 2 deu dung = " + (value1 && value2));
		System.out.println("1 trong 2 dung = " + (value1 || value2));
		System.out.println("Phu dinh = " + (!value1));

		System.out.println("====================");

		/* Condition------------------------------- */
		int _1st = 10;
		int _2nd = 20;
		int _3rd = 30;
		boolean bValue;
		int iValue;

		bValue = (_3rd == _1st + _2nd) ? true : false;
		System.out.println("Gia tri = " + bValue);
		
		iValue = (_3rd == _1st + _2nd) ? 50 : 100;
		System.out.println("Gia tri = " + iValue);
		
		iValue = (!(_3rd == _1st + _2nd)) ? 100 : 50;
		System.out.println("Gia tri = " + iValue);
		
		System.out.println("====================");
	}
}
