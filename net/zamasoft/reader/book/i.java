package net.zamasoft.reader.book;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import jp.cssj.sakae.a.h;
import jp.cssj.sakae.b.a.b;
import jp.cssj.sakae.c.a.e;
import jp.cssj.sakae.c.b;
import jp.cssj.sakae.c.b.b;
import jp.cssj.sakae.pdf.c.c;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.b;
import net.zamasoft.reader.book.epub.b;
import net.zamasoft.reader.util.c;

public class i {
  public Area a;
  
  public final URI b;
  
  public final boolean c;
  
  public final boolean d;
  
  public final boolean e;
  
  public final boolean f;
  
  public final b g;
  
  private final ImageView i = new ImageView(new Image(b.class.getResource("icons/video.png").toString()));
  
  protected MediaPlayer h = null;
  
  private static final Map<String, i> j = new HashMap<>();
  
  private static int k = -1;
  
  private static final List<i> l = new ArrayList<>();
  
  private static final List<i> m = new ArrayList<>();
  
  private v n;
  
  private t o;
  
  private StackPane p;
  
  private Rectangle q;
  
  private MediaView r;
  
  private ImageView s;
  
  private boolean t;
  
  private Stage u;
  
  private Scene v;
  
  private ChangeListener<Boolean> w;
  
  private boolean x = false;
  
  private i(Area paramArea, URI paramURI, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, b paramb) {
    this.a = paramArea;
    this.b = paramURI;
    this.c = paramBoolean1;
    this.d = paramBoolean2;
    this.e = paramBoolean3;
    this.f = paramBoolean4;
    this.g = paramb;
  }
  
  public static i a(Area paramArea, URI paramURI, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, b paramb, String paramString) {
    i i1 = j.get(paramString);
    if (i1 == null) {
      i1 = new i(paramArea, paramURI, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramb);
      j.put(paramString, i1);
    } else {
      i1.a = paramArea;
      if (i1.r != null) {
        Rectangle2D rectangle2D = i1.a.getBounds2D();
        if (i1.s != null) {
          i1.s.setFitWidth(rectangle2D.getWidth());
          i1.s.setFitHeight(rectangle2D.getHeight());
        } 
        i1.r.setFitWidth(rectangle2D.getWidth());
        i1.r.setFitHeight(rectangle2D.getHeight());
        i1.q.setWidth(rectangle2D.getWidth());
        i1.q.setHeight(rectangle2D.getHeight());
      } 
    } 
    return i1;
  }
  
  public static synchronized void a() {
    for (i i1 : l)
      i1.h(); 
    l.clear();
    for (i i1 : m)
      i1.h(); 
    m.clear();
    j.clear();
    k = -1;
  }
  
  public static synchronized i a(int paramInt) {
    for (i i1 : l) {
      if (i1.h != null) {
        if (i1.h.getStatus() == MediaPlayer.Status.PLAYING)
          return i1; 
        if (i1.h.getStatus() == MediaPlayer.Status.READY && i1.c)
          return i1; 
      } 
    } 
    if (k != paramInt)
      for (i i1 : m) {
        if (i1.h != null) {
          if (i1.h.getStatus() == MediaPlayer.Status.PLAYING)
            return i1; 
          if (i1.h.getStatus() == MediaPlayer.Status.READY && i1.c)
            return i1; 
        } 
      }  
    return null;
  }
  
  public static synchronized void b(int paramInt) {
    for (i i1 : l)
      i1.h(); 
    l.clear();
    if (k != paramInt) {
      for (i i1 : m)
        i1.h(); 
      m.clear();
    } 
  }
  
