# Age of Alloys: instructions for Claude

Rules and pointers for this repository. Step-by-step procedures live in the skills under `.claude/skills/`; this file does not repeat them.

## Project

Minecraft mod adding alloys, with tools, weapons and (later) armor. Forge 1.20.1 (47.4.10), Java 17, mod id `ageofalloys`, package `fr.kerdanetyvan.ageofalloys`, license All Rights Reserved.

- Roadmap: `ROADMAP.md`. Step 1 design: `docs/design/starter-alloys.md`. Code layout: `docs/architecture.md`. Learning checklist: `docs/NOTIONS.md`.
- Board: GitHub Project 6 (owner `KerdanetYvan`). Repo: `KerdanetYvan/age-of-alloys`.

## Language

- **Everything the user reads is in French**: chat replies, plans (the plan file and the text shown for approval), questions, summaries, lessons.
- **English** for code, identifiers, file names, commit messages, and PR titles and bodies.
- Tickets: French title, body in French then English. Docs under `docs/`: French. READMEs: bilingual.
- Do not infer the user's gender; use neutral phrasing.

## Workflow

- One issue = one branch = one PR. Never work on `main`; it is protected.
- Update the board **before** coding: use `start-ticket` (moves the ticket to "En cours", creates the branch, then proposes a plan in plan mode and waits for approval).
- Create tickets with `new-ticket`. Commit with `commit`, only at the end of an approved plan or on request. Open PRs with `open-pr`, **only when the user asks**.
- Never merge a PR: the user merges ("Squash and merge"). Squash merge is the only method allowed and the `Build mod` check must be green.
- `board` audits code, Git and the board (read-only report, fixes only after the user approves), suggests ticket splits and the next task.
- `release` prepares a version on the release ticket's branch, then, after the merge and an explicit confirmation, tags it and publishes the GitHub Release. `--dry-run` only previews.
- "Review" and "Fait" on the board are set by GitHub automations; do not set them by hand unless they did not fire.
- A parent ticket (one with sub-issues) has no branch and no PR: `start-ticket` moves it to "En cours" with its first started sub-issue, and `open-pr` closes it after the merge of its last sub-issue. This works at any depth: `start-ticket` moves all ancestors, `open-pr` closes them in cascade.

## Git

- Conventional Commits, English, imperative. Details in `CONTRIBUTING.md` and the `commit` and `open-pr` skills.
- **No attribution lines**: no `Co-Authored-By` trailer in commits, no "Generated with" footer in PRs.

## Code

- `./gradlew build` must pass before any PR. It includes `checkArchitecture`.
- Follow `docs/architecture.md`: the `content` package never imports `net.minecraftforge`; prefer JSON data over code; client-only code stays in `client`.
- Keep version values consistent (Java, Minecraft, Forge, mod id, package); the `open-pr` skill lists the checks.

## Teaching

The user is learning Minecraft modding from scratch and wants notions, not syntax drills. Use the `lesson` skill when a concept is needed: explain first, show the code, ask a check question. Show code before asking the user to write any. Never tick a box in `docs/NOTIONS.md`: mark covered notions `[*]` and let the user validate with `[x]`.
