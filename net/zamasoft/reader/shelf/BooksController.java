package net.zamasoft.reader.shelf;

import com.b.a.a;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.b;
import net.zamasoft.reader.util.AlertController;
import net.zamasoft.reader.util.c;

public class BooksController implements Initializable {
  private static Logger j = Logger.getLogger(BooksController.class.getName());
  
  private List<Comparator<Node>> k = Arrays.asList((Comparator<Node>[])new Comparator[] { l, m, n, o, p, q, r });
  
  private static final Comparator<Node> l = new Comparator<Node>() {
      public int a(Node param1Node1, Node param1Node2) {
        a a1 = (a)param1Node1;
        a a2 = (a)param1Node2;
        long l1 = a1.a.t();
        long l2 = a2.a.t();
        return (l1 == l2) ? BooksController.n.compare(param1Node1, param1Node2) : ((l1 > l2) ? -1 : 1);
      }
    };
  
  private static final Comparator<Node> m = new Comparator<Node>() {
      public int a(Node param1Node1, Node param1Node2) {
        a a1 = (a)param1Node1;
        a a2 = (a)param1Node2;
        long l1 = a1.a.B().n();
        long l2 = a2.a.B().n();
        if (l1 == 0L && l2 == 0L)
          return BooksController.n.compare(param1Node1, param1Node2); 
        if (l1 == 0L)
          return 1; 
        if (l2 == 0L)
          return -1; 
        int i = -Long.valueOf(l1).compareTo(Long.valueOf(l2));
        return (i == 0) ? BooksController.n.compare(param1Node1, param1Node2) : i;
      }
    };
  
  private static final Comparator<Node> n = new Comparator<Node>() {
      public int a(Node param1Node1, Node param1Node2) {
        a a1 = (a)param1Node1;
        a a2 = (a)param1Node2;
        long l1 = a1.a.u();
        long l2 = a2.a.u();
        return (l1 == l2) ? 0 : ((l1 > l2) ? -1 : 1);
      }
    };
  
  private static final Comparator<Node> o = new Comparator<Node>() {
      public int a(Node param1Node1, Node param1Node2) {
        a a1 = (a)param1Node1;
        a a2 = (a)param1Node2;
        String str1 = a1.a.B().i_();
        String str2 = a2.a.B().i_();
        if (str1 == null)
          str1 = a1.a.B().i_(); 
        if (str2 == null)
          str2 = a2.a.B().i_(); 
        if (str1 == null && str2 == null)
          return BooksController.n.compare(param1Node1, param1Node2); 
        if (str1 == null)
          return 1; 
        if (str2 == null)
          return -1; 
        int i = c.d(str1).compareTo(c.d(str2));
        return (i == 0) ? BooksController.n.compare(param1Node1, param1Node2) : i;
      }
    };
  
  private static final Comparator<Node> p = new Comparator<Node>() {
      public int a(Node param1Node1, Node param1Node2) {
        a a1 = (a)param1Node1;
        a a2 = (a)param1Node2;
        String str1 = a1.a.B().j_();
        String str2 = a2.a.B().j_();
        if (str1 == null)
          str1 = a1.a.B().i_(); 
        if (str2 == null)
          str2 = a2.a.B().i_(); 
        if (str1 == null && str2 == null)
          return BooksController.n.compare(param1Node1, param1Node2); 
        if (str1 == null)
          return 1; 
        if (str2 == null)
          return -1; 
        int i = c.d(str1).compareTo(c.d(str2));
        return (i == 0) ? BooksController.n.compare(param1Node1, param1Node2) : i;
      }
    };
  
  private static final Comparator<Node> q = new Comparator<Node>() {
      public int a(Node param1Node1, Node param1Node2) {
        a a1 = (a)param1Node1;
        a a2 = (a)param1Node2;
        String[] arrayOfString1 = a1.a.B().f();
        String[] arrayOfString2 = a2.a.B().f();
        if (arrayOfString1 == null || arrayOfString1.length <= 0 || arrayOfString1[0] == null)
          arrayOfString1 = a1.a.B().f(); 
        if (arrayOfString2 == null || arrayOfString2.length <= 0 || arrayOfString2[0] == null)
          arrayOfString2 = a2.a.B().f(); 
        if ((arrayOfString1 == null || arrayOfString1.length <= 0 || arrayOfString1[0] == null) && (arrayOfString2 == null || arrayOfString2.length <= 0 || arrayOfString2[0] == null))
          return BooksController.n.compare(param1Node1, param1Node2); 
        if (arrayOfString1 == null || arrayOfString1.length <= 0 || arrayOfString1[0] == null)
          return 1; 
        if (arrayOfString2 == null || arrayOfString2.length <= 0 || arrayOfString2[0] == null)
          return -1; 
        int i = c.d(arrayOfString1[0]).compareTo(c.d(arrayOfString2[0]));
        return (i == 0) ? BooksController.n.compare(param1Node1, param1Node2) : i;
      }
    };
  
  private static final Comparator<Node> r = new Comparator<Node>() {
      public int a(Node param1Node1, Node param1Node2) {
        a a1 = (a)param1Node1;
        a a2 = (a)param1Node2;
        String[] arrayOfString1 = a1.a.B().g();
        String[] arrayOfString2 = a2.a.B().g();
        if (arrayOfString1 == null || arrayOfString1.length <= 0 || arrayOfString1[0] == null)
          arrayOfString1 = a1.a.B().f(); 
        if (arrayOfString2 == null || arrayOfString2.length <= 0 || arrayOfString2[0] == null)
          arrayOfString2 = a2.a.B().f(); 
        if ((arrayOfString1 == null || arrayOfString1.length <= 0 || arrayOfString1[0] == null) && (arrayOfString2 == null || arrayOfString2.length <= 0 || arrayOfString2[0] == null))
          return BooksController.n.compare(param1Node1, param1Node2); 
        if (arrayOfString1 == null || arrayOfString1.length <= 0 || arrayOfString1[0] == null)
          return 1; 
        if (arrayOfString2 == null || arrayOfString2.length <= 0 || arrayOfString2[0] == null)
          return -1; 
        int i = c.d(arrayOfString1[0]).compareTo(c.d(arrayOfString2[0]));
        return (i == 0) ? BooksController.n.compare(param1Node1, param1Node2) : i;
      }
    };
  
  public static final int a = 0;
  
  public static final int b = 1;
  
  public static final int c = 2;
  
  public static final int d = 3;
  
  public static final int e = 4;
  
  public static final int f = 5;
  
  public static final int g = 6;
  
  private int s = 0;
  
  public static final ExecutorService h = new ThreadPoolExecutor(b.q, b.q, 10000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()) {
      public ThreadFactory getThreadFactory() {
        return new ThreadFactory(this) {
            public Thread newThread(Runnable param2Runnable) {
              Thread thread = new Thread(param2Runnable);
              thread.setPriority(1);
              thread.setDaemon(true);
              return thread;
            }
          };
      }
    };
  
  @FXML
  protected ScrollPane scrollPane;
  
  @FXML
  protected Pane booksPane;
  
  public static final double i = 140.0D;
  
  private ShelfController t = null;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    a.a().g().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            this.a.e();
          }
        });
    this.scrollPane.vvalueProperty().addListener(paramObservable -> d());
    this.scrollPane.hvalueProperty().addListener(paramObservable -> d());
  }
  
  public void a(int paramInt) {
    this.s = paramInt;
    b();
  }
  
  public int a() {
    return this.s;
  }
  
  public void b() {
    FXCollections.sort(this.booksPane.getChildren(), this.k.get(this.s));
    this.booksPane.layout();
    Platform.runLater(new Runnable(this) {
          public void run() {
            this.a.d();
          }
        });
  }
  
  public void a(ShelfController paramShelfController) {
    this.t = paramShelfController;
  }
  
  public a a(b paramb) {
    return a(paramb, false);
  }
  
  public a a(b paramb, boolean paramBoolean) {
    a a = new a(paramb);
    new Object(this, a) {
      
      };
    ObservableList observableList = this.booksPane.getChildren();
    boolean bool = false;
    if (paramBoolean) {
      a a1 = a(a.a.d());
      if (a1 != null) {
        FXCollections.replaceAll(observableList, a1, a);
        bool = true;
      } 
    } 
    if (!bool)
      observableList.add(a); 
    return a;
  }
  
  private void d() {
    Bounds bounds = this.scrollPane.localToScene(this.scrollPane.getBoundsInParent());
    for (Node node : this.booksPane.getChildrenUnmodifiable()) {
      Bounds bounds1 = node.localToScene(node.getBoundsInLocal());
      if (bounds.intersects(bounds1)) {
        a a = (a)node;
        a.a(false, this.scrollPane);
      } 
    } 
  }
  
  public a a(File paramFile) {
    ObservableList observableList = this.booksPane.getChildren();
    for (Node node : observableList) {
      a a = (a)node;
      if (a.a.d().equals(paramFile))
        return a; 
    } 
    return null;
  }
  
  public void b(b paramb) {
    ObservableList observableList = this.booksPane.getChildren();
    for (byte b1 = 0; b1 < observableList.size(); b1++) {
      a a = (a)observableList.get(b1);
      if (a.a == paramb) {
        observableList.remove(b1);
        return;
      } 
    } 
  }
  
  public void c() {
    this.booksPane.getChildren().clear();
  }
  
  private void e() {
    for (Node node : this.booksPane.getChildren()) {
      a a = (a)node;
      try {
        a.a = a.a.a(true);
        a.a();
      } catch (Exception exception) {}
    } 
    b();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/BooksController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */