package global.coda.hms.exception.mapper;

import global.coda.hms.constant.HttpStatusConstant;
import global.coda.hms.exception.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * The type Business exception mapper.
 */
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
    private static final Logger LOGGER = LogManager.getLogger(BusinessExceptionMapper.class);

    /**
     * Exception Mapper .
     *
     * @param exception exception
     * @return Response
     */
    @Override
    public Response toResponse(BusinessException exception) {
        LOGGER.info(exception.getMessage());
        return Response.status(HttpStatusConstant.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity(exception.getMessage()).build();
    }
}
