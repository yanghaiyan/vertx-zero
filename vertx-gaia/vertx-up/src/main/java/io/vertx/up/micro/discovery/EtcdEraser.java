package io.vertx.up.micro.discovery;

import io.vertx.servicediscovery.Record;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.tool.mirror.Instance;

import java.util.concurrent.ConcurrentMap;

/**
 * Delete/Clean Up the service/ipc node that are not OK
 */
public class EtcdEraser {

    private static EtcdEraser INSTANCE;

    public static EtcdEraser create() {
        if (null == INSTANCE) {
            INSTANCE = new EtcdEraser();
        }
        return INSTANCE;
    }

    public void start() {
        // Services
        Runner.run(this::eraseApi, "endpoint-eraser");
        // Ipc
        Runner.run(this::eraseIpc, "ipc-eraser");
    }

    private void eraseApi() {
        final Origin origin = Instance.singleton(ApiOrigin.class);
        final ConcurrentMap<String, Record> records = origin.getRegistryData();
        records.forEach((key, value) -> origin.erasing(value));
    }

    private void eraseIpc() {
        final Origin origin = Instance.singleton(IpcOrigin.class);
        final ConcurrentMap<String, Record> records = origin.getRegistryData();
        records.forEach((key, value) -> origin.erasing(value));
    }
}