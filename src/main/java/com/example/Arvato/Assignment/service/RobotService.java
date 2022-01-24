package com.example.Arvato.Assignment.service;

import com.example.Arvato.Assignment.enums.Facing;
import com.example.Arvato.Assignment.enums.Rotation;
import com.example.Arvato.Assignment.exception.APIException;
import com.example.Arvato.Assignment.exception.Table;
import com.example.Arvato.Assignment.model.Robot;
import com.example.Arvato.Assignment.repository.RobotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RobotService {

    private final RobotRepository robotRepository;
    @Autowired
    private Table table;
    private Facing facing;
    private int x;
    private int y;
    private boolean placed;


    /**
     * Places a robot on a table if it wasn't placed already and the coordinates
     * are valid
     *
     * @param newX to be used as X coordinate
     * @param newY to be used as Y coordinate
     * @param f    to face on
     */
    public void place(int newX, int newY, Facing f) throws APIException {
        if (table.isPositionValid(newX, newY)) {
            placed = true;
            x = newX;
            y = newY;
            facing = f;
            robotRepository.save(new Robot(x, y, facing));
        } else {
            throw new APIException("X And Y coordinates must be between " + 0 + " and " + 4);


        }
    }

    /**
     * Moves the robot in the facing direction if possible
     */
    public void move(Robot robot) throws APIException {
        if (!placed) {
            throw new APIException("Robot is not placed");
        }
        Facing facing = robot.getFacing();
        switch (facing) {
            case EAST:
                robot.setXCoord(robot.getXCoord() + 1);
                break;
            case WEST:
                robot.setXCoord(robot.getXCoord() - 1);
                break;
            case NORTH:
                robot.setYCoord(robot.getYCoord() + 1);
                break;
            case SOUTH:
                robot.setYCoord(robot.getYCoord() - 1);
                break;
        }

        if (!table.isPositionValid(robot.getXCoord(), robot.getYCoord())) {
            throw new APIException("Out of bounds movement!");
        }
        robotRepository.save(robot);
    }

    /**
     * Rotates the robot to the left
     */
    public void rotate(Robot robot, Rotation rotation) {
        Facing facing = robot.getFacing();
        switch (facing) {
            case EAST:
                if (rotation.equals(Rotation.LEFT)) {
                    robot.setFacing(Facing.NORTH);
                } else if (rotation.equals(Rotation.RIGHT)) {
                    robot.setFacing(Facing.SOUTH);
                }
                break;
            case WEST:
                if (rotation.equals(Rotation.LEFT)) {
                    robot.setFacing(Facing.SOUTH);
                } else if (rotation.equals(Rotation.RIGHT)) {
                    robot.setFacing(Facing.NORTH);
                }
                break;
            case NORTH:
                if (rotation.equals(Rotation.LEFT)) {
                    robot.setFacing(Facing.WEST);
                } else if (rotation.equals(Rotation.RIGHT)) {
                    robot.setFacing(Facing.EAST);
                }
                break;
            case SOUTH:
                if (rotation.equals(Rotation.LEFT)) {
                    robot.setFacing(Facing.EAST);
                } else if (rotation.equals(Rotation.RIGHT)) {
                    robot.setFacing(Facing.WEST);
                }
                break;
        }
        robotRepository.save(robot);
    }

    /**
     * Rotates the robot to the right
     */
    public String right() throws APIException {
        if (!placed) {
            throw new APIException("Robot is not placed");
        }
        switch (facing) {
            case NORTH:
                facing = Facing.EAST;
                break;
            case SOUTH:
                facing = Facing.WEST;
                break;
            case EAST:
                facing = Facing.SOUTH;
                break;
            case WEST:
                facing = Facing.NORTH;
                break;
        }
        return "Facing " + facing;
    }
    /**
     * Report the current cords and the facing
     */
    public String report() throws APIException {
        if (!placed) {
            throw new APIException("Robot is not placed");
        }
        return x + "," + y + "," + facing;
    }
}

