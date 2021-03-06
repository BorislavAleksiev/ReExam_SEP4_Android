package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.Reservation;
import model.room.entity.IntegerEntity;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.AccountRepository;
import model.room.repositories.ReservationRepository;
import model.room.repositories.SaunaRepository;

public class EmployeeViewModel extends AndroidViewModel {
    private SaunaRepository repositorySauna;
    private ReservationRepository repositoryReservation;
    private AccountRepository repositoryAccount;

    public EmployeeViewModel (Application application) {
        super(application);
        repositorySauna = new SaunaRepository(application);
        repositoryReservation = new ReservationRepository(application);
        repositoryAccount = new AccountRepository(application);
    }

    public LiveData<List<Sauna>> getAllSaunas() {
        repositorySauna.emptyAndPopulateSaunasRepoAPI();
        return repositorySauna.getAllSaunas(); }

    public LiveData<List<Customer>> getCustomers(){
        return repositoryAccount.getCustomers(); }

    public void book(Reservation reservation){
        repositoryReservation.createReservationAPI(reservation);
    }

    public void openDoor(int id){
        repositorySauna.openDoorAPI(id);
    }

    public void checkforNotifications(){
        repositorySauna.checkNotificationsAPI();
    }

    public LiveData<List<IntegerEntity>> getAllNotifiedSaunas(){
        return repositorySauna.getAllIntegerEntities();
    }
}
