package auth;
import static auth.AttachmentLastScreenshot.*;

import static com.codeborne.selenide.Selenide.screenshot;

/**
 * Created by vlas2 on 06.02.2017.
 */
public class ScreenShotOnEveryTest {
    public static void screenShotOnEveryTest (){
        screenshot(String.valueOf(System.currentTimeMillis()));
        AttachmentLastScreenshot attachmentLastScreenshot = new AttachmentLastScreenshot();
        attachmentLastScreenshot.attachment();
    }
}