  public MediaPlayer a(b paramb, int paramInt) {
    if (paramInt != k) {
      k = paramInt;
      if (m.contains(this))
        return this.h; 
      if (l.contains(this)) {
        for (i i1 : m)
          i1.h(); 
        m.clear();
        m.addAll(l);
        l.clear();
        return this.h;
      } 
      if (l.isEmpty()) {
        l.addAll(m);
      } else {
        for (i i1 : m)
          i1.h(); 
      } 
      m.clear();
    } else if (m.contains(this)) {
      return this.h;
    } 
    if (this.h == null)
      this.h = new MediaPlayer(paramb.b(this.b)); 
    m.add(this);
    if (this.c) {
      boolean bool = true;
      for (i i1 : m) {
        if (i1 != this && i1.c) {
          bool = false;
          break;
        } 
      } 
      if (bool) {
        this.h.play();
        b();
      } 
    } 
    if (!this.e) {
      this.h.setOnPlaying(new Runnable(this) {
            public void run() {
              this.a.b();
            }
          });
      this.h.setOnEndOfMedia(new Runnable(this) {
            public void run() {
              this.a.d();
            }
          });
    } 
    return this.h;
  }
  
  public void b() {
    if (this.r != null) {
      this.r.setVisible(true);
      this.q.setVisible(true);
      this.i.setVisible(false);
    } 
    if (m.contains(this)) {
      for (i i1 : l)
        i1.h(); 
      l.clear();
    } 
  }
  
  public void c() {
    if (this.r != null)
      this.i.setVisible(true); 
  }
  
  public void d() {
    this.h.seek(this.h.getStartTime());
    if (this.d) {
      this.h.play();
    } else {
      List<i> list;
      if (this.r != null) {
        this.i.setVisible(true);
        this.r.setVisible(false);
        this.q.setVisible(false);
      } 
      this.h.stop();
      int j = m.indexOf(this);
      if (j != -1) {
        list = m;
      } else {
        j = l.indexOf(this);
        list = l;
      } 
      if (j != -1)
        for (int k = j + 1; k < list.size(); k++) {
          i i1 = list.get(k);
          if (i1.c) {
            i1.h.seek(i1.h.getStartTime());
            i1.h.play();
            break;
          } 
        }  
    } 
  }
  
