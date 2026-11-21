package stellarburgers.api;

import java.util.UUID;

public class UserGenerator {

    public static User getRandomUser() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        return new User(
                "user_" + uniqueId + "@yandex.ru",
                "pass_" + uniqueId,
                "User_" + uniqueId
        );
    }
}