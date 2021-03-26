package net.zamasoft.reader.book;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import net.zamasoft.reader.b;
import net.zamasoft.reader.util.AlertController;

public class A extends C {
  private static Logger a = Logger.getLogger(A.class.getName());
  
  private Button d;
  
  private TextField e;
  
  public A(BookController paramBookController, TextField paramTextField) {
    super(paramBookController);
    this.e = paramTextField;
  }
  
  protected void a() {
    if (this.d != null && this.d.getParent() != null)
      ((Pane)this.d.getParent()).getChildren().remove(this.d); 
    this.d = null;
  }
  
  protected void a(D paramD) {
    if (paramD == null || paramD.a()) {
      this.d = null;
      return;
    } 
    this.e.setText(this.b.i.c());
    if (this.d == null) {
      this.d = new Button();
      this.d.setText(b.a.getString("webSearch"));
      this.d.setOnAction(new EventHandler<ActionEvent>(this) {
            public void a(ActionEvent param1ActionEvent) {
              this.a.e();
            }
          });
    } 
    z z = this.b.b(paramD.a, paramD.b);
    Rectangle[] arrayOfRectangle = this.b.a(z, paramD, false);
    Rectangle rectangle = arrayOfRectangle[arrayOfRectangle.length - 1];
    double d1 = rectangle.getX();
    double d2 = rectangle.getY() + rectangle.getHeight();
    if (d1 + 92.0D > z.getWidth())
      d1 = z.getWidth() - 92.0D; 
    if (d2 + 32.0D > z.getHeight())
      d2 = rectangle.getY() - 32.0D; 
    this.d.relocate(d1, d2);
    z.d.getChildren().add(this.d);
  }
  
  private void e() {
    try {
      URI uRI;
      String str = this.b.i.c();
      if (str.length() > 100) {
        AlertController.a(b.a.getString("failOnWebSearch"), MessageFormat.format(b.a.getString("failOnWebSearchMessage"), new Object[] { Integer.valueOf(str.length()) }), false);
        return;
      } 
      str = URLEncoder.encode(str, "UTF-8");
      switch ((this.b.g.l_()).z) {
        case 0:
          uRI = URI.create("https://www.google.co.jp/#q=" + str);
          break;
        case 1:
          uRI = URI.create("http://search.yahoo.co.jp/search?p=" + str);
          break;
        default:
          throw new IllegalStateException();
      } 
      Desktop.getDesktop().browse(uRI);
    } catch (IOException iOException) {
      a.log(Level.WARNING, "検索できませんでした", iOException);
    } 
  }
  
  public void d() {
    super.d();
    if (this.d != null && this.d.getParent() != null)
      ((Pane)this.d.getParent()).getChildren().remove(this.d); 
    this.d = null;
    this.b.a((D)null);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/A.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */