---
name: board
description: Audit the consistency between the code, the Git repo and the GitHub project board of this repo, report what is badly tracked, suggest tickets to split, and fix the board after the user approves. Use when the user types /board, asks to check or clean up the board, asks what to work on next, or wants to know whether a ticket should be split into sub-issues.
---

# Board audit

Compares three sources (code, Git, GitHub board) and reports the gaps. **Read-only until the user approves a fix.** Everything the user reads is in French (see `CLAUDE.md`).

Project: number `6`, owner `KerdanetYvan`, id `PVT_kwHOCjZVkc4BnnU1`. Status field id `PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg`. Repo `KerdanetYvan/age-of-alloys`. Statuses: `Backlog`, `En cours`, `Review`, `Fait`.

## Subcommands

No argument means `audit`.

- `audit`: full report, read-only.
- `fix`: apply the corrections of the last report that the user approved.
- `split <n>`: analyse one ticket and propose sub-issues.
- `next`: suggest what to work on next.

## 1. Collect (read-only)

```bash
gh project item-list 6 --owner KerdanetYvan --limit 200 --format json
gh issue list -R KerdanetYvan/age-of-alloys --state all --limit 200 --json number,title,state,labels,milestone,closedAt,updatedAt
gh pr list -R KerdanetYvan/age-of-alloys --state all --limit 200 --json number,title,state,headRefName,mergedAt,closingIssuesReferences
git branch -a
git log main --oneline
```

- Parents and sub-issues: `gh api graphql -f query='query{repository(owner:"KerdanetYvan",name:"age-of-alloys"){issue(number:<n>){parent{number} subIssues(first:50){nodes{number state}}}}}'` (one call per ticket that needs it, as in `start-ticket`).
- Code: registered items in `ModItems` and `ModCreativeTabs`, assets under `src/main/resources/assets/ageofalloys/` (`models`, `textures`, `lang`), data under `src/main/resources/data/ageofalloys/`, and `TODO` / `FIXME` in `src/` (use Grep).
- Docs: `ROADMAP.md`, `docs/NOTIONS.md`.

## 2. Detection rules

Each finding gets a number and a proposed correction.

1. Ticket "En cours" or "Review" whose PR is merged: propose "Fait". Wait ~15 s and re-check first; the automation may just be late.
2. Ticket closed but not "Fait": propose "Fait".
3. Ticket "En cours" with no branch `*/<n>-*` and no commit mentioning it: propose "Backlog".
4. Ticket "En cours" with no activity for more than 14 days: flag as stale.
5. Branch or PR with no matching ticket: propose `new-ticket`.
6. Shipped code with no ticket: an item (or family of items) in `ModItems` that no issue or PR mentions: propose `new-ticket`.
7. Ticket without label, without milestone, or missing from the board: propose the fix (`gh issue edit`, `gh project item-add`).
8. Parent whose sub-issues are all closed but still open: propose closing it. Parent "En cours" with no sub-issue "En cours": flag it.
9. `ROADMAP.md`: a step marked done while its milestone still has open tickets, or the opposite.
10. `TODO` / `FIXME` in the code with no ticket: propose `new-ticket`.
11. `docs/NOTIONS.md`: notions marked `[*]` waiting for the user to validate; remind them.

## 3. Split analysis

A ticket is a split candidate if it shows at least two of these signs: more than 5 "definition of done" items, several independent deliverables, a whole family of items bundled together, a title with "et" / "and". Skip tickets that are already parents or closed.

Propose the breakdown (French title, label and one line per sub-issue, same milestone as the parent), wait for approval, then create them following the "Sub-issues" section of `new-ticket`.

## 4. Next

Among "Backlog" tickets, rank by: oldest unfinished milestone, then sub-issue order, then size (small first). Give the top 3 with one sentence each and suggest `start-ticket <n>`. Start nothing.

## 5. Report format

In French. One table per category (Incohérences, Tickets manquants, Découpages suggérés, Obsolètes, Docs). Each row: ticket as a markdown link, the finding, and the numbered proposed correction. End with "Dites-moi quels numéros appliquer". If everything is consistent, say so in one line.

## 6. Fix

Apply only the numbers the user approved, in one batch after a single recap and confirmation. Use the commands of `start-ticket` (status change), `new-ticket` (creation), `gh issue edit` / `gh issue close` (metadata, closing).

## Rules

- `audit`, `split` and `next` change nothing: no board write, no ticket created, no project file edited.
- Never force "Review" or "Fait" when the automation could still set it; re-check after ~15 s, as in `open-pr`.
- Never tick `[x]` in `docs/NOTIONS.md`.
- Never close, delete or merge anything without explicit approval. Never merge a PR.
- Tickets are always created through `new-ticket` (duplicate check, bilingual body, label, milestone).
- `/board` starts no work: that is `start-ticket`.
