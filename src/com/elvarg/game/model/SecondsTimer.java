package com.elvarg.game.model;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

/**
 * Represents a timer in seconds.
 * @author Professor Oak
 */
public class SecondsTimer {

    /**
     * The amount of seconds to count down.
     */
    private int seconds;

    /**
     * The actual timer.
     */
    private final Stopwatch stopwatch;

    /**
     * Constructs a new timer.
     */
    public SecondsTimer() {
    	this.stopwatch = Stopwatch.createUnstarted();
    	this.seconds = 0;
    }

    /**
     * Constructs a new timer and
     * starts it immediately.
     *
     * @param seconds The amount of seconds to
     */
    public SecondsTimer(int seconds) {
    	this();
        start(seconds);
    }

    /**
     * Starts this timer.
     *
     * @param seconds The amount of seconds.
     */
    public void start(int seconds) {
        this.seconds = seconds;

        //Reset and then start the stopwatch.
        stopwatch.reset();
        stopwatch.start();
    }

    /**
     * Stops this timer
     */
    public void stop() {
    	seconds = 0;
    	if (stopwatch.isRunning()) {
    		stopwatch.reset();
    	}
    }

    /**
     * Gets the amount of seconds remaining
     * before this timer has reached 0.
     *
     * @return The seconds remaining.
     */
    public int secondsRemaining() {
    	if (seconds == 0) {
    		return 0;
    	}
    	int remaining = seconds - secondsElapsed();
    	if (remaining < 0) {
    		remaining = 0;
    	}
        return remaining;
    }

    /**
     * Checks if this timer has finished
     * counting down, basically reaching 0.
     *
     * @return true if finished, false otherwise.
     */
    public boolean finished() {
    	if (secondsRemaining() == 0) {
    		stop();
    		return true;
    	}
        return false;
    }
    
    /**
     * Gets the amount of seconds that have elapsed
     * since the timer was started.
     *
     * @return The seconds elapsed.
     */
    public int secondsElapsed() {
        return (int) stopwatch.elapsed(TimeUnit.MILLISECONDS) / 1000;
    }
}
