package com.example.Arvato.Assignment.model;

import com.example.Arvato.Assignment.enums.Facing;
import lombok.Data;
import javax.persistence.*;


@Data
@Entity
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "robot_id")
    private Long robotId;
    @Column(name = "robot_coord_x")
    private int xCoord;

    @Column(name = "robot_coord_y")
    private int yCoord;

    @Column(name = "facing")
    @Enumerated(EnumType.STRING)
    private Facing facing;

    public Robot() {
    }

    public Robot(int xCoord, int yCoord, Facing facing) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.facing = facing;
    }
}
