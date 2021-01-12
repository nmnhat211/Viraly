package com.example.viralyapplication.utility;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtil {
        public static messageModel parseError(Response<?> response) {
            Converter<ResponseBody, messageModel> converter =
                    NetworkProfile.getRetrofitInstance()
                            .responseBodyConverter(messageModel.class, new Annotation[0]);

            messageModel error;

            try {
                assert response.errorBody() != null;
                error = converter.convert(response.errorBody());
            } catch (IOException e) {
                return new messageModel();
            }

            return error;
        }
}
