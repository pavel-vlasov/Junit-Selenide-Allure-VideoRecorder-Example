package auth;

import ru.yandex.qatools.allure.annotations.Step;

import static auth.ScreenShotOnEveryTest.screenShotOnEveryTest;

/**
 * Created by vlas2 on 06.02.2017.
 */
public class SubStepAllure {
    @Step("{0}")
    public static void log(String value) {
        //empty method
        screenShotOnEveryTest();
    }
}
