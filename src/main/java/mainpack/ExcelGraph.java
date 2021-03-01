package mainpack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;
public class ExcelGraph {
    static ArrayList<String> cities = new ArrayList<>();

    /*
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<String>> arr= returnArray("C:\\Users\\tonon\\OneDrive\\Documents\\BerenGreatLakes2018.csv");
        for (ArrayList<String> i: arr){
            for (String i2 : i){
                System.out.print(i2 + " ");
            }
            System.out.println();
        }
    }
     */
    public static String RandomTime(){
        Random rand = new Random();
        int hr = rand.nextInt(24)+1;
        int minute = rand.nextInt(61);
        String Hr, Min;
        if (hr < 10)
            Hr = "0"+ hr;
        else
            Hr ="" +hr;
        if (minute < 10)
            Min = "0"+ minute;
        else
            Min ="" +minute;
        return Hr+":"+Min;
    }
    public static String getStateWithCity(String city){
        ArrayList<ArrayList<String>> arr = StateList();
        for(ArrayList<String> i: arr){
            if(city.equals(i.get(0))==true)
                return i.get(1);
        }
        return "";
    }
    public static ArrayList<ArrayList<String>> StateList() {
        ArrayList<ArrayList<String>> tempArr = new ArrayList<>();
        try {
            ArrayList<ArrayList<String>> arr = returnArray();
            for (ArrayList<String> i: arr){
                ArrayList<String> temp = new ArrayList<>();
                if (i.get(15).contains(" ") == false){
                temp.add(i.get(15));
                temp.add(i.get(8));
                tempArr.add(temp);}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> Errlist = new ArrayList<>();
        Errlist.add("Error");
        ArrayList<ArrayList<String>> finlist = new ArrayList<>();
        finlist.add(Errlist);
        return tempArr;
    }

    public static void createCityArray(){
        try {
            ArrayList<ArrayList<String>> arr = returnArray();
            for (ArrayList<String> i: arr){
                if(i.contains(" ")==false)
                    cities.add(i.get(15));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createTimeArray(){
        try {
            ArrayList<ArrayList<String>> arr = returnArray();
            for (ArrayList<String> i: arr){
                cities.add(Main.getTime(i.get(2)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String RandomCity(){
        createCityArray();
        Random rand = new Random();
        return cities.get(rand.nextInt(cities.size()));
    }
    public static ArrayList<String> generateDates(){
        String date = Date();
        ArrayList<String> arr = new ArrayList<>();
        int num = Integer.parseInt(date.split("-")[2]);
        for (int i = -7; i <=7; i++){
            if (num+i <= 0)
                continue;
            if (num+i > 28)
                continue;
            arr.add(date.split("-")[0]+ "-" +date.split("-")[1]+ "-" + (num+i));
        }
        return arr;
    }
    public static String ExcelSearchTimeIndex (){
        String FDATE = null;
        try {
            ArrayList<ArrayList<String>> arr = returnArray();
            arr.remove(0);
            ArrayList<String> dates = generateDates();
            for (ArrayList<String> i: arr){
                String date = GetWeather.getDMY(i.get(0), i.get(1));
                for (String i2: dates){
                    if (i2.equals(date))
                        return ExcelSearchTimeIndex();
                }
                FDATE=date;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FDATE;
    }
    public static String Date(){
        Random rand = new Random();
        int day = rand.nextInt(28)+1, month = rand.nextInt(12)+1;
        String Day = "", Month = "";
        if(day <10)
            Day += "0" + day;
        else
            Day +=day;
        if(day <10)
            Month += "0" + month;
        else
            Month +=month;
        return (rand.nextInt(3)+2015) +"-"+ Month + "-" + Day;
    }
    public static ArrayList<ArrayList<String>> returnArray() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\WaterspoutPrediction\\src\\main\\java\\mainpack\\WaterspoutDatabase.csv"));
        ArrayList<String> arr2 = new ArrayList<>();
        while (br.ready()) {
            String st = br.readLine();
            String[] arr = st.split("\"");
            for (String i: arr){
                arr2.add(i);
            }

        }
        ArrayList<String[]> TempFArr = new ArrayList<>();
        ArrayList<ArrayList<String>> FArr = new ArrayList<>();
        for (int i = 0; i < arr2.size(); i++){
            TempFArr.add(arr2.get(i).split(","));
        }for (String[] i: TempFArr) {
            ArrayList<String> TempFarr2 = new ArrayList<>();
            for (String k: i){
                TempFarr2.add(k);
            }
            FArr.add(TempFarr2);
        }
        return FArr;
    }
}
