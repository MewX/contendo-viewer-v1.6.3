package net.zamasoft.reader.shelf;

import com.b.a.a;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.a;
import net.zamasoft.reader.b;
import net.zamasoft.reader.util.AlertController;
import net.zamasoft.reader.util.LogoutController;
import org.apache.commons.lang3.SystemUtils;

public class ToolBarController implements Initializable, a {
  private static Logger c = Logger.getLogger(ToolBarController.class.getName());
  
  private ShelfController d;
  
  @FXML
  private Region root;
  
  @FXML
  private Button foldButton;
  
  @FXML
  private Button searchButton;
  
  @FXML
  private Button reloadButton;
  
  @FXML
  private Button sortButton;
  
  @FXML
  private Button addButton;
  
  @FXML
  private Button fullScreenButton;
  
  @FXML
  private Button logoutButton;
  
  @FXML
  private Button configButton;
  
  @FXML
  private Button guideButton;
  
  @FXML
  private Button shopButton;
  
  @FXML
  protected ComboBox<String> sortCombo;
  
  private ChangeListener<Boolean> e = new ChangeListener<Boolean>(this) {
      public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
        Pane pane = (Pane)this.a.fullScreenButton.getGraphic();
        ((Node)pane.getChildren().get(0)).setVisible(!param1Boolean2.booleanValue());
        ((Node)pane.getChildren().get(1)).setVisible(param1Boolean2.booleanValue());
      }
    };
  
  ChangeListener<Number> a = new ChangeListener<Number>(this) {
      public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
        if (param1Number2.intValue() == -1)
          return; 
        int i = param1Number2.intValue();
        if (this.a.d.tabPane.getSelectionModel().getSelectedIndex() == 0 && i >= 1)
          i++; 
        this.a.d.a(i);
        this.a.sortCombo.setVisible(false);
      }
    };
  
  ChangeListener<Boolean> b = new ChangeListener<Boolean>(this) {
      public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
        this.a.sortCombo.setVisible(param1Boolean2.booleanValue());
      }
    };
  
  private static final int f = 32;
  
  private static final int g = 200;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    ReadOnlyBooleanProperty readOnlyBooleanProperty = ReaderApplication.getInstance().k().fullScreenProperty();
    readOnlyBooleanProperty.addListener(this.e);
    this.e.changed((ObservableValue)readOnlyBooleanProperty, readOnlyBooleanProperty.getValue(), readOnlyBooleanProperty.getValue());
  }
  
  public void a() {
    ReaderApplication.getInstance().k().fullScreenProperty().removeListener(this.e);
  }
  
  public void a(ShelfController paramShelfController) {
    this.d = paramShelfController;
    this.d.tabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(this) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            this.a.b();
          }
        });
    b();
  }
  
  protected void b() {
    ObservableList observableList;
    if (this.d.tabPane.getSelectionModel().getSelectedIndex() == 0) {
      this.searchButton.setDisable((this.d.userStack.getChildren().get(this.d.userStack.getChildren().size() - 1) != this.d.a.root));
      observableList = FXCollections.observableArrayList((Object[])new String[] { b.a.getString("sortByLastRead"), b.a.getString("sortByDownloaded"), b.a.getString("sortByTitle"), b.a.getString("sortByTitleKana"), b.a.getString("sortByAuthor"), b.a.getString("sortByAuthorKana") });
      this.addButton.setDisable(false);
      this.reloadButton.setDisable(false);
      this.sortButton.setDisable(false);
    } else {
      this.searchButton.setDisable((this.d.b == null || this.d.webStack.getChildren().get(this.d.webStack.getChildren().size() - 1) != this.d.b.root));
      observableList = FXCollections.observableArrayList((Object[])new String[] { b.a.getString("sortByLastRead"), b.a.getString("sortByPurchased"), b.a.getString("sortByDownloaded"), b.a.getString("sortByTitle"), b.a.getString("sortByTitleKana"), b.a.getString("sortByAuthor"), b.a.getString("sortByAuthorKana") });
      this.addButton.setDisable(true);
      this.reloadButton.setDisable(!a.a().h());
      this.sortButton.setDisable(!a.a().h());
    } 
    this.logoutButton.setDisable(!a.a().h());
    this.sortCombo.getSelectionModel().selectedIndexProperty().removeListener(this.a);
    this.sortCombo.setVisible(false);
    this.sortCombo.setItems(observableList);
    if (this.d.f() != null)
      this.sortCombo.getSelectionModel().select((this.d.f()).booksController.a()); 
    this.sortCombo.getSelectionModel().selectedIndexProperty().addListener(this.a);
    this.sortCombo.focusedProperty().addListener(this.b);
    boolean bool = ReaderApplication.getInstance().d().get();
    a(this.reloadButton);
    a(this.searchButton);
    a(this.sortButton);
    a(this.addButton);
    a(this.fullScreenButton);
    a(this.logoutButton);
    a(this.configButton);
    this.logoutButton.setVisible(!bool);
    this.configButton.setVisible(!bool);
    a(this.guideButton);
    a(this.shopButton);
    this.sortCombo.setPrefWidth(bool ? 32.0D : 200.0D);
    Pane pane = (Pane)this.foldButton.getGraphic();
    ((Node)pane.getChildren().get(0)).setVisible(!bool);
    ((Node)pane.getChildren().get(1)).setVisible(bool);
  }
  
  @FXML
  private void foldAction(ActionEvent paramActionEvent) {
    boolean bool = !ReaderApplication.getInstance().d().get() ? true : false;
    ReaderApplication.getInstance().d().set(bool);
    b();
  }
  
  private void a(Button paramButton) {
    paramButton.setText(ReaderApplication.getInstance().d().get() ? "" : paramButton.getTooltip().getText());
    paramButton.setPrefWidth(ReaderApplication.getInstance().d().get() ? 32.0D : 200.0D);
  }
  
  @FXML
  private void addAction(ActionEvent paramActionEvent) {
    ReaderApplication readerApplication = ReaderApplication.getInstance();
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(b.a.getString("selectFile"));
    if (readerApplication.a.isDirectory() && readerApplication.a.canRead())
      fileChooser.setInitialDirectory(readerApplication.a); 
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("EPUB/PDF", new String[] { "*.epub", "*.pdf" }));
    File file = fileChooser.showOpenDialog((Window)ReaderApplication.getInstance().k());
    if (file == null)
      return; 
    try {
      readerApplication.a = file.getParentFile();
      this.d.a(file);
    } catch (Exception exception) {
      c.log(Level.FINE, "ファイルを読み込めませんでした", exception);
      AlertController.a(b.a.getString("failOnLoad"), b.a.getString("failOnLoadMessage"), false);
    } 
  }
  
  @FXML
  private void searchAction(ActionEvent paramActionEvent) {
    this.d.e();
  }
  
  @FXML
  private void configAction(ActionEvent paramActionEvent) {
    ReaderApplication.getInstance().j().b();
  }
  
  @FXML
  private void reloadAction(ActionEvent paramActionEvent) {
    this.d.f().a();
  }
  
  @FXML
  private void sortAction(ActionEvent paramActionEvent) {
    this.sortCombo.setVisible(true);
    this.sortCombo.show();
    this.sortCombo.requestFocus();
  }
  
  @FXML
  private void logoutAction(ActionEvent paramActionEvent) {
    if (LogoutController.a()) {
      a.a().d();
      this.d.d();
    } 
  }
  
  @FXML
  private void openGuideAction(ActionEvent paramActionEvent) {
    try {
      String str;
      if (SystemUtils.IS_OS_WINDOWS) {
        str = "https://contendo.jp/topics/guide/viewer/windows.html";
      } else {
        str = "https://contendo.jp/topics/guide/viewer/mac.html";
      } 
      Desktop.getDesktop().browse(URI.create(str));
    } catch (IOException iOException) {
      c.log(Level.FINE, "リンク切れ", iOException);
    } 
  }
  
  @FXML
  private void openShopAction(ActionEvent paramActionEvent) {
    try {
      Desktop.getDesktop().browse(URI.create("https://contendo.jp/"));
    } catch (IOException iOException) {
      c.log(Level.FINE, "リンク切れ", iOException);
    } 
  }
  
  @FXML
  private void fullScreenAction(ActionEvent paramActionEvent) {
    Stage stage = ReaderApplication.getInstance().k();
    stage.setFullScreen(!stage.isFullScreen());
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/ToolBarController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */