package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.DataPointRepository;
import model.room.repositories.SaunaRepository;

public class SaunaViewModel extends AndroidViewModel {
    private SaunaRepository repositorySauna;
    private DataPointRepository repositoryData;

    public SaunaViewModel (Application application) {
        super(application);
       repositorySauna = new SaunaRepository(application);
       repositoryData = new DataPointRepository(application);
    }

    public LiveData<List<Sauna>> getAllSaunas() { return repositorySauna.getAllSaunas(); }
    public LiveData<List<DataPoint>> getAllDatapointsForASauna(Sauna sauna){
        repositoryData.populateDatapointRepo(sauna);
        return repositoryData.getAllDataPoints();}
    public void spinServo(Sauna sauna){
        //unimplemented
    }
}