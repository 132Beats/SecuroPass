import java.security.MessageDigest;

public abstract class SecuroPass {
	static final String chars="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWYXZ.,";
	static final String hexes="0123456789abcdef";
	static final int charCount = 32;
	public static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    } 
	    catch(Exception ex){
	    	throw new RuntimeException(ex);
	    }
	}
	public static String generatePassword(String inputString) {
		String str="";
		for(int i=0;i<charCount;i++) {
			str=str+chars.charAt((hexes.indexOf(sha256(inputString).charAt(i*2)))*4+(hexes.indexOf(sha256(inputString).charAt(i*2+1))%4));
		}
		return str;
	}
}
