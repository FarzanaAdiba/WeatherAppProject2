package com.example.user.bitmproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.bitmproject2.api_interfaces.WeatherApiInterface;
import com.example.user.bitmproject2.models.Weather;
import com.example.user.bitmproject2.utils.RetrofitClient;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    WeatherApiInterface weatherApiInterface;
    EditText cityET;
    TextView cityTxt,tempText,maxtempTxt,mintempTxt,dateTxt,humTxt,pressTxt,conditionTxt,sunsetTxt,sunriseTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.action_bar));

        cityET=findViewById(R.id.cityNameTxt);

        //for current
        cityTxt=findViewById(R.id.txtCity);
        tempText=findViewById(R.id.txtTemperature);
        maxtempTxt=findViewById(R.id.txtMaxTempature);
        mintempTxt=findViewById(R.id.txtMinTempature);
        dateTxt=findViewById(R.id.txtDate);
        humTxt=findViewById(R.id.txtHumidity);
        pressTxt=findViewById(R.id.txtPressure);
        conditionTxt=findViewById(R.id.txtCloudStatus);
        sunriseTxt=findViewById(R.id.txtSunriseTime);
        sunsetTxt=findViewById(R.id.txtSunsetTime);


    }

    private void getCurrentWeather() {
        weatherApiInterface= RetrofitClient.getRetrofitClient().create(WeatherApiInterface.class);
      /*  Call<Weather> weatherCall=weatherApiInterface.getWeatherByCity("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"
                +cityET.getText().toString()+"%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        */
      retrofit2.Call<Weather> weatherCall=weatherApiInterface.getWeatherByCity("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"
              +cityET.getText().toString()+"%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
      weatherCall.enqueue(new Callback<Weather>() {
          @Override
          public void onResponse(retrofit2.Call<Weather> call, Response<Weather> response) {
              Weather weather=response.body();
              Double F=Double.parseDouble(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp());
              Double C=(((F-32)*5)/9);
              String celcius=String.format("%.2f",C);
              tempText.setText(celcius+"'C");
              dateTxt.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getDate());
              cityTxt.setText(response.body().getQuery().getResults().getChannel().getLocation().getCity());
              sunriseTxt.setText(response.body().getQuery().getResults().getChannel().getAstronomy().getSunrise());
              sunsetTxt.setText(response.body().getQuery().getResults().getChannel().getAstronomy().getSunset());
              //conditionTxt.setText(response.body().getQuery().getResults().getChannel().getLocation().getCountry());
              conditionTxt.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getText());
              humTxt.setText(response.body().getQuery().getResults().getChannel().getAtmosphere().getHumidity()+" %");
              pressTxt.setText(response.body().getQuery().getResults().getChannel().getAtmosphere().getPressure()+" hPa");

              maxtempTxt.setText(response.body().getQuery().getResults().getChannel().getItem().getLat());
              mintempTxt.setText(response.body().getQuery().getResults().getChannel().getItem().getLong());
          }

          @Override
          public void onFailure(retrofit2.Call<Weather> call, Throwable t) {

          }
      });

    }

    private void getWeatherinFarenhoite(){
        weatherApiInterface= RetrofitClient.getRetrofitClient().create(WeatherApiInterface.class);
      /*  Call<Weather> weatherCall=weatherApiInterface.getWeatherByCity("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"
                +cityET.getText().toString()+"%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        */
        retrofit2.Call<Weather> weatherCall=weatherApiInterface.getWeatherByCity("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"
                +cityET.getText().toString()+"%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(retrofit2.Call<Weather> call, Response<Weather> response) {
                Weather weather=response.body();
                //Double F=Double.parseDouble(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp());
               // Double C=(((F-32)*5)/9);
                tempText.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp()+"'F");
                dateTxt.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getDate());
                cityTxt.setText(response.body().getQuery().getResults().getChannel().getLocation().getCity());
                sunriseTxt.setText(response.body().getQuery().getResults().getChannel().getAstronomy().getSunrise());
                sunsetTxt.setText(response.body().getQuery().getResults().getChannel().getAstronomy().getSunset());
                //conditionTxt.setText(response.body().getQuery().getResults().getChannel().getLocation().getCountry());
                conditionTxt.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getText());
                humTxt.setText(response.body().getQuery().getResults().getChannel().getAtmosphere().getHumidity()+" %");
                pressTxt.setText(response.body().getQuery().getResults().getChannel().getAtmosphere().getPressure()+" hPa");

                maxtempTxt.setText(response.body().getQuery().getResults().getChannel().getItem().getLat());
                mintempTxt.setText(response.body().getQuery().getResults().getChannel().getItem().getLong());
            }

            @Override
            public void onFailure(retrofit2.Call<Weather> call, Throwable t) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.forscate:
                Intent intent=new Intent(MainActivity.this,ForecastActivity.class);
                startActivity(intent);

            case R.id.convertCelcius:

                getWeatherinFarenhoite();


        }
        return super.onOptionsItemSelected(item);
    }
    public void getWeather(View view) {
        getCurrentWeather();
    }
}
