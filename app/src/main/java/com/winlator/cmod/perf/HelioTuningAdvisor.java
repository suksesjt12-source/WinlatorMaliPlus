package com.winlator.cmod.perf;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import java.util.Locale;

/**
 * Read-only hardware diagnostics and practical starting recommendations for
 * MediaTek Helio G99/G100 devices with Mali GPUs. This class never writes sysfs
 * or changes kernel thermal/frequency controls.
 */
public final class HelioTuningAdvisor {
    private static final double GIB = 1024.0 * 1024.0 * 1024.0;

    private HelioTuningAdvisor() {}

    public static String buildDiagnostics(Context context,
            HelioDeviceProfileManager.DeviceInfo info) {
        if (context == null || info == null) return "Device diagnostics unavailable";

        int logicalCores = Runtime.getRuntime().availableProcessors();
        String bigCores = CpuTopology.getBigCoreCpuList();
        String cluster = bigCores.isEmpty()
                ? "Big-core cluster: not verified (automatic pinning will be skipped)"
                : "Detected high-frequency cores: " + bigCores;

        String memory = "RAM information unavailable";
        try {
            ActivityManager manager = (ActivityManager)
                    context.getSystemService(Context.ACTIVITY_SERVICE);
            if (manager != null) {
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                manager.getMemoryInfo(memoryInfo);
                memory = String.format(Locale.ROOT, "RAM: %.1f GB total / %.1f GB available",
                        memoryInfo.totalMem / GIB, memoryInfo.availMem / GIB);
            }
        } catch (Throwable ignored) {}

        return "Hardware diagnostics\n"
                + info.chipsetLabel + "\n"
                + "GPU renderer: " + info.gpuRenderer + "\n"
                + "Logical CPU cores: " + logicalCores + "\n"
                + cluster + "\n"
                + memory + "\n"
                + "Android API: " + Build.VERSION.SDK_INT + "\n"
                + HelioDeviceProfileManager.getBatteryTemperatureLabel(context)
                + " · Thermal: "
                + HelioDeviceProfileManager.getThermalStatusLabel(context)
                + "\nBattery temperature is not the CPU/GPU junction temperature.";
    }

    public static String buildRecommendations(HelioDeviceProfileManager.DeviceInfo info) {
        String target = info != null && info.chipsetLabel != null
                && info.chipsetLabel.contains("G99") ? "Helio G99" : "Helio G99/G100-class";
        return "Recommended starting point for " + target + " + Mali-G57\n"
                + "• Runtime profile: Adaptive Gaming (thermal-aware)\n"
                + "• Resolution: 960×540; start with a 30 FPS cap for demanding 3D games\n"
                + "• Box64: Performance for faster startup/CPU throughput; use Intermediate "
                + "when a long session becomes unstable or too warm\n"
                + "• Graphics: Vortex for the Mali/Ludashi setup when the game is compatible\n"
                + "• DXVK: 1.10.3 Async as a starting point; switch to non-Async if a game "
                + "has visual glitches or instability\n"
                + "• VSync: Off for lower input latency; enable it only to address visible tearing\n"
                + "• VRAM limit: begin at 2048 MB where the wrapper exposes this option\n"
                + "• Resolution scaling: try 1024×576 only after 960×540 is stable; use 1280×720 "
                + "for light games or after testing\n"
                + "• Keep the Android thermal service enabled. The adaptive guard releases "
                + "big-core affinity at MODERATE thermal status and restores boosted thread "
                + "priorities at SEVERE status.\n\n"
                + "These are starting values, not a guaranteed FPS result. Game engine, "
                + "driver, wrapper, RAM pressure, and device cooling determine the final result.";
    }
}
