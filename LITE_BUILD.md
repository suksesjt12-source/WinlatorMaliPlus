# Winlator Mali Lite

This branch adds a separate `lite` product flavor without changing the existing standard, Ludashi, AnTuTu, or Futuremark flavors.

## Lite profile

- Package ID: `com.winlator.mali.lite`
- ABI: ARM64 (same as the existing project)
- Retains the common runtime, container assets, Mali wrapper archives, PulseAudio, input controls, and Windows components.
- Bundles Box64 0.4.3 and WOWBox64 0.4.3, plus FEXCore 2605 and hybrid-x86.
- Retains a focused DXVK/VKD3D set and removes older bundled versions from the Lite APK.
- Omits the bundled Adreno v819 and Turnip driver archives.
- The existing Contents and graphics-wrapper catalog features remain in the app for optional components.

The goal is a smaller installation package. This does not by itself guarantee lower game-time RAM use or higher FPS; those depend on the selected runtime, game, and graphics settings.

## Build

GitHub Actions builds `:app:assembleLiteRelease` on pushes to this branch. The APK is uploaded as the `winlator-mali-lite-arm64` workflow artifact.
