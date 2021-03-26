package net.zamasoft.reader.book;

import java.awt.geom.Rectangle2D;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import net.zamasoft.reader.b;
import net.zamasoft.reader.book.epub.d;

public class r extends C {
  private Pane d;
  
  private e e;
  
  EventHandler<MouseEvent> a = new EventHandler<MouseEvent>(this) {
      public void a(MouseEvent param1MouseEvent) {
        this.a.e = null;
        double d1 = param1MouseEvent.getX();
        double d2 = param1MouseEvent.getY();
        z z = this.a.b.a(d1, d2);
        if (z == null)
          return; 
        d1 -= z.a();
        d2 -= z.b();
        if (z.a instanceof net.zamasoft.reader.book.epub.g) {
          int i = z.a.a(d1, d2);
          if (i != -1) {
            this.a.e = z.a.b(i);
            for (s s : this.a.b.g.a(z.b)) {
              if (s.a() && s.a == ((d)this.a.e).c) {
                this.a.e = null;
                break;
              } 
            } 
            if (this.a.e != null) {
              Rectangle2D rectangle2D = this.a.e.a().getBounds2D();
              Rectangle rectangle = new Rectangle(rectangle2D.getX(), rectangle2D.getY(), rectangle2D.getWidth(), rectangle2D.getHeight());
              this.a.a(z, rectangle);
            } 
          } 
        } 
      }
    };
  
  public r(BookController paramBookController) {
    super(paramBookController);
  }
  
  protected void a() {
    if (this.d != null && this.d.getParent() != null)
      ((Pane)this.d.getParent()).getChildren().remove(this.d); 
  }
  
  private void a(z paramz, Rectangle paramRectangle) {
    if (this.d == null) {
      this.d = (Pane)new VBox();
      TextArea textArea = new TextArea();
      Button button = new Button();
      button.setText(b.a.getString("add"));
      button.setOnAction(new EventHandler<ActionEvent>(this, paramz, textArea) {
            public void a(ActionEvent param1ActionEvent) {
              s s;
              if (this.c.e != null) {
                String str = ((d)this.c.e).b.c();
                if (str == null || str.length() == 0)
                  str = b.a.getString("image"); 
                s = this.a.b.a(-1, ((d)this.c.e).c, ((d)this.c.e).c, str, this.b.getText(), true);
              } else {
                D d = this.c.b.i.b();
                j j = d.a.e(d.b);
                l l = j.c(d.c);
                int i = l.e() + d.d;
                l = j.c(d.e - 1);
                int k = l.e() + d.f;
                s = d.a.a(d.b, i, k, d.c(), this.b.getText(), false);
              } 
              this.c.b.g.o().add(s);
              this.c.b.a((D)null);
              this.c.b.g();
              this.c.b.f();
            }
          });
      textArea.setPrefWidth(280.0D);
      textArea.setPrefHeight(115.0D);
      this.d.getChildren().add(textArea);
      this.d.getChildren().add(button);
    } 
    double d1 = paramRectangle.getX();
    double d2 = paramRectangle.getY() + paramRectangle.getHeight();
    if (d1 + 280.0D > paramz.getWidth())
      d1 = paramz.getWidth() - 280.0D; 
    if (d2 + 145.0D > paramz.getHeight())
      d2 = Math.max(48.0D, paramRectangle.getY() - 145.0D); 
    this.d.relocate(d1, d2);
    ((TextArea)this.d.getChildren().get(0)).setText("");
    paramz.d.getChildren().add(this.d);
  }
  
  protected void a(D paramD) {
    this.e = null;
    if (paramD == null || paramD.a()) {
      this.d = null;
      return;
    } 
    z z = this.b.b(paramD.a, paramD.b);
    Rectangle[] arrayOfRectangle = this.b.a(z, paramD, false);
    Rectangle rectangle = arrayOfRectangle[arrayOfRectangle.length - 1];
    a(z, rectangle);
  }
  
  public void c() {
    super.c();
    this.b.stackPane.addEventHandler(MouseEvent.MOUSE_CLICKED, this.a);
  }
  
  public void d() {
    super.d();
    this.b.stackPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, this.a);
    if (this.d != null && this.d.getParent() != null) {
      ((Pane)this.d.getParent()).getChildren().remove(this.d);
      this.d = null;
    } 
    this.b.a((D)null);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/r.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */