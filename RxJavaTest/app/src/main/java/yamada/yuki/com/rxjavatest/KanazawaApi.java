package yamada.yuki.com.rxjavatest;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Yuki on 2016/02/04.
 */
public interface KanazawaApi {
    /*@以降は用途に合わせて　Get Post Put Delete　を用いる
        @Pathは{}内の物を照会
        @Queryを用いることでよくあるevent.json?1d=19などを簡単に記述できる
        例 getEvent(@Query("id"));
     */

    @GET("/{name}.json")// 「/」から開始しないとはじかれる
    public Observable<KanazawaEntity> getEvent(@Path("name")String url);
}
