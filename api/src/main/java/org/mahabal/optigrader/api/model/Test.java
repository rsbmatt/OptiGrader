package org.mahabal.optigrader.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Matthew
 */
@NoArgsConstructor
@Data
public class Test {

    public String testName;
    public String code;
    public String solutions;
    public String testOwner;
    public long expiration;

}
