package net.zamasoft.reader.shelf;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public abstract class AbstractBoardController {
  @FXML
  protected Pane root;
  
  @FXML
  protected BooksController booksController;
  
  protected ShelfController a = null;
  
  public void a(ShelfController paramShelfController) {
    this.a = paramShelfController;
    this.booksController.a(paramShelfController);
  }
  
  public abstract void a();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/AbstractBoardController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */