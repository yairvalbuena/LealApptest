package yairvalbuena.android.lealtestapp.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import yairvalbuena.android.lealtestapp.Model.Transaction;
import yairvalbuena.android.lealtestapp.Model.TransactionInfo;
import yairvalbuena.android.lealtestapp.Model.User;

public interface LealtestApi {
    @GET("transactions")
    Call<List<Transaction>> getTransactions();

    @GET("transactions/{id}/info")
    Call<TransactionInfo> getTransactionInfo(@Path("id") int id);

    @GET("users/{id}")
    Call<User> getUserInfo(@Path("id") int id);

}
