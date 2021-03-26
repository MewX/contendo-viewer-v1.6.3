package net.zamasoft.reader.util;

import com.b.a.a;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.zamasoft.reader.ReaderApplication;

public class LogoutController {
  private static Logger a = Logger.getLogger(LogoutController.class.getName());
  
  private boolean b;
  
  @FXML
  private Label memberIdLabel;
  
  @FXML
  private void logoutAction(ActionEvent paramActionEvent) {
    this.b = false;
    this.memberIdLabel.getScene().getWindow().hide();
  }
  
  @FXML
  private void cancelAction(ActionEvent paramActionEvent) {
    this.b = true;
    this.memberIdLabel.getScene().getWindow().hide();
  }
  
  public static boolean a() {
    try {
      Stage stage1 = ReaderApplication.getInstance().k();
      FXMLLoader fXMLLoader = c.a(LogoutController.class.getResource("logout.fxml"));
      fXMLLoader.load();
      Parent parent = (Parent)fXMLLoader.getRoot();
      LogoutController logoutController = (LogoutController)fXMLLoader.getController();
      Scene scene = new Scene(parent);
      Stage stage2 = new Stage(StageStyle.UTILITY);
      stage2.setWidth(300.0D);
      stage2.setHeight(200.0D);
      stage2.setX((stage1.getWidth() - stage2.getWidth()) / 2.0D + stage1.getX());
      stage2.setY((stage1.getHeight() - stage2.getHeight()) / 2.0D + stage1.getY());
      stage2.setScene(scene);
      stage2.initStyle(StageStyle.UNDECORATED);
      stage2.initOwner(stage1.getScene().getWindow());
      stage2.initModality(Modality.WINDOW_MODAL);
      stage2.setResizable(false);
      stage2.setTitle("%logout");
      logoutController.memberIdLabel.setText(a.a().b());
      stage2.showAndWait();
      return !logoutController.b;
    } catch (IOException iOException) {
      a.log(Level.SEVERE, "UIを生成できませんでした", iOException);
      throw new RuntimeException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/LogoutController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */