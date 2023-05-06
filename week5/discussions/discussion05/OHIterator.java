package discussion05;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OHIterator implements Iterator<OHRequest> {

  private OHRequest curr;

  public OHIterator(OHRequest queue) {
    curr = queue;
  }

  public static boolean isGood(String description) { 
    return description.length() >= 5; 
  }

  @Override
  public boolean hasNext() {
    while(curr != null && !isGood(curr.description)) {
        curr = curr.next;
    }
    if(curr == null) {
        return false;
    }
    return true;
  }

  @Override
  public OHRequest next() {
    if(!hasNext()) {
        throw new NoSuchElementException("no good element");
    }
    OHRequest returnReq = curr;
    curr = curr.next;
    return returnReq;
  }

}
