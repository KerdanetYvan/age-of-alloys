# 📚 Fiche de notions : modder Minecraft de A à Z

Liste de ce qu'il faut **comprendre** (pas forcément mémoriser) pour mener Age of Alloys jusqu'au bout.
On coche une notion quand on est capable de **l'expliquer avec ses propres mots**.

`[ ]` pas encore vu · `[*]` vu en cours, en attente de ta validation · `[x]` compris et validé par toi. Quand tu as compris une notion `[*]`, remplace l'étoile par un `x`.

Légende des étapes du projet : **E1** items · **E2** mécaniques d'alliage · **E3** génération du monde · **E4** compatibilité mods · **🚢** publication / qualité

---

## 1. Les fondations du modding

- [x] Ce qu'est un mod : du code Java chargé par le jeu au démarrage (E1)
- [x] Minecraft Java Edition vs Bedrock : pourquoi on parle uniquement de Java (E1)
- [x] Les **mod loaders** : Forge, NeoForge, Fabric, Quilt, et pourquoi ils existent (E1)
- [x] Les **mappings** : le code de Minecraft est obfusqué, comment on le rend lisible (E1)
- [x] Le **versioning de Minecraft** et pourquoi un mod est lié à une version précise (E1)
- [x] Côté **client** vs côté **serveur** (logical sides) : la notion la plus importante du modding (E1)
- [x] Solo = serveur intégré + client : ce que ça implique (E1)

## 2. Environnement et outillage

- [x] **JDK** : quelle version de Java pour quelle version de Minecraft (E1)
- [x] **Gradle** : système de build, `build.gradle`, `gradle.properties`, wrapper (E1)
- [x] Le **MDK / template** de mod fourni par le loader (E1)
- [x] Structure d'un projet de mod : `src/main/java`, `src/main/resources` (E1)
- [x] Lancer le jeu en développement : `runClient`, `runServer`, `runData` (E1)
- [x] Lire un **crash report** et un `latest.log` (E1)
- [ ] Déboguer avec l'IDE (points d'arrêt) (E1)
- [x] Le fichier de métadonnées du mod : `mods.toml` (Forge), `neoforge.mods.toml` / `fabric.mod.json` (E1)
- [x] **mod id**, namespace, et convention de nommage (E1)

## 3. Le modèle de données de Minecraft

- [x] **Registries** (registres) : comment le jeu connaît items, blocs, entités… (E1)
- [x] **ResourceLocation / Identifier** : `namespace:chemin` (E1)
- [x] **Deferred registration** : pourquoi on n'enregistre pas "directement" (E1)
- [x] **Event bus** : le système d'événements du loader (E1)
- [x] Cycle de vie du chargement : construction, setup commun, setup client (E1)
- [x] **Data-driven** (données JSON) vs **code** : ce qui peut être fait sans Java (E1)
- [x] **Datapacks** et **resource packs** : à quoi ils servent et leur lien avec un mod (E1)

## 4. Items

- [x] Un **Item** vs un **ItemStack** (E1)
- [x] **Item.Properties** : stack size, durabilité, rareté, résistance au feu… (E1)
- [x] Les **tiers de matériaux** (Tier) : durabilité, vitesse de minage, dégâts, enchantabilité (E1)
- [x] Outils : pioche, hache, pelle, houe, épée, et leurs **attributs** (E1)
- [ ] Armures : **ArmorMaterial**, slots, protection, toughness, knockback resistance (E1)
- [ ] **Data components** (1.20.5+) vs NBT : stocker des données sur un item (E1)
- [ ] **Creative tabs** : ranger ses items dans l'inventaire créatif (E1)
- [x] **Tags** : `#forge:ingots` (Forge 1.20.1 ; `#c:ingots` sur Fabric et NeoForge), `#minecraft:pickaxes`… et pourquoi ils sont clés pour la compat (E1/E4)
- [x] **Localisation** (`lang/*.json`) : noms traduits (E1)

## 5. Assets visuels (côté ressources)

- [x] **Textures** : format PNG, taille 16×16, palettes cohérentes (E1)
- [x] **Item models** JSON (`item/generated`, `item/handheld`) (E1)
- [ ] **Blockstates** et **block models** (E3)
- [ ] **Armor models / layers** : rendu des armures sur le joueur (E1)
- [x] **Atlas** : comment les textures sont regroupées (E1)
- [ ] **pack.mcmeta** et icône du mod (🚢)

## 6. Blocs et block entities

- [x] **Block** vs **BlockState** vs **BlockItem** (E2/E3)
- [x] **Block.Properties** : dureté, résistance, son, outil requis (E2/E3)
- [x] **Block entity** : un bloc qui possède des données/une logique (four, forge…) (E2)
- [x] **Ticking** : exécuter de la logique à chaque tick (E2)
- [x] **Loot tables** : ce qu'un bloc/ennemi lâche (E1/E3) (le butin d'un minerai, avec Fortune et Silk Touch, est vu avec #109)
- [ ] Niveau d'outil requis pour miner (`needs_iron_tool` etc.) (E3)

## 7. Recettes et mécaniques d'alliage

- [x] **Recipes** JSON en forme (*shaped*) : `pattern`, `key`, `result`, et leur génération par datagen (E1/E2)
- [ ] **Recipes** sans forme, fourneau, haut fourneau, forge : *shapeless*, *smelting*, *blasting*, *smithing* (E2) (le four et le haut fourneau sont vus avec #109, pas encore les recettes sans forme ni la forge)
- [x] **Recipe types** et **recipe serializers** custom (E2)
- [ ] Le **Smithing Table** (table de forge) et les templates (E2)
- [x] **Inventaires** : `Container`, slots, **capabilities** d'items (E2)
- [x] **Menus (Menu/Container)** et **Screens (GUI)** : interface d'un bloc custom (E2)
- [ ] Synchronisation client ↔ serveur : **networking / payloads** (E2) (les données du menu sont vues avec #87, pas encore les vrais paquets)
- [ ] **Fluides** (si métal fondu) et capabilities de fluides (E2)
- [x] **Énergie / chaleur** : modéliser une machine qui a besoin de combustible (E2)
- [x] Concevoir un **système d'alliages** : ratios, températures, règles métier (E2)
- [ ] **Advancements** : guider le joueur dans la progression (E2)

## 8. Génération du monde

- [x] Pipeline de génération : biomes, relief, **features**, structures (E3)
- [x] **Configured Feature** vs **Placed Feature** (E3)
- [ ] **Ore generation** : taille de veine, nombre d'essais, plage de hauteur, distribution (E3) (vu avec #111 : le trapèze est compris, la dispersion horizontale par `InSquarePlacement` est à revoir)
- [x] **Biome modifiers** : ajouter ses minerais aux biomes existants (E3)
- [x] Équilibrage : rareté, profondeur, progression dans le jeu (E3)
- [x] **Datagen** : générer les JSON par code plutôt qu'à la main (E1→E3)
- [ ] Structures et biomes custom (optionnel) (E3+)
- [x] Mondes existants : ce qui se passe quand on ajoute un mod à une sauvegarde (E3)

## 9. Équilibrage et game design

- [ ] Placer un alliage dans la **courbe de progression** (cuivre → fer → diamant → netherite) (E1)
- [ ] Chiffrer durabilité, dégâts, armure de façon cohérente avec le jeu de base (E1)
- [ ] Éviter d'invalider les matériaux vanilla (E1)
- [ ] Identité visuelle et thématique de chaque alliage (E1)

## 10. Compatibilité avec d'autres mods

- [ ] **Dépendances** : obligatoires, optionnelles, incompatibilités (E4)
- [ ] **Tags communs** (`forge:` sur Forge 1.20.1, `c:` sur Fabric et NeoForge) : la base de l'interopérabilité (E4)
- [ ] Conventions de matériaux partagés (lingots, poudres, plaques…) (E4)
- [ ] **Create** : recettes de mélange, pressage, etc. via datapack (E4)
- [ ] **Tinkers' Construct** : fonderie, alliages, matériaux d'outils (E4)
- [ ] Autres candidats : JEI/REI/EMI (affichage des recettes), Jade/WTHIT (infobulles) (E4)
- [ ] **Conditions de chargement** : n'activer une recette que si le mod X est présent (E4)
- [ ] API vs intégration directe vs simple datapack : choisir le bon niveau (E4)

## 11. Tests et qualité

- [ ] **Game tests** / tests d'intégration en jeu (🚢)
- [ ] Tests unitaires Java sur la logique pure (JUnit) (🚢)
- [ ] Tester en **multijoueur dédié** (pas seulement en solo) (🚢)
- [ ] Tester avec et sans les mods optionnels (E4/🚢)
- [ ] Vérifier la compat d'un monde existant (mise à jour sans casse) (🚢)

## 12. Versionnement et release

- [ ] **Git** : commits, branches, tags (🚢)
- [ ] **Conventional Commits** : messages lisibles et automatisables (🚢)
- [ ] **Semantic Versioning** appliqué à un mod (`MAJOR.MINOR.PATCH`) et lien avec la version MC (🚢)
- [ ] **Changelog** (Keep a Changelog) (🚢)
- [ ] **GitHub Releases** et tags (🚢)
- [ ] **CI/CD** avec GitHub Actions : build automatique à chaque push (🚢)
- [ ] Publication automatisée vers **CurseForge** et **Modrinth** (🚢)
- [ ] Gérer plusieurs versions de Minecraft (branches, ports) (🚢)

## 13. Publication et communauté

- [ ] **Licence** : choisir (MIT, LGPL, ARR…) et ce que ça implique pour les autres (🚢)
- [ ] Page CurseForge : description, captures, catégories, dépendances (🚢)
- [ ] Page Modrinth (🚢)
- [ ] **README** exemplaire : installation, features, captures, compat (🚢)
- [ ] **Wiki** : page par alliage, recettes, guides (🚢)
- [ ] Gestion des **issues** et de la communauté (templates d'issues, CONTRIBUTING) (🚢)
- [ ] Respect des règles CurseForge (assets, attribution, monétisation) (🚢)
