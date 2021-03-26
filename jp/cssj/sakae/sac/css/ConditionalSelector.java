package jp.cssj.sakae.sac.css;

public interface ConditionalSelector extends SimpleSelector {
  SimpleSelector getSimpleSelector();
  
  Condition getCondition();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/ConditionalSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */