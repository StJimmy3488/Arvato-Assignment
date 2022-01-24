package com.example.Arvato.Assignment.controller;

import com.example.Arvato.Assignment.enums.Facing;
import com.example.Arvato.Assignment.enums.Rotation;
import com.example.Arvato.Assignment.exception.APIException;
import com.example.Arvato.Assignment.exception.MissingRobotException;
import com.example.Arvato.Assignment.model.Robot;
import com.example.Arvato.Assignment.repository.RobotRepository;
import com.example.Arvato.Assignment.service.RobotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class RobotController {
    @Autowired
    private final RobotService robotService;
    @Autowired
    private RobotRepository robotRepository;

    @RequestMapping("/place")
    public String place(@RequestParam(value = "x") int x, @RequestParam(value = "y") int y, @RequestParam(value = "f") Facing f) throws APIException {
        robotService.place(x, y, f);
        return robotService.report();

    }

    @GetMapping(path = "{robotId}/move")
    public String move(@PathVariable(value = "robotId") Long robotId) throws APIException, MissingRobotException {
        Optional<Robot> byId = robotRepository.findById(robotId);
        if (byId.isEmpty()) {
            throw new MissingRobotException("Robot is missing");
        }
        robotService.move(byId.get());
        return robotService.report();
    }


    @GetMapping(path = "{robotId}/left")
    public String rotateLeft(@PathVariable(value = "robotId") Long robotId) throws MissingRobotException, APIException {
        Optional<Robot> byId = robotRepository.findById(robotId);
        if (byId.isEmpty()) {
            throw new MissingRobotException("Robot is missing");
        }
        robotService.rotate(byId.get(), Rotation.LEFT);
        return robotService.report();
    }

    @GetMapping(path = "{robotId}/right")
    public String rotateRight(@PathVariable(value = "robotId") Long robotId) throws MissingRobotException, APIException {
        Optional<Robot> byId = robotRepository.findById(robotId);
        if (byId.isEmpty()) {
            throw new MissingRobotException("Robot is missing");
        }
        robotService.rotate(byId.get(), Rotation.RIGHT);
        return robotService.report();
    }

    @RequestMapping("/report")
    public String report() throws APIException {
        return robotService.report();
    }

}
