package com.example.sep4_android.API;

import com.example.sep4_android.Model.Account.Account;
import com.example.sep4_android.Model.Account.Reservation;
import com.example.sep4_android.Model.Account.RightsEnum;
import com.example.sep4_android.Model.Sauna.Sauna;
import com.example.sep4_android.Model.Sauna.Servo;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceAPI {

    @GET()
    Call<List<Account>> getAllAccounts();

    @GET()
    Call<List<Sauna>> getAllSaunas();

    @POST()
    Call<Account> logIn(
            @Query("username") String username,
            @Query("password") String password
    );

    @POST()
    Call<Servo> openTheDoor(
            @Query("saunaID") int saunaID
    );

    @POST()
    Call<Reservation> createReservation(
            @Query("saunaID") int sauinaID,
            @Query("roomNumber") String roomNum,
            @Query("from")Date timeFrom,
            @Query("to") Date timeTo
    );

    @POST()
    Call<Account> createNewAccount(
            @Query("username") String username,
            @Query("password") String password,
            @Query("rights") RightsEnum rights,
            @Query("saunas") List<Sauna> saunas
    );

    @POST()
    Call<Account> removeUser(
            @Query("userID") int userID
    );

    @POST()
    Call<Account> setRights(
          @Query("rights") RightsEnum rights,
          @Query("userID") int userID
    );

    @POST()
    Call<List<Sauna>> setThresholds(
            @Query("temperature") float temperature,
            @Query("humidity") float humidiry,
            @Query("CO2") float CO2
    );
}
