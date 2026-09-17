package Steps.ReqRes;

import Calls.ReqRess.UserCalls;
import Models.ReqRes.UserResponse.UserModel;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

public class UserSteps {
    UserCalls userCalls = new UserCalls();
    Response userResponse;
    List<UserModel> users;

    public UserSteps getUsersFromSecondPage() {
        userResponse = userCalls.getUsers(2);
        return this;
    }

    public UserSteps usersDeserialization() {
        users = userResponse
                .jsonPath()
                .getList("data", UserModel.class);
        return this;
    }

    public UserSteps checkUsers() {
        for (UserModel user : users) {
            Assert.assertTrue(user.email.contains("@"));
            Assert.assertFalse(user.first_name.isEmpty());
            Assert.assertFalse(user.last_name.isEmpty());
        }
        return this;
    }
}
