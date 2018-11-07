package testNG;

import org.testng.annotations.Test;

public class _0_Priority {
	@Test(groups = "1", priority = 2)
	public void TC07() {
		System.out.println("TC07");
	}

	@Test(groups = "3", priority = 1)
	public void TC08() {
		System.out.println("TC08");
	}

}
