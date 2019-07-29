package com.heziz.qixia3.network;

import com.google.gson.stream.JsonReader;
import com.heziz.qixia3.bean.yc.YcProjectDBean;
import com.heziz.qixia3.bean.yc.YcProjectDeviceBean;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 *
 *Created by hasee on 2017/home_bg_icon3/20.
 *
 */
public abstract class JsonCallBack2<T> extends AbsCallback<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        JsonReader jsonReader = new JsonReader(response.body().charStream());
        SRequstBean<RequestBean<YcProjectDBean<YcProjectDeviceBean>>> data = Convert.fromJson(jsonReader,type);
        response.close();
        if (data.isSuccess()){
            return (T) data;
        }else {
//            if(data.errorCode.equals("2")){
//                ToastUtil.showToast(data.errorMsg);
//            }
            throw new IllegalStateException(data.getResultDesc());
        }
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);

    }
}
