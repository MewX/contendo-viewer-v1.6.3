package net.zamasoft.reader.book;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import net.zamasoft.reader.book.epub.g;

public class q implements w {
  private BookController d;
  
  private z e;
  
  private g f;
  
  private Pane g;
  
  private c h;
  
  EventHandler<MouseEvent> a = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        this.a.a(param1MouseEvent.getX(), param1MouseEvent.getY());
        if (this.a.h != null) {
          this.a.d.stackPane.setCursor(Cursor.HAND);
        } else {
          this.a.d.stackPane.setCursor(Cursor.DEFAULT);
        } 
      }
    };
  
  EventHandler<MouseEvent> b = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        if (this.a.h != null) {
          c c = this.a.h;
          if (c.b().a() != 3 && this.a.d.a(c, (j)null) == 1)
            this.a.a(); 
          this.a.h = null;
          param1MouseEvent.consume();
        } 
      }
    };
  
  EventHandler<MouseEvent> c = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        if (this.a.h != null)
          return; 
        this.a.a();
      }
    };
  
  public q(BookController paramBookController, z paramz, g paramg, Pane paramPane) {
    this.d = paramBookController;
    this.e = paramz;
    this.f = paramg;
    this.g = paramPane;
  }
  
  private void a(double paramDouble1, double paramDouble2) {
    paramDouble1 -= this.e.a();
    paramDouble2 -= this.e.b();
    paramDouble1 -= this.g.getLayoutX() + 20.0D;
    paramDouble2 -= this.g.getLayoutY() + 20.0D;
    if (this.h == null || !this.h.a().contains(paramDouble1, paramDouble2))
      this.h = this.f.b(paramDouble1, paramDouble2); 
  }
  
  public void a() {
    ((Pane)this.g.getParent()).getChildren().remove(this.g);
    this.d.a(new x(this.d));
  }
  
  public void b() {}
  
  public void c() {
    this.d.stackPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this.c);
    this.d.stackPane.addEventHandler(MouseEvent.MOUSE_MOVED, this.a);
    this.d.stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, this.b);
  }
  
  public void d() {
    this.d.stackPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, this.c);
    this.d.stackPane.removeEventHandler(MouseEvent.MOUSE_MOVED, this.a);
    this.d.stackPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, this.b);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/q.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */