package net.zamasoft.reader.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.zamasoft.reader.b;

public class CheckUpdateController {
  private static Logger a = Logger.getLogger(CheckUpdateController.class.getName());
  
  @FXML
  private Label titleText;
  
  @FXML
  private Label messageText;
  
  @FXML
  private Button downloadButton;
  
  @FXML
  private Button cancelButton;
  
  private boolean b = false;
  
  @FXML
  private void downloadAction(ActionEvent paramActionEvent) {
    this.b = true;
    this.titleText.getScene().getWindow().hide();
  }
  
  @FXML
  private void cancelAction(ActionEvent paramActionEvent) {
    this.b = false;
    this.titleText.getScene().getWindow().hide();
  }
  
  public static boolean a(boolean paramBoolean) {
    try {
      Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
      FXMLLoader fXMLLoader = c.a(CheckUpdateController.class.getResource("checkupdate.fxml"));
      fXMLLoader.load();
      Parent parent = (Parent)fXMLLoader.getRoot();
      CheckUpdateController checkUpdateController = (CheckUpdateController)fXMLLoader.getController();
      checkUpdateController.titleText.setText(b.a.getString("updateViewer"));
      checkUpdateController.messageText.setText(b.a.getString(paramBoolean ? "requireUpdate" : "recommendUpdate"));
      Scene scene = new Scene(parent);
      Stage stage = new Stage(StageStyle.UTILITY);
      stage.setWidth(rectangle2D.getWidth());
      stage.setX(rectangle2D.getMinX());
      stage.setY(rectangle2D.getMinY() + (rectangle2D.getHeight() - 200.0D) / 2.0D);
      stage.setScene(scene);
      stage.initStyle(StageStyle.UNDECORATED);
      stage.initModality(Modality.WINDOW_MODAL);
      stage.setResizable(false);
      stage.setTitle(checkUpdateController.titleText.getText());
      if (paramBoolean)
        ((Pane)checkUpdateController.cancelButton.getParent()).getChildren().remove(checkUpdateController.cancelButton); 
      stage.showAndWait();
      return checkUpdateController.b;
    } catch (IOException iOException) {
      a.log(Level.SEVERE, "UIを生成できませんでした", iOException);
      throw new RuntimeException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/CheckUpdateController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */