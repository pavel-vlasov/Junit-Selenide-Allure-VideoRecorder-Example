package auth;


import com.automation.remarks.junit.VideoRule;
import com.automation.remarks.video.enums.RecorderType;
import com.automation.remarks.video.enums.RecordingMode;
import com.automation.remarks.video.enums.VideoSaveMode;
import com.automation.remarks.video.recorder.VideoRecorder;
import com.codeborne.selenide.*;
import com.codeborne.selenide.commands.ShouldBe;
import com.codeborne.selenide.conditions.Text;
import com.codeborne.selenide.junit.SoftAsserts;
import com.codeborne.selenide.testng.BrowserPerTest;


import org.junit.*;
import org.openqa.selenium.By;
import com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.AssertionMode.SOFT;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import com.automation.remarks.video.annotations.Video;
//import com.automation.remarks.video.junit.VideoRule;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;


/**
 * Created by vlas2 on 01.02.2017.
 */
public class Index {

    public static final  SelenideElement search_box = $(byXpath("//input[@aria-label='Найти']")) ;
    public static final  SelenideElement search = $(byXpath("//button[@aria-label='Поиск в Google']")) ;



}
