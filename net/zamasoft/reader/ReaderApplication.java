package net.zamasoft.reader;

import com.b.a.a;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import de.codecentric.centerdevice.a;
import de.codecentric.centerdevice.dialogs.about.a;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.SplashScreen;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.zamasoft.reader.book.b;
import net.zamasoft.reader.book.d;
import net.zamasoft.reader.shelf.b;
import net.zamasoft.reader.util.CheckUpdateController;
import net.zamasoft.reader.util.c;
import org.apache.commons.lang3.SystemUtils;

public class ReaderApplication extends Application {
  private static Logger b = Logger.getLogger(ReaderApplication.class.getName());
  
  private Stage c;
  
  private MainController d;
  
  public File a = new File(System.getProperty("user.home"));
  
  private static ReaderApplication e;
  
  private final DoubleProperty f = (DoubleProperty)new SimpleDoubleProperty();
  
  private final BooleanProperty g = (BooleanProperty)new SimpleBooleanProperty(false);
  
  private IntegerProperty h = null;
  
  private final BooleanProperty i = (BooleanProperty)new SimpleBooleanProperty(false);
  
  private final BooleanProperty j = (BooleanProperty)new SimpleBooleanProperty(false);
  
  private final StringProperty k = (StringProperty)new SimpleStringProperty("");
  
  private final IntegerProperty l = (IntegerProperty)new SimpleIntegerProperty(-1);
  
  private final StringProperty m = (StringProperty)new SimpleStringProperty("");
  
  private final StringProperty n = (StringProperty)new SimpleStringProperty("");
  
  private int o = 0;
  
  public DoubleProperty a() {
    return this.f;
  }
  
  public BooleanProperty b() {
    return this.g;
  }
  
  public IntegerProperty c() {
    return this.h;
  }
  
  public BooleanProperty d() {
    return this.i;
  }
  
  public BooleanProperty e() {
    return this.j;
  }
  
  public StringProperty f() {
    return this.k;
  }
  
  public IntegerProperty g() {
    return this.l;
  }
  
  public StringProperty h() {
    return this.m;
  }
  
  public StringProperty i() {
    return this.n;
  }
  
  public static ReaderApplication getInstance() {
    return e;
  }
  
  public static void setTitle(String paramString) {
    if (paramString == null || paramString.length() == 0) {
      e.k().setTitle(b.b + " version 1.6.3");
    } else {
      paramString = paramString.replaceAll("[\n\r]+", "");
      e.k().setTitle(paramString + " - " + paramString + " version 1.6.3");
    } 
  }
  
  public void start(Stage paramStage) throws Exception {
    e = this;
    this.c = paramStage;
    this.c.initStyle(StageStyle.UNDECORATED);
    this.c.getIcons().add(new Image(ReaderApplication.class.getResource("icons/icon.png").toString()));
    Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
    double d1 = rectangle2D.getMinX();
    double d2 = rectangle2D.getMinY();
    double d3 = rectangle2D.getWidth();
    double d4 = rectangle2D.getHeight();
    this.g.set(true);
    if (b.j.exists()) {
      Properties properties = new Properties();
      try {
        FileInputStream fileInputStream = new FileInputStream(b.j);
        try {
          properties.loadFromXML(fileInputStream);
        } finally {
          fileInputStream.close();
        } 
        this.a = new File(properties.getProperty("defaultDir"));
        d1 = Double.parseDouble(properties.getProperty("windowX"));
        d2 = Double.parseDouble(properties.getProperty("windowY"));
        d3 = Double.parseDouble(properties.getProperty("windowWidth"));
        d4 = Double.parseDouble(properties.getProperty("windowHeight"));
        String str1 = properties.getProperty("brightness");
        if (str1 != null)
          this.f.set(Double.parseDouble(str1)); 
        String str2 = properties.getProperty("displayRequired");
        if (str2 != null && !Boolean.parseBoolean(str2))
          this.g.set(false); 
        String str3 = properties.getProperty("displayOrientation");
        if (str3 != null && this.h != null)
          this.h.set(Integer.parseInt(str3)); 
        this.i.set(Boolean.valueOf(properties.getProperty("foldToolbar", "false")).booleanValue());
        this.j.set(Boolean.valueOf(properties.getProperty("useProxy", "false")).booleanValue());
        this.k.set(properties.getProperty("proxyHost", ""));
        this.l.set(Integer.valueOf(properties.getProperty("proxyPort", "-1")).intValue());
        this.m.set(properties.getProperty("proxyUser", ""));
        this.n.set(properties.getProperty("proxyPassword", ""));
      } catch (Exception exception) {
        b.log(Level.WARNING, "設定ファイルが異常です。", exception);
      } 
    } 
    a.a a = a.a().k();
    if (a != null) {
      boolean bool = CheckUpdateController.a(a.a);
      if (bool) {
        try {
          Desktop.getDesktop().browse(URI.create(a.b));
        } catch (IOException iOException) {
          b.log(Level.FINE, "リンク切れ", iOException);
        } 
        l();
        System.exit(0);
        return;
      } 
    } 
    if (this.g.get())
      a(true); 
    if (SystemUtils.IS_OS_MAC) {
      Timer timer = new Timer();
      timer.schedule(new TimerTask(this) {
            public void run() {
              this.a.a(this.a.g.get());
            }
          },  10000L, 10000L);
    } else {
      this.g.addListener(new ChangeListener<Boolean>(this) {
            public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
              this.a.a(param1Boolean2.booleanValue());
            }
          });
    } 
    if (SystemUtils.IS_OS_WINDOWS)
      try {
        PointerByReference pointerByReference = new PointerByReference();
        if (XUser32.INSTANCE.GetAutoRotationState((PointerType)pointerByReference)) {
          Pointer pointer = pointerByReference.getPointer();
          if (pointer.getInt(0L) == 0)
            this.h = (IntegerProperty)new SimpleIntegerProperty(); 
        } 
        if (this.h != null) {
          if (this.h.get() != 0)
            a(this.h.get()); 
          this.h.addListener(new ChangeListener<Number>(this) {
                public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
                  this.a.a(param1Number2.intValue());
                }
              });
        } 
      } catch (Throwable throwable) {} 
    if (d1 > rectangle2D.getWidth())
      d1 = rectangle2D.getWidth() - d3; 
    if (d2 > rectangle2D.getHeight())
      d2 = rectangle2D.getHeight() - d4; 
    if (d1 < rectangle2D.getMinX())
      d1 = rectangle2D.getMinX(); 
    if (d2 < rectangle2D.getMinY())
      d2 = rectangle2D.getMinY(); 
    this.c.setX(d1);
    this.c.setY(d2);
    this.c.setWidth(d3);
    this.c.setHeight(d4);
    List<String> list = getParameters().getRaw();
    FXMLLoader fXMLLoader = c.a(MainController.class.getResource(SystemUtils.IS_OS_MAC ? "main-mac.fxml" : "main.fxml"));
    Parent parent = (Parent)fXMLLoader.load();
    this.d = (MainController)fXMLLoader.getController();
    this.c.setOnCloseRequest(new EventHandler<WindowEvent>(this) {
          public void a(WindowEvent param1WindowEvent) {
            ReaderApplication.getInstance().k().close();
            System.exit(0);
          }
        });
    Runtime.getRuntime().addShutdownHook(new Thread(this) {
          public void run() {
            this.a.l();
          }
        });
    Scene scene = new Scene(parent, this.c.getWidth(), this.c.getHeight());
    this.c.setScene(scene);
    this.c.show();
    if (list.size() <= 0) {
      this.d.a();
    } else {
      String str = list.get(0);
      b b = d.a(new File(str));
      this.d.a((b)b);
    } 
    if (SystemUtils.IS_OS_MAC) {
      a a1 = a.a();
      if (a1 != null) {
        String str = b.a.getString("appName");
        a a2 = a.g(str);
        a2.c(str);
        a2.b("1.6.3");
        Image image = new Image(ReaderApplication.class.getResource("icons/ConTenDoViewer.png").toString());
        a2.a(image);
        Stage stage = a2.a();
        VBox vBox = (VBox)stage.getScene().getRoot().getChildrenUnmodifiable().get(0);
        Button button1 = new Button(b.a.getString("legal"));
        button1.setOnAction(paramActionEvent -> {
              try {
                File file1 = new File(b.i, "legal.html");
                File file2 = new File("legal");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file1), "UTF-8");
                PrintWriter printWriter = new PrintWriter(outputStreamWriter);
                try {
                  printWriter.println("<pre style='font-size:12pt;white-space:pre-wrap;'>");
                  if (file2.exists()) {
                    File file = new File(file2, "DEPENDENCIES.txt");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
                    try {
                      for (String str = bufferedReader.readLine(); str != null; str = bufferedReader.readLine())
                        printWriter.println(str); 
                      bufferedReader.close();
                    } catch (Throwable throwable) {
                      try {
                        bufferedReader.close();
                      } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                      } 
                      throw throwable;
                    } 
                    for (File file3 : file2.listFiles()) {
                      if (file3.isDirectory())
                        for (File file4 : file3.listFiles()) {
                          if (file4.getName().startsWith("LICENSE") || file4.getName().endsWith(".md")) {
                            printWriter.println();
                            printWriter.println("--------- " + file4.getPath() + " ---------");
                            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(file4), "ISO-8859-1"));
                            try {
                              for (String str = bufferedReader1.readLine(); str != null; str = bufferedReader1.readLine())
                                printWriter.println(str); 
                              bufferedReader1.close();
                            } catch (Throwable throwable) {
                              try {
                                bufferedReader1.close();
                              } catch (Throwable throwable1) {
                                throwable.addSuppressed(throwable1);
                              } 
                              throw throwable;
                            } 
                          } 
                        }  
                    } 
                  } 
                  printWriter.println("</pre>");
                  printWriter.close();
                } catch (Throwable throwable) {
                  try {
                    printWriter.close();
                  } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                  } 
                  throw throwable;
                } 
                Desktop.getDesktop().browse(file1.toURI());
              } catch (IOException iOException) {
                b.log(Level.FINE, "リンク切れ", iOException);
              } 
            });
        vBox.getChildren().add(button1);
        Button button2 = new Button(b.a.getString("site"));
        button2.setOnAction(paramActionEvent -> {
              try {
                Desktop.getDesktop().browse(URI.create("https://contendo.jp/"));
              } catch (IOException iOException) {
                b.log(Level.FINE, "リンク切れ", iOException);
              } 
            });
        vBox.getChildren().add(button2);
        vBox.getChildren().add(new Label("iPress Japan"));
        stage.getScene().getStylesheets().add(ReaderApplication.class.getResource("about.css").toExternalForm());
        Menu menu = a1.a(str, stage);
        ((MenuItem)menu.getItems().get(0)).setText(b.a.getString("aboutApp"));
        ((MenuItem)menu.getItems().get(2)).setText(b.a.getString("hideApp"));
        ((MenuItem)menu.getItems().get(3)).setText(b.a.getString("hideOthers"));
        ((MenuItem)menu.getItems().get(4)).setText(b.a.getString("showAll"));
        ((MenuItem)menu.getItems().get(6)).setText(b.a.getString("exitApp"));
        ((MenuItem)menu.getItems().get(6)).setOnAction(paramActionEvent -> {
              getInstance().k().close();
              System.exit(0);
            });
        a1.a(menu);
      } 
    } 
    SplashScreen splashScreen = SplashScreen.getSplashScreen();
    if (splashScreen != null)
      splashScreen.close(); 
  }
  
  private void a(boolean paramBoolean) {
    try {
      if (SystemUtils.IS_OS_WINDOWS) {
        XKernel32.INSTANCE.SetThreadExecutionState(Integer.MIN_VALUE | (paramBoolean ? 3 : 0));
      } else if (SystemUtils.IS_OS_MAC) {
        CoreFoundation.b b = IOKit.INSTANCE.IOPSCopyExternalPowerAdapterDetails();
        if (paramBoolean && b != null && this.o == 0) {
          char[] arrayOfChar = "NoDisplaySleepAssertion".toCharArray();
          CoreFoundation.c c1 = CoreFoundation.INSTANCE.CFStringCreateWithCharacters(null, arrayOfChar, new NativeLong(arrayOfChar.length));
          CoreFoundation.c c2 = CoreFoundation.INSTANCE.CFStringCreateWithCharacters(null, arrayOfChar, new NativeLong(arrayOfChar.length));
          IntByReference intByReference = new IntByReference();
          int i = IOKit.INSTANCE.IOPMAssertionCreateWithName(c1, 255L, c2, (PointerType)intByReference);
          if (i == 0)
            this.o = intByReference.getValue(); 
        } 
        if ((!paramBoolean || b == null) && this.o != 0) {
          int i = IOKit.INSTANCE.IOPMAssertionRelease(this.o);
          if (i == 0)
            this.o = 0; 
        } 
      } 
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
  }
  
  private void a(int paramInt) {
    try {
      XUser32.INSTANCE.SetDisplayAutoRotationPreferences(paramInt);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
  }
  
  public static double getRenderScale() {
    Screen screen = Screen.getPrimary();
    try {
      Method method;
      try {
        method = screen.getClass().getDeclaredMethod("getScale", new Class[0]);
      } catch (NoSuchMethodException noSuchMethodException) {
        method = screen.getClass().getDeclaredMethod("getRenderScale", new Class[0]);
      } 
      method.setAccessible(true);
      return ((Float)method.invoke(screen, new Object[0])).doubleValue();
    } catch (Exception exception) {
      return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getDefaultTransform().getScaleX();
    } 
  }
  
  public MainController j() {
    return this.d;
  }
  
  public Stage k() {
    return this.c;
  }
  
  private void l() {
    if (this.d != null)
      this.d.close(); 
    Properties properties = new Properties();
    properties.setProperty("defaultDir", this.a.getAbsolutePath());
    properties.setProperty("windowX", String.valueOf(this.c.getX()));
    properties.setProperty("windowY", String.valueOf(this.c.getY()));
    properties.setProperty("windowWidth", String.valueOf(this.c.getWidth()));
    properties.setProperty("windowHeight", String.valueOf(this.c.getHeight()));
    properties.setProperty("brightness", String.valueOf(this.f.get()));
    properties.setProperty("displayRequired", String.valueOf(this.g.get()));
    if (this.h != null)
      properties.setProperty("displayOrientation", String.valueOf(this.h.get())); 
    properties.setProperty("foldToolbar", String.valueOf(this.i.get()));
    properties.setProperty("useProxy", String.valueOf(this.j.get()));
    properties.setProperty("proxyHost", String.valueOf(this.k.get()));
    properties.setProperty("proxyPort", String.valueOf(this.l.get()));
    properties.setProperty("proxyUser", String.valueOf(this.m.get()));
    properties.setProperty("proxyPassword", String.valueOf(this.n.get()));
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(b.j);
      try {
        properties.storeToXML(fileOutputStream, b.b);
      } finally {
        fileOutputStream.close();
      } 
    } catch (Exception exception) {
      b.log(Level.WARNING, "設定ファイルに書き込めませんでした。", exception);
    } 
  }
  
  public static void main(String... paramVarArgs) throws Exception {
    System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
    if (SystemUtils.IS_OS_MAC)
      System.setProperty("apple.laf.useScreenMenuBar", "true"); 
    SplashScreen splashScreen = SplashScreen.getSplashScreen();
    if (splashScreen != null && !SystemUtils.IS_OS_MAC) {
      Thread thread = new Thread(splashScreen) {
          public void run() {
            Graphics2D graphics2D = this.a.createGraphics();
            try {
              ImageInputStream imageInputStream = ImageIO.createImageInputStream(ReaderApplication.class.getResourceAsStream("loader.gif"));
              try {
                ImageReader imageReader = ImageIO.getImageReadersByFormatName("gif").next();
                imageReader.setInput(imageInputStream);
                int i = imageReader.getNumImages(true);
                int j = imageReader.getWidth(0);
                int k = imageReader.getHeight(0);
                j = ((this.a.getSize()).width - j) / 2;
                k = (this.a.getSize()).height - k - 30;
                while (this.a.isVisible()) {
                  for (byte b = 0; b < i; b++) {
                    BufferedImage bufferedImage = imageReader.read(b);
                    graphics2D.drawImage(bufferedImage, j, k, null);
                    this.a.update();
                    Thread.sleep(100L);
                  } 
                } 
              } finally {
                imageInputStream.close();
              } 
            } catch (Exception exception) {}
          }
        };
      thread.start();
    } 
    if (SystemUtils.IS_OS_WINDOWS) {
      Thread thread = new Thread() {
          WinUser.HHOOK a;
          
          final User32 b = User32.INSTANCE;
          
          public void run() {
            WinDef.HMODULE hMODULE = Kernel32.INSTANCE.GetModuleHandle(null);
            WinUser.LowLevelKeyboardProc lowLevelKeyboardProc = new WinUser.LowLevelKeyboardProc(this) {
                public WinDef.LRESULT callback(int param2Int, WinDef.WPARAM param2WPARAM, WinUser.KBDLLHOOKSTRUCT param2KBDLLHOOKSTRUCT) {
                  return (param2KBDLLHOOKSTRUCT.vkCode == 44) ? new WinDef.LRESULT(1L) : this.a.b.CallNextHookEx(this.a.a, param2Int, param2WPARAM, new WinDef.LPARAM(Pointer.nativeValue(param2KBDLLHOOKSTRUCT.getPointer())));
                }
              };
            while (true) {
              this.a = this.b.SetWindowsHookEx(13, (WinUser.HOOKPROC)lowLevelKeyboardProc, (WinDef.HINSTANCE)hMODULE, 0);
              WinUser.MSG mSG = new WinUser.MSG();
              while (true) {
                try {
                  Thread.sleep(10L);
                } catch (InterruptedException interruptedException) {}
                int i = this.b.GetMessage(mSG, null, 0, 0);
                if (i == -1) {
                  this.b.UnhookWindowsHookEx(this.a);
                  continue;
                } 
                this.b.TranslateMessage(mSG);
                this.b.DispatchMessage(mSG);
              } 
              break;
            } 
          }
        };
      thread.setDaemon(true);
      thread.setPriority(1);
      thread.start();
    } 
    launch(paramVarArgs);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/ReaderApplication.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */