package org.github.ybqdren.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/17 17:14
 * @package org.github.ybqdren.entity
 * @description
 **/

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotNull
    @Id
    private String id;

    @NotNull
    private String name;

    private int sex;

    @Email
    private String emailAddress;
}

