package org.apache.html.dom;

class CollectionIndex {
  private int _index;
  
  int getIndex() {
    return this._index;
  }
  
  void decrement() {
    this._index--;
  }
  
  boolean isZero() {
    return (this._index <= 0);
  }
  
  CollectionIndex(int paramInt) {
    this._index = paramInt;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/html/dom/CollectionIndex.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */