package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@AllArgsConstructor
@RestController
@Api(value="ReservationController", description="APIs related to Reservation")
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @ApiOperation(value="Book Reservation for a TennisCourt")
    @ApiResponses(value={
    		@ApiResponse(code=201, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/bookReservation", method=RequestMethod.POST)
    public ResponseEntity<Void> bookReservation(CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @ApiOperation(value="Find Reservation for a TennisCourt")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/findReservation/{id}", method=RequestMethod.GET)
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable(value="id") Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @ApiOperation(value="Find Reservation for a TennisCourt")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/cancelReservation/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable(value="id") Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @ApiOperation(value="Update Reservation for a TennisCourt")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/updateReservation/{reservationId}/{scheduleId}", method=RequestMethod.PUT)
    public ResponseEntity<ReservationDTO> rescheduleReservation(@PathVariable(value="reservationId") Long reservationId, @PathVariable(value="scheduleId") Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
