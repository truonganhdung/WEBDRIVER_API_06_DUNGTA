/* Author: truonganhdung
 * Created Date: 10/24/2018
 * Modified Date: ../../2018
 * */

package guru99.III_X_Data_Driven.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {
	Properties p = new Properties();
 
	public Properties getObjectRepository() throws IOException{
		InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\III_X_Data_Driven_objects\\object.properties"));
		p.load(stream);
			return p;
	}
}