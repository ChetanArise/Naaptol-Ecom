package pom;

public class RemoveComma {
	 public String removeCommaFromString(String s) {     
			String n ="";
			for(int i=0;i<s.length();i++)
			{
				if(s.charAt(i)!= ',')
				{
					n= n+s.charAt(i);
				}
			}
			return n;                                    
	 }
}
