package mainpack;
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
public class GetNearestAirport{
    /*public String getAirport (String Path) throws IOException {
        var url = new URL(Path);
        String value;
        try (var br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            ArrayList<String> array = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                array.add(line);
            }
            String air = array.get(191);
            String s1 = air.substring(air.indexOf("(")+1);
            s1.trim();
            String Fair = s1.substring(0, s1.indexOf(")"));
            value = Fair;
            System.out.println(Fair);
        }
        return value;
        // String city = "City City";
        //city.replace(" ", "_");
    }
    */
    public static String airpId(String city, String state) { // note: state is in thhis format: ST
        //city = city.split(" ")[0];
        city.replace(' ','_');
        try {
            String State = getState(state);
            URL myurl = new URL("https://forecast.weather.gov/MapClick.php?CityName=" + city+ "&state=" + State);
            HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
            con.setRequestProperty ( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0" );
            Scanner sc = new Scanner(con.getInputStream());
            String totstring = "";
            while (sc.hasNextLine()) {
                totstring+=sc.nextLine();
            }
            //System.out.println(totstring);
            Pattern pattern = Pattern.compile("<h2 class=\"panel-title\">(.*?)</h2>", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(totstring);
            int runs = 0;
            while(matcher.find()) {
                //runs++;
                //if(runs==2) {

                //return(totstring.substring(matcher.start()+24, matcher.end()-5)); - Gives airport name + Id in parenthesis
                return(totstring.substring(matcher.end()-10, matcher.end()-6));
                //break;
                //}
            }



        }
        catch(Exception e) {
            e.printStackTrace();
        }


        return "";
    }
    public static String getState(String i){
        String[] a = "ALABAMA(AL ALASKA(AK AMERICAN-SAMOA(AS ARIZONA(AZ ARKANSAS(AR CALIFORNIA(CA COLORADO(CO CONNECTICUT(CT DELAWARE(DE DISTRICT-OF-COLUMBIA(DC FLORIDA(FL GEORGIA(GA GUAM(GU HAWAII(HI IDAHO(ID ILLINOIS(IL INDIANA(IN IOWA(IA KANSAS(KS KENTUCKY(KY LOUISIANA(LA MAINE(ME MARYLAND(MD MASSACHUSETTS(MA MICHIGAN(MI MINNESOTA(MN MISSISSIPPI(MS MISSOURI(MO MONTANA(MT NEBRASKA(NE NEVADA(NV NEW-HAMPSHIRE(NH NEW-JERSEY(NJ NEW-MEXICO(NM NEW-YORK(NY NORTH-CAROLINA(NC NORTH-DAKOTA(ND NORTHERN-MARIANA-IS(MP OHIO(OH OKLAHOMA(OK OREGON(OR PENNSYLVANIA(PA PUERTO-RICO(PR RHODE-ISLAND(RI SOUTH-CAROLINA(SC SOUTH-DAKOTA(SD TENNESSEE(TN TEXAS(TX UTAH(UT VERMONT(VT VIRGINIA(VA VIRGINA-ISLANDS(VI WASHINGTON(WA WEST-VIRGINIA(WV WISCONSIN(WI WYOMING(WY".split(" ");
        for (int k = 0; k < a.length; k++){
            if(a[k].contains("-"))
                a[k].replaceAll("-"," ");
        }
        ArrayList<String[]> a2 = new ArrayList<>();
        for (String k: a)
            a2.add(k.split("\\("));
        for(String[] k: a2){
            if (i.equals(k[0]))
                return k[1];
        }
        return "";
    }
}
