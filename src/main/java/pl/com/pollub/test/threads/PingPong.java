package pl.com.pollub.test.threads;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.infra.Control;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mmaciasz on 2017-05-09.
 */
@State(Scope.Group)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Threads(2)
public class PingPong {

    private static final String pingPong = "pingPong";
    private static final String intPingPong = "intPingPong";

    private final AtomicBoolean boolFlag = new AtomicBoolean();
    private final AtomicInteger intFlag = new AtomicInteger();

    private static void doNothing() {
    }

    @Benchmark
    @Group(pingPong)
    public void ping(Control cnt) {
        while (!cnt.stopMeasurement && !boolFlag.compareAndSet(false, true)) {
            doNothing();
        }
    }

    @Benchmark
    @Group(pingPong)
    public void pong(Control cnt) {
        while (!cnt.stopMeasurement && !boolFlag.compareAndSet(true, false)) {
            doNothing();
        }
    }

    @Benchmark
    @Group(intPingPong)
    public void intPing(Control cnt) {
        while (!cnt.stopMeasurement && !intFlag.compareAndSet(0, 1)) {
            doNothing();
        }
    }

    @Benchmark
    @Group(intPingPong)
    public void intPong(Control cnt) {
        while (!cnt.stopMeasurement && !intFlag.compareAndSet(1, 0)) {
            doNothing();
        }
    }
}
