/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;
 
public class ExtractInfo {
    
    private final String USER_AGENT = "Mozilla/5.0";
 
  public static void main(String[] args) throws Exception {
 	
        ExtractInfo a = new ExtractInfo();
        a.parseDetails();
        
        //a.sendGet();
  }
 
  //***********************Parses lat, long, date and time from CSV file. Also, makes the api call and gets the weather details using these parameters.************************
  public void parseDetails() {
 
	String csvFile = "C:\\RIT Project\\Project related files\\Data\\Latest\\Test.csv";
	BufferedReader br = null;
	String line = "";
	String splitBy = ",";
        
        int i = 0;
        String tempF ;
        String windMiles ;
        String precipMM ;
        String humidity ;
 
	try {
 
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) 
                {
                        i++;
                    
			String[] lat_long = line.split(splitBy);
 
			System.out.println("Latitude= " + lat_long[3] + " , Longitude=" + lat_long[4] );
                            
                            if(i > 1)  {
                                
                                if(lat_long[3].contains("null") || lat_long[4].contains("null")) {
                                  
                                  tempF = null;
                                  windMiles = null;
                                  precipMM = null;
                                  humidity = null;
                              }
                                
                                    else {

                                        String url = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?q="+lat_long[3]+","+lat_long[4]+"&date="+lat_long[5]+"&format=csv&key=3a5339e4a169c256c99dc73e758bcd280351cef2";

                                        URL obj = new URL(url);
                                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                                        con.setRequestMethod("GET");

                                        con.setRequestProperty("User-Agent", USER_AGENT);

                                        BufferedReader in = new BufferedReader(
                                                new InputStreamReader(con.getInputStream()));
                                        String inputLine;
                                        StringBuffer response = new StringBuffer();

                                        while ((inputLine = in.readLine()) != null) {
                                                response.append(inputLine);
                                        }
                                        in.close();

                                        response.delete(0, 597);

                                        String[] details = response.toString().split(splitBy);

                                        tempF = details[3];
                                        windMiles = details[4];
                                        precipMM = details[11];
                                        humidity = details[12];

                                        System.out.println(tempF);
                                        System.out.println(windMiles);
                                        System.out.println(precipMM);
                                        System.out.println(humidity);
                              }                            
                              }
 
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 
 
  
  }
 
}
