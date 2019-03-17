package GeneralPackage;

import java.sql.Statement;
import java.util.ArrayList;

import GeneralPackage.apiImport.question;

public interface DBAccessInterface {
		
	public String connect(String IP,String Port, String DBname, String User, String Pass);
	public void query(String query, String action) throws Exception; // need to delete the action ( check the first word in query select/update)
	public void queryImportlb(String user, String difficulty, int score) throws Exception;
	public ArrayList queryExportlb(String difficulty);
	public ArrayList exportQueryApi(String diff);
	public String disconnect();
}