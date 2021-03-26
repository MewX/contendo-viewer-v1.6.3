package org.w3c.css.sac;

public interface ConditionalSelector extends SimpleSelector {
  SimpleSelector getSimpleSelector();
  
  Condition getCondition();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/ConditionalSelector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */