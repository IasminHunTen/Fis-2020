package register;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public abstract class User {

	protected String Name;
	protected String Password;
	protected byte[] Salt;
	
	
	public User() {
		
	}
	
	public User(String Name, String Password) {
		this.Name=Name;
		
		try {
			this.Salt= getSaltGenerator() ;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.Password=SHA_256(Password,Salt);
	}
	
	public byte[] getSalt() {
		return Salt;
	}

	@Override
	public boolean equals(Object obj) {// presupunem ca nu acceptam 2 useri cu acelasi nume
		if (obj instanceof User)
		{
			User u=(User)obj;
			if(this.getName().equals(u.getName()))
				return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return Name.hashCode();
	}

	
	
	@Override
	public String toString() {
		return "User [Name=" + Name + ", Password=" + Password + ", Salt=" + Arrays.toString(Salt) + "]";
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}

	public void setSalt(byte[] salt) {
		Salt = salt;
	}
	
	public boolean checkUsername(String usernameTest) {
		if (Name.equals(usernameTest))
			return true;
		else
			return false;
	}

	public boolean checkPassword(String passwordTest) { // verifica daca stringul introdus incriptat e identic cu parola incriptata
		String hashedPassword=SHA_256(passwordTest,Salt);
		if (hashedPassword.equals(Password))
			return true;
		else
			return false;
	}


	private String SHA_256(String plainPassword,byte[] sal) {
		  {
		        String generatedPassword = null;
		        try {
		            MessageDigest md = MessageDigest.getInstance("SHA-1");
		            md.update(sal);
		            byte[] bytes = md.digest(plainPassword.getBytes());
		            StringBuilder sb = new StringBuilder();
		            for(int i=0; i< bytes.length ;i++)
		            {
		                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		            }
		            generatedPassword = sb.toString();
		        } 
		        catch (NoSuchAlgorithmException e) 
		        {
		            e.printStackTrace();
		        }
		        return generatedPassword;
		    }
	}
	
	private static byte[] getSaltGenerator() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
	
	
	
}
