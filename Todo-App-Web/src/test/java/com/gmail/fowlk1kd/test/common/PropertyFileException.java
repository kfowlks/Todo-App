package com.gmail.fowlk1kd.test.common;

public class PropertyFileException extends Exception {

  static final long serialVersionUID = -400708519743154784L;
  
  
  /**
   * Constructs a PropertyFileException with the specified detail message.
   * The string msg may later be retrieved by the getMessage() method.
   *
   * @param  msg  Text message describing the error condition that occurred.
   */
  public PropertyFileException( String msg )
  {
      super(msg);
  }
}
