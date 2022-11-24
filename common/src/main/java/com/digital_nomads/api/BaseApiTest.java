package com.digital_nomads.api;

import com.digital_nomads.api.apiHelper.BaseMethods;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    public static Response response;

    @BeforeClass
    public static void init() {

    }
}
