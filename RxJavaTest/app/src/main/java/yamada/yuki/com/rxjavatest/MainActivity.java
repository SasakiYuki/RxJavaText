package yamada.yuki.com.rxjavatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testObservable.subscribe(testObserver);
        createNewObservable();
        Intent intent = new Intent(MainActivity.this,SampleActivity.class);
        startActivity(intent);
    }

    private void createNewObservable (){
        Observable.from(new String[]{"Hello", "world!!"}).
                filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return (s.length() == 5);
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s.toUpperCase();//すべて大文字に変換。
                    }
                }).
                subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("test",s);
                    }
                });
    }

    private Observable<String> testObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onNext("Hello");
                    subscriber.onNext("world!!");
                    subscriber.onCompleted();
                }
            }
    );

    private Observer<String> testObserver = new Observer<String>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(String s) {
            Log.d("test",s);
        }
    };

}
