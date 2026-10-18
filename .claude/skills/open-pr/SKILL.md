---
name: open-pr
description: Push the current branch and open a pull request for this repo, linked to its ticket, after a local build and a version-consistency check. Use ONLY when the user explicitly asks to open a PR. When work is finished, say it is ready and ask; never open the PR on your own initiative.
---

# Open a pull request

**Only on explicit request from the user.** When a ticket's work is done, tell them it is ready and ask whether to open the PR.

## 1. Preconditions

1. `git branch --show-current` must not be `main`. The branch name `type/<n>-slug` gives the ticket number.
2. Everything is committed (use `commit`). Unrelated local edits must not be swept in.
3. **Always run `./gradlew build` locally first**, even for docs-only changes. It must succeed. If it fails, fix it or report it; do not open the PR.
4. If the change is visible to players, `CHANGELOG.md` has an entry under `[Non publié]`.

## 2. Consistency check (can crash the game if it drifts)

Verify these values agree, and report any mismatch before opening the PR:

| Check | Where |
|---|---|
| Java version is the same everywhere (17 for Minecraft 1.20.1) | `build.gradle` (`JavaLanguageVersion`), `.github/workflows/build.yml` (`java-version`), `README.md` / `README.fr.md` compatibility table |
| Minecraft version is consistent | `minecraft_version` and `minecraft_version_range` in `gradle.properties`, README table, badges |
| Forge version matches the Minecraft version (1.20.1 -> 47.x) and falls in the ranges | `forge_version`, `forge_version_range`, `loader_version_range` in `gradle.properties` |
| Mod id is identical in Gradle and Java | `mod_id` in `gradle.properties`, `MOD_ID` constant and `@Mod(...)` in the main class |
| Base package matches the group id | `mod_group_id` in `gradle.properties` vs the Java package folders under `src/main/java` |
| Resource pack format fits the Minecraft version (15 for 1.20.1) | `pack_format` in `src/main/resources/pack.mcmeta` |
| Version number matches the release being prepared | `mod_version` in `gradle.properties` vs `CHANGELOG.md` |

Useful commands: `grep -nE "^(minecraft|forge|loader|mod_)" gradle.properties`, `grep -n "JavaLanguageVersion" build.gradle`, `grep -n "java-version" .github/workflows/build.yml`, `grep -rn "MOD_ID\|@Mod" src/main/java`.

## 3. Learning checklist (`docs/NOTIONS.md`)

Never tick a box. For each notion that this ticket was supposed to teach, add a trailing ` *` to its line (`- [ ] **Registries** ... (E1) *`). The `*` means "should have been covered, waiting for the user to confirm". The user ticks the box and removes the `*` once they have understood. If the legend is missing at the top of the file, add: `` `*` = covered in a lesson, awaiting your validation ``. Touching `NOTIONS.md` belongs in the ticket's own branch.

## 4. Open

```bash
git push -u origin <branch>
gh pr create --base main --head <branch> --title "<type(scope): subject>" --body-file <file>
```

- **Everything in English.** Title in Conventional Commits (it becomes the commit on `main` after the squash merge).
- **Body**, following `.github/pull_request_template.md`: `## What`, `## Why`, `Closes #<n>` (one line per ticket), then the checklist with boxes ticked only when actually verified. **No "Generated with Claude Code" footer and no other attribution line** (explicit decision of the repo owner, same as for commits).
- Write the body to a file in the session scratchpad and use `--body-file`.

## 5. After opening

- Do not move the ticket to "Review" by hand: the "Pull request linked to issue" automation does it. Wait about 15 seconds and verify with `gh project item-list 6 --owner KerdanetYvan --limit 100 --format json`. Set it manually only if it did not move, and tell the user.
- Watch CI: `gh pr checks <pr> --watch`. Report the result. On failure read the log (`gh run view --log-failed`), fix on the same branch, push.
- Give the PR link as a markdown link.

## Rules

- **Never merge.** The user merges with "Squash and merge". Branch protection requires a green `Build mod` check.
- After the user says it is merged: `git switch main && git pull`, delete the local branch, and confirm the ticket reached "Fait" (automation "Pull request merged").
