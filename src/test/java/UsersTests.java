import Steps.ReqRes.UserSteps;
import org.testng.annotations.Test;

public class UsersTests {

    @Test
    public void getUsersFromSecondPage(){
        new UserSteps()
                .getUsersFromSecondPage()
                .usersDeserialization()
                .checkUsers();
    }
}
