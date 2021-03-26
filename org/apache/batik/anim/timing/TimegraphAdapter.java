package org.apache.batik.anim.timing;

public class TimegraphAdapter implements TimegraphListener {
  public void elementAdded(TimedElement e) {}
  
  public void elementRemoved(TimedElement e) {}
  
  public void elementActivated(TimedElement e, float t) {}
  
  public void elementFilled(TimedElement e, float t) {}
  
  public void elementDeactivated(TimedElement e, float t) {}
  
  public void intervalCreated(TimedElement e, Interval i) {}
  
  public void intervalRemoved(TimedElement e, Interval i) {}
  
  public void intervalChanged(TimedElement e, Interval i) {}
  
  public void intervalBegan(TimedElement e, Interval i) {}
  
  public void elementRepeated(TimedElement e, int i, float t) {}
  
  public void elementInstanceTimesChanged(TimedElement e, float isBegin) {}
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/TimegraphAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */