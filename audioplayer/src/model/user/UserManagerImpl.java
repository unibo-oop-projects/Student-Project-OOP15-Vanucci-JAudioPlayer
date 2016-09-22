package model.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import model.FileHandler;
import model.FileHandlerImpl;

public class UserManagerImpl{
	
	private static User currentUser = new UserImpl();
	
	private UserManagerImpl(){}
	
	public static void setUser(String username, String password){
		currentUser.setUsername(username);
		currentUser.setPassword(password);
	}
	
	public static User getUser(){
		return currentUser;
	}
	
	public static boolean userExists(String username, String password) throws FileNotFoundException, IOException{
		
		FileHandler handler = new FileHandlerImpl();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(handler.getFile("users.txt")))) {
			for(String line; (line = br.readLine()) != null; ) {
				if(line.contains("username: "+username) && line.contains("password: "+password)){
					String userDir = handler.getMainDir();
					if(!new File(userDir+username).exists()){
						new File(userDir+username).mkdir();
					}
					return true;
				}
			}
			return false;
		}
	}
}
