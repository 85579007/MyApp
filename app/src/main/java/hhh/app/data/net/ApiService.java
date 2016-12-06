package hhh.app.data.net;

import java.util.List;

import hhh.app.data.bean.MyResult;
import hhh.app.data.bean.Product;
import retrofit2.http.GET;

/**
 * Created by hhh on 2016/10/24.
 */
public interface ApiService {
    @GET("getproducts")
    rx.Observable<MyResult<List<Product>>> getProducts();

//    Call<ResponseBody> getProducts();
//    Call<MyResult<Product>> getProducts();

//    @GET("getproduct/{pid}")
//    Call<ResponseBody> getProduct(@Path("pid") String pid);
}
