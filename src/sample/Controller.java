package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.io.IOException;

import com.sun.istack.internal.NotNull;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import javax.swing.*;

public class Controller {

    private Stage stage;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text pressure;

    @FXML
    private Button getData;


    @FXML
    private Text description;


    @FXML
    private Text error;


    private Button exitButton;
    @FXML
    private AnchorPane scenePane;

    @FXML
    private Text speed;



    public void exit(javafx.event.ActionEvent actionEvent) {
        stage = (Stage) scenePane.getScene().getWindow();
        System.out.println("nice");
        stage.close();
    }

    @FXML
    void initialize() {
        getData.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                getData.fire();
            }
        });


        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=f51990e7149906aa689774767bf390da&unit=metric");

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);

                    double temp_c = obj.getJSONObject("main").getDouble("temp");
                    double temp_c_1 = temp_c - 273.15f;
                    double temp_c_2 = Math.round(temp_c_1 * 100.0) / 100.0;

                    double temp_feels_c = obj.getJSONObject("main").getDouble("feels_like");
                    double temp_feels_1 = temp_feels_c - 273.15f;
                    double temp_feels_2 = Math.round(temp_feels_1 * 100.0) / 100.0;

                    double temp_max_c = obj.getJSONObject("main").getDouble("temp_max");
                    double temp_max_1 = temp_max_c - 273.15f;
                    double temp_max_2 = Math.round(temp_max_1 * 100.0) / 100.0;

                    double temp_min_c = obj.getJSONObject("main").getDouble("temp_min");
                    double temp_min_1 = temp_min_c - 273.15f;
                    double temp_min_2 = Math.round(temp_min_1 * 100.0) / 100.0;

                    temp_info.setText(temp_c_2 + " C");
                    temp_feels.setText("Feels: " + temp_feels_2 + " C");
                    temp_max.setText("Maximum: " + temp_max_2 + " C");
                    temp_min.setText("Minimum: " + temp_min_2 + " C");
                    pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure"));
                    description.setText("Weather: " + obj.getJSONArray("weather")
                            .getJSONObject(0)
                            .getString("description"));
                    speed.setText("Speed: " + obj.getJSONObject("wind").getDouble("speed") + " m/s");


                }
            }
        });

    }

    private String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {

            error.setText("error : такого города нет");
            System.out.println("такого города нет");
        }
        return content.toString();
    }



}