  public Node e() {
    if (!this.f && !this.e)
      return null; 
    if (this.n != null)
      return (Node)this.n; 
    if (this.f) {
      u u = new u();
      this.p = new StackPane();
      this.p.setMouseTransparent(false);
      this.p.setCursor(Cursor.DEFAULT);
      Rectangle2D rectangle2D = this.a.getBounds2D();
      this.r = new MediaView(this.h);
      this.q = new Rectangle(0.0D, 0.0D, rectangle2D.getWidth(), rectangle2D.getHeight());
      this.q.setFill((Paint)Color.web("#222222"));
      if (this.g != null) {
        BufferedImage bufferedImage = new BufferedImage((int)this.g.a(), (int)this.g.b(), 2);
        Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        RenderingHints renderingHints = graphics2D.getRenderingHints();
        c.a(renderingHints);
        graphics2D.setRenderingHints(renderingHints);
        b b1 = new b(graphics2D, (e)new c((h)c.a()));
        this.g.a((b)b1);
        this.s = new ImageView((Image)SwingFXUtils.toFXImage(bufferedImage, null));
        this.s.setPreserveRatio(true);
        this.s.setFitWidth(rectangle2D.getWidth());
        this.s.setFitHeight(rectangle2D.getHeight());
        this.p.getChildren().add(this.s);
        this.r.setVisible(false);
        this.q.setVisible(false);
      } 
      this.p.getChildren().add(this.q);
      this.r.setPreserveRatio(true);
      this.r.setFitWidth(rectangle2D.getWidth());
      this.r.setFitHeight(rectangle2D.getHeight());
      StackPane.setAlignment((Node)this.r, Pos.CENTER);
      this.p.getChildren().add(this.r);
      this.i.setFitWidth(64.0D);
      this.i.setFitHeight(64.0D);
      this.i.setOnMousePressed(new EventHandler<MouseEvent>(this) {
            public void a(MouseEvent param1MouseEvent) {
              MediaPlayer.Status status = this.a.h.getStatus();
              if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED)
                return; 
              if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED) {
                if (this.a.h.getCurrentTime().equals(this.a.h.getStopTime()))
                  this.a.h.seek(this.a.h.getStartTime()); 
                this.a.h.play();
              } 
              param1MouseEvent.consume();
            }
          });
      StackPane.setAlignment((Node)this.i, Pos.CENTER);
      this.p.getChildren().add(this.i);
      if (this.e) {
        this.o = new t(this, this.h);
        this.o.setOpacity(0.0D);
        StackPane.setAlignment((Node)this.o, Pos.BOTTOM_CENTER);
        this.p.getChildren().add(this.o);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000.0D), (Node)this.o);
        Timeline timeline = new Timeline(new KeyFrame[] { new KeyFrame(Duration.millis(3000.0D), new EventHandler<ActionEvent>(this, fadeTransition) {
                  public void a(ActionEvent param1ActionEvent) {
                    this.a.stop();
                    this.a.setDuration(Duration.millis(1000.0D * this.b.o.getOpacity()));
                    this.a.setFromValue(this.b.o.getOpacity());
                    this.a.setToValue(0.0D);
                    this.a.play();
                  }
                }new javafx.animation.KeyValue[0]) });
        this.p.setOnMousePressed(new EventHandler<MouseEvent>(this) {
              public void a(MouseEvent param1MouseEvent) {
                this.a.x = false;
              }
            });
        this.p.setOnMouseDragged(new EventHandler<MouseEvent>(this) {
              public void a(MouseEvent param1MouseEvent) {
                this.a.x = true;
              }
            });
        this.p.setOnMouseReleased(new EventHandler<MouseEvent>(this, fadeTransition, timeline) {
              public void a(MouseEvent param1MouseEvent) {
                if (this.c.x)
                  return; 
                if (this.c.o.getOpacity() >= 0.99D || (this.c.o.getOpacity() >= 0.01D && this.a.getStatus() == Animation.Status.RUNNING && this.a.getToValue() == 1.0D)) {
                  this.a.stop();
                  this.b.stop();
                  this.a.setDuration(Duration.millis(1000.0D * this.c.o.getOpacity()));
                  this.a.setFromValue(this.c.o.getOpacity());
                  this.a.setToValue(0.0D);
                  this.a.play();
                } else if (this.c.o.getOpacity() <= 0.01D || (this.c.o.getOpacity() <= 0.99D && this.a.getStatus() == Animation.Status.RUNNING && this.a.getToValue() == 0.0D)) {
                  this.a.stop();
                  this.b.stop();
                  this.a.setDuration(Duration.millis(1000.0D * (1.0D - this.c.o.getOpacity())));
                  this.a.setFromValue(this.c.o.getOpacity());
                  this.a.setToValue(1.0D);
                  this.a.play();
                } 
                param1MouseEvent.consume();
              }
            });
        this.p.setOnMouseEntered(new EventHandler<MouseEvent>(this, fadeTransition, timeline) {
              public void a(MouseEvent param1MouseEvent) {
                this.a.stop();
                this.b.stop();
                this.a.setDuration(Duration.millis(1000.0D * (1.0D - this.c.o.getOpacity())));
                this.a.setFromValue(this.c.o.getOpacity());
                this.a.setToValue(1.0D);
                this.a.play();
              }
            });
        this.p.setOnMouseExited(new EventHandler<MouseEvent>(this, fadeTransition, timeline) {
              public void a(MouseEvent param1MouseEvent) {
                this.a.stop();
                this.b.stop();
                this.a.setDuration(Duration.millis(1000.0D * this.c.o.getOpacity()));
                this.a.setFromValue(this.c.o.getOpacity());
                this.a.setToValue(0.0D);
                this.a.play();
              }
            });
        this.p.setOnMouseMoved(new EventHandler<MouseEvent>(this, timeline, fadeTransition) {
              public void a(MouseEvent param1MouseEvent) {
                if (this.c.o.getOpacity() >= 0.01D) {
                  this.a.stop();
                  this.a.play();
                  return;
                } 
                this.b.stop();
                this.a.stop();
                this.b.setDuration(Duration.millis(1000.0D));
                this.b.setFromValue(0.0D);
                this.b.setToValue(1.0D);
                this.b.play();
              }
            });
        this.o.setOnMouseEntered(new EventHandler<MouseEvent>(this, fadeTransition, timeline) {
              public void a(MouseEvent param1MouseEvent) {
                this.a.stop();
                this.b.stop();
                this.a.setDuration(Duration.millis(1000.0D * (1.0D - this.c.o.getOpacity())));
                this.a.setFromValue(this.c.o.getOpacity());
                this.a.setToValue(1.0D);
                this.a.play();
              }
            });
        this.o.setOnMousePressed(new EventHandler<MouseEvent>(this) {
              public void a(MouseEvent param1MouseEvent) {
                param1MouseEvent.consume();
              }
            });
        this.o.setOnMouseDragged(new EventHandler<MouseEvent>(this) {
              public void a(MouseEvent param1MouseEvent) {
                param1MouseEvent.consume();
              }
            });
        this.o.setOnMouseReleased(new EventHandler<MouseEvent>(this) {
              public void a(MouseEvent param1MouseEvent) {
                param1MouseEvent.consume();
              }
            });
        this.o.setOnMouseMoved(new EventHandler<MouseEvent>(this) {
              public void a(MouseEvent param1MouseEvent) {
                param1MouseEvent.consume();
              }
            });
        this.o.setOnMouseExited(new EventHandler<MouseEvent>(this, timeline) {
              public void a(MouseEvent param1MouseEvent) {
                this.a.stop();
                this.a.play();
              }
            });
      } 
      u.getChildren().add(this.p);
      this.n = u;
    } else {
      this.n = new t(this, this.h);
    } 
    return (Node)this.n;
  }
  
  public void f() {
    Group group = (Group)this.n;
    if (this.u == null) {
      this.u = ReaderApplication.getInstance().k();
      this.t = this.u.isFullScreen();
      this.o.a(true);
      group.getChildren().remove(this.p);
      Scene scene = new Scene((Parent)this.p);
      scene.getStylesheets().add(ReaderApplication.class.getResource("main.css").toExternalForm());
      scene.getStylesheets().add(ReaderApplication.class.getResource("book/book.css").toExternalForm());
      ObservableList observableList = Screen.getScreensForRectangle(this.u.getX(), this.u.getY(), this.u.getWidth(), this.u.getHeight());
      Screen screen = (Screen)observableList.get(0);
      this.r.setFitWidth(screen.getBounds().getWidth());
      this.r.setFitHeight(screen.getBounds().getHeight());
      this.q.setWidth(screen.getBounds().getWidth());
      this.q.setHeight(screen.getBounds().getHeight());
      if (this.s != null) {
        this.s.setFitWidth(screen.getBounds().getWidth());
        this.s.setFitHeight(screen.getBounds().getHeight());
      } 
      this.v = this.u.getScene();
      this.u.setScene(scene);
      this.u.setFullScreen(true);
      this.w = new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            this.a.u.fullScreenProperty().removeListener(this);
            this.a.g();
            Platform.runLater(new Runnable(this) {
                  public void run() {
                    ReaderApplication.getInstance().k().setFullScreen(this.a.a.t);
                  }
                });
          }
        };
      this.u.fullScreenProperty().addListener(this.w);
    } else {
      this.u.fullScreenProperty().removeListener(this.w);
      g();
      ReaderApplication.getInstance().k().setFullScreen(this.t);
    } 
  }
  
  protected void g() {
    this.o.a(false);
    Group group = (Group)this.n;
    Rectangle2D rectangle2D = this.a.getBounds2D();
    this.r.setFitWidth(rectangle2D.getWidth());
    this.r.setFitHeight(rectangle2D.getHeight());
    this.q.setWidth(rectangle2D.getWidth());
    this.q.setHeight(rectangle2D.getHeight());
    if (this.s != null) {
      this.s.setFitWidth(rectangle2D.getWidth());
      this.s.setFitHeight(rectangle2D.getHeight());
    } 
    double d1 = this.v.getWidth();
    double d2 = this.v.getHeight();
    Parent parent = this.v.getRoot();
    this.v.setRoot((Parent)new Group());
    Scene scene = new Scene(parent, d1, d2);
    scene.setOnKeyPressed(this.v.getOnKeyPressed());
    this.u.setScene(this.v = scene);
    group.getChildren().add(this.p);
    this.u.sizeToScene();
    this.u = null;
    this.v = null;
    this.w = null;
  }
  
  protected void h() {
    if (this.h != null) {
      this.h.dispose();
      this.h = null;
    } 
    this.n = null;
    this.i.setVisible(true);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/i.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */