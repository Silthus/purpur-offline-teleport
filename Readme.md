# OfflineTeleporter

[![Build Status](https://github.com/Silthus/purpur-offline-teleporter/workflows/Build/badge.svg)](../../actions?query=workflow%3ABuild)
[![GitHub release (latest SemVer including pre-releases)](https://img.shields.io/github/v/release/Silthus/purpur-offline-teleporter?include_prereleases&label=release)](../../releases)
[![codecov](https://codecov.io/gh/Silthus/purpur-offline-teleporter/branch/master/graph/badge.svg)](https://codecov.io/gh/Silthus/purpur-offline-teleporter)
[![Commitizen friendly](https://img.shields.io/badge/commitizen-friendly-brightgreen.svg)](http://commitizen.github.io/cz-cli/)
[![semantic-release](https://img.shields.io/badge/%20%20%F0%9F%93%A6%F0%9F%9A%80-semantic--release-e10079.svg)](https://github.com/semantic-release/semantic-release)

A simple and quick plugin to teleport offline players to your current location.

> **IMPORTANT**  
> This plugin requires running a [Purpur](https://github.com/pl3xgaming/Purpur) server with at least version `1.17`.

## Commands

- `/offlinetp player <player>`: *Teleports the given offline player to your current location.*
- `/offlinetp all`: *Teleports all offline (and online) players to your current location.*
- `/offlinetp all <world>`: *Teleports all offline (and online) players that were last seen in the given world to your current location. The world must exist and still be loaded!*