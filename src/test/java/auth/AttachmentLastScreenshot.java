package auth;

import com.codeborne.selenide.Screenshots;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by vlas2 on 06.02.2017.
 */
public class AttachmentLastScreenshot {
    @Attachment(value = "scr", type = "img/png")
    public byte[] attachment() {
        try {
            File img = Screenshots.getLastScreenshot();
            return toByteArray(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
    private byte[] toByteArray(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
    }
}
