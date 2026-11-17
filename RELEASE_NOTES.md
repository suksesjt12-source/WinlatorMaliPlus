# Winlator Mali+ Local Integration Update

## Helio G99/G100 tuning suite — 1.4.0

- Added an Adaptive Gaming runtime profile for Helio G99/G100-class devices.
- Added Android thermal-status handling: guest big-core affinity is released at MODERATE status; guest thread-priority boosts are restored at SEVERE status and can return after recovery.
- Added device diagnostics for SoC/GPU identification, logical core count, readable high-frequency core cluster, RAM availability, battery temperature, and Android thermal status.
- Added a Helio G99 starting-preset helper for Box64, Vortex, DXVK, resolution, frame-rate cap, VSync, and wrapper memory settings.
- Removed the guessed upper-half CPU-core fallback. Big-core pinning is skipped unless the higher-frequency cluster can be identified from readable CPU frequency data.
- Android thermal management remains enabled. No CPU/GPU voltage or frequency is forced, and no thermal limit is bypassed.

## Validation scope

- The Ludashi debug APK is compiled by the pull-request workflow.
- Runtime behavior and actual game FPS still require physical-device testing on Helio G99/G100 hardware.

---

#This source update brings the local Winlator Mali+ integration forward with Vulkan and shortcut-library improvements while preserving the upstream EGL/Gladio and ZeroFG paths.

## Highlights

- Optional Vulkan host renderer with Vulkan-specific display settings, native rendering support, and LSFG/Win-FG controls.
- Online Bannerlator wrapper catalog with wrapper selection and archive validation.
- Live PanVK release list in **Adrenotools GPU Drivers**. Refreshes the latest releases from three GitHub repositories, filters for supported Adrenotools ZIP packages, verifies downloads, and imports them directly.
- PanVK release entries show repository owner, version, publication date, package size, and reported device/GPU compatibility.
- Shortcut artwork and library improvements, including full-cover game cards, per-game cover handling, and direct executable shortcut creation with container selection.
- Animated PlayStation-inspired background and liquid-glass styling across app and in-game menus.

## Experimental driver notice

PanVK is experimental and may be less smooth or stable than the built-in System driver. Compatibility and performance depend on the device, Mali GPU, kernel, and game. Review the device notes for each release. If problems occur, switch back to the System driver. Import success does not guarantee that a driver will work on a particular device.

## APK packages

Upload the four APK files manually as release assets:

- Standard — `app-standard-release.apk`
- Ludashi — `app-ludashi-release.apk`
- AnTuTu — `app-antutu-release.apk`
- Futuremark — `app-futuremark-release.apk`

This repository update contains source and release text only; APK files are not committed or uploaded by this change.
