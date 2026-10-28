<p align="center"><img src="docs/assets/banner.png" alt="Age of Alloys" width="100%"></p>

🇬🇧 **English** · 🇫🇷 [Français](README.fr.md)

> **The best of every metal.** A Minecraft mod that adds **alloys**, and with them new tools and weapons.

[![Build](https://github.com/KerdanetYvan/age-of-alloys/actions/workflows/build.yml/badge.svg)](https://github.com/KerdanetYvan/age-of-alloys/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/KerdanetYvan/age-of-alloys?include_prereleases&label=release)](https://github.com/KerdanetYvan/age-of-alloys/releases)
![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-62b47a)
![Forge](https://img.shields.io/badge/Forge-47.x-d9672b)
![Status](https://img.shields.io/badge/status-beta-yellow)
![License](https://img.shields.io/badge/license-All%20Rights%20Reserved-lightgrey)
<!-- TODO: CurseForge / Modrinth download badges -->

## Concept

Age of Alloys deepens Minecraft's progression with composite metals: iron, gold and diamond are no longer enough, you will **smelt, mix and forge** brand new materials. The raw metals are found in the world as ores, and you make the alloys from them in an alloy foundry.

## What's in the mod

Everything is in the **Age of Alloys** creative tab, and it can all be obtained in survival:

- **4 ores**: tin, aluminum, magnesium and manganese, found in stone and deepslate in every biome of the Overworld. Each drops a raw item that melts into an ingot in a furnace or a blast furnace. Aluminum and tin are mined with a stone pickaxe, magnesium and manganese need an iron-level pickaxe. Ores only generate in chunks created after this version: explore a new area or create a new world.
- **7 ingots**: three alloys (bronze, duralumin and steel) and the four raw metals they are made from (tin, aluminum, magnesium and manganese).
- **15 tools and weapons**: a pickaxe, an axe, a shovel, a hoe and a sword for each alloy. All of them mine at iron level, and they are crafted with ingots and sticks.
- **The alloy foundry**: a block crafted from four stone bricks, three smooth stones and a bucket. It melts bronze, duralumin and steel from the raw metals, and the fuel must be hot enough for the alloy: any fuel for bronze, coal or better for duralumin, a coal block or a lava bucket for steel.
- **12 pieces of armor**: a helmet, a chestplate, leggings and boots for each alloy, crafted with ingots in the shapes of the vanilla armor and repaired with the ingot of their alloy.
- Names in English and French. The ingots are tagged `forge:ingots/<metal>`, so other mods and recipes recognize them as ingots of their metal.

| Alloy | Role | Durability | Mining speed |
|---|---|---:|---:|
| Bronze | A sturdy mid-game tool, more durable than iron | 400 | 6 |
| Duralumin | The fast miner: quick and highly enchantable, twice as fast on rock and ores, with a sword that hits 2.0 times per second and reaches half a block further | 300 | 9 |
| Steel | The best material of this version, just below diamond, a bit faster on stone and deepslate, with a sword that pushes a little harder | 1,000 | 7.5 |
| _Iron (vanilla, for reference)_ | | _250_ | _6_ |

| Armor | Role | Protection (full set) | Durability (full set) | Toughness (per piece) |
|---|---|---:|---:|---:|
| Bronze | Like iron, but it lasts longer; the full set slowly mends itself | 15 | 1,100 | 0 |
| Duralumin | Like iron, a bit less durable and a bit faster to run in, and the full set softens falls; the best enchantability | 15 | 715 | 0 |
| Steel | Just below diamond, a bit heavy but harder to knock back, and the full set halves fire damage | 18 | 1,485 | 1 |
| _Iron (vanilla, for reference)_ | | _15_ | _825_ | _0_ |

> **Beta:** the whole loop works in survival (find the ores, melt the ingots, make the alloys, craft tools and armor), but the mod is still being tested: expect rough edges. Compatibility with other mods, advancements and a recipe book for the foundry come later.

## Planned features

- [x] **Alloys** and their gear (tools and weapons)
- [x] **Alloy creation** in game
- [x] **Raw materials** generated in the world
- [x] **Armor** for each alloy
- [ ] **Compatibility** with popular mods
- [x] Full **wiki**

Details and order are in the [roadmap](ROADMAP.md) (in French).

## Installation

Requirements: Minecraft 1.20.1 and Forge 47.x (Java 17).

1. Install Forge for Minecraft 1.20.1.
2. Download `ageofalloys-0.3.0.jar` from the [releases](https://github.com/KerdanetYvan/age-of-alloys/releases) page.
3. Put the jar in the `mods` folder of your game.

v0.3.0 is a pre-release (beta): expect rough edges.

## Compatibility

| Minecraft | Loader | Status |
|-----------|--------|--------|
| 1.20.1 | Forge 47.x (Java 17) | 🧪 Beta (v0.3.0) |

A port to NeoForge and newer Minecraft versions is planned for later.

## Documentation

- [Wiki](https://kerdanetyvan.github.io/age-of-alloys/en/) (recipes, ores, alloys)
- [Roadmap](ROADMAP.md) (French)
- [Changelog](CHANGELOG.md)
- [Contributing](CONTRIBUTING.md) (French)
- [Visual identity](docs/identity.md) (French)
- [Learning notes](docs/NOTIONS.md) (French)

## License

© 2026 Kerdanet Yvan, **All Rights Reserved**. See [LICENSE](LICENSE).
Some textures are derived from Minecraft assets (property of Mojang/Microsoft).
