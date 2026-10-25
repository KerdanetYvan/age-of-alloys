# Architecture du code

> Document de décision. Il fixe comment organiser le code **dès maintenant** pour qu'un futur portage vers NeoForge ou Fabric reste peu coûteux, sans restructurer le projet en multi-loader tout de suite.

## 1. Objectif et non-objectifs

**Objectif** : écrire le Java des objets (ticket #10) une seule fois, sur une organisation qui sépare ce qui est réutilisable de ce qui dépend de Forge.

**Non-objectifs** :
- Pas de projet multi-modules (`common` + `forge` + `fabric`) pour l'instant.
- Pas de dépendance à une bibliothèque cross-loader (Architectury, par exemple).
- Le projet reste **Forge 1.20.1**, seul loader supporté aujourd'hui.

## 2. Ce qui est neutre, ce qui dépend du loader

| Neutre (réutilisable tel quel) | Dépend du loader (à réécrire par loader) |
|---|---|
| Recettes, tables de butin, modèles, textures, traductions (JSON et PNG) | Enregistrement des objets (`DeferredRegister` sur Forge, registres de Fabric) |
| Définitions des alliages : identifiants et statistiques, écrites en Java qui n'utilise que des types vanilla (`Tier` est une interface de Minecraft) | Branchement des événements (bus du mod), onglets créatifs, configuration, réseau |
| Constantes (`MOD_ID`, noms d'objets) | Fichier de métadonnées, espace des tags, conditions de chargement, génération de données |

Conséquence : plus on met de choses **en données** (JSON), moins il y a de code à porter. C'est la règle n°1.

## 3. Règles de code

**Organisation des paquets**, sous `fr.kerdanetyvan.ageofalloys` :

| Paquet | Contenu | Dépend de Forge ? |
|---|---|---|
| `content` | Définitions des alliages (statistiques, identifiants), implémentation du `Tier` | **Non** : uniquement du Java et des types `net.minecraft.*` |
| `block` | Blocs qui portent une logique (la fonderie) et leurs *block entities* : inventaires, tick, capacités | Oui |
| `registry` | Enregistrement des objets (`DeferredRegister`, `RegistryObject`), enregistrement du tier auprès de Forge | Oui |
| `client` | Tout ce qui touche à l'affichage (écrans, rendu) | Oui, **côté client seulement** (voir la leçon sur les côtés) |
| `datagen` | Génération des fichiers JSON | Oui |

La classe d'entrée `AgeOfAlloys` reste à la racine : c'est elle qui branche les autres.

**Règles** :
1. **`content` n'importe rien de `net.minecraftforge`.** Si une définition d'alliage a besoin de Forge, elle est mal placée.
2. **Les données avant le code** : si on peut décrire une chose en JSON (recette, tag, modèle), on ne l'écrit pas en Java.
3. **Les identifiants et les noms de tags sont définis à un seul endroit**, pour que l'espace `forge:` soit remplaçable d'un coup.
4. **Le code d'affichage reste dans `client`**, jamais mélangé au code serveur.
5. **La règle 1 est vérifiée automatiquement** par la tâche Gradle `checkArchitecture` (dans `build.gradle`). Elle échoue en citant le fichier et la ligne si `content` mentionne `net.minecraftforge`. Elle est rattachée à `check`, donc elle tourne à chaque `./gradlew build` et dans la CI. Limite : elle lit le texte, elle ne comprend pas le code ; une bibliothèque comme ArchUnit serait plus puissante, mais ajouterait une dépendance pour une règle simple.

Cette organisation s'applique dès les tickets #10 (lingots) et #11 (outils).

## 4. Options évaluées (aucune adoptée pour l'instant)

| Option | Principe | Pour ce projet |
|---|---|---|
| **Un loader, une branche par portage** | On garde Forge ; pour NeoForge, on copie le projet vers une nouvelle branche | Le plus simple aujourd'hui, le plus manuel plus tard |
| **[MultiLoader Template](https://github.com/jaredlll08/MultiLoader-Template)** | Un module `common` + un module par loader, sans dépendance tierce | Léger et explicite ; il faut apprendre un build multi-modules |
| **[Architectury](https://docs.architectury.dev/api/getting-started/setup)** | Module `common` + une API qui masque les différences (événements, registres) | Le plus d'abstraction ; sa documentation actuelle vise Minecraft 26.x et le JDK 25 |
| **Stonecutter / [Stonecraft](https://stonecraft.meza.gg/)** | Un seul code source, transformé à la compilation pour chaque version et chaque loader | Idéal pour beaucoup de versions ; mise en place la plus exigeante |

Ces informations viennent d'une recherche web du 19 octobre 2026 et sont à **revérifier avant d'adopter** l'une d'elles. Côté Forge, l'équipe NeoForge a [abandonné la 1.20.1](https://neoforged.net/news/2024-retrospection/) pour se concentrer sur les versions plus récentes : Forge reste le bon choix pour cette version.

## 5. Recommandation et déclencheurs

**Recommandation** : rester sur un seul loader pendant les premières versions, avec l'organisation de la section 3.

**On rouvre la question quand** :
- on commence réellement un deuxième loader, ou
- on commence une version de Minecraft plus récente.

Le plus probable est de **commencer par une branche de portage** vers une version récente (avec NeoForge), puis d'évaluer MultiLoader ou Stonecutter avec un **vrai besoin** en main.

À savoir : les versions récentes de Minecraft ne sont plus obfusquées, donc la notion de *mappings* disparaîtra à ce moment-là.

## 6. Conventions par loader

| | Forge 1.20.1 | NeoForge (1.21+) | Fabric |
|---|---|---|---|
| Fichier de métadonnées | `META-INF/mods.toml` | `META-INF/neoforge.mods.toml` | `fabric.mod.json` |
| Espace des tags communs | `forge:` | `c:` | `c:` |
| Condition de chargement d'une recette | `forge:conditions` | `neoforge:conditions` | `fabric:load_conditions` |
| Génération de données | `GatherDataEvent` | `GatherDataEvent` | `DataGeneratorEntrypoint` |
| Enregistrement | `DeferredRegister` | `DeferredRegister` | `Registry.register` |

Ces conventions sont à **confirmer au moment du portage**, car elles évoluent avec les versions.
