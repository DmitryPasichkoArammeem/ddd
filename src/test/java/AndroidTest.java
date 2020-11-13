import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidTest {
   private AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");//версия андроида
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");//android or ios
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android SDK built for x86");//открыть настройки андроида>информация о девайсе
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5556");//id эммулятор - получить выпополнив комманду adb devices
        caps.setCapability("appPackage", "com.dencreak.dlcalculator");// через apk info
        caps.setCapability("appActivity", "com.dencreak.dlcalculator.DLCalculatorActivity");// через apk info


        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testExample() {
        System.out.println(driver.getPlatformName());

    }

    @AfterClass
    public void tearDown() {
        driver.closeApp();//CloseApp() функция используется для закрытия нативного или гибридного приложения, а quit() и close() - для веба
    }
}
