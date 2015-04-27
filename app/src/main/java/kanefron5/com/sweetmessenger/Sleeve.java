package kanefron5.com.sweetmessenger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sleeve{

	public static boolean sleeve(int var1, int var2)
	{
		try{
			 URL url = new URL(String.format("https://api.vk.com/method/groups.isMember?group_id=%sweetvkontach&user_id=%Account.user_id", var1, var2));
		     HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
		     urlCon.setRequestMethod("GET");
		     urlCon.setDoOutput(true);
		     BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
		     String line;
		     StringBuilder sb = new StringBuilder();
		     while ((line = br.readLine()) != null) {
		       	sb.append(line);
		     	sb.append("\n");
		     }
		     br.close();
		     return sb.toString().indexOf(":1}") != -1;
	     }
	     catch (Exception e) {
	    	 return false;
	     }
	}
}
