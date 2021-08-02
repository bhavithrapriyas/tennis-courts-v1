package main.java.com.tenniscourts.guests;

import main.java.com.tenniscourts.config.BaseRestController;
import main.java.com.tenniscourts.guests.GuestDTO;
import main.java.com.tenniscourts.guests.GuestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Api(value="GuestController", description="APIs related to Guests")
public class GuestController extends BaseRestController {

    private final GuestService guestService;

    @ApiOperation(value="add Guest")
    @ApiResponses(value={
    		@ApiResponse(code=201, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/addGuest", method=RequestMethod.POST)
    public ResponseEntity<Void> addGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(guestDTO).getId())).build();
    }

    @ApiOperation(value="update Guest")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/updateGuest", method=RequestMethod.PUT)
    public ResponseEntity<Void> updateGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.updateGuest(guestDTO).getId())).build();
    }

    @ApiOperation(value="delete Guest")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/deleteGuest/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteGuest(@PathVariable(value="id") Long guestId) {
        guestService.deleteGuest(guestId);
        return ResponseEntity.ok("Deleted successfully");
    }

    @ApiOperation(value="find Guest By ID")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/findGuest/{id}", method=RequestMethod.GET)
    public ResponseEntity<GuestDTO> findGuestById(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(guestService.findGuestById(id));
    }

    @ApiOperation(value="find Guest By Name")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/findGuest/{name}", method=RequestMethod.GET)
    public ResponseEntity<List<GuestDTO>> findGuestByName(@PathVariable(value="name") String name) {
        return ResponseEntity.ok(guestService.findGuestByName(name));
    }

    @ApiOperation(value="find list of all guests")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/findGuest", method=RequestMethod.GET)
    public ResponseEntity<List<GuestDTO>> findAllGuests() {
        return ResponseEntity.ok(guestService.findGuestAll());
    }
}
