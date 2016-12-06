package hhh.app.data.repository;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by hhh on 2016/11/23.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Local {
}
