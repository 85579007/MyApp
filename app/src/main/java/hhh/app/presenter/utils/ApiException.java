package hhh.app.presenter.utils;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public class ApiException extends Throwable {
    public ApiException() {
        super();
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }
}
