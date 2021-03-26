package net.zamasoft.reader.book;

import javafx.scene.control.ScrollBar;

public class MyScrollBar extends ScrollBar {
  private BookController a;
  
  public void setBookController(BookController paramBookController) {
    this.a = paramBookController;
  }
  
  public void adjustValue(double paramDouble) {
    if (paramDouble < getValue() / getMax()) {
      if (this.a.g.l() == 1) {
        this.a.i();
      } else {
        this.a.h();
      } 
    } else if (this.a.g.l() == 1) {
      this.a.h();
    } else {
      this.a.i();
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/MyScrollBar.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */