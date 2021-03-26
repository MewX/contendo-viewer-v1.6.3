package net.zamasoft.reader.shelf;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.zamasoft.reader.ReaderApplication;

public class ConfigController implements Initializable {
  @FXML
  private CheckBox useProxy;
  
  @FXML
  private TextField proxyHost;
  
  @FXML
  private TextField proxyPort;
  
  @FXML
  private TextField proxyUser;
  
  @FXML
  private PasswordField proxyPassword;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    this.useProxy.setSelected(ReaderApplication.getInstance().e().get());
    this.proxyHost.setText((String)ReaderApplication.getInstance().f().get());
    int i = ReaderApplication.getInstance().g().get();
    this.proxyPort.setText((i == -1) ? "" : String.valueOf(i));
    this.proxyUser.setText((String)ReaderApplication.getInstance().h().get());
    this.proxyPassword.setText((String)ReaderApplication.getInstance().i().get());
    this.useProxy.selectedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            this.a.a();
          }
        });
    a();
  }
  
  private void a() {
    boolean bool = this.useProxy.isSelected();
    this.proxyHost.setDisable(!bool);
    this.proxyPort.setDisable(!bool);
    this.proxyUser.setDisable(!bool);
    this.proxyPassword.setDisable(!bool);
  }
  
  @FXML
  private void applyAction(ActionEvent paramActionEvent) {
    ReaderApplication.getInstance().e().set(this.useProxy.isSelected());
    ReaderApplication.getInstance().f().set(this.proxyHost.getText().trim());
    int i = -1;
    try {
      i = Integer.parseInt(this.proxyPort.getText());
    } catch (NumberFormatException numberFormatException) {}
    ReaderApplication.getInstance().g().set(i);
    ReaderApplication.getInstance().h().set(this.proxyUser.getText().trim());
    ReaderApplication.getInstance().i().set(this.proxyPassword.getText().trim());
    this.useProxy.getScene().getWindow().hide();
  }
  
  @FXML
  private void cancelAction(ActionEvent paramActionEvent) {
    this.useProxy.getScene().getWindow().hide();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/ConfigController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */