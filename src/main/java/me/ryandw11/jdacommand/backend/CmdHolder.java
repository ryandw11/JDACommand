package me.ryandw11.jdacommand.backend;

import java.lang.reflect.Method;

/**
 * Holds the Object and Method associated with a command.
 *
 * <p>For internal use only.</p>
 */
public class CmdHolder {

    private final Method mtd;
    private final Object obj;

    public CmdHolder(Method mtd, Object obj) {
        this.mtd = mtd;
        this.obj = obj;
    }

    public Method getMethod() {
        return mtd;
    }

    public Object getObject() {
        return obj;
    }

}
