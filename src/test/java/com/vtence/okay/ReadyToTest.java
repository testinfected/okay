package com.vtence.okay;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReadyToTest {
    boolean readyToTest = true;

    @Test public void
    wereReadyToTest() {
        assertThat(readyToTest, is(true));
    }
}
