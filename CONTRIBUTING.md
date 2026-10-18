# Contribuer à Age of Alloys

## Branches

- `main` : toujours stable, chaque commit publiable.
- `feat/<sujet>`, `fix/<sujet>`, `docs/<sujet>` : branches de travail, fusionnées via Pull Request.

## Commits : Conventional Commits

Format : `type(portée): description à l'impératif`

| Type | Usage |
|------|-------|
| `feat` | nouvelle fonctionnalité (item, mécanique…) |
| `fix` | correction de bug |
| `docs` | documentation, wiki, README |
| `refactor` | réécriture sans changement de comportement |
| `assets` | textures, modèles, sons, traductions |
| `build` | Gradle, dépendances, CI |
| `chore` | maintenance diverse |

Exemple : `feat(items): add bronze pickaxe`

## Versionnement

[SemVer](https://semver.org/lang/fr/) : `MAJOR.MINOR.PATCH`.
Chaque version est un **tag Git** `vX.Y.Z` et une **GitHub Release**.

## Changelog

Toute PR qui modifie le comportement du mod ajoute une ligne dans la section `[Non publié]` de [CHANGELOG.md](CHANGELOG.md)
(catégories : Ajouté, Modifié, Déprécié, Retiré, Corrigé, Sécurité).

## Processus de release

1. Déplacer `[Non publié]` vers une nouvelle section `[X.Y.Z] - AAAA-MM-JJ`.
2. Mettre à jour la version dans la config de build.
3. Commit `chore(release): vX.Y.Z`, puis tag `vX.Y.Z`.
4. Publier la GitHub Release (notes = section du changelog).
5. Publier sur CurseForge / Modrinth.

_(Sera automatisé via GitHub Actions une fois le build en place.)_
