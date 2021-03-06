package com.example.vidhipatel.myapplication2;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Func1;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static rx.android.app.AppObservable.bindActivity;


public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    @Bind(R.id.edit_query)
    EditText text;
    private static int DURATION = 500;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Observable<String> odd = Observable.just("AA", "CC", "EE", "GG", "II");
        Observable<Integer> even = Observable.just(4, 5);
        Observable.merge(odd, even).subscribe(integer -> Log.d(TAG, integer + "")).unsubscribe();

        Observable<OnTextChangeEvent> searchObservable = WidgetObservable.text(text);
        mSubscriptions.add(bindActivity(this,
                searchObservable
                        .debounce(DURATION, TimeUnit.MILLISECONDS)
                        .filter(onTextChangeEvent -> !onTextChangeEvent.text().toString().isEmpty())
                        .flatMap(new Func1<OnTextChangeEvent, Observable<?>>() {
                            @Override
                            public Observable<?> call(OnTextChangeEvent onTextChangeEvent) {
                                return Observable.from(onTextChangeEvent.text().toString().split(" ", 3));
                            }
                        })
                        .map(o -> o.toString())
                        .map(s -> "Search:" + s)
                                //     .takeFirst(s1 -> s1.equals("Search:vi"))
                                //.mergeWith(odd)
                        .observeOn(AndroidSchedulers.mainThread()))
                .subscribe(s -> print(s)));

        //network access
        Observable<List<User>> userObservable = Observable.create(new Observable.OnSubscribe<List<User>>() {
            @Override
            public void call(Subscriber<? super List<User>> subscriber) {
                Log.d(TAG, "Call");
                subscriber.onNext(RestClient.getApi().getUsers());
            }
        });
        mSubscriptions.add(bindActivity(this,
                userObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread()))
                .subscribe(user -> printUsers(user)));


        mSubscriptions.add(bindActivity(this,
                Observable.timer(0, 200, TimeUnit.MILLISECONDS,Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread()))
                .subscribe(aLong -> Toast.makeText(getApplicationContext(), "Timer" + aLong, Toast.LENGTH_SHORT).show()));
    }

    private void printUsers(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            User mUser = users.get(i);
            Log.d(TAG, i + ": " + mUser.getName());
        }
    }

    private void print(String s) {
        Log.d(TAG, s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscriptions != null)
            //mSubscriptions.unsubscribe();
            mSubscriptions.clear();
        Log.d(TAG, "Destroyed");
    }

}
