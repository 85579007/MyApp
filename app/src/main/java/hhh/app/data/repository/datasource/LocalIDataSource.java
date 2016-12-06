package hhh.app.data.repository.datasource;

import java.util.List;

import hhh.app.data.bean.Product;
import rx.Observable;

/**
 * Created by hhh on 2016/10/28.
 */

public class LocalIDataSource implements IDataSource {

    public LocalIDataSource() {
    }

    @Override
    public <U> Observable<List<U>> getList() {
        return null;
    }

    @Override
    public <U> Observable<U> getDetail() {
        return null;
    }
}
