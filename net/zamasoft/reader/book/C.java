package net.zamasoft.reader.book;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import net.zamasoft.reader.c;

public abstract class C implements w {
  protected BookController b;
  
  private l a;
  
  private EventHandler<MouseEvent> d = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        param1MouseEvent.consume();
        double d1 = param1MouseEvent.getX();
        double d2 = param1MouseEvent.getY();
        z z = this.a.b.a(d1, d2);
        if (z == null)
          return; 
        d1 -= z.a();
        d2 -= z.b();
        if (this.a.a == null || !this.a.a.a.contains(d1, d2))
          this.a.a = z.a.c(d1, d2); 
        if (this.a.a != null) {
          this.a.b.stackPane.setCursor((this.a.a.a() == 3) ? c.a() : Cursor.TEXT);
        } else {
          this.a.b.stackPane.setCursor(Cursor.DEFAULT);
        } 
      }
    };
  
  z c = null;
  
  private EventHandler<MouseEvent> e = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        D d;
        param1MouseEvent.consume();
        double d1 = param1MouseEvent.getX();
        double d2 = param1MouseEvent.getY();
        this.a.c = this.a.b.a(d1, d2);
        if (this.a.c == null)
          return; 
        d1 -= this.a.c.a();
        d2 -= this.a.c.b();
        this.a.a();
        int i = this.a.c.a.d(d1, d2);
        if (i != -1) {
          double d3;
          d = new D(this.a.c.b, this.a.c.c);
          d.e = (d.c = i) + 1;
          l l = this.a.c.a.c(d.c);
          boolean bool = (l.a() == 3) ? true : false;
          if (bool) {
            d3 = d2 - l.a.getBounds2D().getY();
          } else {
            d3 = d1 - l.a.getBounds2D().getX();
          } 
          double[] arrayOfDouble = l.c();
          d.d = 0;
          while (d.d < arrayOfDouble.length) {
            d3 -= arrayOfDouble[d.d];
            if (d3 <= 0.0D)
              break; 
            d.d++;
          } 
          d.f = d.d;
        } else {
          d = null;
        } 
        this.a.b.a(d);
      }
    };
  
  private EventHandler<MouseEvent> f = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        param1MouseEvent.consume();
        D d = this.a.b.i;
        if (d == null)
          return; 
        double d1 = param1MouseEvent.getX();
        double d2 = param1MouseEvent.getY();
        z z = this.a.b.a(d1, d2);
        if (z == null || this.a.c != z)
          return; 
        d1 -= z.a();
        d2 -= z.b();
        int i = z.a.d(d1, d2);
        if (i != -1) {
          double d3;
          l l = z.a.c(i);
          boolean bool = (l.a() == 3) ? true : false;
          if (bool) {
            d3 = l.a.getBounds2D().getMaxY() - d2;
          } else {
            d3 = l.a.getBounds2D().getMaxX() - d1;
          } 
          double[] arrayOfDouble = l.c();
          d.f = l.f() - 1;
          while (d.f > 0) {
            d3 -= arrayOfDouble[d.f];
            if (d3 <= 0.0D)
              break; 
            d.f--;
          } 
          d.e = i + 1;
          if (d.e >= d.c + 1 && d.f >= d.d && d.f < l.f())
            d.f++; 
        } 
        this.a.b.a(d);
      }
    };
  
  private EventHandler<MouseEvent> g = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        param1MouseEvent.consume();
        D d = this.a.b.i;
        if (d == null || this.a.c == null)
          return; 
        this.a.a(d);
        this.a.c = null;
      }
    };
  
  public C(BookController paramBookController) {
    this.b = paramBookController;
  }
  
  public void b() {
    this.c = null;
  }
  
  protected abstract void a();
  
  protected abstract void a(D paramD);
  
  public void c() {
    this.b.stackPane.addEventHandler(MouseEvent.MOUSE_MOVED, this.d);
    this.b.stackPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this.e);
    this.b.stackPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, this.f);
    this.b.stackPane.addEventHandler(MouseEvent.MOUSE_RELEASED, this.g);
  }
  
  public void d() {
    this.b.stackPane.removeEventHandler(MouseEvent.MOUSE_MOVED, this.d);
    this.b.stackPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, this.e);
    this.b.stackPane.removeEventHandler(MouseEvent.MOUSE_DRAGGED, this.f);
    this.b.stackPane.removeEventHandler(MouseEvent.MOUSE_RELEASED, this.g);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/C.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */