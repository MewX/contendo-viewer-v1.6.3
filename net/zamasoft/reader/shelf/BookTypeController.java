package net.zamasoft.reader.shelf;

import com.b.a.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.json.Json;
import javax.json.JsonObject;
import net.zamasoft.reader.b;
import net.zamasoft.reader.book.b;
import net.zamasoft.reader.book.d;
import net.zamasoft.reader.util.AlertController;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

public class BookTypeController {
  private static Logger a = Logger.getLogger(BookTypeController.class.getName());
  
  @FXML
  private ProgressBar progress;
  
  @FXML
  private Label message;
  
  @FXML
  private Pane info;
  
  @FXML
  private Label downloadCount;
  
  @FXML
  private Label downloadTerm;
  
  @FXML
  private Label readTerm;
  
  @FXML
  private Button readButton;
  
  @FXML
  private Button downloadButton;
  
  @FXML
  private Button cancelButton;
  
  @FXML
  private Button deleteButton;
  
  @FXML
  private Button redownloadButton;
  
  private c.a b;
  
  private b c;
  
  private BookInfoController d;
  
  private Task<Void> e;
  
  public void a(BookInfoController paramBookInfoController, c.a parama) {
    this.d = paramBookInfoController;
    this.b = parama;
    this.message.setText(b.a.getString(parama.d()));
    this.message.setStyle("-fx-text-fill: Black;");
    a();
  }
  
  public void a(BookInfoController paramBookInfoController, b paramb) {
    this.d = paramBookInfoController;
    this.c = paramb;
    a();
  }
  
  public void a() {
    this.cancelButton.setVisible(false);
    this.downloadButton.setVisible(false);
    this.readButton.setVisible(false);
    this.deleteButton.setDisable(false);
    this.redownloadButton.setVisible(false);
    this.progress.setVisible(false);
    if (this.b != null) {
      if (this.e != null) {
        this.cancelButton.setVisible(true);
        this.cancelButton.setDisable(false);
        this.deleteButton.setDisable(true);
        this.progress.setVisible(true);
      } else if (this.b.a()) {
        this.readButton.setVisible(true);
        this.redownloadButton.setVisible((this.b.e() != 0));
      } else {
        this.downloadButton.setVisible(true);
        this.deleteButton.setDisable(true);
      } 
      if (this.b.e() == -1) {
        this.downloadCount.setText(b.a.getString("unlimited"));
      } else {
        this.downloadCount.setText(String.valueOf(this.b.e()));
      } 
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      if (this.b.f() == 0L) {
        this.downloadTerm.setText(b.a.getString("unlimited"));
      } else {
        this.downloadTerm.setText(simpleDateFormat.format(new Date(this.b.f())));
      } 
      if (this.b.g() == 0L) {
        this.readTerm.setText(b.a.getString("unlimited"));
      } else {
        this.readTerm.setText(simpleDateFormat.format(new Date(this.b.g())));
      } 
    } else {
      this.readButton.setVisible(true);
      ((VBox)this.info.getParent()).getChildren().removeAll((Object[])new Node[] { (Node)this.info });
    } 
  }
  
  @FXML
  private void redownloadAction(ActionEvent paramActionEvent) {
    try {
      Integer.parseInt(this.downloadCount.getText());
      if (!AlertController.a(b.a.getString("confirm"), b.a.getString("confirmRedownloadLimited"), true, this.info.getScene().getWindow()))
        return; 
    } catch (NumberFormatException numberFormatException) {
      if (!AlertController.a(b.a.getString("confirm"), b.a.getString("confirmRedownload"), true, this.info.getScene().getWindow()))
        return; 
    } 
    downloadAction(paramActionEvent);
  }
  
