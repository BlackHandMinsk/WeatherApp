package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {
private static final String API = "введите нужный АПИ погоды";
    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text information;

    @FXML
    private Text temp;

    @FXML
    private Text feel;

    @FXML
    private Text max;

    @FXML
    private Text min;

    @FXML
    private Text dav;
    @FXML
    void initialize(){
        getData.setOnAction(event ->{
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")) {
                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&units=metric&appid="+API );
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp.setText("Температура:" + obj.getJSONObject("main" ).getDouble("temp" ));
                    feel.setText("Ощущается:" + obj.getJSONObject("main" ).getDouble("feels_like" ));
                    max.setText("Температура максимальна:" + obj.getJSONObject("main" ).getDouble("temp_max" ));
                    min.setText("Температура минимальная:" + obj.getJSONObject("main" ).getDouble("temp_min" ));
                    dav.setText("Давление:" + obj.getJSONObject("main" ).getDouble("pressure" ));
                }
                System.out.println(output);
            }
            });
    }
    private static String getUrlContent (String urlAdress){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = bufferedReader.readLine())!=null) {
                content.append(line+"\n");
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("ТАКОЙ ГОРОД НЕ НАЙДЕН");
        }
        return content.toString();
    }
}