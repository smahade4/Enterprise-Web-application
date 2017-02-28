package assign3;

import java.text.*;
import java.io.*;

import java.util.Date;

public class LoginDetails implements Serializable
{
int UserId;
String Username;
String Password;
String FirstName;
String LastName;
String EmailId;
String PhoneNo;

 public int getUserId() {
        return UserId;
    }


    public void setUserId(int UserId) {
       this.UserId = UserId;
    }

	public String getUsername() {
        return Username;
    }


    public void setUsername(String Username) {
       this.Username = Username;
    }


    public String getPassword() {
        return Password;
    }


    public void setPassword(String Password) {
       this.Password = Password;
    }

    public String getFirstName() {
        return FirstName;
    }


    public void setFirstName(String FirstName) {
       this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }


    public void setLastName(String LastName) {
       this.LastName = LastName;
    }

    public String getEmailId() {
        return EmailId;
    }


    public void setEmailId(String EmailId) {
       this.EmailId = EmailId;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }


    public void setPhoneNo(String PhoneNo) {
       this.PhoneNo = PhoneNo;
    }


}