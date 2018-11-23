/* Author: truonganhdung
 * Created Date: 11/23/2018
 * Modified Date: ../../2018
 * */

package java_basic;

public class Java_01_Data_Types {

	public static void main(String[] args) {
		/* =================== boolean =================== */
		boolean testResult;
		
		testResult = true;
		System.out.println("Ket qua dung = " + testResult);
		
		testResult = false;
		System.out.println("Ket qua sai = " + testResult);
		
		System.out.println("====================");
		
		/* =================== int =================== */
		int cart = 2;
		
		testResult = true;
		System.out.println("So luong gio hang = " + cart);
		
		cart = cart + 10;
		System.out.println("So luong gio hang = " + cart);
		
		System.out.println("====================");
		
		/* =================== double =================== */
		double ketqua = 8.60;
		
		testResult = true;
		System.out.println("Ket qua = " + ketqua);
		
		System.out.println("====================");
		
		/* =================== String =================== */
		String name, address;
		name = "abc"; address = "xyz";
		
		System.out.println(name);
		System.out.println(address);
	}

}
