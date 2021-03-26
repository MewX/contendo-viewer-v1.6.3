package net.zamasoft.reader.shelf;

import com.b.a.a;
import com.b.a.e;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.b;
import net.zamasoft.reader.util.AlertController;

public class LoginController implements Initializable {
  private static Logger a = Logger.getLogger(LoginController.class.getName());
  
  @FXML
  private Label messageLabel;
  
  @FXML
  private TextField memberIdText;
  
  @FXML
  private PasswordField passwordText;
  
  @FXML
  private Button loginButton;
  
  @FXML
  private CheckBox saveId;
  
  @FXML
  private CheckBox autoLogin;
  
  private ShelfController b;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    this.memberIdText.textProperty().addListener(new ChangeListener<String>(this) {
          public void a(ObservableValue<? extends String> param1ObservableValue, String param1String1, String param1String2) {
            this.a.loginButton.setDisable((this.a.memberIdText.getText().length() == 0));
          }
        });
    this.autoLogin.selectedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            this.a.saveId.setDisable(param1Boolean2.booleanValue());
            this.a.saveId.setSelected(true);
          }
        });
    if (a.a().i()) {
      this.memberIdText.setText(a.a().b());
      this.passwordText.setText(a.a().c());
      this.saveId.setSelected(true);
      this.loginButton.setDisable(false);
    } 
    if (a.a().j())
      this.autoLogin.setSelected(true); 
  }
  
  public void a(ShelfController paramShelfController) {
    this.b = paramShelfController;
  }
  
  @FXML
  private void loginAction(ActionEvent paramActionEvent) {
    try {
      a.a().a(this.memberIdText.getText(), this.passwordText.getText(), this.autoLogin.isSelected(), this.saveId.isSelected());
      this.b.c();
      if (!this.saveId.isSelected()) {
        this.memberIdText.setText("");
        this.passwordText.setText("");
      } 
    } catch (e e) {
      a.log(Level.WARNING, "ログインエラー", (Throwable)e);
      boolean bool = AlertController.a(b.a.getString("failOnLogin"), b.a.getString("networkError"), true);
      if (bool)
        ReaderApplication.getInstance().j().b(); 
    } catch (SecurityException securityException) {
      a.log(Level.WARNING, "ログインエラー", securityException);
      AlertController.a(b.a.getString("failOnLogin"), securityException.getMessage(), false);
    } catch (Exception exception) {
      a.log(Level.WARNING, "ログインエラー", exception);
      boolean bool = AlertController.a(b.a.getString("failOnLogin"), b.a.getString("networkError"), true);
      if (bool)
        ReaderApplication.getInstance().j().b(); 
    } 
  }
  
  @FXML
  private void forgotPasswordClicked(MouseEvent paramMouseEvent) {
    try {
      Desktop.getDesktop().browse(URI.create("https://contendo.jp/PasswordReminder/Form/"));
    } catch (IOException iOException) {
      a.log(Level.FINE, "リンク切れ", iOException);
    } 
  }
  
  @FXML
  private void registerClicked(MouseEvent paramMouseEvent) {
    try {
      Desktop.getDesktop().browse(URI.create("https://contendo.jp/MemberRegist/Form/"));
    } catch (IOException iOException) {
      a.log(Level.FINE, "リンク切れ", iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/LoginController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */