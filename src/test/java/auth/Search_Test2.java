package auth;

import com.automation.remarks.junit.VideoRule;
import com.automation.remarks.video.annotations.Video;
import com.automation.remarks.video.enums.RecorderType;
import com.automation.remarks.video.enums.RecordingMode;
import com.automation.remarks.video.enums.VideoSaveMode;
import com.automation.remarks.video.recorder.VideoRecorder;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static auth.Index.search;
import static auth.Index.search_box;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.awaitility.Awaitility.await;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

//import com.automation.remarks.video.junit.VideoRule;
//import ru.yandex.qatools.allure.annotations.Attachment;


/**
 * Created by vlas2 on 01.02.2017.
 */


public class Search_Test2 {

    @Rule
    public TestWatcher videoWatcher = new TestWatcher() {
        File path = null;
        String newVideoName = null;
        @Override
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            path = VideoRecorder.getLastRecording();
            newVideoName = path.getPath().split("\\.")[0] + ".mp4";
            System.out.println(path.getAbsolutePath());
            System.out.println(path.getPath());
            System.out.println(path.getName().split("\\.")[0] + ".mp4");
            Encoder.encode(path.getAbsolutePath(), newVideoName);

            attachment();
        }

        @Attachment(value = "video", type = "video/mp4")
        private byte[] attachment() {
           // File video = null;
            try {
               // video = VideoRecorder.getLastRecording();
                await().atMost(5, TimeUnit.SECONDS)
                        .pollDelay(1, TimeUnit.SECONDS)
                        .ignoreExceptions();
                        //.until( video != null);
                return Files.readAllBytes(Paths.get(newVideoName));
            }
            catch (IOException e) {
                System.out.println("Allure listener exception" + e);

                return new byte[0];
            }

        }
    };

    @Rule
    public TestWatcher screenshotWatcher = new TestWatcher() {
        @Override
            protected void failed(Throwable e, Description description) {
                super.failed(e, description);
                attachment();
            }

        @Attachment(value = "scr", type = "img/png")
            private byte[] attachment() {
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
            };


    @Rule
    public VideoRule videoRule = new VideoRule();


///////////////////////testcontainers
    public static BrowserWebDriverContainer firefox =
            new BrowserWebDriverContainer()
                    .withDesiredCapabilities(DesiredCapabilities.firefox())
                    .withRecordingMode(RECORD_ALL, new File("./target/"));

//    @BeforeClass
//    public static void setUp(){
//        firefox.start();
//        RemoteWebDriver driver = firefox.getWebDriver();
//        WebDriverRunner.setWebDriver(driver);
//    }
/////////////////////////////////////////////////////////////////////////////


    @Before

    public void before() {
        /////Настройки записи видео:
        firefox.start();
        RemoteWebDriver driver = firefox.getWebDriver();
        WebDriverRunner.setWebDriver(driver);


        //System.setProperty("jenkins_url", "/video");
        URL hubUrl = null;
        try {
            hubUrl = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------");
        System.out.println(firefox.getContainerIpAddress());
        System.out.println("-----------------------------");







        VideoRecorder.conf().videoEnabled(true)                       // Disabled video globally
                .withVideoSaveMode(VideoSaveMode.FAILED_ONLY)     // Save videos for passed and failed tests
                .withRecorderType(RecorderType.FFMPEG)   // Monte is Default recorder
                .withRecordMode(RecordingMode.ANNOTATED);



        ///////Настройка заспуска в браузере Chrome
        //           System.setProperty("webdriver.chrome.driver", "C:/Users/vlas2/AppData/Local/Google/Chrome/application/chromedriver.exe");
        //           Configuration.browser = "chrome";
        //clearBrowserCache();
    }



@Stories("Поиск Google")
    @Test
    @Video

    @Step("Открыть гугл")
        public void open_page() {

            open("http://Google.com");
            sleep(30000);

            search_box.shouldBe(value("10"));

        }

    @Step("{0} Вписать в поле поиска текст \"Hello\" ")
        public void search_box_test() {


            search_box.sendKeys("Hello");
        }

    @Step("Перейи в меню картинки")
        public void search_box() {
            search.click();
            //$(byText("Результатов: примерно 1 320 000 000 (1,08 сек.)")).shouldBe(visible);
            $(byXpath("//div[@id='resultStats']")).shouldBe(visible);
        }

    @Step("Перейти в меню картинки")
        public void pictures(){
            $(By.linkText("Картинки")).click();
            sleep(3000);
        }


    @After
    public void after() {
        close();
    }
}

