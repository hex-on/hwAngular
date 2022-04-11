package com.example.homeworktwo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks", schema = "public")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private Long employeeId;
    private int status; //0 = not done,1 = in progress, 2 = done
    private int progress; // if status == 1 then progress is %
    private String title;
    private String description;

}
