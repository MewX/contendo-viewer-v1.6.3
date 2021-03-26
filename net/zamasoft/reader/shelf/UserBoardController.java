package net.zamasoft.reader.shelf;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import net.zamasoft.reader.b;
import net.zamasoft.reader.book.a.c;
import net.zamasoft.reader.book.b;
import net.zamasoft.reader.book.d;
import net.zamasoft.reader.util.AlertController;
import net.zamasoft.reader.util.c;
import org.apache.commons.io.FileUtils;

public class UserBoardController extends AbstractBoardController implements Initializable {
  private static Logger d = Logger.getLogger(UserBoardController.class.getName());
  
  public static final File b = new File(b.i, "books/user");
  
  protected SearchBoardController c;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    b.mkdirs();
    b();
  }
  
  public void a() {
    ObservableList observableList = this.booksController.booksPane.getChildren();
    for (Node node : observableList) {
      a a = (a)node;
      (new File(a.a.d(), "thumb_small")).delete();
    } 
    b();
  }
  
  public void b() {
    BooksController.h.execute(new Runnable(this) {
          public void run() {
            Platform.runLater(new Runnable(this) {
                  public void run() {
                    this.a.a.root.getScene().getRoot().setCursor(Cursor.WAIT);
                  }
                });
            Thread.yield();
            boolean bool = true;
            try {
              File file = this.a.e();
              if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                try {
                  String str = bufferedReader.readLine();
                  bool = !"1.6.3".equals(str) ? true : false;
                  int i = 0;
                  try {
                    i = Integer.parseInt(bufferedReader.readLine());
                  } catch (Exception exception) {}
                  int j = i;
                  Platform.runLater(new Runnable(this, j) {
                        public void run() {
                          this.b.a.booksController.a(this.a);
                        }
                      });
                } finally {
                  bufferedReader.close();
                } 
              } 
            } catch (Exception exception) {
              UserBoardController.d.log(Level.WARNING, "sortファイルを読み込めませんでした。", exception);
            } 
            ArrayList<b> arrayList = new ArrayList();
            HashSet<String> hashSet = new HashSet();
            File[] arrayOfFile = UserBoardController.b.listFiles(c.a);
            if (arrayOfFile != null)
              for (File file : arrayOfFile) {
                Thread.yield();
                try {
                  String str1 = file.getName();
                  String str2 = c.a(str1);
                  if (!str2.equals(str1)) {
                    File file1 = c.a(UserBoardController.b, str2);
                    file.renameTo(file1);
                    file = file1;
                  } 
                  b b = this.a.a(file);
                  if (bool)
                    (new File(b.d(), "thumb_small")).delete(); 
                  hashSet.add(file.getName());
                  arrayList.add(b);
                } catch (Exception exception) {
                  UserBoardController.d.log(Level.WARNING, "読み込めないファイルがありました:" + file, exception);
                } 
              }  
            Platform.runLater(new Runnable(this, arrayList) {
                  public void run() {
                    this.b.a.booksController.c();
                    for (b b : this.a)
                      this.b.a.booksController.a((b)b); 
                    this.b.a.booksController.b();
                    this.b.a.root.getScene().getRoot().setCursor(null);
                  }
                });
            arrayOfFile = this.a.c().listFiles(c.a);
            if (arrayOfFile != null)
              for (File file : arrayOfFile) {
                if (file.isDirectory() && !hashSet.contains(file.getName()))
                  try {
                    FileUtils.deleteDirectory(file);
                  } catch (Exception exception) {
                    UserBoardController.d.log(Level.WARNING, "キャッシュファイルを削除できませんでした。", exception);
                  }  
              }  
            if (this.a.c != null)
              this.a.c.a(); 
          }
        });
  }
  
  protected File c() {
    return new File(b, ".cache");
  }
  
  private File e() {
    File file = c();
    file.mkdirs();
    return new File(file, "sort");
  }
  
  public void d() {
    try {
      File file = e();
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file)), "UTF-8");
      try {
        outputStreamWriter.write("1.6.3");
        outputStreamWriter.write("\n");
        outputStreamWriter.write(String.valueOf(this.booksController.a()));
        outputStreamWriter.write("\n");
      } finally {
        outputStreamWriter.close();
      } 
    } catch (Exception exception) {
      d.log(Level.WARNING, "sortファイルを保存できませんでした。", exception);
    } 
  }
  
  @FXML
  protected void dragOver(DragEvent paramDragEvent) {
    if (paramDragEvent.getDragboard().hasFiles()) {
      for (File file : paramDragEvent.getDragboard().getFiles()) {
        if (!b.equals(file.getParentFile())) {
          paramDragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
          break;
        } 
      } 
    } else {
      a a = (a)paramDragEvent.getGestureSource();
      File file = ((b)a.a).c();
      if (b.equals(file.getParentFile()))
        paramDragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE); 
    } 
    paramDragEvent.consume();
  }
  
  @FXML
  protected void dragDropped(DragEvent paramDragEvent) {
    if (paramDragEvent.getDragboard().hasFiles()) {
      List list = paramDragEvent.getDragboard().getFiles();
      BooksController.h.execute(new Runnable(this, list) {
            public void run() {
              for (File file1 : this.a) {
                if (UserBoardController.b.equals(file1.getParentFile()))
                  continue; 
                Thread.yield();
                File file2 = null;
                try {
                  file2 = c.a(file1, UserBoardController.b);
                  FileUtils.deleteDirectory(new File(this.b.c(), file2.getName()));
                  b b = this.b.a(file2);
                  Platform.runLater(new Runnable(this, b) {
                        public void run() {
                          this.b.b.booksController.a((b)this.a);
                          this.b.b.booksController.b();
                        }
                      });
                } catch (Exception exception) {
                  if (file2 != null)
                    file2.delete(); 
                  UserBoardController.d.log(Level.WARNING, "ファイルをコピーできませんでした。", exception);
                  Platform.runLater(new Runnable(this) {
                        public void run() {
                          AlertController.a(b.a.getString("failOnLoad"), b.a.getString("failOnLoadMessage"), false);
                        }
                      });
                } 
              } 
            }
          });
      paramDragEvent.consume();
    } 
  }
  
  public b a(File paramFile) throws Exception {
    c c;
    try {
      b b = d.a(paramFile);
    } catch (SecurityException securityException) {
      c = new c(paramFile);
    } 
    return (b)c;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/UserBoardController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */