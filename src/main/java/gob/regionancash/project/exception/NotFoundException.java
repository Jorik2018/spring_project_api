package gob.regionancash.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource not found exception.
 *
 * @author Givantha Kalansuriya
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

  /**
   * Instantiates a new Resource not found exception.
   *
   * @param message the message
   */
  public NotFoundException(String message) {
    super(message);
  }
}