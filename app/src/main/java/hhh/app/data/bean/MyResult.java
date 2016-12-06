package hhh.app.data.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/23 0023.
 */
public class MyResult<T> {
    public static final int STATE_SUC=0;
    public static final int STATE_ERR=1;

    public int code;
    public String message;
    public T data;
}
