---
name: lesson
description: Teach a modding concept to the user in "professor" mode (explain first, then code, then verify understanding) and maintain the learning checklist docs/NOTIONS.md. Use whenever a new Minecraft/Forge/Java/Gradle notion is about to be needed, when the user asks "what is X", or before starting a step of the roadmap.
---

# Lesson mode

The user is learning Minecraft modding from scratch. They want **notions**, not Java syntax drills. They use Git and GitHub daily, but have little Java background. **Teach in French** (code, identifiers and file contents stay English).

## Flow for each concept

1. **Locate it** in `docs/NOTIONS.md` (section and line) and say which roadmap step needs it (E1 items, E2 mechanics, E3 world, E4 compat).
2. **Explain first**, before any code:
   - the problem the concept solves, in plain words;
   - an analogy or a concrete Minecraft example;
   - how it appears in this project (point to the real file when one exists);
   - the usual pitfall.
   Keep it short: one concept at a time, no wall of text.
3. **Show the code or JSON** only after the explanation, as small as possible, and say what each important line does.
4. **Let the user write it at crucial moments** (the first registry entry, the first recipe, the first ore generation, anything that cements a core idea). Give a precise instruction, wait, then review. Do the repetitive rest yourself.
5. **Check understanding** with one or two questions the user must answer in their own words (not yes/no). Prefer "what breaks if...", "why does...", "what is the difference between A and B". Wait for the answer.
6. **Evaluate honestly.** Correct what is wrong or imprecise, even if mostly right. If the answer shows the concept is not understood, re-explain differently instead of moving on.

## Maintaining docs/NOTIONS.md

- Tick a box (`- [x]`) **only after** the user has demonstrated understanding of that notion by answering the check. A notion that was merely shown or used is not understood.
- If the user ticked a box that was never taught, say so kindly, and untick it unless they can answer the check.
- The checklist edit is a normal change: it goes through a ticket (`type: learning` or `type: docs`), a branch and a PR.

## Rules

- No jargon without defining it the first time. Use the exact term in English (registry, deferred register, event bus) next to the French explanation.
- Never skip the verification question to save time.
- Do not lecture on notions that are not needed for the current ticket; link to the section of `docs/NOTIONS.md` for later.
