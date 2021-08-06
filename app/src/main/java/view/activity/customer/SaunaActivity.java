package view.activity.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sep4_android.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


import model.room.entity.Sauna.DataPoint;
import viewmodel.SaunaViewModel;


public class SaunaActivity extends AppCompatActivity {

    private TextView saunaName;
    private LineChart graph;
    private SaunaViewModel mSauna;
    private List<DataPoint> datapointList;
    private List<String> co2 = new ArrayList<>();
    private ArrayList<String> humidity = new ArrayList<>();
    private ArrayList<String> temperature = new ArrayList<>();
    private ArrayList<String> xAxis = new ArrayList<>();
    private ArrayList<String> yAxisCO2 = new ArrayList<>();
    private ArrayList<String>yAxisTemp = new ArrayList<>();
    private ArrayList<String>yAxisHum = new ArrayList<>();
    private TextView txtCO2,txtHum,txtTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauna);
        saunaName = findViewById(R.id.saunaName);
        graph = findViewById(R.id.lineChart);
        txtCO2 = findViewById(R.id.cO2Val);
        txtHum = findViewById(R.id.humVal);
        txtTemp = findViewById(R.id.tempVal);

        mSauna = new ViewModelProvider(this).get(SaunaViewModel.class);

        Bundle extras = getIntent().getExtras();
        String name;
        name = String.valueOf(extras.getInt("Sauna"));
        saunaName.setText("Sauna " + name);

       mSauna.getAllDatapointsForASauna(extras.getInt("Sauna")).observe(this, new Observer<List<DataPoint>>() {
            @Override
            public void onChanged(List<DataPoint> dataPoints) {
                datapointList = dataPoints;
                System.out.print("ID IS :::::"+extras.getInt("Sauna"));
                for (int i =0; i < datapointList.size(); i++){
                    xAxis.add(datapointList.get(i).getDateTime());
                    yAxisCO2.add(datapointList.get(i).getCo2());
                    yAxisTemp.add(datapointList.get(i).getTemperature());
                    yAxisHum.add(datapointList.get(i).getHumidity());

                    txtCO2.setText(yAxisCO2.get(0));
                    txtTemp.setText(yAxisTemp.get(0));
                    txtHum.setText(yAxisHum.get(0));
                    /*
                    yAxisCO2.add(new Entry(Float.parseFloat(xAxis.get(i)),Float.parseFloat(datapointList.get(i).getCo2())));
                    yAxisHum.add(new Entry(Float.parseFloat(xAxis.get(i)),Float.parseFloat(datapointList.get(i).getHumidity())));
                    yAxisTemp.add(new Entry(Float.parseFloat(xAxis.get(i)),Float.parseFloat(datapointList.get(i).getTemperature())));
                    */
                }

            }
        });
        /*

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        LineDataSet datasetCO2 = new LineDataSet(yAxisCO2,"CO2");
        datasetCO2.setDrawCircles(false);
        datasetCO2.setColor(Color.BLUE);

        LineDataSet datasetHum = new LineDataSet(yAxisHum,"Humidity");
        datasetHum.setDrawCircles(false);
        datasetHum.setColor(Color.YELLOW);

        LineDataSet datasetTemp = new LineDataSet(yAxisTemp,"Temperature");
        datasetTemp.setDrawCircles(false);
        datasetTemp.setColor(Color.RED);

        dataSets.add(datasetCO2);
        dataSets.add(datasetHum);
        dataSets.add(datasetTemp);

        graph.setData(new LineData(dataSets));

         */
    }
}