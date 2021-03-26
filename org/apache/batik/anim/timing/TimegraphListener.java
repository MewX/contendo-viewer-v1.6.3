package org.apache.batik.anim.timing;

public interface TimegraphListener {
  void elementAdded(TimedElement paramTimedElement);
  
  void elementRemoved(TimedElement paramTimedElement);
  
  void elementActivated(TimedElement paramTimedElement, float paramFloat);
  
  void elementFilled(TimedElement paramTimedElement, float paramFloat);
  
  void elementDeactivated(TimedElement paramTimedElement, float paramFloat);
  
  void intervalCreated(TimedElement paramTimedElement, Interval paramInterval);
  
  void intervalRemoved(TimedElement paramTimedElement, Interval paramInterval);
  
  void intervalChanged(TimedElement paramTimedElement, Interval paramInterval);
  
  void intervalBegan(TimedElement paramTimedElement, Interval paramInterval);
  
  void elementRepeated(TimedElement paramTimedElement, int paramInt, float paramFloat);
  
  void elementInstanceTimesChanged(TimedElement paramTimedElement, float paramFloat);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/TimegraphListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */