package wundergroundstuff;
/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
*/
import java.io.*;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetWeather {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    try {
	        //URL oracle = new URL("https://www.wunderground.com/history/daily/us/mi/pellston/KMCD/date/2000-1-2");
	        //String httpsURL = "https://www.wunderground.com/history/daily/us/mi/pellston/KMCD/date/2000-1-2";
	    	//  https://api.weather.gov/stations/{stationId}/observations
	    	//String httpsURL = "https://www.ogimet.com/display_metars2.php?lang=en&lugar=KMCD&tipo=ALL&ord=REV&nil=SI&fmt=html&ano=2010&mes=01&day=02&hora=02&anof=2010&mesf=01&dayf=03&horaf=02&minf=59&send=send";
	        
	    	//String httpsURL = "https://api.weather.gov/stations/KMCD/observations";
	       
	    	//"https://api.weather.gov/stations/KMCD/observations/2020-12-26T23:15:00+00:00"
	        //String httpsURL = "https://api.weather.gov/stations/KMCD/observations/2020-3-16T13:27:00+00:00";
	    	String httpsURL = "https://api.weather.gov/stations/KMCD/observations/2020-12-26T23:15:00+00:00";
	    	String FILENAME = "C:\\Users\\Daniel\\eclipse-workspace\\WaterSpout Prediction\\src\\wundergroundstuff\\temp.txt";
	        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
	        URL myurl = new URL(httpsURL);
	        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
	        con.setRequestProperty ( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0" );
	        InputStream ins = con.getInputStream();
	        InputStreamReader isr = new InputStreamReader(ins, "Windows-1252");
	        BufferedReader in = new BufferedReader(isr);
	        String inputLine;

	        // Write each line into the file
	        while ((inputLine = in.readLine()) != null) {
	            System.out.println(inputLine);
	            bw.write(inputLine);
	        }
	        in.close(); 
	        bw.close();
	        
	        } catch(Exception e) {
	        	System.out.println("Hello.");
	        }

	}

}
