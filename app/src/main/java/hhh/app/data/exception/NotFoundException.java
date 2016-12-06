package hhh.app.data.exception;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class NotFoundException extends Exception {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String detailMessage) {
        super(detailMessage);
    }

    public NotFoundException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NotFoundException(Throwable throwable) {
        super(throwable);
    }
}
