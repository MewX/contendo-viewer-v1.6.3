package net.zamasoft.reader.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javax.swing.Timer;
import net.zamasoft.reader.c;
import net.zamasoft.reader.util.c;

public class x implements w {
  private double j = -1.0D;
  
  private long k = -1L;
  
  private double l = -1.0D;
  
  private boolean m;
  
  private BookController n;
  
  private c o;
  
  private j p;
  
  private e q;
  
  private j r;
  
  private int s;
  
  EventHandler<MouseEvent> a = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        this.a.a(param1MouseEvent.getX(), param1MouseEvent.getY());
        if (this.a.o != null) {
          this.a.n.stackPane.setCursor(Cursor.HAND);
        } else if (this.a.q != null) {
          this.a.n.stackPane.setCursor(c.b());
        } else {
          this.a.n.stackPane.setCursor(Cursor.DEFAULT);
        } 
      }
    };
  
  EventHandler<MouseEvent> b = new EventHandler<MouseEvent>(this) {
      boolean a = false;
      
      public void a(MouseEvent param1MouseEvent) {
        if (this.b.o != null) {
          c c = this.b.o;
          this.a = false;
          if (this.b.q != null) {
            int i = c.b();
            Timer timer = new Timer(i, new ActionListener(this, c) {
                  public void actionPerformed(ActionEvent param2ActionEvent) {
                    if (!this.b.a)
                      Platform.runLater(new Runnable(this) {
                            public void run() {
                              this.a.b.b.n.a(this.a.a, this.a.b.b.p);
                            }
                          }); 
                  }
                });
            timer.setRepeats(false);
            timer.start();
          } else {
            this.b.n.a(c, this.b.p);
          } 
          this.b.o = null;
          this.b.l = -1.0D;
          param1MouseEvent.consume();
        } 
        if (this.b.q != null && param1MouseEvent.getClickCount() >= 2) {
          if (this.b.l >= 0.0D) {
            this.b.n.m();
            this.b.l = -1.0D;
          } 
          this.b.n.a(this.b.q, this.b.r, this.b.s, 0.0D);
          this.b.q = null;
          param1MouseEvent.consume();
          this.a = true;
        } 
      }
    };
  
  EventHandler<ZoomEvent> c = new EventHandler<ZoomEvent>(this) {
      public void a(ZoomEvent param1ZoomEvent) {
        if (this.a.n.g.s()) {
          this.a.a(param1ZoomEvent.getX(), param1ZoomEvent.getY());
          if (this.a.q != null) {
            if (this.a.l >= 0.0D) {
              this.a.n.m();
              this.a.l = -1.0D;
            } 
            this.a.n.a(this.a.q, this.a.r, this.a.s, 0.0D);
            this.a.q = null;
            return;
          } 
        } else {
          this.a.n.a(0.0D);
        } 
      }
    };
  
  private boolean t = false;
  
  EventHandler<ScrollEvent> d = new EventHandler<ScrollEvent>(this) {
      public void a(ScrollEvent param1ScrollEvent) {
        param1ScrollEvent.consume();
        this.a.t = true;
      }
    };
  
  EventHandler<ScrollEvent> e = new EventHandler<ScrollEvent>(this) {
      public void a(ScrollEvent param1ScrollEvent) {
        param1ScrollEvent.consume();
        if (!this.a.t) {
          if (System.currentTimeMillis() - this.a.k < 1000L)
            return; 
          this.a.a((param1ScrollEvent.getTotalDeltaX() > 0.0D));
          return;
        } 
      }
    };
  
  EventHandler<ScrollEvent> f = new EventHandler<ScrollEvent>(this) {
      public void a(ScrollEvent param1ScrollEvent) {
        param1ScrollEvent.consume();
        if (this.a.t)
          this.a.a((param1ScrollEvent.getTotalDeltaX() > 0.0D)); 
        this.a.t = false;
      }
    };
  
  EventHandler<MouseEvent> g = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        param1MouseEvent.consume();
        this.a.a(param1MouseEvent.getX());
      }
    };
  
  EventHandler<MouseEvent> h = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        param1MouseEvent.consume();
        this.a.b(param1MouseEvent.getX());
      }
    };
  
  EventHandler<MouseEvent> i = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        param1MouseEvent.consume();
        this.a.c(param1MouseEvent.getX());
      }
    };
  
  public x(BookController paramBookController) {
    this.n = paramBookController;
  }
  
  private void a(double paramDouble1, double paramDouble2) {
    if (this.l > 0.0D)
      return; 
    z z = this.n.a(paramDouble1, paramDouble2);
    if (z == null) {
      this.q = null;
      this.o = null;
      return;
    } 
    paramDouble1 -= z.a();
    paramDouble2 -= z.b();
    if (this.o == null || this.p != z.a || !this.o.a().contains(paramDouble1, paramDouble2)) {
      this.o = z.a.b(paramDouble1, paramDouble2);
      this.p = z.a;
    } 
    if (this.n.g.s() && (this.q == null || this.r != z.a || !this.q.a().contains(paramDouble1, paramDouble2))) {
      this.s = z.a.a(paramDouble1, paramDouble2);
      if (this.s != -1) {
        this.q = z.a.b(this.s);
        this.r = z.a;
      } else {
        this.q = null;
      } 
    } 
  }
  
  private void a(boolean paramBoolean) {
    double d1 = 100.0D;
    double d2 = this.n.root.getWidth() / 2.0D + (paramBoolean ? -d1 : d1);
    a(d2);
    this.q = null;
    this.o = null;
    c(d2 + (paramBoolean ? -d1 : d1));
    this.k = System.currentTimeMillis();
  }
  
  private void a(double paramDouble) {
    if (this.l != -1.0D)
      return; 
    this.m = (paramDouble <= this.n.root.getWidth() / 2.0D);
    if ((this.n.g.l_()).u == 0) {
      try {
        if ((this.m ^ ((this.n.g.l() == 2) ? 1 : 0)) != 0) {
          if (!this.n.d(true))
            return; 
        } else if (!this.n.c(true)) {
          return;
        } 
      } catch (Exception exception) {
        this.n.a(exception);
        return;
      } 
    } else if (Math.abs(this.n.root.getWidth() / 2.0D - paramDouble) < 100.0D) {
      return;
    } 
    this.j = paramDouble;
    this.k = System.currentTimeMillis();
    this.l = 0.0D;
  }
  
  private void b(double paramDouble) {
    this.q = null;
    if (this.l < 0.0D)
      return; 
    if ((this.n.g.l_()).u == 0 && this.n.c[0] != null) {
      double d2;
      boolean bool = (this.n.c[1] != null) ? true : false;
      double d1 = paramDouble - this.j;
      if (!this.m)
        d1 = -d1; 
      if (bool) {
        d2 = this.n.c[0].getWidth() + this.n.c[1].getWidth();
      } else {
        d2 = this.n.c[0].getWidth();
      } 
      if (d1 >= 0.0D && d1 <= d2) {
        this.l = d1 * 100.0D / d2;
        d(this.l);
      } 
    } 
  }
  
  private void c(double paramDouble) {
    if (this.l < 0.0D)
      return; 
    if (this.q != null) {
      b();
      return;
    } 
    boolean bool = false;
    double d = paramDouble - this.j;
    if (this.m) {
      if (d > this.n.c[0].getWidth() / 4.0D)
        bool = true; 
    } else if (-d > this.n.c[0].getWidth() / 4.0D) {
      bool = true;
    } 
    if (!bool && System.currentTimeMillis() - this.k < 250L && this.o == null && (paramDouble < this.n.root.getWidth() / 2.0D - 100.0D || paramDouble > this.n.root.getWidth() / 2.0D + 100.0D))
      bool = true; 
    if (this.n.c[1] != null) {
      d = paramDouble - this.j;
      double d1 = this.n.c[0].getWidth() + this.n.c[1].getWidth();
      if (this.m) {
        if (-d > this.n.c[0].getWidth() / 4.0D && paramDouble < d1 / 2.0D && this.j < d1 / 2.0D)
          bool = false; 
      } else if (d > this.n.c[0].getWidth() / 4.0D && paramDouble > d1 / 2.0D && this.j > d1 / 2.0D) {
        bool = false;
      } 
    } 
    if (bool)
      this.o = null; 
    try {
      if ((this.n.g.l_()).u == 0) {
        if (bool) {
          d = this.l;
          this.l = -2.0D;
          Transition transition = new Transition(this, d) {
              protected void interpolate(double param1Double) {
                this.b.d(this.a + (100.0D - this.a) * param1Double);
              }
            };
          transition.setOnFinished(new EventHandler<ActionEvent>(this) {
                public void a(ActionEvent param1ActionEvent) {
                  try {
                    this.a.n.l();
                  } catch (Exception exception) {
                    this.a.n.a(exception);
                  } 
                  this.a.l = -1.0D;
                  this.a.q = null;
                  this.a.o = null;
                }
              });
          transition.play();
        } else {
          b();
        } 
      } else {
        if (bool)
          if ((this.m ^ ((this.n.g.l() == 2) ? 1 : 0)) != 0) {
            this.n.d(false);
          } else {
            this.n.c(false);
          }  
        this.l = -1.0D;
      } 
    } catch (Exception exception) {
      this.n.a(exception);
    } 
  }
  
  public void b() {
    this.n.m();
    this.l = -1.0D;
  }
  
  private void d(double paramDouble) {
    boolean bool = (this.n.g.l() == 2) ? true : false;
    if (this.m) {
      z z1;
      z z2;
      boolean bool1 = ((bool && this.n.c[1] != null) || (!bool && this.n.d[1] != null)) ? true : false;
      if (bool1) {
        z1 = bool ? this.n.c[1] : this.n.c[0];
        z2 = bool ? this.n.d[0] : this.n.d[1];
      } else {
        z1 = this.n.c[0];
        z2 = this.n.d[0];
      } 
      z1.toFront();
      if (bool1) {
        double d1 = z1.getWidth() * paramDouble / 100.0D;
        double d2 = (z1.getWidth() + z2.getWidth()) * paramDouble / 100.0D;
        z1.setTranslateX(d2);
        z1.setClip((Node)new Rectangle(0.0D, 0.0D, z1.getWidth() - d1, z1.getHeight()));
        z2.toFront();
        z2.setClip((Node)new Rectangle(0.0D, 0.0D, Math.max(0.0D, d2 - z2.getLayoutX() - 3.0D), z2.getHeight()));
      } else if (bool) {
        double d = z1.getWidth() * paramDouble / 100.0D;
        z1.setTranslateX(d);
        z1.setClip((Node)new Rectangle(0.0D, 0.0D, z1.getWidth() - d, z1.getHeight()));
      } else {
        double d = z2.getWidth() * paramDouble / 100.0D;
        z2.toFront();
        z2.setTranslateX(-z2.getWidth() + d);
        z2.setClip((Node)new Rectangle(z2.getWidth() - d, 0.0D, d, z2.getHeight()));
      } 
    } else {
      z z1;
      z z2;
      boolean bool1 = ((bool && this.n.d[1] != null) || (!bool && this.n.c[1] != null)) ? true : false;
      if (bool1) {
        z1 = bool ? this.n.d[1] : this.n.d[0];
        z2 = bool ? this.n.c[0] : this.n.c[1];
        z1.toFront();
        (bool ? this.n.d[0] : this.n.c[1]).toFront();
      } else {
        z1 = this.n.d[0];
        z2 = this.n.c[0];
      } 
      z2.toFront();
      if (bool1) {
        double d1 = z2.getWidth() * paramDouble / 100.0D;
        double d2 = (z1.getWidth() + z2.getWidth()) * paramDouble / 100.0D;
        z2.setTranslateX(-d2);
        z2.setClip((Node)new Rectangle(d1, 0.0D, z2.getWidth() - d1, z2.getHeight()));
        z1.setClip((Node)new Rectangle(Math.max(0.0D, z2.getLayoutX() - 3.0D + z2.getWidth() - d2), 0.0D, z1.getWidth(), z1.getHeight()));
      } else if (!bool) {
        double d = z2.getWidth() * paramDouble / 100.0D;
        z2.setTranslateX(-d);
        z2.setClip((Node)new Rectangle(d, 0.0D, z2.getWidth() - d, z2.getHeight()));
      } else {
        double d = z1.getWidth() * paramDouble / 100.0D;
        z1.toFront();
        z1.setTranslateX(z1.getWidth() - d);
        z1.setClip((Node)new Rectangle(0.0D, 0.0D, d, z1.getHeight()));
      } 
    } 
  }
  
  public void c() {
    this.n.stackPane.addEventHandler(MouseEvent.MOUSE_MOVED, this.a);
    this.n.stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, this.b);
    this.n.stackPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this.g);
    this.n.stackPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, this.h);
    this.n.stackPane.addEventHandler(MouseEvent.MOUSE_RELEASED, this.i);
    this.n.stackPane.addEventHandler(ZoomEvent.ZOOM_STARTED, this.c);
    this.n.stackPane.addEventHandler(ScrollEvent.SCROLL_STARTED, this.d);
    this.n.stackPane.addEventHandler(ScrollEvent.SCROLL, this.e);
    this.n.stackPane.addEventHandler(ScrollEvent.SCROLL_FINISHED, this.f);
  }
  
  public void d() {
    this.n.stackPane.removeEventHandler(MouseEvent.MOUSE_MOVED, this.a);
    this.n.stackPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, this.b);
    this.n.stackPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, this.g);
    this.n.stackPane.removeEventHandler(MouseEvent.MOUSE_DRAGGED, this.h);
    this.n.stackPane.removeEventHandler(MouseEvent.MOUSE_RELEASED, this.i);
    this.n.stackPane.removeEventHandler(ZoomEvent.ZOOM_STARTED, this.c);
    this.n.stackPane.removeEventHandler(ScrollEvent.SCROLL_STARTED, this.d);
    this.n.stackPane.removeEventHandler(ScrollEvent.SCROLL, this.e);
    this.n.stackPane.removeEventHandler(ScrollEvent.SCROLL_FINISHED, this.f);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/x.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */