package net.zamasoft.reader.book;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.c;

public class ImageController implements Initializable {
  private static final double a = 10.0D;
  
  private static final double b = 100.0D;
  
  @FXML
  private AnchorPane root;
  
  @FXML
  private ScrollPane scrollPane;
  
  @FXML
  private AnchorPane anchor;
  
  @FXML
  private ImageView imageView;
  
  @FXML
  private Button zoomOutButton;
  
  @FXML
  private Button zoomInButton;
  
  private BufferedImage c;
  
  private j d;
  
  private int e;
  
  private BookController f;
  
  private double g = 1.0D;
  
  private double h = 1.0D;
  
  private double i = 1.0D;
  
  private double j = -1.0D;
  
  private double k = -1.0D;
  
  private EventHandler<? super KeyEvent> l;
  
  private EventHandler<? super KeyEvent> m;
  
  private boolean n = false;
  
  private double o;
  
  private double p;
  
  private double q;
  
  private double r;
  
  private double s;
  
  private double t;
  
  private byte u;
  
  private static final byte v = 1;
  
  private static final byte w = 2;
  
  private static final byte x = 3;
  
  private static final byte y = 4;
  
  private boolean z;
  
  private Cursor A = null;
  
  private double B;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    this.scrollPane.widthProperty().addListener(new ChangeListener<Number>(this) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            this.a.b();
          }
        });
    this.scrollPane.heightProperty().addListener(new ChangeListener<Number>(this) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            this.a.b();
          }
        });
    this.scrollPane.hvalueProperty().addListener(new ChangeListener<Number>(this) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            if (this.a.j != -1.0D) {
              this.a.scrollPane.setHvalue(this.a.j);
              this.a.j = -1.0D;
            } 
          }
        });
    this.scrollPane.vvalueProperty().addListener(new ChangeListener<Number>(this) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            if (this.a.k != -1.0D) {
              this.a.scrollPane.setVvalue(this.a.k);
              this.a.k = -1.0D;
            } 
          }
        });
    this.anchor.setCursor(c.b());
    Scene scene = ReaderApplication.getInstance().k().getScene();
    this.m = scene.getOnKeyPressed();
    this.l = scene.getOnKeyReleased();
    scene.setOnKeyPressed(new EventHandler<KeyEvent>(this) {
          public void a(KeyEvent param1KeyEvent) {
            if (param1KeyEvent.getCode() == KeyCode.ESCAPE) {
              this.a.a();
              param1KeyEvent.consume();
              return;
            } 
            if (param1KeyEvent.isControlDown()) {
              this.a.anchor.setCursor(c.c());
              return;
            } 
            this.a.anchor.setCursor(c.b());
            switch (ImageController.null.a[param1KeyEvent.getCode().ordinal()]) {
              case 1:
                this.a.a((byte)4);
                break;
              case 2:
                this.a.a((byte)3);
                break;
              case 3:
                this.a.a((byte)1);
                break;
              case 4:
                this.a.a((byte)2);
                break;
              case 5:
              case 6:
                Event.fireEvent((EventTarget)this.a.scrollPane, (Event)param1KeyEvent);
                break;
              case 7:
                if (param1KeyEvent.isShiftDown()) {
                  Event.fireEvent((EventTarget)this.a.scrollPane, (Event)new KeyEvent(param1KeyEvent.getEventType(), "", "", KeyCode.UP, false, false, false, false));
                  break;
                } 
                Event.fireEvent((EventTarget)this.a.scrollPane, (Event)param1KeyEvent);
                break;
            } 
            param1KeyEvent.consume();
          }
        });
    scene.setOnKeyReleased(new EventHandler<KeyEvent>(this) {
          public void a(KeyEvent param1KeyEvent) {
            this.a.anchor.setCursor(c.b());
            param1KeyEvent.consume();
          }
        });
    this.scrollPane.addEventFilter(ScrollEvent.SCROLL_STARTED, paramScrollEvent -> {
          if (this.A == null) {
            this.A = this.anchor.getCursor();
            this.anchor.setCursor(c.e());
          } 
          this.n = true;
        });
    this.scrollPane.addEventFilter(ScrollEvent.SCROLL, paramScrollEvent -> {
          if (!this.n)
            return; 
          double d1 = paramScrollEvent.getTotalDeltaX();
          double d2 = this.imageView.getFitWidth();
          double d3 = this.scrollPane.getWidth();
          if (d2 <= d3) {
            double d7 = d3 - d2;
            double d8 = this.s + d1;
            if (d8 < -100.0D) {
              d8 = -100.0D;
              this.u = 2;
            } else if (d8 > d7 + 100.0D) {
              d8 = d7 + 100.0D;
              this.u = 1;
            } 
            this.anchor.setTranslateX(d8);
          } else if (this.scrollPane.getHvalue() <= 0.0D && d1 > 0.0D) {
            if (d1 > 100.0D) {
              d1 = 100.0D;
              this.u = 1;
            } 
            this.anchor.setTranslateX(d1);
          } else if (this.scrollPane.getHvalue() >= 1.0D && d1 < 0.0D) {
            if (d1 < -100.0D) {
              d1 = -100.0D;
              this.u = 2;
            } 
            this.anchor.setTranslateX(d1);
          } 
          double d4 = paramScrollEvent.getTotalDeltaY();
          double d5 = this.imageView.getFitHeight();
          double d6 = this.scrollPane.getHeight();
          if (d5 <= d6) {
            double d7 = d6 - d5;
            double d8 = this.t + d4;
            if (d8 < -100.0D) {
              d8 = -100.0D;
            } else if (d8 > d7 + 100.0D) {
              d8 = d7 + 100.0D;
            } 
            this.anchor.setTranslateY(d8);
          } else if (this.scrollPane.getVvalue() <= 0.0D && d4 > 0.0D) {
            if (d4 > 100.0D)
              d4 = 100.0D; 
            this.anchor.setTranslateY(d4);
          } else if (this.scrollPane.getVvalue() >= 1.0D && d4 < 0.0D) {
            if (d4 < -100.0D)
              d4 = -100.0D; 
            this.anchor.setTranslateY(d4);
          } 
        });
    this.scrollPane.addEventFilter(ScrollEvent.SCROLL_FINISHED, paramScrollEvent -> {
          if (this.A != null) {
            this.anchor.setCursor(this.A);
            this.A = null;
          } 
          this.n = false;
          c();
        });
  }
  
  private void a() {
    Scene scene = ReaderApplication.getInstance().k().getScene();
    scene.setOnKeyPressed(this.m);
    scene.setOnKeyReleased(this.l);
    Pane pane = (Pane)this.root.getParent();
    pane.getChildren().remove(this.root);
    pane.requestFocus();
  }
  
  private void b() {
    double d1 = this.imageView.getBoundsInLocal().getWidth();
    double d2 = this.imageView.getBoundsInLocal().getHeight();
    double d3 = Math.max(0.0D, (this.scrollPane.getWidth() - d1) / 2.0D);
    double d4 = Math.max(0.0D, (this.scrollPane.getHeight() - d2) / 2.0D);
    this.anchor.setTranslateX(d3);
    this.anchor.setTranslateY(d4);
    this.s = d3;
    this.t = d4;
  }
  
  public void a(BufferedImage paramBufferedImage, j paramj, int paramInt, double paramDouble) {
    this.c = paramBufferedImage;
    this.d = paramj;
    this.e = paramInt;
    if (paramBufferedImage.getWidth() > this.scrollPane.getWidth())
      this.g = this.scrollPane.getWidth() / paramBufferedImage.getWidth(); 
    if (paramBufferedImage.getHeight() * this.g > this.scrollPane.getHeight())
      this.g = this.scrollPane.getHeight() / paramBufferedImage.getHeight(); 
    if (paramDouble != 0.0D && paramDouble < this.g)
      paramDouble = this.g; 
    this.i = this.g * 0.9D;
    this.g = this.scrollPane.getWidth() / paramBufferedImage.getWidth();
    if (paramBufferedImage.getHeight() * this.g > this.scrollPane.getHeight())
      this.g = this.scrollPane.getHeight() / paramBufferedImage.getHeight(); 
    if (paramDouble != 0.0D)
      this.g = paramDouble; 
    d();
    b();
  }
  
  public void a(BookController paramBookController) {
    if (paramBookController == null)
      throw new NullPointerException(); 
    this.f = paramBookController;
    if (this.f.g.l() == 1) {
      this.scrollPane.setHvalue(0.0D);
    } else {
      this.scrollPane.setHvalue(1.0D);
    } 
    this.root.requestFocus();
  }
  
  @FXML
  private void mouseMoved(MouseEvent paramMouseEvent) {
    paramMouseEvent.consume();
  }
  
  @FXML
  private void mousePressed(MouseEvent paramMouseEvent) {
    this.o = paramMouseEvent.getSceneX();
    this.p = paramMouseEvent.getSceneY();
    this.q = this.scrollPane.getHvalue();
    this.r = this.scrollPane.getVvalue();
    this.u = 0;
    this.z = paramMouseEvent.isSecondaryButtonDown();
    paramMouseEvent.consume();
  }
  
  @FXML
  private void mouseDragged(MouseEvent paramMouseEvent) {
    this.u = 0;
    if (this.A == null) {
      this.A = this.anchor.getCursor();
      this.anchor.setCursor(c.e());
    } 
    double d1 = this.imageView.getFitWidth();
    double d2 = this.scrollPane.getWidth();
    if (d1 <= d2) {
      double d5 = d2 - d1;
      double d6 = this.s + paramMouseEvent.getSceneX() - this.o;
      if (d6 < -100.0D) {
        d6 = -100.0D;
        this.u = 2;
      } else if (d6 > d5 + 100.0D) {
        d6 = d5 + 100.0D;
        this.u = 1;
      } 
      this.anchor.setTranslateX(d6);
    } else {
      double d = this.q;
      d += (this.o - paramMouseEvent.getSceneX()) / (d1 - d2);
      if (d < 0.0D) {
        double d5 = d * (d1 - d2);
        if (d5 >= -100.0D) {
          this.anchor.setTranslateX(-d5);
        } else {
          this.u = 1;
        } 
        d = 0.0D;
      } else if (d > 1.0D) {
        double d5 = d * (d1 - d2) - d1 - d2;
        if (d5 <= 100.0D) {
          this.anchor.setTranslateX(-d5);
        } else {
          this.u = 2;
        } 
        d = 1.0D;
      } 
      this.scrollPane.setHvalue(d);
    } 
    double d3 = this.imageView.getFitHeight();
    double d4 = this.scrollPane.getHeight();
    if (d3 <= d4) {
      double d5 = d4 - d3;
      double d6 = this.t + paramMouseEvent.getSceneY() - this.p;
      if (d6 < -100.0D) {
        d6 = -100.0D;
      } else if (d6 > d5 + 100.0D) {
        d6 = d5 + 100.0D;
      } 
      this.anchor.setTranslateY(d6);
    } else {
      double d = this.r;
      d += (this.p - paramMouseEvent.getSceneY()) / (d3 - d4);
      if (d < 0.0D) {
        double d5 = d * (d3 - d4);
        if (d5 >= -100.0D)
          this.anchor.setTranslateY(-d5); 
        d = 0.0D;
      } else if (d > 1.0D) {
        double d5 = d * (d3 - d4) - d3 - d4;
        if (d5 <= 100.0D)
          this.anchor.setTranslateY(-d5); 
        d = 1.0D;
      } 
      this.scrollPane.setVvalue(d);
    } 
    paramMouseEvent.consume();
  }
  
  @FXML
  private void mouseReleased(MouseEvent paramMouseEvent) {
    if (this.A != null) {
      this.anchor.setCursor(this.A);
      this.A = null;
    } 
    if (Math.abs(paramMouseEvent.getSceneX() - this.o) > 10.0D || Math.abs(paramMouseEvent.getSceneY() - this.p) > 10.0D) {
      c();
      return;
    } 
    a(paramMouseEvent.isControlDown() ^ this.z, paramMouseEvent.getX(), paramMouseEvent.getY());
    paramMouseEvent.consume();
  }
  
  private void c() {
    if (this.u != 0)
      a(this.u); 
    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200.0D), (Node)this.anchor);
    translateTransition.setToX(this.s);
    translateTransition.setToY(this.t);
    translateTransition.play();
  }
  
  private void a(boolean paramBoolean, double paramDouble1, double paramDouble2) {
    double d;
    if (paramBoolean) {
      d = 0.75D;
    } else if (this.g == 10.0D) {
      d = this.i / this.g;
    } else {
      d = 1.25D;
    } 
    a(d, paramDouble1, paramDouble2);
  }
  
  private void a(byte paramByte) {
    // Byte code:
    //   0: aload_0
    //   1: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   4: getfield g : Lnet/zamasoft/reader/book/b;
    //   7: invokevirtual s : ()Z
    //   10: ifeq -> 31
    //   13: aload_0
    //   14: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   17: getfield g : Lnet/zamasoft/reader/book/b;
    //   20: getfield k : Lnet/zamasoft/reader/book/k;
    //   23: invokeinterface u_ : ()Z
    //   28: ifeq -> 35
    //   31: iconst_1
    //   32: goto -> 36
    //   35: iconst_0
    //   36: istore_2
    //   37: iload_1
    //   38: iconst_4
    //   39: if_icmpeq -> 75
    //   42: iload_1
    //   43: iconst_1
    //   44: if_icmpne -> 51
    //   47: iconst_1
    //   48: goto -> 52
    //   51: iconst_0
    //   52: aload_0
    //   53: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   56: getfield g : Lnet/zamasoft/reader/book/b;
    //   59: invokevirtual l : ()I
    //   62: iconst_2
    //   63: if_icmpne -> 70
    //   66: iconst_1
    //   67: goto -> 71
    //   70: iconst_0
    //   71: ixor
    //   72: ifeq -> 433
    //   75: aload_0
    //   76: getfield e : I
    //   79: iconst_1
    //   80: if_icmplt -> 126
    //   83: aload_0
    //   84: invokevirtual a : ()V
    //   87: aload_0
    //   88: getfield d : Lnet/zamasoft/reader/book/j;
    //   91: aload_0
    //   92: getfield e : I
    //   95: iconst_1
    //   96: isub
    //   97: invokeinterface b : (I)Lnet/zamasoft/reader/book/e;
    //   102: astore_3
    //   103: aload_0
    //   104: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   107: aload_3
    //   108: aload_0
    //   109: getfield d : Lnet/zamasoft/reader/book/j;
    //   112: aload_0
    //   113: getfield e : I
    //   116: iconst_1
    //   117: isub
    //   118: aload_0
    //   119: getfield g : D
    //   122: invokevirtual a : (Lnet/zamasoft/reader/book/e;Lnet/zamasoft/reader/book/j;ID)V
    //   125: return
    //   126: aload_0
    //   127: getfield d : Lnet/zamasoft/reader/book/j;
    //   130: ifnull -> 271
    //   133: aload_0
    //   134: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   137: getfield c : [Lnet/zamasoft/reader/book/z;
    //   140: iconst_1
    //   141: aaload
    //   142: ifnull -> 271
    //   145: aload_0
    //   146: getfield d : Lnet/zamasoft/reader/book/j;
    //   149: aload_0
    //   150: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   153: getfield c : [Lnet/zamasoft/reader/book/z;
    //   156: iconst_1
    //   157: aaload
    //   158: getfield a : Lnet/zamasoft/reader/book/j;
    //   161: if_acmpne -> 271
    //   164: aload_0
    //   165: invokevirtual a : ()V
    //   168: aload_0
    //   169: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   172: getfield c : [Lnet/zamasoft/reader/book/z;
    //   175: iconst_0
    //   176: aaload
    //   177: getfield a : Lnet/zamasoft/reader/book/j;
    //   180: ifnull -> 270
    //   183: aload_0
    //   184: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   187: getfield c : [Lnet/zamasoft/reader/book/z;
    //   190: iconst_0
    //   191: aaload
    //   192: getfield a : Lnet/zamasoft/reader/book/j;
    //   195: invokeinterface f : ()I
    //   200: iconst_1
    //   201: if_icmplt -> 270
    //   204: aload_0
    //   205: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   208: getfield c : [Lnet/zamasoft/reader/book/z;
    //   211: iconst_0
    //   212: aaload
    //   213: getfield a : Lnet/zamasoft/reader/book/j;
    //   216: invokeinterface f : ()I
    //   221: iconst_1
    //   222: isub
    //   223: istore_3
    //   224: aload_0
    //   225: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   228: getfield c : [Lnet/zamasoft/reader/book/z;
    //   231: iconst_0
    //   232: aaload
    //   233: getfield a : Lnet/zamasoft/reader/book/j;
    //   236: iload_3
    //   237: invokeinterface b : (I)Lnet/zamasoft/reader/book/e;
    //   242: astore #4
    //   244: aload_0
    //   245: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   248: aload #4
    //   250: aload_0
    //   251: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   254: getfield c : [Lnet/zamasoft/reader/book/z;
    //   257: iconst_0
    //   258: aaload
    //   259: getfield a : Lnet/zamasoft/reader/book/j;
    //   262: iload_3
    //   263: aload_0
    //   264: getfield g : D
    //   267: invokevirtual a : (Lnet/zamasoft/reader/book/e;Lnet/zamasoft/reader/book/j;ID)V
    //   270: return
    //   271: iload_2
    //   272: ifeq -> 745
    //   275: aload_0
    //   276: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   279: iconst_0
    //   280: invokevirtual d : (Z)Z
    //   283: ifeq -> 745
    //   286: aload_0
    //   287: invokevirtual a : ()V
    //   290: aload_0
    //   291: getfield d : Lnet/zamasoft/reader/book/j;
    //   294: ifnull -> 421
    //   297: iconst_1
    //   298: istore_3
    //   299: aload_0
    //   300: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   303: getfield c : [Lnet/zamasoft/reader/book/z;
    //   306: iload_3
    //   307: aaload
    //   308: ifnonnull -> 313
    //   311: iconst_0
    //   312: istore_3
    //   313: aload_0
    //   314: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   317: getfield c : [Lnet/zamasoft/reader/book/z;
    //   320: iload_3
    //   321: aaload
    //   322: getfield a : Lnet/zamasoft/reader/book/j;
    //   325: ifnull -> 418
    //   328: aload_0
    //   329: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   332: getfield c : [Lnet/zamasoft/reader/book/z;
    //   335: iload_3
    //   336: aaload
    //   337: getfield a : Lnet/zamasoft/reader/book/j;
    //   340: invokeinterface f : ()I
    //   345: iconst_1
    //   346: if_icmplt -> 418
    //   349: aload_0
    //   350: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   353: getfield c : [Lnet/zamasoft/reader/book/z;
    //   356: iload_3
    //   357: aaload
    //   358: getfield a : Lnet/zamasoft/reader/book/j;
    //   361: invokeinterface f : ()I
    //   366: iconst_1
    //   367: isub
    //   368: istore #4
    //   370: aload_0
    //   371: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   374: getfield c : [Lnet/zamasoft/reader/book/z;
    //   377: iload_3
    //   378: aaload
    //   379: getfield a : Lnet/zamasoft/reader/book/j;
    //   382: iload #4
    //   384: invokeinterface b : (I)Lnet/zamasoft/reader/book/e;
    //   389: astore #5
    //   391: aload_0
    //   392: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   395: aload #5
    //   397: aload_0
    //   398: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   401: getfield c : [Lnet/zamasoft/reader/book/z;
    //   404: iload_3
    //   405: aaload
    //   406: getfield a : Lnet/zamasoft/reader/book/j;
    //   409: iload #4
    //   411: aload_0
    //   412: getfield g : D
    //   415: invokevirtual a : (Lnet/zamasoft/reader/book/e;Lnet/zamasoft/reader/book/j;ID)V
    //   418: goto -> 432
    //   421: aload_0
    //   422: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   425: aload_0
    //   426: getfield g : D
    //   429: invokevirtual a : (D)V
    //   432: return
    //   433: aload_0
    //   434: getfield d : Lnet/zamasoft/reader/book/j;
    //   437: ifnull -> 501
    //   440: aload_0
    //   441: getfield e : I
    //   444: aload_0
    //   445: getfield d : Lnet/zamasoft/reader/book/j;
    //   448: invokeinterface f : ()I
    //   453: iconst_1
    //   454: isub
    //   455: if_icmpge -> 501
    //   458: aload_0
    //   459: invokevirtual a : ()V
    //   462: aload_0
    //   463: getfield d : Lnet/zamasoft/reader/book/j;
    //   466: aload_0
    //   467: getfield e : I
    //   470: iconst_1
    //   471: iadd
    //   472: invokeinterface b : (I)Lnet/zamasoft/reader/book/e;
    //   477: astore_3
    //   478: aload_0
    //   479: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   482: aload_3
    //   483: aload_0
    //   484: getfield d : Lnet/zamasoft/reader/book/j;
    //   487: aload_0
    //   488: getfield e : I
    //   491: iconst_1
    //   492: iadd
    //   493: aload_0
    //   494: getfield g : D
    //   497: invokevirtual a : (Lnet/zamasoft/reader/book/e;Lnet/zamasoft/reader/book/j;ID)V
    //   500: return
    //   501: aload_0
    //   502: getfield d : Lnet/zamasoft/reader/book/j;
    //   505: ifnull -> 624
    //   508: aload_0
    //   509: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   512: getfield c : [Lnet/zamasoft/reader/book/z;
    //   515: iconst_1
    //   516: aaload
    //   517: ifnull -> 624
    //   520: aload_0
    //   521: getfield d : Lnet/zamasoft/reader/book/j;
    //   524: aload_0
    //   525: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   528: getfield c : [Lnet/zamasoft/reader/book/z;
    //   531: iconst_0
    //   532: aaload
    //   533: getfield a : Lnet/zamasoft/reader/book/j;
    //   536: if_acmpne -> 624
    //   539: aload_0
    //   540: invokevirtual a : ()V
    //   543: aload_0
    //   544: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   547: getfield c : [Lnet/zamasoft/reader/book/z;
    //   550: iconst_1
    //   551: aaload
    //   552: getfield a : Lnet/zamasoft/reader/book/j;
    //   555: ifnull -> 623
    //   558: aload_0
    //   559: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   562: getfield c : [Lnet/zamasoft/reader/book/z;
    //   565: iconst_1
    //   566: aaload
    //   567: getfield a : Lnet/zamasoft/reader/book/j;
    //   570: invokeinterface f : ()I
    //   575: iconst_1
    //   576: if_icmplt -> 623
    //   579: aload_0
    //   580: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   583: getfield c : [Lnet/zamasoft/reader/book/z;
    //   586: iconst_1
    //   587: aaload
    //   588: getfield a : Lnet/zamasoft/reader/book/j;
    //   591: iconst_0
    //   592: invokeinterface b : (I)Lnet/zamasoft/reader/book/e;
    //   597: astore_3
    //   598: aload_0
    //   599: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   602: aload_3
    //   603: aload_0
    //   604: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   607: getfield c : [Lnet/zamasoft/reader/book/z;
    //   610: iconst_1
    //   611: aaload
    //   612: getfield a : Lnet/zamasoft/reader/book/j;
    //   615: iconst_0
    //   616: aload_0
    //   617: getfield g : D
    //   620: invokevirtual a : (Lnet/zamasoft/reader/book/e;Lnet/zamasoft/reader/book/j;ID)V
    //   623: return
    //   624: iload_2
    //   625: ifeq -> 745
    //   628: aload_0
    //   629: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   632: iconst_0
    //   633: invokevirtual c : (Z)Z
    //   636: ifeq -> 745
    //   639: aload_0
    //   640: invokevirtual a : ()V
    //   643: aload_0
    //   644: getfield d : Lnet/zamasoft/reader/book/j;
    //   647: ifnull -> 733
    //   650: aload_0
    //   651: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   654: getfield c : [Lnet/zamasoft/reader/book/z;
    //   657: iconst_0
    //   658: aaload
    //   659: getfield a : Lnet/zamasoft/reader/book/j;
    //   662: ifnull -> 744
    //   665: aload_0
    //   666: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   669: getfield c : [Lnet/zamasoft/reader/book/z;
    //   672: iconst_0
    //   673: aaload
    //   674: getfield a : Lnet/zamasoft/reader/book/j;
    //   677: invokeinterface f : ()I
    //   682: iconst_1
    //   683: if_icmplt -> 744
    //   686: aload_0
    //   687: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   690: getfield c : [Lnet/zamasoft/reader/book/z;
    //   693: iconst_0
    //   694: aaload
    //   695: getfield a : Lnet/zamasoft/reader/book/j;
    //   698: iconst_0
    //   699: invokeinterface b : (I)Lnet/zamasoft/reader/book/e;
    //   704: astore_3
    //   705: aload_0
    //   706: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   709: aload_3
    //   710: aload_0
    //   711: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   714: getfield c : [Lnet/zamasoft/reader/book/z;
    //   717: iconst_0
    //   718: aaload
    //   719: getfield a : Lnet/zamasoft/reader/book/j;
    //   722: iconst_0
    //   723: aload_0
    //   724: getfield g : D
    //   727: invokevirtual a : (Lnet/zamasoft/reader/book/e;Lnet/zamasoft/reader/book/j;ID)V
    //   730: goto -> 744
    //   733: aload_0
    //   734: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   737: aload_0
    //   738: getfield g : D
    //   741: invokevirtual a : (D)V
    //   744: return
    //   745: iload_2
    //   746: ifne -> 754
    //   749: aload_0
    //   750: invokevirtual a : ()V
    //   753: return
    //   754: goto -> 766
    //   757: astore_2
    //   758: aload_0
    //   759: getfield f : Lnet/zamasoft/reader/book/BookController;
    //   762: aload_2
    //   763: invokevirtual a : (Ljava/lang/Exception;)V
    //   766: return
    // Exception table:
    //   from	to	target	type
    //   0	125	757	java/lang/Exception
    //   126	270	757	java/lang/Exception
    //   271	432	757	java/lang/Exception
    //   433	500	757	java/lang/Exception
    //   501	623	757	java/lang/Exception
    //   624	744	757	java/lang/Exception
    //   745	753	757	java/lang/Exception
  }
  
  @FXML
  private void zoomStarted(ZoomEvent paramZoomEvent) {
    this.B = this.g;
  }
  
  @FXML
  private void zoom(ZoomEvent paramZoomEvent) {
    a(paramZoomEvent.getZoomFactor(), paramZoomEvent.getX(), paramZoomEvent.getY());
    this.B = Math.max(this.B, this.g);
  }
  
  private void d() {
    double d1 = ReaderApplication.getRenderScale();
    double d2 = this.c.getWidth() * this.g * d1;
    double d3 = this.c.getHeight() * this.g * d1;
    if (this.imageView.getImage() == null || (this.g >= 1.0D && this.h < 1.0D) || this.g < 1.0D) {
      BufferedImage bufferedImage;
      if (this.g * d1 >= 1.0D) {
        bufferedImage = this.c;
      } else {
        bufferedImage = new BufferedImage((int)d2, (int)d3, 2);
        bufferedImage.getGraphics().drawImage(this.c.getScaledInstance((int)d2, (int)d3, 4), 0, 0, null);
      } 
      this.imageView.setImage((Image)SwingFXUtils.toFXImage(bufferedImage, null));
    } 
    this.zoomInButton.setDisable((this.g >= 10.0D));
    this.zoomOutButton.setDisable((this.g <= this.i));
    this.h = this.g;
    this.imageView.setFitWidth(d2 / d1);
    this.imageView.setFitHeight(d3 / d1);
  }
  
  private void a(double paramDouble1, double paramDouble2, double paramDouble3) {
    double d1 = this.g * paramDouble1;
    if (this.g <= this.i && d1 < this.i && this.B <= this.i)
      a(); 
    d1 = Math.min(10.0D, d1);
    d1 = Math.max(this.i, d1);
    paramDouble2 *= d1 / this.g;
    paramDouble3 *= d1 / this.g;
    this.g = d1;
    d();
    double d2 = this.imageView.getFitWidth();
    double d3 = this.imageView.getFitHeight();
    b();
    double d4 = this.scrollPane.getWidth();
    double d5 = this.scrollPane.getHeight();
    double d6 = Math.max(0.0D, Math.min(1.0D, (paramDouble2 - d4 / 2.0D) / (d2 - d4)));
    this.scrollPane.setHvalue(d6);
    this.j = d6;
    double d7 = Math.max(0.0D, Math.min(1.0D, (paramDouble3 - d5 / 2.0D) / (d3 - d5)));
    this.scrollPane.setVvalue(d7);
    this.k = d7;
    this.root.requestFocus();
  }
  
  @FXML
  private void leftAction(ActionEvent paramActionEvent) {
    a((byte)1);
  }
  
  @FXML
  private void rightAction(ActionEvent paramActionEvent) {
    a((byte)2);
  }
  
  @FXML
  private void zoomInAction(ActionEvent paramActionEvent) {
    a(false, this.anchor.getWidth() / 2.0D, this.anchor.getHeight() / 2.0D);
  }
  
  @FXML
  private void zoomOutAction(ActionEvent paramActionEvent) {
    a(true, this.anchor.getWidth() / 2.0D, this.anchor.getHeight() / 2.0D);
  }
  
  @FXML
  private void closeAction(ActionEvent paramActionEvent) {
    a();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/ImageController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */