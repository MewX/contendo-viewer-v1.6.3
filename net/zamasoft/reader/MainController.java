package net.zamasoft.reader;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import net.zamasoft.reader.book.BookController;
import net.zamasoft.reader.book.b;
import net.zamasoft.reader.shelf.ConfigController;
import net.zamasoft.reader.shelf.ShelfController;
import net.zamasoft.reader.shelf.b;
import net.zamasoft.reader.util.AlertController;
import net.zamasoft.reader.util.c;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

public class MainController implements Closeable, Initializable {
  private static Logger a = Logger.getLogger(MainController.class.getName());
  
  private static final double b = 12.0D;
  
  @FXML
  private Pane titlebar;
  
  @FXML
  private Label title;
  
  @FXML
  private Button fullScreenButton;
  
  @FXML
  private Pane content;
  
  private Cursor c;
  
  private double d;
  
  private double e;
  
  private double f;
  
  private double g;
  
  private double h;
  
  private double i;
  
  private a j = null;
  
  private ShelfController k = null;
  
  private boolean l = false;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    Stage stage = ReaderApplication.getInstance().k();
    stage.fullScreenProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            if (param1Boolean2.booleanValue()) {
              AnchorPane.setTopAnchor((Node)this.a.content, Double.valueOf(1.0D));
            } else {
              AnchorPane.setTopAnchor((Node)this.a.content, Double.valueOf(36.0D));
            } 
            this.a.titlebar.setVisible(!param1Boolean2.booleanValue());
            Pane pane = (Pane)this.a.fullScreenButton.getGraphic();
            ((Node)pane.getChildren().get(0)).setVisible(!param1Boolean2.booleanValue());
            ((Node)pane.getChildren().get(1)).setVisible(param1Boolean2.booleanValue());
            this.a.l = param1Boolean2.booleanValue();
            if (this.a.l) {
              Scene scene = this.a.titlebar.getScene();
              scene.setCursor(Cursor.DEFAULT);
            } 
          }
        });
    this.title.textProperty().bind((ObservableValue)stage.titleProperty());
  }
  
  @FXML
  private void mouseMoved(MouseEvent paramMouseEvent) {
    if (this.l)
      return; 
    double d1 = paramMouseEvent.getSceneX();
    double d2 = paramMouseEvent.getSceneY();
    Scene scene = this.titlebar.getScene();
    if ((d1 <= 12.0D || d2 <= 12.0D) && d1 <= 36.0D && d2 <= 36.0D) {
      this.c = Cursor.NW_RESIZE;
    } else if ((d1 <= 12.0D || d2 >= scene.getHeight() - 12.0D) && d1 <= 36.0D && d2 >= scene.getHeight() - 36.0D) {
      this.c = Cursor.SW_RESIZE;
    } else if ((d1 >= scene.getWidth() - 12.0D || d2 <= 12.0D) && d1 >= scene.getWidth() - 36.0D && d2 <= 36.0D) {
      this.c = Cursor.NE_RESIZE;
    } else if ((d1 >= scene.getWidth() - 12.0D || d2 >= scene.getHeight() - 12.0D) && d1 >= scene.getWidth() - 36.0D && d2 >= scene.getHeight() - 36.0D) {
      this.c = Cursor.SE_RESIZE;
    } else if (d2 <= 12.0D) {
      this.c = Cursor.N_RESIZE;
    } else if (d2 >= scene.getHeight() - 12.0D) {
      this.c = Cursor.S_RESIZE;
    } else if (d1 <= 12.0D) {
      this.c = Cursor.W_RESIZE;
    } else if (d1 >= scene.getWidth() - 12.0D) {
      this.c = Cursor.E_RESIZE;
    } else {
      this.c = Cursor.DEFAULT;
    } 
    scene.setCursor(this.c);
  }
  
  @FXML
  private void mousePressed(MouseEvent paramMouseEvent) {
    if (this.l)
      return; 
    Scene scene = this.titlebar.getScene();
    if (this.c == null)
      mouseMoved(paramMouseEvent); 
    this.e = paramMouseEvent.getScreenY();
    if (this.c == Cursor.DEFAULT && paramMouseEvent.getSceneY() > this.titlebar.getHeight()) {
      this.c = null;
      return;
    } 
    Window window = scene.getWindow();
    this.d = paramMouseEvent.getScreenX();
    this.f = window.getX();
    this.g = window.getY();
    this.h = window.getWidth();
    this.i = window.getHeight();
  }
  
  @FXML
  private void mouseDragged(MouseEvent paramMouseEvent) {
    if (this.l)
      return; 
    if (this.c == null)
      return; 
    Window window = this.titlebar.getScene().getWindow();
    if (this.c == Cursor.DEFAULT) {
      window.setX(this.f + paramMouseEvent.getScreenX() - this.d);
      window.setY(this.g + paramMouseEvent.getScreenY() - this.e);
    } else {
      if (this.c == Cursor.W_RESIZE || this.c == Cursor.NW_RESIZE || this.c == Cursor.SW_RESIZE) {
        window.setX(this.f + paramMouseEvent.getScreenX() - this.d);
        window.setWidth(Math.max(600.0D, this.h - paramMouseEvent.getScreenX() + this.d));
      } 
      if (this.c == Cursor.E_RESIZE || this.c == Cursor.NE_RESIZE || this.c == Cursor.SE_RESIZE)
        window.setWidth(Math.max(600.0D, this.h + paramMouseEvent.getScreenX() - this.d)); 
      if (this.c == Cursor.N_RESIZE || this.c == Cursor.NE_RESIZE || this.c == Cursor.NW_RESIZE) {
        window.setY(this.g + paramMouseEvent.getScreenY() - this.e);
        window.setHeight(Math.max(520.0D, this.i - paramMouseEvent.getScreenY() + this.e));
      } 
      if (this.c == Cursor.S_RESIZE || this.c == Cursor.SE_RESIZE || this.c == Cursor.SW_RESIZE)
        window.setHeight(Math.max(520.0D, this.i + paramMouseEvent.getScreenY() - this.e)); 
    } 
    ObservableList observableList = Screen.getScreens();
    double d1 = Double.MAX_VALUE;
    double d2 = Double.MAX_VALUE;
    for (Screen screen : observableList) {
      d1 = Math.min(d1, screen.getVisualBounds().getMinX());
      d2 = Math.min(d2, screen.getVisualBounds().getMinY());
    } 
    if (window.getX() < d1 - window.getWidth() + 30.0D)
      window.setX(d1 - window.getWidth() + 30.0D); 
    if (window.getY() < d2)
      window.setY(d2); 
  }
  
  @FXML
  private void mouseExited(MouseEvent paramMouseEvent) {
    Scene scene = this.titlebar.getScene();
    scene.setCursor(Cursor.DEFAULT);
    if (this.l)
      return; 
  }
  
  @FXML
  private void closeAction(ActionEvent paramActionEvent) {
    if (this.j instanceof BookController) {
      a();
      return;
    } 
    ReaderApplication.getInstance().k().close();
    System.exit(0);
  }
  
  @FXML
  private void minimizeAction(ActionEvent paramActionEvent) {
    Stage stage = ReaderApplication.getInstance().k();
    stage.setIconified(true);
  }
  
  @FXML
  private void fullScreenAction(ActionEvent paramActionEvent) {
    Stage stage = ReaderApplication.getInstance().k();
    stage.setFullScreen(!stage.isFullScreen());
  }
  
  public ShelfController a() {
    ReaderApplication.setTitle(b.a.getString("shelf"));
    if (this.content.getChildren().size() == 2) {
      this.content.getChildren().remove(this.content.getChildren().size() - 1);
      ((Node)this.content.getChildren().get(this.content.getChildren().size() - 1)).setVisible(true);
      c();
      this.j = (a)this.k;
      return this.k;
    } 
    RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
    String str1 = runtimeMXBean.getName();
    String str2 = str1.split("@")[0];
    File file = new File(b.i, "pid");
    if (file.exists())
      try {
        String str = FileUtils.readFileToString(file, "UTF-8");
        if (!str.equals(str2))
          if (SystemUtils.IS_OS_WINDOWS) {
            Process process = Runtime.getRuntime().exec(new String[] { "tasklist.exe", "/fo", "csv", "/nh" });
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            CSVParser cSVParser = CSVFormat.EXCEL.parse(bufferedReader);
            for (CSVRecord cSVRecord : cSVParser) {
              if (str.equals(cSVRecord.get(1)) && "javaw.exe".equals(cSVRecord.get(0))) {
                process.destroy();
                a.log(Level.WARNING, "二重起動です");
                System.exit(0);
                return null;
              } 
            } 
            process.destroy();
          } else if (SystemUtils.IS_OS_MAC) {
            Process process = Runtime.getRuntime().exec(new String[] { "ps", "ax" });
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (String str3 = bufferedReader.readLine(); str3 != null; str3 = bufferedReader.readLine()) {
              String[] arrayOfString = str3.split(" ");
              if (str.equals(arrayOfString[1].trim()) && str3.indexOf("java") != -1) {
                process.destroy();
                a.log(Level.WARNING, "二重起動です");
                System.exit(0);
                return null;
              } 
            } 
            process.destroy();
          }  
      } catch (Exception exception) {} 
    try {
      FileUtils.write(file, str2, "UTF-8");
    } catch (IOException iOException) {
      a.log(Level.WARNING, "PIDファイルを作成できませんでした。", iOException);
      close();
    } 
    try {
      this.content.getChildren().clear();
      c();
      FXMLLoader fXMLLoader = c.a(ShelfController.class.getResource("shelf.fxml"));
      Node node = (Node)fXMLLoader.load();
      this.content.getChildren().add(node);
      this.k = (ShelfController)fXMLLoader.getController();
      this.j = (a)this.k;
      return this.k;
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  public BookController a(b paramb) {
    return a(paramb, this.k);
  }
  
  public BookController a(b paramb, ShelfController paramShelfController) {
    try {
      b b1;
      if (!paramb.z()) {
        try {
          try {
            b1 = (b)paramb.a(false);
          } catch (SecurityException securityException) {
            if (securityException instanceof com.b.a.f) {
              this.k.b();
              return null;
            } 
            a(securityException);
            return null;
          } 
        } catch (Exception exception) {
          a(exception);
          return null;
        } 
      } else {
        b1 = (b)paramb;
      } 
      this.k = paramShelfController;
      if (paramShelfController != null) {
        File file = b1.e();
        if (file.exists()) {
          file.setLastModified(System.currentTimeMillis());
        } else {
          file.createNewFile();
        } 
        paramShelfController.g();
      } 
      FXMLLoader fXMLLoader = c.a(BookController.class.getResource("book.fxml"));
      Node node = (Node)fXMLLoader.load();
      if (!this.content.getChildren().isEmpty())
        ((Node)this.content.getChildren().get(this.content.getChildren().size() - 1)).setVisible(false); 
      this.content.getChildren().add(node);
      BookController bookController = (BookController)fXMLLoader.getController();
      this.content.layout();
      bookController.a(b1);
      this.j = (a)bookController;
      return bookController;
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  private void a(Exception paramException) {
    a.log(Level.WARNING, "本を開を開く際にエラーが発生しました。", paramException);
    boolean bool1 = paramException instanceof com.b.a.e;
    boolean bool2 = AlertController.a(b.a.getString("error"), (paramException.getCause() != null) ? paramException.getCause().getMessage() : paramException.getMessage(), bool1);
    if (bool1 && bool2)
      b(); 
  }
  
  public void b() {
    Stage stage1 = ReaderApplication.getInstance().k();
    FXMLLoader fXMLLoader = c.a(ConfigController.class.getResource("config.fxml"));
    try {
      fXMLLoader.load();
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
    Parent parent = (Parent)fXMLLoader.getRoot();
    Scene scene = new Scene(parent);
    Stage stage2 = new Stage(StageStyle.UTILITY);
    stage2.setWidth(340.0D);
    stage2.setHeight(390.0D);
    stage2.setX((stage1.getWidth() - stage2.getWidth()) / 2.0D + stage1.getX());
    stage2.setY((stage1.getHeight() - stage2.getHeight()) / 2.0D + stage1.getY());
    stage2.setScene(scene);
    stage2.initStyle(StageStyle.UNDECORATED);
    stage2.initOwner(stage1.getScene().getWindow());
    stage2.initModality(Modality.WINDOW_MODAL);
    stage2.setResizable(false);
    stage2.setTitle("%config");
    stage2.showAndWait();
  }
  
  private void c() {
    if (this.j != null) {
      this.j.a();
      this.j = null;
    } 
  }
  
  public void close() {
    c();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/MainController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */