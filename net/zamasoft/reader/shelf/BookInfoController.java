package net.zamasoft.reader.shelf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.util.c;

public class BookInfoController {
  @FXML
  private Canvas cover;
  
  @FXML
  private Label title;
  
  @FXML
  private VBox types;
  
  private List<BookTypeController> g = new ArrayList<>();
  
  public ShelfController a;
  
  public a b;
  
  private Stage h;
  
  public static final int c = 0;
  
  public static final int d = 1;
  
  public static final int e = 2;
  
  public int f = 0;
  
  private b i;
  
  public void a(ShelfController paramShelfController, a parama, Stage paramStage) {
    WritableImage writableImage;
    this.a = paramShelfController;
    this.b = parama;
    this.h = paramStage;
    this.title.setText(parama.a.B().i_());
    File file = new File(parama.a.d(), "thumb_small");
    if (!(parama.a instanceof net.zamasoft.reader.book.a.c)) {
      Image image = new Image(file.toURI().toString());
    } else {
      double d = ReaderApplication.getRenderScale();
      int i = (int)Math.ceil(135.0D * d);
      int j = (int)Math.ceil(188.0D * d);
      writableImage = SwingFXUtils.toFXImage(parama.a.d(i, j), null);
    } 
    this.cover.setWidth(135.0D);
    this.cover.setHeight(188.0D);
    GraphicsContext graphicsContext = this.cover.getGraphicsContext2D();
    graphicsContext.drawImage((Image)writableImage, 0.0D, 0.0D, 135.0D, 188.0D);
    a();
  }
  
  public void a() {
    this.types.getChildren().clear();
    if (this.b.a instanceof c) {
      c c = (c)this.b.a;
      for (c.a a1 : c.a()) {
        FXMLLoader fXMLLoader = c.a(BookTypeController.class.getResource("booktype.fxml"));
        try {
          fXMLLoader.load();
        } catch (IOException iOException) {
          throw new RuntimeException(iOException);
        } 
        Parent parent = (Parent)fXMLLoader.getRoot();
        BookTypeController bookTypeController = (BookTypeController)fXMLLoader.getController();
        this.types.getChildren().add(parent);
        this.g.add(bookTypeController);
        bookTypeController.a(this, a1);
      } 
    } else {
      FXMLLoader fXMLLoader = c.a(BookTypeController.class.getResource("booktype.fxml"));
      try {
        fXMLLoader.load();
      } catch (IOException iOException) {
        throw new RuntimeException(iOException);
      } 
      Parent parent = (Parent)fXMLLoader.getRoot();
      BookTypeController bookTypeController = (BookTypeController)fXMLLoader.getController();
      this.types.getChildren().add(parent);
      this.g.add(bookTypeController);
      bookTypeController.a(this, this.b.a);
    } 
  }
  
  public b b() {
    return this.i;
  }
  
  public void a(b paramb) {
    this.f = 1;
    this.i = paramb;
    this.h.hide();
  }
  
  public void c() {
    this.f = 2;
    this.h.hide();
  }
  
  @FXML
  private void closeAction(ActionEvent paramActionEvent) {
    for (BookTypeController bookTypeController : this.g)
      bookTypeController.b(); 
    this.h.hide();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/BookInfoController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */