package GeneralPackage;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

	import java.io.*; 
	import java.net.*; 

	class TCPServer {  
	  public static void main(String argv[]) throws Exception { 
		  mySQLdb sql = new mySQLdb();
		  
		  ServerSocket s = null;
		  try {
			//General Connection (encrypted) based on the interface:
			  System.out.println(sql.connect(Decryptor.Decrypt("DfHDuWgegpdkOwBL2kAyJQ=="), Decryptor.Decrypt("UvuZ3Q+MCNLuFrotRvCDwQ=="), Decryptor.Decrypt("GZeU/Hjjvz3ZbsoBqNHp+A=="), Decryptor.Decrypt("nrP6hmGba2fGvpvtxzGJPg=="), Decryptor.Decrypt("EzQrYJCpoiQQmOaGC9oHBA==")));
			  s = new ServerSocket(10000);
			  System.out.println("Server is Ready!");
			  
			} catch(IOException e) {
			    System.out.println(e);
			    System.exit(1);
			}
		  		while (true) {
		  			Socket incoming = null;
			    
		  			try {
		  				incoming = s.accept();	 //start task		
		  			} catch(IOException e) {
		  				System.out.println(e);
		  				continue;
		  			}
			    	
			    new socketHandler(incoming , sql).start();
			    System.out.println(incoming);
			} 
	    }
	} 
	
	
	