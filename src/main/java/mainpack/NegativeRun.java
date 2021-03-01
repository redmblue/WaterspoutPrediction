package mainpack;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//
public class NegativeRun {
    public static void main(String args[]){
        try {
            WriteNegativeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void WriteNegativeFile() throws IOException {
        try{
            FileOutputStream writeData = new FileOutputStream("Negative.csv");
            //https://www.youtube.com/watch?v=NE0wTMh4E_E
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject("\n");
            for(int i = 0; i <= 2000; i++) {
                String city = ExcelGraph.RandomCity();
                String date = ExcelGraph.ExcelSearchTimeIndex();
                String time = ExcelGraph.RandomTime();
                String state = ExcelGraph.getStateWithCity(city);
                if (city.contains(" ")==true){
                    i -=1;
                    continue;
                }
                System.out.println(state);
                if(GetHistoricalWeather.getHistoricalWeather(GetNearestAirport.airpId(city, state), (date + " " + time)).equals("Error")&&!(state.equals(" ")||state.equals(null))){
                    i-=1;
                    continue;
                }
                String i2 =(GetNearestAirport.airpId(city, state) + "," + city +" " + state+ "," + date + "," + time + "," + GetHistoricalWeather.getHistoricalWeather(GetNearestAirport.airpId(city, state), (date + " " + time)));
                writeStream.writeObject(i2 + "\n");
            }
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
