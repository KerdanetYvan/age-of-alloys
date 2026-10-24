---
name: release
description: Prepare and publish a version of the mod (changelog section, mod_version bump, release commit, then tag and GitHub Release after the merge). Use when the user types /release, asks to release or publish a version, or works on a release ticket. Without a version, or with --dry-run, it only previews.
---

# Release

Automates the release process of `CONTRIBUTING.md`. Everything the user reads is in French (see `CLAUDE.md`).

Repo `KerdanetYvan/age-of-alloys` (public). Version lives in `mod_version` of `gradle.properties`. The build produces `build/libs/ageofalloys-<version>.jar`. The changelog is `CHANGELOG.md` (French, Keep a Changelog format, sections `## [Non publié]` and `## [X.Y.Z] - AAAA-MM-JJ`).

## Usage

`/release <X.Y.Z>`. With `--dry-run`, or without a version, **only preview** (see "Preview"): write nothing, tag nothing, publish nothing.

## Why two phases

`main` is protected and one ticket = one branch = one PR, so the release commit cannot go to `main` directly: it goes through the branch of the release ticket and a PR that **the user merges**. The tag and the GitHub Release must point at the commit that the squash merge created on `main`, so they are made **after** the merge.

## Phase A: prepare (on the release ticket's branch)

1. **Preconditions**, stop with the detail if one fails:
   - the version is SemVer (`X.Y.Z`) and greater than the current `mod_version`;
   - the current branch is not `main` and is the branch of a release ticket (`chore/<n>-...`, started with `start-ticket`);
   - the working tree is clean (`git status --short`);
   - the tag `vX.Y.Z` does not exist (`git tag -l vX.Y.Z`, `git ls-remote --tags origin vX.Y.Z`).
2. **Changelog check**: the `[Non publié]` section is not empty, otherwise stop.
3. **Edit**:
   - `CHANGELOG.md`: `## [Non publié]` becomes `## [X.Y.Z] - <today, from date +%F>`, and a new empty `## [Non publié]` section is recreated above it;
   - `gradle.properties`: `mod_version=X.Y.Z`.
4. **Check**: `./gradlew build` must succeed, then the version check of `open-pr` ("Version number matches the release being prepared").
5. **Commit** with the `commit` skill: `chore(release): vX.Y.Z`. Open the PR with `open-pr` **only when the user asks**.

## Phase B: publish (only after the user says the PR is merged)

6. `git switch main && git pull`. Check that `mod_version` is `X.Y.Z` and that `CHANGELOG.md` has the `[X.Y.Z]` section, then run `./gradlew build`. The jar `build/libs/ageofalloys-X.Y.Z.jar` must exist.
7. Extract the `[X.Y.Z]` section of the changelog (without its heading) into a notes file in the session scratchpad.
8. **Ask for explicit confirmation** with a recap: version, commit that will be tagged (`git rev-parse --short HEAD`), jar, notes, and whether it is flagged as a pre-release. A tag and a release are public and hard to undo. Do nothing before the answer.
9. After the confirmation:
   ```bash
   git tag -a vX.Y.Z -m "vX.Y.Z"
   git push origin vX.Y.Z
   gh release create vX.Y.Z build/libs/ageofalloys-X.Y.Z.jar --title "vX.Y.Z" --notes-file <notes> [--prerelease]
   ```
   Use `--prerelease` while the major version is `0` (beta).
10. Give the release link as a markdown link, then remind the user that CurseForge and Modrinth are separate (ticket #70) and not handled here.

## Preview (`--dry-run` or no version)

Report, in French and without writing anything:
- the current version and the target version, and which preconditions of step 1 hold or fail (a failing branch or clean-tree check is information here, not an error);
- the changelog section that would become `[X.Y.Z]` (its lines), or that it is empty;
- the jar name, the tag, and whether `--prerelease` would be used;
- that no file, tag or release was touched (`git status --short` and `git tag -l` as proof).

## Rules

- Never commit or push to `main`.
- Never create or push a tag, and never publish a release, without the confirmation of step 8, asked right before.
- Never merge a PR; the user merges ("Squash and merge").
- No attribution line in the commit or in the release notes (explicit decision of the repo owner).
- If a check fails, stop and report; do not fix it silently and do not skip it.
- `--dry-run` writes nothing: no file, no commit, no tag, no release.
