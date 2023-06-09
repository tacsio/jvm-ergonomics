package io.tacsio.jvm.tuning;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.util.HashMap;
import java.util.Map;

@RestController
public final class InfoController {

    @GetMapping("/info")
    public ResponseEntity<?> info() {
        var response = new HashMap<>();

        var runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        var memoryMXBean = ManagementFactory.getMemoryMXBean();
        var gcMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        var osMXBean = ManagementFactory.getOperatingSystemMXBean();
        var hulkOsMXBean = (com.sun.management.OperatingSystemMXBean) osMXBean;

//        gcMXBeans.stream().forEach(gc -> {
//            System.out.println("""
//                    gc: %s
//                    count: %s
//                    """.formatted(gc.getName(), gc.getCollectionCount()));
//        });

        response.put("pid", runtimeMXBean.getPid());
        response.put("jvmArgs", runtimeMXBean.getInputArguments());
        response.put("availableProcessors", osMXBean.getAvailableProcessors());
        response.put("cpu", hulkOsMXBean.getCpuLoad() * 100);
        response.put("gc", Map.of(
                "type", gcMXBeans.stream().map(MemoryManagerMXBean::getName),
                "executions", gcMXBeans.stream().map(GarbageCollectorMXBean::getCollectionCount).reduce(0L, Long::sum)
        ));

        response.put("memory", Map.of(
                "OsFreeMemory", bytesToMBString(hulkOsMXBean.getFreeMemorySize()),
                "OsTotalMemory", bytesToMBString(hulkOsMXBean.getTotalMemorySize()),
                "JvmHeap", Map.of(
                        "init", bytesToMBString(memoryMXBean.getHeapMemoryUsage().getInit()),
                        "used", bytesToMBString(memoryMXBean.getHeapMemoryUsage().getUsed()),
                        "committed", bytesToMBString(memoryMXBean.getHeapMemoryUsage().getCommitted()),
                        "max", bytesToMBString(memoryMXBean.getHeapMemoryUsage().getMax()))
//                ,"JvmNonHeap", Map.of(
//                        "init", bytesToMBString(memoryMXBean.getNonHeapMemoryUsage().getInit()),
//                        "used", bytesToMBString(memoryMXBean.getNonHeapMemoryUsage().getUsed()),
//                        "committed", bytesToMBString(memoryMXBean.getNonHeapMemoryUsage().getCommitted()),
//                        "max", bytesToMBString(memoryMXBean.getNonHeapMemoryUsage().getMax()))
        ));

        return ResponseEntity.ok(response);
    }

    private String bytesToMBString(long bytes) {
        return Long.toString(bytes / 1024 / 1024) + " MB";
    }
}
