package org.mahabal.optigrader.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Session {

    public String nid;
    public String ip;
    public String token;

}
