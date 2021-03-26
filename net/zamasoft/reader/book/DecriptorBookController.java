package net.zamasoft.reader.book;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.b;
import net.zamasoft.reader.util.AlertController;

public class DecriptorBookController extends BookController {
  private static Logger r = Logger.getLogger(DecriptorBookController.class.getName());
  
  public void a(Exception paramException) {
    Platform.runLater(new Runnable(this, paramException) {
          public void run() {
            this.b.root.getScene().getRoot().setCursor(null);
            if (this.a instanceof com.b.a.f) {
              ReaderApplication.getInstance().j().a(this.b.g);
              return;
            } 
            DecriptorBookController.r.log(Level.WARNING, "本の表示中にエラーが発生しました。", this.a);
            AlertController.a(b.a.getString("error"), this.a.getMessage(), false);
            ReaderApplication.getInstance().j().a();
          }
        });
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/DecriptorBookController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */