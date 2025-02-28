/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.scheidtbachmann.phfa.digitalproduct.zrconnector.api;

import com.scheidtbachmann.phfa.digitalproduct.zrconnector.model.ResponseCodeDTO;
import com.scheidtbachmann.phfa.digitalproduct.zrconnector.model.VersionDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
@Validated
@Api(value = "version", description = "the version API")
public interface VersionApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /version : Provide version of service
     *
     * @param xSampBSelfCheckoutApp  (required)
     * @param correlationId  (optional)
     * @return successful operation (status code 200)
     *         or NOT authorized !!! (status code 401)
     *         or ERROR (status code 200)
     */
    @ApiOperation(value = "Provide version of service", nickname = "getVersion", notes = "", response = VersionDTO.class, tags={ "Version", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = VersionDTO.class),
        @ApiResponse(code = 401, message = "NOT authorized !!!"),
        @ApiResponse(code = 200, message = "ERROR", response = ResponseCodeDTO.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/version",
        produces = { "application/json" }
    )
    default ResponseEntity<VersionDTO> getVersion(@ApiParam(value = "", required = true, defaultValue = "SampB-SelfCheckout-2022") @RequestHeader(value = "X-SampB-SelfCheckout-App", required = true) String xSampBSelfCheckoutApp,@ApiParam(value = "") @RequestHeader(value = "correlationId", required = false) String correlationId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"service-version\" : \"1.0.0\", \"interface-version\" : \"0.1.6\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
