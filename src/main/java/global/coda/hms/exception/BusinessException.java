package global.coda.hms.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * To manage business exception .
 */
public final class BusinessException extends Exception implements ExceptionMapper<Exception> {
    /**
     * exception .
     *
     * @param e e
     */
    public BusinessException(Exception e) {
        super(e);
    }

    @Override
    public Response toResponse(Exception exception) {
        return null;
    }
}
