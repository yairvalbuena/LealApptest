package yairvalbuena.android.lealtestapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yairvalbuena.android.lealtestapp.Adapters.TransactionsAdapter;
import yairvalbuena.android.lealtestapp.Interface.LealtestApi;
import yairvalbuena.android.lealtestapp.Model.Transaction;
import yairvalbuena.android.lealtestapp.Model.TransactionInfo;
import yairvalbuena.android.lealtestapp.Model.User;

public class MainActivity extends AppCompatActivity implements  TransactionsAdapter.OnTransactionListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;


    private RecyclerView recyclerView;
    private TransactionsAdapter adapter;
    private List<Transaction> transactions;
    private List<Transaction> transactionClick;

    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;

    // Info usuario y transaccion
    TextView usuario ;
    TextView comercio ;
    TextView creacion ;
    TextView nacimiento ;
    TextView rama ;
    TextView puntos ;
    TextView valor;
    ImageView userPhoto ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navigationview);

        usuario = findViewById(R.id.userName);
        comercio = findViewById(R.id.commerce);
        userPhoto = findViewById(R.id.imageUser);
        creacion = findViewById(R.id.userDate);
        nacimiento = findViewById(R.id.userBirthday);
        rama = findViewById(R.id.branchname);
        puntos = findViewById(R.id.puntos);
        valor = findViewById(R.id.valor);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
        drawerLayout.addDrawerListener(toggle);
        toggle= new ActionBarDrawerToggle(this,drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();


        transactions = new ArrayList<>();
        adapter = new TransactionsAdapter(transactions, MainActivity.this, this);
        recyclerView =  findViewById(R.id.recyclerViewA);
        LinearLayoutManager lim = new LinearLayoutManager((MainActivity.this));
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        getLTransactions();

        FloatingActionButton fab = findViewById(R.id.FAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactions.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Lista de Transacciones Borrada", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation, menu);

        return true;
    }


    private void getLTransactions() {
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mobiletest.leal.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        LealtestApi lealtestApi = retrofit.create(LealtestApi.class);

        Call<List<Transaction>> call = lealtestApi.getTransactions();

        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                transactionClick = response.body();
                transactions = response.body();
                adapter.setData(transactions);
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                Log.d("TAG1", "Error: "+ t.getMessage());
            }
        });
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
           transactions.remove(viewHolder.getAdapterPosition());
           adapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int res_id = item.getItemId();
        if(res_id == R.id.home)
        {
            getLTransactions();
            Toast.makeText(getApplicationContext(), "Lista Renovada", Toast.LENGTH_LONG).show();
        }
        if(toggle.onOptionsItemSelected(item))
            return true;
        else {
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void OnTransactionClick(int position) {

        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mobiletest.leal.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        LealtestApi lealtestApi = retrofit.create(LealtestApi.class);

        Call<User> call = lealtestApi.getUserInfo(transactions.get(position).userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                usuario.setText(response.body().name);
                Picasso.get().load(response.body().photo).into(userPhoto);
                creacion.setText(response.body().createdDate.split("T")[0]);
                nacimiento.setText(response.body().birthday.split("T")[0]);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG1", "Error: "+ t.getMessage());
            }
        });

        Call<TransactionInfo> call2 = lealtestApi.getTransactionInfo(transactions.get(position).id);

        call2.enqueue(new Callback<TransactionInfo>() {
            @Override
            public void onResponse(Call<TransactionInfo> call, Response<TransactionInfo> response) {
                String points = String.valueOf(response.body().points);
                String price = String.valueOf(response.body().value);
                valor.setText(price);
                puntos.setText(points);
                rama.setText(transactions.get(position).branch.name);
                comercio.setText(transactions.get(position).commerce.name);
            }

            @Override
            public void onFailure(Call<TransactionInfo> call, Throwable t) {
                Log.d("TAG1", "Error: "+ t.getMessage());
            }
        });

    }
}
