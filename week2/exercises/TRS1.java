package week2.exercises;

public class TRS1 {
  // 取输入整数每一位的数字相加，直到和小于10
  // 12346，每位数字相加 1+2+3+4+6=16，继续每位相加，1+6=7，返回7
  public int addDigits(int n) {
    int sum = 0;
    while(n > 0) {
      sum += n % 10;
      n = n / 10;
    }
    if(sum < 10) {
      return sum;
    }
    return addDigits(sum);
  }

  public int addDigtisIter(int n) {
    int sum = sumDigit(n);
    while(sum > 10) {
      sum = sumDigit(sum);
    }
    return sum;
  }

  // 返回每一位数字的相加的和
  private int sumDigit(int n) {
    if(n < 10) {
      return n;
    }
    int sum = 0;
    while(n > 0) {
      sum += n % 10;
      n = n / 10;
    }
    return sum;
  }


  public class IntList {
    public int first;
    public IntList rest;

    /** Nondestructively reverses IntList L. */
    public static IntList reverseNondestructive(IntList l) {
      TRS1 s1 = new TRS1();
      IntList newList = s1.new IntList(l.first, null);
      while(l.rest != null) {
        l = l.rest;
        newList = s1.new IntList(l.first, newList);
      }

      return newList;
    }

    /** Destructively reverses IntList L using recursion. */
    public static IntList reverseDestructive(IntList l) {
      IntList prev = null;
      IntList curr = l;
      while(curr != null) {
        // 暂存下一个
        IntList temp = curr.rest;
        // 将当前节点的指向从下一个改为上一个
        curr.rest = prev;
        // 保存当前节点为下一次替换的上一个
        prev = curr;
        curr = temp;
      }
      return prev;
    }

    /** Destructively reverses IntList L using recursion. */
    public static IntList reverseDestructiveRecur(IntList L) {
      if (L == null || L.rest == null) {
        return L;
      } else {
        IntList reversed = reverseDestructive(L.rest);
        L.rest.rest = L;
        L.rest = null;
        return reversed;
      }
    }

    public IntList(int f, IntList r) {
      first = f;
      rest = r;
    }

    public String toString() {
      if(this.rest == null) {
        return "" + this.first;
      }
      return this.first + " -> " + this.rest.toString();
    }

    // IntList A = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    // After calling A.skippify(), A: (1, 3, 6, 10)
    public void skippify() {
      IntList p = this;
      int n = 1;
      while (p != null) { 
        IntList next = p;
        for(int i=0; i<n; i++) {
          if(next != null) {
            next = next.rest;
          }
        }
        p.rest = next.rest;
        p = p.rest;
        n += 1;
      }
    }
  
  
    // Destructively shifts the elements of the given IntList L to the
    // left by one position. Returns the first node in the shifted list.
    // original list (5 → 4 → 9 → 1 → 2 → 3); return the list (4 → 9 → 1 → 2 → 3 → 5).
    public static IntList shiftListDestructive(IntList L) {
      IntList head = L;
      IntList p = L;
      IntList returnedNode = L.rest;
      while(p.rest != null) {
        p = p.rest;
      }
      p.rest = head;
      head.rest = null;
      return returnedNode;
    }
  }


  public static void main(String[] args) {
    TRS1 s1 = new TRS1();
    // System.out.println(s1.addDigits(12348));
    // System.out.println(s1.addDigtisIter(12348));

    IntList l1 = s1.new IntList(1, null);
    l1 = s1.new IntList(2, l1);
    l1 = s1.new IntList(3, l1);
    l1 = s1.new IntList(4, l1);
    l1 = s1.new IntList(5, l1);
    l1 = s1.new IntList(6, l1);
    l1 = s1.new IntList(7, l1);
    l1 = s1.new IntList(8, l1);
    l1 = s1.new IntList(9, l1);

    System.out.println(l1);
    l1 = IntList.reverseDestructiveRecur(l1);
    System.out.println(l1);
    // l1.skippify();
    System.out.println(IntList.shiftListDestructive(l1));
    // IntList l2 = IntList.reverseDestructive(l1);
    // System.out.println(l2);
  }


}