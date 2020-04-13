package com.wqy.wx.back.configer.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

public class LogColor extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        Level level = event.getLevel();
        switch (level.toInt()) {
            case Level.ERROR_INT:
                return ANSIConstants.RED_FG;
            case Level.WARN_INT:
                return ANSIConstants.YELLOW_FG;
            case Level.INFO_INT:
                return ANSIConstants.WHITE_FG;
            case Level.DEBUG_INT:
                return ANSIConstants.GREEN_FG;
            case Level.TRACE_INT:
                return ANSIConstants.DEFAULT_FG;
            default:
                return ANSIConstants.DEFAULT_FG;
        }
    }

}
