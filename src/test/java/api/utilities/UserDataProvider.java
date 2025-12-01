package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class UserDataProvider {
	
	
	@DataProvider(name="userdata")
	public String[][] getUserData() throws IOException {
		
		String path = System.getProperty("user.dir")+"/testData/userData.xlsx";
		
		XLUtility excel = new XLUtility(path);
		
		
		String data[][] = new String[excel.getRowCount("Sheet1")][excel.getCellCount("Sheet1",0)];
		
		
		for(int i=1; i<=excel.getRowCount("Sheet1"); i++) {
			
			for(int j=0; j<excel.getCellCount("Sheet1",i); j++) {
				
				data[i-1][j]= excel.getCellData("Sheet1", i, j);
			}
			
		}
		
		return data;
	}
	
	
	
	@DataProvider(name="username")
	public String[] getUsername() throws IOException {
		
		String path = System.getProperty("user.dir")+"/testData/userData.xlsx";
		
		XLUtility excel= new XLUtility(path);
		String data[] = new String[excel.getRowCount("Sheet1")];
		
		for(int i=1;i<=excel.getRowCount("Sheet1");i++) {
			
			data[i-1]=excel.getCellData("Sheet1", i, 1);
		}
		
		
		return data;
		
	}

}
