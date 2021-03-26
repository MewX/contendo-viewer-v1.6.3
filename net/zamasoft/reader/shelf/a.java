package net.zamasoft.reader.shelf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javax.imageio.ImageIO;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.b;
import net.zamasoft.reader.util.SVGImage;

public class a extends BorderPane {
  private static Logger b = Logger.getLogger(a.class.getName());
  
  private static final Image c = (Image)new SVGImage(b.class.getResource("icons/shelf-bookmark.svg").toString(), 32.0F);
  
  private static final Image d = (Image)new SVGImage(b.class.getResource("icons/download.svg").toString(), 32.0F);
  
  private static final Image e = (Image)new SVGImage(b.class.getResource("icons/downloaded.svg").toString(), 32.0F);
  
  public b a;
  
  private final Canvas f;
  
  private final Label g;
  
  private boolean h = false;
  
  public a(b paramb) {
    this.a = paramb;
    this.f = new Canvas(135.0D, 208.0D);
    setCenter((Node)this.f);
    this.g = new Label();
    this.g.setPadding(new Insets(10.0D, 0.0D, 0.0D, 0.0D));
    this.g.setMaxWidth(135.0D);
    setBottom((Node)this.g);
  }
  
  public void a() {
    this.h = false;
  }
  
  public void a(boolean paramBoolean, ScrollPane paramScrollPane) {
    if (this.h && !paramBoolean)
      return; 
    this.h = true;
    this.g.setText(this.a.B().i_());
    File file = new File(this.a.d(), "thumb_small");
    GraphicsContext graphicsContext = this.f.getGraphicsContext2D();
    graphicsContext.clearRect(0.0D, 0.0D, this.f.getWidth(), this.f.getHeight());
    double d = ReaderApplication.getRenderScale();
    int i = (int)Math.ceil(135.0D * d);
    int j = (int)Math.ceil(188.0D * d);
    long l = (this.a instanceof c) ? ((c)this.a).l() : this.a.u();
    if (file.exists() && file.lastModified() >= l) {
      Image image = new Image(file.toURI().toString());
      if (image.getWidth() == i && image.getHeight() == j) {
        a(image, graphicsContext);
        return;
      } 
    } 
    BooksController.h.execute(new Runnable(this, paramBoolean, paramScrollPane, i, j, file, graphicsContext, l) {
          public void run() {
            if (!this.a && this.b != null) {
              Bounds bounds1 = this.b.localToScene(this.b.getBoundsInParent());
              Bounds bounds2 = this.h.localToScene(this.h.getBoundsInLocal());
              if (!bounds1.intersects(bounds2)) {
                this.h.h = false;
                return;
              } 
            } 
            BufferedImage bufferedImage = this.h.a.d(this.c, this.d);
            Platform.runLater(new Runnable(this, bufferedImage) {
                  public void run() {
                    if (!(this.b.h.a instanceof net.zamasoft.reader.book.a.c))
                      try {
                        ImageIO.write(this.a, "png", this.b.e);
                      } catch (IOException iOException) {
                        a.b.log(Level.WARNING, "キャッシュを保存できませんでした。", iOException);
                      }  
                    WritableImage writableImage = SwingFXUtils.toFXImage(this.a, null);
                    this.b.h.a((Image)writableImage, this.b.f);
                    this.a.flush();
                    this.b.e.setLastModified(this.b.g);
                  }
                });
          }
        });
  }
  
  private void a(Image paramImage, GraphicsContext paramGraphicsContext) {
    double d = 188.0D - paramImage.getHeight() / ReaderApplication.getRenderScale();
    if (this.a.t() != 0L)
      paramGraphicsContext.drawImage(c, 87.0D, d, 48.0D, 48.0D); 
    paramGraphicsContext.setFill((Paint)Color.WHITE);
    paramGraphicsContext.fillRect(0.0D, 20.0D, 135.0D, 188.0D);
    paramGraphicsContext.drawImage(paramImage, 0.0D, d + 20.0D, 135.0D, 188.0D);
    paramGraphicsContext.setFill((Paint)Color.BLACK);
    paramGraphicsContext.strokeRect(0.0D, 20.0D, 135.0D, 188.0D);
    if (this.a instanceof c) {
      c c = (c)this.a;
      if (!c.o()) {
        paramGraphicsContext.setFill((Paint)Color.web("rgba(0,0,0,0.5)"));
        paramGraphicsContext.fillRect(0.0D, 20.0D, 135.0D, 188.0D);
        paramGraphicsContext.setFill((Paint)Color.YELLOW);
        paramGraphicsContext.fillRect(101.0D, 174.0D, 32.0D, 32.0D);
        paramGraphicsContext.drawImage(d, 101.0D, 174.0D, 32.0D, 32.0D);
      } else {
        paramGraphicsContext.drawImage(e, 101.0D, 174.0D, 32.0D, 32.0D);
      } 
    } else {
      paramGraphicsContext.drawImage(e, 101.0D, 174.0D, 32.0D, 32.0D);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */