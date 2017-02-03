package auth;

import com.automation.remarks.video.recorder.VideoRecorder;
import org.apache.commons.logging.Log;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

/**
 * Created by vlas2 on 02.02.2017.
 */

    public class AllureWatcher extends TestWatcher {
        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            attachment();
        }
        @Attachment(value = "video", type = "video/mp4")
        private byte[] attachment() {
            try {
                File video = VideoRecorder.getLastRecording();
//                await().atMost(5, TimeUnit.SECONDS)
//                        .pollDelay(1, TimeUnit.SECONDS)
//                        .ignoreExceptions()
//                        .until(() -> video != null);

                return Files.readAllBytes(Paths.get(video.getAbsolutePath()));
            } catch (IOException e) {
                System.out.println ("Allure listener exception" + e);

                return new byte[0];
            }
        }
    }

