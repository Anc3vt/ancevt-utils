package ru.ancevt.util;

import org.apache.log4j.Logger;

/**
 *
 * @author ancevt
 */
public class Tracer {
    public static Logger logger;
    
    public static void trace(Object o) {
        logger.trace(o);
    }
}
