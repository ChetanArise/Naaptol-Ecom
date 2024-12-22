package pom;

public class RemoveComma {
	 public String removeCommaFromString(String s) {     
			String  modified="";
			for(int i=0;i<s.length();i++)
			{
				if(s.charAt(i)!= ',')
				{
				  modified= modified+s.charAt(i);
				}
			}
			return modified;                                    
	 }
}
