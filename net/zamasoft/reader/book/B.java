package net.zamasoft.reader.book;

import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import net.zamasoft.reader.b;
import net.zamasoft.reader.c;
import net.zamasoft.reader.util.AlertController;
import net.zamasoft.reader.util.SVGImage;
import net.zamasoft.reader.util.g;

public class B extends AnchorPane implements w {
  private VBox d = new VBox();
  
  private Slider e;
  
  private Label f;
  
  private Slider g;
  
  private BookController h;
  
  private final ImageView i = new ImageView((Image)new SVGImage(b.class.getResource("icons/audio-play.svg").toString(), 30.0F));
  
  private final ImageView j;
  
  private final ImageView k;
  
  private final ImageView l;
  
  private final ImageView m;
  
  private final ImageView n;
  
  final Button a;
  
  final Button b;
  
  final Button c;
  
  private boolean o;
  
  private l p;
  
  private int q;
  
  private k r;
  
  private final StringBuffer s;
  
  private int t;
  
  private int u;
  
  private String v;
  
  private w w;
  
  private final EventHandler<MouseEvent> x;
  
  private final EventHandler<MouseEvent> y;
  
  protected double computeMinWidth(double paramDouble) {
    return 240.0D;
  }
  
  protected double computeMinHeight(double paramDouble) {
    return 60.0D;
  }
  
  protected double computePrefWidth(double paramDouble) {
    return 240.0D;
  }
  
  protected double computePrefHeight(double paramDouble) {
    return 60.0D;
  }
  
  protected double computeMaxHeight(double paramDouble) {
    return 60.0D;
  }
  
  public void a(BookController paramBookController) {
    this.h = paramBookController;
  }
  
  public B() {
    this.i.setFitWidth(30.0D);
    this.i.setFitHeight(30.0D);
    this.j = new ImageView((Image)new SVGImage(b.class.getResource("icons/audio-select.svg").toString(), 30.0F));
    this.j.setFitWidth(30.0D);
    this.j.setFitHeight(30.0D);
    this.k = new ImageView((Image)new SVGImage(b.class.getResource("icons/read-volume.svg").toString(), 50.0F, 30.0F));
    this.k.setFitWidth(50.0D);
    this.k.setFitHeight(30.0D);
    this.l = new ImageView((Image)new SVGImage(b.class.getResource("icons/audio-pause.svg").toString(), 30.0F));
    this.l.setFitWidth(30.0D);
    this.l.setFitHeight(30.0D);
    this.m = new ImageView((Image)new SVGImage(b.class.getResource("icons/audio-stop.svg").toString(), 30.0F));
    this.m.setFitWidth(30.0D);
    this.m.setFitHeight(30.0D);
    this.n = new ImageView((Image)new SVGImage(b.class.getResource("icons/speech.svg").toString(), 24.0F));
    this.n.setFitWidth(24.0D);
    this.n.setFitHeight(24.0D);
    this.a = new Button();
    this.b = new Button();
    this.c = new Button();
    this.o = true;
    this.s = new StringBuffer();
    this.x = (paramMouseEvent -> {
        paramMouseEvent.consume();
        double d1 = paramMouseEvent.getX();
        double d2 = paramMouseEvent.getY();
        z z = this.h.a(d1, d2);
        if (z == null)
          return; 
        d1 -= z.a();
        d2 -= z.b();
        if (this.p == null || !this.p.a.contains(d1, d2)) {
          int i = z.a.e(d1, d2);
          this.p = (i == -1) ? null : z.a.d(i);
        } 
        if (this.p != null) {
          this.h.stackPane.setCursor((this.p.a() == 3) ? c.a() : Cursor.TEXT);
        } else {
          this.h.stackPane.setCursor(Cursor.DEFAULT);
        } 
      });
    this.y = (paramMouseEvent -> {
        this.h.a(this.w);
        paramMouseEvent.consume();
        double d1 = paramMouseEvent.getX();
        double d2 = paramMouseEvent.getY();
        z z = this.h.a(d1, d2);
        if (z == null)
          return; 
        d1 -= z.a();
        d2 -= z.b();
        int i = z.a.e(d1, d2);
        if (i != -1) {
          double d;
          l l1 = z.a.d(i);
          boolean bool = (l1.a() == 3) ? true : false;
          if (bool) {
            d = d2 - l1.a.getBounds2D().getY();
          } else {
            d = d1 - l1.a.getBounds2D().getX();
          } 
          double[] arrayOfDouble = l1.c();
          byte b;
          for (b = 0; b < arrayOfDouble.length; b++) {
            d -= arrayOfDouble[b];
            if (d <= 0.0D)
              break; 
          } 
          g();
          this.t = b;
          int j;
          for (j = 0; j < i; j++)
            this.t += z.a.d(j).f(); 
          this.u = 0;
          this.s.delete(0, this.s.length());
          this.s.append(l1.d(), b, l1.f() - b);
          for (j = ++i; j < z.a.i(); j++) {
            l1 = z.a.d(j);
            this.s.append(l1.d(), 0, l1.f());
          } 
          this.q = z.c + 1;
          this.r = this.h.g.k;
          this.o = false;
          e();
        } 
      });
    setId("speechControls");
    getChildren().add(this.d);
    HBox hBox1 = new HBox();
    hBox1.setAlignment(Pos.CENTER_LEFT);
    HBox hBox2 = new HBox();
    hBox2.setAlignment(Pos.CENTER_LEFT);
    this.d.getChildren().add(hBox1);
    this.d.getChildren().add(hBox2);
    setPadding(new Insets(5.0D, 10.0D, 5.0D, 10.0D));
    this.f = new Label();
    this.f.setPrefWidth(50.0D);
    this.f.setMinWidth(50.0D);
    this.f.setTextAlignment(TextAlignment.CENTER);
    hBox2.getChildren().add(this.f);
    this.f.setText(" 1.0x");
    this.e = new Slider(0.5D, 4.0D, 1.0D);
    this.e.setPrefWidth(100.0D);
    this.e.setBlockIncrement(1.0D);
    this.e.setMajorTickUnit(0.1D);
    this.e.setMinorTickCount(0);
    this.e.setSnapToTicks(true);
    HBox.setHgrow((Node)this.e, Priority.ALWAYS);
    hBox2.getChildren().add(this.e);
    this.e.valueProperty().addListener(new ChangeListener<Number>(this) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            double d = param1Number2.doubleValue();
            g.a().a(d);
            this.a.f.setText((" " + d + "x").substring(0, 5));
          }
        });
    this.a.setGraphic((Node)this.m);
    hBox2.getChildren().add(this.a);
    this.b.setGraphic((Node)this.i);
    this.b.setOnAction(paramActionEvent -> {
          if (this.b.getGraphic() == this.l) {
            f();
            return;
          } 
          if (this.o) {
            this.t = 0;
            this.s.delete(0, this.s.length());
            this.q = this.h.g.l;
            this.r = this.h.g.k;
            this.o = false;
          } 
          e();
        });
    this.a.setOnAction(paramActionEvent -> g());
    hBox2.getChildren().add(this.b);
    this.c.setGraphic((Node)this.j);
    this.c.setOnAction(paramActionEvent -> {
          this.w = this.h.c();
          this.h.a(this);
        });
    hBox2.getChildren().add(this.c);
    hBox1.getChildren().add(this.k);
    this.g = new Slider(0.0D, 100.0D, 100.0D);
    this.g.setPrefWidth(100.0D);
    this.g.setMaxWidth(Double.NEGATIVE_INFINITY);
    this.g.setMinWidth(50.0D);
    this.g.setMajorTickUnit(1.0D);
    this.g.valueProperty().addListener(new ChangeListener<Number>(this) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            g.a().a((int)this.a.g.getValue());
          }
        });
    hBox1.getChildren().add(this.g);
    hBox1.getChildren().add(new Label("　読み上げ "));
    hBox1.getChildren().add(this.n);
  }
  
  private void a() {
    label48: while (true) {
      if (this.s.length() > 0) {
        for (byte b1 = 0; b1 < this.s.length(); b1++) {
          char c = this.s.charAt(b1);
          if (c == '。' || c == '．' || c == '.' || c == '！' || c == '」' || c == '!' || c == '？' || c == '?') {
            this.v = this.s.substring(0, b1 + 1);
            this.s.delete(0, b1 + 1);
            this.t += this.v.length();
            break label48;
          } 
        } 
        if (this.q >= this.r.s_()) {
          this.v = this.s.substring(0, this.s.length());
          this.s.delete(0, this.s.length());
          this.t += this.v.length();
          break;
        } 
      } 
      if (this.q >= this.r.s_()) {
        this.q = 0;
        int i = this.r.r_();
        if (++i >= this.h.g.k()) {
          this.o = true;
          Platform.runLater(() -> {
                this.h.o();
                this.b.setGraphic((Node)this.i);
              });
          return;
        } 
        try {
          this.r = this.h.g.a(i);
        } catch (Exception exception) {
          this.h.a(exception);
        } 
      } 
      try {
        this.h.a(this.r, this.q);
        Platform.runLater(() -> {
              try {
                this.h.n();
              } catch (Exception exception) {
                this.h.a(exception);
                return;
              } 
            });
      } catch (Exception exception) {
        this.h.a(exception);
      } 
      this.u = this.t;
      this.t = -this.s.length();
      j j = this.r.e(this.q);
      for (byte b = 0; b < j.i(); b++) {
        l l1 = j.d(b);
        this.s.append(l1.d(), 0, l1.f());
      } 
      this.q++;
    } 
    Platform.runLater(() -> {
          try {
            int i = this.v.length();
            int j = this.t - i;
            this.h.o();
            if (j < 0) {
              D d1 = D.a(this.r, this.q - 2, this.u, this.u - j - 1, true);
              this.h.a(d1, true);
              i += j;
              j = 0;
            } 
            D d = D.a(this.r, this.q - 1, j, j + i - 1, true);
            this.h.a(d, true);
          } catch (Exception exception) {
            this.h.a(exception);
            return;
          } 
        });
    try {
      g.a().a(this.v, () -> {
            if (this.b.getGraphic() == this.i)
              return; 
            a();
          });
    } catch (IOException iOException) {
      Platform.runLater(() -> {
            g();
            AlertController.a("", b.a.getString("noSpeech"), false);
          });
    } 
  }
  
  private void e() {
    this.b.setGraphic((Node)this.l);
    if (g.a().d())
      return; 
    a();
  }
  
  private void f() {
    g.a().e();
    this.h.o();
    this.b.setGraphic((Node)this.i);
    if (this.v != null) {
      this.t -= this.v.length();
      this.s.insert(0, this.v);
    } 
  }
  
  private void g() {
    g.a().e();
    this.o = true;
    this.h.o();
    this.b.setGraphic((Node)this.i);
  }
  
  public void c() {
    this.h.stackPane.addEventHandler(MouseEvent.MOUSE_MOVED, this.x);
    this.h.stackPane.addEventHandler(MouseEvent.MOUSE_PRESSED, this.y);
    setDisable(true);
  }
  
  public void b() {
    this.e.setValue(g.a().b());
  }
  
  public void d() {
    this.h.stackPane.removeEventHandler(MouseEvent.MOUSE_MOVED, this.x);
    this.h.stackPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, this.y);
    setDisable(false);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/B.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */