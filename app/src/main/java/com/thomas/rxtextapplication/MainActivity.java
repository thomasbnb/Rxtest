package com.thomas.rxtextapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thomas.rxtextapplication.bean.MovieEntity;
import com.thomas.rxtextapplication.retrofit.HttpMethods;
import com.thomas.rxtextapplication.retrofit.MovieService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Test1();
    }

    @OnClick(R.id.button)

    /**
     *
     * 第一种只使用retrofit请求网络
     *
     * **/
    public void TestRetrofit1() {

        /*String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);

        Call<MovieEntity> call = movieService.getTopMovie(0, 10);


        call.enqueue(new retrofit2.Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                Log.i("thomas", "onResponse:getTotal:" + response.body().getTotal());
                Log.i("thomas", "onResponse:getCount:" + response.body().getCount());
                Log.i("thomas", "onResponse:size():" + response.body().getSubjects().size());
                textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });*/

    }

    /**
     *
     * 第二种，使用retrofit和rxjave
     *
     * **/
    public void TestRetrofit2() {
        String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        movieService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        textView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        Log.i("thomas", "onResponse:getTotal:" + movieEntity.getTotal());
                        Log.i("thomas", "onResponse:getCount:" + movieEntity.getCount());
                        Log.i("thomas", "onResponse:size():" + movieEntity.getSubjects().size());
                        textView.setText(movieEntity.toString());
                    }
                });
    }

    /**
     *
     * 第三种，使用retrofit和rxjave并且进行封装
     *
     * **/
    public void TestRetrofit3() {

        Subscriber<MovieEntity> subscriber = new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                textView.setText(e.getMessage());
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                textView.setText(movieEntity.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber, 0, 10);

    }



    private void Test1() {

        ApiImp imp = new ApiImp();

        ApiWrapper wrap = new ApiWrapper(imp);
        CatsHelper cat = new CatsHelper(wrap);

        AsyncJob<Uri> save = cat.saveTheCutestCat("ttt");
        save.start(new Callback<Uri>() {
            @Override
            public void onResult(Uri result) {
                Log.i("thomas", "onResult:" + result.toString());
            }

            @Override
            public void onError(Exception e) {
                Log.i("thomas", "onError:");
            }
        });


    }
}
