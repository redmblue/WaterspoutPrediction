package mainpack;
import java.io.*;
import java.net.URL;
import java.util.*;
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
public class Main {
    static ArrayList<ArrayList<String>> arr = new ArrayList<>();
    //arr.add(Arrays.asList("j","k","l"));
    {
        try {
            arr = ExcelGraph.returnArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        //step 1: find occurence in csv. Step 2: Get weather at the nearest time.
        // Step 3: Analyze using current model - add percent of occurance happening in there
        //Step 4: make model and add percent of occurance happening - this fi

        try {
            WritePositiveFile();
            //WriteNegativeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getTime(String time){
        if(time.equals("0")){
            time = "0100";
        }
        else if(Integer.parseInt(time)<100){
            time= "0100";
        }
        char[] arr = time.toCharArray();
        System.out.println("Time is for the error: "+ time);
        if (arr.length == 4){
            return (arr[0]+ "" + arr[1]+":"+arr[2]+""+arr[3]);
        }
        else
            return ("0" + arr[0]+":"+arr[1]+ "" +arr[2]);
    }
    // writes the positive file
    public static ArrayList<String> getArray(){
        ArrayList<String> FArr = new ArrayList<>();
        try {
            ArrayList<ArrayList<String>> arr2 = ExcelGraph.returnArray();
            arr2.remove(0);
            for (ArrayList<String> i: arr2){
                System.out.println(i.get(15));
                if (i.get(15).contains(" "))
                    continue;
                String time = getTime(i.get(2));
                FArr.add(GetNearestAirport.airpId(i.get(15), i.get(8)) + "," + i.get(15) + " " + i.get(8) + "," + GetHistoricalWeather.getHistoricalWeather(GetNearestAirport.airpId(i.get(15), i.get(8)), (GetWeather.getDMY(i.get(0), i.get(1)) + " " + time))+ "," + time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FArr;
    }
    public static String getState(String city){
        ArrayList<String> temps = new ArrayList<>(Arrays.asList("a","b","c"));
        ArrayList<ArrayList<String>> tempArr = new ArrayList<>(Arrays.asList(temps));
        tempArr = ExcelGraph.StateList();
        for (ArrayList<String> i: tempArr){
            if(city.equals(i.get(0)))
                return i.get(1);
        }
        return "";
    }
    public static ArrayList<String> getNegativeArray(){

        String city = ExcelGraph.RandomCity();
        String date = ExcelGraph.ExcelSearchTimeIndex();
        String time = ExcelGraph.RandomTime();
        String state = ExcelGraph.getStateWithCity(city);
        ArrayList<String> FArr = new ArrayList<>();
        FArr.add(city + "," + date + "," + time + "," + GetHistoricalWeather.getHistoricalWeather(GetNearestAirport.airpId(city, state), (date + " " + time)));
        return FArr;
    }
    public static void WritePositiveFile() throws IOException{
        System.out.println("Called");
        try{

            //f2.write(setting);
            //f2.close();

            FileOutputStream writeData = new FileOutputStream("Positive.csv");
            //https://www.youtube.com/watch?v=NE0wTMh4E_E
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject("\n");
            ArrayList<ArrayList<String>> arr2 = ExcelGraph.returnArray();
            arr2.remove(0);

            for (ArrayList<String> i: arr2){
                System.out.println(i.get(15));
                if (i.get(15).contains(" "))
                    continue;
                String time = getTime(i.get(2));
                String i2 =GetNearestAirport.airpId(i.get(15), i.get(8)) + "," + i.get(15) + " " + i.get(8) + "," + GetHistoricalWeather.getHistoricalWeather(GetNearestAirport.airpId(i.get(15), i.get(8)), (GetWeather.getDMY(i.get(0), i.get(1)) + " " + time))+ "," + time;
                if (i2.contains("Error"))
                    continue;

                writeStream.writeObject(i2 + "\n");
            }
            //writeStream.flush();
            //writeStream.close();
            writeStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
