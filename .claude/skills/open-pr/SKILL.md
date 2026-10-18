---
name: open-pr
description: Push the current branch and open a pull request for this repo, linked to its ticket, following the PR template. Use when the user asks to open a PR, or when the work on a ticket is finished and ready for review.
---

# Open a pull request

## Preconditions

1. `git branch --show-current` must not be `main`. The branch name `type/<n>-slug` gives the ticket number.
2. Working tree is committed (use the `commit` skill). Unrelated local edits must not be swept in.
3. If Java code, resources or Gradle files changed: run `./gradlew build` and confirm it passes. If it fails, fix or report; do not open the PR.
4. If the change is visible to players, `CHANGELOG.md` must have an entry under `[Non publié]`.
5. If the change touches a concept listed in `docs/NOTIONS.md`, do **not** tick boxes yourself: ticking is done only after the user shows understanding (see the `lesson` skill).

## Open

```bash
git push -u origin <branch>
gh pr create --base main --head <branch> --title "<type(scope): subject>" --body-file <file>
```

- **Title**: Conventional Commits, English. Because only squash merge is allowed, the title becomes the commit on `main`.
- **Body**: follow `.github/pull_request_template.md`: `## What`, `## Why`, then `Closes #<n>` (one `Closes` line per ticket), then the checklist with boxes ticked only when actually verified. End the body with:
  `🤖 Generated with [Claude Code](https://claude.com/claude-code)`
- Write the body to a temporary file in the session scratchpad and use `--body-file` so formatting survives.

## After opening

- **Do not** move the ticket to "Review" by hand. The project automation "Pull request linked to issue" does it. Wait about 15 seconds, then verify with `gh project item-list 6 --owner KerdanetYvan --limit 100 --format json`. Only if it did not move, set it manually and tell the user.
- Watch the CI: `gh pr checks <pr> --watch`. Report the result. If it fails, read the log (`gh run view --log-failed`), fix on the same branch, push.
- Give the user the PR link as a markdown link.

## Rules

- **Never merge the PR.** The user merges, using "Squash and merge". Branch protection requires a green `Build mod` check and a linear history.
- After the user says it is merged: `git switch main && git pull`, delete the local branch, and confirm the ticket reached "Fait" (set by the "Pull request merged" automation).