  @FXML
  private void downloadAction(ActionEvent paramActionEvent) {
    File file2;
    File file1 = this.b.h();
    File file3 = this.b.i();
    if (file1.exists()) {
      file2 = file3;
      try {
        FileUtils.moveFile(file1, file3);
      } catch (IOException iOException) {}
    } else {
      file2 = null;
      try {
        file3.createNewFile();
      } catch (IOException iOException) {}
    } 
    this.message.setVisible(false);
    this.redownloadButton.setDisable(false);
    this.e = new Task<Void>(this, file1, file2, file3) {
        protected Void a() throws Exception {
          try {
            CloseableHttpClient closeableHttpClient = a.e();
            try {
              URIBuilder uRIBuilder = new URIBuilder("https://api.contendo.jp/users_api/contents_download/");
              uRIBuilder.addParameter("items_id", this.d.b.b());
              uRIBuilder.addParameter("sales_type", this.d.b.d());
              HttpGet httpGet2 = new HttpGet(uRIBuilder.build());
              httpGet2.setHeader("x-authentication-key", a.a().m());
              CloseableHttpResponse closeableHttpResponse2 = closeableHttpClient.execute((HttpUriRequest)httpGet2);
              JsonObject jsonObject = Json.createReader(closeableHttpResponse2.getEntity().getContent()).readObject();
              if (jsonObject.containsKey("error")) {
                String str = jsonObject.getJsonObject("error").getString("message");
                Platform.runLater(new Runnable(this, str) {
                      public void run() {
                        this.b.d.message.setText(this.a);
                        this.b.d.message.setStyle("-fx-text-fill: Red;");
                        this.b.d.message.setVisible(true);
                      }
                    });
                Void void_ = null;
                if (closeableHttpClient != null)
                  closeableHttpClient.close(); 
                return void_;
              } 
              String str1 = jsonObject.getString("contents_url");
              String str2 = jsonObject.getString("download_token");
              HttpGet httpGet1 = new HttpGet(str1);
              CloseableHttpResponse closeableHttpResponse1 = closeableHttpClient.execute((HttpUriRequest)httpGet1);
              long l = closeableHttpResponse1.getEntity().getContentLength();
              int i = 0;
              InputStream inputStream = closeableHttpResponse1.getEntity().getContent();
              try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.a);
                try {
                  byte[] arrayOfByte = new byte[1024];
                  int j;
                  for (j = inputStream.read(arrayOfByte); j != -1 && !isCancelled(); j = inputStream.read(arrayOfByte)) {
                    fileOutputStream.write(arrayOfByte, 0, j);
                    i += j;
                    updateProgress(i, l);
                  } 
                  fileOutputStream.close();
                } catch (Throwable throwable) {
                  try {
                    fileOutputStream.close();
                  } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                  } 
                  throw throwable;
                } 
                if (inputStream != null)
                  inputStream.close(); 
              } catch (Throwable throwable) {
                if (inputStream != null)
                  try {
                    inputStream.close();
                  } catch (Throwable throwable1) {
                    throwable.addSuppressed(throwable1);
                  }  
                throw throwable;
              } 
              Platform.runLater(new Runnable(this) {
                    public void run() {
                      this.a.d.message.setText(b.a.getString(this.a.d.b.d()));
                      this.a.d.message.setStyle("-fx-text-fill: Black;");
                      this.a.d.message.setVisible(true);
                    }
                  });
              if (isCancelled()) {
                this.a.delete();
                if (this.b != null)
                  FileUtils.moveFile(this.b, this.a); 
              } else {
                URIBuilder uRIBuilder1 = new URIBuilder("https://api.contendo.jp/users_api/contents_download/");
                uRIBuilder1.addParameter("download_token", str2);
                HttpPost httpPost = new HttpPost(uRIBuilder1.build());
                httpPost.setHeader("x-authentication-key", a.a().m());
                closeableHttpClient.execute((HttpUriRequest)httpPost);
                this.c.delete();
              } 
              Platform.runLater(new Runnable(this) {
                    public void run() {
                      this.a.d.c();
                    }
                  });
              if (closeableHttpClient != null)
                closeableHttpClient.close(); 
            } catch (Throwable throwable) {
              if (closeableHttpClient != null)
                try {
                  closeableHttpClient.close();
                } catch (Throwable throwable1) {
                  throwable.addSuppressed(throwable1);
                }  
              throw throwable;
            } 
          } catch (Exception exception) {
            BookTypeController.a.log(Level.WARNING, "ダウンロードできませんでした。", exception);
            Platform.runLater(new Runnable(this) {
                  public void run() {
                    this.a.d.message.setText(b.a.getString("downloadError"));
                    this.a.d.message.setStyle("-fx-text-fill: Red;");
                    this.a.d.message.setVisible(true);
                  }
                });
          } finally {
            this.d.e = null;
            Platform.runLater(new Runnable(this) {
                  public void run() {
                    this.a.d.a();
                  }
                });
          } 
          return null;
        }
      };
    this.progress.progressProperty().bind((ObservableValue)this.e.progressProperty());
    Thread thread = new Thread((Runnable)this.e);
    thread.start();
    a();
  }
  
  @FXML
  private void openAction(ActionEvent paramActionEvent) {
    if (this.b != null) {
      File file = this.b.h();
      try {
        b b1 = d.a(file);
        this.d.a((b)b1);
      } catch (Exception exception) {
        a.log(Level.WARNING, "ファイルを開けませんでした。", exception);
      } 
    } else {
      this.d.a(this.c);
    } 
  }
  
  @FXML
  private void deleteAction(ActionEvent paramActionEvent) {
    if (this.b != null) {
      if (!AlertController.a(b.a.getString("confirm"), b.a.getString("confirmDeleteDownloaded"), true, this.info.getScene().getWindow()))
        return; 
      this.b.h().delete();
      c();
      a();
      return;
    } 
    if (!AlertController.a(b.a.getString("confirm"), b.a.getString("confirmDelete"), true, this.downloadButton.getScene().getWindow()))
      return; 
    this.d.c();
  }
  
  private void c() {
    this.d.a.a.b();
    this.d.a.b.a(false, false);
    this.d.b = this.d.a.b.booksController.a(this.d.b.a.d());
    this.d.a.b.booksController.b();
    this.d.b.a(true, (ScrollPane)null);
    this.d.a();
  }
  
  @FXML
  private void cancelDownloadAction(ActionEvent paramActionEvent) {
    this.cancelButton.setDisable(true);
    this.e.cancel();
  }
  
  public void b() {
    if (this.e != null)
      this.e.cancel(); 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/BookTypeController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */