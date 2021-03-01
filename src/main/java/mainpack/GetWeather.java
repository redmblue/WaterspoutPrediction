package mainpack;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

public class GetWeather {
    public static ArrayList<String> arr = new ArrayList<>();

    /*public static ArrayList<String> main(String[] args) {
        try {
            URL myurl = new URL("https://www.aviationweather.gov/adds/metars/index?submit=1&station_ids=KMCD&chk_metars=on&chk_tafs=on&hoursStr=1&std_trans=translated");
            HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
            con.setRequestProperty ( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0" );
            Scanner sc = new Scanner(con.getInputStream());
            String totstring = "";
            while (sc.hasNextLine()) {
                //System.out.println(sc.nextLine());
                totstring+=sc.nextLine();
            }
            System.out.println(totstring);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
     */
    public static String getDMY(String ym, String d){
        //2010-01-02
        char[] temp = ym.toCharArray();
        String yr = temp[0] + "" + temp[1] +""+temp[2]+""+temp[3];
        System.out.println(yr);
        if (Integer.parseInt(d) > 9)
            return yr +"-"+temp[4]+temp[5]+"-"+d;
        else
            return yr+"-"+temp[4]+temp[5]+"-"+"0"+d;
    }
    /*public static String MetarSearch(String time, String ym, String d){
        int counter = 0;
        for (ArrayList<String> i: arr){
            if (i.get(0).contains(getDMY(ym, d)+" " +time)){
                return i.get(1);
            }
        }
        String[] temparr = time.split(":");
        String str= String.valueOf(Integer.parseInt(temparr[1]) + 1);
        return MetarSearch(temparr[0]+":"+str, ym, d);
    }
    public static String MetarSearch(String date, String time){
        int counter = 0;
        for (ArrayList<String> i: arr){
            if (i.get(0).contains(date+ " " +time)){
                return i.get(1);
            }
        }
        String[] temparr = time.split(":");
        String str= String.valueOf(Integer.parseInt(temparr[1]) + 1);
        return MetarSearch(date, temparr[0]+":"+str);
    }
     */
    public static String getMetar(String airptag) {
        try {
            URL myurl = new URL("https://www.aviationweather.gov/adds/metars/index?submit=1&station_ids="+airptag +"&chk_metars=on&chk_tafs=on&hoursStr=1&std_trans=translated");
            HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
            con.setRequestProperty ( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0" );
            Scanner sc = new Scanner(con.getInputStream());
            String totstring = "";
            while (sc.hasNextLine()) {
                totstring+=sc.nextLine();
            }
            //System.out.println(totstring);
            Pattern pattern = Pattern.compile("<strong>(.*?)</strong>", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(totstring);
            int runs = 0;
            while(matcher.find()) {
                runs++;
                if(runs==2) {
                    return(totstring.substring(matcher.start()+8, matcher.end()-9));
                    //break;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static ArrayList<ArrayList<String>> gethistory() {
        return null;
    }

    /*
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
            String s = bw.toString();
            System.out.println(s);
            in.close();
            bw.close();

        } catch(Exception e) {
            System.out.println("Hello.");
        }

    }
    */

}
