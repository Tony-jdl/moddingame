package it.moddingame.rest.exception;

import it.moddingame.rest.api.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by p.b on 20/04/2017 11:10
 */
@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = {ApplicationException.class, ServiceException.class})
    public ResponseEntity<BaseResponse> resourceException(ApplicationException ex) {
        BaseResponse response = new BaseResponse();
        response.setStato(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        response.setDescrizione(ex.getMessage());
        return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
