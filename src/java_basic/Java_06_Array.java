/* Author: truonganhdung
 * Created Date: 11/23/2018
 * Modified Date: ../../2018
 * */

package java_basic;

public class Java_06_Array {

	public static void main(String[] args) {
		/* Init array--------------------------------------------*/
		String[] test = new String[2];
		test[0] = "auto";
		test[1] = "mation";
		
		String[] testing = {"auto", "mation", "testing"};
		
		
		/* Access value in array--------------------------------------------*/
		System.out.println("/* Access value in array -----------------------*/");

		int iLen = testing.length;
		System.out.println("Do dai cua mang = " + iLen);
		
		String _1st = testing[0];
		System.out.println("Gia tri dau tien cua mang = " + _1st);
		
		String _end = testing[iLen - 1];
		System.out.println("Gia tri cuoi cung cua mang = " + _end);
		System.out.println("---------------------------------------");
		
		for (int i = 0; i <= iLen - 1; i++) {
			System.out.println("Phan tu [" + i + "] trong Array = " + testing[i]);
		}
		System.out.println("---------------------------------------");
		
		for (String testForEach: testing) {
			System.out.println("Gia tri = " + testForEach);
		}
		System.out.println("---------------------------------------\n");
		
		
		/* Parse array to method --------------------------------------------*/
		System.out.println("/* Parse array to method ------------------------*/");

		Parse_Array_To_Method(test);
		System.out.println("---------------------------------------");
		Parse_Array_To_Method(testing);
		
		System.out.println("---------------------------------------\n");
		
		
		/* Return array from method --------------------------------------------*/
		System.out.println("/* Return array from method ------------------------*/");
		
		String[] newArray = Return_Array_From_Method();
		for (String array: newArray) {
			System.out.println("In tat ca cac phan tu trong array = " + array);
		}
	}

	public static void Parse_Array_To_Method(String[] array) {
		for (int i = 0; i <= array.length - 1; i++) {
			System.out.println("In tat ca cac phan tu trong array = " + array[i]);
		}
	}
	
	public static String[] Return_Array_From_Method() {
		String[] aRRay = {"new", "array"};
		return aRRay;
	}
}
