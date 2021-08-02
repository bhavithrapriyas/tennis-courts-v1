package main.java.com.tenniscourts.schedules;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import lombok.AllArgsConstructor;
import main.java.com.tenniscourts.config.BaseRestController;

@AllArgsConstructor
@RestController
@Api(value="ScheduleController", description="APIs related to Tenniscourt schedule")
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;

    //TODO: implement rest and swagger
    @ApiOperation(value="add TennisCourt schedules")
    @ApiResponses(value={
    		@ApiResponse(code=201, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/addSchedule", method=RequestMethod.POST)
    public ResponseEntity<Void> addScheduleTennisCourt(@RequestBody CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    //TODO: implement rest and swagger
    @ApiOperation(value="find TennisCourt schedules by Date")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/findScheduleByDate", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@RequestParam(name="startDate") LocalDate startDate,
    																@RequestParam(name="endDate") LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    //TODO: implement rest and swagger
    @ApiOperation(value="find TennisCourt schedules by ID")
    @ApiResponses(value={
    		@ApiResponse(code=200, message="Success")
    		@ApiResponse(code=403, message="Forbidden")
    		@ApiResponse(code=500, message="Internal Server Error")
    		@ApiResponse(code=404, message="Not found")
    })
    @RequestMapping(value="/findScheduleById/{id}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ScheduleDTO> findByScheduleId(@PathVariable(value="id") Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
