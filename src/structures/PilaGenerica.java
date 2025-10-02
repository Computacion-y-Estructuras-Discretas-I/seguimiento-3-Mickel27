package structures;

import java.lang.IndexOutOfBoundsException;
import java.lang.StackOverflowError;

public class PilaGenerica<T> {
  private T[] arreglo;
  private int top;
  private int size;

  public PilaGenerica(int n) {
    this.arreglo = (T[]) new Object[n];
    this.size = n;
  }

  public void Push(T x) throws IndexOutOfBoundsException {
    if(this.top < this.size) {
      this.arreglo[top] = x;
      this.top++;
    } else {
      throw new StackOverflowError();
    }
  }

  public T Pop() throws IndexOutOfBoundsException {
    if(this.top > 0) {
      this.top--;
      return this.arreglo[top];
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public T[] getArreglo() {
    return arreglo;
  }

  public boolean isEmpty() {
    return top == 0;
  }

  public int getTop() {
    return top;
  }

  public int getSize() {
    return size;
  }

  public void setArreglo(T[] arreglo) {
    this.arreglo = arreglo;
  }

  public void setTop(int top) {
    this.top = top;
  }

  public void setSize(int size) {
    this.size = size;
  }
}