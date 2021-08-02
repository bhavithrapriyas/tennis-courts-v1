package main.java.com.tenniscourts.tenniscourts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import lombok.AllArgsConstructor;
import main.java.com.tenniscourts.config.BaseRestController;

@AllArgsConstructor
@RestController
@Api(value="TennisCourtController", description="APIs related to Tenniscourt")
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;

    //TODO: implement rest and swagger
    @ApiOperation(value="add Tennis Court")
    @ApiResponses(value={
    		@ApiResponse(code=201, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/addTennisCourt", method=RequestMethod.POST)
    public ResponseEntity<Void> addTennisCourt(TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    //TODO: implement rest and swagger
    @ApiOperation(value="find Tennis Court by ID")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/findTennisCourtById/{id}", method=RequestMethod.GET)
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(@PathVariable(value="id") Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }

    //TODO: implement rest and swagger
    @ApiOperation(value="find Tennis Court with Schedules by ID")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/findTennisCourtWithSchedulesById/{id}", method=RequestMethod.GET)
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(@PathVariable(value="id") Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
