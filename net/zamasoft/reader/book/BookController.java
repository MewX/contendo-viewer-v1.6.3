package net.zamasoft.reader.book;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import jp.cssj.e.e.d;
import jp.cssj.homare.b.a.b.n;
import jp.cssj.homare.b.a.c;
import jp.cssj.homare.b.a.c.i;
import jp.cssj.homare.b.a.c.m;
import jp.cssj.homare.b.b.a.a;
import jp.cssj.homare.b.c.g;
import jp.cssj.homare.b.g.a;
import jp.cssj.homare.css.e.f;
import jp.cssj.homare.ua.m;
import jp.cssj.sakae.a.h;
import jp.cssj.sakae.c.b;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.a;
import net.zamasoft.reader.b;
import net.zamasoft.reader.book.epub.b;
import net.zamasoft.reader.book.epub.c;
import net.zamasoft.reader.book.epub.g;
import net.zamasoft.reader.book.epub.h;
import net.zamasoft.reader.book.epub.n;
import net.zamasoft.reader.c;
import net.zamasoft.reader.util.AlertController;
import net.zamasoft.reader.util.c;
import net.zamasoft.reader.util.f;
import net.zamasoft.reader.util.g;

public class BookController implements Initializable, a {
  private static Logger r = Logger.getLogger(BookController.class.getName());
  
  private static final double s = 16.0D;
  
  private static final int t = 3;
  
  protected final ThreadPoolExecutor a = new ThreadPoolExecutor(this, 3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()) {
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
  protected AnchorPane root;
  
  @FXML
  protected Pane stackPane;
  
  @FXML
  protected Pane maskPane;
  
  @FXML
  protected Pane disablePane;
  
  @FXML
  protected MyScrollBar scroll;
  
  @FXML
  protected ToolBarController toolbarController;
  
  @FXML
  protected SidePaneController sidepaneController;
  
  @FXML
  protected Label scrollLabel;
  
  @FXML
  protected Button audioStop;
  
  protected B b = new B();
  
  protected z[] c = new z[2];
  
  protected z[] d = new z[2];
  
  protected k e;
  
  protected int f = 0;
  
  protected b g;
  
  protected w h;
  
  protected D i = null;
  
  protected List<Rectangle> j = new ArrayList<>();
  
  protected List<Rectangle> k = new ArrayList<>();
  
  private static final long u = 1000L;
  
  private long v = 0L;
  
  private Thread w = new Thread(this) {
      public synchronized void run() {
        while (this.a.w != null) {
          long l = 1000L - System.currentTimeMillis() - this.a.v;
          try {
            if (l <= 0L) {
              wait();
            } else {
              wait(l);
            } 
            if (this.a.w == null || this.a.root.getScene() != ReaderApplication.getInstance().k().getScene() || System.currentTimeMillis() - this.a.v < 1000L || ((int)this.a.stackPane.getWidth() - 4 == this.a.g.n_() && (int)this.a.stackPane.getHeight() - 4 == this.a.g.i()))
              continue; 
            Platform.runLater(new Runnable(this) {
                  public void run() {
                    this.a.a.v();
                  }
                });
            try {
              this.a.p();
            } catch (Exception exception) {
              this.a.a(exception);
            } finally {
              Platform.runLater(new Runnable(this) {
                    public void run() {
                      this.a.a.w();
                    }
                  });
            } 
          } catch (InterruptedException interruptedException) {}
        } 
      }
    };
  
  private static final Duration x = Duration.millis(300.0D);
  
  private boolean y = false;
  
  private boolean z = false;
  
  private boolean A = false;
  
  private double B;
  
  private double C;
  
  private int D = 0;
  
  Pane l = null;
  
  EventTarget m = null;
  
  private k E;
  
  private int F;
  
  private static final byte G = 1;
  
  private static final byte H = 2;
  
  private static final byte I = 3;
  
  private ChangeListener<Number> J = new ChangeListener<Number>(this) {
      public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
        Platform.runLater(new Runnable(this) {
              public void run() {
                this.a.a.t();
              }
            });
      }
    };
  
  public static final byte n = 0;
  
  public static final byte o = 1;
  
  public static final byte p = 2;
  
  public static final byte q = 3;
  
  public void b() {
    synchronized (this.w) {
      this.v = System.currentTimeMillis();
      this.w.notifyAll();
    } 
    this.b.setTranslateY(this.stackPane.getHeight() - this.b.getHeight());
  }
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    this.sidepaneController.a(this);
    this.toolbarController.a(this);
    this.b.a(this);
    this.root.getChildren().add(this.b);
    Rectangle rectangle = new Rectangle(0.0D, 0.0D, this.stackPane.getWidth(), this.stackPane.getHeight());
    this.stackPane.setClip((Node)rectangle);
    this.stackPane.widthProperty().addListener(new ChangeListener<Number>(this, rectangle) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            this.a.setWidth(param1Number2.doubleValue());
            this.b.a((Pane[])this.b.c);
            this.b.a((Pane[])this.b.d);
            this.b.toolbarController.root.setTranslateX((this.b.stackPane.getWidth() - this.b.toolbarController.root.getWidth()) / 2.0D);
            this.b.b();
          }
        });
    this.stackPane.heightProperty().addListener(new ChangeListener<Number>(this, rectangle) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            this.a.setHeight(param1Number2.doubleValue());
            this.b.a((Pane[])this.b.c);
            this.b.a((Pane[])this.b.d);
            this.b.b();
          }
        });
    this.toolbarController.root.widthProperty().addListener(new ChangeListener<Number>(this) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            this.a.toolbarController.root.setTranslateX((this.a.stackPane.getWidth() - this.a.toolbarController.root.getWidth()) / 2.0D);
          }
        });
    this.scroll.setBookController(this);
    this.root.setFocusTraversable(true);
    this.scroll.valueProperty().addListener(new ChangeListener<Number>(this) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            int i = this.a.r();
            try {
              String str = this.a.g.b(i);
              if (str != null)
                this.a.scrollLabel.setText(str); 
            } catch (Exception exception) {
              this.a.a(exception);
            } 
          }
        });
    Scene scene = ReaderApplication.getInstance().k().getScene();
    scene.setOnKeyPressed(paramKeyEvent -> {
          KeyCode keyCode = paramKeyEvent.getCode();
          switch (null.a[keyCode.ordinal()]) {
            case 1:
            case 2:
              keyCode = paramKeyEvent.isShiftDown() ? KeyCode.PAGE_UP : KeyCode.PAGE_DOWN;
              break;
          } 
          switch (null.a[keyCode.ordinal()]) {
            case 3:
            case 4:
              try {
                d(false);
              } catch (Exception exception) {
                a(exception);
              } 
              paramKeyEvent.consume();
              break;
            case 5:
            case 6:
              try {
                c(false);
              } catch (Exception exception) {
                a(exception);
              } 
              paramKeyEvent.consume();
              break;
            case 7:
              try {
                if (this.g.l() == 2) {
                  c(false);
                } else {
                  d(false);
                } 
              } catch (Exception exception) {
                a(exception);
              } 
              paramKeyEvent.consume();
              break;
            case 8:
              try {
                if (this.g.l() == 2) {
                  d(false);
                } else {
                  c(false);
                } 
              } catch (Exception exception) {
                a(exception);
              } 
              paramKeyEvent.consume();
              break;
          } 
        });
    a(new x(this));
  }
  
  public void a() {
    if (this.w != null)
      synchronized (this.w) {
        Thread thread = this.w;
        this.w = null;
        thread.notify();
        try {
          thread.join();
        } catch (InterruptedException interruptedException) {}
      }  
    this.a.shutdown();
    try {
      this.a.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    } catch (InterruptedException interruptedException) {}
    this.g.r();
    this.g.o_().removeListener(this.J);
    this.g.a();
    this.toolbarController.a();
    g.a().e();
  }
  
  private synchronized int r() {
    int i = (int)this.scroll.getValue();
    if (this.g.l() == 2)
      i = (int)this.scroll.getMax() - i; 
    return i;
  }
  
  @FXML
  protected synchronized void scrollMouseEntered(MouseEvent paramMouseEvent) {
    this.scrollLabel.setOpacity(1.0D);
  }
  
  @FXML
  protected synchronized void scrollMouseExited(MouseEvent paramMouseEvent) {
    this.scrollLabel.setOpacity(0.0D);
  }
  
  @FXML
  protected void scrollMouseReleased(MouseEvent paramMouseEvent) {
    this.g.o_().removeListener(this.J);
    v();
    this.a.execute(new Runnable(this) {
          public void run() {
            try {
              y y = this.a.g.d(this.a.r());
              if (y == null || (this.a.g.k == y.a && this.a.g.l == y.b)) {
                Platform.runLater(new Runnable(this) {
                      public void run() {
                        this.a.a.w();
                      }
                    });
                return;
              } 
              this.a.a(y.a, y.b);
              Platform.runLater(new Runnable(this) {
                    public void run() {
                      try {
                        this.a.a.n();
                      } catch (Exception exception) {
                        this.a.a.a(exception);
                      } finally {
                        this.a.a.g.o_().addListener(this.a.a.J);
                        this.a.a.t();
                        this.a.a.w();
                      } 
                    }
                  });
            } catch (Exception exception) {
              this.a.a(exception);
            } 
          }
        });
  }
  
  @FXML
  protected void mouseMoved(MouseEvent paramMouseEvent) {
    if (!this.A && paramMouseEvent.getY() >= this.toolbarController.root.getHeight() && this.y)
      a(false); 
    if (paramMouseEvent.getY() < 16.0D && !this.y)
      a(true); 
    if (!this.A && paramMouseEvent.getX() >= this.sidepaneController.root.getWidth() && this.z)
      b(false); 
    if (paramMouseEvent.getX() < 16.0D && !this.z)
      b(true); 
  }
  
  protected void a(boolean paramBoolean) {
    Duration duration = Duration.millis(500.0D);
    TranslateTransition translateTransition = new TranslateTransition(duration, (Node)this.toolbarController.root);
    if (paramBoolean) {
      translateTransition.setToY(0.0D);
      translateTransition.play();
      this.y = true;
      this.A = false;
    } else {
      this.toolbarController.b();
      translateTransition.setToY(-this.toolbarController.root.getHeight() + 5.0D);
      translateTransition.play();
      this.y = false;
      if (!this.z)
        this.root.requestFocus(); 
    } 
  }
  
  protected void b(boolean paramBoolean) {
    Duration duration = Duration.millis(500.0D);
    TranslateTransition translateTransition1 = new TranslateTransition(duration, (Node)this.sidepaneController.root);
    TranslateTransition translateTransition2 = new TranslateTransition(duration, (Node)this.b);
    if (paramBoolean) {
      translateTransition1.setToX(0.0D);
      translateTransition1.play();
      translateTransition2.setToX(0.0D);
      translateTransition2.play();
      this.z = true;
      this.A = false;
    } else {
      this.sidepaneController.b();
      translateTransition1.setToX(-this.sidepaneController.root.getWidth());
      translateTransition1.play();
      translateTransition2.setToX(-this.b.getWidth() + 10.0D);
      translateTransition2.play();
      this.z = false;
      if (!this.y)
        this.root.requestFocus(); 
    } 
  }
  
  @FXML
  protected void mousePressed(MouseEvent paramMouseEvent) {
    this.B = paramMouseEvent.getX();
    this.C = paramMouseEvent.getY();
    if (this.l != null) {
      ((Pane)this.l.getParent()).getChildren().remove(this.l);
      this.l = null;
      this.m = null;
      paramMouseEvent.consume();
      return;
    } 
  }
  
  @FXML
  protected void mouseClicked(MouseEvent paramMouseEvent) {
    if (paramMouseEvent.isConsumed())
      return; 
    if (Math.abs(this.root.getWidth() / 2.0D - paramMouseEvent.getX()) >= 100.0D)
      return; 
    if (Math.abs(paramMouseEvent.getX() - this.B) > 10.0D || Math.abs(paramMouseEvent.getY() - this.C) > 10.0D)
      return; 
    this.h.b();
    TranslateTransition translateTransition1 = new TranslateTransition(x, (Node)this.toolbarController.root);
    TranslateTransition translateTransition2 = new TranslateTransition(x, (Node)this.sidepaneController.root);
    TranslateTransition translateTransition3 = new TranslateTransition(x, (Node)this.b);
    if (this.y || this.z) {
      this.toolbarController.b();
      this.sidepaneController.b();
      translateTransition1.setToY(-this.toolbarController.root.getHeight() + 5.0D);
      translateTransition2.setToX(-this.sidepaneController.root.getWidth());
      translateTransition3.setToX(-this.b.getWidth() + 10.0D);
      this.y = this.z = false;
      this.root.requestFocus();
    } else {
      translateTransition1.setToY(0.0D);
      translateTransition2.setToX(0.0D);
      translateTransition3.setToX(0.0D);
      this.y = this.z = true;
      this.A = true;
    } 
    translateTransition1.play();
    translateTransition2.play();
    translateTransition3.play();
    paramMouseEvent.consume();
  }
  
  void a(w paramw) {
    if (this.h != null)
      this.h.d(); 
    this.h = paramw;
    this.h.c();
  }
  
  w c() {
    return this.h;
  }
  
  double d() {
    double d = this.c[0].getWidth();
    if (this.c[1] != null)
      d += this.c[1].getWidth(); 
    return (this.root.getWidth() - d) / 2.0D;
  }
  
  double e() {
    double d = this.c[0].getHeight();
    if (this.c[1] != null)
      d = Math.max(d, this.c[1].getHeight()); 
    return (this.root.getHeight() - d) / 2.0D;
  }
  
  private void a(Pane[] paramArrayOfPane) {
    if (paramArrayOfPane[0] == null && paramArrayOfPane[1] == null)
      return; 
    if (paramArrayOfPane[1] != null) {
      double d1 = paramArrayOfPane[1].getWidth() + paramArrayOfPane[0].getWidth();
      double d2 = Math.max(paramArrayOfPane[1].getHeight(), paramArrayOfPane[0].getHeight());
      double d3 = Math.max(0.0D, (this.stackPane.getWidth() - d1) / 2.0D);
      double d4 = Math.max(0.0D, (this.stackPane.getHeight() - d2) / 2.0D);
      if (this.g.l() == 2) {
        paramArrayOfPane[1].relocate(d3 + 0.5D, d4);
        paramArrayOfPane[0].relocate(d3 + paramArrayOfPane[1].getWidth() - 0.5D, d4);
      } else {
        paramArrayOfPane[0].relocate(d3 + 0.5D, d4);
        paramArrayOfPane[1].relocate(d3 + paramArrayOfPane[0].getWidth() - 0.5D, d4);
      } 
    } else {
      double d1 = paramArrayOfPane[0].getWidth();
      double d2 = paramArrayOfPane[0].getHeight();
      double d3 = Math.max(0.0D, (this.stackPane.getWidth() - d1) / 2.0D);
      double d4 = Math.max(0.0D, (this.stackPane.getHeight() - d2) / 2.0D);
      paramArrayOfPane[0].relocate(d3, d4);
      if (paramArrayOfPane[1] != null)
        paramArrayOfPane[1].relocate(d3, d4); 
    } 
  }
  
  z a(double paramDouble1, double paramDouble2) {
    z[] arrayOfZ = this.c;
    return (arrayOfZ[0] != null && (arrayOfZ[0]).d.contains(paramDouble1 - arrayOfZ[0].a(), paramDouble2 - arrayOfZ[0].b())) ? (((arrayOfZ[0]).a == null) ? null : arrayOfZ[0]) : ((arrayOfZ[1] != null && (arrayOfZ[1]).d.contains(paramDouble1 - arrayOfZ[1].a(), paramDouble2 - arrayOfZ[1].b())) ? (((arrayOfZ[1]).a == null) ? null : arrayOfZ[1]) : null);
  }
  
  private void a(z paramz, boolean paramBoolean, int paramInt, double paramDouble1, double paramDouble2) {
    Color color = (this.g.l_()).w ? Color.BLACK : b.r[(this.g.l_()).v];
    String str = "rgb(" + color.getRed() * 255.0D + "," + color.getGreen() * 255.0D + "," + color.getBlue() * 255.0D + ")";
    paramz.setStyle("-fx-background-color: " + str + ";");
    if (!paramBoolean) {
      Rectangle rectangle = new Rectangle(0.0D, 0.0D, paramDouble1, paramDouble2);
      if (paramInt == 2) {
        rectangle.setStyle("-fx-fill: linear-gradient(to right, rgba(0,0,0,0.2) 0%, rgba(0,0,0,0) 5%, rgba(0,0,0,0) 80%, rgba(0,0,0,0.1) 100%);");
      } else {
        rectangle.setStyle("-fx-fill: linear-gradient(to left, rgba(0,0,0,0.2) 0%, rgba(0,0,0,0) 5%, rgba(0,0,0,0) 80%, rgba(0,0,0,0.1) 100%);");
      } 
      rectangle.setMouseTransparent(true);
      paramz.getChildren().add(rectangle);
    } 
  }
  
  private z c(k paramk, int paramInt) {
    double d1;
    double d2;
    j j = paramk.e(paramInt);
    ImageView imageView = new ImageView(paramk.f(paramInt));
    imageView.setFitWidth(j.c());
    imageView.setFitHeight(j.d());
    int i = j.b();
    switch (i) {
      case 0:
      case 3:
        d1 = this.g.i;
        d2 = this.g.j;
        break;
      case 1:
      case 2:
        d1 = this.g.m_();
        d2 = this.g.i();
        break;
      default:
        throw new IllegalStateException();
    } 
    if ((this.g.l_()).n == 1 && paramk.t_())
      i = 0; 
    z z1 = new z(j, paramk, paramInt, d1, d2, (Node)imageView, i);
    a(z1, paramk.u_(), j.b(), d1, d2);
    return z1;
  }
  
  public void f() {
    this.D++;
    for (byte b1 = 0; b1 < this.c.length; b1++) {
      z z1 = this.c[b1];
      if (z1 != null && z1.a != null) {
        ObservableList observableList = z1.d.getChildren();
        Iterator<Node> iterator = observableList.iterator();
        while (iterator.hasNext()) {
          Node node = iterator.next();
          if (!(node instanceof v))
            continue; 
          iterator.remove();
        } 
        j j = z1.a;
        for (byte b2 = 0; b2 < j.j(); b2++) {
          i i = j.e(b2);
          MediaPlayer mediaPlayer = i.a((b)this.g, this.D);
          if (mediaPlayer != null) {
            Node node = i.e();
            if (node != null) {
              Rectangle2D rectangle2D = i.a.getBounds2D();
              node.setLayoutX(rectangle2D.getX());
              node.setLayoutY(rectangle2D.getY());
              z1.d.getChildren().add(node);
            } 
          } 
        } 
      } 
    } 
    s();
  }
  
  private void s() {
    i i = i.a(this.D);
    if (i != null)
      i.h.statusProperty().addListener(new ChangeListener<MediaPlayer.Status>(this) {
            public void a(ObservableValue<? extends MediaPlayer.Status> param1ObservableValue, MediaPlayer.Status param1Status1, MediaPlayer.Status param1Status2) {
              if (param1Status2 != MediaPlayer.Status.PLAYING) {
                param1ObservableValue.removeListener(this);
                this.a.s();
              } 
            }
          }); 
    this.audioStop.setVisible((i != null));
  }
  
  @FXML
  protected void audioStopAction(ActionEvent paramActionEvent) {
    i.b(this.D);
    this.audioStop.setVisible(false);
  }
  
  public void g() {
    this.l = null;
    this.m = null;
    for (byte b1 = 0; b1 < this.c.length; b1++) {
      z z1 = this.c[b1];
      if (z1 != null && z1.a != null) {
        ObservableList observableList = z1.d.getChildren();
        null = observableList.iterator();
        while (null.hasNext()) {
          Node node = null.next();
          if (node instanceof v)
            continue; 
          null.remove();
        } 
        for (s s : this.g.a(z1.b)) {
          Rectangle[] arrayOfRectangle;
          if (s.a()) {
            e e = s.a(z1.b, z1.c);
            if (e == null)
              continue; 
            Rectangle2D rectangle2D = e.a().getBounds2D();
            arrayOfRectangle = new Rectangle[] { new Rectangle(rectangle2D.getX() - 2.0D, rectangle2D.getY() - 2.0D, rectangle2D.getWidth() + 4.0D, rectangle2D.getHeight() + 4.0D) };
            for (Rectangle rectangle : arrayOfRectangle) {
              rectangle.setStrokeWidth(4.0D);
              rectangle.setStroke((Paint)Color.YELLOW);
              rectangle.setFill((Paint)Color.TRANSPARENT);
              rectangle.setBlendMode(BlendMode.MULTIPLY);
            } 
          } else {
            D d = s.b(z1.b, z1.c);
            if (d == null)
              continue; 
            arrayOfRectangle = a(z1, d, false);
            if (arrayOfRectangle == null)
              continue; 
            for (Rectangle rectangle : arrayOfRectangle) {
              rectangle.setFill((Paint)Color.YELLOW);
              rectangle.setBlendMode(BlendMode.MULTIPLY);
            } 
          } 
          EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>(this, s, z1, arrayOfRectangle) {
              public void a(MouseEvent param1MouseEvent) {
                if (this.d.l != null) {
                  ((Pane)this.d.l.getParent()).getChildren().remove(this.d.l);
                  if (this.d.m == param1MouseEvent.getTarget()) {
                    this.d.l = null;
                    this.d.m = null;
                    return;
                  } 
                } 
                param1MouseEvent.consume();
                this.d.l = (Pane)new VBox();
                this.d.m = param1MouseEvent.getTarget();
                TextArea textArea = new TextArea(this.a.d);
                textArea.setPrefWidth(280.0D);
                textArea.setPrefHeight(115.0D);
                this.d.l.getChildren().add(textArea);
                HBox hBox = new HBox();
                Button button1 = new Button(b.a.getString("update"));
                button1.setOnAction(new EventHandler<ActionEvent>(this, textArea) {
                      public void a(ActionEvent param2ActionEvent) {
                        this.b.a.d = this.a.getText();
                        FXCollections.replaceAll(this.b.d.g.o(), this.b.a, this.b.a);
                        this.b.b.getChildren().remove(this.b.d.l);
                        this.b.d.g();
                      }
                    });
                Button button2 = new Button(b.a.getString("delete"));
                button2.setOnAction(new EventHandler<ActionEvent>(this) {
                      public void a(ActionEvent param2ActionEvent) {
                        this.a.d.g.o().remove(this.a.a);
                        this.a.b.getChildren().remove(this.a.d.l);
                        this.a.d.g();
                      }
                    });
                hBox.getChildren().add(button1);
                hBox.getChildren().add(button2);
                this.d.l.getChildren().add(hBox);
                this.b.getChildren().add(this.d.l);
                Rectangle rectangle = this.c[this.c.length - 1];
                double d1 = rectangle.getX();
                double d2 = rectangle.getY() + rectangle.getHeight();
                if (d1 + 280.0D > this.b.getWidth())
                  d1 = this.b.getWidth() - 280.0D; 
                if (d2 + 145.0D > this.b.getHeight())
                  d2 = Math.max(48.0D, rectangle.getY() - 145.0D); 
                this.d.l.relocate(d1, d2);
              }
            };
          for (Rectangle rectangle : arrayOfRectangle) {
            rectangle.setOnMousePressed(eventHandler);
            rectangle.setCursor(c.d());
            observableList.add(rectangle);
          } 
        } 
      } 
    } 
  }
  
  private z a(boolean paramBoolean, int paramInt) {
    z z1 = new z();
    z1.setPrefSize(this.g.m_(), this.g.i());
    a(z1, paramBoolean, paramInt, this.g.m_(), this.g.i());
    return z1;
  }
  
  private void d(k paramk, int paramInt) throws Exception {
    int i = paramk.e(paramInt).b();
    if (i == 1 || i == 2) {
      if (i == this.g.q_()) {
        paramk.f(paramInt);
      } else {
        paramk.f(paramInt);
        if (paramInt + 1 < paramk.s_()) {
          if (paramk.e(++paramInt).b() != this.g.p_())
            paramk.f(paramInt); 
        } else {
          int j = paramk.r_() + 1;
          if (j < this.g.k() && this.g.a(j).e(0).b() == this.g.q_())
            this.g.a(paramk.r_() + 1).f(0); 
        } 
      } 
    } else {
      paramk.f(paramInt);
    } 
  }
  
  private void a(z[] paramArrayOfz, k paramk, int paramInt) throws Exception {
    ObservableList observableList = this.stackPane.getChildren();
    int i = paramk.e(paramInt).b();
    if (i == 1 || i == 2) {
      if (i == this.g.q_()) {
        paramArrayOfz[1] = c(paramk, paramInt);
        paramArrayOfz[0] = a(paramk.u_(), (this.g.l() == 1) ? 1 : 2);
      } else {
        paramArrayOfz[0] = c(paramk, paramInt);
        if (paramInt + 1 < paramk.s_()) {
          if (paramk.e(++paramInt).b() == this.g.p_()) {
            paramArrayOfz[1] = a(paramk.u_(), (this.g.l() == 1) ? 2 : 1);
          } else {
            paramArrayOfz[1] = c(paramk, paramInt);
          } 
        } else {
          int j = paramk.r_() + 1;
          if (j < this.g.k() && this.g.a(j).e(0).b() == this.g.q_()) {
            paramArrayOfz[1] = c(this.g.a(paramk.r_() + 1), 0);
          } else {
            paramArrayOfz[1] = a(paramk.u_(), (this.g.l() == 1) ? 2 : 1);
          } 
        } 
      } 
      observableList.add(0, paramArrayOfz[0]);
      observableList.add(0, paramArrayOfz[1]);
    } else {
      paramArrayOfz[0] = c(paramk, paramInt);
      paramArrayOfz[1] = null;
      observableList.add(0, paramArrayOfz[0]);
    } 
    this.stackPane.layout();
    a((Pane[])paramArrayOfz);
  }
  
  synchronized void h() {
    k k1 = this.g.k;
    if (k1 == null)
      return; 
    int i = this.g.l;
    try {
      if (k1.r_() < this.g.k() - 1) {
        k1 = this.g.a(k1.r_() + 1);
        i = 0;
      } else {
        i = k1.s_() - 1;
      } 
      if (!a(k1, i)) {
        c(false);
      } else {
        n();
      } 
    } catch (Exception exception) {
      a(exception);
    } 
  }
  
  synchronized void i() {
    k k1 = this.g.k;
    if (k1 == null)
      return; 
    int i = this.g.l;
    try {
      if (k1.r_() > 0) {
        k1 = this.g.a(k1.r_() - 1);
        i = 0;
      } else {
        i = 0;
      } 
      if (!a(k1, i)) {
        d(false);
      } else {
        n();
      } 
    } catch (Exception exception) {
      a(exception);
    } 
  }
  
  void j() throws Exception {
    this.a.execute(new Runnable(this) {
          public void run() {
            this.a.E = this.a.g.k;
            this.a.F = this.a.g.l;
            try {
              for (byte b = 0; b < 3 && this.a.w != null && this.a.a(this.a.E, this.a.F, (byte)3); b++);
            } catch (Exception exception) {
              exception.printStackTrace();
            } 
          }
        });
  }
  
  void k() throws Exception {
    this.a.execute(new Runnable(this) {
          public void run() {
            this.a.E = this.a.g.k;
            this.a.F = this.a.g.l;
            try {
              for (byte b = 0; b < 3 && this.a.w != null && this.a.b(this.a.E, this.a.F, (byte)3); b++);
            } catch (Exception exception) {
              exception.printStackTrace();
            } 
          }
        });
  }
  
  boolean c(boolean paramBoolean) throws Exception {
    return a(this.g.k, this.g.l, paramBoolean ? 2 : 1);
  }
  
  private boolean a(k paramk, int paramInt, byte paramByte) throws Exception {
    k k1 = paramk;
    int i = paramInt;
    for (byte b1 = 0; b1 < 2; b1++) {
      if (i + 1 < k1.s_()) {
        i++;
      } else {
        if (k1.r_() == -1 || k1.r_() == this.g.k() - 1)
          return false; 
        k1 = this.g.a(k1.r_() + 1);
        i = 0;
      } 
      int j = paramk.e(paramInt).b();
      if (j != this.g.p_())
        break; 
      int m = k1.e(i).b();
      if (m != this.g.q_())
        break; 
    } 
    switch (paramByte) {
      case 1:
        a(k1, i);
        n();
        break;
      case 2:
        a(this.d, this.e = k1, this.f = i);
        break;
      case 3:
        d(k1, i);
        this.E = k1;
        this.F = i;
        break;
    } 
    return true;
  }
  
  boolean d(boolean paramBoolean) throws Exception {
    return b(this.g.k, this.g.l, paramBoolean ? 2 : 1);
  }
  
  private boolean b(k paramk, int paramInt, byte paramByte) throws Exception {
    k k1 = paramk;
    int i = paramInt;
    for (byte b1 = 0; b1 < 2; b1++) {
      if (i >= 1) {
        i--;
      } else {
        if (k1.r_() <= 0 && i <= 0) {
          if (b1 == 1)
            break; 
          return false;
        } 
        k1 = this.g.a(k1.r_() - 1);
        i = k1.s_() - 1;
      } 
      if (b1 == 0 && k1.e(i).b() != this.g.q_())
        break; 
      if (b1 == 1 && k1.e(i).b() != this.g.p_()) {
        k1 = paramk;
        i = paramInt;
        if (i >= 1) {
          i--;
          break;
        } 
        k1 = this.g.a(k1.r_() - 1);
        i = k1.s_() - 1;
        break;
      } 
    } 
    switch (paramByte) {
      case 1:
        a(k1, i);
        n();
        break;
      case 2:
        a(this.d, this.e = k1, this.f = i);
        break;
      case 3:
        d(k1, i);
        this.E = k1;
        this.F = i;
        break;
    } 
    return true;
  }
  
  public synchronized void l() throws Exception {
    ObservableList observableList = this.stackPane.getChildren();
    observableList.remove(this.c[0]);
    if (this.c[1] != null)
      observableList.remove(this.c[1]); 
    z[] arrayOfZ = this.c;
    this.c = this.d;
    this.d = arrayOfZ;
    this.d[1] = null;
    this.d[0] = null;
    a(this.e, this.f);
    u();
    this.c[0].getTransforms().clear();
    g();
    f();
    j();
    k();
  }
  
  public synchronized void m() {
    this.c[0].setTranslateX(0.0D);
    this.c[0].setClip(null);
    this.c[0].toFront();
    if (this.c[1] != null) {
      this.c[1].setTranslateX(0.0D);
      this.c[1].setClip(null);
      this.c[1].toFront();
      if (this.g.l() == 2)
        this.c[0].toFront(); 
    } 
    ObservableList observableList = this.stackPane.getChildren();
    observableList.remove(this.d[0]);
    observableList.remove(this.d[1]);
    this.d[1] = null;
    this.d[0] = null;
  }
  
  private void t() {
    this.scroll.setMax((this.g.o_().intValue() - 1));
    u();
  }
  
  public void a(b paramb) {
    v();
    this.g = paramb;
    String str = paramb.B().i_();
    ReaderApplication.setTitle(str);
    this.toolbarController.root.setTranslateY(-64.0D);
    this.sidepaneController.root.setTranslateX(-330.0D);
    this.b.setTranslateX(-this.b.getWidth() + 10.0D);
    this.b.setTranslateY(this.stackPane.getHeight() - this.b.getHeight());
    this.a.execute(() -> {
          try {
            paramb.a((int)this.stackPane.getWidth(), (int)this.stackPane.getHeight());
            k k1 = paramb.k;
            paramb.k = null;
            a(k1, paramb.l);
            Platform.runLater(());
          } catch (Exception exception) {
            a(exception);
          } 
        });
  }
  
  boolean a(k paramk, int paramInt) throws Exception {
    j j = paramk.e(paramInt);
    if (j.b() == this.g.q_())
      if (paramInt == 0) {
        if (paramk.r_() > 0) {
          k k1 = this.g.a(paramk.r_() - 1);
          if (k1.e(k1.s_() - 1).b() == this.g.p_()) {
            paramk = k1;
            paramInt = k1.s_() - 1;
          } 
        } 
      } else {
        paramInt--;
      }  
    boolean bool = true;
    if (this.g.k == paramk && this.g.l == paramInt)
      bool = false; 
    this.g.k = paramk;
    this.g.l = paramInt;
    return bool;
  }
  
  private void u() {
    int i = this.g.a(this.g.k, this.g.l);
    if (this.g.l() == 2)
      i = (int)this.scroll.getMax() - i; 
    if ((int)this.scroll.getValue() != i)
      this.scroll.setValue(i); 
  }
  
  public byte a(c paramc, j paramj) {
    g g = paramc.b();
    switch (g.a()) {
      case 3:
        return b(paramc, paramj);
    } 
    return a(g);
  }
  
  public byte a(g paramg) {
    switch (paramg.a()) {
      case 1:
        return a(((g.c)paramg).b());
      case 2:
        return a(((g.a)paramg).b());
    } 
    throw new IllegalStateException();
  }
  
  public byte a(URI paramURI) {
    String str = paramURI.getScheme();
    if (str == null || "archive".equalsIgnoreCase(str))
      return b(paramURI); 
    try {
      Desktop.getDesktop().browse(paramURI);
    } catch (IOException iOException) {
      r.log(Level.FINE, "リンク切れ", iOException);
    } 
    return 2;
  }
  
  public byte a(h paramh) {
    v();
    this.a.execute(new Runnable(this, paramh) {
          public void run() {
            try {
              k k = this.b.g.a(this.a);
              if (k == null) {
                Platform.runLater(new Runnable(this) {
                      public void run() {
                        this.a.b.w();
                      }
                    });
                return;
              } 
              int i = k.b(this.a);
              this.b.a(k, i);
              Platform.runLater(new Runnable(this) {
                    public void run() {
                      try {
                        this.a.b.n();
                      } catch (Exception exception) {
                        this.a.b.a(exception);
                      } finally {
                        this.a.b.g.o_().addListener(this.a.b.J);
                        this.a.b.t();
                        this.a.b.w();
                      } 
                    }
                  });
            } catch (Exception exception) {
              this.b.a(exception);
            } 
          }
        });
    return 1;
  }
  
  private byte b(URI paramURI) {
    v();
    this.a.execute(new Runnable(this, paramURI) {
          public void run() {
            try {
              k k = this.b.g.a(this.a);
              if (k == null) {
                Platform.runLater(new Runnable(this) {
                      public void run() {
                        this.a.b.w();
                      }
                    });
                return;
              } 
              String str = this.a.getFragment();
              boolean bool = (str == null) ? false : k.a(str);
              this.b.a(k, bool);
              Platform.runLater(new Runnable(this) {
                    public void run() {
                      try {
                        this.a.b.n();
                      } catch (Exception exception) {
                        this.a.b.a(exception);
                      } finally {
                        this.a.b.g.o_().addListener(this.a.b.J);
                        this.a.b.t();
                        this.a.b.w();
                      } 
                    }
                  });
            } catch (Exception exception) {
              this.b.a(exception);
            } 
          }
        });
    return 1;
  }
  
  private byte b(c paramc, j paramj) {
    URI uRI = ((g.b)paramc.b()).b();
    String str = uRI.getScheme();
    if (str != null && !"archive".equalsIgnoreCase(str))
      return 0; 
    try {
      h h = (h)this.g.a(uRI);
      String str1 = uRI.getFragment();
      boolean bool = (str1 == null) ? false : h.a(str1);
      g g = (g)h.e(bool);
      for (byte b1 = 0; b1 < g.l(); b1++) {
        c.a a1 = g.f(b1);
        if (a1.a.equalsIgnoreCase(str1) && a1.b instanceof c) {
          double d1 = ReaderApplication.getRenderScale();
          c c1 = (c)a1.b;
          ArrayList<g> arrayList = new ArrayList();
          n n = new n((b)this.g, arrayList, 1, false, false);
          URI uRI1 = d.a("UTF-8", "archive:///" + (h.a()).d);
          n.c().a(uRI1);
          n.a().a((h)c.a());
          i i = new i();
          double d2 = 300.0D;
          double d3 = c1.p();
          double d4 = c1.q();
          d4 = Math.min(d4, f.a((m)n, d2 - 30.0D, (short)17, (short)21));
          i.D = (c1.c_()).D;
          if (i.D == 1) {
            i.X = m.a(d3, 0.0D, (byte)1, (byte)3);
          } else {
            i.X = m.a(0.0D, d4, (byte)3, (byte)1);
          } 
          n n1 = new n(i, (m)n);
          a a2 = new a(null, (c)n1);
          c1.a(a2, 0);
          a2.x();
          g g1 = new g(0);
          b b2 = n.s();
          a a3 = n.b(b2);
          n1.a(g1, a3);
          g1.a(b2);
          n.a(b2);
          n.t();
          g g2 = arrayList.get(0);
          double d5 = Math.ceil((f.a((m)n, n1.p(), (short)21, (short)17) + 60.0D) * d1);
          double d6 = Math.ceil((f.a((m)n, n1.q(), (short)21, (short)17) + 30.0D) * d1);
          BufferedImage bufferedImage = new BufferedImage((int)d5, (int)d6, 2);
          Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
          RenderingHints renderingHints = graphics2D.getRenderingHints();
          c.a(renderingHints);
          graphics2D.setRenderingHints(renderingHints);
          f f = new f(graphics2D, g2.a());
          AffineTransform affineTransform = AffineTransform.getScaleInstance(d1, d1);
          affineTransform.translate(15.0D, 15.0D);
          f.a(affineTransform);
          g2.a((b)f);
          WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
          Pane pane = new Pane();
          pane.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-width: 2; -fx-border-style: solid;");
          ImageView imageView = new ImageView((Image)writableImage);
          imageView.setFitWidth(d5 /= d1);
          imageView.setFitHeight(d6 /= d1);
          if (d6 > d2) {
            ScrollPane scrollPane = new ScrollPane((Node)imageView);
            d6 = d2;
            scrollPane.setPrefHeight(d6);
            pane.getChildren().add(scrollPane);
          } else {
            pane.getChildren().add(imageView);
          } 
          z z1 = this.c[0];
          if (this.c[1] != null && (this.c[1]).a == paramj)
            z1 = this.c[1]; 
          double d7 = paramc.a().getBounds2D().getMaxY();
          d7 = Math.min(d7, z1.getHeight() - d6 - 20.0D);
          pane.relocate((z1.getWidth() - d5) / 2.0D, d7);
          z1.d.getChildren().add(pane);
          a(new q(this, z1, g2, pane));
        } 
      } 
    } catch (Exception exception) {
      a(exception);
    } 
    return 3;
  }
  
  protected void a(e parame, j paramj, int paramInt, double paramDouble) {
    try {
      FXMLLoader fXMLLoader = c.a(ImageController.class.getResource("image.fxml"));
      Node node = (Node)fXMLLoader.load();
      this.root.getChildren().add(node);
      this.root.layout();
      ImageController imageController = (ImageController)fXMLLoader.getController();
      BufferedImage bufferedImage = parame.a(paramInt);
      imageController.a(bufferedImage, paramj, paramInt, paramDouble);
      imageController.a(this);
    } catch (IOException iOException) {
      r.log(Level.SEVERE, "画像表示モードに切替えられませんでした。", iOException);
      throw new RuntimeException(iOException);
    } 
  }
  
  protected void a(double paramDouble) {
    try {
      ImageController imageController;
      FXMLLoader fXMLLoader = c.a(ImageController.class.getResource("image.fxml"));
      Node node = (Node)fXMLLoader.load();
      this.root.getChildren().add(node);
      this.root.layout();
      e e = (this.c[0]).a.b(0);
      BufferedImage bufferedImage = e.a(200);
      if (this.c[1] == null || (this.c[1]).a == null) {
        imageController = (ImageController)fXMLLoader.getController();
        imageController.a(bufferedImage, null, 0, paramDouble);
      } else {
        e e1 = (this.c[1]).a.b(0);
        BufferedImage bufferedImage1 = e1.a(200);
        BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth() + bufferedImage1.getWidth(), Math.max(bufferedImage.getHeight(), bufferedImage1.getHeight()), 2);
        if (this.g.l() == 1) {
          bufferedImage2.getGraphics().drawImage(bufferedImage, 0, 0, null);
          bufferedImage2.getGraphics().drawImage(bufferedImage1, bufferedImage.getWidth(), 0, null);
        } else {
          bufferedImage2.getGraphics().drawImage(bufferedImage1, 0, 0, null);
          bufferedImage2.getGraphics().drawImage(bufferedImage, bufferedImage1.getWidth(), 0, null);
        } 
        imageController = (ImageController)fXMLLoader.getController();
        imageController.a(bufferedImage2, null, 0, paramDouble);
      } 
      imageController.a(this);
    } catch (IOException iOException) {
      r.log(Level.SEVERE, "画像表示モードに切替えられませんでした。", iOException);
      throw new RuntimeException(iOException);
    } 
  }
  
  public synchronized void n() throws Exception {
    u();
    this.stackPane.getChildren().clear();
    a(this.c, this.g.k, this.g.l);
    g();
    f();
    j();
    k();
  }
  
  public z b(k paramk, int paramInt) {
    return ((this.c[0]).b == paramk && (this.c[0]).c == paramInt) ? this.c[0] : ((this.c[1] == null) ? null : (((this.c[1]).b == paramk && (this.c[1]).c == paramInt) ? this.c[1] : null));
  }
  
  public void a(D paramD) {
    for (Rectangle rectangle : this.k) {
      if (!(this.c[0]).d.getChildren().remove(rectangle) && this.c[1] != null)
        (this.c[1]).d.getChildren().remove(rectangle); 
    } 
    this.k.clear();
    this.i = paramD;
    if (paramD == null)
      return; 
    z z1 = b(paramD.a, paramD.b);
    if (z1 == null)
      return; 
    Rectangle[] arrayOfRectangle = a(z1, paramD, false);
    if (arrayOfRectangle == null)
      return; 
    ObservableList observableList = z1.d.getChildren();
    for (Rectangle rectangle : arrayOfRectangle) {
      rectangle.setFill((Paint)Color.WHITE);
      rectangle.setBlendMode(BlendMode.DIFFERENCE);
      observableList.add(rectangle);
      this.k.add(rectangle);
    } 
  }
  
  public void o() {
    if (this.j.isEmpty())
      return; 
    for (Rectangle rectangle : this.j) {
      if (!(this.c[0]).d.getChildren().remove(rectangle) && this.c[1] != null)
        (this.c[1]).d.getChildren().remove(rectangle); 
    } 
    this.j.clear();
  }
  
  public void a(D paramD, boolean paramBoolean) {
    z z1 = b(paramD.a, paramD.b);
    if (z1 == null)
      return; 
    Rectangle[] arrayOfRectangle = a(z1, paramD, paramBoolean);
    if (arrayOfRectangle == null)
      return; 
    ObservableList observableList = z1.d.getChildren();
    for (Rectangle rectangle : arrayOfRectangle) {
      rectangle.setFill((Paint)Color.LIGHTGREEN);
      rectangle.setBlendMode(BlendMode.MULTIPLY);
      observableList.add(rectangle);
      this.j.add(rectangle);
    } 
  }
  
  Rectangle[] a(z paramz, D paramD, boolean paramBoolean) {
    paramD = paramD.b();
    if (paramD.c == -1)
      return null; 
    ArrayList<Rectangle> arrayList = new ArrayList();
    for (int i = paramD.c; i < paramD.e; i++) {
      l l = paramBoolean ? paramz.a.d(i) : paramz.a.c(i);
      boolean bool = (l.a() == 3) ? true : false;
      Rectangle2D rectangle2D = l.a.getBounds2D();
      double d1 = rectangle2D.getX();
      double d2 = rectangle2D.getY();
      double d3 = rectangle2D.getWidth();
      double d4 = rectangle2D.getHeight();
      double d5 = l.b() + 2.0D;
      if (bool) {
        d1 += (d3 - d5) / 2.0D;
        d3 = d5;
      } else {
        d2 += (d4 - d5) / 2.0D;
        d4 = d5;
      } 
      if (i == paramD.c) {
        double[] arrayOfDouble = l.c();
        for (byte b1 = 0; b1 < paramD.d; b1++) {
          double d = arrayOfDouble[b1];
          if (bool) {
            d2 += d;
            d4 -= d;
          } else {
            d1 += d;
            d3 -= d;
          } 
        } 
      } 
      if (i == paramD.e - 1) {
        double[] arrayOfDouble = l.c();
        for (int j = paramD.f; j < arrayOfDouble.length; j++) {
          double d = arrayOfDouble[j];
          if (bool) {
            d4 -= d;
          } else {
            d3 -= d;
          } 
        } 
      } 
      arrayList.add(new Rectangle(d1, d2, d3, d4));
    } 
    return arrayList.<Rectangle>toArray(new Rectangle[arrayList.size()]);
  }
  
  private void v() {
    this.root.setDisable(true);
    this.disablePane.setVisible(true);
    this.root.getScene().getRoot().setCursor(Cursor.WAIT);
  }
  
  private void w() {
    this.root.setDisable(false);
    this.disablePane.setVisible(false);
    this.root.getScene().getRoot().setCursor(null);
  }
  
  void p() throws Exception {
    h h = ((this.c[0]).a == null) ? (this.c[1]).a.e() : (this.c[0]).a.e();
    this.g.b((int)this.stackPane.getWidth(), (int)this.stackPane.getHeight());
    this.g.r();
    k k1 = this.g.a(h);
    int i = k1.b(h);
    this.g.k = null;
    a(k1, i);
    Platform.runLater(new Runnable(this) {
          public void run() {
            try {
              this.a.toolbarController.c();
              this.a.n();
            } catch (Exception exception) {
              this.a.a(exception);
            } 
          }
        });
  }
  
  void q() {
    v();
    this.a.execute(new Runnable(this) {
          public void run() {
            try {
              this.a.p();
            } catch (Exception exception) {
              this.a.a(exception);
            } finally {
              Platform.runLater(new Runnable(this) {
                    public void run() {
                      this.a.a.w();
                    }
                  });
            } 
          }
        });
  }
  
  public void a(Exception paramException) {
    Platform.runLater(new Runnable(this, paramException) {
          public void run() {
            this.b.root.getScene().getRoot().setCursor(null);
            BookController.r.log(Level.WARNING, "本の表示中にエラーが発生しました。", this.a);
            AlertController.a(b.a.getString("error"), this.a.getMessage(), false);
            ReaderApplication.getInstance().j().a();
          }
        });
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/BookController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */