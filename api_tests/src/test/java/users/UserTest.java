package users;

import com.digital_nomads.api.BaseApiTest;
import com.digital_nomads.api.apiHelper.BaseMethods;
import com.digital_nomads.api.endpoints.UserEndpoints;
import com.digital_nomads.api.models.User;
import com.digital_nomads.api.utils.ObjectConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.Test;


@Epic("All tests for User")
@Feature("GET request for user")
public class UserTest extends BaseApiTest {


    @Test
    @Description("Get all users from DB ")
    @Story("All users")
    @Link("public static RequestSpecification rs = RestAssured.given();")
    public void getAllUsersTest() throws JsonProcessingException {
        response = BaseMethods.initHeaders()
                .basePath(UserEndpoints.USERS)
                .request(Method.GET);
        User[] users = ObjectConverter.convertJsonObjectToJavaObject(response.body().asString(),User[].class);
        Assert.assertEquals(users.length,1);
    }
}
