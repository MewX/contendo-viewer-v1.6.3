package org.apache.xerces.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.xerces.xni.Augmentations;

public class AugmentationsImpl implements Augmentations {
  private AugmentationsItemsContainer fAugmentationsContainer = new SmallContainer();
  
  public Object putItem(String paramString, Object paramObject) {
    Object object = this.fAugmentationsContainer.putItem(paramString, paramObject);
    if (object == null && this.fAugmentationsContainer.isFull())
      this.fAugmentationsContainer = this.fAugmentationsContainer.expand(); 
    return object;
  }
  
  public Object getItem(String paramString) {
    return this.fAugmentationsContainer.getItem(paramString);
  }
  
  public Object removeItem(String paramString) {
    return this.fAugmentationsContainer.removeItem(paramString);
  }
  
  public Enumeration keys() {
    return this.fAugmentationsContainer.keys();
  }
  
  public void removeAllItems() {
    this.fAugmentationsContainer.clear();
  }
  
  public String toString() {
    return this.fAugmentationsContainer.toString();
  }
  
  static final class LargeContainer extends AugmentationsItemsContainer {
    private final HashMap fAugmentations = new HashMap();
    
    public Object getItem(Object param1Object) {
      return this.fAugmentations.get(param1Object);
    }
    
    public Object putItem(Object param1Object1, Object param1Object2) {
      return this.fAugmentations.put(param1Object1, param1Object2);
    }
    
    public Object removeItem(Object param1Object) {
      return this.fAugmentations.remove(param1Object);
    }
    
    public Enumeration keys() {
      return Collections.enumeration(this.fAugmentations.keySet());
    }
    
    public void clear() {
      this.fAugmentations.clear();
    }
    
    public boolean isFull() {
      return false;
    }
    
    public AugmentationsImpl.AugmentationsItemsContainer expand() {
      return this;
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("LargeContainer");
      for (Map.Entry entry : this.fAugmentations.entrySet()) {
        stringBuffer.append("\nkey == ");
        stringBuffer.append(entry.getKey());
        stringBuffer.append("; value == ");
        stringBuffer.append(entry.getValue());
      } 
      return stringBuffer.toString();
    }
  }
  
  static final class SmallContainer extends AugmentationsItemsContainer {
    static final int SIZE_LIMIT = 10;
    
    final Object[] fAugmentations = new Object[20];
    
    int fNumEntries = 0;
    
    public Enumeration keys() {
      return new SmallContainerKeyEnumeration(this);
    }
    
    public Object getItem(Object param1Object) {
      for (int i = 0; i < this.fNumEntries * 2; i += 2) {
        if (this.fAugmentations[i].equals(param1Object))
          return this.fAugmentations[i + 1]; 
      } 
      return null;
    }
    
    public Object putItem(Object param1Object1, Object param1Object2) {
      for (int i = 0; i < this.fNumEntries * 2; i += 2) {
        if (this.fAugmentations[i].equals(param1Object1)) {
          Object object = this.fAugmentations[i + 1];
          this.fAugmentations[i + 1] = param1Object2;
          return object;
        } 
      } 
      this.fAugmentations[this.fNumEntries * 2] = param1Object1;
      this.fAugmentations[this.fNumEntries * 2 + 1] = param1Object2;
      this.fNumEntries++;
      return null;
    }
    
    public Object removeItem(Object param1Object) {
      for (int i = 0; i < this.fNumEntries * 2; i += 2) {
        if (this.fAugmentations[i].equals(param1Object)) {
          Object object = this.fAugmentations[i + 1];
          for (int j = i; j < this.fNumEntries * 2 - 2; j += 2) {
            this.fAugmentations[j] = this.fAugmentations[j + 2];
            this.fAugmentations[j + 1] = this.fAugmentations[j + 3];
          } 
          this.fAugmentations[this.fNumEntries * 2 - 2] = null;
          this.fAugmentations[this.fNumEntries * 2 - 1] = null;
          this.fNumEntries--;
          return object;
        } 
      } 
      return null;
    }
    
    public void clear() {
      for (int i = 0; i < this.fNumEntries * 2; i += 2) {
        this.fAugmentations[i] = null;
        this.fAugmentations[i + 1] = null;
      } 
      this.fNumEntries = 0;
    }
    
    public boolean isFull() {
      return (this.fNumEntries == 10);
    }
    
    public AugmentationsImpl.AugmentationsItemsContainer expand() {
      AugmentationsImpl.LargeContainer largeContainer = new AugmentationsImpl.LargeContainer();
      for (int i = 0; i < this.fNumEntries * 2; i += 2)
        largeContainer.putItem(this.fAugmentations[i], this.fAugmentations[i + 1]); 
      return largeContainer;
    }
    
    public String toString() {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("SmallContainer - fNumEntries == ").append(this.fNumEntries);
      for (int i = 0; i < 20; i += 2) {
        stringBuffer.append("\nfAugmentations[");
        stringBuffer.append(i);
        stringBuffer.append("] == ");
        stringBuffer.append(this.fAugmentations[i]);
        stringBuffer.append("; fAugmentations[");
        stringBuffer.append(i + 1);
        stringBuffer.append("] == ");
        stringBuffer.append(this.fAugmentations[i + 1]);
      } 
      return stringBuffer.toString();
    }
    
    final class SmallContainerKeyEnumeration implements Enumeration {
      Object[] enumArray;
      
      int next;
      
      private final AugmentationsImpl.SmallContainer this$0;
      
      SmallContainerKeyEnumeration(AugmentationsImpl.SmallContainer this$0) {
        this.this$0 = this$0;
        this.enumArray = new Object[this.this$0.fNumEntries];
        this.next = 0;
        for (byte b = 0; b < this$0.fNumEntries; b++)
          this.enumArray[b] = this$0.fAugmentations[b * 2]; 
      }
      
      public boolean hasMoreElements() {
        return (this.next < this.enumArray.length);
      }
      
      public Object nextElement() {
        if (this.next >= this.enumArray.length)
          throw new NoSuchElementException(); 
        Object object = this.enumArray[this.next];
        this.enumArray[this.next] = null;
        this.next++;
        return object;
      }
    }
  }
  
  static abstract class AugmentationsItemsContainer {
    public abstract Object putItem(Object param1Object1, Object param1Object2);
    
    public abstract Object getItem(Object param1Object);
    
    public abstract Object removeItem(Object param1Object);
    
    public abstract Enumeration keys();
    
    public abstract void clear();
    
    public abstract boolean isFull();
    
    public abstract AugmentationsItemsContainer expand();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/util/AugmentationsImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */