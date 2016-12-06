package com.booking.util;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.commons.lang3.StringUtils.leftPad;

import java.text.NumberFormat;
import java.util.function.Predicate;

import com.booking.structure.logging.Logger;
import com.google.common.base.Stopwatch;

import net.serenitybdd.core.time.InternalSystemClock;
import net.serenitybdd.core.time.SystemClock;

public class WaitUtil {

    private static final SystemClock CLOCK = new InternalSystemClock();

    private WaitUtil() {
    }

    public static void waitABit(final long timeInMilliseconds) {
        CLOCK.pauseFor(timeInMilliseconds);
    }

    public static <T> void waitFor(final Predicate<T> condition, final T arg, final long waitTimeout,
            final long waitInterval) {
        final Stopwatch waitTimer = Stopwatch.createStarted();
        for (long secs = 0; secs <= waitTimeout; secs += waitInterval) {
            waitABit(waitInterval);
            if (condition.test(arg) || (waitTimer.elapsed(MILLISECONDS) > waitTimeout)) {
                break;
            } else {
                debugElapsedTime(waitTimer.elapsed(MILLISECONDS));
            }
        }
    }

    private static void debugElapsedTime(final long millis) {
        final long logMillis = Math.round(millis / 1000) * 1000;
        Logger.out.debug("%s ms waiting...", leftPad(String.valueOf(NumberFormat.getInstance().format(logMillis)), 6));
    }
}
