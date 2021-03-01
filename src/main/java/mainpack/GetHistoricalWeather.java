package mainpack;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
public class GetHistoricalWeather {
    public static String dayAfter(int mon, int day, int year) {
        int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
        if((year%4==0&&(!(year%100==0)||year%400==0))) {
            days[1] = 29;//2-1 = 1
        }
        int daytobe = day+1;
        int daysinmon = 0;
        switch(mon) {
            case 1:
                daysinmon = days[0];
                break;
            case 2:
                daysinmon = days[1];
                break;
            case 3:
                daysinmon = days[2];
                break;
            case 4:
                daysinmon = days[3];
                break;
            case 5:
                daysinmon = days[4];
                break;
            case 6:
                daysinmon = days[5];
                break;
            case 7:
                daysinmon = days[6];
                break;
            case 8:
                daysinmon = days[7];
                break;
            case 9:
                daysinmon = days[8];
                break;
            case 10:
                daysinmon = days[9];
                break;
            case 11:
                daysinmon = days[10];
                break;
            case 12:
                daysinmon = days[11];
                break;
            default:
                System.out.println("Wrong month formatting/number");
                return "Wrong month formatting/number";
        }
        if(daytobe>daysinmon) {
            if(mon==12) {
                return("1/1/"+(year+1));
            }
            else {
                return((mon+1)+"/1/"+year);
            }
        }
        else {
            return ((mon)+"/"+(day+1)+"/"+year);
        }
        //return "";
    }
    /*
    public static String getHistoricalWeather(String airptag, int begmon, int begday, int begyear, int endday, int endmon, int endyear) {
        try {
            URL myurl = new URL("https://mesonet.agron.iastate.edu/cgi-bin/request/asos.py?station=EST&data=metar&year1=2021&month1=1&day1=1&year2=2021&month2=2&day2=27&tz=Etc%2FUTC&format=onlycomma&latlon=no&elev=no&missing=M&trace=T&direct=no&report_type=1&report_type=2");
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
    */
    public static String getHistoricalWeather(String airptag, int mon, int day, int year, String timeUTC) {//00:00
        try {
            //round time to nearest 5 mins since that's when all of the ASOSs were captured
            System.out.println(mon+"/"+day+"/"+year);
            String unspdate = dayAfter(mon,day,year);
            String[] arr = unspdate.split("/");
            for(String s:arr){
                System.out.println("Arr is " + s);
            }
            System.out.println("Arr 2 is" + arr[2]);
            URL myurl = new URL("https://mesonet.agron.iastate.edu/cgi-bin/request/asos.py?station=" + airptag + "&data=metar&year1="+year+"&month1="+mon+"&day1="+day+"&year2="+arr[2]+"&month2="+arr[0]+"&day2="+arr[1]+"&tz=Etc%2FUTC&format=onlycomma&latlon=no&elev=no&missing=M&trace=T&direct=no&report_type=1&report_type=2");
            HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
            con.setRequestProperty ( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0" );
            Scanner sc = new Scanner(con.getInputStream());
            String totstring = "";
            while (sc.hasNextLine()) {
                totstring+=sc.nextLine();
            }
            //System.out.println(totstring);
            String arre[] = timeUTC.split(":");
            int a = Integer.parseInt(arre[1]);
            int lastdig = a%10;
            System.out.println(a);
            if(Math.abs(5-lastdig)==5) {
                //don't round
            }
            else if((lastdig<=5&&(5-lastdig>=3))||lastdig>5&&(10-lastdig>=3)) {
                //round down
                //int diff = Math.abs(5-lastdig);
                a-=lastdig;
            }
            else {
                //int diff = Math.abs(5-lastdig);
                a+=lastdig;
                //round up
            }
            //String.format("%s:%2f", arre[0],a);
            String s = "";
            if(a<10) {
                s=arre[0]+":0"+a;
            }
            else {
                s = arre[0]+":"+a;
            }
            //arre[0]+":"+a;
            //System.out.println(s);
            //System.out.println(year);
            Pattern pattern = Pattern.compile(s+"(.*?),"+year, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(totstring);
            int runs = 0;
            int idstart = 0;
            int idend=0;
            while(matcher.find()) {
                //runs++;
                //if (runs==1) {
                //	idstart= matcher.start();
                //}
                //else if(runs==3) {
                return(totstring.substring(matcher.start()+6, matcher.end()-5));
                //	idend=matcher.end();
                //	break;
                //}
            }
            System.out.println(totstring.substring(idstart, idend));
            //return(totstring.substring(idstart, idend));
            return "Error";


        }
        catch(Exception e) {
            e.printStackTrace();
            return "Error";
        }


        //return "Error";
    }

    public static String getHistoricalWeather(String Airport_Tag,String TimeUTC) {

        try {
            String mainarrtime[] = TimeUTC.split(" ");
            System.out.println(TimeUTC);
            //String secondtimearr[] = mainarrtime[1].split(":");
            String firsttimearr[] = mainarrtime[0].split("-");
            for(String df:firsttimearr){
                System.out.println("First time arr is" + df);
            }
            System.out.println("Time sending is " + mainarrtime[1]);
            return(getHistoricalWeather(Airport_Tag.toUpperCase(Locale.ROOT),Integer.parseInt(firsttimearr[1]),Integer.parseInt(firsttimearr[2]),Integer.parseInt(firsttimearr[0]),mainarrtime[1]));
        }
        catch(Exception e) {
            return ("Error in formatting");
        }
        //return "";
    }
    /*
    public static void main(String[] args) {
        System.out.println(getHistoricalWeather("KPTK",03,20,2020,"10:10"));
        System.out.println(getHistoricalWeather("KPTK",03,20,2020,"10:05"));
        System.out.println(getHistoricalWeather("KPTK","2018-01-02 07:52"));
        //Make a thing that waits 1 minute after being ratelimited
        //Make algo that gets the next day(if last of the month, either delete or have a string switch that switches it to the next month)
        //https://mesonet.agron.iastate.edu/cgi-bin/request/asos.py?station=EST&data=metar&year1=2021&month1=1&day1=1&year2=2021&month2=2&day2=27&tz=Etc%2FUTC&format=onlycomma&latlon=no&elev=no&missing=M&trace=T&direct=no&report_type=1&report_type=2
    }
     */
}
