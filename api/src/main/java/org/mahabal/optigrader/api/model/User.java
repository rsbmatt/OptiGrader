package org.mahabal.optigrader.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Matthew
 */
@NoArgsConstructor
@Data
public class User {

    public String nid;
    public int user_mode;
    public String current_test;
    public int returned_score;
    public String returned_answers;
    public long dateCreated;
    public String firstName;
    public String lastName;
    public String login;
    public String password;

}
