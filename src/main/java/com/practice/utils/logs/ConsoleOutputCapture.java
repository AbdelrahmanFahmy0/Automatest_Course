package com.practice.utils.logs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Captures console output for the current thread without suppressing normal console output.
 * <p>
 * Each thread has an independent buffer, allowing TestNG to associate stdout and stderr
 * produced during one test with that test's Allure result. Output written by other threads
 * is never added to the current thread's buffer.
 */
public final class ConsoleOutputCapture {

    private static final ThreadLocal<ByteArrayOutputStream> CAPTURED_OUTPUT = new ThreadLocal<>();
    private static boolean installed;

    private ConsoleOutputCapture() {
    }

    /**
     * Installs delegating wrappers around {@link System#out} and {@link System#err}.
     * <p>
     * This method is idempotent and must be called before test execution begins. The wrappers
     * continue writing every message to the original console streams and copy only output from
     * an active capture into that thread's buffer.
     */
    public static synchronized void install() {
        if (installed) {
            return;
        }

        System.setOut(new PrintStream(new CapturingOutputStream(System.out), true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(new CapturingOutputStream(System.err), true, StandardCharsets.UTF_8));
        installed = true;
    }

    /**
     * Starts a new capture session for the current thread.
     * <p>
     * Any previously captured output on this thread is discarded. Invoke this before
     * {@code @BeforeMethod} so setup and test-method output are collected together.
     */
    public static void startCapture() {
        CAPTURED_OUTPUT.set(new ByteArrayOutputStream());
    }

    /**
     * Ends the current thread's capture session and returns its output.
     * <p>
     * Removing the thread-local buffer prevents logs from one test being reused by the next
     * test executed on the same TestNG worker thread.
     *
     * @return captured stdout and stderr text, or an empty string when no capture is active
     */
    public static String stopCapture() {
        ByteArrayOutputStream output = CAPTURED_OUTPUT.get();
        CAPTURED_OUTPUT.remove();
        return output == null ? "" : output.toString(StandardCharsets.UTF_8);
    }

    private static final class CapturingOutputStream extends OutputStream {

        private final PrintStream delegate;

        private CapturingOutputStream(PrintStream delegate) {
            this.delegate = delegate;
        }

        @Override
        public void write(int value) {
            delegate.write(value);
            capture(new byte[]{(byte) value}, 0, 1);
        }

        @Override
        public void write(byte[] buffer, int offset, int length) {
            delegate.write(buffer, offset, length);
            capture(buffer, offset, length);
        }

        @Override
        public void flush() {
            delegate.flush();
        }

        @Override
        public void close() throws IOException {
            flush();
        }

        private void capture(byte[] buffer, int offset, int length) {
            ByteArrayOutputStream output = CAPTURED_OUTPUT.get();
            if (output != null) {
                output.write(buffer, offset, length);
            }
        }
    }
}