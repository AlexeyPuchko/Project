package com.alexeypuchko.project.network.api;

import com.alexeypuchko.project.AppConfig;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Alexey on 14.03.2015.
 */
public class ApiBuilder {

    private static Api api;

    private static RestAdapter.Builder createRestAdapterBuilder() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        return createRestAdapterBuilder(
//                builder.setEndpoint(BuildConfig.CLASSPASS_API_ENDPOINT)
        );
    }

    public static Api getApi() {
        if (api == null) {
            api = createRestAdapterBuilder().build().create(Api.class);
        }
        return api;
    }

    private static RestAdapter.Builder createRestAdapterBuilder(
            RestAdapter.Builder builder) {

        builder.setClient(new OkClient(getInnerClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        return builder;
    }

    private static OkHttpClient getInnerClient() {
        OkHttpClient okClient = new OkHttpClient();
        okClient.setConnectTimeout(AppConfig.CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        okClient.setReadTimeout(AppConfig.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return okClient;
    }
}
