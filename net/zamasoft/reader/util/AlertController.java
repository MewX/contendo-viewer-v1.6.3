package net.zamasoft.reader.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.b;

public class AlertController {
  private static Logger a = Logger.getLogger(AlertController.class.getName());
  
  @FXML
  private Label titleText;
  
  @FXML
  private Label messageText;
  
  @FXML
  private Button yesButton;
  
  @FXML
  private Button noButton;
  
  private boolean b = false;
  
  @FXML
  private void yesAction(ActionEvent paramActionEvent) {
    this.b = true;
    this.titleText.getScene().getWindow().hide();
  }
  
  @FXML
  private void noAction(ActionEvent paramActionEvent) {
    this.b = false;
    this.titleText.getScene().getWindow().hide();
  }
  
  public static boolean a(String paramString1, String paramString2, boolean paramBoolean) {
    Stage stage = ReaderApplication.getInstance().k();
    Window window = stage.getScene().getWindow();
    return a(paramString1, paramString2, paramBoolean, window);
  }
  
  public static boolean a(String paramString1, String paramString2, boolean paramBoolean, Window paramWindow) {
    try {
      FXMLLoader fXMLLoader = c.a(AlertController.class.getResource("alert.fxml"));
      fXMLLoader.load();
      Parent parent = (Parent)fXMLLoader.getRoot();
      AlertController alertController = (AlertController)fXMLLoader.getController();
      alertController.titleText.setText(paramString1);
      alertController.messageText.setText(paramString2);
      Scene scene = new Scene(parent);
      Stage stage = new Stage(StageStyle.UTILITY);
      stage.setWidth(460.0D);
      stage.setX(paramWindow.getX() + (paramWindow.getWidth() - stage.getWidth()) / 2.0D);
      stage.setY(paramWindow.getY() + (paramWindow.getHeight() - 200.0D) / 2.0D);
      stage.setScene(scene);
      stage.initStyle(StageStyle.UNDECORATED);
      stage.initOwner(paramWindow);
      stage.initModality(Modality.WINDOW_MODAL);
      stage.setResizable(false);
      stage.setTitle(paramString1);
      if (!paramBoolean) {
        alertController.yesButton.setText(b.a.getString("confirm"));
        ((Pane)alertController.noButton.getParent()).getChildren().remove(alertController.noButton);
      } 
      stage.showAndWait();
      return alertController.b;
    } catch (IOException iOException) {
      a.log(Level.SEVERE, "UIを生成できませんでした", iOException);
      throw new RuntimeException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/AlertController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */